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
package org.streamconnect.dss.access.api;

import org.streamconnect.dss.access.api.builder.LoginBuilder;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.enums.Messages;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import java.util.UUID;

/**
 * This class contains User login related APIs ( login, logout and
 * authenticate).
 *
 * @version 1.0
 */

@Component
@Path("/lg")
public class LoginAPI {
    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger.getLogger(LoginAPI.class);

    /** The Constant TOKEN. */
    private static final String TOKEN = "token";

    /** The login builder. */
    @Autowired
    private LoginBuilder loginBuilder;

    /**
     * Method for user login.
     *
     * @param userName
     *            the user name
     * @param password
     *            the password
     * @param token
     *            the token
     * @param request
     *            the request
     * @return Response
     */
    @POST
    @Path("/sn")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    public Response logIn(@FormParam("userName") final String userName,
            @FormParam("password") final String password,
            @HeaderParam(TOKEN) final String token,
            @Context final HttpServletRequest request) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginAPI : logIn function : " + Constants.START_STATUS);
        String uuId = UUID.randomUUID().toString();
        String objResponse = loginBuilder.logIn(userName, password, token,
                uuId);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginAPI : logIn function : " + Constants.END_STATUS);
        if (token == null || "".equals(token)) {
            return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                    .header(Messages.ACCESS_CONTROL, Messages.COMPLETE_ACCESS)
                    .header(TOKEN, uuId).build();
        } else {
            return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                    .build();
        }
    }

    /**
     * Method for user logout.
     *
     * @param token
     *            the token
     * @return Response
     */
    @POST
    @Path("/lgt")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    public Response logout(@HeaderParam(TOKEN) final String token) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginAPI : logout function : " + Constants.START_STATUS);
        String objResponse = loginBuilder.logout(token);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginAPI : logout function : " + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for checking whether user in session or not.
     *
     * @param token
     *            the token
     * @return Response
     */
    @GET
    @Path("/aut")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    public Response authenticate(@HeaderParam(TOKEN) final String token) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginAPI : authenticate function : "
                + Constants.START_STATUS);
        String objResponse = loginBuilder.authenticate(token);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " LoginAPI : authenticate function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }
}

