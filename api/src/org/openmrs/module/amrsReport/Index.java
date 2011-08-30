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
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.module.amrsReport.Summary;
import java.util.Date;

/**
 * An object that will hold index information of a summary. The index will contains location and "supposedly" return date of a patient. Some patients
 * might have blank location and return date. <ul> <li>Location are based on the latest encounter of a patient. So, if a patient doesn't have
 * encounter, they will not have location.</li> <li>Return date also based on patients latest observation on "RETURN VISIT DATE" concept. If a patient
 * doesn't have this observation in their latest encounter then the return date of that patients will also going to be blank.</li> </ul>
 */
public class Index extends BaseOpenmrsData {

	private Integer id;

	private Patient patient;

	private Location location;

	private Date returnDate;

	private Date generatedDate;

	private Date initialDate;

	private Summary summary;

	public Index() {
		this.returnDate = new Date();
		this.generatedDate = new Date();
		this.initialDate = new Date();
	}

	public Index(final Patient patient, final Summary summary, final Date initialDate) {
		this.initialDate = initialDate;
		this.patient = patient;
		this.summary = summary;
		this.returnDate = new Date();
		this.generatedDate = new Date();
		this.initialDate = new Date();
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
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 * 		the location to set
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate
	 * 		the returnDate to set
	 */
	public void setReturnDate(final Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * Return the value of the generatedDate
	 *
	 * @return the generatedDate
	 */
	public Date getGeneratedDate() {
		return generatedDate;
	}

	/**
	 * Set the generatedDate with the generatedDate value
	 *
	 * @param generatedDate
	 * 		the generatedDate to set
	 */
	public void setGeneratedDate(final Date generatedDate) {
		this.generatedDate = generatedDate;
	}

	/**
	 * Return the value of the initialDate
	 *
	 * @return the initialDate
	 */
	public Date getInitialDate() {
		return initialDate;
	}

	/**
	 * Set the initialDate with the initialDate value
	 *
	 * @param initialDate
	 * 		the initialDate to set
	 */
	public void setInitialDate(final Date initialDate) {
		this.initialDate = initialDate;
	}

	/**
	 * @return the summary
	 */
	public Summary getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 * 		the summary to set
	 */
	public void setSummary(final Summary summary) {
		this.summary = summary;
	}
}
