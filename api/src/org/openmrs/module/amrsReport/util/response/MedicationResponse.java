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
package org.openmrs.module.amrsReport.util.response;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.Person;

import java.util.Date;

public class MedicationResponse extends BaseOpenmrsData {

	private static final Log log = LogFactory.getLog(MedicationResponse.class);

	private Integer id;

	private Patient patient;

	private Person provider;

	private Concept concept;

	private MedicationType medicationType;

	private Date medicationDatetime;

	private Integer status;

	/**
	 * @return id - The unique Identifier for the object
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id - The unique Identifier for the object
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 */
	public void setPatient(final Patient patient) {
		this.patient = patient;
	}

	/**
	 * @return
	 */
	public Person getProvider() {
		return provider;
	}

	/**
	 * @param provider
	 */
	public void setProvider(final Person provider) {
		this.provider = provider;
	}

	/**
	 * @return
	 */
	public Concept getConcept() {
		return concept;
	}

	/**
	 * @param concept
	 */
	public void setConcept(final Concept concept) {
		this.concept = concept;
	}

	/**
	 * @return
	 */
	public MedicationType getMedicationType() {
		return medicationType;
	}

	/**
	 * @param medicationType
	 */
	public void setMedicationType(final MedicationType medicationType) {
		this.medicationType = medicationType;
	}

	/**
	 * @return
	 */
	public Date getMedicationDatetime() {
		return medicationDatetime;
	}

	/**
	 * @param medicationDatetime
	 */
	public void setMedicationDatetime(final Date medicationDatetime) {
		this.medicationDatetime = medicationDatetime;
	}

	/**
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(final Integer status) {
		this.status = status;
	}
}
