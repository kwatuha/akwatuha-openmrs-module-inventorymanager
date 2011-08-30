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
package org.openmrs.module.amrsReport.util.weight;


import org.openmrs.module.amrsReport.db.hibernate.type.StringEnum;

/**
 */
public enum AgeUnit implements StringEnum {

	UNIT_WEEK("Week"), UNIT_MONTH("Month");

	private final String value;

	private AgeUnit(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
