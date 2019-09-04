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
 * Description : Controller for User Management screen.
 */
/*global
app, console, angular
 */
app.controller("UserCtrl", ['$scope', '$rootScope', '$route', '$http', '$templateCache', '$controller', 'uiGridConstants', '$filter', '$uibModal', 'ngDialog', 'accessService', '$timeout', 'HierarchyNodeService',
                            function ($scope, $rootScope, $route, $http, $templateCache, $controller, $uiGridConstants, $filter, $uibModal, ngDialog, accessService, $timeout, HierarchyNodeService) {
        "use strict";
        $scope.roleIdOnSave = 0;
        $scope.roleIdOnMap = 0;
        $scope.isRoleSelectedinAccess = false;
        $scope.showTreeinAccess = false;
        var token = localStorage.getItem('loginToken');
        console.log(token);
        /**
         *  Method to list existing roles in Role Management .
         */
        $scope.getAllRoleDetails = function () {
            token = localStorage.getItem('loginToken');
            return accessService.getAllRoleDetails(token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.existingRoles = data.root;
                        console.log(JSON.stringify($scope.existingRoles));
                    } else {
                        var error = data.errors;
                        $scope.existingRoles = "";
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                    }
                }
            );
        };

        if (!$rootScope.modulesMain.access.showBlock) {
            $scope.openConfirmLogin();
        } else {
            // Calling service to list all roles.
            $scope.getAllRoleDetails();
        }
        /**
         *  Method invoked on click of tab in User Manager Menu .
         */
        $scope.tabOnClick = function (management) {
            token = localStorage.getItem('loginToken');
            if (token) {
                if (management === 'role') {
                    //$scope.getAllRoleDetails();
                    console.log("role");
                } else if (management === 'roleUser') {
                    $scope.getAllUserRoleDetails();
                    $scope.getAllUserDetails();
                } else if (management === 'roleAccess') {
                    //$scope.getAllPortalDetails();
                    $scope.existingRolesinAccess = $scope.existingRoles;
                }
            } else {
                $scope.openConfirmLogin();
            }
        };
        /**
         *  Method to list existing users in Role Access Screen .
         */
        $scope.getAllUserDetails = function () {
            token = localStorage.getItem('loginToken');
            return accessService.getAllUserDetails(token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.existingUsers = data.root;
                        console.log(JSON.stringify($scope.existingUsers));
                    } else {
                        var error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }

                    }
                }
            );
        };
        /**
         *  Method to list existing users-role mapping details in User Role Management screen .
         */
        $scope.getAllUserRoleDetails = function () {
            token = localStorage.getItem('loginToken');
            return accessService.getAllUserRoleDetails(token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.existingUserRoleMap = data.root;
                        console.log(JSON.stringify($scope.existingUserRoleMap));
                    } else {
                        var error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }

                    }
                }
            );
        };
        /**
         *  Method to list portal details when user selects a role id in Role Access Management screen .h
         */
        $scope.getAllPortalDetails = function (roleObj) {
            token = localStorage.getItem('loginToken');
            return accessService.getAllPortalDetails(roleObj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.existingPortalDetails = data.root;
                        console.log(JSON.stringify($scope.existingPortalDetails));
                    } else {
                        var error = data.errors;
                        $scope.existingPortalDetails = "";

                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        console.log(error.errorMessage);
                    }
                }
            );
        };
        /**
         *  Method to list portal details in Role Access Management screen .
         */
        $scope.getAllPortalDetailsinAccess = function () {
            token = localStorage.getItem('loginToken');
            return accessService.getAllPortalDetailsinAccess(token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.portalTreeinAccess = data.root;
                        console.log(JSON.stringify($scope.portalTreeinAccess));
                    } else {
                        var error = data.errors;
                        $scope.portalTreeinAccess = "";
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                    }
                }
            );
        };
        /**
         *  Method to to open add role popup in Role Management screen .
         */
        $scope.addNewRole = function () {
            token = localStorage.getItem('loginToken');
            if (token) {
                $scope.roleDetails = {};
                $scope.newRole = true;
                $scope.roleIdOnSave = 0;
                $scope.roleName = "";
                $scope.roleDesc = "";
                $scope.newRolePopUp = $uibModal.open({
                    templateUrl : 'app/views/role-details.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope
                });
            } else {
                $scope.openConfirmLogin();
            }
        };
        /**
         *  Method to close popup.
         */
        $scope.cancel = function (popUpInstance, type) {
            console.log("Close >> " + popUpInstance);
            popUpInstance.close();

        };
        /**
         *  Method to to open edit role popup in Role Management screen .
         */
        $scope.editRole = function (role) {
            token = localStorage.getItem('loginToken');
            if (token) {
                $scope.roleName = role.strRoleName;
                $scope.roleDesc = role.strRoleDesc;
                $scope.roleIdOnSave = role.inRoleId;
                $scope.newRole = false;
                $scope.newRolePopUp = $uibModal.open({
                    templateUrl : 'app/views/role-details.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope
                });
            } else {
                $scope.openConfirmLogin();
            }
        };
        /**
         *  Method to show confirmation dialog for login
         */
        $scope.openConfirmLogin = function () {
            ngDialog.openConfirm({
                template : 'modalDialogLogin',
                className : 'ngdialog-theme-default',
                showClose: false
            }).then(
                function (value) {
                    //callback();
                    window.location = 'login.html';
                },
                function (reason) {
                    console.log('Modal promise rejected. Reason: ', reason);
                }
            );
        };
        /**
        *  Method to get mapped roles for selected user .
        */
        $scope.selectUserMap = function (user) {
            var userName, dialog, error;
            userName = user.name;
            token = localStorage.getItem('loginToken');
            accessService.getUserDetails(userName, token).then(
                function (data) {
                    if (data.success === true) {
                        if (data.root.roles.length > 0 ){
                            $scope.existingRoles.selected = [];
                            angular.forEach(data.root.roles, function (val, k) {
                                angular.forEach($scope.existingRoles, function (val2, k) {
                                    if (val.inRoleId === val2.inRoleId) {
                                        $scope.existingRoles.selected.push(val2);
                                    }
                                });
                            });
                        }  else {
                            $scope.existingRoles.selected = {};
                        }
                    } else {
                        error = data.errors;
                        $scope.existingRoles.selected = {};
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                    }
                }
            );
        };
        /**
         *  Method to save role in Role Management screen .
         */
        $scope.saveRole = function () {
            console.log($scope.roleIdOnSave);
            var dialog, error, roleObj;
            console.log(document.getElementById("roleName").value);
            roleObj = {
                "inRoleId": $scope.roleIdOnSave,
                "strRoleName": document.getElementById("roleName").value,
                "strRoleDesc": document.getElementById("roleDesc").value
            };
            token = localStorage.getItem('loginToken');
            accessService.addRoleDetails(roleObj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.newRolePopUp.close();
                        $scope.success = data.success;
                        console.log(data.successDetails.successMessage);
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        $scope.getAllRoleDetails();
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        dialog = ngDialog.open({
                            template: error.errorCode + " : " + error.errorMessage,
                            plain: true,
                            className: 'ngdialog-theme-error'
                        });
                    }
                }
            );
        };
        /**
         *  Method to delete role in Role Management screen .
         */
        $scope.deleteRole = function (role) {
            var roleId, dialog, error;
            roleId = role.inRoleId;
            //Calling service to delete a configuration
            token = localStorage.getItem('loginToken');
            accessService.deleteRole(roleId, token).then(
                function (data) {
                    if (data.success === true) {
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        $scope.getAllRoleDetails();

                    } else {
                        error = data.errors;
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        dialog = ngDialog.open({
                            template: error.errorCode + " : " + error.errorMessage,
                            plain: true,
                            className: 'ngdialog-theme-error'
                        });
                    }
                }
            );
        };
        /**
         *  Method to open user-role mapping screen in User-Role Management screen .
         */
        $scope.addUserRoleMap = function () {
            token = localStorage.getItem('loginToken');
            if (token) {
                $scope.userRoleDetails = {};
                $scope.roleIdOnSave = 0;
                $scope.existingUsers.selected = {};
                $scope.existingRoles.selected = [];
                $scope.newUserRole = true;
                $scope.userRolePopUp = $uibModal.open({
                    templateUrl : 'app/views/user-role-mapping.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope
                });
            } else {
                $scope.openConfirmLogin();
            }
        };
        /**
         *  Method to edit user-role mapping screen in User-Role Management screen .
         */
        $scope.editUserRoleMapping = function (user) {
            token = localStorage.getItem('loginToken');
            var userId, rolesArray, defaultRoles;
            if (token) {
                userId = user.inUserId;
                $scope.userName = user.strUserName;
                console.log(JSON.stringify(user));
                angular.forEach($scope.existingUsers, function (val, k) {
                    if (val.id === userId) {
                        $scope.existingUsers.selected = val;
                        $scope.name = val.name;
                    }
                });
                rolesArray = user.roles;
                $scope.existingRoles.selected = [];
                angular.forEach($scope.existingRoles, function (val, k) {
                    angular.forEach(rolesArray, function (val2, k) {
                        if (val.inRoleId === val2.inRoleId) {
                            $scope.existingRoles.selected.push(val);
                        }
                    });
                });
                defaultRoles = user.defaultRole;
                $scope.existingRoles.selected.selected = {};
                angular.forEach($scope.existingRoles.selected, function (val, k) {                    
                    if (val.inRoleId === defaultRoles) {
                        $scope.existingRoles.selected.selected = val;
                    }
                });
                $scope.newUserRole = false;
                $scope.userRolePopUp = $uibModal.open({
                    templateUrl : 'app/views/user-role-mapping.html',
                    backdrop  : 'static',
                    keyboard  : false,
                    scope : $scope
                });
            } else {
                $scope.openConfirmLogin();
            }
        };
        /**
         *  Method to save user-role mapping screen in User-Role Management screen .
         */
        $scope.saveUserRole = function () {
            console.log($scope.roleIdOnMap);
            var dialog, error, mapObj;
            mapObj = {};
            mapObj.inUserId = $scope.existingUsers.selected.id;
            mapObj.strUserName = $scope.existingUsers.selected.name;
            mapObj.roles = [];

            angular.forEach($scope.existingRoles.selected, function (val, k) {
                var tempObj = {};
                tempObj.inRoleId = val.inRoleId;
                tempObj.strRoleName = val.strRoleName;
                mapObj.roles.push(tempObj);
            });
            mapObj.defaultRole = $scope.existingRoles.selected.selected.inRoleId;
            token = localStorage.getItem('loginToken');
            accessService.addUserRoleDetails(mapObj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.success = data.success;
                        if (mapObj.roles.length === 0) {
                            dialog = ngDialog.open({
                                template: "Please select a role",
                                plain: true,
                                className: 'ngdialog-theme-success'
                            });
                        } else {
                            $scope.userRolePopUp.close();
                            console.log(data.successDetails.successMessage);
                            dialog = ngDialog.open({
                                template: data.successDetails.successMessage,
                                plain: true,
                                className: 'ngdialog-theme-success'

                            });
                        }
                        $scope.existingRoles.selected = [];
                        $scope.getAllUserRoleDetails();
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        dialog = ngDialog.open({
                            template: error.errorCode + " : " + error.errorMessage,
                            plain: true,
                            className: 'ngdialog-theme-error'
                        });

                    }
                }
            );
        };
        /**
         *  Method to show confirmation dialog when user deletes
         *  user-role mapping  in User-Role Management screen .
         */
        $scope.openConfirm = function (user, callback) {
            ngDialog.openConfirm({
                template : 'modalDialogId',
                className : 'ngdialog-theme-default'
            }).then(
                function (value) {
                    callback(user);
                },
                function (reason) {
                    console.log('Modal promise rejected. Reason: ', reason);
                }
            );
        };
        /**
         *  Method to delete user-role mapping  in User-Role Management screen .
         */
        $scope.deleteUserRoleMapping = function (user) {
            var userId, dialog, error;
            userId = user.inUserId;
            //Calling service to delete a configuration
            token = localStorage.getItem('loginToken');
            accessService.deleteUserRoleMapping(userId, token).then(
                function (data) {
                    if (data.success === true) {
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        setTimeout(function () {
                            dialog.close();
                        }, 2000);
                        $scope.getAllUserRoleDetails();
                    } else {
                        error = data.errors;
                        console.log(error.errorMessage);
                        if (error.errorCode === "3006") {
                            $scope.openConfirmLogin();
                        }
                        dialog = ngDialog.open({
                            template : error.errorCode + " : " + error.errorMessage,
                            plain : true,
                            className : 'ngdialog-theme-error'
                        });
                        setTimeout(function () {
                            dialog.close();
                        }, 2000);
                    }
                }
            );
        };
        /**
         *  Method invoked to clear data in Role Management .
         */
        $scope.clearNewRole = function () {
            if (document.getElementById("roleName")) {
                document.getElementById("roleName").value = "";
            }
            if (document.getElementById("roleDesc")) {
                document.getElementById("roleDesc").value = "";
            }
        };
        /**
         *  Method invoked to clear data in User-Role Management .
         */
        $scope.clearNewUser = function () {
            $scope.existingUsers.selected = {};
            $scope.existingRoles.selected = [];
            document.getElementById("strUserName").value = "";
        };
        /**
         *  Method to modify the tree structure to include in UI tree in Role Access Management .
         *  Returns the new tree structure compatible with the UI tree element.
         */
        $scope.modifyTreeStructure = function (tree) {
            console.log(JSON.stringify(tree));
            var newTree = [], dashboardArray, categoryArray, kpiArray, visArray;
            if (tree.length > 0) {
                angular.forEach(tree, function (portal, key) {
                    var portalObj = {};
                    portalObj.id = portal.portalId;
                    portalObj.title = portal.strPortalName;
                    if (portal.portalViewEnabled) {
                        portalObj.isSelected = portal.portalViewEnabled;
                    } else {
                        portalObj.isSelected = false;
                    }
                    if (portal.dashboards) {
                        dashboardArray = [];
                        angular.forEach(portal.dashboards, function (dashboard, key) {
                            var dashboardObj = {};
                            dashboardObj.id = dashboard.dashboardId;
                            dashboardObj.title = dashboard.strDashboardlName;
                            if (dashboard.dashboardViewEnabled) {
                                dashboardObj.isSelected = dashboard.dashboardViewEnabled;
                            } else {
                                dashboardObj.isSelected = false;
                            }
                            if (dashboard.categoryKpiVisualizeList) {
                                categoryArray = [];
                                angular.forEach(dashboard.categoryKpiVisualizeList, function (category, key) {
                                    var categoryObj = {};
                                    categoryObj.id = category.inCategoryId;
                                    categoryObj.title = category.strCategoryName;
                                    if (category.categoryViewEnabled) {
                                        categoryObj.isSelected = category.categoryViewEnabled;
                                    } else {
                                        categoryObj.isSelected = false;
                                    }
                                    if (category.kpis) {
                                        kpiArray = [];
                                        angular.forEach(category.kpis, function (kpi, key) {
                                            var kpiObj = {};
                                            kpiObj.id = kpi.inKpiId;
                                            kpiObj.title = kpi.strKpiName;
                                            if (kpi.kpiViewEnabled) {
                                                kpiObj.isSelected = kpi.kpiViewEnabled;
                                            } else {
                                                kpiObj.isSelected = false;
                                            }
                                            if (kpi.visualizations) {
                                                visArray = [];
                                                angular.forEach(kpi.visualizations, function (vis, key) {
                                                    var visObj = {};
                                                    visObj.id = vis.inVisualizeId;
                                                    visObj.title = vis.strVisualizeName;
                                                    visObj.inPortalAccessId = vis.inPortalAccessId;
                                                    if (vis.visualizeViewEnabled) {
                                                        visObj.isSelected = vis.visualizeViewEnabled;
                                                    } else {
                                                        visObj.isSelected = false;
                                                    }
                                                    visArray.push(visObj);
                                                });
                                                kpiObj.items = visArray;
                                            }
                                            kpiArray.push(kpiObj);
                                        });
                                        categoryObj.items = kpiArray;
                                    }
                                    categoryArray.push(categoryObj);
                                });
                                dashboardObj.items = categoryArray;
                            }
                            dashboardArray.push(dashboardObj);
                        });
                        portalObj.items = dashboardArray;
                    }
                    newTree.push(portalObj);
                });
            }
            return newTree;
        };
        /**
         *  Method to create array from the left side visualization hierarchy for portal access.
         *  Returns the array of objects to be shown in the right table in portal access.
         */
        $scope.createRightSidePortalAccessArray = function (list) {
            var newList = [];
            angular.forEach(list, function (portal, key) {
                //var selectedObject= {};
                if (portal.items) {
                    angular.forEach(portal.items, function (dashboard, key) {
                        if (dashboard.items) {
                            angular.forEach(dashboard.items, function (category, key) {
                                if (category.items) {
                                    angular.forEach(category.items, function (kpi, key) {
                                        if (kpi.items) {
                                            angular.forEach(kpi.items, function (visualization, key) {
                                                var selectedObject = {};
                                                selectedObject.strPortalName = portal.title;
                                                selectedObject.inPortalId = portal.id;
                                                if (portal.isSelected) {
                                                    selectedObject.portalViewEnabled = portal.isSelected;
                                                } else {
                                                    selectedObject.portalViewEnabled = false;
                                                }
                                                selectedObject.strDashboardName = dashboard.title;
                                                selectedObject.inDashboardId = dashboard.id;
                                                if (dashboard.isSelected) {
                                                    selectedObject.dashboardViewEnabled = dashboard.isSelected;
                                                } else {
                                                    selectedObject.dashboardViewEnabled = false;
                                                }
                                                selectedObject.strCategoryName = category.title;
                                                selectedObject.inCategoryId = category.id;
                                                if (category.isSelected) {
                                                    selectedObject.categoryViewEnabled = category.isSelected;
                                                } else {
                                                    selectedObject.categoryViewEnabled = false;
                                                }
                                                selectedObject.strKpiName = kpi.title;
                                                selectedObject.inKpiId = kpi.id;
                                                if (kpi.isSelected) {
                                                    selectedObject.kpiViewEnabled = kpi.isSelected;
                                                } else {
                                                    selectedObject.kpiViewEnabled = false;
                                                }
                                                selectedObject.strVisualizeName = visualization.title;
                                                selectedObject.inVisualizeId = visualization.id;
                                                if (visualization.isSelected) {
                                                    selectedObject.visualizeViewEnabled = visualization.isSelected;
                                                } else {
                                                    selectedObject.visualizeViewEnabled = false;
                                                }

                                                if (visualization.inPortalAccessId) {
                                                    selectedObject.inPortalAccessId = visualization.inPortalAccessId;
                                                } else {
                                                    selectedObject.inPortalAccessId = 0;
                                                }

                                                if (selectedObject.visualizeViewEnabled) {
                                                    newList.push(selectedObject);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
            return newList;
        };
        /**
         *  Method invoked on selecting role id in Role-Access Management .
         */
        $scope.selectRoleInAccess = function (selectedRole) {
            $scope.isRoleSelectedinAccess = true;
            $scope.showTreeinAccess = false;
            $scope.selectedPortalAccessList = [];
            $scope.assessDetailsForRole = [];
            $scope.modifiedPortalTreeinAccess = [];
            $scope.modulesWithAccess = [
                {
                    "inFeatureId": 1,
                    "strFeatureName": "Configuration",
                    "featureViewEnabled": false,
                    "featureDeleteEnabled": false,
                    "featureEditEnabled": false,
                    "inFeatureAccessId" : 0
                },
                {
                    "inFeatureId": 2,
                    "strFeatureName": "Pipeline",
                    "featureViewEnabled": false,
                    "featureDeleteEnabled": false,
                    "featureEditEnabled": false,
                    "inFeatureAccessId" : 0
                },
                {
                    "inFeatureId": 3,
                    "strFeatureName": "Execution",
                    "featureViewEnabled": false,
                    "featureDeleteEnabled": false,
                    "featureEditEnabled": false,
                    "inFeatureAccessId" : 0
                },
                {
                    "inFeatureId": 4,
                    "strFeatureName": "Visualization",
                    "featureViewEnabled": false,
                    "featureDeleteEnabled": false,
                    "featureEditEnabled": false,
                    "inFeatureAccessId" : 0
                },
                {
                    "inFeatureId": 5,
                    "strFeatureName": "Stream Studio",
                    "featureViewEnabled": false,
                    "featureDeleteEnabled": false,
                    "featureEditEnabled": false,
                    "inFeatureAccessId" : 0
                },
                {
                    "inFeatureId": 6,
                    "strFeatureName": "Stream Analytics",
                    "featureViewEnabled": false,
                    "featureDeleteEnabled": false,
                    "featureEditEnabled": false,
                    "inFeatureAccessId" : 0
                },
                {
                    "inFeatureId": 7,
                    "strFeatureName": "User Access Manager",
                    "featureViewEnabled": false,
                    "featureDeleteEnabled": false,
                    "featureEditEnabled": false,
                    "inFeatureAccessId" : 0
                },
                {
                    "inFeatureId": 8,
                    "strFeatureName": "Operational UI Wizard",
                    "featureViewEnabled": false,
                    "featureDeleteEnabled": false,
                    "featureEditEnabled": false,
                    "inFeatureAccessId" : 0
                }
            ];
            var obj = {};
            obj.inRoleId = selectedRole.inRoleId;
            token = localStorage.getItem('loginToken');
            accessService.getAllPortalDetailsforRole(obj, token).then(
                function (data) {
                    if (data.success === true) {
                        $scope.assessDetailsForRole = data.root;
                        console.log(JSON.stringify($scope.assessDetailsForRole));
                        if ($scope.assessDetailsForRole.accessLevel.portalList) {
                            $scope.modifiedPortalTreeinAccess = $scope.modifyTreeStructure($scope.assessDetailsForRole.accessLevel.portalList);
                            $scope.selectedPortalAccessList = $scope.createRightSidePortalAccessArray($scope.modifiedPortalTreeinAccess);
                        }
                        if ($scope.assessDetailsForRole.accessLevel.featureAccesses.length > 0) {
                            $scope.modulesWithAccessForRole = $scope.modulesWithAccess;
                            angular.forEach($scope.modulesWithAccessForRole, function (selectedVal, key) {
                                angular.forEach($scope.assessDetailsForRole.accessLevel.featureAccesses, function (apiVal, key) {
                                    if (selectedVal.inFeatureId === apiVal.inFeatureId) {
                                        selectedVal.featureViewEnabled = apiVal.featureViewEnabled;
                                        selectedVal.featureDeleteEnabled = apiVal.featureDeleteEnabled;
                                        selectedVal.featureEditEnabled = apiVal.featureEditEnabled;
                                        selectedVal.inFeatureAccessId = apiVal.inFeatureAccessId;
                                    }
                                });
                            });
                        } else {
                            $scope.modulesWithAccessForRole = $scope.modulesWithAccess;
                        }
                        $scope.showTreeinAccess = true;
                        $scope.baseList = $scope.modifiedPortalTreeinAccess;
                        $scope.list = $scope.baseList;
                        console.log($scope.list);

                    } else {
                        var error = data.errors;
                        console.log(error.errorMessage);
                        $scope.modulesWithAccessForRole = $scope.modulesWithAccess;
                        $scope.selectedPortalAccessList  = [];
                        $scope.portalTreeinAccess = [];
                        token = localStorage.getItem('loginToken');
                        accessService.getAllPortalDetailsinAccess(token).then(
                            function (data) {
                                if (data.success === true) {
                                    $scope.portalTreeinAccess = data.root;
                                    console.log(JSON.stringify($scope.portalTreeinAccess));
                                    $scope.modifiedPortalTreeinAccess = $scope.modifyTreeStructure($scope.portalTreeinAccess);
                                    $scope.showTreeinAccess = true;
                                    $scope.baseList = $scope.modifiedPortalTreeinAccess;
                                    $scope.list = $scope.baseList;
                                    console.log($scope.list);
                                } else {
                                    var error = data.errors;
                                    console.log(error.errorMessage);
                                    if (error.errorCode === "3006") {
                                        $scope.openConfirmLogin();
                                    }
                                    $scope.portalTreeinAccess = "";
                                    $scope.modifiedPortalTreeinAccess = [];
                                    console.log(error.errorMessage);
                                }
                            }
                        );
                    }
                }
            );
        };
        /**
         *  Method invoked on click of checkbox in portal access in Role-Access Management .
         */
        $scope.addselectedVisualizationAccess = function (listItem) {
            $scope.selectedPortalAccessList = [];
            //$scope.list = accessService.getList();
            $scope.list = listItem;
            console.log($scope.list);
            $scope.selectedPortalAccessList = $scope.createRightSidePortalAccessArray($scope.list);
        };
        $rootScope.$on("CallParentMethod", function (ev, item) {
            console.log("addselectedVisualizationAccess in UserCtrl");
            $scope.addselectedVisualizationAccess(item.data);
        });
        /**
         *  Method invoked on updating access in Role-Access Management .
         */
        $scope.updateRoleAccess = function () {
            token = localStorage.getItem('loginToken');
            var newAccessObj = {}, dialog, error, selectedRoleId;
            newAccessObj.inRoleId = $scope.existingRoles.selected.inRoleId;
            newAccessObj.accessLevel = {};
            if ($scope.existingRoles.selected.accessLevel) {
                if ($scope.existingRoles.selected.accessLevel.inAccessId) {
                    newAccessObj.accessLevel.inAccessId = $scope.existingRoles.selected.accessLevel.inAccessId;
                }
            } else {
                newAccessObj.accessLevel.inAccessId = 0;
            }

            newAccessObj.accessLevel.featureAccesses = $scope.modulesWithAccess;

            newAccessObj.accessLevel.portalAccesses = $scope.selectedPortalAccessList;

            console.log(JSON.parse(angular.toJson(newAccessObj)));
            accessService.updateRoleAccess(JSON.parse(angular.toJson(newAccessObj)), token).then(
                function (data) {
                    if (data.success === true) {
                        dialog = ngDialog.open({
                            template: data.successDetails.successMessage,
                            plain: true,
                            className: 'ngdialog-theme-success'
                        });
                        console.log($scope.existingRoles.selected);
                        selectedRoleId = $scope.existingRoles.selected.inRoleId;
                        $scope.getAllRoleDetails().then(function (data) {
                            angular.forEach($scope.existingRoles, function (role, key) {
                                if (role.inRoleId === selectedRoleId) {
                                    $scope.existingRoles.selected = role;
                                }
                            });
                        });
                    } else {
                        error = data.errors;
                        dialog = ngDialog.open({
                            template : error.errorCode + " : " + error.errorMessage,
                            plain : true,
                            className : 'ngdialog-theme-error'
                        });
                    }
                }
            );
        };
    }]);
