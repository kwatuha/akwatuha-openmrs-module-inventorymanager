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

import org.openmrs.EncounterType;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.amrsReport.Mapping;
import org.openmrs.module.amrsReport.MappingType;
import org.openmrs.module.amrsReport.Summary;

import java.util.List;

public interface SummaryDAO {

	Summary saveSummary(final Summary summary) throws DAOException;

	Summary getSummary(final Integer id) throws DAOException;

	List<Summary> getAllSummaries(final Boolean includeRetired) throws DAOException;

	/* Mapping Section */

	Mapping saveMapping(final Mapping mapping) throws DAOException;

	Mapping getMapping(final Integer id) throws DAOException;

	List<Mapping> getAllMappings() throws DAOException;

	List<Mapping> getMappings(final Summary summary, final EncounterType encounterType, final MappingType mappingType) throws DAOException;
}
