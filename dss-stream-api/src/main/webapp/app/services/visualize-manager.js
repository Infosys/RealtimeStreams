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
 * Description : Services in Visualization Manager Page.
 */

app.service('visualizeManagerService', function($http, $q) {
	
	//Function to get all existing Portals
	this.getExistingPortalList = function(tokenValue) {
		console.log(serviceURL.getPortalList);
		return $http({
			method : 'GET',
			url : serviceURL.getPortalList,
			headers: {'token': tokenValue} 
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
	
	this.getExistingDashboardList = function(portalId,tokenValue) {
		console.log(serviceURL.getDashboardDetails);
		return $http({
			method : 'GET',
			url : serviceURL.getDashboardList+ "?portalId=" + portalId,
			headers: {'token': tokenValue} 
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
			/*response.data = {
					   "success": true,
					   "root":    [
						            {
						            	"dashboardId": 1,
							            "strDashboardlName": "DashboardName_1",
							            "portalId ": 1,
							            "strCreatedUser": "User_1",
							            "strUpdatedUser": "User_2",
										"createdDate": 1506692634000,
										"updatedDate": 1506692634000,
										"deleteStatus": 0,
										"visualizations": [{
											"id": 1,
											"inCategoryId": 2,
											"inKpiId": 2,
											"inVisualizeId": 21,
											"strKpiName": "SorterScanKPI",
											"strCategoryName": "BPostBaggage",
											"strVisualizeName": "En1"
										},
										{
											"id": 1,
											"inCategoryId": 2,
											"inKpiId": 2,
											"inVisualizeId": 21,
											"strKpiName": "SorterScanKPI",
											"strCategoryName": "BPostBaggage",
											"strVisualizeName": "En1"
										}]
						            },{
						            	"dashboardId": 2,
							            "strDashboardlName": "DashboardName_2",
							            "portalId ": 1,
							            "strCreatedUser": "User_1",
							            "strUpdatedUser": "User_2",
										"createdDate": 1506692634000,
										"updatedDate": 1506692634000,
										"deleteStatus": 0,
										"visualizations": [{
											"id": 1,
											"inCategoryId": 2,
											"inKpiId": 2,
											"inVisualizeId": 21,
											"strKpiName": "SorterScanKPI",
											"strCategoryName": "BPostBaggage",
											"strVisualizeName": "En1"
										}]
						            }
					            ]	
					}
				return response.data;*/
		});
	}
	
	this.getPortalDetails = function(portalId,tokenValue) {
		
		return $http({
			method : 'GET',
			url : serviceURL.getPortalDetails+ "?portalId=" + portalId,
			headers: {'token': tokenValue} 
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.execError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.execError;
			return res;
		});
	}
	
	this.addPortalInput = function(newPortalObj,tokenValue) {
		
		return $http({
			method : 'POST',
			url : serviceURL.addPortalInput,
			data : newPortalObj,
			headers: {'token': tokenValue} 
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.execError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.execError;
			return res;
		});
	}
	
	this.getDashboardDetails = function(dashboardId,tokenValue) {
		
		return $http({
			method : 'GET',
			url : serviceURL.getDashboardDetails+ "?dashboardId=" + dashboardId,
			headers: {'token': tokenValue} 
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.execError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.execError;
			return res;
			/*response.data =  {
					"success": true,
					"root": {
						"portalId": 2,
						"createdDate": 1506692634000,
						"updatedDate": 1506692634000,
						"dashboardId": 1,
						"deleteStatus": 1,
						"visualizations": [{
							"id": 1,
							"inCategoryId": 2,
							"inKpiId": 2,
							"inVisualizeId": 21,
							"strKpiName": "SorterScanKPI",
							"strCategoryName": "BPostBaggage",
							"strVisualizeName": "En1"
						},
						{
							"id": 1,
							"inCategoryId": 2,
							"inKpiId": 2,
							"inVisualizeId": 21,
							"strKpiName": "SorterScanKPI",
							"strCategoryName": "BPostBaggage",
							"strVisualizeName": "En1"
						}],
						"strCreatedUser": null,
						"strUpdatedUser": null,
						"strDashboardlName": "d2",
						"strDashboardlDesc" : ""
					}
				}
				return response.data;*/
			
		});
	}
	
	this.addDashboard = function(newDashboardObj,tokenValue) {
		
		return $http({
			method : 'POST',
			url : serviceURL.addDashboard,
			data: newDashboardObj,
			headers: {'token': tokenValue} 
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.execError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.execError;
			return res;
		});
	}
	
	this.deletePortalDetails = function(portalId,tokenValue) {
		
		return $http({
			method : 'POST',
			url : serviceURL.deletePortalDetails+ "?portalId=" + portalId,
			headers: {'token': tokenValue} 
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.execError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.execError;
			return res;
		});
	}
	
	this.deleteDashboardDetails = function(dashboardId,tokenValue) {
		
		return $http({
			method : 'POST',
			url : serviceURL.deleteDashboardDetails+ "?dashboardId=" + dashboardId,
			headers: {'token': tokenValue} 
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.execError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.execError;
			return res;
		});
	}
	
	
	this.getExistingCategoriesList = function(tokenValue) {
		
		return $http({
			method : 'GET',
			url : serviceURL.getExistingCategoriesList,
			headers: {'token': tokenValue} 
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.execError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.execError;
			return res;
				/*response.data = {
						"success": true,
						"root": [{
							"inCategoryId": 1,
							"kpis": [{
								"inKpiId": 1,
								"strKpiDesc": null,
								"categories": null,
								"strKpiName": "KPISample1",
								"visualizations": null
							}],
							"strCategoryName": "CategorySample1Ed"
						}, {
							"inCategoryId": 2,
							"kpis": [{
								"inKpiId": 2,
								"strKpiDesc": null,
								"categories": null,
								"strKpiName": "SorterScanKPI",
								"visualizations": [{
									"strKeySpace": null,
									"kpiList": null,
									"inVisualizeId": 21,
									"inVisualizeEntityId": 0,
									"strVisualizeName": "En1",
									"strVisualizeParentType": null,
									"strVisualizeConfigDetails": null,
									"strVisualizeSubType": null,
									"strVisualizeDesc": null
								}]
							}],
							"strCategoryName": "BPostBaggage"
						}, {
							"inCategoryId": 3,
							"kpis": [{
								"inKpiId": 3,
								"strKpiDesc": null,
								"categories": null,
								"strKpiName": "Throughput PSM",
								"visualizations": [{
									"strKeySpace": null,
									"kpiList": null,
									"inVisualizeId": 18,
									"inVisualizeEntityId": 0,
									"strVisualizeName": "Visualize_Sorter_Scan",
									"strVisualizeParentType": null,
									"strVisualizeConfigDetails": null,
									"strVisualizeSubType": null,
									"strVisualizeDesc": null
								}]
							}],
							"strCategoryName": "Sorter Scan Analysis"
						}]
					}

				return response.data;*/
		});
	}
});

