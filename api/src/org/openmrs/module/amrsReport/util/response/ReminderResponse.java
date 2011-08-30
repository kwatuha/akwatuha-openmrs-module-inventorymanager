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
import org.openmrs.Patient;
import org.openmrs.Person;

import java.util.Date;

public class ReminderResponse extends BaseOpenmrsData {

	private static final Log log = LogFactory.getLog(ReminderResponse.class);

	private Integer id;

	private Patient patient;

	private Person provider;

	private String token;

	private String response;

	private Date reminderDatetime;

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
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 */
	public void setToken(final String token) {
		this.token = token;
	}

	/**
	 * @return
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response
	 */
	public void setResponse(final String response) {
		this.response = response;
	}

	/**
	 * @return
	 */
	public Date getReminderDatetime() {
		return reminderDatetime;
	}

	/**
	 * @param reminderDatetime
	 */
	public void setReminderDatetime(final Date reminderDatetime) {
		this.reminderDatetime = reminderDatetime;
	}
}
