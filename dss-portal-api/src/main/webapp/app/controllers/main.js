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

app.controller("MainCtrl", ['$scope', '$rootScope','$route', '$http','$templateCache','uiGridConstants','$filter','mainService','$sce','mainFactory','ngDialog',function($scope, $rootScope,$route,$http,$templateCache,$uiGridConstants,$filter,mainService,$sce,mainFactory,ngDialog){
	var loginData = localStorage.getItem('loginObject');
	$scope.openConfirmLogin = function() {
		ngDialog.openConfirm({
			template : 'modalDialogLogin',
			className : 'ngdialog-theme-default'
		}).then(
				function(value) {
					//callback();
					window.location = 'login.html';	
				},
				function(reason) {
					console.log('Modal promise rejected. Reason: ', reason);
				});
	};	
	
	$scope.getRoleList = function(){
		var loginData = localStorage.getItem('loginObject');
		loginData = JSON.parse(loginData);
		$scope.userName = loginData.userSession.userName
		$scope.roles = loginData.userSession.roles;
		var defaultRole = $scope.roles[0];
		$scope.roleChange(defaultRole);
	}
	
	$scope.roleChange = function(role){
		$scope.selectedRole = role.strRoleName;
		$scope.selectedRoleId = role.inRoleId;
		var token = localStorage.getItem('loginToken');
		return mainService.getExistingPortalList($scope.selectedRoleId,token).then(
				function(data) {
					if (data.success == true) {
						$scope.existingPortals = data.root;
						 angular.forEach($scope.existingPortals, function(val){
							 var d = new Date(val.createdDate);
							 val.createdDate  = d.toDateString();
						 });
						console.log(JSON.stringify($scope.existingPortals));
					} else {
						var error = data.errors;
						$scope.existingPortals = "";
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
					}
				});
	}
	
	if(!loginData){
		$scope.openConfirmLogin();
	}
	else{
		$scope.getRoleList();
	}
	
	
	$scope.viewPortal = function(portalId,roleId){
		var portalId = portalId;
		var roleId = roleId;
		var token = localStorage.getItem('loginToken');
		if(token){
			localStorage.setItem('portalId', portalId);
			localStorage.setItem('roleId', roleId);
			localStorage.setItem('roleName', $scope.selectedRole);
			window.open('index.html','_blank');
		}else{
			$scope.openConfirmLogin();
		}
	}
	
	$scope.logout = function(){
		var token = localStorage.getItem('loginToken');
		return mainService.logout(token).then(
				function(data) {
					if (data.success == true) {
						$scope.logout = data.root;//response.data.root.userSession.uuid						
						if($scope.logout.statusCode == '3008'){
							alert($scope.logout.statusMessage);
						}else if($scope.logout.statusCode == '3012'){
							alert($scope.logout.statusMessage);
						}else if($scope.logout.statusCode == '3011'){
							 window.location = 'login.html';
							 localStorage.removeItem('loginToken');
							 var token = localStorage.getItem('loginToken');
						}
						console.log(JSON.stringify($scope.logout));
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
					}
				});
	}
	
	
	
}]);