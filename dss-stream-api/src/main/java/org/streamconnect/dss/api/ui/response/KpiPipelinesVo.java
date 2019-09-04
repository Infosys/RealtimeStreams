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

import org.streamconnect.dss.dto.IdNameDto;

/**
 * The Class KpiPipelinesVo.
 *
 * @version 1.0
 */
public class KpiPipelinesVo implements Serializable {

    /** The in kpi id. */
    private int inKpiId;

    /** The str kpi name. */
    private String strKpiName;

    /** The pipelines. */
    private List<IdNameDto> pipelines;

    /**
     * Gets the in kpi id.
     *
     * @return the in kpi id
     */
    public int getInKpiId() {
        return inKpiId;
    }

    /**
     * Sets the in kpi id.
     *
     * @param inKpiId
     *            the new in kpi id
     */
    public void setInKpiId(final int inKpiId) {
        this.inKpiId = inKpiId;
    }

    /**
     * Gets the str kpi name.
     *
     * @return the str kpi name
     */
    public String getStrKpiName() {
        return strKpiName;
    }

    /**
     * Sets the str kpi name.
     *
     * @param strKpiName
     *            the new str kpi name
     */
    public void setStrKpiName(final String strKpiName) {
        this.strKpiName = strKpiName;
    }

    /**
     * Gets the pipelines.
     *
     * @return the pipelines
     */
    public List<IdNameDto> getPipelines() {
        return pipelines;
    }

    /**
     * Sets the pipelines.
     *
     * @param pipelines
     *            the new pipelines
     */
    public void setPipelines(final List<IdNameDto> pipelines) {
        this.pipelines = pipelines;
    }
}
