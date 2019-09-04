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

package org.streamconnect.dss.api.service.impl;

import org.streamconnect.dss.api.service.IDashboardService;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.metadata.connection.tx.DashboardTransaction;
import org.streamconnect.dss.metadata.connection.tx.PortalTransaction;
import org.streamconnect.dss.util.MetadataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.streamconnect.dss.dto.*;

import java.util.List;

/**
 * The Class DashboardService.
 *
 */
@Service
public class DashboardService implements IDashboardService {

	/** The logger. */
	private static final DSSLogger LOGGER = DSSLogger
			.getLogger(DashboardService.class);

	/** The dashboard transaction. */
	@Autowired
	private DashboardTransaction dashboardTransaction;

	/** The portal transaction. */
	@Autowired
	private PortalTransaction portalTransaction;

	/** The metadata util. */
	@Autowired
	private MetadataUtil metadataUtil;

	/**
	 * Method for getting a source details by passing source id.
	 *
	 * @param inSourceId
	 *            the in source id
	 * @return the source data
	 */
	public SourceDto getSourceData(final int inSourceId) {

		return dashboardTransaction.getSourceData(inSourceId);
	}

	/**
	 * Method for getting source list.
	 *
	 * @return listSourceDto
	 */
	public List<IdNameDto> getSourceList() {

		return dashboardTransaction.getSourceList();
	}

	/**
	 * Method for saving source details.
	 *
	 * @param sourceDto
	 *            the source dto
	 * @return true, if successful
	 */
	public boolean saveSource(final SourceDto sourceDto) {
		return dashboardTransaction.saveSource(sourceDto);
	}

	/**
	 * Check source exist or not.
	 *
	 * @param inSourceId the in source id
	 * @param sourceName the source name
	 * @param userName the user name
	 * @return true, if successful
	 */
	public boolean checkSourceExistOrNot(int inSourceId, String sourceName,
										 String userName) {
		return dashboardTransaction.checkSourceExistOrNot(inSourceId, sourceName,
				userName);
	}
	/**
	 * Method for getting sink list.
	 *
	 * @return sinkDtoList
	 */
	public List<IdNameDto> getSinkList() {
		return dashboardTransaction.getSinkList();
	}

	/**
	 * Method for getting sink details by passing sink id.
	 *
	 * @param inSinkId
	 *            the in sink id
	 * @return sinkDto
	 */
	public SinkDto getSinkData(final int inSinkId) {
		return dashboardTransaction.getSinkData(inSinkId);
	}

	/**
	 * Method for saving sink details.
	 *
	 * @param sinkDto
	 *            the sink dto
	 * @return boolean
	 */
	public boolean saveSink(final SinkDto sinkDto) {
		return dashboardTransaction.saveSink(sinkDto);
	}
	/**
	 * Check sink exist or not.
	 *
	 * @param inSinkId the in sink id
	 * @param sinkName the sink name
	 * @param userName the user name
	 * @return true, if successful
	 */
	public boolean checkSinkExistOrNot(int inSinkId, String sinkName,
									   String userName) {
		return dashboardTransaction.checkSinkExistOrNot(inSinkId, sinkName,
	userName);
	}

	/**
	 * Method for getting process details by passing process id.
	 *
	 * @param inProcessId
	 *            the in process id
	 * @return processDto
	 */
	public ProcessDto getProcessData(final int inProcessId) {

		return dashboardTransaction.getProcessData(inProcessId);
	}

	/**
	 * Method for getting process list.
	 *
	 * @return listProcessDto
	 */
	public List<IdNameDto> getProcessList() {
		return dashboardTransaction.getProcessList();
	}

	/**
	 * Method for saving process details.
	 *
	 * @param processDto
	 *            the process dto
	 * @return boolean
	 */
	public boolean saveProcess(final ProcessDto processDto) {

		return dashboardTransaction.saveProcess(processDto);
	}

	/**
	 * Check process exist or not.
	 *
	 * @param inProcessId the in process id
	 * @param processName the process name
	 * @param userName the user name
	 * @return true, if successful
	 */
	public boolean checkProcessExistOrNot(int inProcessId, String processName,
										  String userName) {
		return dashboardTransaction.checkProcessExistOrNot(inProcessId, processName,
				userName);
	}

	/**
	 * Method for saving category details.
	 *
	 * @param categoryDto
	 *            the category dto
	 * @return boolean
	 */
	public boolean saveCategory(final CategoryDto categoryDto) {

		return dashboardTransaction.saveCategory(categoryDto);
	}

	/**
	 * Check category exist or not.
	 *
	 * @param inCategoryId the in category id
	 * @param categoryName the category name
	 * @param userName the user name
	 * @return true, if successful
	 */
	public boolean checkCategoryExistOrNot(int inCategoryId, String categoryName,
										   String userName) {
		return dashboardTransaction.checkCategoryExistOrNot(inCategoryId, categoryName,
				userName);
	}

	/**
	 * Method for save pipeline details.
	 *
	 * @param pipelineDto
	 *            the pipeline dto
	 * @param visualizeDtos
	 *            the visualize dtos
	 * @param deletedVisualizations
	 *            the deleted visualizations
	 * @return boolean
	 */
	public boolean savePipeline(final PipelineDto pipelineDto,
								final List<VisualizeDto> visualizeDtos,
								final List<Integer> deletedVisualizations) {
		return dashboardTransaction.savePipeline(pipelineDto, visualizeDtos,
				deletedVisualizations);
	}

	/**
	 * Check pipeline exist or not.
	 *
	 * @param inPipelineId the in pipeline id
	 * @param pipelineName the pipeline name
	 * @param userName the user name
	 * @return true, if successful
	 */
	public boolean checkPipelineExistOrNot(int inPipelineId, String
			pipelineName, String userName) {
		return dashboardTransaction.checkPipelineExistOrNot(inPipelineId, pipelineName,
				userName);
	}

	/**
	 * Check visualization nameexist or not.
	 *
	 * @param visualizeCheck the visualize check
	 * @return true, if successful
	 */
	public boolean checkVisualizationExistOrNot(final VisualizeCheckDto
												 visualizeCheck) {
		return dashboardTransaction.checkVisualizationExistOrNot(visualizeCheck);
	}

	/**
	 * Method for getting category list.
	 *
	 * @return catList
	 */
	public List<IdNameDto> getCategoryList() {

		return dashboardTransaction.getCategoryList();
	}

	/**
	 * Method for getting category details by passing category id.
	 *
	 * @param inCategoryId
	 *            the in category id
	 * @return categoryDto
	 */
	public CategoryDto getCategoryData(final int inCategoryId) {

		return dashboardTransaction.getCategoryData(inCategoryId);
	}

	/**
	 * Method for getting pipeline list.
	 *
	 * @return pipelineDtoList
	 */
	public List<IdNameDto> getPipelineList() {
		return dashboardTransaction.getPipelineList();
	}

	/**
	 * Method for getting pipeline details by passing pipeline id.
	 *
	 * @param pipelineId
	 *            the pipeline id
	 * @return pipelineDto
	 */
	public PipelineDto getPipelineData(final int pipelineId) {
		return dashboardTransaction.getPipelineData(pipelineId);
	}

	/**
	 * Method for getting Kpi list.
	 *
	 * @return listKpiDto
	 */
	public List<IdNameDto> getKpiList() {

		return dashboardTransaction.getKpiList();
	}

	/**
	 * Method for getting kpi details by passing kpi id.
	 *
	 * @param inkpiId
	 *            the inkpi id
	 * @return kpiDto
	 */
	public KpiDto getKpiData(final int inkpiId) {

		return dashboardTransaction.getKpiData(inkpiId);
	}

	/**
	 * Method for saving kpi details.
	 *
	 * @param kpiDto
	 *            the kpi dto
	 * @return false
	 */
	public boolean saveKpi(final KpiDto kpiDto) {

		return dashboardTransaction.saveKpi(kpiDto);
	}
	/**
	 * Check kpi exist or not.
	 *
	 * @param inKpiId  the in kpi id
	 * @param kpiName the kpi name
	 * @param userName the user name
	 * @return true, if successful
	 */
	public boolean checkKpiExistOrNot(int inKpiId, String kpiName, String userName){
		return dashboardTransaction.checkKpiExistOrNot(inKpiId, kpiName,
				userName);
	}

	/**
	 * Method for listing each category and all kpis under each category.
	 *
	 * @return categoryKpisDtoList
	 */
	public List<CategoryKpisDto> getCategoryWithKpis() {

		return dashboardTransaction.getCategoryWithKpis();
	}

	/**
	 * Method for deleting source by passing source id.
	 *
	 * @param inSourceId
	 *            the in source id
	 * @return boolean
	 */
	public boolean deleteSource(final int inSourceId) {

		return dashboardTransaction.deleteSource(inSourceId);
	}

	/**
	 * Method for deleting sink by passing sink id.
	 *
	 * @param inSinkId
	 *            the in sink id
	 * @return boolean
	 */
	public boolean deleteSink(final int inSinkId) {

		return dashboardTransaction.deleteSink(inSinkId);
	}

	/**
	 * Method for deleting process by passing process id.
	 *
	 * @param inProcessId
	 *            the in process id
	 * @return boolean
	 */
	public boolean deleteProcess(final int inProcessId) {

		return dashboardTransaction.deleteProcess(inProcessId);
	}

	/**
	 * Method for deleting category by passing category id.
	 *
	 * @param inCategoryId
	 *            the in category id
	 * @return boolean
	 */
	public boolean deleteCategory(final int inCategoryId) {

		return dashboardTransaction.deleteCategory(inCategoryId);
	}

	/**
	 * Method for deleting kpi by passing kpi id.
	 *
	 * @param inKpiId
	 *            the in kpi id
	 * @return boolean
	 */
	public boolean deleteKpi(final int inKpiId) {

		return dashboardTransaction.deleteKpi(inKpiId);
	}

	/**
	 * Method for delete pipeline by passing pipeline id.
	 *
	 * @param inPipelineId
	 *            the in pipeline id
	 * @return boolean
	 */
	public boolean deletePipeline(final int inPipelineId) {

		return dashboardTransaction.deletePipeline(inPipelineId);
	}

	/**
	 * Method for listing all pipelines with category and kpi details.
	 *
	 * @return pipelineDtos
	 */
	public List<PipelineDto> getPipelines() {
		return dashboardTransaction.getPipelines();
	}

	/**
	 * Method for listing all visualizations with category and kpi details.
	 *
	 * @return categoryList
	 */
	public List<CategoryDto> getVisualizations() {
		return dashboardTransaction.getVisualizations();
	}

	/**
	 * Method for saving pipline status.
	 *
	 * @param pplId
	 *            the ppl id
	 * @param execPplId
	 *            the exec ppl id
	 * @param pplExecStatus
	 *            the ppl exec status
	 * @return boolean
	 */
	public boolean savePipelineStatus(final int pplId, final String execPplId,
									  final String pplExecStatus) {

		return dashboardTransaction.savePipelineStatus(pplId, execPplId,
				pplExecStatus);
	}

	/**
	 * Method for getting Pipeline execution URL.
	 *
	 * @param pipelineId
	 *            the pipeline id
	 * @return String
	 */
	public String getPipelineExecutionUrl(final int pipelineId) {
		return dashboardTransaction.getPipelineExecutionUrl(pipelineId);
	}

	/**
	 * Method for getting Pipeline execution URL.
	 *
	 * @param pipelineId
	 *            the pipeline id
	 * @return PipelineExecutionDto
	 */
	public PipelineExecutionDto getPipelineExecutionData(final int pipelineId) {
		return dashboardTransaction.getPipelineExecutionData(pipelineId);
	}

	/**
	 * Method for getting Pipeline execution id.
	 *
	 * @param pipelineId
	 *            the pipeline id
	 * @return String
	 */
	public String getPipelineExecutionId(final int pipelineId) {
		return dashboardTransaction.getPipelineExecutionId(pipelineId);
	}

	/**
	 * Method for getting max value of inVisualizeEntityId for a particular
	 * pipeline.
	 *
	 * @param pipelineId
	 *            the pipeline id
	 * @return int
	 */
	public int getMaxVisualizationCount(final int pipelineId) {
		return dashboardTransaction.getMaxVisualizationCount(pipelineId);
	}

	/**
	 * Method to get sink datatypes.
	 *
	 * @param strSinkType
	 *            the str sink type
	 * @return String
	 */
	public String getSinkDatatypes(final String strSinkType) {
		return dashboardTransaction.getSinkDatatypes(strSinkType);
	}

	/**
	 * Method for getting the list of metadata dto of uploaded source schema.
	 *
	 * @param inputString
	 *            the input string
	 * @param strFileType
	 *            the str file type
	 * @param strDelimiter
	 *            the str delimiter
	 * @param isHeaderExists
	 *            the is header exists
	 * @return List
	 */
	public List<ColumnMetadataDto> getColumnMetadataList(
			final String inputString, final String strFileType,
			final String strDelimiter, final boolean isHeaderExists) {
		return metadataUtil.getColumnMetadataDtoList(inputString, strFileType,
				strDelimiter, isHeaderExists);
	}

	/**
	 * Method for save or update lookup basic configuration.
	 *
	 * @param lookupDetailsDto
	 *            the lookup details dto
	 * @return ResponseDto
	 */
	public ResponseDto saveOrUpdateLookupBasicDetails(
			final LookupDetailsDto lookupDetailsDto) {
		return dashboardTransaction
				.saveOrUpdateLookupBasicDetails(lookupDetailsDto);
	}

	/**
	 * Check lookup exist or not.
	 *
	 * @param inLookupId the in lookup id
	 * @param lookupConfigName the lookup config name
	 * @param userName the user name
	 * @return true, if successful
	 */
	public boolean checkLookupExistOrNot(int inLookupId, String lookupConfigName,
									  String userName) {
		return dashboardTransaction.checkLookupExistOrNot(inLookupId,
				lookupConfigName, userName);
	}

	/**
	 * Method for save or update lookup Advanced configurations.
	 *
	 * @param lookupAdvancedDetailsDto
	 *            the lookup advanced details dto
	 * @return boolean
	 */
	public boolean saveOrUpdateLookupAdvancedDetails(
			final LookupAdvancedDetailsDto lookupAdvancedDetailsDto) {
		return dashboardTransaction
				.saveOrUpdateLookupAdvancedDetails(lookupAdvancedDetailsDto);
	}

	/**
	 * Method for getting lookup basic and advancedDetails.
	 *
	 * @param inLookupId
	 *            the in lookup id
	 * @return lookupDetailsDto
	 */
	public LookupDetailsDto getLookupDetails(final int inLookupId) {
		return dashboardTransaction.getLookupDetails(inLookupId);
	}

	/**
	 * Method for deleting Lookup by passing lookup id.
	 *
	 * @param inLookupId
	 *            the in lookup id
	 * @return boolean
	 */
	public boolean deleteLookup(final int inLookupId) {
		return dashboardTransaction.deleteLookup(inLookupId);
	}

	/**
	 * Method for deleting LookupDetails by passing lookupDetails Id.
	 *
	 * @param lookupDetailsId
	 *            the lookup details id
	 * @return boolean
	 */
	public boolean deleteLookupDetails(final int lookupDetailsId) {
		return dashboardTransaction.deleteLookupDetails(lookupDetailsId);
	}

	/**
	 * Method for listing all lookups.
	 *
	 * @return List<LookupDetailsDto>
	 */
	public List<LookupDetailsDto> getAllLookups() {
		return dashboardTransaction.getAllLookups();
	}

	/**
	 * Method for testing redis connection status.
	 *
	 * @param redisHost
	 *            the redis host
	 * @param redisPort
	 *            the redis port
	 * @return boolean
	 */
	public boolean testRedisConnection(final String redisHost,
									   final int redisPort) {
		return dashboardTransaction.testRedisConnection(redisHost, redisPort);
	}

	/**
	 * Method for getting the Spark Query Status.
	 *
	 * @param queryPlanDto
	 *            the query plan dto
	 * @return the query plan status
	 */
	public QueryPlanResultDto getQueryPlanStatus(
			final QueryPlanDto queryPlanDto) {
		return dashboardTransaction.getQueryPlanStatus(queryPlanDto);

	}

	/**
	 * Method for getting the sink table validation status.
	 *
	 * @param sinkValidationDto
	 *            the sink validation dto
	 * @return true, if is valid sink table
	 */
	public boolean isValidSinkTable(final SinkValidationDto sinkValidationDto) {

		return dashboardTransaction.isValidSinkTable(sinkValidationDto);
	}

	/**
	 * Method for geting Cassandra Table and Uploaded CSV file Schema details,
	 * Table data and CSV data for preview  by passing lookupDetails Id and
	 * data required status.
	 *
	 * @param lookupDetailsId
	 * @param isDataRequired
	 * @return PreviewDataDto
	 */
	public PreviewDataDto getSchemaAndContent(int lookupDetailsId, boolean isDataRequired){
		return dashboardTransaction.getSchemaAndContent(lookupDetailsId, isDataRequired);
	}

	/**
	 * Method for getting Lookup configurations ( id, name and type).
	 *
	 * @return List
	 */
	public List<IdNameDto> getLookupConfigurations(){
		return dashboardTransaction.getLookupConfigurations();
	}

}
