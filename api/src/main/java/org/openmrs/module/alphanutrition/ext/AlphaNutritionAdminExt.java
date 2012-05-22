/**
 * 
 */
package org.openmrs.module.alphanutrition.ext;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.web.extension.AdministrationSectionExt;

/**
 * @author Ampath Developers
 * 
 */
public class AlphaNutritionAdminExt extends AdministrationSectionExt {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getLinks()
	 */
	@Override
	public Map<String, String> getLinks() {
		Map<String, String> map = new HashMap<String, String>();
		
		
map.put("module/alphanutrition/alphaNutritionAllocation.form", "AlphaNutrition Allocation ");

map.put("module/alphanutrition/alphaNutritionFoodProduct.form", "AlphaNutrition FoodProduct ");

return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getTitle()
	 */
	@Override
	public String getTitle() {
		return "AlphaNutrition Module";
	}

}
