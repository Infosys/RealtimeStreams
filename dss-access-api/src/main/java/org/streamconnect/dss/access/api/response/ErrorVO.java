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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * ErrorVO Builder Pojo for creating response builder for error and error
 * handling conditions.
 *
 * @version 1.0
 */
@XmlRootElement(name = "error")
public class ErrorVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5596877677766560928L;

    /** The error code. */
    @XmlElement(name = "errorcode")
    private String errorCode;

    /** The error message. */
    @XmlElement(name = "errormessage")
    private String errorMessage;

    /**
     * Instantiates a new error VO.
     */
    public ErrorVO() {
    }

    /**
     * Gets the error code.
     *
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the error message.
     *
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Instantiates a new error VO.
     *
     * @param errCode
     *            the error code
     * @param errMessage
     *            the error message
     */
    public ErrorVO(final String errCode, final String errMessage) {
        this.errorCode = errCode;
        this.errorMessage = errMessage;
    }

    /**
     * To return response in JSON format({"a":"b"}).
     *
     * @return the string
     */
    @Override
    public String toString() {
        try {
            return new JSONObject().put("errorCode", errorCode)
                    .put("errorMessage", errorMessage).toString();
        } catch (JSONException e) {
            return null;
        }
    }
}
