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
 * Description : Guided messages for Stream Connect.
 */


//initialize instance for Home Page
//var enjoyhint_instance = new EnjoyHint({});

//simple config. 
//Only one step - highlighting(with description) "New" button 
//hide EnjoyHint after a click on the button.
var enjoyhint_script_steps_all = [

                              {
                            	  'next #configBox' : 'Click here to configure the components like Source,Process,Sink..',
                            	  "skipButton" : {className: "mySkip", text: "SKIP"},
                            	  'nextButton' : {className: "myNext", text: "NEXT"}

                              },
                              {
                            	  'next #pipelineBox' : 'Click here to create the data pipelines.',
                            	  "skipButton" : {className: "mySkip", text: "SKIP"},
                            	  'nextButton' : {className: "myNext", text: "NEXT"}

                              },
                              {
                            	  'next #executeBox' : 'Click here to execute the pipelines created.',
                            	  "skipButton" : {className: "mySkip", text: "SKIP"},
                            	  'nextButton' : {className: "myNext", text: "NEXT"}

                              },
                              {
                            	  'next #visBox' : 'Click here to visualize the executed pipelines.',
                            	  "skipButton" : {className: "mySkip", text: "SKIP"},
                            	  'nextButton' : {className: "myNext", text: "NEXT"}

                              }
                              ];

var enjoyhint_script_steps_no_vis = [
                                  {
                                	  'next #configBox' : 'Click here to configure the components like Source,Process,Sink..',
                                	  "skipButton" : {className: "mySkip", text: "SKIP"},
                                	  'nextButton' : {className: "myNext", text: "NEXT"}

                                  },
                                  {
                                	  'next #pipelineBox' : 'Click here to create the data pipelines.',
                                	  "skipButton" : {className: "mySkip", text: "SKIP"},
                                	  'nextButton' : {className: "myNext", text: "NEXT"}

                                  },
                                  {
                                	  'next #executeBox' : 'Click here to execute the pipelines created.',
                                	  "skipButton" : {className: "mySkip", text: "SKIP"},
                                	  'nextButton' : {className: "myNext", text: "NEXT"}

                                  }
                                  ];
//set script config
//enjoyhint_instance.set(enjoyhint_script_steps);

//run Enjoyhint script
//enjoyhint_instance.run();

//initialize instance for Configuration Page
/*var enjoyhint_instance_config = new EnjoyHint({});

//simple config. 
//Only one step - highlighting(with description) "New" button 
//hide EnjoyHint after a click on the button.
var enjoyhint_script_steps_config = [

  {
	'next #sourceBox' : 'Click the "New" button to start creating your project',
	 "skipButton" : {className: "mySkip", text: "SKIP"}

  },
  {
	    'next #processBox' : 'Click the "New" button to start creating your project',
	    "skipButton" : {className: "mySkip", text: "SKIP"}

	  }
];

//set script config
enjoyhint_instance_config.set(enjoyhint_script_steps_config);*/