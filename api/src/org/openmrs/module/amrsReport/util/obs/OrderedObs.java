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
package org.openmrs.module.amrsReport.util.obs;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Person;

import java.util.Date;

/**
 */
public class OrderedObs extends BaseOpenmrsData {

	private static final Log log = LogFactory.getLog(OrderedObs.class);

	private Integer id;

	private Person person;

	private Person provider;

	private Location location;

	private Concept concept;

	private Concept valueCoded;

	private Double valueNumeric;

	private Date orderedDatetime;

	private Obs obs;

	private Status status;

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
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person
	 */
	public void setPerson(final Person person) {
		this.person = person;
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
	public Concept getValueCoded() {
		return valueCoded;
	}

	/**
	 * @param valueCoded
	 */
	public void setValueCoded(final Concept valueCoded) {
		this.valueCoded = valueCoded;
	}

	/**
	 * @return
	 */
	public Double getValueNumeric() {
		return valueNumeric;
	}

	/**
	 * @param valueNumeric
	 */
	public void setValueNumeric(final Double valueNumeric) {
		this.valueNumeric = valueNumeric;
	}

	/**
	 * @return
	 */
	public Date getOrderedDatetime() {
		return orderedDatetime;
	}

	/**
	 * @param orderedDatetime
	 */
	public void setOrderedDatetime(final Date orderedDatetime) {
		this.orderedDatetime = orderedDatetime;
	}

	/**
	 * @return
	 */
	public Obs getObs() {
		return obs;
	}

	/**
	 * @param obs
	 */
	public void setObs(final Obs obs) {
		this.obs = obs;
		this.person = obs.getPerson();
		this.concept = obs.getConcept();
		this.valueCoded = obs.getValueCoded();
		this.valueNumeric = obs.getValueNumeric();
		this.orderedDatetime = obs.getObsDatetime();
	}

	/**
	 * @return
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(final Status status) {
		this.status = status;
	}
}
