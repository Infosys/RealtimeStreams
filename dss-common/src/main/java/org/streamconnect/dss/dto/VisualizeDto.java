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
package org.streamconnect.dss.dto;

import java.io.Serializable;
import java.util.List;


/**
 * The type Visualize dto.
 */
public class VisualizeDto implements Serializable {

    /** The in visualize id. */
    private int inVisualizeId;

    /** The str visualize name. */
    private String strVisualizeName;

    /** The str visualize desc. */
    private String strVisualizeDesc;

    /** The str visualize parent type. */
    private String strVisualizeParentType;

    /** The str visualize sub type. */
    private String strVisualizeSubType;

    /** The str visualize config details. */
    private Object strVisualizeConfigDetails;

    /** The str key space. */
    private String strKeySpace;

    /** The in visualize entity id. */
    private int inVisualizeEntityId;

    /** The in pipeline id. */
    private int inPipelineId;

    /** The kpi list. */
    private List<KpiDto> kpiList;

    /** The visualize view enabled. */
    private boolean visualizeViewEnabled;

    /** The in portal access id. */
    private int inPortalAccessId;


    /**
     * Gets in visualize id.
     *
     * @return the in visualize id
     */
    public int getInVisualizeId() {
        return inVisualizeId;
    }

    /**
     * Sets in visualize id.
     *
     * @param inVisualizeId the in visualize id
     */
    public void setInVisualizeId(final int inVisualizeId) {
        this.inVisualizeId = inVisualizeId;
    }

    /**
     * Gets str visualize name.
     *
     * @return the str visualize name
     */
    public String getStrVisualizeName() {
        return strVisualizeName;
    }

    /**
     * Sets str visualize name.
     *
     * @param strVisualizeName the str visualize name
     */
    public void setStrVisualizeName(final String strVisualizeName) {
        this.strVisualizeName = strVisualizeName;
    }

    /**
     * Gets str visualize desc.
     *
     * @return the str visualize desc
     */
    public String getStrVisualizeDesc() {
        return strVisualizeDesc;
    }

    /**
     * Sets str visualize desc.
     *
     * @param strVisualizeDesc the str visualize desc
     */
    public void setStrVisualizeDesc(final String strVisualizeDesc) {
        this.strVisualizeDesc = strVisualizeDesc;
    }

    /**
     * Gets str visualize parent type.
     *
     * @return the str visualize parent type
     */
    public String getStrVisualizeParentType() {
        return strVisualizeParentType;
    }

    /**
     * Sets str visualize parent type.
     *
     * @param strVisualizeParentType the str visualize parent type
     */
    public void setStrVisualizeParentType(final String strVisualizeParentType) {
        this.strVisualizeParentType = strVisualizeParentType;
    }

    /**
     * Gets str visualize sub type.
     *
     * @return the str visualize sub type
     */
    public String getStrVisualizeSubType() {
        return strVisualizeSubType;
    }

    /**
     * Sets str visualize sub type.
     *
     * @param strVisualizeSubType the str visualize sub type
     */
    public void setStrVisualizeSubType(final String strVisualizeSubType) {
        this.strVisualizeSubType = strVisualizeSubType;
    }

    /**
     * Gets str visualize config details.
     *
     * @return the str visualize config details
     */
    public Object getStrVisualizeConfigDetails() {
        return strVisualizeConfigDetails;
    }

    /**
     * Sets str visualize config details.
     *
     * @param strVisualizeConfigDetails the str visualize config details
     */
    public void setStrVisualizeConfigDetails(final Object strVisualizeConfigDetails) {
        this.strVisualizeConfigDetails = strVisualizeConfigDetails;
    }

    /**
     * Gets str key space.
     *
     * @return the str key space
     */
    public String getStrKeySpace() {
        return strKeySpace;
    }

    /**
     * Sets str key space.
     *
     * @param strKeySpace the str key space
     */
    public void setStrKeySpace(final String strKeySpace) {
        this.strKeySpace = strKeySpace;
    }

    /**
     * Gets in visualize entity id.
     *
     * @return the in visualize entity id
     */
    public int getInVisualizeEntityId() {
        return inVisualizeEntityId;
    }

    /**
     * Sets in visualize entity id.
     *
     * @param inVisualizeEntityId the in visualize entity id
     */
    public void setInVisualizeEntityId(final int inVisualizeEntityId) {
        this.inVisualizeEntityId = inVisualizeEntityId;
    }

    /**
     * Gets in pipeline id.
     *
     * @return the in pipeline id
     */
    public int getInPipelineId() {
        return inPipelineId;
    }

    /**
     * Sets in pipeline id.
     *
     * @param inPipelineId the in pipeline id
     */
    public void setInPipelineId(final int inPipelineId) {
        this.inPipelineId = inPipelineId;
    }

    /**
     * Gets kpi list.
     *
     * @return the kpi list
     */
    public List<KpiDto> getKpiList() {
        return kpiList;
    }

    /**
     * Sets kpi list.
     *
     * @param kpiList the kpi list
     */
    public void setKpiList(final List<KpiDto> kpiList) {
        this.kpiList = kpiList;
    }

    /**
     * Is visualize view enabled boolean.
     *
     * @return the boolean
     */
    public boolean isVisualizeViewEnabled() {
        return visualizeViewEnabled;
    }

    /**
     * Sets visualize view enabled.
     *
     * @param visualizeViewEnabled the visualize view enabled
     */
    public void setVisualizeViewEnabled(final boolean visualizeViewEnabled) {
        this.visualizeViewEnabled = visualizeViewEnabled;
    }

    /**
     * Gets in portal access id.
     *
     * @return the in portal access id
     */
    public int getInPortalAccessId() {
        return inPortalAccessId;
    }

    /**
     * Sets in portal access id.
     *
     * @param inPortalAccessId the in portal access id
     */
    public void setInPortalAccessId(final int inPortalAccessId) {
        this.inPortalAccessId = inPortalAccessId;
    }
}


