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
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.amrsreport.rule.EvaluableNameConstants;
import org.openmrs.module.amrsreport.rule.EvaluableRule;

/**
 * Author ningosi
 */
/**
 * Parameters: <ul> <li>[Optional] encounterType: list of all applicable encounter types </ul>
 */

public class TBStartDateRule extends EvaluableRule {

	private static final Log log = LogFactory.getLog(TBStartDateRule.class);

	public static final String TOKEN = "MOH TB Start Date";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, Integer, java.util.Map)
	 */
	//@Override
	public Result evaluate(final LogicContext context, final Integer patientId, final Map<String, Object> parameters) throws LogicException {
		
		Result result = new Result();
		Date startDate=null;
		
		//get concepts to use here plan, start and stop
		Concept TBPlan=Context.getConceptService().getConcept(EvaluableNameConstants.TUBERCULOSIS_TREATMENT_PLAN);
		Concept TBStartDate=Context.getConceptService().getConcept(EvaluableNameConstants.START_DRUGS);
		
		
		//find the patient
		Patient patient = context.getPatient(patientId);
		
		//find obs based on plan and the start dates 
		List<Obs> obsTBStart=Context.getObsService().getObservationsByPersonAndConcept(patient, TBPlan);
		
		
		//loop through the obsTBStart list of obs
		for(Obs observations:obsTBStart){
			if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(TBStartDate))
			{
				
			startDate=observations.getObsDatetime();
		    Result tbStartResult = new Result(startDate);
			result.add(tbStartResult);
			break;
			}
		}
		
		log.info(result);
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
