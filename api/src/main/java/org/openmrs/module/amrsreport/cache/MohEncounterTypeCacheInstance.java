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
package org.openmrs.module.amrsreport.cache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.EncounterType;

import java.util.HashMap;
import java.util.Map;

class MohEncounterTypeCacheInstance {

	private static final Log log = LogFactory.getLog(MohEncounterTypeCacheInstance.class);

	private static MohEncounterTypeCacheInstance ourInstance = new MohEncounterTypeCacheInstance();

	public static MohEncounterTypeCacheInstance getInstance() {
		return ourInstance;
	}

	private final Map<String, EncounterType> encounterTypeMap;

	private MohEncounterTypeCacheInstance() {
		encounterTypeMap = new HashMap<String, EncounterType>();
	}

	/**
	 * Add an element to the cache system
	 *
	 * @param conceptName
	 * @param encounterType
	 */
	synchronized void addEncounterType(String conceptName, EncounterType encounterType) {
		encounterTypeMap.put(conceptName, encounterType);
	}

	/**
	 * Clear content of the cache
	 */
	synchronized void clearCache() {
		encounterTypeMap.clear();
	}

	/**
	 * Internal implementation of getting an element from the cache
	 *
	 * @param encounterTypeName
	 * @return
	 */
	EncounterType getEncounterType(String encounterTypeName) {
		return encounterTypeMap.get(encounterTypeName);
	}
}
