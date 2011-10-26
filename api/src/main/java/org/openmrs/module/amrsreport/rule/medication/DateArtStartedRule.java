 
 package org.openmrs.module.amrsreport.rule.medication;
 /*
  * Oliver 
  */
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

public class DateArtStartedRule extends EvaluableRule{
 
 	private static final Log log = LogFactory.getLog(DateArtStartedRule.class);
 
 	public static final String TOKEN = "MOH Date ART Started";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
			//get ctx stop dateNewBornProphylacticArvUse
	@Override
	public Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		Result result=new Result();
		
		//find the patient
		Patient patient = context.getPatient(patientId);
		
		Date  ArvPlanStartDate=null;//Capture date using ArvPlan start date
		Date ArtTreatmentStartDate=null;//Capture date using the ARV Treatment Start Date=Start drugs
		Date ReasonArvStartedDate=null;//Capture date using Reason ARV started
		Date PatientReportedRsnForCurrentArvStartedDate=null;//Capture date using Patient Reason for currentArv Start Date
		Date NewBornArvUseDate=null;//Capture date using New born ARV use
		Date NewBornArvUseLopinavirDate=null;//Capture date using New Born ARV using Lopinavir and Ritonavir
		Date NewBornProphylacticArvUseDate=null;//Capture date using New born prophylactic use
		
		
		
		//find all the encounters for a given patient
		//List<Encounter> encounters=Context.getEncounterService().getEncountersByPatient(patient);
		
		Concept ArvTreatmentStartDate=Context.getConceptService().getConcept(EvaluableNameConstants.ANTIRETROVIRAL_DRUG_TREATMENT_START_DATE );
		Concept ArvPlan=Context.getConceptService().getConcept(EvaluableNameConstants. ANTIRETROVIRAL_PLAN);
		Concept StartDrugs=Context.getConceptService().getConcept(EvaluableNameConstants.START_DRUGS);
		Concept ContinueRegimen=Context.getConceptService().getConcept(EvaluableNameConstants.CONTINUE_REGIMEN);
		Concept ChangeFormulation=Context.getConceptService().getConcept(EvaluableNameConstants. CHANGE_FORMULATION);
		Concept ChangeRegimen=Context.getConceptService().getConcept(EvaluableNameConstants.CHANGE_REGIMEN);
		Concept Refilled=Context.getConceptService().getConcept(EvaluableNameConstants.REFILLED);
		Concept NotRefilled=Context.getConceptService().getConcept(EvaluableNameConstants.NOT_REFILLED);
		Concept DrugSubstitution=Context.getConceptService().getConcept(EvaluableNameConstants. DRUG_SUBSTITUTION);
		Concept DrugRestart=Context.getConceptService().getConcept(EvaluableNameConstants.DRUG_RESTART);
		Concept DosingChange=Context.getConceptService().getConcept(EvaluableNameConstants.DOSING_CHANGE);
		Concept ReasonArvStarted=Context.getConceptService().getConcept(EvaluableNameConstants.REASON_ANTIRETROVIRALS_STARTED);
		Concept TotalMtctProphylaxis=Context.getConceptService().getConcept(EvaluableNameConstants. TOTAL_MATERNAL_TO_CHILD_TRANSMISSION_PROPHYLAXIS);
		Concept PmtctOfHiv=Context.getConceptService().getConcept(EvaluableNameConstants. PREVENTION_OF_MOTHER_TO_CHILD_TRANSMISSION_OF_HIV);
		Concept PatientReportedRsnForCurrentArvStarted=Context.getConceptService().getConcept(EvaluableNameConstants.PATIENT_REPORTED_REASON_FOR_CURRENT_ANTIRETROVIRALS_STARTED);
		Concept NewBornArvUse=Context.getConceptService().getConcept(EvaluableNameConstants.NEWBORN_ANTIRETROVIRAL_USE);
		Concept Stavudine=Context.getConceptService().getConcept(EvaluableNameConstants.STAVUDINE);
		Concept Lamivudine=Context.getConceptService().getConcept(EvaluableNameConstants.LAMIVUDINE);
		Concept Nevirapine=Context.getConceptService().getConcept(EvaluableNameConstants.NEVIRAPINE);
		Concept Nelfinavir=Context.getConceptService().getConcept(EvaluableNameConstants.NELFINAVIR);
		Concept Lopinavir=Context.getConceptService().getConcept(EvaluableNameConstants.LOPINAVIR);
		Concept Ritonavir=Context.getConceptService().getConcept(EvaluableNameConstants.RITONAVIR);
		Concept Zidovudine=Context.getConceptService().getConcept(EvaluableNameConstants. ZIDOVUDINE);
		Concept OtherNonCoded=Context.getConceptService().getConcept(EvaluableNameConstants. OTHER_NON_CODED);
		Concept NewBornProphylacticArvUse=Context.getConceptService().getConcept(EvaluableNameConstants.NEWBORN_PROPHYLACTIC_ANTIRETROVIRAL_USE);
		Concept Yes=Context.getConceptService().getConcept(EvaluableNameConstants.YES);
		
		
		//Using ANTIRETROVIRAL DRUG TREATMENT START DATE 
		Result ArtTreatmentStartDateResult=null;
		
		List<Obs> arvDrugTreatmentobs=Context.getObsService().getObservationsByPersonAndConcept(patient, ArvTreatmentStartDate);
		
		for(Obs observations:arvDrugTreatmentobs){
			ArtTreatmentStartDate=observations.getObsDatetime();
			ArtTreatmentStartDateResult = new Result(ArtTreatmentStartDate);
			result.add(ArtTreatmentStartDateResult);
			break;
		}
	// End of ANTIRETROVIRAL DRUG TREATMENT START DATE 
		
		
		//Using ANTIRETROVIRAL PLAN
		Result ArvPlantartDateResult=null;
					
			List<Obs> arvPlanobs=Context.getObsService().getObservationsByPersonAndConcept(patient, ArvPlan);
			
			for(Obs observations:arvPlanobs){
				if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(StartDrugs)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ContinueRegimen)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ChangeFormulation)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ChangeRegimen)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(Refilled)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(NotRefilled)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(DrugSubstitution)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(DrugRestart)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(DosingChange))
					ArvPlanStartDate=observations.getObsDatetime();
				ArvPlantartDateResult = new Result(ArvPlanStartDate);
				result.add(ArvPlantartDateResult);
				break;
			}
			
		//End of Using ANTIRETROVIRAL PLAN	
			
			
			//Using REASON ANTIRETROVIRALS STARTED 
			Result ReasonArvStartedResult=null;
			
			List<Obs> ReasonArvStartedobs=Context.getObsService().getObservationsByPersonAndConcept(patient,ReasonArvStarted);
			
			for(Obs observations:ReasonArvStartedobs){
				if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(TotalMtctProphylaxis)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(PmtctOfHiv))
				ReasonArvStartedDate=observations.getObsDatetime();
				ReasonArvStartedResult = new Result(ReasonArvStartedDate);
				result.add(ReasonArvStartedResult);
				break;
			}
		// End of REASON ANTIRETROVIRALS STARTED 
			
			//Using PATIENT REPORTED REASON FOR CURRENT ANTIRETROVIRALS STARTED
			Result PatientReportedRsnForCurrentArvStartedResult=null;
			
			List<Obs> PatientReportedRsnForCurrentArvStartedobs=Context.getObsService().getObservationsByPersonAndConcept(patient,PatientReportedRsnForCurrentArvStarted);
			
			for(Obs observations:PatientReportedRsnForCurrentArvStartedobs){
				if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(PmtctOfHiv))
					PatientReportedRsnForCurrentArvStartedDate=observations.getObsDatetime();
				PatientReportedRsnForCurrentArvStartedResult = new Result(PatientReportedRsnForCurrentArvStartedDate);
				result.add(PatientReportedRsnForCurrentArvStartedResult);
				break;
			}
		// End of REASON ANTIRETROVIRALS STARTED 	
			
			//Using NEWBORN ANTIRETROVIRAL USE
			Result NewBornArvUseResult=null;
			
			List<Obs> NewBornArvUseobs=Context.getObsService().getObservationsByPersonAndConcept(patient,NewBornArvUse);
			
			for(Obs observations:NewBornArvUseobs){
				if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(Stavudine)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(Lamivudine)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(Nevirapine)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(Nelfinavir)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(Zidovudine)||Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(OtherNonCoded))
					NewBornArvUseDate=observations.getObsDatetime();
				NewBornArvUseResult = new Result(NewBornArvUseDate);
				result.add(NewBornArvUseResult);
				break;
			}
		// End of NEWBORN ANTIRETROVIRAL USE
			
			//Using NEWBORN ANTIRETROVIRAL USE=LOPINAVIR AND RITONAVIR
			Result NewBornArvUseLopinavirResult=null;
			
			List<Obs> NewBornArvUseLopinavirobs=Context.getObsService().getObservationsByPersonAndConcept(patient,NewBornArvUse);
			
			for(Obs observations:NewBornArvUseLopinavirobs){
				if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(Lopinavir) && Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(Ritonavir))
					NewBornArvUseLopinavirDate=observations.getObsDatetime();
				NewBornArvUseLopinavirResult = new Result(NewBornArvUseLopinavirDate);
				result.add(NewBornArvUseLopinavirResult);
				break;
			}
		// End of NEWBORN ANTIRETROVIRAL USE=LOPINAVIR AND RITONAVIR	
			
			//Usanti reing NEWBORN PROPHYLACTIC ANTIRETROVIRAL USE
			Result NewBornProphylacticArvUseResult=null;
			
			List<Obs> NewBornProphylacticArvUseobs=Context.getObsService().getObservationsByPersonAndConcept(patient,NewBornProphylacticArvUse);
			
			for(Obs observations:NewBornProphylacticArvUseobs){
				if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(Yes))
					NewBornProphylacticArvUseDate=observations.getObsDatetime();
				NewBornProphylacticArvUseResult = new Result(NewBornProphylacticArvUseDate);
				result.add(NewBornProphylacticArvUseResult);
				break;
			}
		// End of NEWBORN PROPHYLACTIC ANTIRETROVIRAL USE	
			
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