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

import org.streamconnect.dss.api.ui.builder.VisualizeManagerBuilder;
import org.streamconnect.dss.api.ui.response.Dashboard;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;


/**
 * All the APIs's needed to populate ,update , insert releted to Visualize Manager
 * Components.
 *
 */

@Component
@Path("/vm")
public class VisualizeManagerAPI {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger.getLogger(
            VisualizeManagerAPI.class);

    /** The Constant TOKEN. */
    private static final String TOKEN = "token";

    /**
     * The Visualize Manager Builder.
     */
    @Autowired
    private VisualizeManagerBuilder visualizeManagerBuilder;

    /**
     * Method for save or update Portal Details.
     *
     * @param portalId
     * @param strPortalName
     * @param strPortalTitle
     * @param strPortalUrl
     * @param logoStream
     * @param logoDetail
     * @param cssStream
     * @param cssDetail
     * @param request the request
     * @return Response
     */
    @POST
    @Path("/sprl")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveOrUpdatePortal(
            @FormDataParam("portalId") final Integer portalId,
            @FormDataParam("strPortalName") final String strPortalName,
            @FormDataParam("strPortalTitle") final String strPortalTitle,
            @FormDataParam("strPortalUrl") final String strPortalUrl,
            @FormDataParam("strPortalLogo") final InputStream logoStream,
            @FormDataParam("strPortalLogoDetails") final FormDataContentDisposition logoDetail,
            @FormDataParam("strPortalCss") final InputStream cssStream,
            @FormDataParam("strPortalCssDetails") final
            FormDataContentDisposition cssDetail,
            @Context final HttpServletRequest request) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : saveOrUpdatePortal function : "
                + Constants.START_STATUS);
        final String token = request.getHeader(TOKEN);
        String objResponse = visualizeManagerBuilder.saveOrUpdatePortal(portalId,
                strPortalName, strPortalTitle, strPortalUrl, logoStream,
                logoDetail, cssStream, cssDetail, token);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : saveOrUpdatePortal function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for listing all Portals.
     *
     * @return Response
     */
    @GET
    @Path("/gpl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPortalList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getPortalList function : "
                + Constants.START_STATUS);
        String objResponse = visualizeManagerBuilder.getPortalList();
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getPortalList function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     * @return Response
     */
    @GET
    @Path("/gpd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPortalDetails(
            @QueryParam("portalId") final int portalId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getPortalDetails function : "
                + Constants.START_STATUS);
        String objResponse = visualizeManagerBuilder.getPortalDetails(portalId);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getPortalDetails function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for delete portal.
     *
     * @param portalId
     * @return Response
     */
    @POST
    @Path("/dprl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePortal(@QueryParam("portalId") final int portalId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : deletePortal function : "
                + Constants.START_STATUS);
        String objResponse = visualizeManagerBuilder.deletePortal(portalId);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : deletePortal function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for save/update dashboard details.
     *
     * @param dashboard
     * @return Response
     */
    @POST
    @Path("/sdbd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveOrUpdateDashboard(final Dashboard dashboard,
                                   @Context final HttpServletRequest request) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : saveOrUpdateDashboard function : "
                + Constants.START_STATUS);
        final String token = request.getHeader(TOKEN);
        String objResponse = visualizeManagerBuilder.saveOrUpdateDashboard
                (dashboard, token);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : saveOrUpdateDashboard function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for listing all Dashboards of a particular portal.
     *
     * @param portalId
     * @return Response
     */
    @GET
    @Path("/gdl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboardList(@QueryParam("portalId") final int
                                             portalId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getDashboardList function : "
                + Constants.START_STATUS);
        String objResponse = visualizeManagerBuilder.getDashboardList(portalId);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getDashboardList function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for getting Portal Details.
     *
     * @param dashboardId
     * @return Response
     */
    @GET
    @Path("/gdbd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboardDetails(@QueryParam("dashboardId") final int
                                                dashboardId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getDashboardDetails function : "
                + Constants.START_STATUS);
        String objResponse = visualizeManagerBuilder.getDashboardDetails(dashboardId);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getDashboardDetails function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for delete Dashboard.
     *
     * @param dashboardId
     * @return Response
     */
    @POST
    @Path("/dbd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDashboard(@QueryParam("dashboardId") final int
                                            dashboardId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : deleteDashboard function : "
                + Constants.START_STATUS);
        String objResponse = visualizeManagerBuilder.deleteDashboard(dashboardId);
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : deleteDashboard function : "
                + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

    /**
     * Method for listing each Category and all KPIs under each Category and all
     * visualizations under each KPI.
     *
     * @return Response
     */
    @GET
    @Path("/gct")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryKpiVisualizeList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getCategoryKpiVisualizeList "
                + "function : " + Constants.START_STATUS);
        String objResponse = visualizeManagerBuilder.getCategoryKpiVisualizeList();
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerAPI : getCategoryKpiVisualizeList "
                + "function : " + Constants.END_STATUS);
        return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
    }

}
