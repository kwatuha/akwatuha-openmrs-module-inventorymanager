<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage AlphaNutrition" otherwise="/login.htm"
	redirect="/module/alphanutrition/alphaNutritionAllocation.form" />
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
		$n('#alphaNutritionAllocationdt').dataTable( {
			"sDom": 'T<"clear">lfrtip'
		});
		
	});
	
	
</script>
<h1>AlphaNutrition Allocation </h1>
<form method="POST" id="alphaNutritionAllocationform">
<table border="1">
<tr><th><tr><td>Address </td><td><input type="text" id="address" name="address"> </td></tr>
<tr><th><tr><td>FoodProduct Uuid </td><td>
<select name="foodproductuuid" id="foodproductuuid">
<c:forEach var="listalphaNutritionFoodProduct" items="${listAlphaNutritionFoodProduct}">
<option id="${listalphaNutritionFoodProduct.foodproductName}" value="${listalphaNutritionFoodProduct.uuid}">${listalphaNutritionFoodProduct.foodproductName}</option>
</c:forEach>
</select>
</td></tr><tr><td>Allocation Name </td><td><input type="text" id="allocationname" name="allocationname"> </td></tr>
<tr><td>&nbsp;</td><td><input type="submit" id="alphaNutritionAllocationformsubmit"name="alphaNutritionAllocationformsubmit" value="Submit"></td></tr></table></form>
<table cellpadding="5" width="100%" id="alphaNutritionAllocationdt">
          <thead>
					<tr>
					<th class="tdClass">Num</th><th class="tbClass">Address </th>
<th class="tbClass">FoodProduct Uuid </th>
<th class="tbClass">Allocation Name </th>

<th class="tdClass">Action</th>
</tr></thead>
<tbody>
					<c:forEach var="alphaNutritionAllocationvar"  items="${listAlphaNutritionAllocation}" varStatus="encIndex" >
						<form method="POST" name="${alphaNutritionAllocationvar.uuid}">
						<tr>
						<td class="tdClass">${encIndex.index + 1}</td><td class="tbClass">${alphaNutritionAllocationvar.address}</td><td class="tbClass">${alphaNutritionAllocationvar.alphaNutritionFoodProduct.foodproductName}</td><td class="tbClass">${alphaNutritionAllocationvar.allocationName}</td><td class="tdClass"><input type="hidden" name="alphaNutritionAllocationtbl" id="alphaNutritionAllocationtbl" value="${alphaNutritionAllocationvar.uuid}" />
								<input type="submit" name="EditalphaNutritionAllocation" value="Edit" /> &nbsp; 
								<input type="submit" name="voidalphaNutritionAllocation"  value="Void" />&nbsp; 
								
							</td>
						</tr>
						</form>
						
					</c:forEach>
					</tbody>
				</table>