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
 * Description : Service for workflow.
 */

wfapp.service('wfService', function($http, $q) {

	//Function to get the visualization id using pipeline id.
	this.getVisualizationId = function(id) {

		return $http({
			method : 'GET',			
//			url : serviceURL.getVisualizationId + "/" + id
			url : serviceURL.getVisualizationId
		}).then(function(response) {
			if (typeof response.data === 'object') {
				response.data = {
						   "success": true,
						   "root":    {
							      "maxId": 0
							   }
				};
				return response.data;				
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}

	this.getExistingSourceNames = function() {
		return $http({
			method : 'GET',
			/*url : "../"+serviceURL.getExistingSourceNames*/
			//url:"data/source-schema.json"
			/*url : serviceURL.getExistingSourceNames*/
			url : serviceURL.getAllSourceConfig
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}



	//Function to get all existing process names with id.
	this.getExistingProcessNames = function() {

		return $http({
			method : 'GET',
			/*url : "../"+serviceURL.getExistingSourceNames*/
			url : serviceURL.getAllProcessConfig
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}



	//Function to get all existing connections
	this.getExistingSinkNames = function() {
		return $http({
			method : 'GET',
			/*url : "../"+serviceURL.getExistingSourceNames*/
			url : serviceURL.getAllSinkConfig
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}

	//Function to get all existing connections
	this.getExistingCategoryNames = function() {
		return $http({
			method : 'GET',
			/*url : serviceURL.getExistingSourceNames*/
			url : serviceURL.getAllCategoryConfig
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}
	//Function to get all existing connections
	this.getExistingKPINames = function() {
		return $http({
			method : 'GET',
			/*url : serviceURL.getExistingSourceNames*/
			url : serviceURL.getAllKPIConfig
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}


	//List with IDs

	//Function to get all existing source details based on ID
	this.getExistingSourcebyId = function(sourceId) {
		return $http({
			method : 'GET',
			url : serviceURL.getSourceConfig,
			params : sourceId
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}
//	Function to get all existing process details based on ID
	this.getExistingProcessbyId = function(processId) {
		return $http({
			method : 'GET',
			url : serviceURL.getProcessConfig,
			params : processId
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}

	//Function to get all existing process details based on ID
	this.getExistingSinkbyId = function(sinkId) {
		return $http({
			method : 'GET',
			url : serviceURL.getSinkConfig,
			params : sinkId
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}

	//Function to get all existing process details based on ID
	this.getExistingCategorybyId = function(categoryId) {
		return $http({
			method : 'GET',
			url : serviceURL.getCategoryConfig,
			params : categoryId
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}
	//Function to get all existing kpi details based on ID
	this.getExistingKPIbyId = function(kpiId) {
		return $http({
			method : 'GET',
			url : serviceURL.getKPIConfig,
			params : kpiId
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}




	this.saveDataflow =  function(dataFlowJson,tokenValue) {
		console.log(serviceURL.savePipeline);
		return $http({
			method : 'POST',
			url : serviceURL.savePipeline,
			data : JSON.stringify(dataFlowJson),
			headers: {'token': tokenValue}
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	}

	this.retreivePipelines = function() {
		return $http({
			method : 'GET',
			url : serviceURL.getAllPipeline

		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});
	}
	this.getPipelineById = function(pipelineId) {
		return $http({
			method : 'POST',
			url : serviceURL.getWorkflow,
			data : pipelineId
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});
	}



});



