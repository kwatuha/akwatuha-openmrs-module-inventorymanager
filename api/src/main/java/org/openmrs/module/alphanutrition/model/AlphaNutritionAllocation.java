package org.openmrs.module.alphanutrition.model;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Program;

import java.util.Date;
public class AlphaNutritionAllocation extends BaseOpenmrsData{

private  String address;
private  AlphaNutritionFoodProduct alphaNutritionFoodProduct;
private  String allocationName;

private Integer id;
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id=id;
		
	}
	
		public String   getAddress() {
		
		// TODO Auto-generated method stub
		return  address;
	    }
		public  void   setAddress (String  address ) {
		
		// TODO Auto-generated method stub
		 this.address=address;
		 
	    }
		public AlphaNutritionFoodProduct   getAlphaNutritionFoodProduct() {
		
		// TODO Auto-generated method stub
		return  alphaNutritionFoodProduct;
	    }
		public  void   setAlphaNutritionFoodProduct (AlphaNutritionFoodProduct  alphaNutritionFoodProduct ) {
		
		// TODO Auto-generated method stub
		 this.alphaNutritionFoodProduct=alphaNutritionFoodProduct;
		 
	    }
		public String   getAllocationName() {
		
		// TODO Auto-generated method stub
		return  allocationName;
	    }
		public  void   setAllocationName (String  allocationname ) {
		
		// TODO Auto-generated method stub
		 this.allocationName=allocationname;
		 
	    }
}