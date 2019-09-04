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

package org.streamconnect.dss.api.ui.builder;

import org.streamconnect.dss.access.cache.impl.CacheService;
import org.streamconnect.dss.api.service.IVisualizeManagerService;
import org.streamconnect.dss.api.ui.response.CategoryKpiVisualize;
import org.streamconnect.dss.api.ui.response.Dashboard;
import org.streamconnect.dss.api.ui.response.DashboardVisualize;
import org.streamconnect.dss.api.ui.response.Kpi;
import org.streamconnect.dss.api.ui.response.Portal;
import org.streamconnect.dss.api.ui.response.Visualize;
import org.streamconnect.dss.dto.CategoryKpiVisualizeDto;
import org.streamconnect.dss.dto.DashboardDto;
import org.streamconnect.dss.dto.DashboardVisualizeDto;
import org.streamconnect.dss.dto.KpiDto;
import org.streamconnect.dss.dto.PortalDto;
import org.streamconnect.dss.dto.UserSessionDto;
import org.streamconnect.dss.dto.VisualizeDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.VisualizeMgrServiceException;
import org.streamconnect.dss.exception.VisualizeMgrTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.CommonUtil;
import org.streamconnect.dss.util.Constants;
import com.sun.jersey.core.header.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class VisualizeManagerBuilder.
 *
 */
@Component
public class VisualizeManagerBuilder extends BaseResponseBuilder {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(VisualizeManagerBuilder.class);

    /** The visualize manager service. */
    @Autowired
    private IVisualizeManagerService visualizeManagerService;

    /** The common util. */
    @Autowired
    private CommonUtil commonUtil;

    /** The cache service. */
    @Autowired
    private CacheService cacheService;

    /**
     * Method for save or update Portal Details.
     *
     * @param portalId
     *            the portal id
     * @param strPortalName
     *            the str portal name
     * @param strPortalTitle
     *            the str portal title
     * @param strPortalUrl
     *            the str portal url
     * @param logoStream
     *            the logo stream
     * @param logoDetail
     *            the logo detail
     * @param cssStream
     *            the css stream
     * @param cssDetail
     *            the css detail
     * @return String
     */
    public String saveOrUpdatePortal(final int portalId,
                                     final String strPortalName, final String strPortalTitle,
                                     final String strPortalUrl, final InputStream logoStream,
                                     final FormDataContentDisposition logoDetail,
                                     final InputStream cssStream,
                                     final FormDataContentDisposition
                                             cssDetail, final String token
                                     ) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerBuilder : saveOrUpdatePortal function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkPortalExistOrNot(portalId, strPortalName,
                    userSession.getUserName());
            if(!status) {
                PortalDto portalDto = new PortalDto();
                portalDto.setPortalId(portalId);
                portalDto.setStrPortalName(strPortalName);
                portalDto.setStrPortalTitle(strPortalTitle);
                portalDto.setStrPortalUrl(strPortalUrl);
                if (logoStream != null) {
                    portalDto.setStrPortalLogoFile(
                            commonUtil.toByteArray(logoStream));
                }
                if (cssStream != null) {
                    portalDto
                            .setStrPortalCssFile(commonUtil.toByteArray(cssStream));
                }
                if (cssDetail != null) {
                    portalDto.setStrPortalCss(cssDetail.getFileName());
                }
                if (logoDetail != null) {
                    portalDto.setStrPortalLogo(logoDetail.getFileName());
                }
                // portalDto.setStrCreatedUser(portal.getStrCreatedUser());
                // portalDto.setStrUpdatedUser(portal.getStrUpdatedUser());
                portalDto.setInUserId(userSession.getInUserId());
                status = visualizeManagerService
                        .saveOrUpdatePortal(portalDto);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " VisualizeManagerBuilder : saveOrUpdatePortal function : "
                        + Constants.END_STATUS);
                if (status) {
                    return getContentBuildingSuccessMessage(
                            Constants.PORTAL_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(Constants.PORTAL_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                    .toString());
                }
            } else {
                return getContentBuildingError(Constants.PORTAL_SAVE_ERROR
                                + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : saveOrUpdatePortal function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_SAVE_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (VisualizeMgrServiceException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : saveOrUpdatePortal function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_SAVE_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : saveOrUpdatePortal function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Check portal exist or not.
     *
     * @param portalId the portal id
     * @param portalName the portal name
     * @param userName the user name
     * @return true, if successful
     */
    private boolean checkPortalExistOrNot(int portalId, String portalName,
                                         String userName) {
        boolean status = false;
        if(portalName != null && !"".equals(portalName.trim())
                && userName != null && !"".equals(userName.trim())){
            status = visualizeManagerService.checkPortalExistOrNot(portalId,
                    portalName.trim(), userName.trim());
        }
        return status;
    }

    /**
     * Method for save/update dashboard details.
     *
     * @param dashboard
     *            the dashboard
     * @param token the token
     * @return String
     */
    public String saveOrUpdateDashboard(final Dashboard dashboard,
                                        final String token) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerBuilder : saveOrUpdateDashboard function : "
                + Constants.START_STATUS);
        try {
            final UserSessionDto userSession = cacheService.getUserMap().get(token);
            boolean status = checkDashboardExistOrNot(dashboard.getPortalId(),
                    dashboard.getDashboardId(), dashboard
                            .getStrDashboardlName(), userSession
                            .getUserName());
            if(!status) {
                DashboardDto dashboardDto = new DashboardDto();
                dashboardDto.setDashboardId(dashboard.getDashboardId());
                dashboardDto.setStrDashboardlName(dashboard.getStrDashboardlName());
                dashboardDto.setStrDashboardDesc(dashboard.getStrDashboardDesc());
                dashboardDto.setCreatedDate(dashboard.getCreatedDate());
                dashboardDto.setUpdatedDate(dashboard.getUpdatedDate());
                dashboardDto.setStrCreatedUser(dashboard.getStrCreatedUser());
                dashboardDto.setStrUpdatedUser(dashboard.getStrUpdatedUser());
                dashboardDto.setPortalId(dashboard.getPortalId());
                dashboardDto.setInUserId(userSession.getInUserId());
                List<DashboardVisualizeDto> visualizeList = null;
                if (dashboard.getVisualizations() != null) {
                    visualizeList = new ArrayList<DashboardVisualizeDto>();
                    for (DashboardVisualize visualize : dashboard
                            .getVisualizations()) {
                        DashboardVisualizeDto visualizeDto = new DashboardVisualizeDto();
                        visualizeDto.setId(visualize.getId());
                        visualizeDto.setInCategoryId(visualize.getInCategoryId());
                        visualizeDto
                                .setStrCategoryName(visualize.getStrCategoryName());
                        visualizeDto.setInKpiId(visualize.getInKpiId());
                        visualizeDto.setStrKpiName(visualize.getStrKpiName());
                        visualizeDto.setInVisualizeId(visualize.getInVisualizeId());
                        visualizeDto.setStrVisualizeName(
                                visualize.getStrVisualizeName());
                        visualizeList.add(visualizeDto);
                    }
                    dashboardDto.setVisualizations(visualizeList);
                }
                status = visualizeManagerService
                        .saveOrUpdateDashboard(dashboardDto);
                LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                        + " VisualizeManagerBuilder : saveOrUpdateDashboard function : "
                        + Constants.END_STATUS);
                if (status) {
                    return getContentBuildingSuccessMessage(
                            Constants.DASHBOARD_SAVE_SUCCESS);
                } else {
                    return getContentBuildingError(Constants.DASHBOARD_SAVE_ERROR,
                            ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                    .toString());
                }
            } else {
                return getContentBuildingError(Constants.DASHBOARD_SAVE_ERROR
                                + ErrorMessageEnum
                                .ENTITY_NAME_EXIST_ERROR.getMessage(),
                        ErrorMessageEnum.ENTITY_NAME_EXIST_ERROR.getCode()
                                .toString());
            }
        } catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : saveOrUpdateDashboard function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_SAVE_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        }  catch (VisualizeMgrServiceException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : saveOrUpdateDashboard function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_SAVE_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : saveOrUpdateDashboard function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_SAVE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Check dashboard exist or not.
     *
     * @param portalId the portal id
     * @param dashboardId the dashboard id
     * @param dashboardName the dashboard name
     * @param userName the user name
     * @return true, if successful
     */
    private boolean checkDashboardExistOrNot(int portalId, int dashboardId,
                                            String dashboardName, String
                                                    userName) {
        boolean status = false;
        if (dashboardName != null && !"".equals(dashboardName.trim())
                && userName != null && !"".equals(userName.trim())) {
            status = visualizeManagerService.checkDashboardExistOrNot(portalId,
                    dashboardId, dashboardName.trim(), userName.trim());
        }
        return status;
    }

    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     *            the portal id
     * @return String
     */
    public String getPortalDetails(final int portalId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerBuilder : getPortalDetails function : "
                + Constants.START_STATUS);
        try {
            Portal portal = null;
            PortalDto portalDto = visualizeManagerService
                    .getPortalDetails(portalId);
            if (portalDto != null) {
                portal = new Portal();
                portal.setPortalId(portalDto.getPortalId());
                portal.setStrPortalName(portalDto.getStrPortalName());
                portal.setStrPortalTitle(portalDto.getStrPortalTitle());
                portal.setStrPortalLogo(portalDto.getStrPortalLogo());
                portal.setStrPortalUrl(portalDto.getStrPortalUrl());
                portal.setStrPortalCss(portalDto.getStrPortalCss());
                portal.setCreatedDate(portalDto.getCreatedDate());
                portal.setUpdatedDate(portalDto.getUpdatedDate());
                portal.setStrCreatedUser(portalDto.getStrCreatedUser());
                portal.setStrUpdatedUser(portalDto.getStrUpdatedUser());
                portal.setDeleteStatus(1);
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " VisualizeManagerBuilder : getPortalDetails function : "
                    + Constants.END_STATUS);
            return toStringJsonify(portal);
        } catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getPortalDetails function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_FETCH_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getPortalDetails function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting Dashboard details.
     *
     * @param dashboardId
     *            the dashboard id
     * @return String
     */
    public String getDashboardDetails(final int dashboardId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerBuilder : getDashboardDetails function : "
                + Constants.START_STATUS);
        try {
            Dashboard dashboard = null;
            DashboardDto dashboardDto = visualizeManagerService
                    .getDashboardDetails(dashboardId);
            if (dashboardDto != null) {
                dashboard = new Dashboard();
                dashboard.setCreatedDate(dashboardDto.getCreatedDate());
                dashboard.setUpdatedDate(dashboardDto.getUpdatedDate());
                dashboard.setDashboardId(dashboardDto.getDashboardId());
                dashboard.setDeleteStatus(1);
                dashboard.setStrDashboardlName(
                        dashboardDto.getStrDashboardlName());
                dashboard.setStrDashboardDesc(
                        dashboardDto.getStrDashboardDesc());
                dashboard.setStrCreatedUser(dashboardDto.getStrCreatedUser());
                dashboard.setStrUpdatedUser(dashboardDto.getStrUpdatedUser());
                dashboard.setPortalId(dashboardDto.getPortalId());
                if (dashboardDto.getVisualizations() != null
                        && !dashboardDto.getVisualizations().isEmpty()) {
                    List<DashboardVisualize> visualizations = new ArrayList<DashboardVisualize>();
                    for (DashboardVisualizeDto dashboardVisualizeDto : dashboardDto
                            .getVisualizations()) {
                        DashboardVisualize dashboardVisualize = new DashboardVisualize();
                        dashboardVisualize.setId(dashboardVisualizeDto.getId());
                        dashboardVisualize.setInCategoryId(
                                dashboardVisualizeDto.getInCategoryId());
                        dashboardVisualize.setStrCategoryName(
                                dashboardVisualizeDto.getStrCategoryName());
                        dashboardVisualize
                                .setInKpiId(dashboardVisualizeDto.getInKpiId());
                        dashboardVisualize.setStrKpiName(
                                dashboardVisualizeDto.getStrKpiName());
                        dashboardVisualize.setInVisualizeId(
                                dashboardVisualizeDto.getInVisualizeId());
                        dashboardVisualize.setStrVisualizeName(
                                dashboardVisualizeDto.getStrVisualizeName());
                        visualizations.add(dashboardVisualize);
                    }
                    dashboard.setVisualizations(visualizations);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " VisualizeManagerBuilder : getDashboardDetails function : "
                    + Constants.END_STATUS);
            return toStringJsonify(dashboard);
        } catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getDashboardDetails function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_FETCH_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getDashboardDetails function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_FETCH_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for listing all getDashboardList.
     *
     * @param portalId
     *            the portal id
     * @return String
     */
    public String getDashboardList(final int portalId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerBuilder : getDashboardList function : "
                + Constants.START_STATUS);
        try {
            List<Dashboard> dashboardList = null;
            List<DashboardDto> dashboards = visualizeManagerService
                    .getDashboardList(portalId);
            if (dashboards != null && !dashboards.isEmpty()) {
                dashboardList = new ArrayList<Dashboard>();
                for (DashboardDto dashboardDto : dashboards) {
                    Dashboard dashboard = new Dashboard();
                    dashboard.setDashboardId(dashboardDto.getDashboardId());
                    dashboard.setStrDashboardlName(
                            dashboardDto.getStrDashboardlName());
                    dashboard.setStrDashboardDesc(
                            dashboardDto.getStrDashboardDesc());
                    dashboard.setCreatedDate(dashboardDto.getCreatedDate());
                    dashboard.setUpdatedDate(dashboardDto.getUpdatedDate());
                    dashboard.setStrCreatedUser(
                            dashboardDto.getStrCreatedUser());
                    dashboard.setStrUpdatedUser(
                            dashboardDto.getStrUpdatedUser());
                    dashboard.setPortalId(dashboardDto.getPortalId());
                    dashboardList.add(dashboard);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " VisualizeManagerBuilder : getDashboardList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(dashboardList);
        } catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getDashboardList function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_LIST_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getDashboardList function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_LIST_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for listing all Portals.
     *
     * @return String
     */
    public String getPortalList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerBuilder : getPortalList function : "
                + Constants.START_STATUS);
        try {
            List<Portal> portalList = null;
            List<PortalDto> portals = visualizeManagerService.getPortalList();
            if (portals != null && !portals.isEmpty()) {
                portalList = new ArrayList<Portal>();
                for (PortalDto portalDto : portals) {
                    Portal portal = new Portal();
                    portal.setPortalId(portalDto.getPortalId());
                    portal.setStrPortalName(portalDto.getStrPortalName());
                    portal.setStrPortalTitle(portalDto.getStrPortalTitle());
                    portal.setStrPortalUrl(portalDto.getStrPortalUrl());
                    portal.setStrPortalCss(portalDto.getStrPortalCss());
                    portal.setStrCreatedUser(portalDto.getStrCreatedUser());
                    portal.setStrUpdatedUser(portalDto.getStrUpdatedUser());
                    portal.setCreatedDate(portalDto.getCreatedDate());
                    portal.setUpdatedDate(portalDto.getUpdatedDate());
                    portalList.add(portal);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " VisualizeManagerBuilder : getPortalList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(portalList);
        } catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getPortalList function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_LIST_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getPortalList function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_LIST_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for listing each Category and all KPIs under each Category and all
     * visualizations under each KPI.
     *
     * @return String
     */
    public String getCategoryKpiVisualizeList() {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerBuilder : getCategoryKpiVisualizeList function : "
                + Constants.START_STATUS);
        List<CategoryKpiVisualize> categoryKpiVisualizeList = null;
        try {
            List<CategoryKpiVisualizeDto> categoryKpiVisualizeDtos = visualizeManagerService
                    .getCategoryKpiVisualizeList();
            if (categoryKpiVisualizeDtos != null
                    && !categoryKpiVisualizeDtos.isEmpty()) {
                categoryKpiVisualizeList = new ArrayList<CategoryKpiVisualize>();
                for (CategoryKpiVisualizeDto categoryKpiVisualizeDto : categoryKpiVisualizeDtos) {
                    CategoryKpiVisualize categoryKpiVisualize = new CategoryKpiVisualize();
                    categoryKpiVisualize.setInCategoryId(
                            categoryKpiVisualizeDto.getInCategoryId());
                    categoryKpiVisualize.setStrCategoryName(
                            categoryKpiVisualizeDto.getStrCategoryName());
                    if (categoryKpiVisualizeDto.getKpis() != null
                            && !categoryKpiVisualizeDto.getKpis().isEmpty()) {
                        List<Kpi> kpis = new ArrayList<Kpi>();
                        for (KpiDto kpiDto : categoryKpiVisualizeDto
                                .getKpis()) {
                            Kpi kpi = new Kpi();
                            kpi.setInKpiId(kpiDto.getInKpiId());
                            kpi.setStrKpiName(kpiDto.getStrKpiName());
                            if (kpiDto.getVisualizations() != null
                                    && !kpiDto.getVisualizations().isEmpty()) {
                                List<Visualize> visualizes = new ArrayList<Visualize>();
                                for (VisualizeDto visualizeDto : kpiDto
                                        .getVisualizations()) {
                                    Visualize visualize = new Visualize();
                                    visualize.setInVisualizeId(
                                            visualizeDto.getInVisualizeId());
                                    visualize.setStrVisualizeName(
                                            visualizeDto.getStrVisualizeName());
                                    visualizes.add(visualize);
                                }
                                kpi.setVisualizations(visualizes);
                            }
                            kpis.add(kpi);
                        }
                        categoryKpiVisualize.setKpis(kpis);
                    }
                    categoryKpiVisualizeList.add(categoryKpiVisualize);
                }
            }
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " VisualizeManagerBuilder : getCategoryKpiVisualizeList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(categoryKpiVisualizeList);
        } catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getCategoryKpiVisualizeList function : ",
                    e);
            return getContentBuildingError(
                    Constants.CATEGORY_TREE_COMPOSE_ERROR
                            + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : getCategoryKpiVisualizeList function : ",
                    e);
            return getContentBuildingError(
                    Constants.CATEGORY_TREE_COMPOSE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for delete portal.
     *
     * @param portalId
     *            the portal id
     * @return String
     */
    public String deletePortal(final int portalId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerBuilder : deletePortal function : "
                + Constants.START_STATUS);
        try {
            boolean status = visualizeManagerService.deletePortal(portalId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " VisualizeManagerBuilder : deletePortal function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.PORTAL_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.PORTAL_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : deletePortal function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_DELETE_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : deletePortal function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for delete Dashboard.
     *
     * @param dashboardId
     *            the dashboard id
     * @return String
     */
    public String deleteDashboard(final int dashboardId) {
        LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                + " VisualizeManagerBuilder : deleteDashboard function : "
                + Constants.START_STATUS);
        try {
            boolean status = visualizeManagerService
                    .deleteDashboard(dashboardId);
            LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
                    + " VisualizeManagerBuilder : deleteDashboard function : "
                    + Constants.END_STATUS);
            if (status) {
                return getContentBuildingSuccessMessage(
                        Constants.DASHBOARD_DELETE_SUCCESS);
            } else {
                return getContentBuildingError(Constants.DASHBOARD_DELETE_ERROR,
                        ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                                .toString());
            }
        } catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : deleteDashboard function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_DELETE_ERROR
                    + ". " + Constants.CHECK_LOG, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerBuilder : deleteDashboard function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_DELETE_ERROR,
                    ErrorMessageEnum.STREAM_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }
}
