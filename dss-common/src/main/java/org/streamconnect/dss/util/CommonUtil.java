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

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 */
@Service
public class CommonUtil {

    /**
     * To json string string.
     *
     * @param obj the obj
     * @return the string
     */
    public String toJsonString(final Object obj) {
        Gson gson = new Gson();
        String strJson = gson.toJson(obj);
        return strJson;
    }

    /**
     * Method for converting Date to String.
     *
     * @param date the input date
     * @return strDate
     */
    public String dateToString(final Date date){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String strDate = df.format(date);
        return strDate;
    }

    /**
     * Method for converting InputStream to  ByteArray.
     *
     * @param inputStream the input stream
     * @return byte[] byte [ ]
     * @throws IOException the io exception
     */
    public byte[] toByteArray(final InputStream inputStream) throws
            IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = inputStream.read();
        while (reads != -1) {
            baos.write(reads);
            reads = inputStream.read();
        }
        return baos.toByteArray();
    }

    /**
     * Method for testing the host reachable or not.
     *
     * @param strHost the str host
     * @return boolean boolean
     * @throws IOException
     */
    public boolean isReachable(final String strHost, final int nPort) {
        boolean bRet = false;
        try {
            (new Socket(strHost, nPort)).close();
            bRet = true;
        } catch (SocketException e) {
            bRet = false;
        } catch (Exception e) {
            bRet = false;
        }
        return bRet;
    }


}
