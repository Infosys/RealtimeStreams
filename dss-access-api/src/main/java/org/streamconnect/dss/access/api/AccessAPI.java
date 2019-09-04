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

import org.streamconnect.dss.access.api.builder.AccessBuilder;
import org.streamconnect.dss.access.api.response.Role;
import org.streamconnect.dss.access.api.response.User;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * All the APIs needed to populate ,update , insert and map user, roles and the
 * access levels.
 *
 * @version 1.0
 */
@Component
@Path("ua")
public final class AccessAPI {

    /** LOGGER Object. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(AccessAPI.class);

    /** The Constant TOKEN. */
    private static final String TOKEN = "token";

    /** The access builder. */
    @Autowired
    private AccessBuilder accessBuilder;

    /**
     * Method for get users.
     *
     * @return Response
     */
    @GET
    @Path("/lu")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getUserList function : "
                + Constants.START_STATUS);
        String objResponse = accessBuilder.getUserList();
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getUserList function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for adding user role.
     *
     * @param role
     *            the role
     * @return Response
     */
    @POST
    @Path("/aur")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveRole(final Role role, @Context final HttpServletRequest
            request) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : saveRole function : " + Constants.START_STATUS);
        final String token = request.getHeader(TOKEN);
        String objResponse = accessBuilder.saveRole(role, token);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : saveRole function : " + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for deleting user role.
     *
     * @param inRoleId
     *            the in role id
     * @return Response
     */
    @POST
    @Path("/dur/{inRoleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRole(@PathParam("inRoleId") final int inRoleId) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : deleteRole function : "
                + Constants.START_STATUS);
        String objResponse = accessBuilder.deleteRole(inRoleId);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : deleteRole function : " + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for mapping user and role.
     *
     * @param user
     *            the user
     * @return Response
     */
    @POST
    @Path("/mur")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mapUserRole(final User user, @Context final HttpServletRequest
            request) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : mapUserRole function : "
                + Constants.START_STATUS);
        final String token = request.getHeader(TOKEN);
        String objResponse = accessBuilder.mapUserRole(user, token);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : mapUserRole function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for removing the mapped user and role.
     *
     * @param inUserId
     *            the in user id
     * @return Response
     */
    @POST
    @Path("/rmur/{inUserId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMappedUserRole(
            @PathParam("inUserId") final int inUserId) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : removeMappedUserRole function : "
                + Constants.START_STATUS);
        String objResponse = accessBuilder.removeMappedUserRole(inUserId);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : removeMappedUserRole function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for get roles.
     *
     * @return Response
     */
    @GET
    @Path("/lr")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoleList() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getRoleList function : "
                + Constants.START_STATUS);
        String objResponse = accessBuilder.getRoleList();
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getRoleList function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for getting roles corresponding to the user.
     *
     * @param username
     *            the username
     * @return Response
     */
    @GET
    @Path("/lur")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserRoleDetails(
            @QueryParam("username") final String username) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getUserRoleDetails function : "
                + Constants.START_STATUS);
        String objResponse = accessBuilder.getUserRoleDetails(username);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getUserRoleDetails function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for getting roles corresponding to the user.
     *
     * @return Response
     */
    @GET
    @Path("/lrs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUserRoleDetails() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : listUserRoleDetails function : "
                + Constants.START_STATUS);
        String objResponse = accessBuilder.listUserRoleDetails();
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : listUserRoleDetails function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for get features.
     *
     * @return Response
     */
    @GET
    @Path("/lf")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeatureList() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getFeatureList function : "
                + Constants.START_STATUS);
        String objResponse = accessBuilder.getFeatureList();
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getFeatureList function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for map user role and access.
     *
     * @param role
     *            the role
     * @return Response
     */
    @POST
    @Path("/mra")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mapRoleAccess(final Role role) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : mapRoleAccess function : "
                + Constants.START_STATUS);
        String objResponse = accessBuilder.mapRoleAccess(role);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : mapRoleAccess function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for get access levels.
     *
     * @param inRoleId
     *            the in role id
     * @return Response
     */
    @GET
    @Path("/lra")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRoleAccessDetails(
            @QueryParam("inRoleId") final int inRoleId) {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : listRoleAccessDetails function : "
                + Constants.START_STATUS);
        String objResponse = accessBuilder.getRoleAccessDetails(inRoleId);
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : listRoleAccessDetails function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for get access levels.
     *
     * @return Response
     */
    @GET
    @Path("/gpt")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPortalDashboardCategoryKpiVisualizeTree() {
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getPortalDashboardCategoryKpiVisualizeTree "
                + "function : " + Constants.START_STATUS);
        String objResponse = accessBuilder
                .getPortalDashboardCategoryKpiVisualizeTree();
        LOGGER.info(LogMessageEnum.ACCESS_LAYER_INFO.getMessage()
                + " AccessAPI : getPortalDashboardCategoryKpiVisualizeTree "
                + "function : " + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

}
