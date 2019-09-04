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
package org.streamconnect.dss.api.service;

import java.util.List;

import org.streamconnect.dss.dto.CategoryKpiVisualizeDto;
import org.streamconnect.dss.dto.DashboardDto;
import org.streamconnect.dss.dto.PortalDto;
import org.streamconnect.dss.dto.RoleDto;

/**
 * The Interface IPortalService.
 *
 */
public interface IPortalService {

    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     *            the portal id
     * @return portalDto
     */
    PortalDto getPortalDetails(int portalId);

    /**
     * Method for getting Dashboard details.
     *
     * @param dashboardId
     *            the dashboard id
     * @param inRoleId
     *            the in role id
     * @return dashboardDto
     */
    DashboardDto getDashboardDetails(int dashboardId, int inRoleId);

    /**
     * Method for listing all dashboards.
     *
     * @param portalId
     *            the portal id
     * @param inRoleId
     *            the in role id
     * @return dashboardList
     */
    List<DashboardDto> getDashboardList(int portalId, int inRoleId);

    /**
     * Method for listing all Portals.
     *
     * @param inRoleId
     *            the in role id
     * @return portalDtoList
     */
    List<PortalDto> getPortalList(int inRoleId);

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
    List<CategoryKpiVisualizeDto> getCategoryKpiVisualizeTreeForPortal(
            int dashboardId, int inRoleId);

    /**
     * Method for getting the access levels.
     *
     * @param inRoleId
     *            the in role id
     * @return String
     */
    RoleDto getRoleAccessDetails(int inRoleId);
}
