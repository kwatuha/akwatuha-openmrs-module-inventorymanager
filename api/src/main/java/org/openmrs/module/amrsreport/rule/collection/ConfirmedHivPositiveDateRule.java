package org.openmrs.module.amrsreport.rule.collection;
 
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
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.amrsreport.rule.EvaluableNameConstants;
import org.openmrs.module.amrsreport.rule.EvaluableRule;
 
 /**
  * Author jmwogi
  */
public class ConfirmedHivPositiveDateRule  extends EvaluableRule {
 
 	private static final Log log = LogFactory.getLog(ConfirmedHivPositiveDateRule.class);
 
 	public static final String TOKEN = "MOH Confirmed HIV Positive Date";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
	public Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
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