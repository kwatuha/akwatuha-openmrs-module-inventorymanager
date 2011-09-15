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

package org.openmrs.module.amrsreport.rule.who;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.result.Result;
import org.openmrs.module.amrsreport.rule.EvaluableConstants;
import org.openmrs.module.amrsreport.rule.EvaluableParameter;
import org.openmrs.module.amrsreport.rule.EvaluableRule;
import org.openmrs.module.amrsreport.rule.observation.ObsWithRestrictionRule;
import org.openmrs.module.amrsreport.rule.observation.ObsWithStringRestrictionRule;
import org.openmrs.module.amrsreport.rule.util.ResultUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Parameters: <ul> <li>[Required] concept: the concept in question </li> <ul>
 */
public class WHOStageRule extends EvaluableRule {

	private static final Log log = LogFactory.getLog(WHOStageRule.class);

	public static final String TOKEN = "MOH WHO Stage";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, Integer, java.util.Map)
	 */
	@Override
	protected Result evaluate(final LogicContext context, final Integer patientId, final Map<String, Object> parameters) throws LogicException {
		Result result = new Result();

		if (!parameters.containsKey(EvaluableConstants.OBS_CONCEPT))
			throw new LogicException("Insufficient parameters to execute rule. Missing parameter: " + EvaluableConstants.OBS_CONCEPT);

		if (log.isDebugEnabled())
			log.debug("Processing adult who stage ...");

		parameters.put(EvaluableConstants.OBS_FETCH_SIZE, 1);

		ObsWithRestrictionRule obsWithRestrictionRule = new ObsWithStringRestrictionRule();
		Result whoStageResults = obsWithRestrictionRule.eval(context, patientId, parameters);

		if (log.isDebugEnabled())
			log.debug("Patient: " + patientId + ", who stages: " + whoStageResults);

		int highestStage = 0;
		for (Result whoStageResult : whoStageResults) {
			int whoStage = NumberUtils.toInt(ResultUtils.stripToDigit(whoStageResult.toString()), 0);
			if (whoStage > highestStage) {
				highestStage = whoStage;
				result = whoStageResult;
			}
		}

		return result;
	}

	/**
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	@Override
	public String[] getDependencies() {
		return new String[]{ObsWithStringRestrictionRule.TOKEN};
	}

	/**
	 * Get the definition of each parameter that should be passed to this rule execution
	 *
	 * @return all parameter that applicable for each rule execution
	 */
	@Override
	public Set<EvaluableParameter> getEvaluationParameters() {
		Set<EvaluableParameter> evaluableParameters = new HashSet<EvaluableParameter>();
		evaluableParameters.add(EvaluableConstants.REQUIRED_OBS_CONCEPT_PARAMETER_DEFINITION);
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
