package org.openmrs.module.amrsreport.rule.collection;
 
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;
import org.openmrs.module.amrsreport.rule.MohEvaluableRule;
import org.openmrs.module.amrsreport.service.MohCoreService;

 
 /**
  * Author ningosi
  */
public class MohUniquePatientNumberRule  extends MohEvaluableRule {
 
 	private static final Log log = LogFactory.getLog(MohUniquePatientNumberRule.class);
 
 	public static final String TOKEN = "MOH Unique Patient Number";

 	
 	/**

	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient,
	 *      java.util.Map)
 	 */
	public Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		
		String mflCode=null;
		
		MohCoreService mohCoreService=Context.getService(MohCoreService.class);
		
		Patient patient = Context.getPatientService().getPatient(patientId);
		
		List<PatientIdentifier> allPatientIdentifiers=mohCoreService.getAllPatientIdenifiers(patient);

        AdministrationService ams=Context.getAdministrationService();



        for(PatientIdentifier patientIdentifier:allPatientIdentifiers){
			// loop through the identifiers and get an identifier with mfl number
				
			try{
					PatientIdentifierType pi=Context.getPatientService().getPatientIdentifierTypeByName(ams.getGlobalProperty("mflgenerator.mfl"));
					
					if((pi !=null) && (pi==patientIdentifier.getIdentifierType()))
						mflCode=patientIdentifier.getIdentifier();
				}
			catch(NullPointerException nullPointerException){
				log.info("CCC Number not found  "+nullPointerException.toString());
			}
				
		
			}
		
		
		
		return new Result(mflCode);
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