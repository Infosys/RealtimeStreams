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
 * Description : Controller for Visualization Manager screen.
 */

app.controller("visualizeManagerCtrl", ['$scope', '$rootScope','ngDialog', '$http','$templateCache','uiGridConstants','$filter','ngDialog','$uibModal','visualizeManagerService','$sce','fileUpload','$timeout', function($scope, $rootScope,ngDialog,$http,$templateCache,$uiGridConstants,$filter,ngDialog,$uibModal,visualizeManagerService,$sce,fileUpload,$timeout){	
	
	
	$scope.edited = false;
	$scope.portalSelection = true;
	$scope.categorySelect = true;
	$scope.selectVis = false;
	$scope.visExists = false;
	$scope.getCategoryList = function(){
		var token = localStorage.getItem('loginToken');
		return visualizeManagerService.getExistingCategoriesList(token).then(
				function(data) {
					if (data.success == true) {
						$scope.existingCategoriesList = data.root;
						console.log(JSON.stringify($scope.existingCategories));
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
						$scope.existingCategories = "";
						
					}
				});
	}
	$scope.getPortalList = function(){		
		var token = localStorage.getItem('loginToken');
		return visualizeManagerService.getExistingPortalList(token).then(
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
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
						$scope.existingPortals = "";
						
					}
				});
	}
	console.log(localStorage.getItem('loginToken'));
	if(!$rootScope.modulesMain.studio.showBlock){
		$scope.openConfirmLogin();
	}else{
		// Calling service to list categories and portals.	
		$scope.getCategoryList();
		$scope.getPortalList();
	}
	

	//$scope.getCategoryList();
	
	
	
	
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
	
	

	//$scope.getPortalList();
	
	 $scope.updateSelection = function(position, existingPortals) {
	      angular.forEach(existingPortals, function(portal, index) {
	        if (position != index) 
	        	portal.selected = false;
	        else{
	        	if(portal.selected == true){
		        	$scope.selectedPortalId = portal.portalId;
		        	$scope.selectedPortalName = portal.strPortalName;
		        	$scope.portalSelection = false;
	        	}
	        	else{
	        		$scope.portalSelection = true;
	        	}
	        }
	      });
	    }
	$scope.activeclass = "active";
	$scope.getDashboardList = function(portalId){	
		$scope.activeclass = "";
		$scope.notActiveClass = "active";
		var token = localStorage.getItem('loginToken');
		return visualizeManagerService.getExistingDashboardList(portalId,token).then(
				function(data) {
					if (data.success == true) {
						$scope.dashboardsList = data.root;
						$scope.existingDashboards = data.root;
						 angular.forEach($scope.existingDashboards, function(val){
							 var d = new Date(val.createdDate);
							 val.createdDate  = d.toDateString();
						 });
						console.log(JSON.stringify($scope.existingDashboards));
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
						$scope.existingDashboards = "";
						
					}
				});
	}
	
	$scope.goToPortal = function(){
		$scope.activeclass = "active";
		$scope.notActiveClass = "";
		$scope.portalSelection = true;
		$scope.updateSelection(-1,$scope.existingPortals);
	}
	
	//Logic to open add portal
	$scope.addNewPortal = function(){
		var token = localStorage.getItem('loginToken');
		if(token){
			$scope.saved = false;
			
			$scope.newPortalPopUp = $uibModal.open({
				templateUrl : 'app/views/portal-details.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope
			})
		}else{
			$scope.openConfirmLogin();
		}
	}
	$scope.enableCssUpload = function(){
		$scope.cssFileInput = false;
		$("#currCssName").hide();

		$scope.cssFileInput = !($scope.cssFileInput);
		console.log("$scope.showFileInput : " + $scope.showFileInput);
	}
	
	$scope.enableLogoUpload = function(){
		$scope.logoFileInput = false;
		$("#currLogoName").hide();

		$scope.logoFileInput = !($scope.logoFileInput);
		console.log("$scope.showFileInput : " + $scope.showFileInput);
	}
	
	$scope.savePortalDetails = function(){
        var cssFile = document.getElementById("css").files[0];
        var logoFile = document.getElementById("logo").files[0];
        var uploadUrl = serviceURL.addPortalInput;
        var token = localStorage.getItem('loginToken');
        fileUpload.uploadFileToUrl('new',token,cssFile,logoFile,uploadUrl).then(
				function(data) {
					if (data.success == true) {
						$scope.success= data.success;
						$scope.saveMessage = data.successDetails.successMessage;
						$scope.saved = true;
						$scope.getPortalList();
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
						$scope.saveMessage = error.errorCode +"-"+ error.errorMessage;
						$scope.success = false;
						$scope.saved = true;
						
					}
				});
	}
	
	
	$scope.updatePortalDetails = function(portalRecord){
		var updatePortalObj = {}
		updatePortalObj = {
				"portalId" : portalRecord.portalId,
				"strPortalName" : portalRecord.strPortalName,
				"strPortalTitle" : portalRecord.strPortalTitle,
				"strPortalUrl" : portalRecord.strPortalUrl
		}
		
		var cssFile = document.getElementById("css").files[0];
        var logoFile = document.getElementById("logo").files[0];
        if(!cssFile){
        	cssFile = ""
        }
        if(!logoFile){
        	logoFile = ""
        }
        var uploadUrl = serviceURL.addPortalInput;
        var token = localStorage.getItem('loginToken');
        fileUpload.uploadFileToUrl('update',token,cssFile,logoFile,uploadUrl,updatePortalObj).then(
				function(data) {
					if (data.success == true) {
						$scope.success= data.success;
						$scope.saveMessage = data.successDetails.successMessage;
						$scope.saved = true;
						$scope.getPortalList();
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
						$scope.saveMessage = error.errorCode +"-"+ error.errorMessage ;
						$scope.success = false;
						$scope.saved = true;
						
					}
				});
	}
	
	$scope.cancel = function(popUpInstance) {
		console.log("Close >> "+popUpInstance);
		popUpInstance.close();
	}
	
	$scope.editPortal = function(action,portalID){
		$scope.saved = false;
		$scope.edited = true;
		$scope.cssFileInput = false;
		$scope.logoFileInput = false;
		$scope.portalAction = action;
		var token = localStorage.getItem('loginToken');
		return visualizeManagerService.getPortalDetails(portalID,token).then(
				function(data) {
					if (data.success == true) {
						$scope.portalRecord = data.root;
						$scope.cssFileName = $scope.portalRecord.strPortalCss;
						$scope.logoFileName = $scope.portalRecord.strPortalLogo;
						$scope.editPortalPopUp = $uibModal.open({
							templateUrl : 'app/views/edit-portal-details.html',
							backdrop  : 'static',
							keyboard  : false,
							scope : $scope
						})
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
					}
				});
	}
	
	//Confirmation dialog
	$scope.confirmDeletePortal = function(data1, callback) {
		ngDialog.openConfirm({
			template : 'modalDialogId',
			className : 'ngdialog-theme-default'
		}).then(
				function(value) {
					callback(data1);
				},
				function(reason) {
					console.log('Modal promise rejected. Reason: ', reason);
				});
	};
	
	$scope.deletePortal = function(portalId){
		var token = localStorage.getItem('loginToken');
		return visualizeManagerService.deletePortalDetails(portalId,token).then(
				function(data) {
					if (data.success == true) {
						var dialog = ngDialog.open({
							//template: "Portal deleted successfully",
							template: data.successDetails.successMessage,
							plain: true,
							className: 'ngdialog-theme-success'
						});
						$scope.getPortalList();
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
					}
				});
	}
	
		
	$scope.clearDetails = function(idName,action,record){
		if(action == "new"){
			if(idName == "portal"){
				document.getElementById("nameOfPortal").value = "";
				document.getElementById("portalTitle").value = "";
				document.getElementById("portalURL").value = "";
			}
			else if(idName == "dashboard"){
				document.getElementById("dashboardName").value = "";
				document.getElementById("dashboardDesc").value = "";
				$scope.selectedVisualizationsList = [];
			}
		}
		else if(action == "edit"){
			if(idName == "portal"){
				record.strPortalName = "";
				record.strPortalTitle = "";
				record.strPortalUrl = "";
			}
			else if(idName == "dashboard"){
				record.strDashboardlName = "";
				$scope.selectedVisualizationsList = [];
			}
		}
	}
	
	$scope.addNewDashboard = function(){
		var token = localStorage.getItem('loginToken');
		if(token){
			var portalId = $scope.selectedPortalId;
			$scope.selectedVisualizationsList = [];
			$scope.selectedList = [];
			$scope.flag = 0;
			$scope.saved = false;
			$scope.visExists = false;
			$scope.selectVis = false;
			$scope.emptyCheckboxes();
			$scope.newDashboardPopUp = $uibModal.open({
				templateUrl : 'app/views/dashboard-details.html',
				backdrop  : 'static',
				keyboard  : false,
				scope : $scope,
				resolve: {
					items: function () {
						return portalId
					}
				}
			})
		}else{
			$scope.openConfirmLogin();
		}
	}
	
	$scope.saveDashboard = function(){
		var visList = [];
		visList = $scope.selectedVisualizationsList;
		angular.forEach(visList, function(val){
			if (val.selected){
				delete val.selected;
			}
		});
		var newDashboardObj = {
				"portalId" : $scope.selectedPortalId,
				"dashboardId" : 0,
				"strDashboardlName" : document.getElementById("dashboardName").value,
				"strDashboardDesc" : document.getElementById("dashboardDesc").value,
				"visualizations" : visList
		}
		var token = localStorage.getItem('loginToken');
		visualizeManagerService.addDashboard(newDashboardObj,token).then(
				function(data) {
					if (data.success == true) {
						$scope.success= data.success;
						$scope.saveMessage = data.successDetails.successMessage;
						$scope.saved = true;
						$scope.getDashboardList(newDashboardObj.portalId);
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
						$scope.saveMessage = error.errorCode +"-"+ error.errorMessage;
						$scope.success = false;
						$scope.saved = true;
						
					}
				});
	}
	
	$scope.updateDashboard = function(dashboardRecord){
		var visList = [];
		visList = $scope.selectedVisualizationsList;
		angular.forEach(visList, function(val){
			if (val.selected){
				delete val.selected;
			}
		});
		var newDashboardObj = {
				"portalId" : dashboardRecord.portalId,
				"dashboardId" : dashboardRecord.dashboardId,
				"strDashboardlName" : dashboardRecord.strDashboardlName,
				"strDashboardDesc" : dashboardRecord.strDashboardDesc,
				"visualizations" : visList
		}
		var token = localStorage.getItem('loginToken');
		visualizeManagerService.addDashboard(newDashboardObj,token).then(
				function(data) {
					if (data.success == true) {
						$scope.success= data.success;
						$scope.saveMessage = data.successDetails.successMessage;
						$scope.saved = true;
						$scope.getDashboardList(newDashboardObj.portalId);
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
						$scope.saveMessage = error.errorCode +"-"+ error.errorMessage;
						$scope.success = false;
						$scope.saved = true;
						
					}
				});
	}
	
	$scope.confirmDeleteDashboard = function(data1, callback) {
		ngDialog.openConfirm({
			template : 'modalDialogId',
			className : 'ngdialog-theme-default'
		}).then(
				function(value) {
					callback(data1);
				},
				function(reason) {
					console.log('Modal promise rejected. Reason: ', reason);
				});
	};
	
	$scope.deleteDashboard = function(dashboardId){
		var token = localStorage.getItem('loginToken');
		return visualizeManagerService.deleteDashboardDetails(dashboardId,token).then(
				function(data) {
					if (data.success == true) {
						var dialog = ngDialog.open({
							//template: "Dashboard deleted successfully",
							template: data.successDetails.successMessage,
							plain: true,
							className: 'ngdialog-theme-success'
						});
						$scope.getDashboardList($scope.selectedPortalId);
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
					}
				});
	}
	
	$scope.editDashboard = function(action,dashboardId){
		$scope.saved = false;
		$scope.edited = true;
		$scope.visExists = false;
		$scope.selectVis = false;
		$scope.dashboardAction = action;
		var token = localStorage.getItem('loginToken');
		return visualizeManagerService.getDashboardDetails(dashboardId,token).then(
				function(data) {
					if (data.success == true) {
						$scope.dashboardRecord = data.root;
						$scope.selectedVisualizationsList  = [];
						$scope.selectedList = [];
						$scope.flag = 0;
						if($scope.dashboardRecord.visualizations){
							$scope.selectedVisualizationsList = $scope.dashboardRecord.visualizations;
							$scope.emptyCheckboxes();
							$scope.updateCheckboxes();
						}
						else{
							$scope.emptyCheckboxes();
						}	
						$scope.editDashboardPopUp = $uibModal.open({
							templateUrl : 'app/views/edit-dashboard-details.html',
							backdrop  : 'static',
							keyboard  : false,
							scope : $scope
						})
					} else {
						var error = data.errors;
						console.log(error.errorMessage);
						if(error.errorCode=="3006"){
							$scope.openConfirmLogin();
						}
					}
				});
	}
	
	$scope.emptyCheckboxes = function(){
		angular.forEach($scope.existingCategoriesList, function(val){
			if(val.kpis){
				angular.forEach(val.kpis, function(val_1){
					if(val_1.visualizations){
						angular.forEach(val_1.visualizations, function(val_2){
							val_2.selected=false;
						})
					}
				})
			}
		})
	}
	$scope.updateCheckboxes = function(){
		if($scope.selectedVisualizationsList.length != 0){
			angular.forEach($scope.selectedVisualizationsList, function(listVal, key) {
				var exists = false;
				angular.forEach($scope.existingCategoriesList, function(selectedCat, key) {
					if(angular.equals(listVal.inCategoryId, selectedCat.inCategoryId)){ 
						angular.forEach(selectedCat.kpis, function(selectedKpi, key){
							if(angular.equals(listVal.inKpiId, selectedKpi.inKpiId)){
								angular.forEach(selectedKpi.visualizations, function(selectedVisualization, key){
									if(angular.equals(listVal.inVisualizeId, selectedVisualization.inVisualizeId)){
										selectedVisualization.selected = true;
										$scope.flag++;
									}
								})
							}
						})
					}; 
				});
			});
		}
		else{
			$scope.flag = 0;
		}
	}
	
	$scope.selectedList = [];
	$scope.selectVisualization = function(visualization,kpiIndex,categoryIndex){
		if(visualization.selected == true){
			var selectedObject= {};
			selectedObject.strCategoryName = $scope.existingCategoriesList [categoryIndex].strCategoryName;
			selectedObject.inCategoryId = $scope.existingCategoriesList [categoryIndex].inCategoryId;
			selectedObject.strKpiName = $scope.existingCategoriesList [categoryIndex].kpis[kpiIndex].strKpiName;
			selectedObject.inKpiId = $scope.existingCategoriesList [categoryIndex].kpis[kpiIndex].inKpiId;
			selectedObject.strVisualizeName = visualization.strVisualizeName;
			selectedObject.inVisualizeId = visualization.inVisualizeId;
			$scope.selectedList.push(selectedObject);
			$scope.flag++
		}
		else{
			$scope.flag = $scope.flag - 1;
			angular.forEach($scope.selectedList, function(val,index){
				if(visualization.inVisualizeId == val.inVisualizeId){
					$scope.selectedList.splice(index)
				};
			});
		}
	}
	
	$scope.addselectedVisualization = function(){
		if($scope.selectedList.length != 0){
			angular.forEach($scope.selectedList, function(listVal, key) {
				var exists = false;
				angular.forEach($scope.selectedVisualizationsList, function(selectedVal, key) {
					if(angular.equals(listVal.inVisualizeId, selectedVal.inVisualizeId) && 
						angular.equals(listVal.inKpiId, selectedVal.inKpiId) && 
							angular.equals(listVal.inCategoryId, selectedVal.inCategoryId)){ 
						exists = true 
					}; 
				});
				if(exists == false && listVal.inVisualizeId != "") { 
					$scope.selectedVisualizationsList.push(listVal);
					$scope.visExists = false;
					$scope.selectVis = false;
				}
				else{
					$scope.visExists = true;
					$scope.selectVis = false;
				}
			});
		}
		else{
			if($scope.flag == 0){
				$scope.selectVis = true;
				$scope.visExists = false;
			}
			else{
				$scope.visExists = true;
				$scope.selectVis = false;
			}
		}
		$scope.checkAllTree();
	}
	
	$scope.checkAll = function () {
        if (!$scope.selectedAll) {
            $scope.selectedAll = true;
        } else {
            $scope.selectedAll = false;
        }
        angular.forEach($scope.selectedVisualizationsList, function(category) {
        	category.selected = $scope.selectedAll;
        });
    };
    
    $scope.checkAllTree = function () {
    	$scope.selectedAllTree = false;
        angular.forEach($scope.existingCategoriesList, function(val){
			if(val.kpis){
				angular.forEach(val.kpis, function(val_1){
					if(val_1.visualizations){
						angular.forEach(val_1.visualizations, function(val_2){
							if(val_2.selected == false){
								val_2.selected=$scope.selectedAllTree;
							}
						})
					}
				})
			}
		})
    };

    $scope.removeselectedVisualization = function(){
        var selectedVisualizationsList=[], tempSelectList=[];
        if($scope.selectedAll == true){
        	$timeout(function() {
                angular.element('#checkAll').triggerHandler('click');
            }, 0);
        }
        
        angular.forEach($scope.selectedVisualizationsList, function(val){
            if(!val.selected){
                tempSelectList.push(val);
                selectedVisualizationsList.push(val);
            }
        }); 
        $scope.selectedVisualizationsList = selectedVisualizationsList;
        $scope.selectedList = tempSelectList;
        $scope.emptyCheckboxes();
        $scope.updateCheckboxes();
        $scope.visExists = false;
    };
}]);