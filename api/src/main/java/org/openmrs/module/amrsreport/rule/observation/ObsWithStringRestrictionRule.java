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

package org.openmrs.module.amrsreport.rule.observation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.result.Result;
import org.openmrs.module.amrsreport.cache.CacheUtils;
import org.openmrs.module.amrsreport.rule.EvaluableConstants;
import org.openmrs.module.amrsreport.rule.EvaluableParameter;
import org.openmrs.module.amrsreport.rule.util.RuleUtils;

/**
 * @see ObsWithObjectRestrictionRule
 */
public class ObsWithStringRestrictionRule extends ObsWithRestrictionRule {

	private static final Log log = LogFactory.getLog(ObsWithStringRestrictionRule.class);

	public static final String TOKEN = "MOH Obs String Restrictions";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, Integer, java.util.Map)
	 */
	@Override
	protected Result evaluate(final LogicContext context, final Integer patientId, final Map<String, Object> parameters) throws LogicException {

		List<Concept> conceptList = new ArrayList<Concept>();
		Object paramConceptObjects = parameters.get(EvaluableConstants.OBS_CONCEPT);
		if (RuleUtils.isValidCollectionObject(paramConceptObjects)) {
			Collection conceptObjects = (Collection) paramConceptObjects;
			for (Object conceptObject : conceptObjects) {
				Concept concept = CacheUtils.getConcept(String.valueOf(conceptObject));
				if (concept != null)
					conceptList.add(concept);
			}
			// trying to pass invalid list of concepts
			if (CollectionUtils.isEmpty(conceptList))
				throw new LogicException("Passing invalid list of concept attempted. Processed parameters are " + conceptObjects);

			parameters.put(EvaluableConstants.OBS_CONCEPT, conceptList);
		}

		List<Concept> answerList = new ArrayList<Concept>();
		Object paramAnswerObjects = parameters.get(EvaluableConstants.OBS_VALUE_CODED);
		if (RuleUtils.isValidCollectionObject(paramAnswerObjects)) {
			Collection answerObjects = (Collection) paramAnswerObjects;
			for (Object answerObject : answerObjects) {
				Concept answer = CacheUtils.getConcept(String.valueOf(answerObject));
				if (answer != null)
					answerList.add(answer);
			}
			// trying to pass invalid list of answers
			if (CollectionUtils.isEmpty(answerList))
				throw new LogicException("Passing invalid list of value coded attempted. Processed parameters are " + answerObjects);

			parameters.put(EvaluableConstants.OBS_VALUE_CODED, answerList);
		}

		ObsWithRestrictionRule obsWithRestrictionRule = new ObsWithObjectRestrictionRule();
		return obsWithRestrictionRule.eval(context, patientId, parameters);
	}

	/**
	 * Get the definition of each parameter that should be passed to this rule execution
	 *
	 * @return all parameter that applicable for each rule execution
	 */
	@Override
	public Set<EvaluableParameter> getEvaluationParameters() {
		Set<EvaluableParameter> evaluableParameters = new HashSet<EvaluableParameter>();
		evaluableParameters.add(EvaluableConstants.OPTIONAL_OBS_CONCEPT_PARAMETER_DEFINITION);
		evaluableParameters.add(EvaluableConstants.OPTIONAL_OBS_ENCOUNTER_PARAMETER_DEFINITION);
		evaluableParameters.add(EvaluableConstants.OPTIONAL_OBS_VALUE_CODED_PARAMETER_DEFINITION);
		evaluableParameters.add(EvaluableConstants.OPTIONAL_OBS_FETCH_ORDER_PARAMETER_DEFINITION);
		evaluableParameters.add(EvaluableConstants.OPTIONAL_OBS_FETCH_SIZE_PARAMETER_DEFINITION);
		// TODO: in the future we might need to add the location here. but for now, there's no need to do that yet
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

	/**
	 * Whether the result of the rule should be cached or not
	 *
	 * @return true if the system should put the result into the caching system
	 */
	@Override
	protected Boolean cacheable() {
		return Boolean.TRUE;
	}
}
