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

import org.streamconnect.dss.dto.CategoryKpiVisualizeDto;
import org.streamconnect.dss.dto.DashboardDto;
import org.streamconnect.dss.dto.PortalDto;

import java.util.List;

/**
 * The Interface IVisualizeManagerService.
 *
 * @version 1.0
 */
public interface IVisualizeManagerService {

    /**
     * Method for save or update Portal Details.
     *
     * @param portalDto
     *            the portal dto
     * @return boolean
     */
    boolean saveOrUpdatePortal(PortalDto portalDto);

    /**
     * Check portal exist or not.
     *
     * @param portalId the portal id
     * @param portalName the portal name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkPortalExistOrNot(int portalId, String portalName,
                                         String userName);

    /**
     * Method for save/update dashboard details.
     *
     * @param dashboardDto
     *            the dashboard dto
     * @return boolean
     */
    boolean saveOrUpdateDashboard(DashboardDto dashboardDto);

    /**
     * Check dashboard exist or not.
     *
     * @param portalId the portal id
     * @param dashboardId the dashboard id
     * @param dashboardName the dashboard name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkDashboardExistOrNot(int portalId, int dashboardId,
                                            String dashboardName, String
                                                    userName);
    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     *            the portal id
     * @return portalDto
     */
    PortalDto getPortalDetails(Integer portalId);

    /**
     * Method for getting Dashboard details.
     *
     * @param dashboardId
     *            the dashboard id
     * @return dashboardDto
     */
    DashboardDto getDashboardDetails(Integer dashboardId);

    /**
     * Method for listing all dashboards.
     *
     * @param portalId
     *            the portal id
     * @return dashboardList
     */
    List<DashboardDto> getDashboardList(Integer portalId);

    /**
     * Method for listing all Portals.
     *
     * @return portalDtoList
     */
    List<PortalDto> getPortalList();

    /**
     * Method for listing each Category and all KPIs under each Category and
     * all visualizations under each KPI.
     *
     * @return categoryKpiVisualizeDtos
     */
    List<CategoryKpiVisualizeDto> getCategoryKpiVisualizeList();

    /**
     * Method for delete portal.
     *
     * @param portalId
     *            the portal id
     * @return boolean
     */
    boolean deletePortal(Integer portalId);

    /**
     * Method for delete Dashboard.
     *
     * @param dashboardId
     *            the dashboard id
     * @return boolean
     */
    boolean deleteDashboard(Integer dashboardId);
}
