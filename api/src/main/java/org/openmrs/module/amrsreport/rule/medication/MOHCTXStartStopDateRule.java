package org.openmrs.module.amrsreport.rule.medication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import org.openmrs.util.OpenmrsUtil;


/**
 * Author ningosi
 */
public class MOHCTXStartStopDateRule extends MohEvaluableRule{
	
	private static final Log log = LogFactory.getLog(MOHCTXStartStopDateRule.class);
	 
 	public static final String TOKEN = "MOH CTX Start-Stop Date";
 	
 	private Map<String, Concept> cachedConcepts = null;

	private List<Concept> cachedQuestions = null;
	
	
	private static class SortByDateComparator implements Comparator<Object> {

		@Override
		public int compare(Object a, Object b) {
			Obs ao = (Obs) a;
			Obs bo = (Obs) b;
			return ao.getValueDatetime().compareTo(bo.getValueDatetime());
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////
	//List of concepts to be used in comparison
	Concept TRIMETHOPRIM_AND_SULFAMETHOXAZOLE=Context.getConceptService().getConcept(MohEvaluableNameConstants.TRIMETHOPRIM_AND_SULFAMETHOXAZOLE);
	Concept DAPSONE=Context.getConceptService().getConcept(MohEvaluableNameConstants.DAPSONE);
	////////////////////////////////////////////////////////////////////////////////////////
	//The real method that does the magic comes here
	public Result evaluate(final LogicContext context, final Integer patientId, final Map<String, Object> parameters) throws LogicException {
		//find the patient based on the patient id
		Patient patient = Context.getPatientService().getPatient(patientId);
		
		//find the observation based on the patient and a set of  concept question required
		List<Obs> ctxObs=Context.getObsService().getObservations(
				Arrays.asList(new Person[]{patient}), null, getQuestionConcepts(),
				null, null, null, null, null, null, null, null, false);
		
		Collections.sort(ctxObs, new SortByDateComparator());
		
		List<Obs> uniqueCTXObs =  popObs(ctxObs);
		String strRtn=""; //used to hold whatever will be returned for the result
		boolean isStart = true; // denotes either start or stop
		
		for(Obs o:uniqueCTXObs){
			
			if((o.getConcept().equals(TRIMETHOPRIM_AND_SULFAMETHOXAZOLE))||(o.getConcept().equals(DAPSONE))){
				if(isStart){
					if(strRtn.equals(""))
						strRtn += (OpenmrsUtil.getDateFormat(Context.getLocale()).format(o.getValueDatetime()) + " - ");
					else
						strRtn += (", " + (OpenmrsUtil.getDateFormat(Context.getLocale()).format(o.getValueDatetime())) + " - ");
					
				}
				else{
					strRtn += ((OpenmrsUtil.getDateFormat(Context.getLocale()).format(o.getValueDatetime())) + " - ");
				}
				isStart = true;
				
			}
			else{
				if(strRtn.equals("")){
					strRtn += (" - " + (OpenmrsUtil.getDateFormat(Context.getLocale()).format(o.getValueDatetime())) + ", ");
				}
				else{
					if (isStart) {
						strRtn += ((OpenmrsUtil.getDateFormat(Context.getLocale()).format(o.getValueDatetime())) + ", ");
                    }else{
                    	strRtn += (", - " + (OpenmrsUtil.getDateFormat(Context.getLocale()).format(o.getValueDatetime())) + ", ");
                    }
					
				}
				isStart = false;
			}
			
		}
		return new Result(strRtn);
		
	}
	///////////////////////////////////////////////////////////////////////////////////////
	
	private Concept getCachedConcept(String name) {
		if (cachedConcepts == null)
			cachedConcepts = new HashMap<String, Concept>();
		if (!cachedConcepts.containsKey(name))
			cachedConcepts.put(name, Context.getConceptService().getConcept(name));
		return cachedConcepts.get(name);
	}

	private List<Concept> getQuestionConcepts() {
		if (cachedQuestions == null) {
			cachedQuestions = new ArrayList<Concept>();
			cachedQuestions.add(getCachedConcept(MohEvaluableNameConstants.REASON_PCP_PROPHYLAXIS_STOPPED));
			cachedQuestions.add(getCachedConcept(MohEvaluableNameConstants.PCP_PROPHYLAXIS_STARTED));
		}
		return cachedQuestions;
	}
	
	//pass to a function setObsPop(obsCol)
	//loop thru the obsCol and get unique obsValueDateTime > Then add to newSet
	//return newSet
	private List<Obs> popObs(List<Obs> listObs){
		Set<Date> setObs = new HashSet<Date>();
		List<Obs> retObs = new ArrayList<Obs>();
		
		for (Obs obs2 : listObs) {
	        if (!setObs.contains(obs2.getValueDatetime())){
	        	setObs.add(obs2.getValueDatetime());
	        	retObs.add(obs2);
	        }
        }
		
		return retObs;
	}

	
	//return the tokens
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
 	
	
 	@Override
	public Datatype getDefaultDatatype() {
		// TODO Auto-generated method stub
		return Datatype.TEXT;
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
