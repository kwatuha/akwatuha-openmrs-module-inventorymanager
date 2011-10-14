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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.result.Result;
import org.openmrs.module.amrsreport.cache.CacheUtils;
import org.openmrs.module.amrsreport.rule.EvaluableConstants;
import org.openmrs.module.amrsreport.rule.EvaluableNameConstants;
import org.openmrs.module.amrsreport.rule.EvaluableParameter;
import org.openmrs.module.amrsreport.rule.EvaluableRule;
import org.openmrs.module.amrsreport.rule.encounter.EncounterWithRestrictionRule;
import org.openmrs.module.amrsreport.rule.encounter.EncounterWithStringRestrictionRule;
import org.openmrs.module.amrsreport.rule.observation.ObsWithRestrictionRule;
import org.openmrs.module.amrsreport.rule.observation.ObsWithStringRestrictionRule;
import org.openmrs.util.OpenmrsUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author ningosi
 */
public class CTXTreatmentRule extends EvaluableRule {

	private static final Log log = LogFactory.getLog(CTXTreatmentRule.class);

	public static final String TOKEN = "MOH CTX TreatmentRule";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, Integer, java.util.Map)
	 */
	@Override
	protected Result evaluate(final LogicContext context, final Integer patientId, final Map<String, Object> parameters) throws LogicException {

		Result result = new Result();
		// check if the caller already pass encounter list object in the parameter
		try{
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
			//get ctx start date and stop date
			parameters.put(EvaluableConstants.OBS_CONCEPT, Arrays.asList(EvaluableNameConstants.PCP_PROPHYLAXIS_STARTED,EvaluableNameConstants.REASON_PCP_PROPHYLAXIS_STOPPED));
			//get ctx stop date
			
			Result ctxDatesResults = obsWithRestrictionRule.eval(context, patientId, parameters);
				//check wether we have the  dates from the obs it should not be empty
			if (CollectionUtils.isNotEmpty(ctxDatesResults)) {
				result.addAll(ctxDatesResults);
			}
			else{
				// process the plan when there's no start and stop dates
				
				parameters.put(EvaluableConstants.OBS_CONCEPT, Arrays.asList(EvaluableNameConstants.PCP_PROPHYLAXIS_PLAN));
				parameters.put(EvaluableConstants.OBS_VALUE_CODED, Arrays.asList(EvaluableNameConstants.CONTINUE_REGIMEN,
						EvaluableNameConstants.REFILLED, EvaluableNameConstants.DOSING_CHANGE, EvaluableNameConstants.START_DRUGS));
				
				Result planResults = obsWithRestrictionRule.eval(context, patientId, parameters);
				 if (CollectionUtils.isNotEmpty(planResults)) {
					// process the reported when there's a correct plan
					 parameters.remove(EvaluableConstants.OBS_VALUE_CODED);
					 
					 parameters.put(EvaluableConstants.OBS_CONCEPT, Arrays.asList(EvaluableNameConstants.PATIENT_REPORTED_CURRENT_PCP_PROPHYLAXIS));
					 
					 Result reportedResults = obsWithRestrictionRule.eval(context, patientId, parameters);
					 
					 Concept noneConcept = CacheUtils.getConcept(EvaluableNameConstants.NONE);
					 
					 Integer counter = 0;
						Boolean noneExists = Boolean.FALSE;
						Result validReportedResults = new Result();
						while (counter < reportedResults.size() && !noneExists) {
							Result reportedResult = reportedResults.get(counter++);
							// if we have none, then clear all reported
							if (!OpenmrsUtil.nullSafeEquals(reportedResult.toConcept(), noneConcept))
								validReportedResults.add(reportedResult);

							if (OpenmrsUtil.nullSafeEquals(reportedResult.toConcept(), noneConcept))
								noneExists = Boolean.TRUE;
						}

						if (!noneExists)
							result.addAll(validReportedResults);
				 }
				
			}
			// process the current  medication
			parameters.put(EvaluableConstants.OBS_CONCEPT, Arrays.asList(EvaluableNameConstants.CURRENT_MEDICATIONS));
			parameters.put(EvaluableConstants.OBS_VALUE_CODED, Arrays.asList(EvaluableNameConstants.TRIMETHOPRIM_AND_SULFAMETHOXAZOLE));
			Result currentResults = obsWithRestrictionRule.eval(context, patientId, parameters);
			result.addAll(currentResults);
		}
		}
		catch (NullPointerException nullPointerException) {
			// TODO: handle exception
			nullPointerException.toString();
		}
		return result;
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
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	@Override
	public String[] getDependencies() {
		return new String[]{ObsWithStringRestrictionRule.TOKEN, EncounterWithStringRestrictionRule.TOKEN};
	}

	/**
	 * Get the definition of each parameter that should be passed to this rule execution
	 *
	 * @return all parameter that applicable for each rule execution
	 */
	@Override
	public Set<EvaluableParameter> getEvaluationParameters() {
		Set<EvaluableParameter> evaluableParameters = new HashSet<EvaluableParameter>();
		evaluableParameters.add(EvaluableConstants.REQUIRED_ENCOUNTER_TYPE_PARAMETER_DEFINITION);
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
