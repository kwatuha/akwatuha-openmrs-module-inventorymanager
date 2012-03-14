 
 package org.openmrs.module.amrsreport.rule.medication;
 
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
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
 	
 	private Map<String, Concept> cachedConcepts = null;
	private List<Concept> cachedQuestions = null;
	private List<Concept> cachedAnswers = null;

 	
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
		
		//find the patient using the patient id
		Patient patient = Context.getPatientService().getPatient(patientId);
		
		
		//find the observation based on the patient and the concept question required
		List<Obs> obs=Context.getObsService().getObservations(
				Arrays.asList(new Person[]{patient}), null, getQuestionConcepts(),
				null, null, null, null, null, null, null, null, false);
		
		
		Result ctxStartResult=null;
		
		
		
 			
			//List<Obs> obs=Context.getObsService().getObservationsByPersonAndConcept(patient, CTXStartDate);
			List<Concept> answerList=getCachedAnswers();
			for(Obs observations:obs){
				for(Concept an:answerList){
					
					if((observations.getValueCoded().getConceptId())==(an.getConceptId())){
						
						ctxStart=observations.getObsDatetime();
					    ctxStartResult = new Result(ctxStart);
						result.add(ctxStartResult);
						//break;
				}
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
	
	private Concept getCachedConcept(String name) {
		
		if (cachedConcepts == null) {
			cachedConcepts = new HashMap<String, Concept>();
		}
		if (!cachedConcepts.containsKey(name)) {
			cachedConcepts.put(name, Context.getConceptService().getConcept(name));
		}
		return cachedConcepts.get(name);
		
	}
	private List<Concept> getQuestionConcepts() {
		if (cachedQuestions == null) {
			cachedQuestions = new ArrayList<Concept>();
			cachedQuestions.add(getCachedConcept(MohEvaluableNameConstants.PCP_PROPHYLAXIS_STARTED));
		}
		return cachedQuestions;
		
	
	}
	private List<Concept> getCachedAnswers() {
		if (cachedAnswers == null) {
			cachedAnswers = new ArrayList<Concept>();
			cachedAnswers.add(getCachedConcept(MohEvaluableNameConstants.TRIMETHOPRIM_AND_SULFAMETHOXAZOLE));
			cachedAnswers.add(getCachedConcept(MohEvaluableNameConstants.DAPSONE));
		}
		return cachedAnswers;
	}	
}