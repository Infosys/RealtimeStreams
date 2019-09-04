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
 * Description : Directive for tree structure in User Management screen.
 */
/*global
app, angular, "_"
 */
app.directive('hierarchySearch', function (HierarchyNodeService, accessService, $timeout) {
    "use strict";
    return {
        restrict : 'E',
        templateUrl : 'app/views/hierarchySearch.tpl.html',
        scope : {
            dataset : '='
        },
        controller : 'HeirarchyCtrl',
        link : function (scope, el, attr) {
            scope.$watch('pastUsersFilter', function (nv) {
                /*jslint nomen: true*/
                if (_.isUndefined(nv)) {
                    return;
                }
                /*jslint nomen: false*/
                if (nv) {
                    HierarchyNodeService.trimLeafs(scope.list[0]);
                } else {
                    scope.list = angular.copy(scope.dataset);
                }
            });
            var inputTimeout, time = 300;
            scope.$watch('searchValue', function (nv) {
                if (!nv && nv !== '') {
                    return;
                }
                var previousDataset, newData;
                previousDataset = angular.copy(scope.list);
                newData = (scope.searchValue === '') ? angular.copy(scope.dataset) : [HierarchyNodeService.treeSearch(angular.copy(scope.dataset[0]), scope.searchValue)];
                /*jslint nomen: true*/
                if (newData.length === 1 && _.isEmpty(newData[0])) {
                    scope.emptyData = true;
                    return;
                }
                scope.emptyData = false;
                if (_.isEqual(previousDataset, newData)) {
                    clearTimeout(inputTimeout);
                    return;
                }
                /*jslint nomen: false*/
                scope.list = newData;
                $timeout.cancel(inputTimeout);
                inputTimeout = $timeout(function () {
                    var els = document.querySelectorAll('[ui-tree-node]');
                    Array.prototype.forEach.call(els, function (el) {
                        el = angular.element(el);
                        var elScope = el.scope();
                        if (elScope.$modelValue.match) {
                            elScope.expand();
                            //loop through all parents and keep expanding until no more parents are found
                            var p = elScope.$parentNodeScope;
                            while (p) {
                                p.expand();
                                p = p.$parentNodeScope;
                            }
                        }
                    });
                }, 500);
            });
            scope.$watch('list', function (nv, ov) {
                if (!nv) {
                    return;
                }
                if (nv && !ov) {
                    scope.$apply();
                }
                //UPDATE SELECTED IDs FOR QUERY
                var rootNode, a;
                //get the root node
                rootNode = nv[0];
                //get all elements where isSelected == true
                a = HierarchyNodeService.getSelected(rootNode, []);
                //get the ids of each element
                /*jslint nomen: true*/
                a = _.pluck(a, 'id');
                /*jslint nomen: false*/
                scope.selected = a;
            }, true);
        }
    };
});
