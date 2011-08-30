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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.BaseOpenmrsData;

/**
 */
public class WeightStandard extends BaseOpenmrsData {

	private static final Log log = LogFactory.getLog(WeightStandard.class);

	private Integer id;

	private Double curve;

	private Double mean;

	private Double coef;

	private String gender;

	private Integer age;

	private String ageUnit;

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
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 */
	public void setAge(final Integer age) {
		this.age = age;
	}

	/**
	 * @return
	 */
	public String getAgeUnit() {
		return ageUnit;
	}

	/**
	 * @param ageUnit
	 */
	public void setAgeUnit(final String ageUnit) {
		this.ageUnit = ageUnit;
	}

	/**
	 * @return
	 */
	public Double getCoef() {
		return coef;
	}

	/**
	 * @param coef
	 */
	public void setCoef(final Double coef) {
		this.coef = coef;
	}

	/**
	 * @return
	 */
	public Double getCurve() {
		return curve;
	}

	/**
	 * @param curve
	 */
	public void setCurve(final Double curve) {
		this.curve = curve;
	}

	/**
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 */
	public void setGender(final String gender) {
		this.gender = gender;
	}

	/**
	 * @return
	 */
	public Double getMean() {
		return mean;
	}

	/**
	 * @param mean
	 */
	public void setMean(final Double mean) {
		this.mean = mean;
	}
}
