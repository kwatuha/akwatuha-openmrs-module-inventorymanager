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

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;

/**
 * When a summary generation process failed, a log will be created on the database. The log are represented using this class. User
 * can use this class to get a sense of how many failing occurred on the process. This information can also be used to re-generate
 * the summary.
 */
public class Loggable extends BaseOpenmrsData {

	private Integer id;

	private Patient patient;

	private String message;

	public Loggable() {
	}

	public Loggable(final Patient patient, final String message) {
		this.patient = patient;
		this.message = message;
	}

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
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 * 		the patient to set
	 */
	public void setPatient(final Patient patient) {
		this.patient = patient;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 * 		the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}
}
