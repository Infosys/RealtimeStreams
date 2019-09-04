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
package org.streamconnect.dss.api.ui;

import org.streamconnect.dss.api.ui.builder.UserFunctionBuilder;
import org.streamconnect.dss.api.ui.response.UserFunction;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * All the APIs's needed to populate ,update , insert releted to User Function
 * Components.
 */
@Component
@Path("/uf")
public class UserFunctionAPI {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger.getLogger(UserFunctionAPI
            .class);

    /** The Constant TOKEN. */
    private static final String TOKEN = "token";
    /**
     * The dashboard builder.
     */
    @Autowired
    private UserFunctionBuilder userFunctionBuilder;


    /**
     * Method for saving the user defined function details
     *
     * @param userFunction the User Defined function
     *
     * @return Response response
     */
    @POST
    @Path("/suf")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveOrUpdateUserFunction(
            final UserFunction userFunction,
            @Context final HttpServletRequest request) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardAPI : saveOrUpdateUdf function : " + Constants
                .START_STATUS);
        final String token = request.getHeader(TOKEN);
        String objResponse = userFunctionBuilder.saveOrUpdateUserFunction
                (userFunction, token);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardAPI : saveOrUpdateUdf function : " + Constants
                .END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for getting user function list.
     *
     * @return Response user function list
     */
    @GET
    @Path("/lufn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFunctionList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardAPI : getUserFunctionList function : "
                + Constants.START_STATUS);
        String objResponse = userFunctionBuilder.getUserFunctionList();
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardAPI : getUserFunctionList function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for getting udf details by passing udf id.
     *
     * @param ufId
     *            the udf id
     * @return the UDF data
     */
    @GET
    @Path("/luf")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFunctionData(@QueryParam("udfId") final int ufId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardAPI : getUserFunctionData function : "
                + Constants.START_STATUS);
        String objResponse = userFunctionBuilder.getUserFunctionData(ufId);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardAPI : getUserFunctionData function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for deleting user function by passing udf id.
     *
     * @param ufId the user function id
     * @return Response response
     */
    @POST
    @Path("/duf/{udfId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserFunction(@PathParam("udfId") final int ufId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardAPI : deleteUserFunction function : " + Constants
                .START_STATUS);
        String objResponse = userFunctionBuilder.deleteUserFunction(ufId);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " DashboardAPI : deleteUserFunction function : " + Constants
                .END_STATUS);
        return  Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

}
