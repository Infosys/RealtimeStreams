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
package org.streamconnect.dss.api.ui.response;

import java.io.Serializable;
import java.util.List;

/**
 * The Class Visualize.
 *
 * @version 1.0
 */
public class Visualize implements Serializable {

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

    /** The str key space. */
    private String strKeySpace;

    /** The str visualize config details. */
    private Object strVisualizeConfigDetails;

    /** The in visualize entity id. */
    private int inVisualizeEntityId;

    /** The kpi list. */
    private List<Kpi> kpiList;

    /** The visualize view enabled. */
    private boolean visualizeViewEnabled;

    /** The in portal access id. */
    private int inPortalAccessId;

    /**
     * Gets the in visualize id.
     *
     * @return the in visualize id
     */
    public int getInVisualizeId() {
        return inVisualizeId;
    }

    /**
     * Sets the in visualize id.
     *
     * @param inVisualizeId
     *            the new in visualize id
     */
    public void setInVisualizeId(final int inVisualizeId) {
        this.inVisualizeId = inVisualizeId;
    }

    /**
     * Gets the str visualize name.
     *
     * @return the str visualize name
     */
    public String getStrVisualizeName() {
        return strVisualizeName;
    }

    /**
     * Sets the str visualize name.
     *
     * @param strVisualizeName
     *            the new str visualize name
     */
    public void setStrVisualizeName(final String strVisualizeName) {
        this.strVisualizeName = strVisualizeName;
    }

    /**
     * Gets the str visualize desc.
     *
     * @return the str visualize desc
     */
    public String getStrVisualizeDesc() {
        return strVisualizeDesc;
    }

    /**
     * Sets the str visualize desc.
     *
     * @param strVisualizeDesc
     *            the new str visualize desc
     */
    public void setStrVisualizeDesc(final String strVisualizeDesc) {
        this.strVisualizeDesc = strVisualizeDesc;
    }

    /**
     * Gets the str visualize parent type.
     *
     * @return the str visualize parent type
     */
    public String getStrVisualizeParentType() {
        return strVisualizeParentType;
    }

    /**
     * Sets the str visualize parent type.
     *
     * @param strVisualizeParentType
     *            the new str visualize parent type
     */
    public void setStrVisualizeParentType(final String strVisualizeParentType) {
        this.strVisualizeParentType = strVisualizeParentType;
    }

    /**
     * Gets the str visualize sub type.
     *
     * @return the str visualize sub type
     */
    public String getStrVisualizeSubType() {
        return strVisualizeSubType;
    }

    /**
     * Sets the str visualize sub type.
     *
     * @param strVisualizeSubType
     *            the new str visualize sub type
     */
    public void setStrVisualizeSubType(final String strVisualizeSubType) {
        this.strVisualizeSubType = strVisualizeSubType;
    }

    /**
     * Gets the str key space.
     *
     * @return the str key space
     */
    public String getStrKeySpace() {
        return strKeySpace;
    }

    /**
     * Sets the str key space.
     *
     * @param strKeySpace
     *            the new str key space
     */
    public void setStrKeySpace(final String strKeySpace) {
        this.strKeySpace = strKeySpace;
    }

    /**
     * Gets the str visualize config details.
     *
     * @return the str visualize config details
     */
    public Object getStrVisualizeConfigDetails() {
        return strVisualizeConfigDetails;
    }

    /**
     * Sets the str visualize config details.
     *
     * @param strVisualizeConfigDetails
     *            the new str visualize config details
     */
    public void setStrVisualizeConfigDetails(
            final Object strVisualizeConfigDetails) {
        this.strVisualizeConfigDetails = strVisualizeConfigDetails;
    }

    /**
     * Gets the in visualize entity id.
     *
     * @return the in visualize entity id
     */
    public int getInVisualizeEntityId() {
        return inVisualizeEntityId;
    }

    /**
     * Sets the in visualize entity id.
     *
     * @param inVisualizeEntityId
     *            the new in visualize entity id
     */
    public void setInVisualizeEntityId(final int inVisualizeEntityId) {
        this.inVisualizeEntityId = inVisualizeEntityId;
    }

    /**
     * Gets the kpi list.
     *
     * @return the kpi list
     */
    public List<Kpi> getKpiList() {
        return kpiList;
    }

    /**
     * Sets the kpi list.
     *
     * @param kpiList
     *            the new kpi list
     */
    public void setKpiList(final List<Kpi> kpiList) {
        this.kpiList = kpiList;
    }

    /**
     * Checks if is visualize view enabled.
     *
     * @return true, if is visualize view enabled
     */
    public boolean isVisualizeViewEnabled() {
        return visualizeViewEnabled;
    }

    /**
     * Sets the visualize view enabled.
     *
     * @param visualizeViewEnabled
     *            the new visualize view enabled
     */
    public void setVisualizeViewEnabled(final boolean visualizeViewEnabled) {
        this.visualizeViewEnabled = visualizeViewEnabled;
    }

    /**
     * Gets the in portal access id.
     *
     * @return the in portal access id
     */
    public int getInPortalAccessId() {
        return inPortalAccessId;
    }

    /**
     * Sets the in portal access id.
     *
     * @param inPortalAccessId
     *            the new in portal access id
     */
    public void setInPortalAccessId(final int inPortalAccessId) {
        this.inPortalAccessId = inPortalAccessId;
    }
}
