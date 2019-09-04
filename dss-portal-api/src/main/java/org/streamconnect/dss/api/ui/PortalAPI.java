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

import org.streamconnect.dss.api.ui.builder.PortalBuilder;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * All the APIs's needed to populate ,update , insert releted to Dashboard
 * Components.
 *
 */

@Component
@Path("/pa")
public class PortalAPI {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(PortalAPI.class);

    /** The portal builder. */
    @Autowired
    private PortalBuilder portalBuilder;

    /**
     * Method for listing all Portals.
     *
     * @param inRoleId
     *            the in role id
     * @return Response
     */
    @GET
    @Path("/gpl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPortalList(@QueryParam("inRoleId") final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getPortalList function : "
                + Constants.START_STATUS);
        String objResponse = portalBuilder.getPortalList(inRoleId);
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getPortalList function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     *            the portal id
     * @return Response
     */
    @GET
    @Path("/gpd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPortalDetails(
            @QueryParam("portalId") final int portalId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getPortalDetails function : "
                + Constants.START_STATUS);
        String objResponse = portalBuilder.getPortalDetails(portalId);
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getPortalDetails function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for listing all Dashboards of a particular portal.
     *
     * @param portalId
     *            the portal id
     * @param inRoleId
     *            the in role id
     * @return Response
     */
    @GET
    @Path("/gdl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboardList(@QueryParam("portalId") final int portalId,
                                     @QueryParam("inRoleId") final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getDashboardList function : "
                + Constants.START_STATUS);
        String objResponse = portalBuilder.getDashboardList(portalId, inRoleId);
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getDashboardList function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for getting Portal Details.
     *
     * @param dashboardId
     *            the dashboard id
     * @param inRoleId
     *            the in role id
     * @return Response
     */
    @GET
    @Path("/gdbd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboardDetails(
            @QueryParam("dashboardId") final int dashboardId,
            @QueryParam("inRoleId") final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getDashboardDetails function : "
                + Constants.START_STATUS);
        String objResponse = portalBuilder.getDashboardDetails(dashboardId,
                inRoleId);
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getDashboardDetails function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

    /**
     * Method for listing each Category and all KPIs under each Category and all
     * visualizations under each KPI.
     *
     * @param dashboardId
     *            the dashboard id
     * @param inRoleId
     *            the in role id
     * @return Response
     */
    @GET
    @Path("/gctp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryKpiVisualizeTreeForPortal(
            @QueryParam("dashboardId") final int dashboardId,
            @QueryParam("inRoleId") final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getCategoryKpiVisualizeTreeForPortal function : "
                + Constants.START_STATUS);
        String objResponse = portalBuilder
                .getCategoryKpiVisualizeTreeForPortal(dashboardId, inRoleId);
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : getCategoryKpiVisualizeTreeForPortal function : "
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
    @Path("/plra")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRoleAccessDetails(
            @QueryParam("inRoleId") final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : listRoleAccessDetails function : "
                + Constants.START_STATUS);
        String objResponse = portalBuilder.getRoleAccessDetails(inRoleId);
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalAPI : listRoleAccessDetails function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse)
                .build();
    }

}

