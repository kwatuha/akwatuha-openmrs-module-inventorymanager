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
import org.openmrs.EncounterType;
import org.openmrs.api.context.Context;

public final class MohCacheUtils {

	private static final Log log = LogFactory.getLog(MohCacheUtils.class);

	/**
	 * @param conceptName
	 * @return
	 */
	public static Concept getConcept(final String conceptName) {
		MohConceptCacheInstance cacheInstance = MohConceptCacheInstance.getInstance();
		Concept concept = cacheInstance.getConcept(conceptName);
		if (concept == null) {
			concept = Context.getConceptService().getConcept(conceptName);
			cacheInstance.addConcept(conceptName, concept);
		}
		return concept;
	}

	/**
	 *
	 */
	public static void clearConceptCache() {
		MohConceptCacheInstance.getInstance().clearCache();
	}

	/**
	 * @param encounterTypeName
	 * @return
	 */
	public static EncounterType getEncounterType(final String encounterTypeName) {
		MohEncounterTypeCacheInstance cacheInstance = MohEncounterTypeCacheInstance.getInstance();
		EncounterType encounterType = cacheInstance.getEncounterType(encounterTypeName);
		if (encounterType == null) {
			encounterType = Context.getEncounterService().getEncounterType(encounterTypeName);
			cacheInstance.addEncounterType(encounterTypeName, encounterType);
		}
		return encounterType;
	}

	/**
	 *
	 */
	public static void clearEncounterTypeCache() {
		MohEncounterTypeCacheInstance.getInstance().clearCache();
	}

}
