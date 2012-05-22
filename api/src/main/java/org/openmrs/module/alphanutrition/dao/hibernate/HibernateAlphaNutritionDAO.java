
/**
 * 
 */
package org.openmrs.module.alphanutrition.dao.hibernate;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.SessionFactory;
import org.openmrs.module.household.model.HouseholdDefinition;
import org.openmrs.module.alphanutrition.dao.AlphaNutritionDAO;

import org.openmrs.module.alphanutrition.model.AlphaNutritionAllocation;

import org.openmrs.module.alphanutrition.model.AlphaNutritionFoodProduct;

/**
 * @author Ampath Developers
 *
 */
public class HibernateAlphaNutritionDAO implements AlphaNutritionDAO {
	
	private SessionFactory sessionFactory;
	
	
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
    	return sessionFactory;
    }

	
    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
    	this.sessionFactory = sessionFactory;
    }

public AlphaNutritionAllocation saveAlphaNutritionAllocation(AlphaNutritionAllocation alphanutritionallocation) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().saveOrUpdate(alphanutritionallocation);
		
		return alphanutritionallocation;
	
	}


	@SuppressWarnings("unchecked")
	public List<AlphaNutritionAllocation> getAlphaNutritionAllocation() {
		// TODO Auto-generated method stub
		
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlphaNutritionAllocation.class);
		
		
		return criteria.list();
		
	}
		@SuppressWarnings("unchecked")
	public AlphaNutritionAllocation getAlphaNutritionAllocationByUuid(String uuid) {	
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlphaNutritionAllocation.class).add(
		Expression.eq("uuid", uuid));
		
		@SuppressWarnings("unchecked")
		
		List<AlphaNutritionAllocation>alphanutritionallocation=criteria.list();
		if (null==alphanutritionallocation||alphanutritionallocation.isEmpty()){
		return null;
		}
		return alphanutritionallocation.get(0);
		}
public AlphaNutritionFoodProduct saveAlphaNutritionFoodProduct(AlphaNutritionFoodProduct alphanutritionfoodproduct) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().saveOrUpdate(alphanutritionfoodproduct);
		
		return alphanutritionfoodproduct;
	
	}


	@SuppressWarnings("unchecked")
	public List<AlphaNutritionFoodProduct> getAlphaNutritionFoodProduct() {
		// TODO Auto-generated method stub
		
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlphaNutritionFoodProduct.class);
		
		
		return criteria.list();
		
	}
		@SuppressWarnings("unchecked")
	public AlphaNutritionFoodProduct getAlphaNutritionFoodProductByUuid(String uuid) {	
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AlphaNutritionFoodProduct.class).add(
		Expression.eq("uuid", uuid));
		
		@SuppressWarnings("unchecked")
		
		List<AlphaNutritionFoodProduct>alphanutritionfoodproduct=criteria.list();
		if (null==alphanutritionfoodproduct||alphanutritionfoodproduct.isEmpty()){
		return null;
		}
		return alphanutritionfoodproduct.get(0);
		}
	}