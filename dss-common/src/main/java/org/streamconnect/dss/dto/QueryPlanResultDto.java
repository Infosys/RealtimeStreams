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
package org.streamconnect.dss.dto;

/**
 * The type Query plan result dto.
 */
public class QueryPlanResultDto {

    /** The is valid. */
    private String isValid;

    /** The status message. */
    private String statusMessage;

    /**
     * Gets is valid.
     *
     * @return the is valid
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * Sets is valid.
     *
     * @param isValid the is valid
     */
    public void setIsValid(final String isValid) {
        this.isValid = isValid;
    }

    /**
     * Gets status message.
     *
     * @return the status message
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets status message.
     *
     * @param statusMessage the status message
     */
    public void setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
