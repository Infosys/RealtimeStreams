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
@XmlRootElement(name = "success")
public class SuccessVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The success message. */
    @XmlElement(name = "errormessage")
    private String successMessage;

    /**
     * Instantiates a new success VO.
     */
    public SuccessVO() {
        super();
    }

    /**
     * Instantiates a new success VO.
     *
     * @param successMsg
     *            the success message
     */
    public SuccessVO(final String successMsg) {
        super();

        this.successMessage = successMsg;
    }

    /**
     * Gets the success message.
     *
     * @return the success message
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Sets the success message.
     *
     * @param successMessage
     *            the new success message
     */
    public void setSuccessMessage(final String successMessage) {
        this.successMessage = successMessage;
    }

    /**
     * To return response in JSON format({"a":"b"}).
     *
     * @return the string
     */
    @Override
    public String toString() {
        try {
            return new JSONObject().put("successMessage", successMessage)
                    .toString();
        } catch (JSONException e) {
            return null;
        }
    }
}
