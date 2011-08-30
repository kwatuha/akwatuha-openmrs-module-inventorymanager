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

import org.openmrs.Cohort;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.amrsReport.Index;
import org.openmrs.module.amrsReport.Summary;

import java.util.Date;
import java.util.List;


/**
 *
 */
public interface IndexDAO {

	Index saveIndex(final Index index) throws DAOException;

	Index getIndex(final Integer id) throws DAOException;

	Index getIndex(final Patient patient, final Summary summary) throws DAOException;

	List<Index> getIndexes(final Patient patient) throws DAOException;

	List<Index> getIndexes(final Location location, final Summary summary, final Date startVisitDate, final Date endVisitDate) throws DAOException;

	Cohort getIndexCohort(final Location location, final Summary summary, final Date startVisitDate, final Date endVisitDate) throws DAOException;

	Integer saveInitialDate(final Location location, final Date date) throws DAOException;

	Date getInitialDate(final Location location) throws DAOException;
}
