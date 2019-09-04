var pipelineId = 0, pipelineName = "";
var flowchartApp = angular.module('flowchart-app', ['flowchart'])
  .factory('prompt', function () {
    return prompt;
  })
  .config(function (NodeTemplatePathProvider) {
    NodeTemplatePathProvider.setTemplatePath("flowchart/node.html");
  })

  .controller('AppCtrl', function AppCtrl($scope, prompt, Modelfactory, flowchartConstants,$http) {

    var deleteKeyCode = 46;
    var ctrlKeyCode = 17;
    var aKeyCode = 65;
    var escKeyCode = 27;
    var nextNodeID = 1;
    var nextConnectorID = 1;
    var ctrlDown = false;


   var pipelineAction = window.parent.angular.element(window.frameElement).scope().pipelineAction;
   if (pipelineAction == "edit" || pipelineAction == "view") {
	   model = {nodes: [],edges: []};
	   var token = localStorage.getItem('loginToken');
	   var pipelineRecord = window.parent.angular.element(window.frameElement).scope().selectedPipelineRecord;
	   var pipelineModel = window.parent.angular.element(window.frameElement).scope().pipelineModel;
	   model.nodes = pipelineModel.nodeDetails;
	   model.edges = pipelineModel.edges;
	   pipelineId = pipelineModel.inPipelineId;
	   pipelineName = pipelineModel.strPipelineName;
	   
	   nextNodeID = pipelineModel.addlDetails.inMaxStepId;
	   nextConnectorID = pipelineModel.addlDetails.inMaxConnId;
	   console.log("Max Step :" + nextNodeID + "Max Conn : " + nextConnectorID)

   } else {
	   model = {nodes: [],edges: []};
   }
   $scope.pipelineAction = pipelineAction;
$scope.flowchartselected = [];
var modelservice = Modelfactory(model, $scope.flowchartselected);


$scope.model = model;
$scope.modelservice = modelservice;

$scope.keyDown = function (evt) {
  if (evt.keyCode === ctrlKeyCode) {
    ctrlDown = true;
    evt.stopPropagation();
    evt.preventDefault();
  }
};

$scope.keyUp = function (evt) {

  if (evt.keyCode === deleteKeyCode) {
    modelservice.deleteSelected();
  }

  if (evt.keyCode == aKeyCode && ctrlDown) {
    modelservice.selectAll();
  }

  if (evt.keyCode == escKeyCode) {
    modelservice.deselectAll();
  }

  if (evt.keyCode === ctrlKeyCode) {
    ctrlDown = false;
    evt.stopPropagation();
    evt.preventDefault();
  }
};

$scope.addNewNode = function (name,icon,x,y) {
	var stepCount = [0], finalCount = 0;
	angular.forEach(model.nodes, function (node, k) {
		if ((node.name.toLowerCase() === name.toLowerCase()) ) {			
			var tempCount = (node.displayName.substr((node.displayName.indexOf(name) + name.length)));
			
			stepCount.push(tempCount);
		}
	});
	finalCount = Math.max.apply(Math, stepCount);
  var newNode = {
    name: name,
    icon: icon,
    id: nextNodeID++,
    x: x,
    y: y,
    color: '#F15B26',
    connectors: [
      {
        id: nextConnectorID++,
        type: flowchartConstants.topConnectorType
      },
      {
        id: nextConnectorID++,
        type: flowchartConstants.bottomConnectorType
      },
    ]
  };

  model.nodes.push(newNode);
};

$scope.activateWorkflow = function() {
  angular.forEach($scope.model.edges, function(edge) {
    edge.active = !edge.active;
  });
};

$scope.addNewInputConnector = function () {
  var connectorName = prompt("Enter a connector name:", "New connector");
  if (!connectorName) {
    return;
  }

  var selectedNodes = modelservice.nodes.getSelectedNodes($scope.model);
  for (var i = 0; i < selectedNodes.length; ++i) {
    var node = selectedNodes[i];
    node.connectors.push({id: nextConnectorID++, type: flowchartConstants.topConnectorType});
  }
};

$scope.addNewOutputConnector = function () {
  var connectorName = prompt("Enter a connector name:", "New connector");
  if (!connectorName) {
    return;
  }

  var selectedNodes = modelservice.nodes.getSelectedNodes($scope.model);
  for (var i = 0; i < selectedNodes.length; ++i) {
    var node = selectedNodes[i];
    node.connectors.push({id: nextConnectorID++, type: flowchartConstants.bottomConnectorType});
  }
};

$scope.deleteSelected = function () {

  modelservice.deleteSelected();
};

$scope.callbacks = {
  edgeDoubleClick: function () {
    console.log('Edge double clicked.');
  },
  edgeMouseOver: function () {
    console.log('mouserover')
  },
  isValidEdge: function (source, destination) {
    return source.type === flowchartConstants.bottomConnectorType && destination.type === flowchartConstants.topConnectorType;
  },
  edgeAdded: function (edge) {
    console.log("edge added");
    console.log(edge);
  },
  nodeRemoved: function (node) {
    console.log("node removed");
    console.log(node);
  },
  edgeRemoved: function (edge) {
    console.log("edge removed");
    console.log(edge);
  },
  nodeCallbacks: {
    'doubleClick': function (event,node) {
    	var stepAddEdit = "Save";

    	/*if($scope.pipelineAction == "edit"){
    		$scope.stepAddEdit = "Update";
    		$scope.$apply(); 
    	} */
    	if(node.properties){
    		var record = node.properties;
    		window.parent.angular.element(window.frameElement).scope().openWFPopup(node.name,"Update","1", node.id, node, model, record);
    	} else {
    		window.parent.angular.element(window.frameElement).scope().openWFPopup(node.name,"Save","1", node.id, node,model);
    	}
     
     console.log('Node was clicked.')
    }
  }
};
modelservice.registerCallbacks($scope.callbacks.edgeAdded, $scope.callbacks.nodeRemoved, $scope.callbacks.edgeRemoved);
$scope.save = function() {
	var nodeData, popupData;
	nodeData = window.parent.angular.element(window.frameElement).scope().flowchart.node;
	popupData = window.parent.angular.element(window.frameElement).scope().flowchart.popup;
	console.log(JSON.stringify(nodeData));
	console.log(JSON.stringify(angular.element(document.getElementById('flowchart')).scope().model));
	
	angular.forEach(angular.element(document.getElementById('flowchart')).scope().model.nodes, function (data, k) {
		if (data.id === nodeData.id) {
			data.properties = popupData;
		}
		/*console.log(pipelineData);*/
	});
}

	$scope.savePipeline = function() {
		var pipelineData, pipelineInpObj = {};
		/*var nodeData, popupData;
		nodeData = window.parent.angular.element(window.frameElement).scope().flowchart.node;
		popupData = window.parent.angular.element(window.frameElement).scope().flowchart.popup;
		console.log(JSON.stringify(nodeData));*/
		console.log(JSON.stringify(angular.element(document.getElementById('flowchart')).scope().model));
		pipelineData = JSON.parse(angular.toJson(angular.element(document.getElementById('flowchart')).scope().model));
		pipelineInpObj.nodes = {};		
		pipelineInpObj.nodes.nodeDetails = pipelineData.nodes;
		pipelineInpObj.edges = pipelineData.edges;
		pipelineInpObj.nodes.addlDetails = {};
		pipelineInpObj.nodes.addlDetails.inMaxStepId = nextNodeID;
		pipelineInpObj.nodes.addlDetails.inMaxConnId = nextConnectorID;
		
		pipelineInpObj.strPipelineName = window.parent.angular.element(window.frameElement).scope().pipelineName; 
		
		if (!pipelineId) {
			pipelineId = 0;
		}
		pipelineInpObj.inPipelineId = pipelineId;
		pipelineInpObj.strPipelineExeURL = "test";
		
		
		
		console.log(pipelineInpObj);
		var token = localStorage.getItem('loginToken');
		console.log(token);	
		window.parent.document.getElementById("loader1").style.visibility = "visible";
		saveDataflow(pipelineInpObj, token).then(
				function (data) {
					if (data.success === true) {
							//alert(data.successDetails.successMessage);
						console.log(data.successDetails.successMessage);
						window.parent.angular.element(window.frameElement).scope().closepopup(pipelineInpObj);

            		} else {
                			//$('#loader1').hide();
	                    	error = data.errors;
	                    	console.log(data);
	                    	//alert("Pipeline Save failed");
	                    	window.parent.angular.element(window.frameElement).scope().failurePopup(error.errorMessage, error.errorCode);
					}
					window.parent.document.getElementById("loader1").style.visibility = "hidden";
				}
			);
	}

	saveDataflow =  function(dataFlowJson,tokenValue) {
		//console.log(serviceURL.savePipeline);
		return $http({
			method : 'POST',
			url : serviceURL.savePipeline,
			data : JSON.stringify(dataFlowJson),
			headers: {'token': tokenValue}
		}).then(function(response) {
			if (typeof response.data === 'object') {
				return response.data;
				//return output;
			} else {
				var res={};
				res.errors={};
				res.success=false;
				res.errors.errorCode="";
				res.errors.errorMessage=msg.msgExistingConnectionError;
				return res;
			}
		}, function(response) {
			var res={};
			res.errors={};
			res.success=false;
			res.errors.errorCode="";
			res.errors.errorMessage=msg.msgExistingConnectionError;
			return res;
		});

	} 
	


})
;
