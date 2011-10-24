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

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;


 
public class AgeAtEnrolment implements Rule {

	private static final Log log = LogFactory.getLog(AgeAtEnrolment.class);

	public static final String TOKEN = "MOH Age At Enrolment";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
	 */
    @Override
    public Result eval(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		
        double age = 0.0;
        Date dob =  null;
        try {
            Patient patient = Context.getPatientService().getPatient(patientId);
            dob = patient.getBirthdate();
            
            //List<EncounterType> et = null;
            //et.add(Context.getEncounterService().getEncounterType(EvaluableNameConstants.ENCOUNTER_TYPE_ADULT_INITIAL));
            /*et.add(Context.getEncounterService().getEncounterType(EvaluableNameConstants.ENCOUNTER_TYPE_ADULT_RETURN));
            et.add(Context.getEncounterService().getEncounterType(EvaluableNameConstants.ENCOUNTER_TYPE_PEDIATRIC_INITIAL));
            et.add(Context.getEncounterService().getEncounterType(EvaluableNameConstants.ENCOUNTER_TYPE_PEDIATRIC_RETURN));*/
            List<Encounter> e = Context.getEncounterService().getEncountersByPatient(patient);//, null, null, null, null, et, null, true);
            //Iterate though encounters for the patient
            Date encounterDate = null;
            Boolean isChild = true;
            for (Iterator<Encounter> it = e.iterator(); it.hasNext();) {
                Encounter encounter = it.next();
                encounterDate = encounter.getEncounterDatetime();
                if(encounter.getEncounterType() == (Context.getEncounterService().getEncounterType(EvaluableNameConstants.ENCOUNTER_TYPE_ADULT_INITIAL)) |
                        (encounter.getEncounterType() == Context.getEncounterService().getEncounterType(EvaluableNameConstants.ENCOUNTER_TYPE_ADULT_RETURN)))
                    isChild = false;
                break;
            }
            //Get age in years
            if(encounterDate!=null)
                if(!isChild){
                    age = ((encounterDate.getTime() - dob.getTime())/(1000 * 60 * 60 * 24 * 30.4375 * 12));
                    return new Result(((int) Math.floor( age )) + "y");
                }
                else{
                    age = ((encounterDate.getTime() - dob.getTime())/(1000 * 60 * 60 * 24 * 30.4375 * 12));
                    if(age<1){
                        age = ((encounterDate.getTime() - dob.getTime())/(1000 * 60 * 60 * 24 * 30.4375));
                        return new Result(((int) Math.floor( age )) + "m");
                    }
                    else
                        return new Result(((int) Math.floor( age )) + "y");
                }
            
        } catch (Exception e) {}
        return new Result("");
    }
    protected String getEvaluableToken() {
            return TOKEN;
    }

    /**
     * @see org.openmrs.logic.Rule#getParameterList()
     */
    @Override
    public Set<RuleParameterInfo> getParameterList() {
            return null;
    }

    /**
     * @see org.openmrs.logic.Rule#getDependencies()
     */
    @Override
    public String[] getDependencies() {
            return new String[] {};
    }

    /**
     * @see org.openmrs.logic.Rule#getTTL()
     */
    @Override
    public int getTTL() {
            return 60 * 60 * 24; // 1 day
    }

    /**
     * @see org.openmrs.logic.Rule#getDefaultDatatype()
     */
    @Override
    public Datatype getDefaultDatatype() {
            return Datatype.TEXT;
    }
	
}