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
 * Description : Main Services .
 */

app.service('mainService', function($http, $q) {
	
	this.getAccessListForRole = function(tokenValue,roleObj) {
		return $http({
			method : 'GET',
			headers: {'token': tokenValue},
			url : serviceURL.getAccessListForRole,
			params : roleObj
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
	
	this.getExistingDashboardList = function(portalId,roleId,tokenValue) {
		console.log(serviceURL.getDashboardDetails);
		return $http({
			method : 'GET',
			url : serviceURL.getDashboardList+ "?portalId=" + portalId+ "&inRoleId="+ roleId,
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
	
	
this.getExistingCategoriesList = function(dashboardId,roleId,tokenValue) {
		return $http({
			method : 'GET',
			url : serviceURL.getExistingCategoriesList+ "?dashboardId=" + dashboardId+ "&inRoleId="+ roleId,
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

	this.getExistingPortalList = function(roleId,tokenValue) {
		console.log(serviceURL.getPortalList);
		return $http({
			method : 'GET',
			url : serviceURL.getPortalList+ "?inRoleId=" + roleId,
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
	
	this.logout = function(tokenValue) {
		console.log(serviceURL.login);
		return $http({
			method : 'POST',
			headers: {'token': tokenValue},
			url : serviceURL.logout
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