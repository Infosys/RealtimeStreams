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
package org.streamconnect.dss.exception;

import org.apache.log4j.Logger;

/**
 * Exception class for PortalTransaction class.
 *
 * @version 1.0
 */
public class PortalTxnException extends DSSException {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger
            .getLogger(PortalTxnException.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The error code. */
    private String errorCode;

    /** The nested exception. */
    private Exception nestedException;

    /**
     * Instantiates a new portal txn exception.
     */
    public PortalTxnException() {
    }

    /**
     * Instantiates a new portal txn exception.
     *
     * @param message
     *            the message
     * @param errCode
     *            the err code
     */
    public PortalTxnException(final String message, final String errCode) {
        super(message, errCode);
        this.setErrorCode(errCode);
    }

    /**
     * Instantiates a new portal txn exception.
     *
     * @param cause
     *            the cause
     */
    public PortalTxnException(final Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new portal txn exception.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public PortalTxnException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new portal txn exception.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     * @param enableSuppression
     *            the enable suppression
     * @param writableStackTrace
     *            the writable stack trace
     */
    public PortalTxnException(final String message, final Throwable cause,
                              final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
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
     * To string.
     *
     * @return String
     */
    public String toString() {
        StringBuffer errorMsg = new StringBuffer();
        errorMsg.append("[" + this.getErrorCode() + "]:");
        errorMsg.append("[" + super.getMessage() + "]:");
        errorMsg.append((this.nestedException != null)
                ? ("\n[Nested " + "exception]:" + this.nestedException) : "");
        LOGGER.error(errorMsg);
        return errorMsg.toString();
    }
}
