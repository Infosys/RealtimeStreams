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

    /*var model = {
      nodes: [
        {
          name: "Geostream",
          type: "Kafka",
          img:"kafka1.png",
          id: 1,
          x: 10,
          y: 50,
          color: '#000',
          borderColor: '#000',
          connectors: [
            {
              type: flowchartConstants.topConnectorType,
              id: 5
            },
            {
              type: flowchartConstants.bottomConnectorType,
              id: 6
            }
          ]
        },
        {
            name: "Geostream1",
            type: "Kafka",
            img:"kafka1.png",
            id: 15,
            x: 10,
            y: 150,
            color: '#000',
            borderColor: '#000',
            connectors: [
              {
                type: flowchartConstants.topConnectorType,
                id: 13
              },
              {
                type: flowchartConstants.bottomConnectorType,
                id: 14
              }
            ]
          },
          {
              name: "Process",
              img:"spark.png",
              id: 16,
              x: 450,
              y: 80,
              color: '#F15B26',
              connectors: [
                {
                  type: flowchartConstants.topConnectorType,
                  id: 17
                },
                {
                  type: flowchartConstants.bottomConnectorType,
                  id:18
                }
              ]
            },
        {
          name: "Join",
          img:"join.png",
          id: 2,
          x: 250,
          y: 80,
          color: '#F15B26',
          connectors: [
            {
              type: flowchartConstants.topConnectorType,
              id: 7
            },
            {
              type: flowchartConstants.bottomConnectorType,
              id: 8
            }
          ]
        },
        {
            name: "Sink",
            img:"cassandra.png",
            id: 3,
            x: 700,
            y: 90,
            color: '#F15B26',
            connectors: [
              {
                type: flowchartConstants.topConnectorType,
                id: 9
              },
              {
                type: flowchartConstants.bottomConnectorType,
                id: 10
              }
            ]
          },
          {
              name: "Visualize",
              img: "grafana.png",
              id: 4,
              x: 900,
              y: 90,
              color: '#F15B26',
              connectors: [
                {
                  type: flowchartConstants.topConnectorType,
                  id: 11
                },
                {
                  type: flowchartConstants.bottomConnectorType,
                  id: 12
                }
              ]
            }
      ],
    edges: [
      {
        source: 6,
        destination: 7
      },
      {
          source: 14,
          destination: 7
        },
      {
        source: 8,
        destination: 17
      },
      {
          source: 18,
          destination: 9
        },
      {
        source: 10,
        destination: 11
      }
    ]
  };*/
    model = {nodes: [],edges: []};
    var pipelineAction = window.parent.angular.element(window.frameElement).scope().pipelineAction;
    if (pipelineAction == "monitor") {
    	var token = localStorage.getItem('loginToken');
    	var pipelineRecord = window.parent.angular.element(window.frameElement).scope().selectedPipelineRecord;
    	var pipelineModel = window.parent.angular.element(window.frameElement).scope().pipelineModel;
    	model.nodes = pipelineModel.nodeDetails;
    	model.edges = pipelineModel.edges;
    	pipelineId = pipelineModel.inPipelineId;
    	pipelineName = pipelineModel.strPipelineName;
    	/*nextNodeID = pipelineModel.addlDetails.inMaxStepId;
    	nextConnectorID = pipelineModel.addlDetails.inMaxConnId;
    	console.log("Max Step :" + nextNodeID + "Max Conn : " + nextConnectorID)*/
    	var nodes = model.nodes;
    	angular.forEach(nodes, function(node) {
    		if (node.name === "Source") {
    			node.img = "kafka1.png";
    		} else if (node.name === "Process") {
    			node.img = "spark.png";
    		} else if (node.name === "Sink") {
    			node.img = "cassandra.png";
    		} else if (node.name === "Visualize") {
    			node.img = "grafana.png";
    		} 
    	});
    } else {
    	model = {nodes: [],edges: []};
    }

   
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
    	/*if (node.name == "Process" && node.properties) {
    		window.parent.angular.element(window.frameElement).scope().openProcessDetails(node);
    	}*/
    }
  }
};
modelservice.registerCallbacks($scope.callbacks.edgeAdded, $scope.callbacks.nodeRemoved, $scope.callbacks.edgeRemoved);
})
;
