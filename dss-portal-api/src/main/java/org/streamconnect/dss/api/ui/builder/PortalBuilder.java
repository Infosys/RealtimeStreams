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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.streamconnect.dss.api.service.IPortalService;
import org.streamconnect.dss.api.ui.response.AccessLevel;
import org.streamconnect.dss.api.ui.response.CategoryKpiVisualize;
import org.streamconnect.dss.api.ui.response.Dashboard;
import org.streamconnect.dss.api.ui.response.Kpi;
import org.streamconnect.dss.api.ui.response.Portal;
import org.streamconnect.dss.api.ui.response.Role;
import org.streamconnect.dss.api.ui.response.Visualize;
import org.streamconnect.dss.dto.AccessLevelDto;
import org.streamconnect.dss.dto.CategoryKpiVisualizeDto;
import org.streamconnect.dss.dto.DashboardDto;
import org.streamconnect.dss.dto.KpiDto;
import org.streamconnect.dss.dto.PortalDto;
import org.streamconnect.dss.dto.RoleDto;
import org.streamconnect.dss.dto.VisualizeDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.PortalTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;

/**
 * The Class PortalBuilder.
 *
 * @version 1.0
 */
@Component
public class PortalBuilder extends BaseResponseBuilder {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(PortalBuilder.class);

    /** The portal service. */
    @Autowired
    private IPortalService portalService;

    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     *            the portal id
     * @return String
     */
    public String getPortalDetails(final int portalId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalBuilder : getPortalDetails function : "
                + Constants.START_STATUS);
        try {
            Portal portal = null;
            PortalDto portalDto = portalService.getPortalDetails(portalId);
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
            LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                    + " PortalBuilder : getPortalDetails function : "
                    + Constants.END_STATUS);
            return toStringJsonify(portal);
        } catch (PortalTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getPortalDetails function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_FETCH_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getPortalDetails function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_FETCH_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting Dashboard details.
     *
     * @param dashboardId
     *            the dashboard id
     * @param inRoleId
     *            the in role id
     * @return String
     */
    public String getDashboardDetails(final int dashboardId, final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalBuilder : getDashboardDetails function : "
                + Constants.START_STATUS);
        try {
            Dashboard dashboard = null;
            DashboardDto dashboardDto = portalService
                    .getDashboardDetails(dashboardId, inRoleId);
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
                if (dashboardDto.getVisualizeDtoList() != null
                        && !dashboardDto.getVisualizeDtoList().isEmpty()) {
                    List<Visualize> visualizations = new ArrayList<Visualize>();
                    for (VisualizeDto visualizeDto : dashboardDto
                            .getVisualizeDtoList()) {
                        Visualize visualize = new Visualize();
                        visualize.setInVisualizeId(
                                visualizeDto.getInVisualizeId());
                        visualize.setStrVisualizeName(
                                visualizeDto.getStrVisualizeName());
                        visualize.setStrVisualizeConfigDetails(
                                visualizeDto.getStrVisualizeConfigDetails());
                        visualize.setStrVisualizeParentType(
                                visualizeDto.getStrVisualizeParentType());
                        visualize.setStrVisualizeSubType(
                                visualizeDto.getStrVisualizeSubType());
                        visualize.setInVisualizeEntityId(
                                visualizeDto.getInVisualizeEntityId());
                        visualize.setStrKeySpace(visualizeDto.getStrKeySpace());
                        visualize.setStrVisualizeDesc(
                                visualizeDto.getStrVisualizeDesc());
                        visualizations.add(visualize);
                    }
                    dashboard.setVisualizeList(visualizations);
                }
            }
            LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                    + " PortalBuilder : getDashboardDetails function : "
                    + Constants.END_STATUS);
            return toStringJsonify(dashboard);
        } catch (PortalTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getDashboardDetails function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_FETCH_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getDashboardDetails function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_FETCH_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for listing all getDashboardList.
     *
     * @param portalId
     *            the portal id
     * @param inRoleId
     *            the in role id
     * @return String
     */
    public String getDashboardList(final int portalId, final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalBuilder : getDashboardList function : "
                + Constants.START_STATUS);
        try {
            List<Dashboard> dashboardList = null;
            List<DashboardDto> dashboards = portalService
                    .getDashboardList(portalId, inRoleId);
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
            LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                    + " PortalBuilder : getDashboardList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(dashboardList);
        } catch (PortalTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getDashboardList function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_LIST_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getDashboardList function : ",
                    e);
            return getContentBuildingError(Constants.DASHBOARD_LIST_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for listing all Portals.
     *
     * @param inRoleId
     *            the in role id
     * @return String
     */
    public String getPortalList(final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalBuilder : getPortalList function : "
                + Constants.START_STATUS);
        try {
            List<Portal> portalList = null;
            List<PortalDto> portals = portalService.getPortalList(inRoleId);
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
            LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                    + " PortalBuilder : getPortalList function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(portalList);
        } catch (PortalTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getPortalList function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_LIST_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getPortalList function : ",
                    e);
            return getContentBuildingError(Constants.PORTAL_LIST_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for listing each Category and all KPIs under each Category and all
     * visualizations under each KPI.
     *
     * @param dashboardId
     *            the dashboard id
     * @param inRoleId
     *            the in role id
     * @return String
     */
    public String getCategoryKpiVisualizeTreeForPortal(final int dashboardId,
                                                       final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalBuilder : getCategoryKpiVisualizeTreeForPortal function : "
                + Constants.START_STATUS);
        List<CategoryKpiVisualize> categoryKpiVisualizeList = null;
        try {
            List<CategoryKpiVisualizeDto> categoryKpiVisualizeDtos = portalService
                    .getCategoryKpiVisualizeTreeForPortal(dashboardId,
                            inRoleId);
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
                                    visualize.setStrVisualizeConfigDetails(
                                            visualizeDto
                                                    .getStrVisualizeConfigDetails());
                                    visualize.setStrVisualizeParentType(
                                            visualizeDto
                                                    .getStrVisualizeParentType());
                                    visualize
                                            .setStrVisualizeSubType(visualizeDto
                                                    .getStrVisualizeSubType());
                                    visualize
                                            .setInVisualizeEntityId(visualizeDto
                                                    .getInVisualizeEntityId());
                                    visualize.setStrKeySpace(
                                            visualizeDto.getStrKeySpace());
                                    visualize.setStrVisualizeDesc(
                                            visualizeDto.getStrVisualizeDesc());
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
            LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                    + " PortalBuilder : getCategoryKpiVisualizeTreeForPortal function : "
                    + Constants.END_STATUS);
            return toCollectionJsonify(categoryKpiVisualizeList);
        } catch (PortalTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getCategoryKpiVisualizeTreeForPortal function : ",
                    e);
            return getContentBuildingError(
                    Constants.CATEGORY_TREE_COMPOSE_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getCategoryKpiVisualizeTreeForPortal function : ",
                    e);
            return getContentBuildingError(
                    Constants.CATEGORY_TREE_COMPOSE_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }

    /**
     * Method for getting the access levels.
     *
     * @param inRoleId
     *            the in role id
     * @return String
     */
    public String getRoleAccessDetails(final int inRoleId) {
        LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                + " PortalBuilder : getRoleAccessDetails function : "
                + Constants.START_STATUS);
        try {
            RoleDto roleDto = portalService.getRoleAccessDetails(inRoleId);
            Role role = new Role();
            role.setInRoleId(roleDto.getInRoleId());
            role.setStrRoleName(roleDto.getStrRoleName());
            role.setStrRoleDesc(roleDto.getStrRoleDesc());
            role.setActive(roleDto.isActive());
            AccessLevelDto accessLevelDto = roleDto.getAccessLevelDto();
            AccessLevel accessLevel = new AccessLevel();

            List<PortalDto> portalDtoList = accessLevelDto.getPortalDtoList();
            List<Portal> portalList = new ArrayList<Portal>();
            for (PortalDto portalDto : portalDtoList) {
                Portal portal = new Portal();
                portal.setPortalId(portalDto.getPortalId());
                portal.setStrPortalName(portalDto.getStrPortalName());
                portal.setPortalViewEnabled(portalDto.isPortalViewEnabled());
                List<Dashboard> dashboardList = new ArrayList<Dashboard>();
                if (portalDto.getDashboards() != null) {
                    for (DashboardDto dashboardDto : portalDto
                            .getDashboards()) {
                        Dashboard dashboard = new Dashboard();
                        dashboard.setDashboardId(dashboardDto.getDashboardId());
                        dashboard.setStrDashboardlName(
                                dashboardDto.getStrDashboardlName());
                        dashboard.setStrDashboardDesc(
                                dashboardDto.getStrDashboardDesc());
                        dashboard.setDashboardViewEnabled(
                                dashboardDto.isDashboardViewEnabled());
                        List<CategoryKpiVisualize> categoryKpiVisualizeList = new ArrayList<CategoryKpiVisualize>();
                        if (dashboardDto
                                .getCategoryKpiVisualizeDtoList() != null) {
                            for (CategoryKpiVisualizeDto categoryKpiVisualizeDto : dashboardDto
                                    .getCategoryKpiVisualizeDtoList()) {
                                CategoryKpiVisualize categoryKpiVisualize = new CategoryKpiVisualize();
                                categoryKpiVisualize
                                        .setInCategoryId(categoryKpiVisualizeDto
                                                .getInCategoryId());
                                categoryKpiVisualize.setStrCategoryName(
                                        categoryKpiVisualizeDto
                                                .getStrCategoryName());
                                categoryKpiVisualize.setCategoryViewEnabled(
                                        categoryKpiVisualizeDto
                                                .isCategoryViewEnabled());
                                List<Kpi> kpiList = new ArrayList<Kpi>();
                                if (categoryKpiVisualizeDto.getKpis() != null) {
                                    for (KpiDto kpiDto : categoryKpiVisualizeDto
                                            .getKpis()) {
                                        Kpi kpi = new Kpi();
                                        kpi.setInKpiId(kpiDto.getInKpiId());
                                        kpi.setStrKpiName(
                                                kpiDto.getStrKpiName());
                                        kpi.setKpiViewEnabled(
                                                kpiDto.isKpiViewEnabled());
                                        List<Visualize> visualizeList = new ArrayList<Visualize>();
                                        if (kpiDto
                                                .getVisualizations() != null) {
                                            for (VisualizeDto visualizeDto : kpiDto
                                                    .getVisualizations()) {
                                                Visualize visualize = new Visualize();
                                                visualize.setInVisualizeId(
                                                        visualizeDto
                                                                .getInVisualizeId());
                                                visualize.setStrVisualizeName(
                                                        visualizeDto
                                                                .getStrVisualizeName());
                                                visualize
                                                        .setVisualizeViewEnabled(
                                                                visualizeDto
                                                                        .isVisualizeViewEnabled());
                                                visualize.setInPortalAccessId(
                                                        visualizeDto
                                                                .getInPortalAccessId());
                                                visualizeList.add(visualize);
                                            }
                                        }
                                        kpi.setVisualizations(visualizeList);
                                        kpiList.add(kpi);
                                    }
                                }
                                categoryKpiVisualize.setKpis(kpiList);
                                categoryKpiVisualizeList
                                        .add(categoryKpiVisualize);
                            }
                        }
                        dashboard.setCategoryKpiVisualizeList(
                                categoryKpiVisualizeList);
                        dashboardList.add(dashboard);
                    }
                }
                portal.setDashboards(dashboardList);
                portalList.add(portal);
            }
            accessLevel.setInAccessId(accessLevelDto.getInAccessId());
            accessLevel.setStrAccessLevelName(
                    accessLevelDto.getStrAccessLevelName());
            accessLevel.setPortalList(portalList);
            // accessLevel.setPortalAccesses(portalAccessList);
            role.setAccessLevel(accessLevel);
            LOGGER.info(LogMessageEnum.PORTAL_API_LAYER_INFO.getMessage()
                    + " PortalBuilder : getRoleAccessDetails function : "
                    + Constants.END_STATUS);
            return toStringJsonify(role);
        } catch (PortalTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getRoleAccessDetails function : ",
                    e);
            return getContentBuildingError(Constants.ACCESS_FETCH_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION
                            .getMessage()
                            + " in PortalBuilder : getRoleAccessDetails function : ",
                    e);
            return getContentBuildingError(Constants.ACCESS_FETCH_ERROR,
                    ErrorMessageEnum.PORTAL_API_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }
}
