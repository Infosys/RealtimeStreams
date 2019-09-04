/*
* Copyright 2019 Infosys Ltd.
*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.streamconnect.dss.api.ui.response;


import java.util.Date;
import java.util.List;

/**
 * The Class LookupDetails.
 *
 * @version 1.0
 */
public class LookupDetails implements java.io.Serializable {


	/** The in lookup id. */
	private int inLookupId;

	/** The lookup config name. */
	private String lookupConfigName;

	/** The lookup type. */
	private String lookupType;

	/** The lookup config details. */
	private Object lookupConfigDetails;

	/** The str created user. */
	private String strCreatedUser;

	/** The str updated user. */
	private String strUpdatedUser;

	/** The created date. */
	private Date createdDate;

	/** The updated date. */
	private Date updatedDate;

	/** The lookup advanced details. */
	private List<LookupAdvancedDetails> lookupAdvancedDetails;

	/**
	 * Instantiates a new lookup details.
	 */
	public LookupDetails() {
	}

	/**
	 * Gets the in lookup id.
	 *
	 * @return the in lookup id
	 */
	public int getInLookupId() {
		return inLookupId;
	}

	/**
	 * Sets the in lookup id.
	 *
	 * @param inLookupId the new in lookup id
	 */
	public void setInLookupId(int inLookupId) {
		this.inLookupId = inLookupId;
	}

	/**
	 * Gets the lookup config name.
	 *
	 * @return the lookup config name
	 */
	public String getLookupConfigName() {
		return lookupConfigName;
	}

	/**
	 * Sets the lookup config name.
	 *
	 * @param lookupConfigName the new lookup config name
	 */
	public void setLookupConfigName(String lookupConfigName) {
		this.lookupConfigName = lookupConfigName;
	}

	/**
	 * Gets the lookup type.
	 *
	 * @return the lookup type
	 */
	public String getLookupType() {
		return lookupType;
	}

	/**
	 * Sets the lookup type.
	 *
	 * @param lookupType the new lookup type
	 */
	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	/**
	 * Gets the lookup config details.
	 *
	 * @return the lookup config details
	 */
	public Object getLookupConfigDetails() {
		return lookupConfigDetails;
	}

	/**
	 * Sets the lookup config details.
	 *
	 * @param lookupConfigDetails the new lookup config details
	 */
	public void setLookupConfigDetails(Object lookupConfigDetails) {
		this.lookupConfigDetails = lookupConfigDetails;
	}

	/**
	 * Gets the str created user.
	 *
	 * @return the str created user
	 */
	public String getStrCreatedUser() {
		return strCreatedUser;
	}

	/**
	 * Sets the str created user.
	 *
	 * @param strCreatedUser the new str created user
	 */
	public void setStrCreatedUser(String strCreatedUser) {
		this.strCreatedUser = strCreatedUser;
	}

	/**
	 * Gets the str updated user.
	 *
	 * @return the str updated user
	 */
	public String getStrUpdatedUser() {
		return strUpdatedUser;
	}

	/**
	 * Sets the str updated user.
	 *
	 * @param strUpdatedUser the new str updated user
	 */
	public void setStrUpdatedUser(String strUpdatedUser) {
		this.strUpdatedUser = strUpdatedUser;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Sets the updated date.
	 *
	 * @param updatedDate the new updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Gets the lookup advanced details.
	 *
	 * @return the lookup advanced details
	 */
	public List<LookupAdvancedDetails> getLookupAdvancedDetails() {
		return lookupAdvancedDetails;
	}

	/**
	 * Sets the lookup advanced details.
	 *
	 * @param lookupAdvancedDetails the new lookup advanced details
	 */
	public void setLookupAdvancedDetails(List<LookupAdvancedDetails> lookupAdvancedDetails) {
		this.lookupAdvancedDetails = lookupAdvancedDetails;
	}
}
