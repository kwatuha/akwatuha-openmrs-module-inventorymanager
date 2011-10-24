package org.openmrs.module.amrsreport.rule;
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

import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;

 
public class EntryPointRule implements Rule {

	private static final Log log = LogFactory.getLog(EntryPointRule.class);

	public static final String TOKEN = "MOH Point Of Entry";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
	 */
    @Override
	public Result eval(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		
		try {
			Patient patient = Context.getPatientService().getPatient(patientId);
                        Integer intPoint = Context.getPersonService().getPersonAttributeTypeByName(EvaluableNameConstants.POINT_OF_HIV_TESTING).getId();
                        int entryPointConcept = Integer.parseInt(patient.getAttribute(intPoint).getValue());
                        String entryPoint = Context.getConceptService().getConcept(entryPointConcept).getName().toString();
			if (entryPoint.equals(EvaluableNameConstants.MOBILE_VOLUNTARY_COUNSELING_AND_TESTING))
                            return new Result("MVCT");
                        else if (entryPoint.equals(EvaluableNameConstants.MATERNAL_CHILD_HEALTH_PROGRAM))
                            return new Result("MCH");
                        else if (entryPoint.equals(EvaluableNameConstants.PREVENTION_OF_MOTHER_TO_CHILD_TRANSMISSION_OF_HIV))
                            return new Result("PMTCT");
                        else if (entryPoint.equals(EvaluableNameConstants.VOLUNTARY_COUNSELING_AND_TESTING_CENTER))
                            return new Result("VCT");
                        else if (entryPoint.equals(EvaluableNameConstants.TUBERCULOSIS))
                            return new Result("TB");
                        else if (entryPoint.equals(EvaluableNameConstants.HOME_BASED_TESTING_PROGRAM))
                            return new Result("HCT");
                        else if (entryPoint.equals(EvaluableNameConstants.OTHER_NON_CODED))
                            return new Result("Other");
                        else if (entryPoint.equals(EvaluableNameConstants.INPATIENT_CARE_OR_HOSPITALIZATION))
                            return new Result("IPD");
                        else if (entryPoint.equals(EvaluableNameConstants.PROVIDER_INITIATED_TESTING_AND_COUNSELING))
                            return new Result("PITC");
                        else if (entryPoint.equals(EvaluableNameConstants.PEDIATRIC_OUTPATIENT_CLINIC))
                            return new Result("POC");
                        else
                            return new Result("");
                    } catch (Exception e) {}
                    return new Result("");
		
	}
	protected String getEvaluableToken() {
		return TOKEN;
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getParameterList()
	 */
	public Set<RuleParameterInfo> getParameterList() {
		return null;
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	public String[] getDependencies() {
		return new String[] {};
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getTTL()
	 */
	public int getTTL() {
		return 60 * 60 * 24; // 1 day
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getDefaultDatatype()
	 */
	public Datatype getDefaultDatatype() {
		return Datatype.DATETIME;
	}
	
}