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
 * The Class VisualizeCheck.
 *
 * @version 1.0
 */
public class VisualizeCheck implements Serializable {

    /** The in pipeline id. */
    private int inPipelineId;

    /** The str visualize name. */
    private String strVisualizeName;

    /** The in visualize entity id. */
    private int inVisualizeEntityId;

    /** The kpi list. */
    private List<Kpi> kpiList;

    /**
     * Gets the in pipeline id.
     *
     * @return the inPipelineId
     */
    public int getInPipelineId() {
        return inPipelineId;
    }

    /**
     * Sets the in pipeline id.
     *
     * @param inPipelineId
     *            the inPipelineId to set
     */
    public void setInPipelineId(final int inPipelineId) {
        this.inPipelineId = inPipelineId;
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
}

