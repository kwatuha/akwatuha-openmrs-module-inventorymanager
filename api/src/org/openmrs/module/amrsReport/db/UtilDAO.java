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
package org.openmrs.module.amrsReport.db;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.OpenmrsObject;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
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
public interface UtilDAO {

	WeightStandard saveWeightStandard(final WeightStandard weightStandard) throws DAOException;

	WeightStandard getWeightStandard(final Integer id) throws DAOException;

	WeightStandard getWeightStandard(final Gender gender, final AgeUnit ageUnit, final Integer age) throws DAOException;

	OrderedObs saveOrderedObs(final OrderedObs orderedObs) throws DAOException;

	OrderedObs getOrderedObs(final Integer id) throws DAOException;

	List<OrderedObs> getOrderedObs(final Patient patient) throws DAOException;

	List<OrderedObs> getOrderedObs(final Map<String, Collection<OpenmrsObject>> restrictions,
	                               final Date startTime, final Date endTime) throws DAOException;

	Integer deleteOrderedObs(final List<Patient> patients) throws DAOException;

	List<Object[]> aggregateOrderedObs(final Map<String, Collection<OpenmrsObject>> restrictions, final Collection<String> groupingProperties,
	                                   final Status status, final Date startTime, final Date endTime) throws DAOException;

	List<? extends BaseOpenmrsData> saveResponses(List<? extends BaseOpenmrsData> responses);

	List<MedicationResponse> getMedicationResponses(Patient patient);

	List<ReminderResponse> getReminderResponses(Patient patient);
}
