<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage AlphaNutrition" otherwise="/login.htm"
	redirect="/module/alphanutrition/alphaNutritionFoodProduct.form" />
	<%-- <%@ include file="localHeader.jsp"%> --%>
<openmrs:htmlInclude file="/moduleResources/alphanutrition/scripts/jquery.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables_jui.css"/>
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js"/>
<openmrs:htmlInclude file="/moduleResources/alphanutrition/scripts/interface.js"/>
<openmrs:htmlInclude file="/moduleResources/alphanutrition/scripts/jquery.form.js"/>

<style>
	#layer1 
	{
		position: absolute;
		width:300px;
		background-color:#f0f5FF;
		border: 1px solid #000;
		z-index: 50;
		vertical-align:middle;
	}
	#layer1_handle 
	{
		background-color:#5588bb;
		padding:2px;
		text-align:center;
		font-weight:bold;
		color: #FFFFFF;
		vertical-align:middle;
	}
	#layer1_content 
	{
		padding:5px;
	}
	#close
	{
		float:right;
		text-decoration:none;
		color:#FFFFFF;
	}
</style>
<script type="text/javascript">
	var $n = jQuery.noConflict();
	
	$n(document).ready( function() {
		$n('#alphaNutritionFoodProductdt').dataTable( {
			"sDom": 'T<"clear">lfrtip'
		});
		
	});
	
	
</script>
<h1>AlphaNutrition FoodProduct </h1>
<form method="POST" id="alphaNutritionFoodProductform">
<table border="1">
<tr><th><tr><td>Foodproduct Name </td><td><input type="text" id="foodproductname" name="foodproductname"> </td></tr>
<tr><td>Otherinfo </td><td><input type="text" id="otherinfo" name="otherinfo"> </td></tr>
<tr><td>Foodproduct Description </td><td><input type="text" id="foodproductdescription" name="foodproductdescription"> </td></tr>
<tr><td>&nbsp;</td><td><input type="submit" id="alphaNutritionFoodProductformsubmit"name="alphaNutritionFoodProductformsubmit" value="Submit"></td></tr></table></form>
<table cellpadding="5" width="100%" id="alphaNutritionFoodProductdt">
          <thead>
					<tr>
					<th class="tdClass">Num</th><th class="tbClass">Foodproduct Name </th>
<th class="tbClass">Otherinfo </th>
<th class="tbClass">Foodproduct Description </th>

<th class="tdClass">Action</th>
</tr></thead>
<tbody>
					<c:forEach var="alphaNutritionFoodProductvar"  items="${listAlphaNutritionFoodProduct}" varStatus="encIndex" >
						<form method="POST" name="${alphaNutritionFoodProductvar.uuid}">
						<tr>
						<td class="tdClass">${encIndex.index + 1}</td><td class="tbClass">${alphaNutritionFoodProductvar.foodproductName}</td><td class="tbClass">${alphaNutritionFoodProductvar.otherinfo}</td><td class="tbClass">${alphaNutritionFoodProductvar.foodproductDescription}</td><td class="tdClass"><input type="hidden" name="alphaNutritionFoodProducttbl" id="alphaNutritionFoodProducttbl" value="${alphaNutritionFoodProductvar.uuid}" />
								<input type="submit" name="EditalphaNutritionFoodProduct" value="Edit" /> &nbsp; 
								<input type="submit" name="voidalphaNutritionFoodProduct"  value="Void" />&nbsp; 
								
							</td>
						</tr>
						</form>
						
					</c:forEach>
					</tbody>
				</table>