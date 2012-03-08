 
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
import org.openmrs.module.amrsreport.rule.MohEvaluableNameConstants;
import org.openmrs.module.amrsreport.rule.MohEvaluableRule;
 
 /**
  * Author ningosi
  */
public class MohCTXStartTreatmentRule  extends MohEvaluableRule {
 
 	private static final Log log = LogFactory.getLog(MohCTXStartTreatmentRule.class);
 
 	public static final String TOKEN = "MOH CTX Start date";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
			//get ctx stop date
	//@Override
	public Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		Result result=new Result();
		
		Date ctxStart=null;
		//Date ctxStop=null;
		
		//find the patient
		Patient patient = context.getPatient(patientId);
		
		
		//find all the encounters for a given patient
		//List<Encounter> encounters=Context.getEncounterService().getEncountersByPatient(patient);
		
		Concept CTXStartDate=Context.getConceptService().getConcept(MohEvaluableNameConstants.PCP_PROPHYLAXIS_STARTED);
		//
		
		//two dates declared here
		Result ctxStartResult=null;
		//Result ctxStoptResult=null;
		
		
 			
			List<Obs> obs=Context.getObsService().getObservationsByPersonAndConcept(patient, CTXStartDate);
			
			for(Obs observations:obs){
				if(!(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(null))){
				ctxStart=observations.getObsDatetime();
			    ctxStartResult = new Result(ctxStart);
				result.add(ctxStartResult);
				break;
				}
			}
			/**/
			
			
			
			log.info(result);
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
	
 }