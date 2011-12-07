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
import org.openmrs.Concept;

import java.util.HashMap;
import java.util.Map;

/**
 * Lightweight concept caching based on the concept name.
 */
class MohConceptCacheInstance {

	private static final Log log = LogFactory.getLog(MohConceptCacheInstance.class);

	private static final MohConceptCacheInstance ourInstance = new MohConceptCacheInstance();

	/**
	 * @return
	 */
	public static MohConceptCacheInstance getInstance() {
		return ourInstance;
	}

	private final Map<String, Concept> conceptMap;

	/**
	 *
	 */
	private MohConceptCacheInstance() {
		conceptMap = new HashMap<String, Concept>();
	}

	/**
	 * Add an element to the cache system
	 *
	 * @param conceptName
	 * @param concept
	 */
	synchronized void addConcept(String conceptName, Concept concept) {
		conceptMap.put(conceptName, concept);
	}

	/**
	 * Clear content of the cache
	 */
	synchronized void clearCache() {
		conceptMap.clear();
	}

	/**
	 * Internal implementation of getting a concept from the cache
	 *
	 * @param conceptName
	 * @return
	 */
	Concept getConcept(String conceptName) {
		return conceptMap.get(conceptName);
	}

}
