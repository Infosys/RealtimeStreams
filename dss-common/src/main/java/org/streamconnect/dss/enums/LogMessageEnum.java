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
package org.streamconnect.dss.enums;

/**
 * This Enum Populates and Maps the Info and Debug Messages.
 *
 * @version 1.0
 * @serial 1.0
 */
public enum LogMessageEnum {

    /**
     * Engine layer info log message enum.
     */
    ENGINE_LAYER_INFO(Messages.ENGINE_LAYER_INFO_MESSAGE),
    /**
     * Executor layer info log message enum.
     */
    EXECUTOR_LAYER_INFO(Messages.EXECUTOR_LAYER_INFO_MESSAGE),
    /**
     * Visualize layer info log message enum.
     */
    VISUALIZE_LAYER_INFO(Messages.VISUALIZE_LAYER_INFO_MESSAGE),
    /**
     * Portal api layer info log message enum.
     */
    PORTAL_API_LAYER_INFO(Messages.PORTAL_API_LAYER_INFO_MESSAGE),
    /**
     * Stream api layer info log message enum.
     */
    STREAM_API_LAYER_INFO(Messages.STREAM_API_LAYER_INFO_MESSAGE),
    /**
     * Content builder layer info log message enum.
     */
    CONTENT_BUILDER_LAYER_INFO(Messages.CONTENT_BUILDER_LAYER_INFO_MESSAGE),
    /**
     * Common layer info log message enum.
     */
    COMMON_LAYER_INFO(Messages.COMMON_LAYER_INFO_MESSAGE),
    /**
     * Service layer info log message enum.
     */
    SERVICE_LAYER_INFO(Messages.SERVICE_LAYER_INFO_MESSAGE),
    /**
     * Transaction layer info log message enum.
     */
    TRANSACTION_LAYER_INFO(Messages.TRANSACTION_LAYER_INFO_MESSAGE),
    /**
     * Access layer info log message enum.
     */
    ACCESS_LAYER_INFO(Messages.ACCESS_LAYER_INFO_MESSAGE);
    /** The message. */
    private String message;

    /**
     *
     * @param logMessage
     */
     LogMessageEnum(final String logMessage) {
        this.message = logMessage;
    }

    /**
     * Gets message.
     *
     * @return message message
     */
    public String getMessage() {
        return message;
    }



}
