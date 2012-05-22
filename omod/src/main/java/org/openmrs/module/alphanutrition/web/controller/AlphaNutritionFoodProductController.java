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
import  org.openmrs.module.alphanutrition.model.AlphaNutritionFoodProduct;
import java.util.List;
import java.util.Date;

@Controller
public class AlphaNutritionFoodProductController {

@RequestMapping(method=RequestMethod.GET, value="module/alphanutrition/alphaNutritionFoodProduct")
public void pageLoad(ModelMap map){
AlphaNutritionService service=Context.getService(AlphaNutritionService.class);
List<AlphaNutritionFoodProduct> listAlphaNutritionFoodProduct=service.getAlphaNutritionFoodProduct();
		
		map.addAttribute("listAlphaNutritionFoodProduct",listAlphaNutritionFoodProduct);
	}

@RequestMapping(method=RequestMethod.POST, value="module/alphanutrition/alphaNutritionFoodProduct")
	public void savePage(ModelMap map,
@RequestParam(required=false, value="alphaNutritionFoodproductvoidform") String voidalphaNutritionFoodproduct,
@RequestParam(required=false, value="EditalphaNutritionFoodproduct") String  editbtn,
@RequestParam(required=false, value="voidalphaNutritionFoodproduct") String  voidbtn,
@RequestParam(required=false, value="voidreason") String  voidReason,
@RequestParam(required=true, value="foodproductname") String  foodproductname,
@RequestParam(required=true, value="otherinfo") String  otherinfo,
@RequestParam(required=true, value="foodproductdescription") String  foodproductdescription){

AlphaNutritionService service=Context.getService(AlphaNutritionService.class);

AlphaNutritionFoodProduct alphanutritionfoodproduct=new AlphaNutritionFoodProduct();
alphanutritionfoodproduct.setFoodproductName(foodproductname);

alphanutritionfoodproduct.setOtherinfo(otherinfo);

alphanutritionfoodproduct.setFoodproductDescription(foodproductdescription);

		service.saveAlphaNutritionFoodProduct(alphanutritionfoodproduct);
		List<AlphaNutritionFoodProduct> listAlphaNutritionFoodProduct=service.getAlphaNutritionFoodProduct();
	
	map.addAttribute("listAlphaNutritionFoodProduct",listAlphaNutritionFoodProduct);
	
	}
	
}