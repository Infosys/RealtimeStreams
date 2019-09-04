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
 * This Enum Populates and Maps the Exception Error Code with the Error
 * Messages.
 *
 * @version 1.0
 * @serial 1.0
 */
public enum ErrorMessageEnum {

    /**
     * Run time exception error message enum.
     */
    RUN_TIME_EXCEPTION(3000, Messages.RUN_TIME_EXCEPTION_MESSEAGE),
    /**
     * Service not available error message enum.
     */
    SERVICE_NOT_AVAILABLE(3001, Messages.SERVICE_NOT_AVAILABLE_MESSEAGE),
    /**
     * Nullpointer exception message error message enum.
     */
    NULLPOINTER_EXCEPTION_MESSAGE(3003, Messages.NULLPOINTER_EXCEPTION_MESSAGE),
    /**
     * Sql layer exception error message enum.
     */
    SQL_LAYER_EXCEPTION(3004, Messages.SQL_LAYER_EXCEPTION_MESSAGE),

    /**
     * Engine layer exception error message enum.
     */
    ENGINE_LAYER_EXCEPTION(11000, Messages.ENGINE_LAYER_EXCEPTION_MESSAGE),
    /**
     * Executor layer exception error message enum.
     */
    EXECUTOR_LAYER_EXCEPTION(10000, Messages.EXECUTOR_LAYER_EXCEPTION_MESSAGE),
    /**
     * Visualize layer exception error message enum.
     */
    VISUALIZE_LAYER_EXCEPTION(9000, Messages.VISUALIZE_LAYER_EXCEPTION_MESSAGE),
    /**
     * Portal api layer exception error message enum.
     */
    PORTAL_API_LAYER_EXCEPTION(8000, Messages.PORTAL_API_LAYER_EXCEPTION_MESSAGE),
    /**
     * Stream api layer exception error message enum.
     */
    STREAM_API_LAYER_EXCEPTION(7000, Messages.STREAM_API_LAYER_EXCEPTION_MESSAGE),
    /**
     * Content builder layer exception error message enum.
     */
    COMMON_LAYER_EXCEPTION(6000, Messages
            .COMMON_LAYER_EXCEPTION_MESSAGE),
    /**
     * Service layer exception error message enum.
     */
    SERVICE_LAYER_EXCEPTION(5000, Messages.SERVICE_LAYER_EXCEPTION_MESSAGE),
    /**
     * Transaction layer exception error message enum.
     */
    TRANSACTION_LAYER_EXCEPTION(4000, Messages.TRANSACTION_LAYER_EXCEPTION_MESSAGE),
    /**
     * Access layer exception error message enum.
     */
    ACCESS_LAYER_EXCEPTION(3000, Messages.ACCESS_LAYER_EXCEPTION_MESSAGE),

    /**
     * Auth failed message error message enum.
     */
    AUTH_FAILED_MESSAGE(3006, Messages.AUTH_FAILED_MESSAGE),
    /**
     * User not registered error message enum.
     */
    USER_NOT_REGISTERED(3007, Messages.USER_NOT_REGISTERED),
    /**
     * User not in session error message enum.
     */
    USER_NOT_IN_SESSION(3008, Messages.USER_NOT_IN_SESSION),
    /**
     * User already in session error message enum.
     */
    USER_ALREADY_IN_SESSION(3009, Messages.USER_ALREADY_IN_SESSION),
    /**
     * User auth successfull error message enum.
     */
    USER_AUTH_SUCCESSFULL(3010, Messages.USER_AUTH_SUCCESSFULL),
    /**
     * User log out success error message enum.
     */
    USER_LOG_OUT_SUCCESS(3011, Messages.USER_LOG_OUT_SUCCESS),
    /**
     * User log out failed error message enum.
     */
    USER_LOG_OUT_FAILED(3012, Messages.USER_LOG_OUT_FAILED),
    /** The entity name exist error. */
    ENTITY_NAME_EXIST_ERROR(7007, Messages.NAME_ALREADY_EXIST),
    /**
     * User access level mapping failed error message enum.
     */
    USER_ACCESS_LEVEL_MAPPING_FAILED(3013, Messages.ACCESS_LEVEL_MAPPING_FAILED);



    /** The code. */
    private Integer code;
    /** The message. */
    private String message;

    /**
     *
     * @param errorCode
     * @param errorMessage
     */
     ErrorMessageEnum(final int errorCode, final String errorMessage) {
        this.code = errorCode;
        this.message = errorMessage;
    }

    /**
     * Gets code.
     *
     * @return code code
     */
    public Integer getCode() {
        return code;
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
