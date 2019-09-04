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
 * Description : Services in Configuration Page.
 */
/*global
app, console, serviceURL, msg
*/
app.service('configService', function ($http, $q) {
    "use strict";
	//Function to get all existing sources with id.
	this.getExistingSourceNames = function (tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getAllSourceConfig,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	//Function to get all existing process names with id.
	this.getExistingProcessNames = function (tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getAllProcessConfig,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	//Function to get all existing sink names with id
	this.getExistingSinkNames = function (tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getAllSinkConfig,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	//Function to get all existing UDF functions with id
	this.getExistingUdf = function (tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getAllUdfConfig,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			/*res={
					"success": true,
					"root": [{
						"inUdfId": 1,
						"strUdfName": "convert",
						"strMainClassName": "Conversion",
						"strReturnType": "boolean",
						"strUdfDesc": "Convert decimal to integer",
						"strServerUrl": "<server ip>/tmp"
					}, {
						"inUdfId": 2,
						"strUdfName": "celsius2Farenheit",
						"strMainClassName": "Conversion",
						"strReturnType": "boolean",
						"strUdfDesc": "Convert decimal to integer",
						"strServerUrl": "<server ip>/tmp"
					}, {
						"inUdfId": 3,
						"strUdfName": "farenheit2Celsius",
						"strMainClassName": "Conversion",
						"strReturnType": "boolean",
						"strUdfDesc": "Convert decimal to integer",
						"strServerUrl": "<server ip>/tmp"
					}]
				}*/



			return res;
		});
	};

	//Function to get all existing category names with id
	this.getExistingCategoryNames = function (tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getAllCategoryConfig,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	//Function to get all existing connections
	this.getExistingKPINames = function (tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getAllKPIConfig,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	//List with IDs

	//Function to get all existing source details based on ID
	this.getExistingSourcebyId = function (sourceId, tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getSourceConfig,
			params : sourceId,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
//	Function to get all existing process details based on ID
	this.getExistingProcessbyId = function (processId, tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getProcessConfig,
			params : processId,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	//Function to get all existing process details based on ID
	this.getExistingSinkbyId = function (sinkId, tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getSinkConfig,
			params : sinkId,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});

	};
	//Function to get all existing udf details based on ID
	this.getExistingUdfbyId = function (ufId, tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getUdfConfig,
			params : ufId,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});

	};

	//Function to get all existing process details based on ID
	this.getExistingCategorybyId = function (categoryId, tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getCategoryConfig,
			params : categoryId,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	//Function to get all existing kpi details based on ID
	this.getExistingKPIbyId = function (kpiId, tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getKPIConfig,
			params : kpiId,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	this.saveSourceConfig =  function (sourceData, tokenValue) {
		console.log(serviceURL.saveSourceConfig);
		return $http({
			method : 'POST',
			//url:"http://10.185.51.82:8080/dss-api/rest/db/sso",
			url : serviceURL.saveSourceConfig,
			data : sourceData,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	this.saveProcessConfig =  function (sourceData, tokenValue) {
		return $http({
			method : 'POST',
			url : serviceURL.saveProcessConfig,
			data : sourceData,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});

	};

	this.saveSinkConfig =  function (sourceData, tokenValue) {
		return $http({
			method : 'POST',
			url : serviceURL.saveSinkConfig,
			data : sourceData,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	this.saveUdfConfig =  function (action, udfData, tokenValue) {
		return $http({
			method : 'POST',
			url : serviceURL.saveUdfConfig,
			data : udfData,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			/*res={
					"success": true,
					"root": {
						"statusCode": "11001",
						"statusMessage": "Udf Saved Successfully"
					}
				}*/
			return res;
		});
	};
	/*this.saveUdfConfig =  function (action, udfData, tokenValue, jarFile) {
		var fd = new FormData();
		var uploadUrl = serviceURL.saveUdfConfig;
    	if(action == "new"){
    	   fd.append('inUfId',0);
	       fd.append('uploadedFile', jarFile);
	       fd.append('uploadedFileName', jarFile);
	       fd.append('strUfName', document.getElementById("functionName").value);
	       fd.append('strUfConfigName', document.getElementById("configurationName").value);
	       fd.append('strMainClassName', document.getElementById("className").value);
	       fd.append('strUfDesc', document.getElementById("description").value);
       }
       else if(action == "update"){
    	   fd.append('inUfId',udfData.inUfId);
	       fd.append('uploadedFile', jarFile);
	       fd.append('uploadedFileName', jarFile);
	       fd.append('strUfName', udfData.strUfName);
	       fd.append('strUfConfigName', udfData.strUfConfigName);
	       fd.append('strMainClassName', udfData.strMainClassName);
	       fd.append('strUfDesc', udfData.strUfDesc);
       }
		return $http.post(uploadUrl, fd, {
	          transformRequest: angular.identity,
	          headers: {'Content-Type': undefined, 'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			res={
					"success": true,
					"root": {
						"statusCode": "11001",
						"statusMessage": "Udf Saved Successfully"
					}
				}
			return res;
		});
	};*/

	this.saveCategoryConfig =  function (sourceData, tokenValue) {
		return $http({
			method : 'POST',
			url : serviceURL.saveCategoryConfig,
			data : sourceData,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	this.saveKPIConfig =  function (sourceData, tokenValue) {
		return $http({
			method : 'POST',
			url : serviceURL.saveKPIConfig,
			data : sourceData,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	//Function to delete selected configuration
	this.deleteSource = function (id, tokenValue) {
		console.log(serviceURL.deleteSource);
		return $http({
			method : 'POST',
			url : serviceURL.deleteSource + "/" + id,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	//Function to delete selected configuration
	this.deleteProcess = function (id, tokenValue) {
		console.log(serviceURL.deleteProcess);
		return $http({
			method : 'POST',
			url : serviceURL.deleteProcess + "/" + id,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	//Function to delete selected configuration
	this.deleteSink = function (id, tokenValue) {
		console.log(serviceURL.deleteSink);
		return $http({
			method : 'POST',
			url : serviceURL.deleteSink + "/" + id,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	//Function to delete selected configuration
	this.deleteUdf = function (udfId, tokenValue) {
		console.log(serviceURL.deleteUdf);
		return $http({
			method : 'POST',
			url : serviceURL.deleteUdf + "/" + udfId,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				/*res={
						"success": true,
						"root": {
							"statusCode": "11003",
							"statusMessage": "Udf Deleted Successfully"
						}
					}*/
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			/*res={
					"success": true,
					"root": {
						"statusCode": "11004",
						"statusMessage": "Udf Delete Failed"
					}
				}*/
			return res;
		});
	};
	//Function to delete selected configuration
	this.deleteCategory = function (id, tokenValue) {
		console.log(serviceURL.deleteCategory);
		return $http({
			method : 'POST',
			url : serviceURL.deleteCategory + "/" + id,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};
	//Function to delete selected configuration
	this.deleteKPI = function (id, tokenValue) {
		console.log(serviceURL.deleteKPI);
		return $http({
			method : 'POST',
			url : serviceURL.deleteKPI + "/" + id,
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	};

	this.testFlumeConnection =  function (host, port, tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.testFlumeConnection  + "?Host=" + host + "&Port=" + port,
			/*params : host, port,*/
			headers: {'token': tokenValue}
		}).then(function (response) {
			if (typeof response.data === 'object') {
		        return response.data;
			} else {
				var res = {};
				res.errors = {};
				res.success = false;
				res.errors.errorCode = "";
				res.errors.errorMessage = msg.msgExistingConnectionError;
				return res;
			}
		}, function (response) {
			var res = {};
			res.errors = {};
			res.success = false;
			res.errors.errorCode = "";
			res.errors.errorMessage = msg.msgExistingConnectionError;
			return res;
		});
	}
});