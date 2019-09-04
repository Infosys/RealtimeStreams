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
package org.streamconnect.dss.api.ui;

import org.streamconnect.dss.api.ui.builder.DashboardBuilder;
import org.streamconnect.dss.api.ui.response.Category;
import org.streamconnect.dss.api.ui.response.Kpi;
import org.streamconnect.dss.api.ui.response.LookupDetails;
import org.streamconnect.dss.api.ui.response.Pipeline;
import org.streamconnect.dss.api.ui.response.Process;
import org.streamconnect.dss.api.ui.response.Sink;
import org.streamconnect.dss.api.ui.response.Source;
import org.streamconnect.dss.api.ui.response.VisualizeCheck;
import org.streamconnect.dss.dto.QueryPlanDto;
import org.streamconnect.dss.dto.SinkValidationDto;
import org.streamconnect.dss.enums.LogMessageEnum;
import org.streamconnect.dss.logger.DSSLogger;
import org.streamconnect.dss.util.Constants;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;


/**
 * All the APIs's needed to populate ,update , insert releted to Dashboard
 * Components.
 *
 */
@Component
@Path("/db")
public class DashboardAPI {
	/** The Constant LOGGER. */
	private static final DSSLogger LOGGER = DSSLogger.getLogger(DashboardAPI
			.class);

	/** The Constant TOKEN. */
	private static final String TOKEN = "token";
	/**
	 * The dashboard builder.
	 */
	@Autowired
	private DashboardBuilder dashboardBuilder;

	/**
	 * Method for getting a source details by passing source id.
	 *
	 * @param sourceId the source id
	 * @return Response source data
	 */
	@GET
	@Path("/laso")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSourceData(@QueryParam("sourceId") final int sourceId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSourceData function : "
				+ Constants.START_STATUS);
		String objResponse = dashboardBuilder.getSourceData(sourceId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSourceData function : "
				+ Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting source list.
	 *
	 * @return Response source list
	 */
	@GET
	@Path("/lason")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSourceList() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSourceList function : "
				+ Constants.START_STATUS);
		String objResponse = dashboardBuilder.getSourceList();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSourceList function : "
				+ Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for saving source details.
	 *
	 * @param source the source
	 * @param request the request
	 * @return Response response
	 */
	@POST
	@Path("/sso")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveSource(final Source source,
							   @Context final HttpServletRequest request) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveSource function : "
				+ Constants.START_STATUS);
		final String token = request.getHeader(TOKEN);
		String objResponse = dashboardBuilder.saveSource(source, token);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveSource function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for deleting source by passing source id.
	 *
	 * @param sourceId the source id
	 * @return Response response
	 */
	@POST
	@Path("/dso/{sourceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSource(@PathParam("sourceId") final int sourceId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteSource function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.deleteSource(sourceId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteSource function : " + Constants
				.END_STATUS);
		return  Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting process details by passing process id.
	 *
	 * @param processId the process id
	 * @return Response process data
	 */
	@GET
	@Path("/lap")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProcessData(@QueryParam("processId") final int
											   processId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getProcessData function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getProcessData(processId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getProcessData function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting process list.
	 *
	 * @return Response process list
	 */
	@GET
	@Path("/lapn")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProcessList() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getProcessList function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getProcessList();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getProcessList function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for saving process details.
	 *
	 * @param process the process
	 * @param request the request
	 * @return Response response
	 */
	@POST
	@Path("/sp")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveProcess(final Process process,
								@Context final HttpServletRequest request) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveProcess function : " + Constants
				.START_STATUS);
		final String token = request.getHeader(TOKEN);
		String objResponse = dashboardBuilder.saveProcess(process, token);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveProcess function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for deleting process by passing process id.
	 *
	 * @param processId the process id
	 * @return Response response
	 */
	@POST
	@Path("/dpr/{processId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProcess(@PathParam("processId") final int processId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteProcess function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.deleteProcess(processId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteProcess function : " + Constants
				.END_STATUS);
		return  Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting sink details by passing sink id.
	 *
	 * @param sinkId the sink id
	 * @return Response sink data
	 */
	@GET
	@Path("/lsi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSinkData(@QueryParam("sinkId") final int sinkId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSinkData function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getSinkData(sinkId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSinkData function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting sink list.
	 *
	 * @return Response sink list
	 */
	@GET
	@Path("/lsin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSinkList() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSinkList function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getSinkList();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSinkList function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for saving sink details.
	 *
	 * @param sink the sink
	 * @param request the request
	 * @return Response response
	 */
	@POST
	@Path("/ssi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveSink(final Sink sink,
							 @Context final HttpServletRequest request) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveSink function : " + Constants
				.START_STATUS);
		final String token = request.getHeader(TOKEN);
		String objResponse = dashboardBuilder.saveSink(sink, token);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveSink function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for deleting sink by passing sink id.
	 *
	 * @param sinkId the sink id
	 * @return Response response
	 */
	@POST
	@Path("/dsi/{sinkId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSink(@PathParam("sinkId") final int sinkId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteSink function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.deleteSink(sinkId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteSink function : " + Constants
				.END_STATUS);
		return  Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting category details by passing category id.
	 *
	 * @param categoryId the category id
	 * @return Response category data
	 */
	@GET
	@Path("/lac")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryData(@QueryParam("categoryId") final int
												categoryId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getCategoryData function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getCategoryData(categoryId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getCategoryData function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting category list.
	 *
	 * @return Response category list
	 */
	@GET
	@Path("/lacn")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryList() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getCategoryList function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getCategoryList();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getCategoryList function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for saving category details.
	 *
	 * @param category the category
	 * @param request the request
	 * @return Response response
	 */
	@POST
	@Path("/sc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveCategory(final Category category,
								 @Context final HttpServletRequest request) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveCategory function : " + Constants
				.START_STATUS);
		final String token = request.getHeader(TOKEN);
		String objResponse = dashboardBuilder.saveCategory(category, token);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveCategory function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for deleting category by passing category id.
	 *
	 * @param categoryId the category id
	 * @return Response response
	 */
	@POST
	@Path("/dca/{categoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCategory(@PathParam("categoryId") final int
											   categoryId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteCategory function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.deleteCategory(categoryId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteCategory function : " + Constants
				.END_STATUS);
		return  Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting pipeline details by passing category id, kpi id and
	 * pipeline id.
	 *
	 * @param categoryId the category id
	 * @param kpiId      the kpi id
	 * @param pipelineId the pipeline id
	 * @return Response pipeline data
	 */
	//TODO Remove categoryId and kpiId
	@GET
	@Path("/lapl")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPipelineData(@QueryParam("categoryId") final int
												categoryId,
									@QueryParam("kpiId") final int kpiId,
									@QueryParam("pipelineId") final int
												pipelineId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelineData function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getPipelineData(pipelineId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelineData function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting pipeline details by pipeline id.
	 * @param pipelineId the pipeline id
	 * @return Response pipeline data
	 */
	@GET
	@Path("/lapgm")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPipelineDataForMonitoring(@QueryParam("pipelineId") final
												 int
														 pipelineId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelineDataForMonitoring function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getPipelineDataForMonitoring(pipelineId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelineDataForMonitoring function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting pipeline list.
	 *
	 * @return Response pipeline list
	 */
	@GET
	@Path("/lapln")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPipelineList() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelineList function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getPipelineList();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelineList function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for save pipeline details.
	 *
	 * @param pipeline the pipeline
	 * @param request the request
	 * @return Response response
	 */
	@POST
	@Path("/spl")
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePipeline(final Pipeline pipeline,
								 @Context final HttpServletRequest request) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : savePipeline function : " + Constants
				.START_STATUS);
		final String token = request.getHeader(TOKEN);
		String objResponse = dashboardBuilder.savePipeline(pipeline, token);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : savePipeline function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Check visualization Name exist or not.
	 *
	 * @param visualizeCheck the visualize check
	 * @return the response
	 */
	@POST
	@Path("/cve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkVisualizationExistOrNot(final VisualizeCheck
														 visualizeCheck) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : checkVisualizationExistOrNot function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.checkVisualizationExistOrNot(visualizeCheck);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : checkVisualizationExistOrNot function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for delete pipeline by passing pipeline id.
	 *
	 * @param pipelineId the pipeline id
	 * @return Response response
	 */
	@POST
	@Path("/dpl/{pipelineId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePipeline(@PathParam("pipelineId") final int
			pipelineId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deletePipeline function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.deletePipeline(pipelineId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deletePipeline function : " + Constants
				.END_STATUS);
		return  Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting kpi details by passing kpi id.
	 *
	 * @param kpiId the kpi id
	 * @return Response kpi data
	 */
	@GET
	@Path("/lakpi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKpiData(@QueryParam("kpiId") final int kpiId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getKpiData function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getKpiData(kpiId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getKpiData function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting Kpi list.
	 *
	 * @return Response kpi list
	 */
	@GET
	@Path("/lakpin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKpiList() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getKpiList function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getKpiList();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getKpiList function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for saving kpi details.
	 *
	 * @param kpi the kpi
	 * @param request the request
	 * @return Response response
	 */
	@POST
	@Path("/skpi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveKpi(final Kpi kpi,
							@Context final HttpServletRequest request) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveKpi function : " + Constants
				.START_STATUS);
		final String token = request.getHeader(TOKEN);
		String objResponse = dashboardBuilder.saveKpi(kpi, token);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveKpi function : " + Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for deleting kpi by passing kpi id.
	 *
	 * @param kpiId the kpi id
	 * @return Response response
	 */
	@POST
	@Path("/dkp/{kpiId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteKpi(@PathParam("kpiId") final int kpiId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteKpi function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.deleteKpi(kpiId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteKpi function : " + Constants
				.END_STATUS);
		return  Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for listing each category and all kpis under each category.
	 *
	 * @return Response category with kpis
	 */
	@GET
	@Path("/lck")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryWithKpis() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getCategoryWithKpis function : "
				+ Constants.START_STATUS);
		String objResponse = dashboardBuilder.getCategoryWithKpis();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getCategoryWithKpis function : "
				+ Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for listing all pipelines with category and kpi details.
	 *
	 * @return Response pipelines
	 */
	@GET
	@Path("/lalpl")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPipelines() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelines function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getPipelines();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelines function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for listing all visualization in a pipeline with category and
	 * kpi details.
	 *
	 * @param catId the cat id
	 * @param kpiId the kpi id
	 * @return Response visualizations
	 */
	@GET
	@Path("/lavis")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisualizations(@QueryParam("catId") final int catId,
									  @QueryParam("kpiId") final int kpiId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getVisualizations function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getVisualizations();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getVisualizations function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for executing a pipeline.
	 *
	 * @param pipelineId the pipeline id
	 * @return Response response
	 */
	@POST
	@Path("/epl/{pipelineId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response executePipeline(@PathParam("pipelineId") final int
												pipelineId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : executePipeline function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.executePipeline(pipelineId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : executePipeline function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for stopping a pipeline.
	 *
	 * @param pipelineId the pipeline id
	 * @return Response response
	 */
	@POST
	@Path("/sepl/{pipelineId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response stopExecutePipeline(@PathParam("pipelineId") final int
													pipelineId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : stopExecutePipeline function : "
				+ Constants.START_STATUS);
		String objResponse = dashboardBuilder.stopExecutePipeline(pipelineId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : stopExecutePipeline function : "
				+ Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting pipeline execution status.
	 *
	 * @param pipelineId the pipeline id
	 * @param execId     the exec id
	 * @return Response pipeline execution status
	 */
	@GET
	@Path("/gepls")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPipelineExecutionStatus(@QueryParam("pipelineId")
														   final int
														   pipelineId,
											   @QueryParam("execId") final
											   String execId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelineExecutionStatus function : "
				+ Constants.START_STATUS);
		String objResponse = dashboardBuilder.getPipelineExecutionStatus(pipelineId, execId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getPipelineExecutionStatus function : "
				+ Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting maximum count of visualizations.
	 *
	 * @param pipelineId the pipeline id
	 * @return Response max visualization count
	 */
	@GET
	@Path("/gmvc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMaxVisualizationCount(@QueryParam("pipelineId") final
												 int pipelineId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getMaxVisualizationCount function : "
				+ Constants.START_STATUS);
		String objResponse = dashboardBuilder.getMaxVisualizationCount(pipelineId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getMaxVisualizationCount function : "
				+ Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting sink datatypes.
	 *
	 * @param strSinkType the str sink type
	 * @return Response sink datatypes
	 */
	@GET
	@Path("/gskd")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSinkDatatypes(@QueryParam("sinkType") final String
												 strSinkType) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSinkDatatypes function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getSinkDatatypes(strSinkType);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSinkDatatypes function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting the metadata of input source schema.
	 *
	 * @param inputStream    the input stream
	 * @param strFileType    the str file type
	 * @param strDelimiter   the str delimiter
	 * @param isHeaderExists the is header exists
	 * @return Response metadata
	 */
	@POST
	@Path("/amd")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMetadata(@FormDataParam("strSchemaInput")
											final InputStream	inputStream,
								@FormDataParam("strFileType") final String
										strFileType, @FormDataParam
											("strDelimiter") final String
											strDelimiter, @FormDataParam
											("isHeaderExists") final boolean
											isHeaderExists) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getMetadata function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getMetadata(inputStream, strFileType, strDelimiter, isHeaderExists);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getMetadata function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}


	/**
	 * Method for save or update lookup basic configuration.
	 *
	 * @param lookupDetails the lookup details
	 * @return Response response
	 */
	@POST
	@Path("/slbd")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveOrUpdateLookupBasicDetails(final LookupDetails
				lookupDetails, @Context final HttpServletRequest request) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveOrUpdateLookupBasicDetails function : "
				+ Constants.START_STATUS);
		final String token = request.getHeader(TOKEN);
		String objResponse = dashboardBuilder.saveOrUpdateLookupBasicDetails
				(lookupDetails, token);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveOrUpdateLookupBasicDetails function : "
				+ Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}


	/**
	 * Method for save or update lookup basic configuration.
	 *
	 * @param lookupId            the lookup id
	 * @param id                  the id
	 * @param strSourceType       the str source type
	 * @param uploadedFile        the uploaded file
	 * @param strUploadedFileName the str uploaded file name
	 * @param sinkId              the sink id
	 * @param strKeySpaceName     the str key space name
	 * @param strtableName        the strtable name
	 * @return Response response
	 */
	@POST
	@Path("/slad")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveOrUpdateLookupAdvancedDetails(
			@FormDataParam("lookupId") final int lookupId,
			@FormDataParam("id") final int id,
			@FormDataParam("strSourceType") final String strSourceType,
			@FormDataParam("uploadedFile") final InputStream uploadedFile,
			@FormDataParam("strUploadedFileName") final FormDataContentDisposition strUploadedFileName,
			@FormDataParam("sinkId") final int sinkId,
			@FormDataParam("strKeySpaceName") final String strKeySpaceName,
			@FormDataParam("strtableName") final String strtableName) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveOrUpdateLookupAdvancedDetails function"
				+ " : " + Constants.START_STATUS);
		String objResponse = dashboardBuilder.saveOrUpdateLookupAdvancedDetails(lookupId, id,
				strSourceType, uploadedFile, strUploadedFileName, sinkId, strKeySpaceName, strtableName);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : saveOrUpdateLookupAdvancedDetails function"
				+ " : " + Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting lookup basic and advancedDetails.
	 *
	 * @param inLookupId the in lookup id
	 * @return Response lookup details
	 */
	@GET
	@Path("/gld")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLookupDetails(@QueryParam("inLookupId") final int inLookupId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getLookupDetails function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getLookupDetails(inLookupId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getLookupDetails function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for deleting Lookup by passing lookup id.
	 *
	 * @param inLookupId the in lookup id
	 * @return Response response
	 */
	@POST
	@Path("/dld")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteLookup(@QueryParam("inLookupId") final int inLookupId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteLookup function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.deleteLookup(inLookupId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteLookup function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for deleting LookupDetails by passing lookupDetails Id.
	 *
	 * @param lookupDetailsId the lookup details id
	 * @return Response response
	 */
	@POST
	@Path("/dlad")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteLookupDetails(@QueryParam("lookupDetailsId") final int lookupDetailsId) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteLookupDetails function : "
				+ Constants.START_STATUS);
		String objResponse = dashboardBuilder.deleteLookupDetails(lookupDetailsId);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : deleteLookupDetails function : "
				+ Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for listing all lookups.
	 *
	 * @return Response all lookups
	 */
	@GET
	@Path("/gal")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLookups() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getAllLookups function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getAllLookups();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getAllLookups function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for testing redis connection status.
	 *
	 * @param redisHost the redis host
	 * @param redisPort the redis port
	 * @return Response response
	 */
	@GET
	@Path("/rst")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testRedisConnection(@QueryParam("redisHost") final String
					redisHost, @QueryParam("redisPort") final int redisPort) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : testRedisConnection function : "
				+ Constants.START_STATUS);
		String objResponse = dashboardBuilder.testRedisConnection(redisHost, redisPort);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : testRedisConnection function : "
				+ Constants.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for getting the Query Plan Status.
	 *
	 * @param queryPlanDto the query plan dto
	 * @return response query plan status
	 */
	@POST
	@Path("/qps")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQueryPlanStatus(final QueryPlanDto queryPlanDto) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getQueryPlanStatus function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getQueryPlanStatus(queryPlanDto);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getQueryPlanStatus function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for validating the sink table name.
	 *
	 * @param sinkValidationDto the sink validation dto
	 * @return status message (valid/already exists)
	 */
	@POST
	@Path("/stv")
	@Produces(MediaType.APPLICATION_JSON)
	public Response isValidSinkTable(final SinkValidationDto
												 sinkValidationDto) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : isValidSinkTable function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.isValidSinkTable(sinkValidationDto);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : isValidSinkTable function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for testing redis connection status.
	 *
	 * @param strHost the str host
	 * @param nPort   the n port
	 * @return Response response
	 */
	@GET
	@Path("/thc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testHostConnection(@QueryParam("Host") final String
												   strHost, @QueryParam("Port") final int nPort) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : testHostConnection function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.testHostConnection(strHost, nPort);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : testHostConnection function : " + Constants
				.END_STATUS);
		return Response.status(Constants.SUCCESS_CODE).entity(objResponse).build();
	}

	/**
	 * Method for geting Cassandra Table and Uploaded CSV file Schema details,
	 * Table data and CSV data for preview  by passing lookupDetails Id and
	 * data required status.
	 *
	 * @param lookupDetailsId
	 * @param isDataRequired
	 * @return Response
	 */
	@GET
	@Path("/plc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSchemaAndContent(@QueryParam("lookupDetailsId") int lookupDetailsId,
										@QueryParam("isDataRequired") boolean isDataRequired) {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSchemaAndContent function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getSchemaAndContent(lookupDetailsId,
				isDataRequired);
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getSchemaAndContent function : " + Constants
				.END_STATUS);
		return Response.status(200).entity(objResponse).build();
	}

	/**
	 *  Method for getting Lookup configurations ( id, name and type).
	 *
	 * @return Response
	 */
	@GET
	@Path("/gll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLookupConfigurations() {
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getLookupConfigurations function : " + Constants
				.START_STATUS);
		String objResponse = dashboardBuilder.getLookupConfigurations();
		LOGGER.info(LogMessageEnum.STREAM_API_LAYER_INFO.getMessage()
				+ " DashboardAPI : getLookupConfigurations function : " + Constants
				.END_STATUS);
		return Response.status(200).entity(objResponse).build();
	}

}
