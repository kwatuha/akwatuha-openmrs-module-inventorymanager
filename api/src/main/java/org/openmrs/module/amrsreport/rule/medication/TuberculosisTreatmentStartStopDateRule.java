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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.amrsreport.rule.EvaluableNameConstants;

/**
 * Author ningosi
 */
/**
 * Parameters: <ul> <li>[Optional] encounterType: list of all applicable encounter types </ul>
 */

public class TuberculosisTreatmentStartStopDateRule implements Rule {

	private static final Log log = LogFactory.getLog(TuberculosisTreatmentStartStopDateRule.class);

	public static final String TOKEN = "MOH TB Start Stop Dates";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, Integer, java.util.Map)
	 */
	//@Override
	public Result eval(final LogicContext context, final Integer patientId, final Map<String, Object> parameters) throws LogicException {
		
		Result result = new Result();
		//get concepts to use here
		Concept TBStartDate=Context.getConceptService().getConcept(EvaluableNameConstants.TUBERCULOSIS_DRUG_TREATMENT_START_DATE);
		Concept TBStopDate=Context.getConceptService().getConcept(EvaluableNameConstants.TUBERCULOSIS_TREATMENT_COMPLETED_DATE);
		
		//find the patient
		Patient patient = context.getPatient(patientId);
		//find obs based on the start and stop dates
		List<Obs> obsTBStart=Context.getObsService().getObservationsByPersonAndConcept(patient, TBStartDate);
		List<Obs> obsTBStop=Context.getObsService().getObservationsByPersonAndConcept(patient, TBStopDate);
		
		//loop through the obsTBStart
		for(Obs observations:obsTBStart){
			Date tbStart=observations.getObsDatetime();
		    Result tbStartResult = new Result(tbStart);
			result.add(tbStartResult);
		}
		
		//loop through the obsTBStop
		
		for(Obs observation:obsTBStop){
			Date tbStop=observation.getObsDatetime();
			Result tbStoptResult = new Result(tbStop);
			result.add(tbStoptResult);
		}
		
		return result;
	}

	
	//return the tokens
	protected String getEvaluableToken() {
		return TOKEN;
 	}
	

	
 	/**
 	 * @see org.openmrs.logic.Rule#getDependencies()
 	 */
	//@Override
 	public String[] getDependencies() {
		return new String[]{};
 	}
 	/**
 	 * Get the definition of each parameter that should be passed to this rule execution
 	 *
 	 * @return all parameter that applicable for each rule execution
 	 */
	
 	@Override
	public Datatype getDefaultDatatype() {
		// TODO Auto-generated method stub
		return Datatype.DATETIME;
 	}
	public Set<RuleParameterInfo> getParameterList() {
		// TODO Auto-generated method stub
		return null;
 	}
	@Override
	public int getTTL() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
