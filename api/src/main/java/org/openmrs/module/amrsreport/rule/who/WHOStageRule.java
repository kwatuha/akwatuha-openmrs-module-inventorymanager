 
 package org.openmrs.module.amrsreport.rule.who;
 
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
 
 
public class WHOStageRule  extends EvaluableRule {
 
 	private static final Log log = LogFactory.getLog( WHOStageRule.class);
 
 	public static final String TOKEN = "MOH WHO Stage";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
			
	@Override
	public Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		Result result=new Result();
		
		//find the patient
		Patient patient = context.getPatient(patientId);
		Date WHOStage1AdultDate=null;
		Date WHOStage2AdultDate=null;
		Date WHOStage3AdultDate=null;
		Date WHOStage4AdultDate=null;
		
		//find all the encounters for a given patient
		//List<Encounter> encounters=Context.getEncounterService().getEncountersByPatient(patient);
		
		Concept FluconazolePlan=Context.getConceptService().getConcept(EvaluableNameConstants.CRYPTOCOCCAL_TREATMENT_PLAN);
		Concept StopDrugs=Context.getConceptService().getConcept(EvaluableNameConstants.STOP_ALL);
		//Concepts instantiation
		Concept WHOStage1Adult=Context.getConceptService().getConcept(EvaluableNameConstants.WHO_STAGE_1_ADULT);
		Concept WHOStage2Adult=Context.getConceptService().getConcept(EvaluableNameConstants.WHO_STAGE_2_ADULT);
		Concept HivStagingAdultHerpesZoster=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_ADULT_HERPES_ZOSTER);
		Concept HivStagingMinorMucocutaneousManifestations=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_MINOR_MUCOCUTANEOUS_MANIFESTATIONS);
		Concept HivStagingWeightLossLessThanTenPercent=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_WEIGHT_LOSS_LESS_THAN_TEN_PERCENT);
		Concept AngularCheilitis=Context.getConceptService().getConcept(EvaluableNameConstants.ANGULAR_CHEILITIS);
		Concept SeborrheicDermatitis=Context.getConceptService().getConcept(EvaluableNameConstants.SEBORRHEIC_DERMATITIS);
		Concept WHOStage3Adult=Context.getConceptService().getConcept(EvaluableNameConstants.WHO_STAGE_3_ADULT);
		Concept HivStagingSeriousBacterialInfections=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_SERIOUS_BACTERIAL_INFECTIONS);
		Concept HivStagingTuberculosisWithinYear=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_TUBERCULOSIS_WITHIN_YEAR);
		Concept HivStagingWeightLossGreaterThanTenPercent=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_WEIGHT_LOSS_GREATER_THAN_TEN_PERCENT);
		Concept ConfirmedHivStagingWeightLossGreaterThanTenPercent=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_WEIGHT_LOSS_GREATER_THAN_TEN_PERCENT);
		Concept ConfirmedHivStagingDiarrheaChronic=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_DIARRHEA_CHRONIC);
		Concept ConfirmedHivStagingPersistentCandidiasisOral=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_PERSISTENT_CANDIDIASIS_ORAL);
		Concept ConfirmedHivStagingPersistentFever=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_PERSISTENT_FEVER);
		Concept ConfirmedWhoStagingOralHairyLeukoplakia=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_WHO_STAGING_ORAL_HAIRY_LEUKOPLAKIA);
		Concept ConfirmedHivStagingTuberculosisPulmonary=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_TUBERCULOSIS_PULMONARY);
		Concept ConfirmedHivStagingSevereBacterialInfections=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_SEVERE_BACTERIAL_INFECTIONS);
		Concept ConfirmedHivStagingUnexplainedAnemiaNeutripaenia=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_UNEXPLAINED_ANAEMIA_NEUTROPAENIA);
		Concept ChronicThrombocytopaenia=Context.getConceptService().getConcept(EvaluableNameConstants.CHRONIC_THROMBOCYTOPAENIA);
		Concept ConfirmedHivStagingAcuteNecrotizingStomatitisGingitivitis=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_ACUTE_NECROTIZING_STOMATITIS_GINGIVITIS);
		Concept WHOStage4Adult=Context.getConceptService().getConcept(EvaluableNameConstants.WHO_STAGE_4_ADULT);
		Concept HivStagingDisseminatedEndemicMycosis=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_DISSEMINATED_ENDEMIC_MYCOSIS);
		Concept HivStagingLymphoma=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_LYMPHOMA);
		Concept HivStagingMucocutaneousHerpesSimplex=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_MUCOCUTANEOUS_HERPES_SIMPLEX);
		Concept HivStagingSalmonellaSepticemia=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_SALMONELLA_SEPTICEMIA);
		Concept KaposiSarcoma=Context.getConceptService().getConcept(EvaluableNameConstants.KAPOSIS_SARCOMA);
		Concept ToxoplasmosisCentralNervousSystem=Context.getConceptService().getConcept(EvaluableNameConstants.TOXOPLASMOSIS_CENTRAL_NERVOUS_SYSTEM);
		Concept ConfirmedHivStagingHivWastingSyndrome=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_HIV_WASTING_SYNDROME);
		Concept ConfirmedHivStagingPneumocysticPneumonia=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_PNEUMOCYSTIC_PNEUMONIA);
		Concept ConfirmedHivStagingRecurrentSevereBacterialPneumoia=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_RECURRENT_SEVERE_BACTERIAL_PNEUMONIA);
		Concept ConfirmedHivStagingChronicHerpesSimplex=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_CHRONIC_HERPES_SIMPLEX);
		Concept ConfirmedHivStagingCandidiasis=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_CANDIDIASIS);
		Concept ConfirmedHivStagingExtrapulmonaryTuberculosis=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_EXTRAPULMONARY_TUBERCULOSIS);
		Concept ConfirmedHivStagingKaposiSarcomaKs=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_KAPOSIS_SARCOMA_KS);
		Concept ConfirmedHivStagingCytomegalovirusDisease=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_CYTOMEGALOVIRUS_DISEASE);
		Concept ConfirmedHivStagingToxoplasmosisCns=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_TOXOPLASMOSIS_CNS);
		Concept ConfirmedHivStagingHivEncephalopathy=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_HIV_ENCEPHALOPATHY);
		Concept ConfirmedHivStagingCryptococcossosExtraPulmonary=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_CRYPTOCOCCOSIS_EXTRAPULMONARY);
		Concept ConfirmedHivStagingDisseminatedNonTuberculosisMyobacterialInfection=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_DISSEMINATED_NON_TUBERCULOSIS_MYCOBACTERIAL_INFECTION);
		Concept ConfirmedHivStagingProgressiveMultifocalLeukoencephalopathy=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_PROGRESSIVE_MULTIFOCAL_LEUKOENCEPHALOPATHY);
		Concept ConfirmedHivStagingChronicCryptosporidiosis=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_CHRONIC_CRYPTOSPORIDIOSIS);
		Concept ConfirmedHivStagingChronicIsosporiasis=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_CHRONIC_ISOSPORIASIS);
		Concept ConfirmedHivStagingDisseminatedMycosis=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_DISSEMINATED_MYCOSIS);
		Concept ConfirmedHivStagingRecurrentSepticemia=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_RECURRENT_SEPTICEMIA);
		Concept ConfirmedHivStagingLymphoma=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_LYMPHOMA);
		Concept ConfirmedHivStagingInvasiveCervicalCarcinoma=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_INVASIVE_CERVICAL_CARCINOMA);
		Concept ConfirmedHivStagingAtypicalDisseminatedLeishmaniasis=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_ATYPICAL_DISSEMINATED_LEISHMANIASIS);
		Concept ConfirmedHivStagingSymptomaticHivAssociatedNephoropathy=Context.getConceptService().getConcept(EvaluableNameConstants.CONFIRMED_HIV_STAGING_SYMPTOMATIC_HIV_ASSOCIATED_NEPHROPATHY);
		Concept WHOStage1Peds=Context.getConceptService().getConcept(EvaluableNameConstants.WHO_STAGE_1_PEDS);
		Concept AsymptomaticHivInfection=Context.getConceptService().getConcept(EvaluableNameConstants.ASYMPTOMATIC_HIV_INFECTION);
		Concept HivStagingPersistentGenerelizedLymphadenopathy=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_PERSISTENT_GENERALIZED_LYMPHADENOPATHY);
		Concept WHOStage2Peds=Context.getConceptService().getConcept(EvaluableNameConstants.WHO_STAGE_2_PEDS);
		Concept Dermatitis=Context.getConceptService().getConcept(EvaluableNameConstants.DERMATITIS);
		Concept HerpesZoster=Context.getConceptService().getConcept(EvaluableNameConstants.HERPES_ZOSTER);
		Concept HivStagingHsvStomatitis=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_HSV_STOMATITIS);
		Concept HivStagingRecurrentUpperRespiratoryInfection=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_RECURRENT_UPPER_RESPIRATORY_INFECTION);
		Concept HivStagingSteroidResistantThrombocytopenia=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_STEROID_RESISTANT_THROMBOCYTOPENIA);
		Concept HumanPapillomavirus=Context.getConceptService().getConcept(EvaluableNameConstants.HUMAN_PAPILLOMAVIRUS);
		Concept MolluscumContagiosum=Context.getConceptService().getConcept(EvaluableNameConstants.MOLLUSCUM_CONTAGIOSUM);
		Concept ParotidEnlargement=Context.getConceptService().getConcept(EvaluableNameConstants.PAROTID_ENLARGEMENT);
		Concept VerrucaPlanus=Context.getConceptService().getConcept(EvaluableNameConstants.VERRUCA_PLANUS);
		Concept RecurrentOralUlceration=Context.getConceptService().getConcept(EvaluableNameConstants.RECURRENT_ORAL_ULCERATION);
		Concept PapularPruriticEruption=Context.getConceptService().getConcept(EvaluableNameConstants.PAPULAR_PRURITIC_ERUPTION);
		Concept FungalNailInfection=Context.getConceptService().getConcept(EvaluableNameConstants.FUNGAL_NAIL_INFECTIONS);
		Concept LinearGingivalErythema=Context.getConceptService().getConcept(EvaluableNameConstants.LINEAR_GINGIVAL_ERYTHEMA);
		Concept WartsGenital=Context.getConceptService().getConcept(EvaluableNameConstants.WARTS_GENITAL);
		Concept ChronicUpperRespiratoryTractInfections=Context.getConceptService().getConcept(EvaluableNameConstants.CHRONIC_UPPER_RESPIRATORY_TRACT_INFECTIONS);
		Concept Hepatpsplenomegaly=Context.getConceptService().getConcept(EvaluableNameConstants.HEPATOSPLENOMEGALY);
		Concept WHOStage3peds=Context.getConceptService().getConcept(EvaluableNameConstants.WHO_STAGE_3_PEDS);
		Concept DiarrheaChronic=Context.getConceptService().getConcept(EvaluableNameConstants.DIARRHEA_CHRONIC);
		Concept FailureToThrive=Context.getConceptService().getConcept(EvaluableNameConstants.FAILURE_TO_THRIVE);
		Concept HivStagingCandidiasisOroresperatoryTract=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_CANDIDIASIS_ORORESPIRATORY_TRACT);
		Concept HivStagingLymphoidInterstitialPneumonia=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_LYMPHOID_INTERSTITIAL_PNEUMONIA);
		Concept HivStagingPedsHerpesZoster=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_PEDS_HERPES_ZOSTER);
		Concept HivStagingRecurrentBActerialPneumonia=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_RECURRENT_BACTERIAL_PNEUMONIA);
		Concept HivStagingRefractoryAnemia=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_REFRACTORY_ANEMIA);
		Concept HivStagingVaricellaDisseminated=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_VARICELLA_DISSEMINATED);
		Concept PneumoniaTuberculosis=Context.getConceptService().getConcept(EvaluableNameConstants.PNEUMONIA_TUBERCULOUS);
		Concept RectovaginalFistula=Context.getConceptService().getConcept(EvaluableNameConstants.RECTOVAGINAL_FISTULA);
		Concept HivStagingModerateMalnutrition=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_MODERATE_MALNUTRITION);
		Concept HivStagingPersistentFever=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_PERSISTENT_FEVER);
		Concept CandidiasisOral=Context.getConceptService().getConcept(EvaluableNameConstants.CANDIDIASIS_ORAL);
		Concept OralHairyLeukoplakia=Context.getConceptService().getConcept(EvaluableNameConstants.ORAL_HAIRY_LEUKOPLAKIA);
		Concept LymphNodeTuberculosis=Context.getConceptService().getConcept(EvaluableNameConstants.LYMPH_NODE_TUBERCULOSIS);
		Concept HivStagingChronicHivAssociatedLungDisease=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_CHRONIC_HIV_ASSOCIATED_LUNG_DISEASE);
		Concept HivStagingUnexplainedAnemiaNeutropenia=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_UNEXAPLINED_ANEMIA_NEUTROPENIA);
		Concept HivStagingAcuteNecrotizingUlcerativeGingitivitisPeriodontis=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_ACUTE_NECROTIZING_ULCERATIVE_GINGIVITIS_PERIODONTIS);
		Concept WHOStage4Peds=Context.getConceptService().getConcept(EvaluableNameConstants.WHO_STAGE_4_PEDS);
		Concept Candidiasis=Context.getConceptService().getConcept(EvaluableNameConstants.CANDIDIASIS);
		Concept CARDIOMYOPATHY=Context.getConceptService().getConcept(EvaluableNameConstants.CARDIOMYOPATHY);
		Concept Cryptococcosis=Context.getConceptService().getConcept(EvaluableNameConstants.CRYPTOCOCCOSIS);
		Concept Encephalopathy=Context.getConceptService().getConcept(EvaluableNameConstants.ENCEPHALOPATHY);
		Concept HivStagingCoccidiodomycosisDisseminated=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_COCCIDIODOMYCOSIS_DISSEMINATED);
		Concept HivStagingCryptococcosisExtraPulmonary=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_CRYPTOCOCCOSIS_EXTRAPULMONARY);
		Concept HivStagingCryptospoidiosis=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_CRYPTOSPORIDIOSIS);
		Concept HivStagingCytomegalovirusDisease=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_CYTOMEGALOVIRUS_DISEASE);
		Concept HivStagingHistoplasmosisDisseminated=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_HISTOPLASMOSIS_DISSEMINATED);
		Concept HivStagingMycobacteriumOther=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_MYCOBACTERIUM_OTHER);
		Concept HivStagingNonResponsiveHerpesSimplexVirus=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_NONRESPONSIVE_HERPES_SIMPLEX_VIRUS);
		Concept HivStagingSevereBacterialInfection=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_SEVERE_BACTERIAL_INFECTION);
		Concept Nephropathy=Context.getConceptService().getConcept(EvaluableNameConstants.NEPHROPATHY);
		Concept PneumocysticCariniiPneumonia=Context.getConceptService().getConcept(EvaluableNameConstants.PNEUMOCYSTIC_CARINII_PNEUMONIA);
		Concept ProgressiveMultifocalLeukoencephalopathy=Context.getConceptService().getConcept(EvaluableNameConstants.PROGRESSIVE_MULTIFOCAL_LEUKOENCEPHALOPATHY);
		Concept Toxoplasmosis=Context.getConceptService().getConcept(EvaluableNameConstants.TOXOPLASMOSIS);
		Concept WastingSyndrome=Context.getConceptService().getConcept(EvaluableNameConstants.WASTING_SYNDROME);
		Concept CandidiasisOesophageal=Context.getConceptService().getConcept(EvaluableNameConstants.CANDIDIASIS_OESOPHAGEAL);
		Concept MycobacteriumTuberculosisExtrapulmonary=Context.getConceptService().getConcept(EvaluableNameConstants.MYCOBACTERIUM_TUBERCULOSIS_EXTRAPULMONARY);
		Concept HivStagingChronicIsisporiasis=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_CHRONIC_ISOSPORIASIS);
		Concept HivStagingCerebralBCellNonHodgkinLymphoma=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_CEREBRAL_B_CELL_NON_HODGKIN_LYMPHOMA);
		
		//End of Concepts instantiation
		
		
		//Using WHO STAGE 1 ADULT 
		Result WHOStage1AdultResult=null;
		
		List<Obs> WHOStage1Adultobs=Context.getObsService().getObservationsByPersonAndConcept(patient, WHOStage1Adult);
		
		for(Obs observations:WHOStage1Adultobs){
			if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(AsymptomaticHivInfection)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingPersistentGenerelizedLymphadenopathy))
			WHOStage1AdultDate=observations.getObsDatetime();
			WHOStage1AdultResult = new Result(WHOStage1AdultDate);
			result.add(WHOStage1AdultResult);
			break;
		}
	// End of WHO STAGE 1 ADULT
		
		
		//Using WHO STAGE 2 ADULT 
		Result WHOstage2AdultResult=null;
		
		List<Obs> WHOStage2Adultobs=Context.getObsService().getObservationsByPersonAndConcept(patient, WHOStage2Adult);
		
		for(Obs observations:WHOStage2Adultobs){
			if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingAdultHerpesZoster)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingMinorMucocutaneousManifestations)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingRecurrentUpperRespiratoryInfection)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingWeightLossLessThanTenPercent)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(AngularCheilitis)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(RecurrentOralUlceration)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(PapularPruriticEruption)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(SeborrheicDermatitis)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(FungalNailInfection)	)
			WHOStage2AdultDate=observations.getObsDatetime();
			WHOstage2AdultResult = new Result(WHOStage2AdultDate);
			result.add(WHOstage2AdultResult);
			break;
		}
	// End of WHO STAGE 2 ADULT
		
		
		//Using WHO STAGE 3 ADULT 
		Result WHOStage3AdultResult=null;
		
		List<Obs> WHOStage3Adultobs=Context.getObsService().getObservationsByPersonAndConcept(patient, WHOStage3Adult);
		
		for(Obs observations:WHOStage3Adultobs){
			if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(CandidiasisOral)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(DiarrheaChronic)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingPersistentFever)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingSeriousBacterialInfections)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingTuberculosisWithinYear)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingWeightLossGreaterThanTenPercent)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(OralHairyLeukoplakia)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ConfirmedHivStagingWeightLossGreaterThanTenPercent)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ConfirmedHivStagingDiarrheaChronic)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ConfirmedHivStagingPersistentCandidiasisOral)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ConfirmedHivStagingPersistentFever)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ConfirmedWhoStagingOralHairyLeukoplakia)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ConfirmedHivStagingTuberculosisPulmonary)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ConfirmedHivStagingSevereBacterialInfections)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ConfirmedHivStagingUnexplainedAnemiaNeutripaenia)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(ConfirmedHivStagingAcuteNecrotizingStomatitisGingitivitis)
					
			)
			WHOStage3AdultDate=observations.getObsDatetime();
			WHOStage3AdultResult = new Result(WHOStage3AdultDate);
			result.add(WHOStage3AdultResult);
			break;
		}
	// End of WHO STAGE 3 ADULT
		
		
		//Using WHO STAGE 4 ADULT 
		Result WHOStage4AdultResult=null;
		
		List<Obs> WHOStage4Adultobs=Context.getObsService().getObservationsByPersonAndConcept(patient, WHOStage4Adult);
		
		for(Obs observations:WHOStage4Adultobs){
			if(Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(AsymptomaticHivInfection)||
					Context.getConceptService().getConcept(observations.getValueCoded().getConceptId()).equals(HivStagingPersistentGenerelizedLymphadenopathy))
			WHOStage4AdultDate=observations.getObsDatetime();
			WHOStage4AdultResult = new Result(WHOStage4AdultDate);
			result.add(WHOStage4AdultResult);
			break;
		}
	// End of WHO STAGE 4 ADULT
			
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