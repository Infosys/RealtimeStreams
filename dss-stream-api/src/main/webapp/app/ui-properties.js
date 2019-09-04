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
/**
 * Description : UI properties file for Logistic DSS
 */

var serviceURL = {

		//Login services
		authenticateUser : "rest/lg/aut",
		login : "rest/lg/sn",
		logout : "rest/lg/lgt" ,

		saveSourceConfig : "rest/db/sso",
		saveProcessConfig : "rest/db/sp",
		saveSinkConfig : "rest/db/ssi",
		saveUdfConfig : "rest/uf/suf",
		saveCategoryConfig : "rest/db/sc",
		saveKPIConfig : "rest/db/skpi",
		savePipeline : "../rest/db/spl",

		validateQuery : "rest/db/qps",

		getAllSourceConfig : "rest/db/lason",
		getAllProcessConfig : "rest/db/lapn",
		getAllSinkConfig : "rest/db/lsin",
		getAllUdfConfig : "rest/uf/lufn",
		getAllCategoryConfig : "rest/db/lacn",
		getAllKPIConfig : "rest/db/lakpin",
		getAllPipeline : "rest/db/lalpl",
		getAllCategoryWithKpi : "rest/db/lck",

		getSourceConfig : "rest/db/laso",
		getProcessConfig : "rest/db/lap",
		getSinkConfig : "rest/db/lsi",
		getUdfConfig : "rest/uf/luf",
		getCategoryConfig : "rest/db/lac",
		getKPIConfig : "rest/db/lakpi",
		getPipeline : "rest/db/lkp",
		getPipelineDetails : "../rest/db/lapl",
		getVisualizationId : "rest/db/lalpl",
		getVisualizationMaxId : "rest/db/gmvc",
		getSinkDatatypes : "rest/db/gskd",
		validateVisualizationName : "rest/db/cve",

		testFlumeConnection : "rest/db/thc",

		getMetaData : "rest/db/amd",
		deletePipeline : "rest/db/dpl",
		deleteSource : "rest/db/dso",
		deleteProcess : "rest/db/dpr",
		deleteSink : "rest/db/dsi",
		deleteUdf : "rest/uf/duf",
		deleteCategory : "rest/db/dca",
		deleteKPI : "rest/db/dkp",

		executePipeline : "rest/db/epl",
		stopExecutePipeline : "rest/db/sepl",
		getVisualizations : "rest/db/lavis",

		addPortalInput : "rest/vm/sprl",
		addDashboard : "rest/vm/sdbd",
		getExistingCategoriesList: "rest/vm/gct",
		getPortalList : "rest/vm/gpl",
		getPortalDetails : "rest/vm/gpd",
		getDashboardList : "rest/vm/gdl",
		getDashboardDetails : "rest/vm/gdbd",
		deletePortalDetails : "rest/vm/dprl",
		deleteDashboardDetails : "rest/vm/dbd",

		getAllRoles : "rest/ua/lr",
		saveRoleData : "rest/ua/aur",
		deleteRole : "rest/ua/dur",

		getAllUsers : "rest/ua/lu",
		getUserData : "rest/ua/lur",
		saveUserRoleMapping: "rest/ua/mur",
		getRoleDetails : "rest/ua/lap",
		getAllUserRoleMapping:"rest/ua/lrs",
		deleteUserRole:"rest/ua/rmur",
		editUserRole:"rest/ua/mur",

		updateAccessData : "rest/ua/mra",
		getAllPortalDetailsForRole : "rest/ua/lra",
		getAllPortalDetailsinAccess : "rest/ua/gpt"

};

var msg = {
		execError : "Error in Execution.",
		msgExecutePipelineError : "Error in executing Pipeline.",
		msgDeletePipelineError : "Error in deleting Pipeline.",

		msgSavePipeline : "Pipeline Saved successfully",
		msgExecutePipelineSuccess : "Pipeline is executed successfully",
		msgDeletePipelineSuccess : "Pipeline is deleted successfully",

		msgSaveSourceStep : "Source details saved successfully",
		msgUpdateSourceStep : "Source details updated successfully",
		msgSaveProcessStep : "Process details saved successfully",
		msgUpdateProcessStep : "Process details updated successfully",
		msgSaveSinkStep : "Sink details saved successfully",
		msgUpdateSinkStep : "Sink details updated successfully",
		msgSaveVisualizeStep : "Visualize details saved successfully",
		msgUpdateVisualizeStep : "Visualize details updated successfully",

		msgDriverSaveError : "file upload error",

		msgLoginError: "Login Failed! Please check the credentials",
		msgGetAllRoleDetailsError: "Error in fetching roles",

		msgExecutionFailure : "Required steps to execute the pipeline are missing. Please update Pipeline to execute",

		msgCreatePipeline : "Pipeline creation flow: Source > Process > Sink > Visualize ",
		msgMissingConnector : "Pipeline component connection is missing ",
		msgInvalidConnector : "Invalid pipeline component connection ",
		msgInvalidComponent : "Pipeline component details are missing ",
		msgError : "Error in fetching data ",
		msgVisualizationNameError : "Visualization Save Failed, Name Already Exist"
};
var commonURL = {
		url: "http://<ip>:9094",
		YRManager: "http://<ip>:9094",
		HDFS: "http://<ip>:50070",
		StreamExecutor : "http://<ip>:8998/threads",
		Spark: "http://<ip>:4040/",
		Grafana: "http://<ip>:3000"
}

