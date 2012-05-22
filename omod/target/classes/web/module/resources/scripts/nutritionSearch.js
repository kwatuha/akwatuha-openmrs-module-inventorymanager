/*
 * Date: 12/19/2008
 * This script contains methods that are used to check matching patients
 */

var householdMembersTable;
var hhMem;

// Executes when page completes loading.
$j(document).ready(function() {
    householdMembersTable = $j("#householdMembers").dataTable({
        "bPaginate": false,
        "bFilter": false,
        "bAutoWidth": false,
        "bInfo": false
    }
    );
});

//Select a patient
function selectedPerson(fieldId, person){
	if (person != null)
            householdMembersTable.fnAddData([person.personName, person.identifier, "<a href=\"javascript:removePerson(" + 
		                                 person.personId + ")\">x</a>"]);
	$j("#new_member_id_selection").focus();
	$j("#new_member_id_selection").val("");
	var oldPersonID= document.getElementById("hiddenbox").value;
	
	$j("#hiddenbox").val("");
	if(oldPersonID=="")
		$j("#hiddenbox").val(person.personId);
	else
		$j("#hiddenbox").val(oldPersonID + "," +  person.personId);
}

//Funtion to remove the selected row.
function removePerson(person){
	
	var strPersonId="";
	
	var oldPersonID= document.getElementById("hiddenbox").value;
	
	var noSeleted = oldPersonID.split(",");
	for(var x=0; x<(noSeleted.length); x++){
            if(noSeleted[x]==person){
                var intPlace= x;
                householdMembersTable.fnDeleteRow(intPlace, null, false);
            }else
                if(strPersonId=="")
                    strPersonId = noSeleted[x];
                else
                    strPersonId = strPersonId + "," + noSeleted[x];
	}
	
	
	$j("#hiddenbox").val("");
	$j("#hiddenbox").val(strPersonId);
	householdMembersTable.fnDraw();
}

//Funtion to select head of household
function headPerson(person){
	$j("#hiddenIndex").val(person);
}
