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
package org.streamconnect.dss.visualizer.impl;

import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.streamconnect.dss.visualizer.IAreaChartVisualizer;
import org.springframework.stereotype.Service;

/**
 * The Class AreaChartVisualizerImpl.
 *
 * @version 1.0
 */
@Service
public class AreaChartVisualizerImpl implements IAreaChartVisualizer {

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(AreaChartVisualizerImpl.class);

    /**
     * Method for getting area chart.
     *
     * @param objVisualizerDtls
     * @return String
     */
    public String getAreaChart(final Object objVisualizerDtls) {
        LOGGER.info(LogMessageEnum.VISUALIZE_LAYER_INFO.getMessage()
                + " AreaChartVisualizerImpl : getAreaChart function : "
                + Constants.START_STATUS);
        String strRet = "";
        try {
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(
                    ErrorMessageEnum.VISUALIZE_LAYER_EXCEPTION.getCode()
                            .toString()
                            + " : "
                            + ErrorMessageEnum.VISUALIZE_LAYER_EXCEPTION
                            .getMessage()
                            + " AreaChartVisualizerImpl : in getAreaChart function : ",
                    e);
        }
        LOGGER.info(LogMessageEnum.VISUALIZE_LAYER_INFO.getMessage()
                + " AreaChartVisualizerImpl : getAreaChart function : "
                + Constants.END_STATUS);
        return strRet;
    }

}
