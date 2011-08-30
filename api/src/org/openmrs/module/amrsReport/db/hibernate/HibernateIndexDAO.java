/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.amrsReport.db.hibernate;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Cohort;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.amrsReport.Index;
import org.openmrs.module.amrsReport.Summary;
import org.openmrs.module.amrsReport.db.IndexDAO;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 */
public class HibernateIndexDAO implements IndexDAO {

	private SessionFactory sessionFactory;

	/**
	 * Method that will be called by Spring to inject the Hibernate's SessionFactory.
	 *
	 * @param sessionFactory the session factory to be injected
	 */
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @see IndexDAO#saveIndex(org.openmrs.module.clinicalsummary.Index)
	 */
	public Index saveIndex(final Index index) throws DAOException {
		sessionFactory.getCurrentSession().saveOrUpdate(index);
		return index;
	}

	/**
	 * @see IndexDAO#getIndex(Integer)
	 */
	public Index getIndex(final Integer id) throws DAOException {
		return (Index) sessionFactory.getCurrentSession().get(Index.class, id);
	}

	/**
	 * @see IndexDAO#getIndex(org.openmrs.Patient, org.openmrs.module.clinicalsummary.Summary)
	 */
	public Index getIndex(final Patient patient, final Summary summary) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Index.class);
		criteria.add(Restrictions.eq("patient", patient));
		criteria.add(Restrictions.eq("summary", summary));
		return (Index) criteria.uniqueResult();
	}

	/**
	 * @see IndexDAO#getIndexes(org.openmrs.Patient)
	 */
	@SuppressWarnings("unchecked")
	public List<Index> getIndexes(final Patient patient) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Index.class);
		criteria.add(Restrictions.eq("patient", patient));
		return criteria.list();
	}

	/**
	 * @see IndexDAO#getIndexes(org.openmrs.Location, org.openmrs.module.clinicalsummary.Summary, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<Index> getIndexes(final Location location, final Summary summary, final Date startVisitDate, final Date endVisitDate) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Index.class);
		criteria.add(Restrictions.eq("location", location));

		if (summary != null)
			criteria.add(Restrictions.eq("summary", summary));

		if (startVisitDate != null)
			criteria.add(Restrictions.ge("returnDate", startVisitDate));

		if (endVisitDate != null)
			criteria.add(Restrictions.le("returnDate", endVisitDate));

		criteria.addOrder(Order.asc("returnDate"));
		return criteria.list();
	}

	/**
	 * @see IndexDAO#getIndexCohort(org.openmrs.Location, org.openmrs.module.clinicalsummary.Summary, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public Cohort getIndexCohort(final Location location, final Summary summary, final Date startVisitDate, final Date endVisitDate) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Index.class);
		criteria.createAlias("patient", "patient");
		criteria.createAlias("patient.identifiers", "identifier");
		criteria.add(Restrictions.eq("location", location));

		if (summary != null)
			criteria.add(Restrictions.eq("summary", summary));

		if (startVisitDate != null)
			criteria.add(Restrictions.ge("returnDate", startVisitDate));

		if (endVisitDate != null)
			criteria.add(Restrictions.le("returnDate", endVisitDate));

		criteria.addOrder(Order.asc("returnDate"));
		criteria.addOrder(Order.asc("identifier.identifier"));
		criteria.setProjection(Projections.property("patient.patientId"));

		Cohort cohort = new Cohort();
		cohort.setMemberIds(new LinkedHashSet<Integer>(criteria.list()));
		return cohort;
	}

	/**
	 * @see IndexDAO#saveInitialDate(org.openmrs.Location, java.util.Date)
	 */
	public Integer saveInitialDate(final Location location, final Date date) throws DAOException {
		String hqlString = "update Index index set index.initialDate = :initialDate where index.location = :location";
		Query query = sessionFactory.getCurrentSession().createQuery(hqlString);
		query.setDate("initialDate", date);
		query.setParameter("location", location);
		return query.executeUpdate();
	}

	/**
	 * @see IndexDAO#getInitialDate(org.openmrs.Location)
	 */
	public Date getInitialDate(final Location location) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Index.class);
		criteria.add(Restrictions.eq("location", location));
		criteria.setProjection(Projections.min("initialDate"));
		return (Date) criteria.uniqueResult();
	}
}
