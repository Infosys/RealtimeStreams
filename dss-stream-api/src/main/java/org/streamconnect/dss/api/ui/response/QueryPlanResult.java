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

/**
 * The Class QueryPlanResult.
 *
 * @version 1.0
 */
public class QueryPlanResult {

    /** The is valid. */
    private String isValid;

    /** The status message. */
    private String statusMessage;

    /**
     * Gets the checks if is valid.
     *
     * @return the checks if is valid
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * Sets the checks if is valid.
     *
     * @param isValid
     *            the new checks if is valid
     */
    public void setIsValid(final String isValid) {
        this.isValid = isValid;
    }

    /**
     * Gets the status message.
     *
     * @return the status message
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the status message.
     *
     * @param statusMessage
     *            the new status message
     */
    public void setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
