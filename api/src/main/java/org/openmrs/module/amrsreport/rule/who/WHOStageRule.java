 
 package org.openmrs.module.amrsreport.rule.who;
 
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.EncounterType;
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
		
		
		
		Concept HivStagingAdultHerpesZoster=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_ADULT_HERPES_ZOSTER);
		Concept HivStagingMinorMucocutaneousManifestations=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_MINOR_MUCOCUTANEOUS_MANIFESTATIONS);
		Concept HivStagingWeightLossLessThanTenPercent=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_WEIGHT_LOSS_LESS_THAN_TEN_PERCENT);
		Concept AngularCheilitis=Context.getConceptService().getConcept(EvaluableNameConstants.ANGULAR_CHEILITIS);
		Concept SeborrheicDermatitis=Context.getConceptService().getConcept(EvaluableNameConstants.SEBORRHEIC_DERMATITIS);
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
		Concept AsymptomaticHivInfection=Context.getConceptService().getConcept(EvaluableNameConstants.ASYMPTOMATIC_HIV_INFECTION);
		Concept HivStagingPersistentGenerelizedLymphadenopathy=Context.getConceptService().getConcept(EvaluableNameConstants.HIV_STAGING_PERSISTENT_GENERALIZED_LYMPHADENOPATHY);
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
		Concept ADULTWHOCONDITIONQUERY=Context.getConceptService().getConcept(EvaluableNameConstants.ADULT_WHO_CONDITION_QUERY);
		Concept PaedsWhoSpecificQuery=Context.getConceptService().getConcept(EvaluableNameConstants.PEDS_WHO_SPECIFIC_CONDITION_QUERY);
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//get the patient information
		
		//find obs based on plan and the start dates 
		log.info("Entering  adult initial encounters types");
		EncounterType adultInitial=Context.getEncounterService().getEncounterType(EvaluableNameConstants.ENCOUNTER_TYPE_ADULT_INITIAL);
		
		List<Obs> obsAdult=Context.getObsService().getObservationsByPersonAndConcept(patient, ADULTWHOCONDITIONQUERY);
		for(Obs obsadult:obsAdult ){
				
			if(obsadult.getEncounter().getEncounterType()==adultInitial){
				log.info("Entering  adult initial encounters");
						if((Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==AsymptomaticHivInfection)
							||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingPersistentGenerelizedLymphadenopathy))
							{
								log.info("Entering stage 1 for determination adult");
								String stage1="WHO STAGE 1 ADULT";
								Date stageDate1=obsadult.getObsDatetime();
								String stageDateCombined1=stage1 +"\n"+stageDate1;
								Result WHOStage1AdultResult = new Result(stageDateCombined1.substring(0, 28));
								result.add(WHOStage1AdultResult);
							}
						else if((Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingAdultHerpesZoster)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingMinorMucocutaneousManifestations)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingRecurrentUpperRespiratoryInfection)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingWeightLossLessThanTenPercent)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==AngularCheilitis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==RecurrentOralUlceration)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==PapularPruriticEruption)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==SeborrheicDermatitis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==FungalNailInfection))
							{
								log.info("Entering stage 2 for determination adult");
								String stage2="WHO STAGE 2 ADULT";
								Date stageDate2=obsadult.getObsDatetime();
								String stageDateCombined2=stage2 +"\n"+stageDate2;
								Result WHOStage2AdultResult = new Result(stageDateCombined2.substring(0, 28));
								result.add(WHOStage2AdultResult);
								
							}
						else if((Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingWeightLossGreaterThanTenPercent)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==CandidiasisOral)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==DiarrheaChronic)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingPersistentFever)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingSeriousBacterialInfections)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingTuberculosisWithinYear)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==OralHairyLeukoplakia)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingWeightLossGreaterThanTenPercent)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingDiarrheaChronic)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingPersistentCandidiasisOral)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingPersistentFever)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedWhoStagingOralHairyLeukoplakia)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==PresumptiveHivStagingTuberculosisPulmorary)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingTuberculosisPulmonary)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingSevereBacterialInfections)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingUnexplainedAnemiaNeutripaenia)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingAcuteNecrotizingStomatitisGingitivitis)
								)
							{
								log.info("Entering stage 3 for determination adult");
								String stage3="WHO STAGE 3 ADULT";
								Date stageDate3=obsadult.getObsDatetime();
								String stageDateCombined3=stage3 +"\n"+stageDate3;
								Result WHOStage3AdultResult = new Result(stageDateCombined3.substring(0, 28));
								result.add(WHOStage3AdultResult);
							}
						else if((Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==Encephalopathy)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingCandidiasisOroresperatoryTract)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingCryptococcosisExtraPulmonary)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingCryptospoidiosis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingCytomegalovirusDisease)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingDisseminatedEndemicMycosis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingLymphoma)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingMucocutaneousHerpesSimplex)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingMycobacteriumOther)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==HivStagingSalmonellaSepticemia)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==KaposiSarcoma)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==MycobacteriumTuberculosisExtrapulmonary)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==PneumocysticCariniiPneumonia)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ProgressiveMultifocalLeukoencephalopathy)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==WastingSyndrome)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ToxoplasmosisCentralNervousSystem)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingHivWastingSyndrome)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingPneumocysticPneumonia)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingRecurrentSevereBacterialPneumoia)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingChronicHerpesSimplex)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingCandidiasis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingExtrapulmonaryTuberculosis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingKaposiSarcomaKs)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingCytomegalovirusDisease)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingToxoplasmosisCns)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingHivEncephalopathy)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingCryptococcossosExtraPulmonary)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingDisseminatedNonTuberculosisMyobacterialInfection)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingProgressiveMultifocalLeukoencephalopathy)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingChronicCryptosporidiosis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingChronicIsosporiasis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingDisseminatedMycosis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingRecurrentSepticemia)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingLymphoma)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingInvasiveCervicalCarcinoma)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingAtypicalDisseminatedLeishmaniasis)
								||(Context.getConceptService().getConcept(obsadult.getValueCoded().getConceptId())==ConfirmedHivStagingSymptomaticHivAssociatedNephoropathy)
								)
							{
								log.info("Entering stage 4 for determination adult");
								String stage4="WHO STAGE 4 ADULT";
								Date stageDate4=obsadult.getObsDatetime();
								String stageDateCombined4=stage4 +"\n"+stageDate4;
								Result WHOStage4AdultResult = new Result(stageDateCombined4.substring(0, 28));
								result.add(WHOStage4AdultResult);
							}
						else{
							//do nothing here or add information not falling anywhere above
						}
						break;
						}
			
			
		}
		//checking for paeds based on any encounter type
		log.info("Match found for patient and concept near paeds");
		List<Obs> obsPaeds=Context.getObsService().getObservationsByPersonAndConcept(patient, PaedsWhoSpecificQuery);
		for(Obs obspaeds:obsPaeds){
			log.info("Found paeds matching " +obsPaeds.size());
			if((Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==AsymptomaticHivInfection)
				||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==Hepatpsplenomegaly)
				||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingPersistentGenerelizedLymphadenopathy)
				)		{
								log.info("Entering stage 1 for determination paeds");
								String stagePaeds1="WHO STAGE 1 PEDS";
								Date stageDatePaeds1=obspaeds.getObsDatetime();
								String stageDateCombinedPaeds1=stagePaeds1 +"\n"+stageDatePaeds1;
								Result WHOStage1PaedsResult = new Result(stageDateCombinedPaeds1.substring(0, 27));
								result.add(WHOStage1PaedsResult);
								break;
						}
			else if((Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==Dermatitis)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HerpesZoster)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingHsvStomatitis)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingRecurrentUpperRespiratoryInfection)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingSteroidResistantThrombocytopenia)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HumanPapillomavirus)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==MolluscumContagiosum)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==OtitisMedia)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==ParotidEnlargement)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==VerrucaPlanus)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==Hepatpsplenomegaly)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==RecurrentOralUlceration)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==PapularPruriticEruption)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==FungalNailInfection)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==LinearGingivalErythema)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==WartsGenital)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==ChronicUpperRespiratoryTractInfections)
					){
								log.info("Entering stage 2 for determination paeds");
								String stagePaeds2="WHO STAGE 2 PEDS";
								Date stageDatePaeds2=obspaeds.getObsDatetime();
								String stageDateCombinedPaeds2=stagePaeds2 +"\n"+stageDatePaeds2;
								Result WHOStage2PaedsResult = new Result(stageDateCombinedPaeds2.substring(0, 27));
								result.add(WHOStage2PaedsResult);
								break;
					}
			else if(
					(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==DiarrheaChronic)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==FailureToThrive)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingCandidiasisOroresperatoryTract)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingLymphoidInterstitialPneumonia)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingNonResponsiveHerpesSimplexVirus)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingPedsHerpesZoster)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingRecurrentBActerialPneumonia)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingRefractoryAnemia)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingVaricellaDisseminated)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==PneumoniaTuberculosis)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==RectovaginalFistula)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingModerateMalnutrition)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingPersistentFever)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==CandidiasisOral)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==OralHairyLeukoplakia)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==LymphNodeTuberculosis)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingChronicHivAssociatedLungDisease)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingUnexplainedAnemiaNeutropenia)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingAcuteNecrotizingUlcerativeGingitivitisPeriodontis)
					){
								log.info("Entering stage 3 for determination paeds");
								String stagePaeds3="WHO STAGE 3 PEDS";
								Date stageDatePaeds3=obspaeds.getObsDatetime();
								String stageDateCombinedPaeds3=stagePaeds3 +"\n"+stageDatePaeds3;
								Result WHOStage3PaedsResult = new Result(stageDateCombinedPaeds3.substring(0, 27));
								result.add(WHOStage3PaedsResult);
								break;
					 }
			else if(
					(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==Candidiasis)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==CARDIOMYOPATHY)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==Cryptococcosis)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==Encephalopathy)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingCoccidiodomycosisDisseminated)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingCryptococcosisExtraPulmonary)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingCryptospoidiosis)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingCytomegalovirusDisease)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingHistoplasmosisDisseminated)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingMycobacteriumOther)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingNonResponsiveHerpesSimplexVirus)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingSevereBacterialInfection)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==KaposiSarcoma)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==Nephropathy)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==PneumocysticCariniiPneumonia)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==ProgressiveMultifocalLeukoencephalopathy)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==Toxoplasmosis)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==WastingSyndrome)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==CandidiasisOesophageal)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==MycobacteriumTuberculosisExtrapulmonary)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingChronicIsisporiasis)
					||(Context.getConceptService().getConcept(obspaeds.getValueCoded().getConceptId())==HivStagingCerebralBCellNonHodgkinLymphoma)
					
					){
								log.info("Entering stage 4 for determination paeds");
								String stagePaeds4="WHO STAGE 4 PEDS";
								Date stageDatePaeds4=obspaeds.getObsDatetime();
								String stageDateCombinedPaeds4=stagePaeds4 +"\n"+stageDatePaeds4;
								Result WHOStage4PaedsResult = new Result(stageDateCombinedPaeds4.substring(0, 27));
								result.add(WHOStage4PaedsResult);
								break;
					 }
			else{
				//add inforamtion relevant for other info
			}
			
			
			
		}
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		return Datatype.DATETIME;
 	}
	public Set<RuleParameterInfo> getParameterList() {
		return null;
 	}
	@Override
	public int getTTL() {
		return 0;
	}

	
 }