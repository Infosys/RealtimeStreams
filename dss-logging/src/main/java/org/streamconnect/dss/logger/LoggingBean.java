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
package org.streamconnect.dss.logger;

/**
 * Bean object can be used for the general Logging Details for the Models which
 * are implementing classes and other providers.
 *
 * @see DSSLogger
 * @see LoggingBean
 */
public class LoggingBean {

    /** The model instance id. */
    private String modelInstanceId;

    /** The error code. */
    private String errorCode;

    /** The host name. */
    private String hostName;

    /** The time stamp. */
    private String timeStamp;

    /** The message type. */
    private String messageType;

    /** The class name. */
    private String className;

    /**
     * Gets the model instance id.
     *
     * @return the modelInstanceId
     */
    public String getModelInstanceId() {
        return modelInstanceId;
    }

    /**
     * Sets the model instance id.
     *
     * @param modelInstanceId
     *            the modelInstanceId to set
     */
    public void setModelInstanceId(final String modelInstanceId) {
        this.modelInstanceId = modelInstanceId;
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
     * Gets the host name.
     *
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets the host name.
     *
     * @param hostName
     *            the hostName to set
     */
    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }

    /**
     * Gets the time stamp.
     *
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the time stamp.
     *
     * @param timeStamp
     *            the timeStamp to set
     */
    public void setTimeStamp(final String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets the message type.
     *
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * Sets the message type.
     *
     * @param messageType
     *            the messageType to set
     */
    public void setMessageType(final String messageType) {
        this.messageType = messageType;
    }

    /**
     * Gets the class name.
     *
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the class name.
     *
     * @param className
     *            the className to set
     */
    public void setClassName(final String className) {
        this.className = className;
    }

    /**
     * Instantiates a new logging bean.
     *
     * @param modelInsId
     *            the model ins id
     * @param errCode
     *            the err code
     * @param host
     *            the host
     * @param timeStmp
     *            the time stmp
     * @param msgType
     *            the msg type
     * @param classNm
     *            the class nm
     */
    public LoggingBean(final String modelInsId, final String errCode,
                       final String host, final String timeStmp, final String msgType,
                       final String classNm) {
        super();
        this.modelInstanceId = modelInsId;
        this.errorCode = errCode;
        this.hostName = host;
        this.timeStamp = timeStmp;
        this.messageType = msgType;
        this.className = classNm;
    }

}
