## Infosys Realtime Streams Community Edition ( Lite Version ) Repository

**Infosys Real time Streams** is a horizontal framework which makes management, monitoring, real-time analytics and visualization of live streaming data a cinch and provides a visual code free interface, allowing the user to create pipelines of real time streams at ease and also depict their visualizations as a presentation layer

The Realtime Streams visual code free interface can be classified into two.

    Realtime Streams : An interface for the Realtime pipeline Management
    Stream Studio    : An interface for the Business Stream Portal Management

Using the Realtime Streams login page we can login to the Realtime pipeline Management user interface with the credentials realtimeuser/realtimeuser

<p align="center"> <img src="https://github.com/Infosys/RealtimeStreams/blob/master/dss-wiki/images/login.jpg"> </p>
  
Realtime Streams core modules deals with creation of data pipeline, configuration of its components, its execution and visualization and granting exceptional access to users. 

The landing page will have the pipeline management screens

 <p align="center"> <img src="https://github.com/Infosys/RealtimeStreams/blob/master/dss-wiki/images/landing.jpg"> </p>

Following are the core modules

    * User Access Manager
    * Configuration
    * Pipeline
    * Execution
    * Visualizations
    * Stream Studio
    * Operational UI Wizard
  
  The user interface has a guided wizard for the users to understand the process flow.
  
 <p align="center"> <img src="https://github.com/Infosys/RealtimeStreams/blob/master/dss-wiki/images/guidedlanding.jpg">  </p>
  
  The configuration screen will have the configuration related functionalities
  
  <p align="center"> <img src="https://github.com/Infosys/RealtimeStreams/blob/master/dss-wiki/images/configuration.jpg">  </p>

  Using the pipeline creation screen we can create the realtime pipelines
  
  <p align="center"> <img src="https://github.com/Infosys/RealtimeStreams/blob/master/dss-wiki/images/pipelinecreation.jpg">  </p>

**Realtime Streams Installation and deployment**

Following are the setup steps that needs to be followed for Realtime Streams Installation and deployment.

**Prerequisites used in the setup**

    * Mysql version 5.1.31
    * Hadoop
    * Sources like Kafka/Flume
    * Processing engine like Spark
    * Sink like Elassandra
    * Livy
    * Grafana
    * Java 1.8
    * Tomcat 8.5.2
    * Maven 3.3.3
    
**Installation and deployment**

    * Download the latest code from Git repository.
    * Change the mysql user id and password in the persistence.properties file available in the dss-stream-api\src\main\resources\ and dss-portal-api\src\main\resources\
    * Change the required properties in the dss-stream-api\src\main\resources\commonconfig.properties
    * Open the mysql prompt and run the script schema.sql in the dss-scripts folder which will create the database .Tables will be created by hibernate internally.
    * Build the code using Maven and get the war file for the deployment from the dss-builder(parent pom) . We will have realtimestreams.war and streamportal.war in dss-stream-api and dss-portal-api target folders respectively.
    * If we are using Tomcat container (added the Jetty plugin as well), copy the war files in the webapps and start the application. Verify whether the mysql tables  are created by hibernate internally.
    * Connect to the mysql data base (realtimestreams) and run all the remaining scripts in dss-scripts config and template folder.
    * Restart the tomcat after applying all the mysql scripts.
    * The application will be available from the following URLs,
          Realtime Streams URL  : http://<ip>:<port>/realtimestreams/login.html  (OR http://<ip>:<port>/dss-stream-api/login.html)
          Stream Portal URL     : http://<ip>:<port>/streamportal/login.html   ( OR  http://<ip>:<port>/dss-portal-api/login.html) 

**Overview on the Community and Enterprise versions of Realtime Streams** 

<p align="center"> <img src="https://github.com/Infosys/RealtimeStreams/blob/master/dss-wiki/images/versiondifference01.jpg">  </p>

<p align="center"> <img src="https://github.com/Infosys/RealtimeStreams/blob/master/dss-wiki/images/versiondifference02.jpg">  </p>

<p align="center"> <img src="https://github.com/Infosys/RealtimeStreams/blob/master/dss-wiki/images/versiondifference03.jpg">  </p>


**Eager to know more on Realtime Streams** 
      
Visit the official Page - https://www.infosys.com/data-analytics/insights/Documents/realtime-streams.pdf

