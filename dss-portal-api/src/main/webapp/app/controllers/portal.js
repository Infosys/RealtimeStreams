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

app.controller("PortalCtrl", ['$scope', '$rootScope','$route', '$http','$templateCache','uiGridConstants','$filter','mainService','$sce','mainFactory','ngDialog',function($scope, $rootScope,$route,$http,$templateCache,$uiGridConstants,$filter,mainService,$sce,mainFactory,ngDialog){
	
	$scope.dashboardNameLogo = false;
	$scope.portalSelected = false;	
	$scope.retrievedportalId = localStorage.getItem('portalId');
	$scope.retrievedroleId = localStorage.getItem('roleId');
	$scope.selectedRole = localStorage.getItem('roleName');
	

	$scope.getPortalDetails = function(portalID){
		var token = localStorage.getItem('loginToken');
		return mainService.getPortalDetails(portalID,token).then(
				function(data) {
					if (data.success == true) {
						$scope.portalRecord = data.root;
						$scope.portalName = $scope.portalRecord.strPortalName;
						$scope.cssFile = "";
						$scope.logoFile = "";
						$scope.cssFile.name = $scope.portalRecord.strPortalCss;
						$scope.logoFile.name = $scope.portalRecord.strPortalLogo;						
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
					}
				});
	}
	
	$scope.getDashboardList = function(portalId){
		/*var toggleValue = document.getElementById("dashboardToggle");*/
		var roleId = $scope.retrievedroleId
		var token = localStorage.getItem('loginToken');
		return mainService.getExistingDashboardList(portalId,roleId,token).then(
				function(data) {
					if (data.success == true) {
						$scope.dashboardsList = data.root;
						console.log($scope.dashboardsList)
						$scope.existingDashboards = data.root;
						console.log($scope.existingDashboards);
						 angular.forEach($scope.existingDashboards, function(val){
							 var d = new Date(val.createdDate);
							 val.createdDate  = d.toDateString();
						 });
						console.log(JSON.stringify($scope.existingDashboards));
						var id = $scope.existingDashboards[0].dashboardId;
						var name = $scope.existingDashboards[0].strDashboardlName;
						$scope.dashboardClick(id,name);
					} else {
						var error = data.errors;
						$scope.existingDashboards = "";
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
					}
				});
	}
	
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
	if(!loginData){
		$scope.openConfirmLogin();
	}
	else{
		$scope.loginObject = localStorage.getItem('loginObject');
		$scope.loginObject = JSON.parse($scope.loginObject);
		$scope.userName = $scope.loginObject.userSession.userName;
		$scope.getDashboardList($scope.retrievedportalId);
		$scope.getPortalDetails($scope.retrievedportalId);
		
	}
	
	/*$scope.$on("CallMethod", function (event, args) {
		var portalId = args.portalId;
		$scope.getDashboardList(portalId);
	});*/
	
	/*$rootScope.$on("CallMethod", function(event, args){
        $scope.getDashboardList(portalId);
	});*/
	
	
	

	
	/*$scope.getPortalId = function(){
		var portalID = mainFactory.getPortalValue();
		$scope.getDashboardList(portalID);
	};*/
	
	
	$scope.dashboardClick = function(dashboardId,dashboardName){
		var token = localStorage.getItem('loginToken');
		var roleId = $scope.retrievedroleId
		$scope.dashboardName = dashboardName;
		$scope.dashboardNameLogo = true;
		$scope.dashboardNameClass = "active";
		$scope.activeDashboard = dashboardId;
		
		return mainService.getExistingCategoriesList(dashboardId,roleId,token).then(
				function(data) {
					if (data.success == true) {
						$scope.existingCategoriesList = data.root;
						console.log(JSON.stringify($scope.existingCategories));
						/*var kpisList = $scope.existingCategoriesList[0].kpis;
						var visList = kpisList[0].visualizations;
						var vis = visList[0];
						$scope.viewVisualization(visList)*/
						$scope.visualizationDisplayList = [];
						angular.forEach($scope.existingCategoriesList, function(val){
							if(val.kpis){
								angular.forEach(val.kpis, function(val_1){
									if(val_1.visualizations){
										angular.forEach(val_1.visualizations, function(val_2){
											val_2.selected=true;
											$scope.viewVisualization(val_2);
										})
									}
								})
							}
						})
					} else {
						var error = data.errors;
						$scope.existingCategories = "";
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
					}
				});
	}
	
	$scope.viewVisualization = function(vis){
		/*$scope.activeMenu = true;*/
		if(vis.selected == true){
			var visualize = JSON.parse(vis.strVisualizeConfigDetails)
			$scope.graphUrl =  visualize.strSinkQuery;
			$scope.visualizationName = vis.strVisualizeName; 
			var graphObj = {
					"id"  : vis.inVisualizeId,
					"name" : $scope.visualizationName,
					"url" : $scope.graphUrl
			}
			if($scope.visualizationDisplayList.length < 4){
				$scope.visualizationDisplayList.push(graphObj);
			}
		}
		else if(vis.selected == false){
			var visualize = JSON.parse(vis.strVisualizeConfigDetails)
			$scope.graphUrl =  visualize.strSinkQuery;
			$scope.visualizationName = vis.strVisualizeName; 
			var index = -1;
			$scope.visualizationDisplayList.some(function(obj, i) {
			  return obj.id === vis.inVisualizeId ? index = i : false;
			});			
			$scope.visualizationDisplayList.splice(index,1);
		}
	}
	
	$scope.trustSrc = function(src) {
		return $sce.trustAsResourceUrl(src);
	};
	
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