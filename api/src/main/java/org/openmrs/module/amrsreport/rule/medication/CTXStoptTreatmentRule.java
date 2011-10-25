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

public class CTXStoptTreatmentRule extends EvaluableRule {

	private static final Log log = LogFactory.getLog(CTXStartTreatmentRule.class);
	 
 	public static final String TOKEN = "MOH CTX Stop Rule";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
			//get ctx stop date
	@Override
	public Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		
		Result result=new Result();
		
		Date ctxStop=null;
		Result ctxStopResult=null;
		//find the patient
		Patient patient = context.getPatient(patientId);
		//find the concept
		Concept CTXStopDate=Context.getConceptService().getConcept(EvaluableNameConstants.REASON_PCP_PROPHYLAXIS_STOPPED);
		//find a list of obs in the Obs instance
		
		List<Obs> obsz=Context.getObsService().getObservationsByPersonAndConcept(patient, CTXStopDate);
		for(Obs observation:obsz){
			if(!(Context.getConceptService().getConcept(observation.getValueCoded().getConceptId()).equals(null)))
			ctxStop=observation.getObsDatetime();
			ctxStopResult = new Result(ctxStop);
			result.add(ctxStopResult);
		}
		
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
