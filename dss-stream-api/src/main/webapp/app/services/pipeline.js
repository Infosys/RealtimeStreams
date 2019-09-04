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
 * Description : Pipeline Services .
 */

app.service('pipelineService', function ($http, $q) {

	//Function to get all existing pipelines
	this.getExistingPipelines = function (tokenValue) {
		console.log(serviceURL.getAllPipeline);
		return $http({
			method : 'GET',
			url : serviceURL.getAllPipeline,
			headers: {'token': tokenValue} 
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res =  {};
				res.errors =  {};
				res.success =  false;
				res.errors.errorCode =  "";
				res.errors.errorMessage =  msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res =  {};
			res.errors =  {};
			res.success =  false;
			res.errors.errorCode =  "";
			res.errors.errorMessage =  msg.msgExistingConnectionError;
			return res;
		});
	}
	//Function to get datatypes based on sink type.
	this.getSinkDatatypes = function (sinkType, tokenValue) {
		console.log(serviceURL.getSinkDatatypes);
		return $http({
			method : 'GET',
			url : serviceURL.getSinkDatatypes + "?sinkType=" + sinkType,
			headers: {'token': tokenValue} 
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res =  {};
				res.errors =  {};
				res.success =  false;
				res.errors.errorCode =  "";
				res.errors.errorMessage =  msg.msgError;
				return res;
			}
		}, function (response) {
			var res =  {};
			res.errors =  {};
			res.success =  false;
			res.errors.errorCode =  "";
			res.errors.errorMessage =  msg.msgError;
			return res;
		});
	}
	//Function to validate visualization name.
	this.validateVisualizationName = function (visObj, tokenValue) {
		console.log(serviceURL.validateVisualizationName);
		return $http({
			method : 'POST',
			url : serviceURL.validateVisualizationName,
			data : JSON.stringify(visObj),
			headers: {'token': tokenValue} 
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res =  {};
				res.errors =  {};
				res.success =  false;
				res.errors.errorCode =  "";
				res.errors.errorMessage =  msg.msgError;
				return res;
			}
		}, function (response) {
			var res =  {};
			res.errors =  {};
			res.success =  false;
			res.errors.errorCode =  "";
			res.errors.errorMessage =  msg.msgError;
			return res;
		});
	}

	this.getVisualizationMaxId = function (id, tokenValue) {
		return $http({
			method : 'GET',			
			url : serviceURL.getVisualizationMaxId,
			params : id,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;				
			} else {
				var res =  {};
				res.errors =  {};
				res.success =  false;
				res.errors.errorCode =  "";
				res.errors.errorMessage =  msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res =  {};
			res.errors =  {};
			res.success =  false;
			res.errors.errorCode =  "";
			res.errors.errorMessage =  msg.msgExistingConnectionError;
			return res;
		});
	}
	//Function to delete selected pipeline
	this.deletePipeline = function (pipelineID, tokenValue) {
		console.log(serviceURL.deletePipeline);
		return $http({
			method : 'POST',
			url : serviceURL.deletePipeline + "/" + pipelineID,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res =  {};
				res.errors =  {};
				res.success =  false;
				res.errors.errorCode =  "";
				res.errors.errorMessage =  msg.msgDeletePipelineError;
				return res;
			}
		}, function (response) {
			var res =  {};
			res.errors =  {};
			res.success =  false;
			res.errors.errorCode =  "";
			res.errors.errorMessage =  msg.msgDeletePipelineError;
			return res;
		});
	}

	//Function to execute selected pipeline
	this.executePipeline = function (pipelineID, pipelineExeURL, tokenValue) {
		console.log(serviceURL.executePipeline);
		return $http({
			method : 'POST',
			url : serviceURL.executePipeline + "/" + pipelineID,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res =  {};
				res.errors =  {};
				res.success =  false;
				res.errors.errorCode =  "";
				res.errors.errorMessage =  msg.msgExecutePipelineError;
				return res;
			}
		}, function (response) {
			var res =  {};
			res.errors =  {};
			res.success =  false;
			res.errors.errorCode =  "";
			res.errors.errorMessage =  msg.msgExecutePipelineError;
			return res;
		});
	};

	//Function to stop execute selected pipeline
	this.stopExecutePipeline = function (pipelineID, tokenValue) {
		console.log(serviceURL.executePipeline);
		return $http({
			method : 'POST',
			url : serviceURL.stopExecutePipeline + "/" + pipelineID,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res =  {};
				res.errors =  {};
				res.success =  false;
				res.errors.errorCode =  "";
				res.errors.errorMessage =  msg.msgExecutePipelineError;
				return res;
			}
		}, function (response) {
			var res =  {};
			res.errors =  {};
			res.success =  false;
			res.errors.errorCode =  "";
			res.errors.errorMessage =  msg.msgExecutePipelineError;
			return res;
		});
	};
	//Function to fetch details of selected pipeline
    this.getSelectedPipelineDetails =  function(pipelineObj,tokenValue) {
    	console.log("Before API"+JSON.stringify(pipelineObj));
		return $http({
			method : 'GET',
			url : serviceURL.getPipelineDetails  + "?categoryId=" + pipelineObj.categoryId + "&kpiId=" + pipelineObj.kpiId + "&pipelineId=" + pipelineObj.pipelineId,
			//data : pipelineObj,
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
	//Function to stop execute selected pipeline
	this.validateQuery = function (queryObj, tokenValue) {
		console.log(serviceURL.validateQuery);
		return $http({
			method : 'POST',
			url : serviceURL.validateQuery,
			data : queryObj,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res =  {};
				res.errors =  {};
				res.success =  false;
				res.errors.errorCode =  "";
				res.errors.errorMessage =  msg.msgExecutePipelineError;
				return res;
			}
		}, function (response) {
			var res =  {};
			res.errors =  {};
			res.success =  false;
			res.errors.errorCode =  "";
			res.errors.errorMessage =  msg.msgExecutePipelineError;
			return res;
		});
	}
	
	//Set the values entered in modal window - SINK.
	this.queriesFromPopup = function () {
		return queries;
	}
	this.windowNamesFromPopup = function () {
		return windowNames;
	}
	this.windowOptionsFromPopup = function () {
		return windowOptions;
	}
	
	this.setQueryPopUpValues = function (value1) {
		queries = value1;
	}
	this.setWindowNamePopUpValues = function (value2) {
		windowNames = value2;
	}
	this.setWindowOptionPopUpValues = function (value3) {
		windowOptions = value3;
	}
});



