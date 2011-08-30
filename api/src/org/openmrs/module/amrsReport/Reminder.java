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
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Person;

import java.util.Date;

/**
 */
public class Reminder extends BaseOpenmrsData {

	private Integer id;

	private String token;

	private String content;

	private Patient patient;

	private Person provider;

	private Location location;

	private Encounter encounter;

	private Date reminderDatetime;

	/**
	 */
	public Reminder() {
	}

	/**
	 * @param reminderDatetime
	 * @param content
	 * @param token
	 */
	public Reminder(final Date reminderDatetime, final String content, final String token) {
		this.reminderDatetime = reminderDatetime;
		this.content = content;
		this.token = token;
	}

	/**
	 * @return id - The unique Identifier for the object
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 * 		- The unique Identifier for the object
	 */
	public void setId(final Integer id) {
		this.id = id;
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
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 */
	public void setContent(final String content) {
		this.content = content;
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
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 * @return
	 */
	public Encounter getEncounter() {
		return encounter;
	}

	/**
	 * @param encounter
	 */
	public void setEncounter(final Encounter encounter) {
		this.encounter = encounter;
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
