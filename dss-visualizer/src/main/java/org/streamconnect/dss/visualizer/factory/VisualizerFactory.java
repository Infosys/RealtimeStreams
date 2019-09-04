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
package org.streamconnect.dss.visualizer.factory;

import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;

/**
 * A factory for creating Visualizer objects.
 *
 * @version 1.0
 */
public class VisualizerFactory {

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(VisualizerFactory.class);

    /**
     * Gets the area chart dtls.
     *
     * @return the area chart dtls
     */
    public String getAreaChartDtls() {
        LOGGER.info(LogMessageEnum.VISUALIZE_LAYER_INFO.getMessage()
                + " VisualizerFactory : getAreaChartDtls function : "
                + Constants.START_STATUS);
        int n = 0;
        switch (n) {
            case 0:
            default:
                break;
        }
        LOGGER.info(LogMessageEnum.VISUALIZE_LAYER_INFO.getMessage()
                + " VisualizerFactory : getAreaChartDtls function : "
                + Constants.END_STATUS);
        return null;

    }

    /**
     * Gets the line graph dtls.
     *
     * @return the line graph dtls
     */
    public String getLineGraphDtls() {
        LOGGER.info(LogMessageEnum.VISUALIZE_LAYER_INFO.getMessage()
                + " VisualizerFactory : getLineGraphDtls function : "
                + Constants.START_STATUS);
        int n = 0;
        switch (n) {
            case 0:
            default:
                break;
        }
        LOGGER.info(LogMessageEnum.VISUALIZE_LAYER_INFO.getMessage()
                + " VisualizerFactory : getLineGraphDtls function : "
                + Constants.END_STATUS);
        return null;

    }

    /**
     * Gets the table panel dtls.
     *
     * @return the table panel dtls
     */
    public String getTablePanelDtls() {
        LOGGER.info(LogMessageEnum.VISUALIZE_LAYER_INFO.getMessage()
                + " VisualizerFactory : getTablePanelDtls function : "
                + Constants.START_STATUS);
        int n = 0;
        switch (n) {
            case 0:
            default:
                break;
        }
        LOGGER.info(LogMessageEnum.VISUALIZE_LAYER_INFO.getMessage()
                + " VisualizerFactory : getTablePanelDtls function : "
                + Constants.END_STATUS);
        return null;
    }

}
