package org.openmrs.module.amrsreport.rule.collection;
 
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
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.amrsreport.rule.MohEvaluableNameConstants;
import org.openmrs.module.amrsreport.rule.MohEvaluableRule;
 
 /**
  * Author jmwogi
  */
public class MohEnrollmentAgeRule  extends MohEvaluableRule {
 
 	private static final Log log = LogFactory.getLog(MohEnrollmentAgeRule.class);
 
 	public static final String TOKEN = "MOH Age At Enrollment";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
	public Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		double age = 0.0;
        Date dob =  null;
        try {
            Patient patient = Context.getPatientService().getPatient(patientId);
            dob = patient.getBirthdate();
            
            List<Encounter> e = Context.getEncounterService().getEncountersByPatient(patient);
            //Iterate though encounters for the patient
            Date encounterDate = null;
            Boolean isChild = true;
            for (Iterator<Encounter> it = e.iterator(); it.hasNext();) {
                Encounter encounter = it.next();
                encounterDate = encounter.getEncounterDatetime();
                if(encounter.getEncounterType() == (Context.getEncounterService().getEncounterType(MohEvaluableNameConstants.ENCOUNTER_TYPE_ADULT_INITIAL)) |
                        (encounter.getEncounterType() == Context.getEncounterService().getEncounterType(MohEvaluableNameConstants.ENCOUNTER_TYPE_ADULT_RETURN)))
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
 	 * @see org.openmrs.logic.Rule#getDependencies()
 	 */
	@Override
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
		return Datatype.TEXT;
 	}
	public Set<RuleParameterInfo> getParameterList() {
		return null;
 	}
	@Override
	public int getTTL() {
		return 0;
	}
	
 }