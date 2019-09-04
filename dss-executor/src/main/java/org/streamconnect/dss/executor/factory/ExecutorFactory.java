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
package org.streamconnect.dss.executor.factory;

import org.streamconnect.dss.dto.PipelineExecutionDto;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.executor.impl.LivyExecutorImpl;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * The type Executor factory.
 *
 * @author
 */
@Component
public class ExecutorFactory {

    /**
     * LivyExecutorImpl object.
     */
    @Autowired
    private LivyExecutorImpl objLivyExecutor;

    /**
    *Logger object for the application logging.
    */
   private static final DSSLogger LOGGER = DSSLogger.getLogger(ExecutorFactory
            .class);

	/**
	 * Method to execute Pipeline.
	 *
	 * @param pipelineExecutionDto the pipeline execution dto
	 * @return json object
	 */
	public Object executeDataPipeline(final PipelineExecutionDto
                                              pipelineExecutionDto) {
		LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "ExecutorFactory : executeDataPipeline function : "
				+ Constants.START_STATUS);

		int n = 0;
		switch (n) {
		    case 0 :
		        return objLivyExecutor.executePipeline(pipelineExecutionDto);
            default :
				break;
		}
        LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "ExecutorFactory : executeDataPipeline function : "
				+ Constants.END_STATUS);
		return null;

	}

	/**
	 * Method to get the Execution status.
	 *
	 * @param pipelineExecutionDto the pipeline execution dto
	 * @return json data pipeline status
	 */
	public Object getDataPipelineStatus(final PipelineExecutionDto
                                                pipelineExecutionDto) {
		LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                + "ExecutorFactory : getDataPipelineStatus function : "
				+ Constants.START_STATUS);

		int n = 0;
		switch (n) {
		case 0 :
			return objLivyExecutor.getPipelineExecStatus(pipelineExecutionDto);

		default :
			break;
		}
		LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
				+ "ExecutorFactory : getDataPipelineStatus function : "
				+ Constants.END_STATUS);
		return null;
	}

	/**
	 * Method to stop executing kafkastream-spark-cassandra Pipeline.
	 *
	 * @param pipelineExecutionDto the pipeline execution dto
	 * @return json object
	 */
	public Object stopDataPipeline(final PipelineExecutionDto
										   pipelineExecutionDto) {
		LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
				+ "ExecutorFactory : stopDataPipeline function : "
				+ Constants.START_STATUS);

		int n = 0;
		switch (n) {
			case 0 :
			    return objLivyExecutor.stopPipelineExec(pipelineExecutionDto);
			default :
				break;
		}
		LOGGER.info(LogMessageEnum.EXECUTOR_LAYER_INFO.getMessage()
                     + " ExecutorFactory : stopDataPipeline function : "
                     + Constants.END_STATUS);
		return null;
	}


}
