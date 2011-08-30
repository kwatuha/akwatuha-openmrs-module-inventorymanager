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
package org.openmrs.module.amrsReport;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.EncounterType;

/**
 *
 */
public class Mapping extends BaseOpenmrsMetadata {

	private Integer id;

	private Summary summary;

	private EncounterType encounterType;

	private MappingType mappingType;

	/**
	 * @see org.openmrs.OpenmrsObject#getId()
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * Return the value of the summary
	 *
	 * @return the summary
	 */
	public Summary getSummary() {
		return summary;
	}

	/**
	 * Set the summary with the summary value
	 *
	 * @param summary
	 * 		the summary to set
	 */
	public void setSummary(final Summary summary) {
		this.summary = summary;
	}

	/**
	 * Return the value of the encounterType
	 *
	 * @return the encounterType
	 */
	public EncounterType getEncounterType() {
		return encounterType;
	}

	/**
	 * Set the encounterType with the encounterType value
	 *
	 * @param encounterType
	 * 		the encounterType to set
	 */
	public void setEncounterType(final EncounterType encounterType) {
		this.encounterType = encounterType;
	}

	/**
	 * Return the value of the mappingType
	 *
	 * @return the mappingType
	 */
	public MappingType getMappingType() {
		return mappingType;
	}

	/**
	 * Set the mappingType with the mappingType value
	 *
	 * @param mappingType
	 * 		the mappingType to set
	 */
	public void setMappingType(final MappingType mappingType) {
		this.mappingType = mappingType;
	}
}
