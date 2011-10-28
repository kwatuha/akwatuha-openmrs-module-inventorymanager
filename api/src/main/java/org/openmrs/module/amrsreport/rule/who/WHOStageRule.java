 
 package org.openmrs.module.amrsreport.rule.who;
 
import java.util.Date;
import java.util.Iterator;
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
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.amrsreport.rule.EvaluableNameConstants;
import org.openmrs.module.amrsreport.rule.EvaluableRule;
 //ningosi
 
public class WHOStageRule  extends EvaluableRule {
 
 	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog( WHOStageRule.class);
 
 	public static final String TOKEN = "MOH WHO Stage";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
			
	@Override
	protected Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		Result result=new Result();
		
		//find the patient
		Patient patient = context.getPatient(patientId);
		
		
		
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
		Concept PresumptiveHivStagingTuberculosisPulmorary=Context.getConceptService().getConcept(EvaluableNameConstants.PRESUMPTIVE_HIV_STAGING_TUBERCULOSIS_PULMONARY);
		Concept OtitisMedia=Context.getConceptService().getConcept(EvaluableNameConstants.OTITIS_MEDIA);
		
		//End of Concepts instantiation
		//find all encounters per the patient
		List<Encounter> allEncounters=Context.getEncounterService().getEncountersByPatient(patient);
		//loop through all the encounters to only pick the initial encounters
		for(Encounter enc:allEncounters){
			//only pick the adult initial 
			if(enc.getEncounterType() ==(Context.getEncounterService().getEncounterType(EvaluableNameConstants.ENCOUNTER_TYPE_ADULT_INITIAL))){
				
				//loop through the obs based on concepts
				
				
				
				@SuppressWarnings("deprecation")
				Set<Obs> WHOStageAdultobs=Context.getObsService().getObservations(enc);
				
				for (@SuppressWarnings("rawtypes")
				Iterator iterator = WHOStageAdultobs.iterator(); iterator.hasNext();) {
					Obs obs = (Obs) iterator.next();
					
						if((obs.getConcept()==WHOStage1Adult) && (obs.getValueCoded()== AsymptomaticHivInfection)
							||(obs.getConcept()==WHOStage1Adult) && (obs.getValueCoded()== AsymptomaticHivInfection)
							){
							
							String stage1="WHO STAGE 1 ADULT";
							Date stageDate1=obs.getObsDatetime();
							String stageDateCombined1=stage1 +"-"+stageDate1;
							Result WHOStage1AdultResult = new Result(stageDateCombined1);
							result.add(WHOStage1AdultResult);
							
						}
						else if((obs.getConcept()==WHOStage2Adult) && (obs.getValueCoded()== HivStagingAdultHerpesZoster)
								||(obs.getConcept()==WHOStage2Adult) && (obs.getValueCoded()== HivStagingMinorMucocutaneousManifestations)
								||(obs.getConcept()==WHOStage2Adult) && (obs.getValueCoded()== HivStagingRecurrentUpperRespiratoryInfection)
								||(obs.getConcept()==WHOStage2Adult) && (obs.getValueCoded()== HivStagingWeightLossLessThanTenPercent)
								||(obs.getConcept()==WHOStage2Adult) && (obs.getValueCoded()== AngularCheilitis)
								||(obs.getConcept()==WHOStage2Adult) && (obs.getValueCoded()== PapularPruriticEruption)
								||(obs.getConcept()==WHOStage2Adult) && (obs.getValueCoded()== SeborrheicDermatitis)
								||(obs.getConcept()==WHOStage2Adult) && (obs.getValueCoded()== FungalNailInfection)
								){
								
								String stage2="WHO STAGE 2 ADULT";
								Date stageDate2=obs.getObsDatetime();
								String stageDateCombined2=stage2 +"-"+stageDate2;
								Result WHOStage2AdultResult = new Result(stageDateCombined2);
								result.add(WHOStage2AdultResult);
								
							}
						else if((obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()== CandidiasisOral)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()== DiarrheaChronic)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()== HivStagingPersistentFever)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()== HivStagingSeriousBacterialInfections)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()== HivStagingTuberculosisWithinYear)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()== HivStagingWeightLossGreaterThanTenPercent)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()== OralHairyLeukoplakia)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()== ConfirmedHivStagingWeightLossGreaterThanTenPercent)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()==  ConfirmedHivStagingDiarrheaChronic)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()==  ConfirmedHivStagingPersistentCandidiasisOral)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()==  ConfirmedHivStagingPersistentFever)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()==  ConfirmedWhoStagingOralHairyLeukoplakia)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()==  PresumptiveHivStagingTuberculosisPulmorary)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()==  ConfirmedHivStagingTuberculosisPulmonary)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()==  ConfirmedHivStagingSevereBacterialInfections)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()==  ConfirmedHivStagingUnexplainedAnemiaNeutripaenia)
								||(obs.getConcept()==WHOStage3Adult) && (obs.getValueCoded()==  ConfirmedHivStagingAcuteNecrotizingStomatitisGingitivitis)
								){
								
								String stage3="WHO STAGE 3 ADULT";
								Date stageDate3=obs.getObsDatetime();
								String stageDateCombined3=stage3 +"-"+stageDate3;
								Result WHOStage3AdultResult = new Result(stageDateCombined3);
								result.add(WHOStage3AdultResult);
								
							}
						else if((obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== Encephalopathy)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== DiarrheaChronic)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== HivStagingCandidiasisOroresperatoryTract)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== HivStagingCryptococcosisExtraPulmonary)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== HivStagingCryptospoidiosis)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== HivStagingCytomegalovirusDisease)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== HivStagingDisseminatedEndemicMycosis)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== HivStagingLymphoma)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== HivStagingMucocutaneousHerpesSimplex)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== HivStagingMycobacteriumOther)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== HivStagingSalmonellaSepticemia)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== KaposiSarcoma)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== MycobacteriumTuberculosisExtrapulmonary)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== PneumocysticCariniiPneumonia)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ProgressiveMultifocalLeukoencephalopathy)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== WastingSyndrome)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ToxoplasmosisCentralNervousSystem)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingHivWastingSyndrome)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingPneumocysticPneumonia)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingRecurrentSevereBacterialPneumoia)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingChronicHerpesSimplex)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingCandidiasis)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingExtrapulmonaryTuberculosis)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingKaposiSarcomaKs)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingCytomegalovirusDisease)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingToxoplasmosisCns)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingHivEncephalopathy)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingCryptococcossosExtraPulmonary)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingDisseminatedNonTuberculosisMyobacterialInfection)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingProgressiveMultifocalLeukoencephalopathy)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingChronicCryptosporidiosis)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingChronicIsosporiasis)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingDisseminatedMycosis)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingRecurrentSepticemia)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingLymphoma)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingInvasiveCervicalCarcinoma)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingAtypicalDisseminatedLeishmaniasis)
								||(obs.getConcept()==WHOStage4Adult) && (obs.getValueCoded()== ConfirmedHivStagingSymptomaticHivAssociatedNephoropathy)
								){
								
								String stage4="WHO STAGE 4 ADULT";
								Date stageDate4=obs.getObsDatetime();
								String stageDateCombined4=stage4 +"-"+stageDate4;
								Result WHOStage4AdultResult = new Result(stageDateCombined4);
								result.add(WHOStage4AdultResult);
								
							}
						else{
							//add information to capture adults who are not falling in any stage
						}
					
					
				}
			}
			//checking if the encounter is paeds initial
			if(enc.getEncounterType() ==(Context.getEncounterService().getEncounterType(EvaluableNameConstants.ENCOUNTER_TYPE_PEDIATRIC_INITIAL))){
				
				@SuppressWarnings("deprecation")
				Set<Obs> WHOStagePaedsobs=Context.getObsService().getObservations(enc);
				
				for (@SuppressWarnings("rawtypes")
				Iterator iterator = WHOStagePaedsobs.iterator(); iterator.hasNext();) {
					Obs obsPaeds = (Obs) iterator.next();
					
					if((obsPaeds.getConcept()==WHOStage1Peds) && (obsPaeds.getValueCoded()== AsymptomaticHivInfection)
							||(obsPaeds.getConcept()==WHOStage1Peds) && (obsPaeds.getValueCoded()== Hepatpsplenomegaly)
							||(obsPaeds.getConcept()==WHOStage1Peds) && (obsPaeds.getValueCoded()== HivStagingPersistentGenerelizedLymphadenopathy)
							){
							
							String stagePaeds1="WHO STAGE 1 PEDS";
							Date stageDatePaeds1=obsPaeds.getObsDatetime();
							String stageDateCombinedPaeds1=stagePaeds1 +"-"+stageDatePaeds1;
							Result WHOStage1PaedsResult = new Result(stageDateCombinedPaeds1);
							result.add(WHOStage1PaedsResult);
							
						}
					else if((obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== Dermatitis)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== HerpesZoster)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== HivStagingHsvStomatitis)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== HivStagingRecurrentUpperRespiratoryInfection)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== HivStagingSteroidResistantThrombocytopenia)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== HumanPapillomavirus)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== MolluscumContagiosum)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== OtitisMedia)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== ParotidEnlargement)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== VerrucaPlanus)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== Hepatpsplenomegaly)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== RecurrentOralUlceration)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== PapularPruriticEruption)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== FungalNailInfection)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== LinearGingivalErythema)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== WartsGenital)
							||(obsPaeds.getConcept()==WHOStage2Peds) && (obsPaeds.getValueCoded()== ChronicUpperRespiratoryTractInfections)
							 
							){
							
							String stagePaeds2="WHO STAGE 2 PEDS";
							Date stageDatePaeds2=obsPaeds.getObsDatetime();
							String stageDateCombinedPaeds2=stagePaeds2 +"-"+stageDatePaeds2;
							Result WHOStage2PaedsResult = new Result(stageDateCombinedPaeds2);
							result.add(WHOStage2PaedsResult);
							
							}
					else if((obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== DiarrheaChronic)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== FailureToThrive)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingCandidiasisOroresperatoryTract)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingLymphoidInterstitialPneumonia)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingNonResponsiveHerpesSimplexVirus)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingPedsHerpesZoster)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingRecurrentBActerialPneumonia)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingRefractoryAnemia)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingVaricellaDisseminated)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== PneumoniaTuberculosis)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== RectovaginalFistula)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingModerateMalnutrition)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingPersistentFever)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== CandidiasisOral)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== OralHairyLeukoplakia)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== LymphNodeTuberculosis)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingChronicHivAssociatedLungDisease)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingUnexplainedAnemiaNeutropenia)
							||(obsPaeds.getConcept()==WHOStage3peds) && (obsPaeds.getValueCoded()== HivStagingAcuteNecrotizingUlcerativeGingitivitisPeriodontis)
							
							 
							){
							
							String stagePaeds3="WHO STAGE 3 PEDS";
							Date stageDatePaeds3=obsPaeds.getObsDatetime();
							String stageDateCombinedPaeds3=stagePaeds3 +"-"+stageDatePaeds3;
							Result WHOStage3PaedsResult = new Result(stageDateCombinedPaeds3);
							result.add(WHOStage3PaedsResult);
							
							}
					else if((obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== Candidiasis)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== CARDIOMYOPATHY)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== Cryptococcosis)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== Encephalopathy)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingCoccidiodomycosisDisseminated)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingCryptococcosisExtraPulmonary)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingCryptospoidiosis)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingCytomegalovirusDisease)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingHistoplasmosisDisseminated)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingMycobacteriumOther)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingNonResponsiveHerpesSimplexVirus)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingSevereBacterialInfection)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingPersistentFever)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== KaposiSarcoma)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== Nephropathy)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== PneumocysticCariniiPneumonia)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== ProgressiveMultifocalLeukoencephalopathy)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== Toxoplasmosis)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== WastingSyndrome)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== CandidiasisOesophageal)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== MycobacteriumTuberculosisExtrapulmonary)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingChronicIsisporiasis)
							||(obsPaeds.getConcept()==WHOStage4Peds) && (obsPaeds.getValueCoded()== HivStagingCerebralBCellNonHodgkinLymphoma)
							
							 
							){
							
							String stagePaeds4="WHO STAGE 4 PEDS";
							Date stageDatePaeds4=obsPaeds.getObsDatetime();
							String stageDateCombinedPaeds4=stagePaeds4 +"-"+stageDatePaeds4;
							Result WHOStage4PaedsResult = new Result(stageDateCombinedPaeds4);
							result.add(WHOStage4PaedsResult);
							
							}
					else{
						//add some code to be evaluated just in case all fails
					}
					
				}
			}
				
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

	
 }