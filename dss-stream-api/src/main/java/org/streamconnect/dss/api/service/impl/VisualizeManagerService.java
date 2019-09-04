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

import org.streamconnect.dss.api.service.IVisualizeManagerService;
import org.streamconnect.dss.dto.CategoryKpiVisualizeDto;
import org.streamconnect.dss.dto.DashboardDto;
import org.streamconnect.dss.dto.PortalDto;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.exception.VisualizeMgrServiceException;
import org.streamconnect.dss.exception.VisualizeMgrTxnException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.tx.VisualizeManagerTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class VisualizeManagerService.
 *
 * @version 1.0
 */
@Service
public class VisualizeManagerService implements IVisualizeManagerService {

    /** The Constant LOGGER. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(VisualizeManagerService.class);

    /** The visualize manager transaction. */
    @Autowired
    private VisualizeManagerTransaction visualizeManagerTransaction;

    /**
     * Method for save or update Portal Details.
     *
     * @param portalDto
     *            the portal dto
     * @return boolean
     */
    public boolean saveOrUpdatePortal(final PortalDto portalDto) {
        LOGGER.info(
                "In VisualizeManagerService: saveOrUpdatePortal() ---> Start");
        try {
            return visualizeManagerTransaction.saveOrUpdatePortal(portalDto);
        }  catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.SERVICE_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.SERVICE_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerService : saveOrUpdatePortal function : ",
                    e);
            throw new VisualizeMgrTxnException(e.getMessage(),e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.SERVICE_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.SERVICE_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerService : saveOrUpdatePortal function : ",
                    e);
            throw new VisualizeMgrServiceException(
                    ErrorMessageEnum.SERVICE_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.SERVICE_LAYER_EXCEPTION.getCode()
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
    public boolean checkPortalExistOrNot(int portalId, String portalName,
                                         String userName) {
        return visualizeManagerTransaction.checkPortalExistOrNot(portalId, portalName,
                userName);
    }

    /**
     * Method for save/update dashboard details.
     *
     * @param dashboardDto
     *            the dashboard dto
     * @return boolean
     */
    public boolean saveOrUpdateDashboard(final DashboardDto dashboardDto) {
        LOGGER.info(
                "In VisualizeManagerService: saveOrUpdateDashboard() ---> Start");
        try {
            return visualizeManagerTransaction
                    .saveOrUpdateDashboard(dashboardDto);
        }  catch (VisualizeMgrTxnException e) {
            LOGGER.error(
                    ErrorMessageEnum.SERVICE_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.SERVICE_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerService : saveOrUpdateDashboard function : ",
                    e);
            throw new VisualizeMgrTxnException(e.getMessage(),e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.SERVICE_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.SERVICE_LAYER_EXCEPTION
                            .getMessage()
                            + " in VisualizeManagerService : saveOrUpdateDashboard function : ",
                    e);
            throw new VisualizeMgrServiceException(
                    ErrorMessageEnum.SERVICE_LAYER_EXCEPTION.getMessage()
                            + " : " + e.getMessage(),
                    ErrorMessageEnum.SERVICE_LAYER_EXCEPTION.getCode()
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
    public boolean checkDashboardExistOrNot(int portalId, int dashboardId,
                                            String dashboardName, String
                                                    userName) {
        return visualizeManagerTransaction.checkDashboardExistOrNot(portalId,
                dashboardId, dashboardName, userName);
    }
    /**
     * Method for getting Portal Details.
     *
     * @param portalId
     *            the portal id
     * @return portalDto
     */
    public PortalDto getPortalDetails(final Integer portalId) {
            return visualizeManagerTransaction.getPortalDetails(portalId);
    }

    /**
     * Method for getting Dashboard details.
     *
     * @param dashboardId
     *            the dashboard id
     * @return dashboardDto
     */
    public DashboardDto getDashboardDetails(final Integer dashboardId) {
        return visualizeManagerTransaction
                .getDashboardDetails(dashboardId);
    }

    /**
     * Method for listing all dashboards.
     *
     * @param portalId
     *            the portal id
     * @return dashboardList
     */
    public List<DashboardDto> getDashboardList(final Integer portalId) {
        return visualizeManagerTransaction
                .getDashboardList(portalId);
    }

    /**
     * Method for listing all Portals.
     *
     * @return portalDtoList
     */
    public List<PortalDto> getPortalList() {
        return visualizeManagerTransaction.getPortalList();
    }

    /**
     * Method for listing each Category and all KPIs under each Category and all
     * visualizations under each KPI.
     *
     * @return categoryKpiVisualizeDtos
     */
    public List<CategoryKpiVisualizeDto> getCategoryKpiVisualizeList() {
        return visualizeManagerTransaction
                .getCategoryKpiVisualizeList();
    }

    /**
     * Method for delete portal.
     *
     * @param portalId
     *            the portal id
     * @return boolean
     */
    public boolean deletePortal(final Integer portalId) {
        LOGGER.info("In VisualizeManagerService: deletePortal() ---> Start");
        return visualizeManagerTransaction.deletePortal(portalId);
    }

    /**
     * Method for delete Dashboard.
     *
     * @param dashboardId
     *            the dashboard id
     * @return boolean
     */
    public boolean deleteDashboard(final Integer dashboardId) {
        LOGGER.info("In VisualizeManagerService: deleteDashboard() ---> Start");
        return visualizeManagerTransaction.deleteDashboard(dashboardId);
    }
}
