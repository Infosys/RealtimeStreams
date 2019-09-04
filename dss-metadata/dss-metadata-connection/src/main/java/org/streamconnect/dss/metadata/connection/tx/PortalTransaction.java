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
package org.streamconnect.dss.metadata.connection.tx;

import org.streamconnect.dss.dto.AccessLevelDto;
import org.streamconnect.dss.dto.CategoryKpiVisualizeDto;
import org.streamconnect.dss.dto.DashboardDto;
import org.streamconnect.dss.dto.DashboardVisualizeDto;
import org.streamconnect.dss.dto.FeatureAccessDto;
import org.streamconnect.dss.dto.KpiDto;
import org.streamconnect.dss.dto.PortalDto;
import org.streamconnect.dss.dto.RoleDto;
import org.streamconnect.dss.dto.VisualizeDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.PortalTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.dao.DashboardDataAccess;
import org.streamconnect.dss.metadata.connection.dao.PortalDataAccess;
import org.streamconnect.dss.metadata.entities.Dashboard;
import org.streamconnect.dss.metadata.entities.DashboardVisualization;
import org.streamconnect.dss.metadata.entities.FeatureAccess;
import org.streamconnect.dss.metadata.entities.Portal;
import org.streamconnect.dss.metadata.entities.PortalAccess;
import org.streamconnect.dss.metadata.entities.Role;
import org.streamconnect.dss.metadata.entities.Visualize;
import org.streamconnect.dss.util.Constants;
import org.streamconnect.dss.util.PropReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is a transaction class for the Data Persistence Layer. Define the TX
 * policies based on the Transaction Type that define.
 *
 * @version 1.0
 */
@Service
public class PortalTransaction {

    /** The portal data access. */
    @Autowired
    private PortalDataAccess<Portal> portalDataAccess;

    /** The portal dashboard data access. */
    @Autowired
    private PortalDataAccess<Dashboard> portalDashboardDataAccess;

    /** The dashboard role data access. */
    @Autowired
    private DashboardDataAccess<Role> dashboardRoleDataAccess;

    /** The portal visualize data access. */
    @Autowired
    private PortalDataAccess<Visualize> portalVisualizeDataAccess;

    /** The portal access data access. */
    @Autowired
    private PortalDataAccess<PortalAccess> portalAccessDataAccess;

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(PortalTransaction.class);

    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     *            the portal id
     * @return portalDto
     */
    @Transactional(readOnly = true)
    public PortalDto getPortalDetails(final int portalId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getPortalDetails function : "
                + Constants.START_STATUS);
        PortalDto portalDto = null;
        try {
            Portal portal = portalDataAccess.findById(new Portal(), portalId);
            if (portal != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getPortalDetails function "
                        + ": Portal is : Portal Id = " + portal.getPortalId()
                        + ", Portal name = " + portal.getStrPortalName());
                String cssFileDetails = PropReader
                        .getPropertyValue(Constants.CSS_LOCATION) + "\\"
                        + portal.getStrPortalCssName();
                checkFileExistanceAndWrite(cssFileDetails,
                        portal.getStrPortalCss());
                String logoFileDetails = PropReader
                        .getPropertyValue(Constants.LOGO_LOCATION) + "\\"
                        + portal.getStrPortalLogoName();
                checkFileExistanceAndWrite(logoFileDetails,
                        portal.getStrPortalLogo());
                portalDto = new PortalDto();
                portalDto.setPortalId(portal.getPortalId());
                portalDto.setStrPortalName(portal.getStrPortalName());
                portalDto.setStrPortalTitle(portal.getStrPortalTitle());
                portalDto.setStrPortalUrl(portal.getStrPortalUrl());
                portalDto.setStrPortalCss(portal.getStrPortalCssName());
                portalDto.setStrPortalLogo(portal.getStrPortalLogoName());
                portalDto.setCreatedDate(portal.getCreatedDate());
                portalDto.setUpdatedDate(portal.getUpdatedDate());
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getPortalDetails function "
                        + ": Portal is null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "PortalTransaction : in getPortalDetails function : ",
                    e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getPortalDetails function : "
                + Constants.END_STATUS);
        return portalDto;
    }

    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     *            the portal id
     * @return portalDto
     */
    @Transactional(readOnly = true)
    public PortalDto getPortalDetailsForUserAccess(final int portalId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getPortalDetailsForUserAccess function :"
                + " " + Constants.START_STATUS);
        PortalDto portalDto = null;
        try {
            Portal portal = portalDataAccess.findById(new Portal(), portalId);
            if (portal != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : "
                        + "getPortalDetailsForUserAccess function : Portal is :"
                        + " Portal Id = " + portal.getPortalId() + ", Portal "
                        + "name = " + portal.getStrPortalName());
                portalDto = new PortalDto();
                portalDto.setPortalId(portal.getPortalId());
                portalDto.setStrPortalName(portal.getStrPortalName());
                portalDto.setStrPortalTitle(portal.getStrPortalTitle());
                portalDto.setStrPortalUrl(portal.getStrPortalUrl());
                Set<Dashboard> dashboardSet = portal.getDashboards();
                List<DashboardDto> dashboardDtoList = new ArrayList<DashboardDto>();
                for (Dashboard dashboard : dashboardSet) {
                    DashboardDto dashboardDto = new DashboardDto();
                    dashboardDto.setDashboardId(dashboard.getDashboardId());
                    dashboardDto.setStrDashboardlName(
                            dashboard.getStrDashboardlName());
                    List<CategoryKpiVisualizeDto> categoryKpiVisualizeDtoList = getCategoryTreeForPortal(
                            dashboard.getDashboardId());
                    dashboardDto.setCategoryKpiVisualizeDtoList(
                            categoryKpiVisualizeDtoList);
                    dashboardDtoList.add(dashboardDto);
                }
                portalDto.setDashboards(dashboardDtoList);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getPortalDetails function "
                        + ": Portal is null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "PortalTransaction : in getPortalDetailsForUserAccess "
                    + "function : ", e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getPortalDetailsForUserAccess function :"
                + " " + Constants.END_STATUS);
        return portalDto;
    }

    /**
     * Method for checking whether the specified file exist or not, if not
     * create the specified file with given file content.
     *
     * @param fileDetails
     *            the file details
     * @param fileContent
     *            the file content
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private void checkFileExistanceAndWrite(final String fileDetails,
                                            final byte[] fileContent) throws IOException {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : checkFileExistanceAndWrite function : "
                + Constants.START_STATUS);
        File file = new File(fileDetails);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileContent);
            fos.close();
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : checkFileExistanceAndWrite function : "
                + Constants.END_STATUS);
    }

    /**
     * Method for getting Dashboard details.
     *
     * @param dashboardId
     *            the dashboard id
     * @param inRoleId
     *            the in role id
     * @return dashboardDto
     */
    @Transactional(readOnly = true)
    public DashboardDto getDashboardDetails(final int dashboardId,
                                            final int inRoleId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getDashboardDetails function : "
                + Constants.START_STATUS);
        DashboardDto dashboardDto = null;
        try {
            Dashboard dashboard = portalDashboardDataAccess
                    .findById(new Dashboard(), dashboardId);
            if (dashboard != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getDashboardDetails "
                        + "function : Dashboard is : Dashboard Id = "
                        + dashboard.getDashboardId() + ", Dashboard name = "
                        + dashboard.getStrDashboardlName());
                dashboardDto = new DashboardDto();
                dashboardDto.setDashboardId(dashboard.getDashboardId());
                dashboardDto
                        .setStrDashboardlName(dashboard.getStrDashboardlName());
                dashboardDto
                        .setStrDashboardDesc(dashboard.getStrDashboardDesc());
                dashboardDto.setCreatedDate(dashboard.getCreatedDate());
                dashboardDto.setUpdatedDate(dashboard.getUpdatedDate());
                if (dashboard.getPortal() != null) {
                    dashboardDto
                            .setPortalId(dashboard.getPortal().getPortalId());
                }
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus", 1);
                paramMap.put("dashboardId", dashboardId);
                paramMap.put("inRoleId", inRoleId);
                paramMap.put("visualizeViewEnabled", true);
                paramMap.put("dashboardViewEnabled", true);
                String query = "select distinct portAcc.visualize from "
                        + "PortalAccess portAcc where portAcc.dashboard"
                        + ".dashboardId = :dashboardId and portAcc.dashboard"
                        + ".deleteStatus = :deleteStatus and portAcc.visualize"
                        + ".deleteStatus = :deleteStatus and portAcc"
                        + ".accessLevel.role.inRoleId = :inRoleId and portAcc"
                        + ".dashboardViewEnabled = :dashboardViewEnabled and "
                        + "portAcc.visualizeViewEnabled = :visualizeViewEnabled"
                        + " order by portAcc.visualize.dateUpdatedVisualize desc";
                List<Visualize> visualizes = portalVisualizeDataAccess
                        .listAll(query, paramMap);
                List<VisualizeDto> visualizeList = null;
                if (visualizes != null && !visualizes.isEmpty()) {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getDashboardDetails function : Visualize List "
                                    + "Size is : " + visualizes.size());
                    visualizeList = new ArrayList<VisualizeDto>();
                    for (Visualize visualize : visualizes) {
                        if (visualize.getDeleteStatus() == 1) {
                            VisualizeDto visualizeDto = new VisualizeDto();
                            visualizeDto.setInVisualizeId(
                                    visualize.getInVisualizeId());
                            visualizeDto.setStrVisualizeName(
                                    visualize.getStrVisualizeName());
                            visualizeDto.setStrVisualizeConfigDetails(
                                    visualize.getStrVisualizeConfigDetails());
                            visualizeDto.setStrVisualizeParentType(
                                    visualize.getStrVisualizeParentType());
                            visualizeDto.setStrVisualizeSubType(
                                    visualize.getStrVisualizeSubType());
                            visualizeDto.setInVisualizeEntityId(
                                    visualize.getInVisualizeEntityId());
                            visualizeDto
                                    .setStrKeySpace(visualize.getStrKeySpace());
                            visualizeDto.setStrVisualizeDesc(
                                    visualize.getStrVisualizeDesc());
                            visualizeList.add(visualizeDto);
                        }
                    }
                    dashboardDto.setVisualizeDtoList(visualizeList);
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getDashboardDetails function : Visualize List "
                                    + "Size is 0");
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getDashboardDetails "
                        + "function : Dashboard is null");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "PortalTransaction : in getDashboardDetails function : ",
                    e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getDashboardDetails function : "
                + Constants.END_STATUS);
        return dashboardDto;
    }

    /**
     * Method for listing all dashboards.
     *
     * @param portalId
     *            the portal id
     * @param inRoleId
     *            the in role id
     * @return dashboardList
     */
    @Transactional(readOnly = true)
    public List<DashboardDto> getDashboardList(final int portalId,
                                               final int inRoleId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getDashboardList function : "
                + Constants.START_STATUS);
        List<DashboardDto> dashboardList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            parameterList.put("portalId", portalId);
            parameterList.put("inRoleId", inRoleId);
            parameterList.put("portalViewEnabled", true);
            parameterList.put("dashboardViewEnabled", true);
            String query = "select distinct portAcc.dashboard from "
                    + "PortalAccess portAcc where portAcc.portal.portalId = "
                    + ":portalId and portAcc.portal.deleteStatus = "
                    + ":deleteStatus and portAcc.dashboard.deleteStatus = "
                    + ":deleteStatus and portAcc.accessLevel.role.inRoleId = "
                    + ":inRoleId and portAcc.portalViewEnabled = "
                    + ":portalViewEnabled and portAcc.dashboardViewEnabled = "
                    + ":dashboardViewEnabled order by portAcc.dashboard"
                    + ".updatedDate desc";
            List<Dashboard> dashboards = portalDashboardDataAccess
                    .listAll(query, parameterList);
            if (dashboards != null && !dashboards.isEmpty()) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getDashboardList function "
                        + ": Dashboard List Size is : " + dashboards.size());
                dashboardList = new ArrayList<DashboardDto>();
                for (Dashboard dashboard : dashboards) {
                    DashboardDto dashboardDto = new DashboardDto();
                    dashboardDto.setDashboardId(dashboard.getDashboardId());
                    dashboardDto.setStrDashboardlName(
                            dashboard.getStrDashboardlName());
                    dashboardDto.setStrDashboardDesc(
                            dashboard.getStrDashboardDesc());
                    dashboardDto.setCreatedDate(dashboard.getCreatedDate());
                    dashboardDto.setUpdatedDate(dashboard.getUpdatedDate());
                    dashboardDto.setPortalId(portalId);
                    dashboardList.add(dashboardDto);
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getDashboardList function "
                        + ": Dashboard List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "PortalTransaction : in getDashboardList function : ",
                    e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getDashboardList function : "
                + Constants.END_STATUS);
        return dashboardList;
    }

    /**
     * Method for listing all Portals.
     *
     * @param inRoleId
     *            the in role id
     * @return portalDtoList
     */
    @Transactional(readOnly = true)
    public List<PortalDto> getPortalList(final int inRoleId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getPortalList function : "
                + Constants.START_STATUS);
        List<PortalDto> portalDtoList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            parameterList.put("inRoleId", inRoleId);
            parameterList.put("portalViewEnabled", true);
            List<Portal> portalList = portalDataAccess.listAll("select "
                    + "distinct portAcc.portal from PortalAccess portAcc where "
                    + "portAcc.portal.deleteStatus = :deleteStatus and portAcc"
                    + ".accessLevel.role.inRoleId = :inRoleId and portAcc"
                    + ".portalViewEnabled = :portalViewEnabled order by portAcc"
                    + ".portal.updatedDate desc", parameterList);
            if (portalList != null && !portalList.isEmpty()) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getPortalList function : "
                        + "Portal List Size is : " + portalList.size());
                portalDtoList = new ArrayList<PortalDto>();
                for (Portal portal : portalList) {
                    PortalDto portalDto = new PortalDto();
                    portalDto.setPortalId(portal.getPortalId());
                    portalDto.setStrPortalName(portal.getStrPortalName());
                    portalDto.setStrPortalTitle(portal.getStrPortalTitle());
                    portalDto.setStrPortalUrl(portal.getStrPortalUrl());
                    portalDto.setStrPortalCss(portal.getStrPortalCssName());
                    portalDto.setStrPortalLogo(portal.getStrPortalLogoName());
                    portalDto.setCreatedDate(portal.getCreatedDate());
                    portalDto.setUpdatedDate(portal.getUpdatedDate());
                    portalDtoList.add(portalDto);
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getPortalList function : "
                        + "Portal List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " "
                            + "PortalTransaction : in getPortalList function : ",
                    e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getPortalList function : "
                + Constants.END_STATUS);
        return portalDtoList;
    }

    /**
     * Method for listing each Category and all KPIs under each Category and all
     * visualizations under each KPI.
     *
     * @param dashboardId
     *            the dashboard id
     * @return categoryKpiVisualizeDtos
     */
    @Transactional(readOnly = true)
    public List<CategoryKpiVisualizeDto> getCategoryTreeForPortal(
            final int dashboardId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getCategoryTreeForPortal function : "
                + Constants.START_STATUS);
        List<CategoryKpiVisualizeDto> categoryKpiVisualizeList = null;
        try {
            Dashboard dashboard = portalDashboardDataAccess
                    .findById(new Dashboard(), dashboardId);
            if (dashboard != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getCategoryTreeForPortal "
                        + "function : Dashboard is : "
                        + dashboard.getDashboardId() + ", Dashboard name = "
                        + dashboard.getStrDashboardlName());
                Set<DashboardVisualization> dashboardVisualizations = dashboard
                        .getVisualizations();
                Map<String, Map<String, List<VisualizeDto>>> categoryKpiMap = new HashMap<String, Map<String, List<VisualizeDto>>>();
                if (dashboardVisualizations != null
                        && !dashboardVisualizations.isEmpty()) {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getCategoryTreeForPortal function : "
                                    + "Visualization List Size is : "
                                    + dashboardVisualizations.size());
                    for (DashboardVisualization dashboardVisualization : dashboardVisualizations) {
                        if (categoryKpiMap.containsKey(dashboardVisualization
                                .getCategory().getInCategoryId() + "_"
                                + dashboardVisualization.getCategory()
                                .getStrCategoryName())) {
                            Map<String, List<VisualizeDto>> kpiVisualizeMap = categoryKpiMap
                                    .get(dashboardVisualization.getCategory()
                                            .getInCategoryId()
                                            + "_"
                                            + dashboardVisualization
                                            .getCategory()
                                            .getStrCategoryName());
                            if (kpiVisualizeMap.containsKey(
                                    dashboardVisualization.getKpi().getInKpiId()
                                            + "_"
                                            + dashboardVisualization.getKpi()
                                            .getStrKpiName())) {
                                List<VisualizeDto> visualizeList = kpiVisualizeMap
                                        .get(dashboardVisualization.getKpi()
                                                .getInKpiId()
                                                + "_"
                                                + dashboardVisualization
                                                .getKpi()
                                                .getStrKpiName());
                                VisualizeDto visualizeDto = new VisualizeDto();
                                visualizeDto.setInVisualizeId(
                                        dashboardVisualization.getVisualize()
                                                .getInVisualizeId());
                                visualizeDto.setStrVisualizeName(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeName());
                                visualizeDto.setStrVisualizeConfigDetails(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeConfigDetails());
                                visualizeDto.setStrVisualizeParentType(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeParentType());
                                visualizeDto.setStrVisualizeSubType(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeSubType());
                                visualizeDto.setInVisualizeEntityId(
                                        dashboardVisualization.getVisualize()
                                                .getInVisualizeEntityId());
                                visualizeDto.setStrKeySpace(
                                        dashboardVisualization.getVisualize()
                                                .getStrKeySpace());
                                visualizeDto.setStrVisualizeDesc(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeDesc());
                                visualizeList.add(visualizeDto);
                            } else {
                                List<VisualizeDto> visualizeList = new ArrayList<VisualizeDto>();
                                VisualizeDto visualizeDto = new VisualizeDto();
                                visualizeDto.setInVisualizeId(
                                        dashboardVisualization.getVisualize()
                                                .getInVisualizeId());
                                visualizeDto.setStrVisualizeName(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeName());
                                visualizeDto.setStrVisualizeConfigDetails(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeConfigDetails());
                                visualizeDto.setStrVisualizeParentType(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeParentType());
                                visualizeDto.setStrVisualizeSubType(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeSubType());
                                visualizeDto.setInVisualizeEntityId(
                                        dashboardVisualization.getVisualize()
                                                .getInVisualizeEntityId());
                                visualizeDto.setStrKeySpace(
                                        dashboardVisualization.getVisualize()
                                                .getStrKeySpace());
                                visualizeDto.setStrVisualizeDesc(
                                        dashboardVisualization.getVisualize()
                                                .getStrVisualizeDesc());
                                visualizeList.add(visualizeDto);
                                kpiVisualizeMap.put(dashboardVisualization
                                                .getKpi().getInKpiId()
                                                + "_"
                                                + dashboardVisualization.getKpi()
                                                .getStrKpiName(),
                                        visualizeList);
                            }
                        } else {
                            Map<String, List<VisualizeDto>> kpiVisualizeMap = new HashMap<String, List<VisualizeDto>>();
                            List<VisualizeDto> visualizeList = new ArrayList<VisualizeDto>();
                            VisualizeDto visualizeDto = new VisualizeDto();
                            visualizeDto.setInVisualizeId(dashboardVisualization
                                    .getVisualize().getInVisualizeId());
                            visualizeDto.setStrVisualizeName(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeName());
                            visualizeDto.setStrVisualizeConfigDetails(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeConfigDetails());
                            visualizeDto.setStrVisualizeParentType(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeParentType());
                            visualizeDto.setStrVisualizeSubType(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeSubType());
                            visualizeDto.setInVisualizeEntityId(
                                    dashboardVisualization.getVisualize()
                                            .getInVisualizeEntityId());
                            visualizeDto.setStrKeySpace(dashboardVisualization
                                    .getVisualize().getStrKeySpace());
                            visualizeDto.setStrVisualizeDesc(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeDesc());
                            visualizeList.add(visualizeDto);
                            kpiVisualizeMap.put(
                                    dashboardVisualization.getKpi().getInKpiId()
                                            + "_"
                                            + dashboardVisualization.getKpi()
                                            .getStrKpiName(),
                                    visualizeList);
                            categoryKpiMap.put(dashboardVisualization
                                            .getCategory().getInCategoryId()
                                            + "_"
                                            + dashboardVisualization.getCategory()
                                            .getStrCategoryName(),
                                    kpiVisualizeMap);
                        }
                    }
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getCategoryTreeForPortal function : "
                                    + "Visualization List Size is 0");
                }
                if (categoryKpiMap != null && !categoryKpiMap.isEmpty()) {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getCategoryTreeForPortal function : Category Kpi"
                                    + " Map Size is " + categoryKpiMap.size());
                    categoryKpiVisualizeList = new ArrayList<CategoryKpiVisualizeDto>();
                    for (Map.Entry<String, Map<String, List<VisualizeDto>>> entry : categoryKpiMap
                            .entrySet()) {
                        CategoryKpiVisualizeDto categoryKpiVisualizeDto = new CategoryKpiVisualizeDto();
                        String[] idNameArray = entry.getKey().split("_");
                        categoryKpiVisualizeDto.setInCategoryId(
                                Integer.valueOf(idNameArray[0]));
                        categoryKpiVisualizeDto
                                .setStrCategoryName(idNameArray[1]);
                        Map<String, List<VisualizeDto>> kpiVisualizeMap = entry
                                .getValue();
                        List<KpiDto> kpiList = new ArrayList<KpiDto>();
                        for (Map.Entry<String, List<VisualizeDto>> kpiEntry : kpiVisualizeMap
                                .entrySet()) {
                            KpiDto kpiDto = new KpiDto();
                            String[] idNameKpiArray = kpiEntry.getKey()
                                    .split("_");
                            kpiDto.setInKpiId(
                                    Integer.valueOf(idNameKpiArray[0]));
                            kpiDto.setStrKpiName(idNameKpiArray[1]);
                            kpiDto.setVisualizations(kpiEntry.getValue());
                            kpiList.add(kpiDto);
                        }
                        categoryKpiVisualizeDto.setKpis(kpiList);
                        categoryKpiVisualizeList.add(categoryKpiVisualizeDto);
                    }
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getCategoryTreeForPortal function : Category Kpi"
                                    + " Visualize List Size is : "
                                    + categoryKpiVisualizeList.size());
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getCategoryTreeForPortal function : Category Kpi"
                                    + " Map Size is 0");
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getCategoryTreeForPortal "
                        + "function : Dashboard is null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "PortalTransaction : in getCategoryTreeForPortal function"
                    + " : ", e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getCategoryTreeForPortal function : "
                + Constants.END_STATUS);
        return categoryKpiVisualizeList;
    }

    /**
     * Method for listing each Category and all KPIs under each Category and all
     * visualizations under each KPI based on role access.
     *
     * @param dashboardId
     *            the dashboard id
     * @param inRoleId
     *            the in role id
     * @return categoryKpiVisualizeDtos
     */
    @Transactional(readOnly = true)
    public List<CategoryKpiVisualizeDto> getCategoryKpiVisualizeTreeForPortal(
            final int dashboardId, final int inRoleId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getCategoryKpiVisualizeTreeForPortal "
                + "function : " + Constants.START_STATUS);
        List<CategoryKpiVisualizeDto> categoryKpiVisualizeList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            parameterList.put("dashboardId", dashboardId);
            parameterList.put("inRoleId", inRoleId);
            parameterList.put("portalViewEnabled", true);
            parameterList.put("dashboardViewEnabled", true);
            parameterList.put("categoryViewEnabled", true);
            parameterList.put("kpiViewEnabled", true);
            parameterList.put("visualizeViewEnabled", true);
            String query = "select distinct portAcc from PortalAccess portAcc"
                    + " where portAcc.dashboard.dashboardId = :dashboardId and "
                    + "portAcc.portal.deleteStatus = :deleteStatus and portAcc"
                    + ".dashboard.deleteStatus = :deleteStatus and portAcc"
                    + ".category.deleteStatus = :deleteStatus and portAcc.kpi"
                    + ".deleteStatus = :deleteStatus and portAcc.accessLevel"
                    + ".role.inRoleId = :inRoleId and portAcc.portalViewEnabled"
                    + " = :portalViewEnabled and portAcc.dashboardViewEnabled ="
                    + " :dashboardViewEnabled and portAcc.categoryViewEnabled ="
                    + " :categoryViewEnabled and portAcc.kpiViewEnabled = "
                    + ":kpiViewEnabled and portAcc.visualizeViewEnabled = "
                    + ":visualizeViewEnabled order by portAcc.visualize"
                    + ".dateUpdatedVisualize desc";
            List<PortalAccess> portalAccessList = portalAccessDataAccess
                    .listAll(query, parameterList);
            if (portalAccessList != null & !portalAccessList.isEmpty()) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : "
                        + "getCategoryKpiVisualizeTreeForPortal function : "
                        + "PortalAccess List Size is : "
                        + portalAccessList.size());
                Map<String, Map<String, List<VisualizeDto>>> categoryKpiMap = new HashMap<String, Map<String, List<VisualizeDto>>>();
                for (PortalAccess dashboardVisualization : portalAccessList) {
                    if (categoryKpiMap.containsKey(dashboardVisualization
                            .getCategory().getInCategoryId() + "_"
                            + dashboardVisualization.getCategory()
                            .getStrCategoryName())) {
                        Map<String, List<VisualizeDto>> kpiVisualizeMap = categoryKpiMap
                                .get(dashboardVisualization.getCategory()
                                        .getInCategoryId() + "_"
                                        + dashboardVisualization.getCategory()
                                        .getStrCategoryName());
                        if (kpiVisualizeMap.containsKey(
                                dashboardVisualization.getKpi().getInKpiId()
                                        + "_" + dashboardVisualization.getKpi()
                                        .getStrKpiName())) {
                            List<VisualizeDto> visualizeList = kpiVisualizeMap
                                    .get(dashboardVisualization.getKpi()
                                            .getInKpiId() + "_"
                                            + dashboardVisualization.getKpi()
                                            .getStrKpiName());
                            VisualizeDto visualizeDto = new VisualizeDto();
                            visualizeDto.setInVisualizeId(dashboardVisualization
                                    .getVisualize().getInVisualizeId());
                            visualizeDto.setStrVisualizeName(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeName());
                            visualizeDto.setStrVisualizeConfigDetails(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeConfigDetails());
                            visualizeDto.setStrVisualizeParentType(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeParentType());
                            visualizeDto.setStrVisualizeSubType(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeSubType());
                            visualizeDto.setInVisualizeEntityId(
                                    dashboardVisualization.getVisualize()
                                            .getInVisualizeEntityId());
                            visualizeDto.setStrKeySpace(dashboardVisualization
                                    .getVisualize().getStrKeySpace());
                            visualizeDto.setStrVisualizeDesc(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeDesc());
                            visualizeList.add(visualizeDto);
                        } else {
                            List<VisualizeDto> visualizeList = new ArrayList<VisualizeDto>();
                            VisualizeDto visualizeDto = new VisualizeDto();
                            visualizeDto.setInVisualizeId(dashboardVisualization
                                    .getVisualize().getInVisualizeId());
                            visualizeDto.setStrVisualizeName(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeName());
                            visualizeDto.setStrVisualizeConfigDetails(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeConfigDetails());
                            visualizeDto.setStrVisualizeParentType(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeParentType());
                            visualizeDto.setStrVisualizeSubType(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeSubType());
                            visualizeDto.setInVisualizeEntityId(
                                    dashboardVisualization.getVisualize()
                                            .getInVisualizeEntityId());
                            visualizeDto.setStrKeySpace(dashboardVisualization
                                    .getVisualize().getStrKeySpace());
                            visualizeDto.setStrVisualizeDesc(
                                    dashboardVisualization.getVisualize()
                                            .getStrVisualizeDesc());
                            visualizeList.add(visualizeDto);
                            kpiVisualizeMap.put(
                                    dashboardVisualization.getKpi().getInKpiId()
                                            + "_"
                                            + dashboardVisualization.getKpi()
                                            .getStrKpiName(),
                                    visualizeList);
                        }
                    } else {
                        Map<String, List<VisualizeDto>> kpiVisualizeMap = new HashMap<String, List<VisualizeDto>>();
                        List<VisualizeDto> visualizeList = new ArrayList<VisualizeDto>();
                        VisualizeDto visualizeDto = new VisualizeDto();
                        visualizeDto.setInVisualizeId(dashboardVisualization
                                .getVisualize().getInVisualizeId());
                        visualizeDto.setStrVisualizeName(dashboardVisualization
                                .getVisualize().getStrVisualizeName());
                        visualizeDto.setStrVisualizeConfigDetails(
                                dashboardVisualization.getVisualize()
                                        .getStrVisualizeConfigDetails());
                        visualizeDto.setStrVisualizeParentType(
                                dashboardVisualization.getVisualize()
                                        .getStrVisualizeParentType());
                        visualizeDto.setStrVisualizeSubType(
                                dashboardVisualization.getVisualize()
                                        .getStrVisualizeSubType());
                        visualizeDto.setInVisualizeEntityId(
                                dashboardVisualization.getVisualize()
                                        .getInVisualizeEntityId());
                        visualizeDto.setStrKeySpace(dashboardVisualization
                                .getVisualize().getStrKeySpace());
                        visualizeDto.setStrVisualizeDesc(dashboardVisualization
                                .getVisualize().getStrVisualizeDesc());
                        visualizeList.add(visualizeDto);
                        kpiVisualizeMap.put(
                                dashboardVisualization.getKpi().getInKpiId()
                                        + "_"
                                        + dashboardVisualization.getKpi()
                                        .getStrKpiName(),
                                visualizeList);
                        categoryKpiMap.put(
                                dashboardVisualization.getCategory()
                                        .getInCategoryId()
                                        + "_"
                                        + dashboardVisualization.getCategory()
                                        .getStrCategoryName(),
                                kpiVisualizeMap);
                    }
                }
                if (categoryKpiMap != null && !categoryKpiMap.isEmpty()) {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getCategoryKpiVisualizeTreeForPortal function : "
                                    + "Category Kpi Map Size is "
                                    + categoryKpiMap.size());
                    categoryKpiVisualizeList = new ArrayList<CategoryKpiVisualizeDto>();
                    for (Map.Entry<String, Map<String, List<VisualizeDto>>> entry : categoryKpiMap
                            .entrySet()) {
                        CategoryKpiVisualizeDto categoryKpiVisualizeDto = new CategoryKpiVisualizeDto();
                        String[] idNameArray = entry.getKey().split("_");
                        categoryKpiVisualizeDto.setInCategoryId(
                                Integer.valueOf(idNameArray[0]));
                        categoryKpiVisualizeDto
                                .setStrCategoryName(idNameArray[1]);
                        Map<String, List<VisualizeDto>> kpiVisualizeMap = entry
                                .getValue();
                        List<KpiDto> kpiList = new ArrayList<KpiDto>();
                        for (Map.Entry<String, List<VisualizeDto>> kpiEntry : kpiVisualizeMap
                                .entrySet()) {
                            KpiDto kpiDto = new KpiDto();
                            String[] idNameKpiArray = kpiEntry.getKey()
                                    .split("_");
                            kpiDto.setInKpiId(
                                    Integer.valueOf(idNameKpiArray[0]));
                            kpiDto.setStrKpiName(idNameKpiArray[1]);
                            kpiDto.setVisualizations(kpiEntry.getValue());
                            kpiList.add(kpiDto);
                        }
                        categoryKpiVisualizeDto.setKpis(kpiList);
                        categoryKpiVisualizeList.add(categoryKpiVisualizeDto);
                    }
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getCategoryKpiVisualizeTreeForPortal function : "
                                    + "Category Kpi Map Size is 0");
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : "
                        + "getCategoryKpiVisualizeTreeForPortal function : "
                        + "PortalAccess List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " " + "PortalTransaction : in "
                            + "getCategoryKpiVisualizeTreeForPortal function : ",
                    e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getCategoryKpiVisualizeTreeForPortal "
                + "function : " + Constants.END_STATUS);
        return categoryKpiVisualizeList;
    }

    /**
     * Method for getting portal dashboard visualize tree.
     *
     * @return portalDtoList
     */
    @Transactional
    public List<PortalDto> getPortalDashboardCategoryKpiVisualizeTree() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : "
                + "getPortalDashboardCategoryKpiVisualizeTree function : "
                + Constants.START_STATUS);
        List<PortalDto> portalDtoList = null;
        List<DashboardDto> dashboardDtoList = null;
        List<DashboardVisualizeDto> dashboardVisualizeDtoList = null;
        try {
            portalDtoList = new ArrayList<PortalDto>();
            Map<String, Object> parameterListPortal = new HashMap<String, Object>();
            parameterListPortal.put("deleteStatus", 1);
            List<Portal> portalList = portalDataAccess.listAll(
                    "from Portal "
                            + "portal where portal.deleteStatus = :deleteStatus",
                    parameterListPortal);
            if (portalList != null && !portalList.isEmpty()) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : "
                        + "getCategoryKpiVisualizeTreeForPortal function : "
                        + "Portal List Size is : " + portalList.size());
                for (Portal portal : portalList) {
                    PortalDto portalDto = new PortalDto();
                    portalDto.setPortalId(portal.getPortalId());
                    portalDto.setStrPortalName(portal.getStrPortalName());
                    Set<Dashboard> dashboardList = portal.getDashboards();
                    if (dashboardList != null && !dashboardList.isEmpty()) {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " PortalTransaction : "
                                + "getCategoryKpiVisualizeTreeForPortal "
                                + "function : Dashboard List Size under the "
                                + "portal : Portal Id = " + portal.getPortalId()
                                + " is : " + dashboardList.size());
                        dashboardDtoList = new ArrayList<DashboardDto>();
                        for (Dashboard dashboard : dashboardList) {
                            if (dashboard.getDeleteStatus() == 1) {
                                DashboardDto dashboardDto = new DashboardDto();
                                dashboardDto.setDashboardId(
                                        dashboard.getDashboardId());
                                dashboardDto.setStrDashboardlName(
                                        dashboard.getStrDashboardlName());
                                List<CategoryKpiVisualizeDto> categoryKpiVisualizeDtoList = getCategoryTreeForPortal(
                                        dashboard.getDashboardId());
                                dashboardDto.setCategoryKpiVisualizeDtoList(
                                        categoryKpiVisualizeDtoList);
                                dashboardDtoList.add(dashboardDto);
                            }
                        }
                        portalDto.setDashboards(dashboardDtoList);
                    } else {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " PortalTransaction : "
                                + "getCategoryKpiVisualizeTreeForPortal "
                                + "function : Dashboard List Size under the "
                                + "portal : Portal Id = " + portal.getPortalId()
                                + " is 0");
                    }
                    portalDtoList.add(portalDto);
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : "
                        + "getCategoryKpiVisualizeTreeForPortal function : "
                        + "Portal List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION
                            .getMessage()
                            + " " + "PortalTransaction : in "
                            + "getPortalDashboardCategoryKpiVisualizeTree function : ",
                    e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : "
                + "getPortalDashboardCategoryKpiVisualizeTree function : "
                + Constants.END_STATUS);
        return portalDtoList;
    }

    /**
     * Method for getting role access details.
     *
     * @param inRoleId
     *            the in role id
     * @return accessLevelDto
     */
    @Transactional(readOnly = true)
    public RoleDto getRoleAccessDetails(final int inRoleId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getRoleAccessDetails function : "
                + Constants.START_STATUS);
        RoleDto roleDto = null;
        try {
            AccessLevelDto accessLevelDto = new AccessLevelDto();
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("roleId", inRoleId);
            Role role = dashboardRoleDataAccess.findById(new Role(), inRoleId);
            if (role != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getRoleAccessDetails "
                        + "function : Role is : Role id = " + role.getInRoleId()
                        + ", Role name = " + role.getStrRoleName());
                roleDto = new RoleDto();
                roleDto.setInRoleId(role.getInRoleId());
                roleDto.setStrRoleName(role.getStrRoleName());
                roleDto.setStrRoleDesc(role.getStrRoleDesc());
                roleDto.setActive(role.isActive());
                int inAccessId = role.getAccessLevel().getInAccessId();
                String strAccessLevelName = role.getAccessLevel()
                        .getStrAccessLevelName();
                Map<String, Object> parameterListAccess = new HashMap<String, Object>();
                parameterListAccess.put("inAccessId", inAccessId);
                Set<PortalAccess> portalAccessList = role.getAccessLevel()
                        .getPortalAccesses();
                List<PortalDto> portalDtoList = getPortalDtoListDetails(
                        portalAccessList);
                Set<FeatureAccess> featureAccessList = role.getAccessLevel()
                        .getFeatureAccesses();
                List<FeatureAccessDto> featureAccessDtoList = null;
                if (featureAccessList != null && !featureAccessList.isEmpty()) {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getRoleAccessDetails function : Feature Access "
                                    + "list Size for the Role : Role id = "
                                    + role.getInRoleId() + ", Role name = "
                                    + role.getStrRoleName() + " is "
                                    + featureAccessList.size());
                    featureAccessDtoList = new ArrayList<FeatureAccessDto>();
                    for (FeatureAccess featureAccess : featureAccessList) {
                        FeatureAccessDto featureAccessDto = new FeatureAccessDto();
                        featureAccessDto.setInFeatureAccessId(
                                featureAccess.getInFeatureAccessId());
                        featureAccessDto.setInFeatureId(
                                featureAccess.getFeature().getInFeatureId());
                        featureAccessDto.setStrFeatureName(
                                featureAccess.getFeature().getStrFeatureName());
                        featureAccessDto.setFeatureViewEnabled(
                                featureAccess.isFeatureViewEnabled());
                        featureAccessDto.setFeatureEditEnabled(
                                featureAccess.isFeatureEditEnabled());
                        featureAccessDto.setFeatureDeleteEnabled(
                                featureAccess.isFeatureDeleteEnabled());
                        featureAccessDtoList.add(featureAccessDto);
                    }
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " PortalTransaction : "
                                    + "getRoleAccessDetails function : Feature Access "
                                    + "list Size for the Role : Role id = "
                                    + role.getInRoleId() + ", Role name = "
                                    + role.getStrRoleName() + " is 0");
                }
                accessLevelDto.setInAccessId(inAccessId);
                accessLevelDto.setStrAccessLevelName(strAccessLevelName);
                accessLevelDto.setPortalDtoList(portalDtoList);
                accessLevelDto.setFeatureAccesses(featureAccessDtoList);
                roleDto.setAccessLevelDto(accessLevelDto);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getRoleAccessDetails "
                        + "function : Role is null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "PortalTransaction : in getRoleAccessDetails function : "
                    + "", e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getRoleAccessDetails function : "
                + Constants.END_STATUS);
        return roleDto;
    }

    /**
     * Method for getting portal dto list.
     *
     * @param portalAccessList
     *            the portal access list
     * @return List
     */
    private List<PortalDto> getPortalDtoListDetails(
            final Set<PortalAccess> portalAccessList) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "PortalTransaction : getPortalDtoListDetails function : "
                + Constants.START_STATUS);
        List<PortalDto> portalDtoList = new ArrayList<PortalDto>();
        try {
            portalDtoList = getPortalDashboardCategoryKpiVisualizeTree();
            if (portalDtoList != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getPortalDtoListDetails "
                        + "function : Portal List Size is "
                        + portalDtoList.size());
                for (PortalDto portalDto : portalDtoList) {
                    for (PortalAccess portalAccess : portalAccessList) {
                        if (portalAccess.getPortal().getPortalId() == portalDto
                                .getPortalId()) {
                            portalDto.setPortalViewEnabled(
                                    portalAccess.isPortalViewEnabled());
                        }
                    }
                    if (portalDto.getDashboards() != null) {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " PortalTransaction : "
                                + "getPortalDtoListDetails function : Dashboard"
                                + " List Size is "
                                + portalDto.getDashboards().size());
                        for (DashboardDto dashboardDto : portalDto
                                .getDashboards()) {
                            for (PortalAccess portalAccess : portalAccessList) {
                                if (portalAccess.getDashboard()
                                        .getDashboardId() == dashboardDto
                                        .getDashboardId()) {
                                    dashboardDto.setDashboardViewEnabled(
                                            portalAccess
                                                    .isDashboardViewEnabled());
                                }
                            }
                            if (dashboardDto
                                    .getCategoryKpiVisualizeDtoList() != null) {
                                LOGGER.debug(
                                        LogMessageEnum.TRANSACTION_LAYER_INFO
                                                .getMessage()
                                                + " PortalTransaction : "
                                                + "getPortalDtoListDetails function : "
                                                + "Category KPI Visualize Combo List "
                                                + "Size is "
                                                + dashboardDto
                                                .getCategoryKpiVisualizeDtoList()
                                                .size());
                                for (CategoryKpiVisualizeDto categoryKpiVisualizeDto : dashboardDto
                                        .getCategoryKpiVisualizeDtoList()) {
                                    for (PortalAccess portalAccess : portalAccessList) {
                                        if (portalAccess.getCategory()
                                                .getInCategoryId() == categoryKpiVisualizeDto
                                                .getInCategoryId()) {
                                            categoryKpiVisualizeDto
                                                    .setCategoryViewEnabled(
                                                            portalAccess
                                                                    .isCategoryViewEnabled());
                                        }
                                    }
                                    if (categoryKpiVisualizeDto
                                            .getKpis() != null) {
                                        LOGGER.debug(
                                                LogMessageEnum.TRANSACTION_LAYER_INFO
                                                        .getMessage() + " "
                                                        + "PortalTransaction : "
                                                        + "getPortalDtoListDetails "
                                                        + "function : KPI List Size is "
                                                        + ""
                                                        + categoryKpiVisualizeDto
                                                        .getKpis()
                                                        .size());
                                        for (KpiDto kpiDto : categoryKpiVisualizeDto
                                                .getKpis()) {
                                            for (PortalAccess portalAccess : portalAccessList) {
                                                if (portalAccess.getKpi()
                                                        .getInKpiId() == kpiDto
                                                        .getInKpiId()) {
                                                    kpiDto.setKpiViewEnabled(
                                                            portalAccess
                                                                    .isKpiViewEnabled());
                                                }
                                            }
                                            if (kpiDto
                                                    .getVisualizations() != null) {
                                                LOGGER.debug(
                                                        LogMessageEnum.TRANSACTION_LAYER_INFO
                                                                .getMessage()
                                                                + " PortalTransaction : getPortalDtoListDetails function : Visualization List Size is "
                                                                + kpiDto.getVisualizations()
                                                                .size());
                                                for (VisualizeDto visualizeDto : kpiDto
                                                        .getVisualizations()) {
                                                    for (PortalAccess portalAccess : portalAccessList) {
                                                        if (portalAccess
                                                                .getVisualize()
                                                                .getInVisualizeId() == visualizeDto
                                                                .getInVisualizeId()) {
                                                            visualizeDto
                                                                    .setVisualizeViewEnabled(
                                                                            portalAccess
                                                                                    .isVisualizeViewEnabled());
                                                            visualizeDto
                                                                    .setInPortalAccessId(
                                                                            portalAccess
                                                                                    .getInPortalAccessId());
                                                        }
                                                    }
                                                }
                                            } else {
                                                LOGGER.debug(
                                                        LogMessageEnum.TRANSACTION_LAYER_INFO
                                                                .getMessage()
                                                                + " PortalTransaction : getPortalDtoListDetails function : Visualization List Size is 0");
                                            }
                                        }
                                    } else {
                                        LOGGER.debug(
                                                LogMessageEnum.TRANSACTION_LAYER_INFO
                                                        .getMessage() + " "
                                                        + "PortalTransaction : "
                                                        + "getPortalDtoListDetails "
                                                        + "function : KPI List Size is "
                                                        + "0");
                                    }
                                }
                            } else {
                                LOGGER.debug(
                                        LogMessageEnum.TRANSACTION_LAYER_INFO
                                                .getMessage()
                                                + " PortalTransaction : "
                                                + "getPortalDtoListDetails function : "
                                                + "Category KPI Visualize Combo List "
                                                + "Size is 0");
                            }
                        }
                    } else {
                        LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                .getMessage() + " PortalTransaction : "
                                + "getPortalDtoListDetails function : Dashboard"
                                + " List Size is 0");
                    }
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " PortalTransaction : getPortalDtoListDetails "
                        + "function : Portal List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "PortalTransaction : in getPortalDtoListDetails function "
                    + ": ", e);
            throw new PortalTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " PortalTransaction : getPortalDtoListDetails function : "
                + Constants.END_STATUS);
        return portalDtoList;
    }

}
