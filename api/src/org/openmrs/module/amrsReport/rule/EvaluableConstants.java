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
package org.openmrs.module.amrsReport.rule;

import java.util.List;

public interface EvaluableConstants {

	String ENCOUNTER_TYPE = "encounterType";

	String ENCOUNTER_LOCATION = "location";

	String ENCOUNTER_PROVIDER = "provider";

	String ENCOUNTER_FETCH_SIZE = "encounter.size";

	String ENCOUNTER_FETCH_ORDER = "encounter.order";

	String OBS_ENCOUNTER = "encounter";

	String OBS_LOCATION = "location";

	String OBS_CONCEPT = "concept";

	String OBS_VALUE_CODED = "valueCoded";

	String OBS_FETCH_SIZE = "obs.size";

	String OBS_FETCH_ORDER = "obs.order";

	EvaluableParameter REQUIRED_ENCOUNTER_TYPE_PARAMETER_DEFINITION = new EvaluableParameter(ENCOUNTER_TYPE, List.class, Boolean.TRUE);

	EvaluableParameter REQUIRED_ENCOUNTER_FETCH_ORDER_PARAMETER_DEFINITION = new EvaluableParameter(ENCOUNTER_FETCH_ORDER, String.class, Boolean.TRUE);

	EvaluableParameter REQUIRED_ENCOUNTER_FETCH_SIZE_PARAMETER_DEFINITION = new EvaluableParameter(ENCOUNTER_FETCH_SIZE, Integer.class, Boolean.TRUE);

	EvaluableParameter REQUIRED_OBS_CONCEPT_PARAMETER_DEFINITION = new EvaluableParameter(OBS_CONCEPT, List.class, Boolean.TRUE);

	EvaluableParameter REQUIRED_OBS_FETCH_ORDER_PARAMETER_DEFINITION = new EvaluableParameter(OBS_FETCH_ORDER, String.class, Boolean.TRUE);

	EvaluableParameter REQUIRED_OBS_FETCH_SIZE_PARAMETER_DEFINITION = new EvaluableParameter(OBS_FETCH_SIZE, Integer.class, Boolean.TRUE);

	EvaluableParameter REQUIRED_OBS_VALUE_CODED_PARAMETER_DEFINITION = new EvaluableParameter(OBS_VALUE_CODED, List.class, Boolean.TRUE);

	EvaluableParameter OPTIONAL_ENCOUNTER_TYPE_PARAMETER_DEFINITION = new EvaluableParameter(ENCOUNTER_TYPE, List.class, Boolean.FALSE);

	EvaluableParameter OPTIONAL_ENCOUNTER_FETCH_ORDER_PARAMETER_DEFINITION = new EvaluableParameter(ENCOUNTER_FETCH_ORDER, String.class, Boolean.FALSE);

	EvaluableParameter OPTIONAL_ENCOUNTER_FETCH_SIZE_PARAMETER_DEFINITION = new EvaluableParameter(ENCOUNTER_FETCH_SIZE, Integer.class, Boolean.FALSE);

	EvaluableParameter OPTIONAL_OBS_CONCEPT_PARAMETER_DEFINITION = new EvaluableParameter(OBS_CONCEPT, List.class, Boolean.FALSE);

	EvaluableParameter OPTIONAL_OBS_ENCOUNTER_PARAMETER_DEFINITION = new EvaluableParameter(OBS_ENCOUNTER, List.class, Boolean.FALSE);

	EvaluableParameter OPTIONAL_OBS_FETCH_ORDER_PARAMETER_DEFINITION = new EvaluableParameter(OBS_FETCH_ORDER, String.class, Boolean.FALSE);

	EvaluableParameter OPTIONAL_OBS_FETCH_SIZE_PARAMETER_DEFINITION = new EvaluableParameter(OBS_FETCH_SIZE, Integer.class, Boolean.FALSE);

	EvaluableParameter OPTIONAL_OBS_VALUE_CODED_PARAMETER_DEFINITION = new EvaluableParameter(OBS_VALUE_CODED, List.class, Boolean.FALSE);
}
