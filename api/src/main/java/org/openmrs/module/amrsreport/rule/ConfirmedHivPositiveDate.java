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
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;


 
public class ConfirmedHivPositiveDate implements Rule {

	private static final Log log = LogFactory.getLog(ConfirmedHivPositiveDate.class);

	public static final String TOKEN = "MOH Confirmed HIV Positive Date";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
	 */
    @Override
    public Result eval(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		
        double age = 0.0;
        Date confirmedDate =  null;
        Date firstEncounterDate = null;
        try {
            Patient patient = Context.getPatientService().getPatient(patientId);
            List<Encounter> e = Context.getEncounterService().getEncountersByPatient(patient);
            //Iterate though encounters for the patient
            int x = 0;
            for (Iterator<Encounter> it = e.iterator(); it.hasNext();) {
                Encounter encounter = it.next();
                if(x==0)
                    firstEncounterDate = encounter.getEncounterDatetime();
                x++;
                //pull encounter obs then loop while checking concepts
                for (Iterator<Obs> it1 = encounter.getAllObs().iterator(); it1.hasNext();) {
                    Obs obs1 = it1.next();
                    if(obs1.getConcept()==Context.getConceptService().getConcept(EvaluableNameConstants.HIV_ENZYME_IMMUNOASSAY_QUALITATIVE)|
                            obs1.getConcept()==Context.getConceptService().getConcept(EvaluableNameConstants.HIV_RAPID_TEST_QUALITATIVE)){
                        if(obs1.getConcept()==Context.getConceptService().getConcept(EvaluableNameConstants.HIV_ENZYME_IMMUNOASSAY_QUALITATIVE) &&
                                (obs1.getValueCoded() == Context.getConceptService().getConcept(EvaluableNameConstants.POSITIVE))){
                            confirmedDate = obs1.getObsDatetime();
                            break;
                        }else if(obs1.getConcept()==Context.getConceptService().getConcept(EvaluableNameConstants.HIV_RAPID_TEST_QUALITATIVE) &&
                                (obs1.getValueCoded() == Context.getConceptService().getConcept(EvaluableNameConstants.POSITIVE))){
                            confirmedDate = obs1.getObsDatetime();
                            break;
                        }     
                    }
                    
                }
                if(confirmedDate!=null){
                    return new Result(confirmedDate);
                }
            }
        } catch (Exception e) {}
        return new Result(firstEncounterDate);
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
            return Datatype.DATETIME;
    }
	
}