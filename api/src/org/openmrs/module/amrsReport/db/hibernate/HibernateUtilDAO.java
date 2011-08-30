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

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.OpenmrsObject;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.amrsReport.db.UtilDAO;
import org.openmrs.module.amrsReport.util.obs.OrderedObs;
import org.openmrs.module.amrsReport.util.obs.Status;
import org.openmrs.module.amrsReport.util.response.MedicationResponse;
import org.openmrs.module.amrsReport.util.response.ReminderResponse;
import org.openmrs.module.amrsReport.util.weight.AgeUnit;
import org.openmrs.module.amrsReport.util.weight.Gender;
import org.openmrs.module.amrsReport.util.weight.WeightStandard;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class HibernateUtilDAO implements UtilDAO {

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
	 * @see UtilDAO#saveWeightStandard(org.openmrs.module.clinicalsummary.util.weight.WeightStandard)
	 */
	public WeightStandard saveWeightStandard(final WeightStandard weightStandard) throws DAOException {
		sessionFactory.getCurrentSession().saveOrUpdate(weightStandard);
		return weightStandard;
	}

	/**
	 * @see UtilDAO#getWeightStandard(Integer)
	 */
	public WeightStandard getWeightStandard(final Integer id) throws DAOException {
		return (WeightStandard) sessionFactory.getCurrentSession().get(WeightStandard.class, id);
	}

	/**
	 * @see UtilDAO#getWeightStandard(org.openmrs.module.clinicalsummary.util.weight.Gender, org.openmrs.module.clinicalsummary.util.weight.AgeUnit,
	 *      Integer)
	 */
	public WeightStandard getWeightStandard(final Gender gender, final AgeUnit ageUnit, final Integer age) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WeightStandard.class);

		criteria.add(Restrictions.eq("gender", gender.getValue()));
		criteria.add(Restrictions.eq("ageUnit", ageUnit.getValue()));
		criteria.add(Restrictions.eq("age", age));

		return (WeightStandard) criteria.uniqueResult();
	}

	/**
	 * @see UtilDAO#saveOrderedObs(org.openmrs.module.clinicalsummary.util.obs.OrderedObs)
	 */
	public OrderedObs saveOrderedObs(final OrderedObs orderedObs) throws DAOException {
		sessionFactory.getCurrentSession().saveOrUpdate(orderedObs);
		return orderedObs;
	}

	/**
	 * @see UtilDAO#getOrderedObs(Integer)
	 */
	public OrderedObs getOrderedObs(final Integer id) throws DAOException {
		return (OrderedObs) sessionFactory.getCurrentSession().get(OrderedObs.class, id);
	}

	/**
	 * @see UtilDAO#getOrderedObs(org.openmrs.Patient)
	 */
	@SuppressWarnings("unchecked")
	public List<OrderedObs> getOrderedObs(final Patient patient) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrderedObs.class);
		criteria.add(Restrictions.eq("patient", patient));
		return criteria.list();
	}

	/**
	 * @see UtilDAO#getOrderedObs(java.util.Map, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<OrderedObs> getOrderedObs(final Map<String, Collection<OpenmrsObject>> restrictions,
	                                      final Date startTime, final Date endTime) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrderedObs.class);

		if (MapUtils.isNotEmpty(restrictions)) {
			OrderedObs orderedObs = new OrderedObs();
			for (String property : restrictions.keySet()) {
				Collection<OpenmrsObject> objects = restrictions.get(property);
				if (CollectionUtils.isNotEmpty(objects) && PropertyUtils.isReadable(orderedObs, property))
					criteria.add(Restrictions.in(property, objects));
			}
		}

		if (startTime != null)
			criteria.add(Restrictions.ge("orderedDatetime", startTime));

		if (endTime != null)
			criteria.add(Restrictions.le("orderedDatetime", endTime));

		return criteria.list();
	}

	/**
	 * @param patients
	 * @see UtilDAO#deleteOrderedObs(java.util.List
	 */
	public Integer deleteOrderedObs(final List<Patient> patients) throws DAOException {
		String queryString = "delete OrderedObs orderedObs where person in (:patients)";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setParameterList("patients", patients);
		return query.executeUpdate();
	}

	/**
	 * @see UtilDAO#getOrderedObs(java.util.Map, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> aggregateOrderedObs(final Map<String, Collection<OpenmrsObject>> restrictions, final Collection<String> groupingProperties,
	                                          final Status status, final Date startTime, final Date endTime) throws DAOException {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrderedObs.class);

		if (MapUtils.isNotEmpty(restrictions)) {
			OrderedObs orderedObs = new OrderedObs();
			for (String property : restrictions.keySet()) {
				Collection<OpenmrsObject> objects = restrictions.get(property);
				if (CollectionUtils.isNotEmpty(objects) && PropertyUtils.isReadable(orderedObs, property))
					criteria.add(Restrictions.in(property, objects));
			}
		}

		if (status != null)
			criteria.add(Restrictions.eq("status", status));

		if (startTime != null)
			criteria.add(Restrictions.ge("orderedDatetime", startTime));

		if (endTime != null)
			criteria.add(Restrictions.le("orderedDatetime", endTime));

		ProjectionList projectionList = Projections.projectionList();
		for (String groupingProperty : groupingProperties) {
			// group by the property and order by the same property desc and the property must not null
			criteria.add(Restrictions.isNotNull(groupingProperty));
			projectionList.add(Projections.groupProperty(groupingProperty));
			criteria.addOrder(Order.asc(groupingProperty));
		}
		// add the row count projection to the projection list
		projectionList.add(Projections.rowCount());
		criteria.setProjection(projectionList);

		return criteria.list();
	}

	/**
	 * @see UtilDAO#saveResponses(java.util.List)
	 * @param responses
	 */
	public List<? extends BaseOpenmrsData> saveResponses(final List<? extends BaseOpenmrsData> responses) {
		int counter = 0;
		Session session = sessionFactory.getCurrentSession();
		for (BaseOpenmrsData response : responses) {
			session.saveOrUpdate(response);
			if (counter % 20 == 0) {
				session.flush();
				session.clear();
			}
			counter++;
		}
		return responses;
	}

	/**
	 * @see UtilDAO#getMedicationResponses(org.openmrs.Patient)
	 */
	@SuppressWarnings("unchecked")
	public List<MedicationResponse> getMedicationResponses(final Patient patient) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MedicationResponse.class);

		criteria.add(Restrictions.eq("patient", patient));
		criteria.add(Restrictions.eq("voided", Boolean.FALSE));

		return criteria.list();
	}

	/**
	 * @see UtilDAO#getMedicationResponses(org.openmrs.Patient)
	 */
	@SuppressWarnings("unchecked")
	public List<ReminderResponse> getReminderResponses(final Patient patient) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ReminderResponse.class);

		criteria.add(Restrictions.eq("patient", patient));
		criteria.add(Restrictions.eq("voided", Boolean.FALSE));

		return criteria.list();
	}
}
