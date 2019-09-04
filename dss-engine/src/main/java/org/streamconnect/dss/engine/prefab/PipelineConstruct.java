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
package org.streamconnect.dss.engine.prefab;

import org.springframework.stereotype.Component;

import org.streamconnect.dss.dto.PipelineConfigDto;
import org.streamconnect.dss.dto.PipelineDetails;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.InterpreterException;
import org.streamconnect.dss.exception.PipelineConstructException;
import org.streamconnect.dss.exception.StreamEngineException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;

/**
 * Constructor enforces the pipeline building in the order
 */

@Component
public class PipelineConstruct {

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(PipelineConstruct.class);

    /**
     * Construct.
     *
     * @param hb
     *            the hb
     * @param objPipelineConf
     *            the obj pipeline conf
     * @return the pipeline details
     */
    // force the order of building process
    public PipelineDetails construct(final StreamFlowInterpreter hb,
                                     final PipelineConfigDto objPipelineConf) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "PipelineConstruct : construct function : "
                + Constants.START_STATUS);
        try {
            if ((hb.getPrerequisite(objPipelineConf)) && (hb.buildSource())
                    && (hb.buildEngine()) && (hb.buildCreateAuditTableLogic()) && hb.buildCreateELIndexLogic()
                    &&(hb.buildSaveRawDataLogic())
                    && (hb.buildProcess()) && (hb.buildErrorInfoFetchLogic())
                    && (hb.buildSink())) {
                LOGGER.info(Constants.PIPELINE_CONSTRUCTION_STATUS);
                return hb.getDataPipeline();
            }
        } catch (InterpreterException e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "PipelineConstruct: in construct function : ",
                    e);
            throw new PipelineConstructException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "PipelineConstruct: in construct function : ",
                    e);
            throw new StreamEngineException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "PipelineConstruct : construct function : "
                + Constants.END_STATUS);
        return null;
    }
}

