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

package org.openmrs.module.amrsreport.rule.medication;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.result.Result;
import org.openmrs.module.amrsreport.rule.EvaluableConstants;
import org.openmrs.module.amrsreport.rule.EvaluableNameConstants;
import org.openmrs.module.amrsreport.rule.EvaluableParameter;
import org.openmrs.module.amrsreport.rule.EvaluableRule;
import org.openmrs.module.amrsreport.rule.encounter.EncounterWithRestrictionRule;
import org.openmrs.module.amrsreport.rule.encounter.EncounterWithStringRestrictionRule;
import org.openmrs.module.amrsreport.rule.observation.ObsWithRestrictionRule;
import org.openmrs.module.amrsreport.rule.observation.ObsWithStringRestrictionRule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Parameters: <ul> <li>[Optional] encounterType: list of all applicable encounter types </ul>
 */
public class CryptococcalRule extends EvaluableRule {

	private static final Log log = LogFactory.getLog(CryptococcalRule.class);

	public static final String TOKEN = "MOH Cryptococcal Medications";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, Integer, java.util.Map)
	 */
	@Override
	protected Result evaluate(final LogicContext context, final Integer patientId, final Map<String, Object> parameters) throws LogicException {
		Result result = new Result();
		// check if the caller already pass encounter list object in the parameter
		Object encounters = parameters.get(EvaluableConstants.OBS_ENCOUNTER);
		if (encounters == null) {
			EncounterWithRestrictionRule encounterWithRestrictionRule = new EncounterWithStringRestrictionRule();
			parameters.put(EvaluableConstants.ENCOUNTER_FETCH_SIZE, 1);
			Result encounterResults = encounterWithRestrictionRule.eval(context, patientId, parameters);
			if (CollectionUtils.isNotEmpty(encounterResults)) {
				Result latestResult = encounterResults.latest();
				encounters = Arrays.asList(latestResult.getResultObject());
			}
		}
		// if the caller didn't pass any encounter list and we can't find any encounter in the database, then just ignore the rest of the process
		if (encounters != null) {
			parameters.put(EvaluableConstants.OBS_ENCOUNTER, encounters);
			ObsWithRestrictionRule obsWithRestrictionRule = new ObsWithStringRestrictionRule();
			// get the plan
			parameters.put(EvaluableConstants.OBS_CONCEPT, Arrays.asList(EvaluableNameConstants.CRYPTOCOCCAL_TREATMENT_PLAN));
			parameters.put(EvaluableConstants.OBS_VALUE_CODED, Arrays.asList(EvaluableNameConstants.START_DRUGS,
					EvaluableNameConstants.REFILLED, EvaluableNameConstants.CONTINUE_REGIMEN));

			Result planResults = obsWithRestrictionRule.eval(context, patientId, parameters);
			Concept fluconazoleConcept = Context.getConceptService().getConcept(EvaluableNameConstants.FLUCONAZOLE);
			if (CollectionUtils.isNotEmpty(planResults))
				result.add(new Result(fluconazoleConcept));

		}

		return result;
	}

	/**
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	@Override
	public String[] getDependencies() {
		return new String[]{ObsWithStringRestrictionRule.TOKEN, EncounterWithStringRestrictionRule.TOKEN};
	}

	/**
	 * Whether the result of the rule should be cached or not
	 *
	 * @return true if the system should put the result into the caching system
	 */
	@Override
	protected Boolean cacheable () {
		return Boolean.TRUE;
	}

	/**
	 * Get the definition of each parameter that should be passed to this rule execution
	 *
	 * @return all parameter that applicable for each rule execution
	 */
	@Override
	public Set<EvaluableParameter> getEvaluationParameters() {
		Set<EvaluableParameter> evaluableParameters = new HashSet<EvaluableParameter>();
		evaluableParameters.add(EvaluableConstants.OPTIONAL_ENCOUNTER_TYPE_PARAMETER_DEFINITION);
		evaluableParameters.add(EvaluableConstants.OPTIONAL_OBS_ENCOUNTER_PARAMETER_DEFINITION);
		return evaluableParameters;
	}

	/**
	 * Get the token name of the rule that can be used to reference the rule from LogicService
	 *
	 * @return the token name
	 */
	@Override
	protected String getEvaluableToken() {
		return TOKEN;
	}
}
