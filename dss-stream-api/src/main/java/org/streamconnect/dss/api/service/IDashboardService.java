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

package org.streamconnect.dss.api.service;

import org.streamconnect.dss.dto.*;

import java.util.List;


/**
 * The Interface IDashboardService.
 *
 */
public interface IDashboardService {
    /**
     * Method for getting source list.
     *
     * @return listSourceDto
     */
    List<IdNameDto> getSourceList();
    /**
     * Method for getting sink list.
     *
     * @return sinkDtoList
     */
    List<IdNameDto> getSinkList();
    /**
     * Method for getting process list.
     *
     * @return listProcessDto
     */
    List<IdNameDto> getProcessList();
    /**
     * Method for getting category list.
     *
     * @return catList
     */
    List<IdNameDto> getCategoryList();
    /**
     * Method for getting pipeline list.
     *
     * @return pipelineDtoList
     */
    List<IdNameDto> getPipelineList();
    /**
     * Method for getting Kpi list.
     *
     * @return listKpiDto
     */
    List<IdNameDto> getKpiList();
    /**
     * Method for listing each category and all kpis under each category.
     *
     * @return categoryKpisDtoList
     */
    List<CategoryKpisDto> getCategoryWithKpis();

    /**
     * Method for getting a source details by passing source id.
     *
     * @param inSourceId the in source id
     * @return the source data
     */
    SourceDto getSourceData(int inSourceId);

    /**
     * Method for getting sink details by passing sink id.
     *
     * @param inSinkId the in sink id
     * @return sinkDto
     */
    SinkDto getSinkData(int inSinkId);

    /**
     * Method for getting process details by passing process id.
     *
     * @param inProcessId the in process id
     * @return processDto
     */
    ProcessDto getProcessData(int inProcessId);

    /**
     * Method for getting category details by passing category id.
     *
     * @param inCategoryId the in category id
     * @return categoryDto
     */
    CategoryDto getCategoryData(int inCategoryId);

    /**
     * Method for getting pipeline details by passing pipeline id.
     *
     * @param pipelineId the pipeline id
     * @return pipelineDto
     */
    PipelineDto getPipelineData(int pipelineId);

    /**
     * Method for getting kpi details by passing kpi id.
     *
     * @param inKpiId the in kpi id
     * @return kpiDto
     */
    KpiDto getKpiData(int inKpiId);

    /**
     * Method for saving source details.
     *
     * @param sourceDto the source dto
     * @return true, if successful
     */
    boolean saveSource(SourceDto sourceDto);
    /**
     * Check source exist or not.
     *
     * @param inSourceId the in source id
     * @param sourceName the source name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkSourceExistOrNot(int inSourceId, String sourceName,
                                         String userName);
    /**
     * Method for saving sink details.
     *
     * @param sinkDto the sink dto
     * @return boolean
     */
    boolean saveSink(SinkDto sinkDto);
    /**
     * Check sink exist or not.
     *
     * @param inSinkId the in sink id
     * @param sinkName the sink name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkSinkExistOrNot(int inSinkId, String sinkName,
                                       String userName);
    /**
     * Method for saving process details.
     *
     * @param processDto the process dto
     * @return boolean
     */
    boolean saveProcess(ProcessDto processDto);
    /**
     * Check process exist or not.
     *
     * @param inProcessId the in process id
     * @param processName the process name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkProcessExistOrNot(int inProcessId, String processName,
                                          String userName);
    /**
     * Method for saving category details.
     *
     * @param categoryDto the category dto
     * @return boolean
     */
    boolean saveCategory(CategoryDto categoryDto);
    /**
     * Check category exist or not.
     *
     * @param inCategoryId the in category id
     * @param categoryName the category name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkCategoryExistOrNot(int inCategoryId, String categoryName,
                                           String userName);
    /**
     * Method for saving kpi details.
     *
     * @param kpiDto the kpi dto
     * @return false
     */
    boolean saveKpi(KpiDto kpiDto);
    /**
     * Check kpi exist or not.
     *
     * @param inKpiId  the in kpi id
     * @param kpiName the kpi name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkKpiExistOrNot(int inKpiId, String kpiName, String userName);

    /**
     * Method for save pipeline details.
     *
     * @param pipelineDto the pipeline dto
     * @param visualizeDtos the visualize dtos
     * @param deletedVisualizations the deleted visualizations
     * @return boolean
     */
    boolean savePipeline(PipelineDto pipelineDto, List<VisualizeDto>
            visualizeDtos, List<Integer> deletedVisualizations);

    /**
     * Check pipeline exist or not.
     *
     * @param inPipelineId the in pipeline id
     * @param pipelineName the pipeline name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkPipelineExistOrNot(int inPipelineId, String pipelineName,
                                           String userName);

    /**
     * Check visualization nameexist or not.
     *
     * @param visualizeCheck the visualize check
     * @return true, if successful
     */
    boolean checkVisualizationExistOrNot(final VisualizeCheckDto
                                                       visualizeCheck);

    /**
     * Method for deleting source by passing source id.
     *
     * @param inSourceId the in source id
     * @return boolean
     */
    boolean deleteSource(int inSourceId);

    /**
     * Method for deleting sink by passing sink id.
     *
     * @param inSinkId the in sink id
     * @return boolean
     */
    boolean deleteSink(int inSinkId);

    /**
     * Method for deleting process by passing process id.
     *
     * @param inProcessId the in process id
     * @return boolean
     */
    boolean deleteProcess(int inProcessId);

    /**
     * Method for deleting category by passing category id.
     *
     * @param inCategoryId the in category id
     * @return boolean
     */
    boolean deleteCategory(int inCategoryId);

    /**
     * Method for deleting kpi by passing kpi id.
     *
     * @param inKpiId the in kpi id
     * @return boolean
     */
    boolean deleteKpi(int inKpiId);

    /**
     * Method for delete pipeline by passing pipeline id.
     *
     * @param inPipelineId the in pipeline id
     * @return boolean
     */
    boolean deletePipeline(int inPipelineId);
    /**
     * Method for listing all pipelines with category and kpi details.
     *
     * @return pipelineDtos
     */
    List<PipelineDto> getPipelines();
    /**
     * Method for listing all visualizations with category and kpi details.
     *
     * @return categoryList
     */
    List<CategoryDto> getVisualizations();

    /**
     * Method for saving pipline status.
     *
     * @param pplId the ppl id
     * @param execPplId the exec ppl id
     * @param pplExecStatus the ppl exec status
     * @return boolean
     */
    boolean savePipelineStatus(int pplId, String execPplId, String
            pplExecStatus);

    /**
     * Method for getting Pipeline execution URL.
     *
     * @param pipelineId the pipeline id
     * @return String
     */
    String getPipelineExecutionUrl(int pipelineId);

    /**
     * Method for getting pipeline execution data.
     *
     * @param pipelineId the pipeline id
     * @return PipelineExecutionDto
     */
    PipelineExecutionDto getPipelineExecutionData(int pipelineId);

    /**
     * Method for getting pipeline execution id.
     *
     * @param pipelineId the pipeline id
     * @return String
     */
    String getPipelineExecutionId(int pipelineId);

    /**
     * Method for getting max value of inVisualizeEntityId for a particular
     * pipeline.
     *
     * @param pipelineId the pipeline id
     * @return int
     */
    int getMaxVisualizationCount(int pipelineId);

    /**
     *  Method to get sink datatypes.
     *
     * @param strSinkType the str sink type
     * @return String
     */
    String getSinkDatatypes(String strSinkType);

    /**
     * Method for getting the list of metadata dto of uploaded source schema.
     *
     * @param inputString the input string
     * @param strFileType the str file type
     * @param strDelimiter the str delimiter
     * @param isHeaderExists the is header exists
     * @return List
     */
    List<ColumnMetadataDto> getColumnMetadataList(String inputString, String
            strFileType, String strDelimiter, boolean isHeaderExists);

    /**
     * Method for save or update lookup basic configuration.
     *
     * param lookupDetailsDto
     *
     * @param lookupDetailsDto the lookup details dto
     * @return ResponseDto
     */
    ResponseDto saveOrUpdateLookupBasicDetails(LookupDetailsDto
                                                   lookupDetailsDto);
    /**
     * Check lookup exist or not.
     *
     * @param inLookupId the in lookup id
     * @param lookupConfigName the lookup config name
     * @param userName the user name
     * @return true, if successful
     */
    boolean checkLookupExistOrNot(int inLookupId, String lookupConfigName,
                                  String userName);

    /**
     * Method for save or update lookup Advanced configurations.
     *
     * @param lookupAdvancedDetailsDto the lookup advanced details dto
     * @return boolean
     */
    boolean saveOrUpdateLookupAdvancedDetails(LookupAdvancedDetailsDto
                                                      lookupAdvancedDetailsDto);

    /**
     * Method for getting lookup basic and advancedDetails.
     *
     * @param inLookupId the in lookup id
     * @return lookupDetailsDto
     */
    LookupDetailsDto getLookupDetails(int inLookupId);

    /**
     * Method for deleting Lookup by passing lookup id.
     *
     * @param inLookupId the in lookup id
     * @return boolean
     */
    boolean deleteLookup(int inLookupId);

    /**
     * Method for deleting LookupDetails by passing lookupDetails Id.
     *
     * @param lookupDetailsId the lookup details id
     * @return boolean
     */
    boolean deleteLookupDetails(int lookupDetailsId);

    /**
     * Method for listing all lookups.
     *
     * @return List
     */
    List<LookupDetailsDto> getAllLookups();

    /**
     * Method for testing redis connection status.
     *
     * @param redisHost the redis host
     * @param redisPort the redis port
     * @return boolean
     */
    boolean testRedisConnection(String redisHost, int redisPort);

    /**
     * Method for getting the Query Plan Status.
     *
     * @param queryPlanDto the query plan dto
     * @return QueryPlanResultDto
     */
    QueryPlanResultDto getQueryPlanStatus(QueryPlanDto queryPlanDto);


    /**
     * Method for validating the Sink Table Name  based on sinktype.
     *
     * @param sinkValidationDto the sink validation dto
     * @return boolean
     */
    boolean isValidSinkTable(SinkValidationDto sinkValidationDto);


    /**
     * Method for geting Cassandra Table and Uploaded CSV file Schema details,
     * Table data and CSV data for preview  by passing lookupDetails Id and
     * data required status.
     *
     * @param lookupDetailsId
     * @param isDataRequired
     * @return PreviewDataDto
     */
    PreviewDataDto getSchemaAndContent(int lookupDetailsId, boolean isDataRequired);

    /**
     * Method for getting Lookup configurations ( id, name and type).
     *
     * @return List
     */
    List<IdNameDto> getLookupConfigurations();

}
