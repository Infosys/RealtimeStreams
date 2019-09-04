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
package org.streamconnect.dss.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Query plan util.
 */
@Service
public class QueryPlanUtil {

    /**
     * method to connect remote host via ssh.
     *
     * @param hostname the hostname
     * @param username the username
     * @param password the password
     * @return session
     */
    public  Session connect(final String hostname, final String username,
                            final String
            password) {

        JSch jSch = new JSch();
        Session session = null;
        try {

            session = jSch.getSession(username, hostname, Constants.SFTP_PORT_NUMBER);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(password);
            session.connect();
        } catch (Exception e) {
            System.out.println("An error occurred while connecting to "
                    + hostname + ": " + e);
        }

        return session;

    }

    /**
     * Method to read data fromt the Inputstream.
     *
     * @param in      the in
     * @param channel the channel
     * @return string from input stream
     * @throws IOException the io exception
     */
    public  String getStringFromInputStream(final InputStream in, final Channel
            channel) throws IOException {

        // System.out.println("Data Stream");
        byte[] tmp = new byte[Constants.HDFS_FILE_BYTE_SIZE];
        StringBuilder sb = new StringBuilder();

        while (true) {

            while (in.available() > 0) {

                int i = in.read(tmp, 0, Constants.HDFS_FILE_BYTE_SIZE);
                if (i < 0) {
                    break;
                }
                sb.append(new String(tmp, 0, i));

            }
            if (channel.isClosed()) {
                //System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
        }
        return  sb.toString();

    }

    /**
     * Method to parse the Spark Sql O/P.
     *
     * @param response the response
     * @return string
     */
    public  String getStatus(final String response) {

        String status = "";
        //AnalysisException
        if (response.contains("AnalysisException") || response.contains("UnresolvedException")
                || !response.contains("== Physical Plan ==")) {
            status = "false|Error In Query ,Check the Syntax or Schema";
        } else {
            status = "true|Valid Query";
        }

        return status;
    }




}
