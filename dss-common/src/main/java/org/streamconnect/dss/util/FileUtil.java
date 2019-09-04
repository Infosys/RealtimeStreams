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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Service;

/**
 * The type File util.
 */
@Service
public class FileUtil {

    /**
     * Method for reading a file.
     *
     * @param filePath
     *            the file path
     * @return string
     * @throws IOException
     *             the io exception
     */
    public static String fileRead(final String filePath) throws IOException {
        String strRet = "";
        try {
            FileReader fileReader = new FileReader(new File(filePath));
            BufferedReader br = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            br.close();
            fileReader.close();
            strRet = stringBuffer.toString();
        } catch (Exception e) {

        }
        return strRet;
    }

    /**
     * Method for creating a file by passing file content as string.
     *
     * @param fileName
     *            the file name
     * @param fileContent
     *            the file content
     * @return boolean
     * @throws IOException
     *             the io exception
     */
    public static boolean fileWrite(final String fileName, final String fileContent) throws IOException {

        boolean bRet = false;
        try {
            File file = new File(fileName);
            file.getParentFile().mkdirs();
            file.setExecutable(true, false);
            file.setReadable(true, false);
            file.setWritable(true, false);
            PrintWriter outFile = new PrintWriter(fileName);
            outFile.println(fileContent);
            outFile.close();
            bRet = true;
        } catch (Exception e) {

        }
        return bRet;
    }

    /**
     * Method for creating a file by passing file content as bytearray.
     *
     * @param fileDetails
     *            the file details
     * @param fileContent
     *            the file content
     * @return the boolean
     * @throws IOException
     *             the io exception
     */
    public boolean createFile(final String fileDetails, final byte[] fileContent) throws IOException {
        boolean status = false;
        try {
            File file = new File(fileDetails);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileContent);
                fos.close();
                status = true;
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

}
