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
package org.streamconnect.dss.engine;

import org.streamconnect.dss.dto.PipelineConfigDto;
import org.streamconnect.dss.dto.PipelineDetails;
import org.streamconnect.dss.engine.prefab.PipelineConstruct;
import org.streamconnect.dss.engine.prefab.StreamFlowInterpreter;
import org.streamconnect.dss.enums.ErrorMessageEnum;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.exception.PipelineConstructException;
import org.streamconnect.dss.exception.StreamEngineException;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.streamconnect.dss.engine.interpreter.*;


/**
 * This is factory class for  various processing interpretors.
 *
 * @version 1.0
 */

@Component
public class InterpreterFactory {

    /** The logger. */
    private static final DSSLogger LOGGER = DSSLogger
            .getLogger(InterpreterFactory.class);

    /** The obj kafka spark EL json interpreter. */
    @Autowired
    private KafkaSparkELJsonInterpreter objKafkaSparkELJsonInterpreter;

    /** The obj flume spark EL unstruct interpreter. */
    @Autowired
    private FlumeSparkELUnstructInterpreter objFlumeSparkELUnstructInterpreter;

    /** The obj kafka spark EL csv interpreter. */
    @Autowired
    private KafkaSparkELCsvInterpreter objKafkaSparkELCsvInterpreter;

    /** The obj flume spark EL csv interpreter. */
    @Autowired
    private FlumeSparkELCsvInterpreter objFlumeSparkELCsvInterpreter;

    /** The obj flume spark EL json interpreter. */
    @Autowired
    private FlumeSparkELJsonInterpreter objFlumeSparkELJsonInterpreter;

    /** The obj flume spark EL unstructured interpreter. */
    @Autowired
    private KafkaSparkELUnstructInterpreter objKafkaSparkELUnstructInterpreter;

    public InterpreterFactory() {
    }

    // TODO change the method name with respect to the factory

    /**
     * Method to create data pipeline execuatble for respective implemenattion
     * based on user selection and returns its details.
     *
     * @param objPipelineDtls the obj pipeline dtls
     * @return the data pipeline
     */
    public PipelineDetails getDataPipeline(final PipelineConfigDto
                                                   objPipelineDtls) {
        LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                + "InterpreterFactory : getDataPipeline function : "
                + Constants.START_STATUS);
        try {
            PipelineConstruct pipelineConstruct = new PipelineConstruct();
            StreamFlowInterpreter objInterpreter = null;

            if ((objPipelineDtls.getStrSourceType().equalsIgnoreCase("kafka"))
                    && (objPipelineDtls.getStrProcessType()
                    .equalsIgnoreCase("spark"))
                    && (objPipelineDtls.getStrSinkType()
                    .equalsIgnoreCase("cassandra"))
                    && (objPipelineDtls.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("json"))) {
                objInterpreter = objKafkaSparkELJsonInterpreter;
            } else if ((objPipelineDtls.getStrSourceType()
                    .equalsIgnoreCase("kafka"))
                    && (objPipelineDtls.getStrProcessType()
                    .equalsIgnoreCase("spark"))
                    && (objPipelineDtls.getStrSinkType()
                    .equalsIgnoreCase("elassandra"))
                    && (objPipelineDtls.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("json"))) {
                objInterpreter = objKafkaSparkELJsonInterpreter;
            } else if ((objPipelineDtls.getStrSourceType()
                    .equalsIgnoreCase("flume"))
                    && (objPipelineDtls.getStrProcessType()
                    .equalsIgnoreCase("spark"))
                    && (objPipelineDtls.getStrSinkType()
                    .equalsIgnoreCase("elassandra"))
                    && (objPipelineDtls.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("unstructured"))) {
                objInterpreter = objFlumeSparkELUnstructInterpreter;
            } else if ((objPipelineDtls.getStrSourceType()
                    .equalsIgnoreCase("kafka"))
                    && (objPipelineDtls.getStrProcessType()
                    .equalsIgnoreCase("spark"))
                    && (objPipelineDtls.getStrSinkType()
                    .equalsIgnoreCase("elassandra"))
                    && (objPipelineDtls.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("delimited"))) {
                objInterpreter = objKafkaSparkELCsvInterpreter;
            } else if ((objPipelineDtls.getStrSourceType()
                    .equalsIgnoreCase("flume"))
                    && (objPipelineDtls.getStrProcessType()
                    .equalsIgnoreCase("spark"))
                    && (objPipelineDtls.getStrSinkType()
                    .equalsIgnoreCase("elassandra"))
                    && (objPipelineDtls.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("delimited"))) {
                objInterpreter = objFlumeSparkELCsvInterpreter;
            } else if ((objPipelineDtls.getStrSourceType()
                    .equalsIgnoreCase("flume"))
                    && (objPipelineDtls.getStrProcessType()
                    .equalsIgnoreCase("spark"))
                    && (objPipelineDtls.getStrSinkType()
                    .equalsIgnoreCase("elassandra"))
                    && (objPipelineDtls.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("json"))) {
                objInterpreter = objFlumeSparkELJsonInterpreter;
            }else if((objPipelineDtls.getStrSourceType()
                    .equalsIgnoreCase("kafka"))
                    && (objPipelineDtls.getStrProcessType()
                    .equalsIgnoreCase("spark"))
                    && (objPipelineDtls.getStrSinkType()
                    .equalsIgnoreCase("elassandra"))
                    && (objPipelineDtls.getStrDataSchema().getStrSchemaType()
                    .equalsIgnoreCase("unstructured"))) {
                objInterpreter = objKafkaSparkELUnstructInterpreter;
            } else {
                return null;
            }
            LOGGER.debug(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                    + "InterpreterFactory : getDataPipeline function : "
                    + "Interpreter = " + objInterpreter);
            LOGGER.info(LogMessageEnum.ENGINE_LAYER_INFO.getMessage() + " "
                    + "InterpreterFactory : getDataPipeline function : "
                    + Constants.END_STATUS);
            return pipelineConstruct.construct(objInterpreter, objPipelineDtls);
        } catch (PipelineConstructException e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "InterpreterFactory: in getDataPipeline function : ",
                    e);
            throw new StreamEngineException(e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode().toString()
                            + " : "
                            + ErrorMessageEnum.ENGINE_LAYER_EXCEPTION
                            .getMessage()
                            + "  "
                            + "InterpreterFactory: in getDataPipeline function : ",
                    e);
            throw new StreamEngineException(
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getMessage() + " : "
                            + e.getMessage(),
                    ErrorMessageEnum.ENGINE_LAYER_EXCEPTION.getCode()
                            .toString());
        }
    }
}
