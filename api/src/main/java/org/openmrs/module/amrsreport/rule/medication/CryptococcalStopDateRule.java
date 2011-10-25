 
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
public class CryptococcalStopDateRule  extends EvaluableRule {
 
 	private static final Log log = LogFactory.getLog(CryptococcalStopDateRule.class);
 
 	public static final String TOKEN = "MOH Fluconazole Treatment Stop Date";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
			//get ctx stop date
	@Override
	public Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		Result result=new Result();
		
		//find the patient
		Patient patient = context.getPatient(patientId);
		Date fluconazoleStopDate=null; 
		
		
		//find all the encounters for a given patient
		//List<Encounter> encounters=Context.getEncounterService().getEncountersByPatient(patient);
		
		Concept FluconazolePlan=Context.getConceptService().getConcept(EvaluableNameConstants.CRYPTOCOCCAL_TREATMENT_PLAN);
		Concept StopDrugs=Context.getConceptService().getConcept(EvaluableNameConstants.STOP_ALL);
		
		
		
		//two dates declared here
		Result FluconazoleStopResult=null;
		
		
		
 			
			List<Obs> obs=Context.getObsService().getObservationsByPersonAndConcept(patient, FluconazolePlan);
			
			for(Obs observations:obs){
				if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(StopDrugs))
				fluconazoleStopDate=observations.getObsDatetime();
				FluconazoleStopResult = new Result(fluconazoleStopDate);
				result.add(FluconazoleStopResult);
			}
			
				
			
			
 		return result;
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

	/*@Override
	protected Result evaluate(LogicContext context, Integer patientId,
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
 }