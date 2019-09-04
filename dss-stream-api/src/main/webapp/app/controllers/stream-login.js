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
 * Description : Controller for Stream Connect login screen.
 */

loginApp.controller("LoginCtrl", ['$scope', '$rootScope','$http','$filter','loginService','$timeout','$location', 
                            function($scope, $rootScope,$http,$filter,loginService,$timeout,$location){

	
	/**
	 *  Method to authenticate user.
	 */
	/*$scope.authenticateUser = function(){
		var token = localStorage.getItem('loginToken');
		return loginService.authenticate(token).then(
				function(data) {
					if (data.success == true) {
						$scope.login = data.root;
						if($scope.login.statusCode == '3008'){
							alert($scope.login.statusMessage);
						}else if($scope.login.statusCode == '3009'){
							alert($scope.login.statusMessage);
						}
						console.log(JSON.stringify($scope.login));
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
					}
				});
	}*/
	//$scope.authenticateUser();
	
	
	
	/**
	 *  Method to show confirmation dialog for login
	 */
	
	$scope.openConfirmLogin = function() {
		ngDialog.openConfirm({
			template : 'modalDialogLogin',
			className : 'ngdialog-theme-default',
			showClose: false
		}).then(
				function(value) {
					//callback();
					window.location = 'login.html';	
				},
				function(reason) {
					console.log('Modal promise rejected. Reason: ', reason);
				});
	};
	/**
	 *  Method invoked when user clicks on Login button.
	 */
	$scope.userLogin = function(){
		console.log("userLogin");
		var userObj  = {};
		userObj.name = $scope.userName;
		userObj.password = $scope.password;
		return loginService.login(userObj).then(
				function(data) {
					if (data.success == true) {
						$scope.login = data.root;//response.data.root.userSession.uuid
						if($scope.login.statusCode=="3006"){
							$scope.loginError = msg.msgLoginError;
						}/*else if($scope.login.statusCode == '3008'){
							alert($scope.login.statusMessage);
						}*/ // user not in session
						else if($scope.login.statusCode == '3009'){
							localStorage.setItem('loginObject', JSON.stringify(loginObject));
							localStorage.setItem('loginToken', $scope.login.userSession.token);
							window.location = 'index.html';
						}else if($scope.login.statusCode == '3010'){
							var loginObject = $scope.login;
							localStorage.setItem('loginObject', JSON.stringify(loginObject));
							localStorage.setItem('loginToken', $scope.login.userSession.token);
							 window.location = 'index.html';
							
						}
						console.log(JSON.stringify($scope.login));
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						$scope.loginError = msg.msgLoginError;
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
							window.location = 'index.html';
						}
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
					}
				});
	}

}]);
