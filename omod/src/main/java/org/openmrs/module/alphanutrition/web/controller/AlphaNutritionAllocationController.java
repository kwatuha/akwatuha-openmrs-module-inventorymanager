package org.openmrs.module.alphanutrition.web.controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.household.model.Household;
import org.openmrs.module.household.model.HouseholdMembership;
import org.openmrs.module.household.service.HouseholdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import  org.openmrs.api.LocationService;
import  org.openmrs.Location;
import  org.openmrs.module.alphanutrition.service.AlphaNutritionService;
import  org.openmrs.module.alphanutrition.model.AlphaNutritionAllocation;import  org.openmrs.module.alphanutrition.model.AlphaNutritionFoodProduct;
	
import java.util.List;
import java.util.Date;

@Controller
public class AlphaNutritionAllocationController {

@RequestMapping(method=RequestMethod.GET, value="module/alphanutrition/alphaNutritionAllocation")
public void pageLoad(ModelMap map){
AlphaNutritionService service=Context.getService(AlphaNutritionService.class);
List<AlphaNutritionFoodProduct> listAlphaNutritionFoodProduct=service.getAlphaNutritionFoodProduct();
		
		map.addAttribute("listAlphaNutritionFoodProduct",listAlphaNutritionFoodProduct);

	
List<AlphaNutritionAllocation> listAlphaNutritionAllocation=service.getAlphaNutritionAllocation();
		
		map.addAttribute("listAlphaNutritionAllocation",listAlphaNutritionAllocation);
	}

@RequestMapping(method=RequestMethod.POST, value="module/alphanutrition/alphaNutritionAllocation")
	public void savePage(ModelMap map,
@RequestParam(required=false, value="alphaNutritionAllocationvoidform") String voidalphaNutritionAllocation,
@RequestParam(required=false, value="EditalphaNutritionAllocation") String  editbtn,
@RequestParam(required=false, value="voidalphaNutritionAllocation") String  voidbtn,
@RequestParam(required=false, value="voidreason") String  voidReason,
@RequestParam(required=true, value="address") String  address,
@RequestParam(required=true, value="foodproductuuid") String  foodproductuuid,
@RequestParam(required=true, value="allocationname") String  allocationname){

AlphaNutritionService service=Context.getService(AlphaNutritionService.class);

AlphaNutritionAllocation alphanutritionallocation=new AlphaNutritionAllocation();
alphanutritionallocation.setAddress(address);

alphanutritionallocation.setAlphaNutritionFoodProduct(service.getAlphaNutritionFoodProductByUuid(foodproductuuid));

alphanutritionallocation.setAllocationName(allocationname);

		service.saveAlphaNutritionAllocation(alphanutritionallocation);
		List<AlphaNutritionAllocation> listAlphaNutritionAllocation=service.getAlphaNutritionAllocation();
	
	map.addAttribute("listAlphaNutritionAllocation",listAlphaNutritionAllocation);
	
List<AlphaNutritionFoodProduct> listAlphaNutritionFoodProduct=service.getAlphaNutritionFoodProduct();
		
		map.addAttribute("listAlphaNutritionFoodProduct",listAlphaNutritionFoodProduct);

	
	}
	
}