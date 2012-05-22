package org.openmrs.module.alphanutrition.model;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Program;

import java.util.Date;
public class AlphaNutritionFoodProduct extends BaseOpenmrsData{

private  String foodproductName;
private  String otherinfo;
private  String foodproductDescription;

private Integer id;
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id=id;
		
	}
	
		public String   getFoodproductName() {
		
		// TODO Auto-generated method stub
		return  foodproductName;
	    }
		public  void   setFoodproductName (String  foodproductname ) {
		
		// TODO Auto-generated method stub
		 this.foodproductName=foodproductname;
		 
	    }
		public String   getOtherinfo() {
		
		// TODO Auto-generated method stub
		return  otherinfo;
	    }
		public  void   setOtherinfo (String  otherinfo ) {
		
		// TODO Auto-generated method stub
		 this.otherinfo=otherinfo;
		 
	    }
		public String   getFoodproductDescription() {
		
		// TODO Auto-generated method stub
		return  foodproductDescription;
	    }
		public  void   setFoodproductDescription (String  foodproductdescription ) {
		
		// TODO Auto-generated method stub
		 this.foodproductDescription=foodproductdescription;
		 
	    }
}