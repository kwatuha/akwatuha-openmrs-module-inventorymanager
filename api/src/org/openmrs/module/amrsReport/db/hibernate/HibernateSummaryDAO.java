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
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.EncounterType;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.amrsReport.Mapping;
import org.openmrs.module.amrsReport.MappingType;
import org.openmrs.module.amrsReport.Summary;
import org.openmrs.module.amrsReport.db.SummaryDAO;

import java.util.List;

/**
 * Hibernate operation from the summary module
 */
public class HibernateSummaryDAO implements SummaryDAO {

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
	 * @see org.openmrs.module.clinicalsummary.db.SummaryDAO#saveSummary(org.openmrs.module.clinicalsummary.Summary)
	 */
	public Summary saveSummary(final Summary summary) throws DAOException {
		sessionFactory.getCurrentSession().saveOrUpdate(summary);
		return summary;
	}

	/**
	 * @see org.openmrs.module.clinicalsummary.db.SummaryDAO#getSummary(Integer)
	 */
	public Summary getSummary(final Integer id) throws DAOException {
		return (Summary) sessionFactory.getCurrentSession().get(Summary.class, id);
	}

	/**
	 * @see org.openmrs.module.clinicalsummary.db.SummaryDAO#getAllSummaries(Boolean)
	 */
	@SuppressWarnings("unchecked")
	public List<Summary> getAllSummaries(final Boolean includeRetired) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Summary.class);
		criteria.add(Restrictions.eq("retired", includeRetired));
		return criteria.list();
	}

	/**
	 * @see org.openmrs.module.clinicalsummary.db.SummaryDAO#saveMapping(org.openmrs.module.clinicalsummary.Mapping)
	 */
	public Mapping saveMapping(final Mapping mapping) throws DAOException {
		sessionFactory.getCurrentSession().saveOrUpdate(mapping);
		return mapping;
	}

	/**
	 * @see org.openmrs.module.clinicalsummary.db.SummaryDAO#getMapping(Integer)
	 */
	public Mapping getMapping(final Integer id) throws DAOException {
		return (Mapping) sessionFactory.getCurrentSession().get(Mapping.class, id);
	}

	/**
	 * @see org.openmrs.module.clinicalsummary.db.SummaryDAO#getAllMappings()
	 */
	@SuppressWarnings("unchecked")
	public List<Mapping> getAllMappings() throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Mapping.class);
		criteria.add(Restrictions.eq("retired", false));
		return criteria.list();
	}

	/**
	 * @see org.openmrs.module.clinicalsummary.db.SummaryDAO#getMappings(org.openmrs.module.clinicalsummary.Summary,
	 *      org.openmrs.EncounterType, org.openmrs.module.clinicalsummary.MappingType)
	 */
	@SuppressWarnings("unchecked")
	public List<Mapping> getMappings(final Summary summary, final EncounterType encounterType, final MappingType mappingType) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Mapping.class);

		if (summary != null)
			criteria.add(Restrictions.eq("summary", summary));

		if (encounterType != null)
			criteria.add(Restrictions.eq("encounterType", encounterType));

		if (mappingType != null)
			criteria.add(Restrictions.eq("mappingType", mappingType));

		criteria.add(Restrictions.eq("retired", false));
		return criteria.list();
	}
}
