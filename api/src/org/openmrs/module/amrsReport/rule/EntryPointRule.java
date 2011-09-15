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

package org.openmrs.module.clinicalsummary.rule.adherence;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.result.Result;
import org.openmrs.module.clinicalsummary.cache.CacheUtils;
import org.openmrs.module.clinicalsummary.rule.EvaluableConstants;
import org.openmrs.module.clinicalsummary.rule.EvaluableNameConstants;
import org.openmrs.module.clinicalsummary.rule.EvaluableParameter;
import org.openmrs.module.clinicalsummary.rule.EvaluableRule;
import org.openmrs.module.clinicalsummary.rule.encounter.EncounterWithRestrictionRule;
import org.openmrs.module.clinicalsummary.rule.encounter.EncounterWithStringRestrictionRule;
import org.openmrs.module.clinicalsummary.rule.observation.ObsWithRestrictionRule;
import org.openmrs.module.clinicalsummary.rule.observation.ObsWithStringRestrictionRule;
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
 */
public class EntryPointRule extends EvaluableRule {

	private static final Log log = LogFactory.getLog(DrugAdherenceRule.class);

	public static final String TOKEN = "MOH Point Of Entry";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, Integer, java.util.Map)
	 */
	@Override
	protected Result evaluate(final LogicContext context, final Integer patientId, final Map<String, Object> parameters) throws LogicException {

		Result result = new Result();
		// list of all dates with missing data
		List<Date> dates = new ArrayList<Date>();
		// time frame for the adherence is six month
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -6);
		Date sixMonthsAgo = calendar.getTime();

		EncounterWithRestrictionRule encounterWithRestrictionRule = new EncounterWithStringRestrictionRule();
		Result encounterResults = encounterWithRestrictionRule.eval(context, patientId, parameters);

		Boolean perfect = Boolean.FALSE;

		Concept noConcept = CacheUtils.getConcept(EvaluableNameConstants.NO);
		Concept allConcept = CacheUtils.getConcept(EvaluableNameConstants.ALL);

		for (Result encounterResult : encounterResults) {

			if (encounterResult.getResultDate().after(sixMonthsAgo)) {
				ObsWithRestrictionRule obsWithRestrictionRule = new ObsWithStringRestrictionRule();

				parameters.put(EvaluableConstants.OBS_ENCOUNTER, Arrays.asList(encounterResult.getResultObject()));
				parameters.put(EvaluableConstants.OBS_CONCEPT, Arrays.asList(EvaluableNameConstants.POINT_OF_HIV_TESTING));
				Result entryPointResults = obsWithRestrictionRule.eval(context, patientId, parameters);

				Concept pointOfHIVTestingConcept = CacheUtils.getConcept(EvaluableNameConstants.POINT_OF_HIV_TESTING);

				String entryPoint = null;

				for (Result entryPointResult : entryPointResults) {
					Obs obs = (Obs) entryPointResult.getResultObject();
					Concept entryPointConcept = entryPointResult.toConcept();
					if (OpenmrsUtil.nullSafeEquals(pointOfHIVTestingConcept, obs.getConcept())
							&& !OpenmrsUtil.nullSafeEquals(entryPointConcept, allConcept)){
						entryPoint = obs.getConcept().getName();
					}
				}

				if (CollectionUtils.isEmpty(entryPointResults) || CollectionUtils.size(entryPointResults) < 2){
					dates.add(encounterResult.getResultDate());
					if (entryPoint.equal(EvaluableNameConstants.MOBILE_VOLUNTARY_COUNSELING_AND_TESTING))
						return new Result("MVCT");
					else if (entryPoint.equal(EvaluableNameConstants.MATERNAL_CHILD_HEALTH_PROGRAM))
						return new Result("MCH");
					else if (entryPoint.equal(EvaluableNameConstants.PREVENTION_OF_MOTHER_TO_CHILD_TRANSMISSION_OF_HIV))
						return new Result("PMTCT");
					else if (entryPoint.equal(EvaluableNameConstants.VOLUNTARY_COUNSELING_AND_TESTING_CENTER))
						return new Result("VCT");
					else if (entryPoint.equal(EvaluableNameConstants.TUBERCULOSIS))
						return new Result("TB");
					else if (entryPoint.equal(EvaluableNameConstants.HOME_BASED_TESTING_PROGRAM))
						return new Result("HCT");
					else if (entryPoint.equal(EvaluableNameConstants.OTHER_NON_CODED))
						return new Result("Other");
					else if (entryPoint.equal(EvaluableNameConstants.INPATIENT_CARE_OR_HOSPITALIZATION))
						return new Result("IPD");
					else if (entryPoint.equal(EvaluableNameConstants.PROVIDER_INITIATED_TESTING_AND_COUNSELING))
						return new Result("PITC");
					else if (entryPoint.equal(EvaluableNameConstants.PEDIATRIC_OUTPATIENT_CLINIC))
						return new Result("POC");
				}else {
					return new Result("Other");
				}
			}
		}
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
