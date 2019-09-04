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

import org.streamconnect.dss.dto.CategoryKpiVisualizeDto;
import org.streamconnect.dss.dto.DashboardDto;
import org.streamconnect.dss.dto.DashboardVisualizeDto;
import org.streamconnect.dss.dto.KpiDto;
import org.streamconnect.dss.dto.PortalDto;
import org.streamconnect.dss.dto.VisualizeDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.VisualizeMgrTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.dao.VisualizeManagerDataAccess;
import org.streamconnect.dss.metadata.entities.Category;
import org.streamconnect.dss.metadata.entities.Dashboard;
import org.streamconnect.dss.metadata.entities.DashboardVisualization;
import org.streamconnect.dss.metadata.entities.Kpi;
import org.streamconnect.dss.metadata.entities.Portal;
import org.streamconnect.dss.metadata.entities.User;
import org.streamconnect.dss.metadata.entities.Visualize;
import org.streamconnect.dss.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a transaction class for the Data Persistence Layer. Define the TX
 * policies based on the Transaction Type that define.
 *
 * @version 1.0
 */
@Service
public class VisualizeManagerTransaction {

    /** The portal visualize manager data access. */
    @Autowired
    private VisualizeManagerDataAccess<Portal> portalVisualizeManagerDataAccess;

    /** The dashboard visualize manager data access. */
    @Autowired
    private VisualizeManagerDataAccess<Dashboard> dashboardVisualizeManagerDataAccess;

    /** The visualize data access. */
    @Autowired
    private VisualizeManagerDataAccess<Visualize> visualizeDataAccess;

    /** The dashboard visualization data access. */
    @Autowired
    private VisualizeManagerDataAccess<DashboardVisualization> dashboardVisualizationDataAccess;

    /** The category visualize manager data access. */
    @Autowired
    private VisualizeManagerDataAccess<Category> categoryVisualizeManagerDataAccess;

    /** The kpi visualize manager data access. */
    @Autowired
    private VisualizeManagerDataAccess<Kpi> kpiVisualizeManagerDataAccess;

    /** The User visualize manager data access. */
    @Autowired
    private VisualizeManagerDataAccess<User> userVisualizeManagerDataAccess;

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(VisualizeManagerTransaction.class);

    /**
     * Method for save or update Portal Details.
     *
     * @param portalDto
     *            the portal dto
     * @return boolean
     */
    @Transactional
    public boolean saveOrUpdatePortal(final PortalDto portalDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : saveOrUpdatePortal function : "
                + "" + Constants.START_STATUS);
        Portal portal = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("inUserId",portalDto.getInUserId());
        User user = userVisualizeManagerDataAccess.findById("from User user"
                        + " where user.inUserId = :inUserId",
                paramMap);
        boolean saveStatus = false;
        try {
            if (portalDto.getPortalId() == 0) {
                portal = new Portal();
                portal.setCreatedDate(new Date());
                portal.setCreatedBy(user);
            } else {
                portal = portalVisualizeManagerDataAccess.findById(new Portal(),
                        portalDto.getPortalId());
                portal.setUpdatedBy(user);
            }
            if (portal != null) {
                portal.setUpdatedDate(new Date());
                portal.setPortalId(portalDto.getPortalId());
                portal.setStrPortalName(portalDto.getStrPortalName());
                portal.setStrPortalTitle(portalDto.getStrPortalTitle());
                portal.setStrPortalUrl(portalDto.getStrPortalUrl());
                if (portalDto.getStrPortalLogoFile() != null
                        && portalDto.getStrPortalLogoFile().length > 0) {
                    portal.setStrPortalLogo(portalDto.getStrPortalLogoFile());
                    portal.setStrPortalLogoName(portalDto.getStrPortalLogo());
                }
                if (portalDto.getStrPortalCssFile() != null
                        && portalDto.getStrPortalCssFile().length > 0) {
                    portal.setStrPortalCss(portalDto.getStrPortalCssFile());
                    portal.setStrPortalCssName(portalDto.getStrPortalCss());
                }
                portal.setDeleteStatus(1);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : "
                        + "saveOrUpdatePortal function : Portal is : Portal id "
                        + "= " + portal.getPortalId() + ", Portal name = "
                        + portal.getStrPortalName());
                saveStatus = portalVisualizeManagerDataAccess
                        .saveOrUpdate(portal);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : "
                        + "saveOrUpdatePortal function : Portal is null");
                saveStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "VisualizeManagerTransaction : in saveOrUpdatePortal "
                    + "function : ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : saveOrUpdatePortal function : "
                + "" + Constants.END_STATUS);
        return saveStatus;
    }

    /**
     * Check portal exist or not.
     *
     * @param portalId the portal id
     * @param portalName the portal name
     * @param userName the user name
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkPortalExistOrNot(int portalId, String portalName,
                                         String userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " VisualizeManagerTransaction : checkPortalExistOrNot function : "
                + Constants.START_STATUS);
        boolean status = false;
        try{
            if(portalName != null && !"".equals(portalName.trim())
                    && userName != null && !"".equals(userName.trim())){
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus",1);
                paramMap.put("portalName", portalName);
                //paramMap.put("userName",userName);
                Portal portal = portalVisualizeManagerDataAccess.findById(
                        "from Portal portal where "
                                + "portal.strPortalName = :portalName and portal"
                                + ".deleteStatus = :deleteStatus",paramMap);
                if(portal != null && portalId != portal.getPortalId()){
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                            + " VisualizeManagerTransaction : checkPortalExistOrNot function:"
                            + " Portal is : Portal id = " + portal.getPortalId()
                            + ", Portal name = " + portal.getStrPortalName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "VisualizeManagerTransaction : in checkPortalExistOrNot "
                    + "function : ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for delete Portal.
     *
     * @param portalId
     *            the portal id
     * @return boolean
     */
    @Transactional
    public boolean deletePortal(final int portalId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : deletePortal function : "
                + Constants.START_STATUS);
        Portal portal = null;
        boolean deleteStatus = false;
        try {
            portal = portalVisualizeManagerDataAccess.findById(new Portal(),
                    portalId);
            if (portal != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : deletePortal "
                        + "function : Deleted Portal is : Portal id = "
                        + portal.getPortalId() + ", Portal name = "
                        + portal.getStrPortalName());
                portal.setDeleteStatus(0);
                deleteStatus = portalVisualizeManagerDataAccess
                        .saveOrUpdate(portal);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : deletePortal "
                        + "function : Deleted Portal is : null");
                deleteStatus = false;
            }
            LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                    + " VisualizeManagerTransaction : deletePortal function : "
                    + Constants.END_STATUS);
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "VisualizeManagerTransaction : in deletePortal function :"
                    + " ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return deleteStatus;
    }

    /**
     * Method for save/update dashboard details.
     *
     * @param dashboardDto
     *            the dashboard dto
     * @return boolean
     */
    @Transactional
    public boolean saveOrUpdateDashboard(final DashboardDto dashboardDto) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : saveOrUpdateDashboard function"
                + " : " + Constants.START_STATUS);
        boolean saveStatus = false;
        try {
            Dashboard dashboard = null;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("inUserId",dashboardDto.getInUserId());
            User user = userVisualizeManagerDataAccess.findById("from User user"
                            + " where user.inUserId = :inUserId",
                    paramMap);
            if (dashboardDto.getPortalId() > 0) {
                if (dashboardDto.getDashboardId() == 0) {
                    dashboard = new Dashboard();
                    dashboard.setCreatedDate(new Date());
                    dashboard.setCreatedBy(user);
                } else {
                    dashboard = dashboardVisualizeManagerDataAccess.findById(
                            new Dashboard(), dashboardDto.getDashboardId());
                    dashboard.setUpdatedBy(user);
                }
                if (dashboard != null) {
                    dashboard.setUpdatedDate(new Date());
                    dashboard.setDashboardId(dashboardDto.getDashboardId());
                    dashboard.setDeleteStatus(1);
                    dashboard.setStrDashboardlName(
                            dashboardDto.getStrDashboardlName());
                    dashboard.setStrDashboardDesc(
                            dashboardDto.getStrDashboardDesc());
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " VisualizeManagerTransaction : "
                                    + "saveOrUpdateDashboard function : Dashboard is : "
                                    + "Dashboard id = "
                                    + dashboard.getDashboardId()
                                    + ", Dashboard name = "
                                    + dashboard.getStrDashboardlName());
                    Set<DashboardVisualization> visualizes = null;
                    if (dashboard.getVisualizations() != null) {
                        visualizes = dashboard.getVisualizations();
                    } else {
                        visualizes = new HashSet<DashboardVisualization>();
                    }
                    visualizes = composeVisualizeSet(visualizes,
                            dashboardDto.getVisualizations());
                    dashboard.setVisualizations(visualizes);
                    Portal portal = portalVisualizeManagerDataAccess
                            .findById(new Portal(), dashboardDto.getPortalId());
                    dashboard.setPortal(portal);
                    portal.getDashboards().add(dashboard);
                    saveStatus = portalVisualizeManagerDataAccess
                            .saveOrUpdate(portal);
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " VisualizeManagerTransaction : "
                                    + "saveOrUpdateDashboard function : Dashboard is : "
                                    + "null");
                    saveStatus = false;
                }
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "VisualizeManagerTransaction : in saveOrUpdateDashboard "
                    + "function : ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : saveOrUpdateDashboard function"
                + " : " + Constants.END_STATUS);
        return saveStatus;
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
    @Transactional(readOnly = true)
    public boolean checkDashboardExistOrNot(int portalId, int dashboardId,
                                   String dashboardName, String  userName) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                + " VisualizeManagerTransaction : checkDashboardExistOrNot "
                + "function : " + Constants.START_STATUS);
        boolean status = false;
        try {
            if(dashboardName != null && !"".equals(dashboardName.trim())
                    && userName != null && !"".equals(userName.trim())) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deleteStatus",1);
                paramMap.put("dashboardName", dashboardName);
                paramMap.put("portalId", portalId);
                //paramMap.put("userName",userName);
                Dashboard dashboard = dashboardVisualizeManagerDataAccess.findById(
                        "from Dashboard dashboard where "
                                + "dashboard.strDashboardlName = :dashboardName and dashboard"
                                + ".deleteStatus = :deleteStatus and "
                                + "dashboard.portal.portalId = :portalId",
                        paramMap);
                if(dashboard != null && dashboardId != dashboard
                        .getDashboardId()) {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                            + " VisualizeManagerTransaction : checkDashboardExistOrNot function:"
                            + " Dashboard is : Dashboard id = " + dashboard.getDashboardId()
                            + ", Dashboard name = " + dashboard.getStrDashboardlName());
                    status = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "VisualizeManagerTransaction : in checkDashboardExistOrNot "
                    + "function : ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        return status;
    }

    /**
     * Method for delete Dashboard.
     *
     * @param dashboardId
     *            the dashboard id
     * @return boolean
     */
    @Transactional
    public boolean deleteDashboard(final int dashboardId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : deleteDashboard function : "
                + Constants.START_STATUS);
        Dashboard dashboard = null;
        boolean deleteStatus = false;
        try {
            dashboard = dashboardVisualizeManagerDataAccess
                    .findById(new Dashboard(), dashboardId);
            if (dashboard != null) {
                dashboard.setDeleteStatus(0);
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : deleteDashboard "
                        + "function : Deleted Dashboard is : Dashboard id = "
                        + dashboard.getDashboardId() + ", Dashboard name = "
                        + dashboard.getStrDashboardlName());
                deleteStatus = dashboardVisualizeManagerDataAccess
                        .saveOrUpdate(dashboard);
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : deleteDashboard "
                        + "function : Deleted Dashboard is : null");
                deleteStatus = false;
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " " + "VisualizeManagerTransaction : in deleteDashboard "
                    + "function : ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : deleteDashboard function : "
                + Constants.END_STATUS);
        return deleteStatus;
    }

    /**
     * Method for composing updated VisualizeSet.
     *
     * @param visualizes
     *            the visualizes
     * @param updatedVisualizeList
     *            the updated visualize list
     * @return visualizeSet
     */
    private Set<DashboardVisualization> composeVisualizeSet(
            final Set<DashboardVisualization> visualizes,
            final List<DashboardVisualizeDto> updatedVisualizeList) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : composeVisualizeSet function :"
                + " " + Constants.START_STATUS);
        Set<DashboardVisualization> visualizeSet = null;
        boolean exist = false;
        if (updatedVisualizeList != null && !updatedVisualizeList.isEmpty()) {
            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                    + " VisualizeManagerTransaction : composeVisualizeSet "
                    + "function : DashboardVisualizeDto List Size is "
                    + updatedVisualizeList.size());
            visualizeSet = new HashSet<DashboardVisualization>();
            for (DashboardVisualizeDto visualizeDto : updatedVisualizeList) {
                exist = false;
                for (DashboardVisualization visualize : visualizes) {
                    if (visualizeDto.getInVisualizeId() == visualize
                            .getVisualize().getInVisualizeId()) {
                        exist = true;
                        visualizeSet.add(visualize);
                    }
                }
                if (!exist) {
                    DashboardVisualization dashboardVisualization = new DashboardVisualization();
                    Category category = categoryVisualizeManagerDataAccess
                            .findById(new Category(),
                                    visualizeDto.getInCategoryId());
                    Kpi kpi = kpiVisualizeManagerDataAccess.findById(new Kpi(),
                            visualizeDto.getInKpiId());
                    Visualize visualize = visualizeDataAccess.findById(
                            new Visualize(), visualizeDto.getInVisualizeId());
                    dashboardVisualization.setId(visualizeDto.getId());
                    dashboardVisualization.setCategory(category);
                    dashboardVisualization.setKpi(kpi);
                    dashboardVisualization.setVisualize(visualize);
                    visualizeSet.add(dashboardVisualization);
                }
            }
        } else {
            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                    + " VisualizeManagerTransaction : composeVisualizeSet "
                    + "function : DashboardVisualizeDto List Size is 0");
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : composeVisualizeSet function :"
                + " " + Constants.END_STATUS);
        return visualizeSet;
    }

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
                + "VisualizeManagerTransaction : getPortalDetails function : "
                + Constants.START_STATUS);
        PortalDto portalDto = null;
        try {
            Portal portal = portalVisualizeManagerDataAccess
                    .findById(new Portal(), portalId);
            if (portal != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : getPortalDetails"
                        + " function : Portal is : Portal id = "
                        + portal.getPortalId() + ", Portal name = "
                        + portal.getStrPortalName());
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
                        + " VisualizeManagerTransaction : getPortalDetails"
                        + " function : Portal is : null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " " + "VisualizeManagerTransaction : in getPortalDetails "
                    + "function : ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : getPortalDetails function : "
                + Constants.END_STATUS);
        return portalDto;
    }

    /**
     * Method for getting Dashboard details.
     *
     * @param dashboardId
     *            the dashboard id
     * @return dashboardDto
     */
    @Transactional(readOnly = true)
    public DashboardDto getDashboardDetails(final int dashboardId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : getDashboardDetails function :"
                + " " + Constants.START_STATUS);
        DashboardDto dashboardDto = null;
        try {
            Dashboard dashboard = dashboardVisualizeManagerDataAccess
                    .findById(new Dashboard(), dashboardId);
            if (dashboard != null) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : "
                        + "getDashboardDetails function : Dashboard is : "
                        + "Dashboard id = " + dashboard.getDashboardId() + ", "
                        + "Dashboard name = "
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
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " VisualizeManagerTransaction : "
                                    + "getDashboardDetails function : Portal is : "
                                    + "Portal id = "
                                    + dashboard.getPortal().getPortalId()
                                    + ", Portal name = "
                                    + dashboard.getPortal().getStrPortalName());
                } else {
                    LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                            .getMessage() + " VisualizeManagerTransaction : "
                            + "getDashboardDetails function : Portal is : null");
                }
                List<DashboardVisualizeDto> visualizeList = null;
                if (dashboard.getVisualizations() != null) {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " VisualizeManagerTransaction : "
                                    + "getDashboardDetails function : Visualization "
                                    + "List Size is "
                                    + dashboard.getVisualizations().size());
                    visualizeList = new ArrayList<DashboardVisualizeDto>();
                    for (DashboardVisualization visualize : dashboard
                            .getVisualizations()) {
                        if (visualize.getVisualize().getDeleteStatus() == 1) {
                            DashboardVisualizeDto visualizeDto = new DashboardVisualizeDto();
                            visualizeDto.setId(visualize.getId());
                            visualizeDto.setInCategoryId(
                                    visualize.getCategory().getInCategoryId());
                            visualizeDto.setStrCategoryName(visualize
                                    .getCategory().getStrCategoryName());
                            visualizeDto.setInKpiId(
                                    visualize.getKpi().getInKpiId());
                            visualizeDto.setStrKpiName(
                                    visualize.getKpi().getStrKpiName());
                            visualizeDto.setInVisualizeId(visualize
                                    .getVisualize().getInVisualizeId());
                            visualizeDto.setStrVisualizeName(visualize
                                    .getVisualize().getStrVisualizeName());
                            visualizeList.add(visualizeDto);
                        }
                    }
                    dashboardDto.setVisualizations(visualizeList);
                } else {
                    LOGGER.debug(
                            LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                                    + " VisualizeManagerTransaction : "
                                    + "getDashboardDetails function : Visualization "
                                    + "List Size is 0");
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : "
                        + "getDashboardDetails function : Dashboard is : null");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "VisualizeManagerTransaction : in getDashboardDetails "
                    + "function : ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : getDashboardDetails function :"
                + " " + Constants.END_STATUS);
        return dashboardDto;
    }

    /**
     * Method for listing all dashboards.
     *
     * @param portalId
     *            the portal id
     * @return dashboardList
     */
    @Transactional(readOnly = true)
    public List<DashboardDto> getDashboardList(final int portalId) {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : getDashboardList function : "
                + Constants.START_STATUS);
        List<DashboardDto> dashboardList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            parameterList.put("portalId", portalId);
            String query = "from Dashboard dashboard where dashboard"
                    + ".deleteStatus = :deleteStatus and dashboard.portal"
                    + ".portalId = :portalId order by dashboard.updatedDate desc";
            List<Dashboard> dashboards = dashboardVisualizeManagerDataAccess
                    .listAll(query, parameterList);
            if (dashboards != null && !dashboards.isEmpty()) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : getDashboardList"
                        + " function : Dashboard List Size is "
                        + dashboards.size());
                dashboardList = new ArrayList<DashboardDto>();
                for (Dashboard dashboard : dashboards) {
                    if (dashboard.getDeleteStatus() == 1) {
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
                }
            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : getDashboardList"
                        + " function : Dashboard List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " " + "VisualizeManagerTransaction : in getDashboardList "
                    + "function : ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : getDashboardList function : "
                + Constants.END_STATUS);
        return dashboardList;
    }

    /**
     * Method for listing all Portals.
     *
     * @return portalDtoList
     */
    @Transactional(readOnly = true)
    public List<PortalDto> getPortalList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : getPortalList function : "
                + Constants.START_STATUS);
        List<PortalDto> portalDtoList = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Portal> portalList = portalVisualizeManagerDataAccess
                    .listAll("from Portal portal  where portal.deleteStatus ="
                            + " :deleteStatus order by portal.updatedDate "
                            + "desc", parameterList);
            if (portalList != null && !portalList.isEmpty()) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : getPortalList "
                        + "function : Portal List Size is "
                        + portalList.size());
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
                        + " VisualizeManagerTransaction : getPortalList "
                        + "function : Portal List Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " "
                    + "VisualizeManagerTransaction : in getPortalList function "
                    + ": ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : getPortalList function : "
                + Constants.END_STATUS);
        return portalDtoList;
    }

    /**
     * Method for listing each Category and all KPIs under each Category and all
     * visualizations under each KPI.
     *
     * @return categoryKpiVisualizeDtos
     */
    @Transactional(readOnly = true)
    public List<CategoryKpiVisualizeDto> getCategoryKpiVisualizeList() {
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : getCategoryKpiVisualizeList "
                + "function : " + Constants.START_STATUS);
        List<CategoryKpiVisualizeDto> categoryKpiVisualizeDtos = null;
        try {
            Map<String, Object> parameterList = new HashMap<String, Object>();
            parameterList.put("deleteStatus", 1);
            List<Category> categoryList = categoryVisualizeManagerDataAccess
                    .listAll("from Category where deleteStatus = "
                            + ":deleteStatus", parameterList);
            if (categoryList != null && !categoryList.isEmpty()) {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : "
                        + "getCategoryKpiVisualizeList function : Category List"
                        + " Size is " + categoryList.size());
                categoryKpiVisualizeDtos = new ArrayList<CategoryKpiVisualizeDto>();
                for (Category category : categoryList) {
                    if (category.getDeleteStatus() == 1) {
                        CategoryKpiVisualizeDto categorykpisDto = new CategoryKpiVisualizeDto();
                        categorykpisDto
                                .setInCategoryId(category.getInCategoryId());
                        categorykpisDto.setStrCategoryName(
                                category.getStrCategoryName());
                        if (category.getKpis() != null
                                && !category.getKpis().isEmpty()) {
                            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                    .getMessage() + " "
                                    + "VisualizeManagerTransaction : "
                                    + "getCategoryKpiVisualizeList function : "
                                    + "KPI List Size is "
                                    + category.getKpis().size());
                            List<KpiDto> kpiList = new ArrayList<KpiDto>();
                            for (Kpi kpi : category.getKpis()) {
                                if (kpi.getDeleteStatus() == 1) {
                                    KpiDto kpiDto = new KpiDto();
                                    kpiDto.setInKpiId(kpi.getInKpiId());
                                    kpiDto.setStrKpiName(kpi.getStrKpiName());
                                    if (kpi.getVisualizes() != null
                                            && !kpi.getVisualizes().isEmpty()) {
                                        LOGGER.debug(
                                                LogMessageEnum.TRANSACTION_LAYER_INFO
                                                        .getMessage() + " "
                                                        + "VisualizeManagerTransaction "
                                                        + ": getCategoryKpiVisualizeList function : Visualize List Size is "
                                                        + kpi.getVisualizes()
                                                        .size());
                                        List<VisualizeDto> visualizeList = new ArrayList<VisualizeDto>();
                                        for (Visualize visualize : kpi
                                                .getVisualizes()) {
                                            if (visualize
                                                    .getDeleteStatus() == 1) {
                                                VisualizeDto visualizeDto = new VisualizeDto();
                                                visualizeDto.setInVisualizeId(
                                                        visualize
                                                                .getInVisualizeId());
                                                visualizeDto
                                                        .setStrVisualizeName(
                                                                visualize
                                                                        .getStrVisualizeName());
                                                visualizeList.add(visualizeDto);
                                            }
                                        }
                                        kpiDto.setVisualizations(visualizeList);
                                    } else {
                                        LOGGER.debug(
                                                LogMessageEnum.TRANSACTION_LAYER_INFO
                                                        .getMessage() + " "
                                                        + "VisualizeManagerTransaction "
                                                        + ": getCategoryKpiVisualizeList function : Visualize List Size is 0");
                                    }
                                    kpiList.add(kpiDto);
                                }
                            }
                            categorykpisDto.setKpis(kpiList);
                        } else {
                            LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO
                                    .getMessage() + " "
                                    + "VisualizeManagerTransaction : "
                                    + "getCategoryKpiVisualizeList function : "
                                    + "KPI List Size is 0");
                        }
                        categoryKpiVisualizeDtos.add(categorykpisDto);
                    }
                }

            } else {
                LOGGER.debug(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage()
                        + " VisualizeManagerTransaction : "
                        + "getCategoryKpiVisualizeList function : Category List"
                        + " Size is 0");
            }
        } catch (Exception e) {
            LOGGER.error(ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                    .toString() + " : "
                    + ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                    + " " + "VisualizeManagerTransaction : in "
                    + "getCategoryKpiVisualizeList function : ", e);
            throw new VisualizeMgrTxnException(
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.TRANSACTION_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.TRANSACTION_LAYER_INFO.getMessage() + " "
                + "VisualizeManagerTransaction : getCategoryKpiVisualizeList "
                + "function : " + Constants.END_STATUS);
        return categoryKpiVisualizeDtos;
    }

}

