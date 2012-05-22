
/**
 * 
 */
package org.openmrs.module.alphanutrition.dao;

import java.util.List;
import org.openmrs.module.alphanutrition.model.AlphaNutritionAllocation;

import org.openmrs.module.alphanutrition.model.AlphaNutritionFoodProduct;

/**
 *  
 * @author Ampath Developers
 */
public interface AlphaNutritionDAO {
	
  
  /**
	 * 
	 * save AlphaNutritionAllocation
	 * @param AlphaNutritionAllocation to be saved
	 * @return AlphaNutritionAllocation object
	 */
	
	public AlphaNutritionAllocation saveAlphaNutritionAllocation(AlphaNutritionAllocation alphanutritionallocation);
	
	
	/**
	 * @return all the AlphaNutritionAllocation
	 * 
	 */
	public List<AlphaNutritionAllocation> getAlphaNutritionAllocation();
	public AlphaNutritionAllocation getAlphaNutritionAllocationByUuid(String uuid);
  /**
	 * 
	 * save AlphaNutritionFoodProduct
	 * @param AlphaNutritionFoodProduct to be saved
	 * @return AlphaNutritionFoodProduct object
	 */
	
	public AlphaNutritionFoodProduct saveAlphaNutritionFoodProduct(AlphaNutritionFoodProduct alphanutritionfoodproduct);
	
	
	/**
	 * @return all the AlphaNutritionFoodProduct
	 * 
	 */
	public List<AlphaNutritionFoodProduct> getAlphaNutritionFoodProduct();
	public AlphaNutritionFoodProduct getAlphaNutritionFoodProductByUuid(String uuid);
	}