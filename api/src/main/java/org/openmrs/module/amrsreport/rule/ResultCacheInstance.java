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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.logic.result.Result;

import java.util.HashMap;
import java.util.Map;

public class ResultCacheInstance {

	private static final Log log = LogFactory.getLog(ResultCacheInstance.class);

	private static ResultCacheInstance ourInstance = new ResultCacheInstance();

	public static ResultCacheInstance getInstance() {
		return ourInstance;
	}

	private final Map<Integer, Map<ResultCacheKey, Result>> resultCache;

	private ResultCacheInstance() {
		resultCache = new HashMap<Integer, Map<ResultCacheKey, Result>>();
	}

	/**
	 * @param key
	 * @param results
	 */
	public synchronized void addResult(ResultCacheKey key, Result results) {
		Map<ResultCacheKey, Result> patientResultCache = resultCache.get(key.getPatientId());
		if (patientResultCache == null) {
			patientResultCache = new HashMap<ResultCacheKey, Result>();
			resultCache.put(key.getPatientId(), patientResultCache);
		}
		patientResultCache.put(key, results);
	}

	/**
	 *
	 */
	public synchronized void clearCache() {
		resultCache.clear();
	}

	/**
	 * @param patient
	 */
	public synchronized void clearCache(Patient patient) {
		resultCache.remove(patient.getPatientId());
	}

	/**
	 * @param key
	 * @return
	 */
	public Result getResult(ResultCacheKey key) {
		Map<ResultCacheKey, Result> patientResultCache = resultCache.get(key.getPatientId());
		if (patientResultCache != null)
			return patientResultCache.get(key);
		return null;
	}
}
