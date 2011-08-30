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
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.amrsReport.Loggable;
import org.openmrs.module.amrsReport.db.LoggableDAO;

import java.util.List;

/**
 *
 */
public class HibernateLoggableDAO implements LoggableDAO {

	private SessionFactory sessionFactory;

	/**
	 * Method that will be called by Spring to inject the Hibernate's SessionFactory.
	 *
	 * @param sessionFactory
	 * 		the session factory to be injected
	 */
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @see org.openmrs.module.clinicalsummary.db.LoggableDAO#saveLoggable(org.openmrs.module.clinicalsummary.Loggable)
	 */
	public Loggable saveLoggable(final Loggable loggable) throws DAOException {
		sessionFactory.getCurrentSession().saveOrUpdate(loggable);
		return loggable;
	}

	/**
	 * @see org.openmrs.module.clinicalsummary.db.LoggableDAO#getLoggable(Integer)
	 */
	public Loggable getLoggable(final Integer id) throws DAOException {
		return (Loggable) sessionFactory.getCurrentSession().get(Loggable.class, id);
	}

	/**
	 * @see org.openmrs.module.clinicalsummary.db.LoggableDAO#getLoggables(org.openmrs.Patient)
	 */
	@SuppressWarnings("unchecked")
	public List<Loggable> getLoggables(final Patient patient) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Loggable.class);
		criteria.add(Restrictions.eq("patient", patient));
		return criteria.list();
	}
}
