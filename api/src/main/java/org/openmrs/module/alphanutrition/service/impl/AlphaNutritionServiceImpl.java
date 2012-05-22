
package org.openmrs.module.alphanutrition.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.household.dao.HouseholdDAO;
import org.openmrs.module.household.service.impl.HouseholdServiceImpl;
import org.openmrs.module.alphanutrition.dao.AlphaNutritionDAO;
import org.openmrs.module.alphanutrition.service.AlphaNutritionService;

import org.openmrs.module.alphanutrition.model.AlphaNutritionAllocation;

import org.openmrs.module.alphanutrition.model.AlphaNutritionFoodProduct;

/**
 * @author Ampath developers
 *
 */
public class AlphaNutritionServiceImpl extends BaseOpenmrsService implements AlphaNutritionService {
protected static final Log log = LogFactory.getLog(AlphaNutritionServiceImpl.class);
	
	

	
	
	private AlphaNutritionDAO alphaNutritionDAO;
	
	/**
	 * @param AlphaNutritionDAO the alphaNutritionDAO to set
	 */
	public void setAlphaNutritionDAO(AlphaNutritionDAO alphaNutritionDAO) {
		this.alphaNutritionDAO = alphaNutritionDAO;
	}
public AlphaNutritionAllocation saveAlphaNutritionAllocation(AlphaNutritionAllocation alphanutritionallocation) {
		
		return alphaNutritionDAO.saveAlphaNutritionAllocation(alphanutritionallocation);
		 
	}

	public List<AlphaNutritionAllocation> getAlphaNutritionAllocation() {
		
		return alphaNutritionDAO.getAlphaNutritionAllocation();
	}
	
	public  AlphaNutritionAllocation getAlphaNutritionAllocationByUuid(String uuid) {
		
		return alphaNutritionDAO.getAlphaNutritionAllocationByUuid(uuid);
	}
public AlphaNutritionFoodProduct saveAlphaNutritionFoodProduct(AlphaNutritionFoodProduct alphanutritionfoodproduct) {
		
		return alphaNutritionDAO.saveAlphaNutritionFoodProduct(alphanutritionfoodproduct);
		 
	}

	public List<AlphaNutritionFoodProduct> getAlphaNutritionFoodProduct() {
		
		return alphaNutritionDAO.getAlphaNutritionFoodProduct();
	}
	
	public  AlphaNutritionFoodProduct getAlphaNutritionFoodProductByUuid(String uuid) {
		
		return alphaNutritionDAO.getAlphaNutritionFoodProductByUuid(uuid);
	}
	}