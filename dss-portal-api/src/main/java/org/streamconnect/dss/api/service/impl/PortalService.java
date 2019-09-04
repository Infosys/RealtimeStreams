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
package org.streamconnect.dss.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.streamconnect.dss.api.service.IPortalService;
import org.streamconnect.dss.dto.CategoryKpiVisualizeDto;
import org.streamconnect.dss.dto.DashboardDto;
import org.streamconnect.dss.dto.PortalDto;
import org.streamconnect.dss.dto.RoleDto;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.tx.PortalTransaction;

/**
 * The Class PortalService.
 *
 */
@Service
public class PortalService implements IPortalService {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(PortalService.class);

    /** The portal transaction. */
    @Autowired
    private PortalTransaction portalTransaction;

    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     *            the portal id
     * @return portalDto
     */
    @Override
    public PortalDto getPortalDetails(final int portalId) {
        LOGGER.info("In PortalService: getPortalDetails() ---> start");
        PortalDto portalDto = null;
        try {
            portalDto = portalTransaction.getPortalDetails(portalId);
            LOGGER.info("In PortalService: getPortalDetails() ---> End");
        } catch (Exception e) {
            LOGGER.error(
                    "Exception in PortalService: getPortalDetails function:",
                    e);
        }
        return portalDto;
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
    @Override
    public DashboardDto getDashboardDetails(final int dashboardId,
                                            final int inRoleId) {
        LOGGER.info("In PortalService: getDashboardDetails() ---> start");
        DashboardDto dashboardDto = null;
        try {
            dashboardDto = portalTransaction.getDashboardDetails(dashboardId,
                    inRoleId);
            LOGGER.info("In PortalService: getDashboardDetails() ---> End");
        } catch (Exception e) {
            LOGGER.error(
                    "Exception in PortalService: getDashboardDetails function:",
                    e);
        }
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
    @Override
    public List<DashboardDto> getDashboardList(final int portalId,
                                               final int inRoleId) {
        LOGGER.info("In PortalService: getDashboardList() ---> start");
        List<DashboardDto> dashboardList = null;
        try {
            dashboardList = portalTransaction.getDashboardList(portalId,
                    inRoleId);
            LOGGER.info("In PortalService: getDashboardList() ---> End");
        } catch (Exception e) {
            LOGGER.error(
                    "Exception in PortalService: getDashboardList function:",
                    e);
        }
        return dashboardList;
    }

    /**
     * Method for listing all Portals.
     *
     * @param inRoleId
     *            the in role id
     * @return portalDtoList
     */
    @Override
    public List<PortalDto> getPortalList(final int inRoleId) {
        LOGGER.info("In PortalService: getPortalList() ---> start");
        List<PortalDto> portalDtoList = null;
        try {
            portalDtoList = portalTransaction.getPortalList(inRoleId);
            LOGGER.info("In PortalService: getPortalList() ---> End");
        } catch (Exception e) {
            LOGGER.error("Exception in PortalService: getPortalList function:",
                    e);
        }
        return portalDtoList;
    }

    /**
     * Method for listing each Category and all KPIs under each Category and all
     * visualizations under each KPI.
     *
     * @param dashboardId
     *            the dashboard id
     * @param inRoleId
     *            the in role id
     * @return categoryKpiVisualizeDtos
     */
    @Override
    public List<CategoryKpiVisualizeDto> getCategoryKpiVisualizeTreeForPortal(
            final int dashboardId, final int inRoleId) {
        LOGGER.info(
                "In PortalService: getCategoryKpiVisualizeTreeForPortal() ---> Start");
        List<CategoryKpiVisualizeDto> categoryKpiVisualizeList = null;
        try {
            categoryKpiVisualizeList = portalTransaction
                    .getCategoryKpiVisualizeTreeForPortal(dashboardId,
                            inRoleId);
            LOGGER.info(
                    "In PortalService: getCategoryKpiVisualizeTreeForPortal() ---> End");
        } catch (Exception e) {
            LOGGER.error(
                    "Exception in PortalService: getCategoryKpiVisualizeTreeForPortal function:",
                    e);
        }
        return categoryKpiVisualizeList;
    }

    /**
     * Method for getting the access levels.
     *
     * @param inRoleId
     *            the in role id
     * @return String
     */
    public RoleDto getRoleAccessDetails(final int inRoleId) {
        return portalTransaction.getRoleAccessDetails(inRoleId);
    }
}
