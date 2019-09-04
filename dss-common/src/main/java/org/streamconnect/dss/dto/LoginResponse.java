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

import java.io.Serializable;


/**
 * This is the mocked user session Object for the distributed Systems.
 *
 */
public class LoginResponse implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1856862670651243395L;

    /** The status code. */
    private String statusCode;

    /** The status message. */
    private String statusMessage;

    /** The user session. */
    private UserSessionDto userSession;

    /**
     * Instantiates a new Login response.
     */
    public LoginResponse() {
    }

    /**
     * Instantiates a new Login response.
     *
     * @param statusCode
     *            the status code
     * @param statusMessage
     *            the status message
     */
    public LoginResponse(final String statusCode, final String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    /**
     * Instantiates a new Login response.
     *
     * @param statusCode
     *            the status code
     * @param statusMessage
     *            the status message
     * @param userSession
     *            the user session
     */
    public LoginResponse(final String statusCode, final String statusMessage,
                         final UserSessionDto userSession) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.userSession = userSession;
    }

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets status code.
     *
     * @param statusCode
     *            the status code
     */
    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
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
     * @param statusMessage
     *            the status message
     */
    public void setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * Gets user session.
     *
     * @return the user session
     */
    public UserSessionDto getUserSession() {
        return userSession;
    }

    /**
     * Sets user session.
     *
     * @param userSession
     *            the user session
     */
    public void setUserSession(final UserSessionDto userSession) {
        this.userSession = userSession;
    }
}
