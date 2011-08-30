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

import org.openmrs.OpenmrsObject;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.amrsReport.Reminder;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 */
public interface ReminderDAO {

	Reminder saveReminder(final Reminder reminder) throws DAOException;

	Reminder getReminder(final Integer id) throws DAOException;

	List<Reminder> getReminders(final Patient patient) throws DAOException;

	List<Reminder> getReminders(final Map<String, Collection<OpenmrsObject>> restrictions,
	                            final Date reminderStart, final Date reminderEnd) throws DAOException;

	List<Object[]> aggregateReminders(final Map<String,Collection<OpenmrsObject>> restrictions, final Collection<String> groupingProperties,
	                                  final Date reminderStart, final Date reminderEnd) throws DAOException;
}
