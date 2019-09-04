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
 * Description : Controller for Logistics DSS.
 */
//initialize instance for Home Page
var enjoyhint_instance, enjoyhint_script_steps;
app.controller("MainCtrl", ['$scope','$rootScope','ngDialog','$route', '$http','$templateCache','uiGridConstants','$filter','mainService','accessService', function($scope, $rootScope,ngDialog,$route,$http,$templateCache,$uiGridConstants,$filter,mainService,accessService){
	this.$route = $route;
	var retrievedObject = localStorage.getItem('loginObject');//loginToken
	retrievedObject = JSON.parse(retrievedObject);
	console.log(retrievedObject);
	
	
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
	/*TODO: validate if retrievedObject is null*/
	if(!retrievedObject){
		$scope.openConfirmLogin();
	}
	else{
	if(retrievedObject.userSession.userName){
		$rootScope.userName = retrievedObject.userSession.userName;
	}else{
		$rootScope.userName = "";
	}
	}
	console.log(localStorage.getItem('loginToken'));
	
	
	$scope.getAccessDetailsMain = function(selectedRole){
		var obj = {};
		obj.inRoleId = selectedRole.inRoleId;
		var token = localStorage.getItem('loginToken');
		//$rootScope.modulesMain ={"pipeline" : false,"configuration" : false,"execution" : false,"visualization" : false,"studio" : false,"ml" : true,"access" : false,"wizard" : false};
		$rootScope.modulesMain = {
				"pipeline": {
					"showBlock": false,
					"viewEnabled": false,
					"editEnabled": false,
					"deleteEnabled": false

				},
				"configuration": {
					"showBlock": false,
					"viewEnabled": false,
					"editEnabled": false,
					"deleteEnabled": false

				},
				"execution": {
					"showBlock": false,
					"viewEnabled": false,
					"editEnabled": false,
					"deleteEnabled": false

				},
				"visualization": {
					"showBlock": false,
					"viewEnabled": false,
					"editEnabled": false,
					"deleteEnabled": false

				},
				"studio": {
					"showBlock": false,
					"viewEnabled": false,
					"editEnabled": false,
					"deleteEnabled": false

				},
				"ml": {
					"showBlock": false,
					"viewEnabled": false,
					"editEnabled": false,
					"deleteEnabled": false

				},
				"access": {
					"showBlock": false,
					"viewEnabled": false,
					"editEnabled": false,
					"deleteEnabled": false

				},
				"wizard": {
					"showBlock": false,
					"viewEnabled": false,
					"editEnabled": false,
					"deleteEnabled": false

				}

		}
		accessService.getAllPortalDetailsforRole(obj,token).then(
				function(data) {
					if (data.success == true) {			
						$rootScope.assessDetailsMain = data.root;
						console.log(JSON.stringify($rootScope.assessDetailsMain));					
						if($rootScope.assessDetailsMain.accessLevel.featureAccesses){
							angular.forEach($rootScope.assessDetailsMain.accessLevel.featureAccesses, function(val, k) {
								if(angular.lowercase(val.strFeatureName) == "pipeline"){
									if(val.featureViewEnabled || val.featureDeleteEnabled || val.featureEditEnabled){
										$rootScope.modulesMain.pipeline.showBlock = true;
									}
									$rootScope.modulesMain.pipeline.viewEnabled = val.featureViewEnabled;
									$rootScope.modulesMain.pipeline.editEnabled = val.featureEditEnabled;
									$rootScope.modulesMain.pipeline.deleteEnabled = val.featureDeleteEnabled;
								}else if(angular.lowercase(val.strFeatureName) == "configuration"){
									if(val.featureViewEnabled || val.featureDeleteEnabled || val.featureEditEnabled){
										$rootScope.modulesMain.configuration.showBlock = true;
									}
									$rootScope.modulesMain.configuration.viewEnabled = val.featureViewEnabled;
									$rootScope.modulesMain.configuration.editEnabled = val.featureEditEnabled;
									$rootScope.modulesMain.configuration.deleteEnabled = val.featureDeleteEnabled;
								}else if(angular.lowercase(val.strFeatureName) == "execution"){
									if(val.featureViewEnabled || val.featureDeleteEnabled || val.featureEditEnabled){
										$rootScope.modulesMain.execution.showBlock = true;
									}
									$rootScope.modulesMain.execution.viewEnabled = val.featureViewEnabled;
									$rootScope.modulesMain.execution.editEnabled = val.featureEditEnabled;
									$rootScope.modulesMain.execution.deleteEnabled = val.featureDeleteEnabled;									
								}else if(angular.lowercase(val.strFeatureName) == "visualization"){
									if(val.featureViewEnabled || val.featureDeleteEnabled || val.featureEditEnabled){
										$rootScope.modulesMain.visualization.showBlock = true;
									}
									$rootScope.modulesMain.visualization.viewEnabled = val.featureViewEnabled;
									$rootScope.modulesMain.visualization.editEnabled = val.featureEditEnabled;
									$rootScope.modulesMain.visualization.deleteEnabled = val.featureDeleteEnabled;									
								}else if(angular.lowercase(val.strFeatureName) == "stream studio"){
									if(val.featureViewEnabled || val.featureDeleteEnabled || val.featureEditEnabled){
										$rootScope.modulesMain.studio.showBlock = true;
									}
									$rootScope.modulesMain.studio.viewEnabled = val.featureViewEnabled;
									$rootScope.modulesMain.studio.editEnabled = val.featureEditEnabled;
									$rootScope.modulesMain.studio.deleteEnabled = val.featureDeleteEnabled;									
								}else if(angular.lowercase(val.strFeatureName) == "stream analytics"){
									if(val.featureViewEnabled || val.featureDeleteEnabled || val.featureEditEnabled){
										$rootScope.modulesMain.ml.showBlock = true;
									}
									$rootScope.modulesMain.ml.viewEnabled = val.featureViewEnabled;
									$rootScope.modulesMain.ml.editEnabled = val.featureEditEnabled;
									$rootScope.modulesMain.ml.deleteEnabled = val.featureDeleteEnabled;									
								}else if(angular.lowercase(val.strFeatureName) == "user access manager"){
									if(val.featureViewEnabled || val.featureDeleteEnabled || val.featureEditEnabled){
										$rootScope.modulesMain.access.showBlock = true;
									}
									$rootScope.modulesMain.access.viewEnabled = val.featureViewEnabled;
									$rootScope.modulesMain.access.editEnabled = val.featureEditEnabled;
									$rootScope.modulesMain.access.deleteEnabled = val.featureDeleteEnabled;									
								}else if(angular.lowercase(val.strFeatureName) == "operational ui wizard"){
									if(val.featureViewEnabled || val.featureDeleteEnabled || val.featureEditEnabled){
										$rootScope.modulesMain.wizard.showBlock = true;
									}
									$rootScope.modulesMain.wizard.viewEnabled = val.featureViewEnabled;
									$rootScope.modulesMain.wizard.editEnabled = val.featureEditEnabled;
									$rootScope.modulesMain.wizard.deleteEnabled = val.featureDeleteEnabled;									
								}							
							});	
							if ( ($rootScope.modulesMain.pipeline.showBlock) && ($rootScope.modulesMain.configuration.showBlock) && ($rootScope.modulesMain.execution.showBlock) && ($rootScope.modulesMain.visualization.showBlock) ) {
								enjoyhint_instance = new EnjoyHint({});
								enjoyhint_instance.set(enjoyhint_script_steps_all);
								$scope.showGuidedMessage();
							} else if (($rootScope.modulesMain.pipeline.showBlock) && ($rootScope.modulesMain.configuration.showBlock) && ($rootScope.modulesMain.execution.showBlock)) {
								enjoyhint_instance = new EnjoyHint({});
								enjoyhint_instance.set(enjoyhint_script_steps_no_vis);
								$scope.showGuidedMessage();
							}
						}	
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
					}
				});
	}
	if(retrievedObject.userSession.roles  && !$rootScope.selectedMappedRole){
		$rootScope.mappedRoles =[];	
		angular.forEach(retrievedObject.userSession.roles, function(val, k) {
			var roleObj = {};
			roleObj.inRoleId = val.inRoleId;
			roleObj.strRoleDesc = val.strRoleDesc;
			roleObj.strRoleName = val.strRoleName;
			$rootScope.mappedRoles.push(roleObj);
		});
		console.log(JSON.stringify($rootScope.mappedRoles));
		//$rootScope.selectedMappedRole = $rootScope.mappedRoles[0];
		
		
		if(retrievedObject.userSession.defaultRole){
			angular.forEach($rootScope.mappedRoles, function(val, k) {
				if (retrievedObject.userSession.defaultRole === val.inRoleId){
				$rootScope.selectedMappedRole = val;
				}
			});	
		}
		else{
			$rootScope.selectedMappedRole = $rootScope.mappedRoles[0];
		}
		console.log($rootScope.selectedMappedRole);
		$scope.getAccessDetailsMain($rootScope.selectedMappedRole);
		//$rootScope.modulesMain ={"pipeline" : false,"configuration" : false,"execution" : false,"visualization" : false,"studio" : false,"ml" : true,"access" : false,"wizard" : false};


	}
	/*	var roleObj = {};
	roleObj.inRoleId = loginDetails.inRoleId;
	roleObj.tokenValue = token;
	accessService.getAllPortalDetailsforRole(obj).then(
			function(data) {
				if (data.success == true) {			
					$rootScope.mappedRoles = data.root;
				}else {
					var error = data.errors;
					console.log(error.errorMessage);
				}
			});*/
	$scope.roleChange = function(mappedRole){
		$rootScope.selectedMappedRole = mappedRole;
		$scope.getAccessDetailsMain(mappedRole);
	}
	
	/**
	 *  Method to show confirmation dialog for login .
	 */
	/*$rootScope.openConfirmLogin = function() {
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
	*/

	$scope.userLogout = function(){
		console.log("userLogout");
		var token = localStorage.getItem('loginToken');
		return mainService.logout(token).then(
				function(data) {
					if (data.success == true) {
						$scope.login = data.root;
						if($scope.login.statusCode == '3008'){
							window.location = 'login.html';
							localStorage.removeItem('loginToken'); 
							localStorage.removeItem('loginObject'); 
						}else if($scope.login.statusCode == '3012'){
							//alert($scope.login.statusMessage);
							ngDialog.open({
								template: $scope.login.statusMessage,
								plain: true,
								className: 'ngdialog-theme-error'
							})
						}else if($scope.login.statusCode == '3011'){
							window.location = 'login.html';
							localStorage.removeItem('loginToken'); 
							localStorage.removeItem('loginObject'); 
						}
						console.log(JSON.stringify($scope.login));
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						ngDialog.open({
							template: $scope.login.statusMessage,
							plain: true,
							className: 'ngdialog-theme-error'
						})
					}
				});
	}
	//Logic to show guided messages in the respective pages using enjoyhint. 
	$scope.showGuidedMessage = function () {
		//$scope.$on('$viewContentLoaded', function(){
			$scope.loadedHTML= $route.current.templateUrl;
			if($scope.loadedHTML.indexOf("home.html") != -1){
				if ($rootScope.mappedRoles) {
					enjoyhint_instance.run();
				}
			}
		//});
	};
}]);