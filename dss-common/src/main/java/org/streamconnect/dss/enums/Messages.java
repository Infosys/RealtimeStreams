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
 * This interface provides the static message needed for the application.
 *
 * @version 1. 0
 */
public final class Messages {

    /**
     * private default constructor.
     */
    private Messages() {

    }

    /**
     * The constant RUN_TIME_EXCEPTION_MESSEAGE.
     */
     public static final String RUN_TIME_EXCEPTION_MESSEAGE = "System Error occurred in the server";
    /**
     * The constant SERVICE_NOT_AVAILABLE_MESSEAGE.
     */
     public static final String SERVICE_NOT_AVAILABLE_MESSEAGE = "Service is temporarly on hold";

    /**
     * The constant ENGINE_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String ENGINE_LAYER_EXCEPTION_MESSAGE = "Exception happened in Engine layer";
    /**
     * The constant EXECUTOR_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String EXECUTOR_LAYER_EXCEPTION_MESSAGE = "Exception happened in Executor layer";
    /**
     * The constant VISUALIZE_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String VISUALIZE_LAYER_EXCEPTION_MESSAGE = "Exception happened in Visualize layer";
    /**
     * The constant PORTAL_API_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String PORTAL_API_LAYER_EXCEPTION_MESSAGE = "Exception happened in Portal API layer";
    /**
     * The constant STREAM_API_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String STREAM_API_LAYER_EXCEPTION_MESSAGE = "Exception happened in Stream API layer";
    /**
     * The constant CONTENT_BUILDER_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String COMMON_LAYER_EXCEPTION_MESSAGE = "Exception " +
            "happened in Content builder layer";
    /**
     * The constant SERVICE_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String SERVICE_LAYER_EXCEPTION_MESSAGE = "Exception happened in Service layer";
    /**
     * The constant TRANSACTION_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String TRANSACTION_LAYER_EXCEPTION_MESSAGE = "Exception happened in Transaction layer";
    /**
     * The constant ACCESS_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String ACCESS_LAYER_EXCEPTION_MESSAGE = "Exception happened in User Access layer";

    /**
     * The constant ENGINE_LAYER_INFO_MESSAGE.
     */
     public static final String ENGINE_LAYER_INFO_MESSAGE = "In Engine layer";
    /**
     * The constant EXECUTOR_LAYER_INFO_MESSAGE.
     */
     public static final String EXECUTOR_LAYER_INFO_MESSAGE = "In Executor layer";
    /**
     * The constant VISUALIZE_LAYER_INFO_MESSAGE.
     */
     public static final String VISUALIZE_LAYER_INFO_MESSAGE = "In Visualize layer";
    /**
     * The constant PORTAL_API_LAYER_INFO_MESSAGE.
     */
     public static final String PORTAL_API_LAYER_INFO_MESSAGE = "In Portal API layer";
    /**
     * The constant STREAM_API_LAYER_INFO_MESSAGE.
     */
     public static final String STREAM_API_LAYER_INFO_MESSAGE = "In Stream API layer";
    /**
     * The constant CONTENT_BUILDER_LAYER_INFO_MESSAGE.
     */
     public static final String CONTENT_BUILDER_LAYER_INFO_MESSAGE = "In Content Builder layer";
    /**
     * The constant COMMON_LAYER_INFO_MESSAGE.
     */
     public static final String COMMON_LAYER_INFO_MESSAGE = "In Commons Layer";
    /**
     * The constant SERVICE_LAYER_INFO_MESSAGE.
     */
     public static final String SERVICE_LAYER_INFO_MESSAGE = "In Service layer";
    /**
     * The constant TRANSACTION_LAYER_INFO_MESSAGE.
     */
     public static final String TRANSACTION_LAYER_INFO_MESSAGE = "In Transaction layer";
    /**
     * The constant ACCESS_LAYER_INFO_MESSAGE.
     */
     public static final String ACCESS_LAYER_INFO_MESSAGE = "In User Access layer";

    /**
     * The constant NULLPOINTER_EXCEPTION_MESSAGE.
     */
     public static final String NULLPOINTER_EXCEPTION_MESSAGE = "Null Pointer Exception while converting objects";
    /**
     * The constant AUTH_FAILED_MESSAGE.
     */
     public static final String AUTH_FAILED_MESSAGE = " Authorization failed";
    /**
     * The constant BUSINESS_LAYER_EXCEPTION.
     */
     public static final String BUSINESS_LAYER_EXCEPTION = "Exception happened in the business / logical layer";
    /**
     * The constant AD_SERVICE_EXCEPTION.
     */
     public static final String AD_SERVICE_EXCEPTION = "Registration Success";
    /**
     * The constant SQL_LAYER_EXCEPTION_MESSAGE.
     */
     public static final String SQL_LAYER_EXCEPTION_MESSAGE = "Exception happend while sql execution";
    /**
     * The constant SUCCESS_MESSAGE.
     */
     public static final String SUCCESS_MESSAGE = "Successfully saved the "
            + "configuration";
    /**
     * The constant USER_NOT_REGISTERED.
     */
     public static final String USER_NOT_REGISTERED = "Not a Registered User";
    /**
     * The constant USER_NOT_IN_SESSION.
     */
     public static final String USER_NOT_IN_SESSION = "User Not in Session";
    /**
     * The constant USER_ALREADY_IN_SESSION.
     */
     public static final String USER_ALREADY_IN_SESSION = "User Already in Session";
    /**
     * The constant USER_AUTH_SUCCESSFULL.
     */
     public static final String USER_AUTH_SUCCESSFULL = "Authorization Successfull";
    /**
     * The constant USER_LOG_OUT_SUCCESS.
     */
     public static final String USER_LOG_OUT_SUCCESS = "User Log Out Successfull";
    /**
     * The constant USER_LOG_OUT_FAILED.
     */
     public static final String USER_LOG_OUT_FAILED = "User Log Out Failed";
    /**
     * The constant COMPLETE_ACCESS.
     */
     public static final String COMPLETE_ACCESS = "*";
    /**
     * The constant ACCESS_CONTROL.
     */
    public static final String ACCESS_CONTROL = "Access-Control-Allow-Origin";
    /**
     * The constant ACCESS_LEVEL_MAPPING_FAILED.
     */
     public static final String ACCESS_LEVEL_MAPPING_FAILED = "Access Level Mapping Failed";

    /** The Constant NAME_ALREADY_EXIST. */
    public static final String NAME_ALREADY_EXIST = ", Name Already Exist";
}
