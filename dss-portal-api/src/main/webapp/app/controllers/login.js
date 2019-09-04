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
 * Description : Main controller for portal
 */

loginapp.controller("LoginCtrl", ['$scope', '$rootScope','$route', '$http','$templateCache','uiGridConstants','$filter','loginService',function($scope, $rootScope,$route,$http,$templateCache,$uiGridConstants,$filter,loginService){
	
	$scope.userLogin = function(){
		var userObj  = {};
		userObj.name = $scope.userName;
		userObj.password = $scope.password;
		return loginService.login(userObj).then(
				function(data) {
					if (data.success == true) {
						$scope.login = data.root;//response.data.root.userSession.uuid
						var loginObject = $scope.login;
						if($scope.login.statusCode == '3008'){
							alert($scope.login.statusMessage);
						}else if($scope.login.statusCode == '3009'){
							alert($scope.login.statusMessage);
						}else if($scope.login.statusCode == '3010'){
							localStorage.setItem('loginObject', JSON.stringify(loginObject));
							localStorage.setItem('loginToken', $scope.login.userSession.token);
							$scope.homePage(); 
						}
						console.log(JSON.stringify($scope.login));
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
					}
				});
	}
	
	$scope.authenticateUser = function(){
		var token = localStorage.getItem('loginToken');
		return loginService.authenticate(token).then(
				function(data) {
					if (data.success == true) {
						$scope.authenticate = data.root;
						var authenticate = $scope.authenticate;
						if(authenticate.statusCode == '3008'){
							$scope.userLogin();
						}else if(authenticate.statusCode == '3009'){
							$scope.homePage();
						}
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
					}
				});
	}
	
	$scope.homePage = function(){
		window.location = 'portal-home.html';
	}
	
}])