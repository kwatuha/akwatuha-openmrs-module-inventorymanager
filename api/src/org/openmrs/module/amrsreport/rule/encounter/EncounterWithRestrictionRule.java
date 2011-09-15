/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.amrsreport.rule;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.result.Result;

/**
 *
 */
public class EncounterWithRestrictionRule extends EvaluableRule{
	
	private static final Log log = LogFactory.getLog(EncounterWithRestrictionRule.class);

	/**
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	public String[] getDependencies() {
		return new String[]{};
	}

	/**
     * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, org.openmrs.Patient, java.util.Map)
     */
    public Result eval(LogicContext arg0, Patient arg1, Map<String, Object> arg2) throws LogicException {
	    // TODO Auto-generated method stub
	    return null;
    }

	/**
     * @see org.openmrs.module.amrsreport.rule.EvaluableRule#evaluate(org.openmrs.logic.LogicContext, java.lang.Integer, java.util.Map)
     */
    @Override
    protected Result evaluate(LogicContext context, Integer patientId, Map<String, Object> parameters) {
	    // TODO Auto-generated method stub
	    return null;
    }

	/**
     * @see org.openmrs.module.amrsreport.rule.EvaluableRule#getEvaluableToken()
     */
    @Override
    protected String getEvaluableToken() {
	    // TODO Auto-generated method stub
	    return null;
    }
}
