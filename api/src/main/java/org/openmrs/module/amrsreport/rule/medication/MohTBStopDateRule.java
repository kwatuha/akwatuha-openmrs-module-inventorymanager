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
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.amrsreport.rule.MohEvaluableNameConstants;
import org.openmrs.module.amrsreport.rule.MohEvaluableRule;

public class MohTBStopDateRule extends MohEvaluableRule {

	private static final Log log = LogFactory.getLog(MohTBStopDateRule.class);

	public static final String TOKEN = "MOH TB Stop Date";

	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, Integer, java.util.Map)
	 */
	@Override
	public Result evaluate(final LogicContext context, final Integer patientId, final Map<String, Object> parameters) throws LogicException {
	
		Result result = new Result();
		Date stopDate=null;
		
		//get concepts to use here plan, start and stop
		Concept TBPlan=Context.getConceptService().getConcept(MohEvaluableNameConstants.TUBERCULOSIS_TREATMENT_PLAN);
		Concept TBStopDate=Context.getConceptService().getConcept(MohEvaluableNameConstants.STOP_ALL);
		
		//find the patient
		Patient patient = context.getPatient(patientId);
		
		//find obs based on plan and the start dates 
		List<Obs> obsTBStop=Context.getObsService().getObservationsByPersonAndConcept(patient, TBPlan);
		
		//loop through the obsTBStart list of obs
		for(Obs observations:obsTBStop){
			if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(TBStopDate))
			{
				
				stopDate=observations.getObsDatetime();
				
			    Result tbStartResult = new Result(stopDate);
			    
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

	@Override
	public Datatype getDefaultDatatype() {
		// TODO Auto-generated method stub
		return Datatype.DATETIME;
 	}

	@Override
 	public String[] getDependencies() {
		return new String[]{};
 	}

	@Override
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
