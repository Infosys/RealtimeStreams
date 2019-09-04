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

package org.streamconnect.dss.access.api.response;

import java.io.Serializable;

/**
 * This is the mocked user session Object for the distributed Systems.
 *
 *
 */
public class LoginResponseVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1856862670651243395L;

    /** The status code. */
    private String statusCode;

    /** The status message. */
    private String statusMessage;

    /** The user session. */
    private UserSession userSession;

    /**
     * Instantiates a new login response VO.
     */
    public LoginResponseVO() {
    }

    /**
     * Instantiates a new login response VO.
     *
     * @param code
     *            the status code
     * @param message
     *            the status message
     */
    public LoginResponseVO(final String code, final String
            message) {
        this.statusCode = code;
        this.statusMessage = message;
    }

    /**
     * Instantiates a new login response VO.
     *
     * @param code
     *            the status code
     * @param message
     *            the status message
     * @param usrSession
     *            the user session
     */
    public LoginResponseVO(final String code, final String message,
                           final UserSession usrSession) {
        this.statusCode = code;
        this.statusMessage = message;
        this.userSession = usrSession;
    }

    /**
     * Gets the serial version UID.
     *
     * @return the serial version UID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Gets the status code.
     *
     * @return the status code
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code.
     *
     * @param statusCode
     *            the new status code
     */
    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
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

    /**
     * Gets the user session.
     *
     * @return the user session
     */
    public UserSession getUserSession() {
        return userSession;
    }

    /**
     * Sets the user session.
     *
     * @param userSession
     *            the new user session
     */
    public void setUserSession(final UserSession userSession) {
        this.userSession = userSession;
    }
}
