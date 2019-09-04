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
/*global
app, angular, getAllChildren, "_"
*/
app.controller("HeirarchyCtrl", function ($scope, $timeout, $rootScope, HierarchyNodeService, accessService) {
    "use strict";
    $scope.numSelected = 0;
    //$scope.list is used by ng-tree, dataset should never be modified
    $scope.list = angular.copy($scope.dataset);

    $scope.options = {};

    $scope.expandNode = function (n, $event) {
        $event.stopPropagation();
        n.toggle();
    };

    function selectParent(parent) {
        var children = HierarchyNodeService.getAllChildren(parent, []);

        if (!children) {
            return;
        }
        children = children.slice(1).map(function (c) {
            return c.isSelected;
        });
        /*jslint nomen: true*/
        parent.isSelected = children.length === _.compact(children).length;
        /*jslint nomen: false*/
        HierarchyNodeService.findParent($scope.list[0], null, parent, selectParent);
    }

    $scope.itemSelect = function (item, test) {
        var rootVal, s;
        rootVal = !item.isSelected;
        HierarchyNodeService.selectChildren(item, rootVal);

        HierarchyNodeService.findParent($scope.list[0], null, item, selectParent);
        /*jslint nomen: true*/
        s = _.compact(HierarchyNodeService.getAllChildren($scope.list[0], []).map(function (c) {
            return c.isSelected && !c.items;
        }));
        /*jslint nomen: false*/
        $scope.numSelected = s.length;

        $rootScope.$emit("CallParentMethod", {
            data: $scope.list
        });

    };

    $scope.nodeStatus = function (node) {
        var flattenedTree = getAllChildren(node, []);
        flattenedTree = flattenedTree.map(function (n) {
            return n.isSelected;
        });
        /*jslint nomen: true*/
        return flattenedTree.length === _.compact(flattenedTree);
    };

    // }

});
app.directive("indeterminateCheckbox", function (HierarchyNodeService) {
    "use strict";
    return {
        restrict: 'A',
        scope: {
            node: '='
        },
        link: function (scope, element, attr) {

            scope.$watch('node', function (nv) {
                var flattenedTree, initalLength, compactedTree, r;
                flattenedTree = HierarchyNodeService.getAllChildren(scope.node, []);
                flattenedTree = flattenedTree.map(function (n) {
                    return n.isSelected;
                });

                initalLength = flattenedTree.length;
                /*jslint nomen: true*/
                compactedTree = _.compact(flattenedTree);
                /*jslint nomen: false*/

                r = compactedTree.length > 0 && compactedTree.length < flattenedTree.length;

                element.prop('indeterminate', r);

            }, true);

        }
    };
});
