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
 * Description : Login Services .
 */

loginapp.service('loginService', function($http, $q) {
	
	this.login = function(userObj) {
		console.log(serviceURL.login);
		return $http({
			method : 'POST',
			headers: {'Content-Type': 'application/x-www-form-urlencoded','token': ''},
			url : serviceURL.login,
			data: $.param({userName: userObj.name, password: userObj.password})
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
	
	this.authenticate = function(tokenValue) {
		
		return $http({
			method : 'GET',
			headers: {'token': tokenValue},
			url : serviceURL.authenticate
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