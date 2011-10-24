 
 package org.openmrs.module.amrsreport.rule.medication;
 
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.openmrs.Concept;
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
 import org.openmrs.module.amrsreport.rule.EvaluableNameConstants;
 
 /**
  * Author ningosi
  */
public class CTXTreatmentRule  implements Rule {
 
 	private static final Log log = LogFactory.getLog(CTXTreatmentRule.class);
 
 	public static final String TOKEN = "MOH CTX TreatmentRule";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
			//get ctx stop date
	//@Override
	public Result eval(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		Result result=new Result();
		
		//find the patient
		Patient patient = context.getPatient(patientId);
		Date ctxStart=null; Date ctxStop=null;
		
		//find all the encounters for a given patient
		//List<Encounter> encounters=Context.getEncounterService().getEncountersByPatient(patient);
		
		Concept CTXStartDate=Context.getConceptService().getConcept(EvaluableNameConstants.PCP_PROPHYLAXIS_STARTED);
		Concept CTXStopDate=Context.getConceptService().getConcept(EvaluableNameConstants.REASON_PCP_PROPHYLAXIS_STOPPED);
		
		//two dates declared here
		Result ctxStartResult=null;
		Result ctxStoptResult=null;
		Result ctxCombined=null;
		List<Result> rs=new ArrayList<Result>();
		
 			
			List<Obs> obs=Context.getObsService().getObservationsByPersonAndConcept(patient, CTXStartDate);
			
			for(Obs observations:obs){
				ctxStart=observations.getObsDatetime();
			    ctxStartResult = new Result(ctxStart);
				result.add(ctxStartResult);
			}
			List<Obs> obsz=Context.getObsService().getObservationsByPersonAndConcept(patient, CTXStopDate);
			for(Obs observation:obsz){
				ctxStop=observation.getObsDatetime();
				ctxStoptResult = new Result(ctxStop);
				result.add(ctxStoptResult);
			}
			
			
			
			
 		return result;
 	}
	
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