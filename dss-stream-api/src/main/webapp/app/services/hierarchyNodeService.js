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
 * Description : Service for tree structure in User Management screen.
 */
/*global
app, "_"
 */
app.service('HierarchyNodeService', function () {
    "use strict";
    function lowerCase(str) {
        return str.split(' ').map(function (e) {
            return e.toString().toLowerCase();
        }).join(' ');
    }
    function treeSearch(tree, query) {
        var branches, newLeaf, trunk;
        if (!tree) {
            return {};
        }
        if (lowerCase(tree.title).indexOf(lowerCase(query)) > -1) {
            tree.match = true;
            return tree;
        }
        /*jslint nomen: true*/
        branches = _.reduce(tree.items, function (acc, leaf) {
            newLeaf = treeSearch(leaf, query);
            if (!_.isEmpty(newLeaf)) {
                /*jslint nomen: false*/
                acc.push(newLeaf);
            }
            return acc;
        }, []);
        /*jslint nomen: true*/
        if (_.size(branches) > 0) {
            trunk = _.omit(tree, 'items');
            /*jslint nomen: false*/
            trunk.items = branches;
            return trunk;
        }
        return {};
    }
    function getAllChildren(node, arr) {
        if (!node) {
            return;
        }
        arr.push(node);
        if (node.items) {
            //if the node has children call getSelected for each and concat to array
            node.items.forEach(function (childNode) {
                arr = arr.concat(getAllChildren(childNode, []));
            });
        }
        return arr;
    }
    function findParent(node, parent, targetNode, cb) {
        /*jslint nomen: true*/
        if (_.isEqual(node, targetNode)) {
            /*jslint nomen: false*/
            cb(parent);
            return;
        }
        if (node.items) {
            node.items.forEach(function (item) {
                findParent(item, node, targetNode, cb);
            });
        }
    }
    function getSelected(node, arr) {
        //if(!node) return [];
        //if this node is selected add to array
        if (node.isSelected) {
            arr.push(node);
            return arr;
        }
        if (node.items) {
            //if the node has children call getSelected for each and concat to array
            node.items.forEach(function (childNode) {
                arr = arr.concat(getSelected(childNode, []));
            });
        }
        return arr;
    }
    function selectChildren(children, val) {
        //set as selected
        children.isSelected = val;
        if (children.items) {
            //recursve to set all children as selected
            children.items.forEach(function (el) {
                selectChildren(el, val);
            });
        }
    }
    function trimLeafs(node, parent) {
        if (!node.items) {
            //da end of the road
            delete parent.items;
        } else {
            node.items.forEach(function (item) {
                trimLeafs(item, node);
            });
        }
    }
    return {
        getAllChildren : getAllChildren,
        getSelected : getSelected,
        selectChildren : selectChildren,
        trimLeafs : trimLeafs,
        treeSearch : treeSearch,
        findParent : findParent
    };
});
