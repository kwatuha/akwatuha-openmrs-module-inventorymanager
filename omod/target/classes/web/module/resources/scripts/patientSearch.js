//select a patient
function selectedPatient(userId,patient){
	if(patient !=null){
		var patientsId=patient.patientId
		var names=patient.personName
		var birthdate=patient.age
		var patientIdentifier=patient.identifier
		var encounters=patient.encounterDatetime
		var household=patient.householdMembershipGroups
	}
	$j("#searchPatient").val(patientsId);
	$j("#name").val(names);
	$j("#age").val(birthdate);
	$j("#patientIdentifier").val(patientIdentifier);
	$j("#patientEncounters").val(birthdate);
	$j("#householdId").val(household);
}
