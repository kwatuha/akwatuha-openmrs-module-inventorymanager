/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.amrsreport.cohort.definition.evaluator;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Cohort;
import org.openmrs.Concept;
import org.openmrs.OpenmrsObject;
import org.openmrs.annotation.Handler;
import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.api.PatientSetService;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrsreport.rule.MohEvaluableConstants;
import org.openmrs.module.amrsreport.cohort.definition.MohCohortDefinition;
import org.openmrs.module.reporting.cohort.EvaluatedCohort;
import org.openmrs.module.reporting.cohort.definition.AgeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.PersonAttributeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.evaluator.CohortDefinitionEvaluator;
import org.openmrs.module.reporting.cohort.definition.service.CohortDefinitionService;
import org.openmrs.module.reporting.common.DurationUnit;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;

@Handler(supports = {MohCohortDefinition.class})
public class MohCohortDefinitionEvaluator implements CohortDefinitionEvaluator {

	private static final Log log = LogFactory.getLog(MohCohortDefinitionEvaluator.class);

	public static final String ENCOUNTER_TYPE_ADULT_RETURN = "ADULTRETURN";

	public static final String ENCOUNTER_TYPE_ADULT_INITIAL = "ADULTINITIAL";

	public static final String FIRST_HIV_RAPID_TEST_QUALITATIVE_CONCEPT = "HIV RAPID TEST, QUALITATIVE";

	public static final String SECOND_HIV_RAPID_TEST_QUALITATIVE_CONCEPT = "HIV RAPID TEST 2, QUALITATIVE";

	public static final String POSITIVE_CONCEPT = "POSITIVE";

	public static final String HIV_ENZYME_IMMUNOASSAY_QUALITATIVE_CONCEPT = "HIV ENZYME IMMUNOASSAY, QUALITATIVE";

	public EvaluatedCohort evaluate(final CohortDefinition cohortDefinition, final EvaluationContext evaluationContext) throws EvaluationException {

		MohCohortDefinition mohCohortDefinition = (MohCohortDefinition) cohortDefinition;

		EncounterService service = Context.getEncounterService();
		ConceptService conceptService = Context.getConceptService();
		CohortDefinitionService definitionService = Context.getService(CohortDefinitionService.class);

		EncounterCohortDefinition encounterCohortDefinition = new EncounterCohortDefinition();
		encounterCohortDefinition.addEncounterType(service.getEncounterType(ENCOUNTER_TYPE_ADULT_INITIAL));
		encounterCohortDefinition.addEncounterType(service.getEncounterType(ENCOUNTER_TYPE_ADULT_RETURN));

		encounterCohortDefinition.setLocationList(mohCohortDefinition.getLocationList());

		Cohort encounterCohort = definitionService.evaluate(encounterCohortDefinition, evaluationContext);

		Concept firstRapidConcept = conceptService.getConcept(FIRST_HIV_RAPID_TEST_QUALITATIVE_CONCEPT);
		Concept secondRapidConcept = conceptService.getConcept(SECOND_HIV_RAPID_TEST_QUALITATIVE_CONCEPT);
		Concept positiveConcept = conceptService.getConcept(POSITIVE_CONCEPT);

		CodedObsCohortDefinition firstRapidCohortDefinition = new CodedObsCohortDefinition();
		firstRapidCohortDefinition.setTimeModifier(PatientSetService.TimeModifier.ANY);
		firstRapidCohortDefinition.setLocationList(mohCohortDefinition.getLocationList());
		firstRapidCohortDefinition.setQuestion(firstRapidConcept);
		firstRapidCohortDefinition.setOperator(SetComparator.IN);
		firstRapidCohortDefinition.setValueList(Arrays.asList(positiveConcept));

		CodedObsCohortDefinition secondRapidCohortDefinition = new CodedObsCohortDefinition();
		secondRapidCohortDefinition.setTimeModifier(PatientSetService.TimeModifier.ANY);
		secondRapidCohortDefinition.setLocationList(mohCohortDefinition.getLocationList());
		secondRapidCohortDefinition.setQuestion(secondRapidConcept);
		secondRapidCohortDefinition.setOperator(SetComparator.IN);
		secondRapidCohortDefinition.setValueList(Arrays.asList(positiveConcept));

		CompositionCohortDefinition rapidCompositionCohortDefinition = new CompositionCohortDefinition();
		rapidCompositionCohortDefinition.addSearch("PositiveFirstRapid", firstRapidCohortDefinition, null);
		rapidCompositionCohortDefinition.addSearch("PositiveSecondRapid", secondRapidCohortDefinition, null);
		rapidCompositionCohortDefinition.setCompositionString("PositiveFirstRapid OR PositiveSecondRapid");

		Cohort rapidCompositionCohort = definitionService.evaluate(rapidCompositionCohortDefinition, evaluationContext);

		AgeCohortDefinition ageCohortDefinition = new AgeCohortDefinition();
		ageCohortDefinition.setMinAge(18);
		ageCohortDefinition.setMinAgeUnit(DurationUnit.MONTHS);
		ageCohortDefinition.setMaxAge(14);
		ageCohortDefinition.setMaxAgeUnit(DurationUnit.YEARS);

		Concept elisaConcept = conceptService.getConcept(HIV_ENZYME_IMMUNOASSAY_QUALITATIVE_CONCEPT);

		CodedObsCohortDefinition elisaCohortDefinition = new CodedObsCohortDefinition();
		elisaCohortDefinition.setTimeModifier(PatientSetService.TimeModifier.ANY);
		elisaCohortDefinition.setLocationList(mohCohortDefinition.getLocationList());
		elisaCohortDefinition.setQuestion(elisaConcept);
		elisaCohortDefinition.setOperator(SetComparator.IN);
		elisaCohortDefinition.setValueList(Arrays.asList(positiveConcept));

		CompositionCohortDefinition elisaCompositionCohortDefinition = new CompositionCohortDefinition();
		elisaCompositionCohortDefinition.addSearch("PaediatricAge", ageCohortDefinition, null);
		elisaCompositionCohortDefinition.addSearch("PositiveElisa", elisaCohortDefinition, null);
		elisaCompositionCohortDefinition.setCompositionString("PaediatricAge AND PositiveElisa");

		Cohort elisaCompositionCohort = definitionService.evaluate(elisaCompositionCohortDefinition, evaluationContext);

		Map<String, Collection<OpenmrsObject>> restrictions = new HashMap<String, Collection<OpenmrsObject>>();
		restrictions.put(MohEvaluableConstants.OBS_CONCEPT, Arrays.<OpenmrsObject>asList(elisaConcept));
		restrictions.put(MohEvaluableConstants.OBS_VALUE_CODED, Arrays.<OpenmrsObject>asList(positiveConcept));

		// Check for the elisa to make sure the elisa happened after 18 months

		PersonAttributeCohortDefinition personAttributeCohortDefinition = new PersonAttributeCohortDefinition();
		personAttributeCohortDefinition.setAttributeType(Context.getPersonService().getPersonAttributeTypeByName("Health Center"));
		personAttributeCohortDefinition.setValueLocations(mohCohortDefinition.getLocationList());

		Concept transferConcept = conceptService.getConcept("TRANSFER CARE TO OTHER CENTER");
		Concept withinConcept = conceptService.getConcept("AMPATH");
		Concept missedVisitConcept = conceptService.getConcept("REASON FOR MISSED VISIT");
		Concept transferVisitConcept = conceptService.getConcept("AMPATH CLINIC TRANSFER");

		CodedObsCohortDefinition transferCohortDefinition = new CodedObsCohortDefinition();
		transferCohortDefinition.setTimeModifier(PatientSetService.TimeModifier.ANY);
		transferCohortDefinition.setLocationList(mohCohortDefinition.getLocationList());
		transferCohortDefinition.setQuestion(transferConcept);
		transferCohortDefinition.setOperator(SetComparator.IN);
		transferCohortDefinition.setValueList(Arrays.asList(withinConcept));

		CodedObsCohortDefinition missedVisitCohortDefinition = new CodedObsCohortDefinition();
		missedVisitCohortDefinition.setTimeModifier(PatientSetService.TimeModifier.ANY);
		missedVisitCohortDefinition.setLocationList(mohCohortDefinition.getLocationList());
		missedVisitCohortDefinition.setQuestion(missedVisitConcept);
		missedVisitCohortDefinition.setOperator(SetComparator.IN);
		missedVisitCohortDefinition.setValueList(Arrays.asList(transferVisitConcept));

		CompositionCohortDefinition transferCompositionCohortDefinition = new CompositionCohortDefinition();
		transferCompositionCohortDefinition.addSearch("HealthCenterAttribute", personAttributeCohortDefinition, null);
		transferCompositionCohortDefinition.addSearch("TransferWithinAmpath", transferCohortDefinition, null);
		transferCompositionCohortDefinition.setCompositionString("HealthCenterAttribute AND TransferWithinAmpath");

		Cohort transferCompositionCohort = definitionService.evaluate(transferCompositionCohortDefinition, evaluationContext);

		CompositionCohortDefinition missedVisitCompositionCohortDefinition = new CompositionCohortDefinition();
		missedVisitCompositionCohortDefinition.addSearch("HealthCenterAttribute", personAttributeCohortDefinition, null);
		missedVisitCompositionCohortDefinition.addSearch("MissedVisitTransfer", missedVisitCohortDefinition, null);
		missedVisitCompositionCohortDefinition.setCompositionString("HealthCenterAttribute AND MissedVisitTransfer");

		Cohort missedVisitCompositionCohort = definitionService.evaluate(missedVisitCompositionCohortDefinition, evaluationContext);

		Set<Integer> patientIds = new HashSet<Integer>();
		patientIds.addAll(encounterCohort.getMemberIds());
		patientIds.addAll(rapidCompositionCohort.getMemberIds());
		patientIds.addAll(elisaCompositionCohort.getMemberIds());
		patientIds.addAll(transferCompositionCohort.getMemberIds());
		patientIds.addAll(missedVisitCompositionCohort.getMemberIds());

		return new EvaluatedCohort(new Cohort(patientIds), cohortDefinition, evaluationContext);
	}
}