package org.openmrs.module.amrsreport.rule.collection;
 
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.amrsreport.rule.EvaluableRule;
 
 /**
  * Author jmwogi
  */
public class UniquePatientNumberRule  extends EvaluableRule {
 
 	private static final Log log = LogFactory.getLog(UniquePatientNumberRule.class);
 
 	public static final String TOKEN = "MOH Unique Patient Number";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
	public Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		String mohFacilityCode = Context.getAdministrationService().getGlobalProperty("amrsreport.MOHFacilityCode");
		String strPatientID = patientId + "";
		int idSize = strPatientID.length();
		if(idSize < 6)
			for(int i=0; i<(6-idSize); i++)
				strPatientID = "0" + strPatientID;
		return new Result( mohFacilityCode + strPatientID );
 	}
	
	protected String getEvaluableToken() {
		return TOKEN;
 	}
	
	/**
 	 * @see org.openmrs.logic.Rule#getDependencies()
 	 */
	@Override
 	public String[] getDependencies() {
		return new String[]{};
 	}
 	/**
 	 * Get the definition of each parameter that should be passed to this rule execution
 	 *
 	 * @return all parameter that applicable for each rule execution
 	 */
	
 	@Override
	public Datatype getDefaultDatatype() {
		return Datatype.TEXT;
 	}
 	
	public Set<RuleParameterInfo> getParameterList() {
		return null;
 	}
	
	@Override
	public int getTTL() {
		return 0;
	}
	
 }