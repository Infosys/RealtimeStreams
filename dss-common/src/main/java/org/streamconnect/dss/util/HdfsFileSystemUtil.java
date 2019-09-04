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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * The type Hdfs file system util.
 */
public final class HdfsFileSystemUtil {

    /**
     * Instantiates a new hdfs file system util.
     */
    private HdfsFileSystemUtil() {

    }

    /**
     * Gets fs.
     *
     * @return the fs
     */
    public static FileSystem getFS() {

        FileSystem fs = null;

        try {
            Configuration conf = new Configuration();
            conf.set("fs.default.name", PropReader.getPropertyValue(Constants.NAMENODE_URL));
            // conf.set("fs.default.name","hdfs://10.177.116.69:9000");
            conf.set("dfs.support.broken.append", "true");
            conf.set("dfs.replication", "1");
            conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
            fs = FileSystem.get(conf);

        } catch (Exception e) {

        }
        return fs;

    }

    /**
     * Gets fs.
     *
     * @param conf
     *            the conf
     * @return the fs
     */
    public static FileSystem getFS(final Configuration conf) {

        FileSystem fs = null;

        try {
            fs = FileSystem.get(conf);
        } catch (Exception e) {

        }
        return fs;
    }

    /**
     * Create hdfs file string.
     *
     * @param strSourceData
     *            the str source data
     * @param strDestPath
     *            the str dest path
     * @return the string
     */
    public static String createHdfsFile(final String strSourceData, final
    String strDestPath) {

        String strRetFilename = "";

        try {

            FileSystem fsObj = getFS();
            String tempStrDestPath  = "";
            // String strRootPath = "hdfs://10.177.116.69:9000";
            String strNamenodePath = PropReader.getPropertyValue(Constants.NAMENODE_URL);
            // String strBasePath = "/data";
            String strBasePath = PropReader.getPropertyValue(Constants.FS_BASEPATH);

            if (strDestPath.charAt(0) != '/') {
                tempStrDestPath = strNamenodePath + strBasePath + "/" + strDestPath;
            } else {
                tempStrDestPath = strNamenodePath + strBasePath + strDestPath;
            }

            Path path = new Path(tempStrDestPath);
            if (fsObj.exists(path)) {
                return strRetFilename;
            }

            FSDataOutputStream outStream = fsObj.create(path);

            InputStream inStream = new ByteArrayInputStream(strSourceData.getBytes());

            byte[] b = new byte[Constants.HDFS_FILE_BYTE_SIZE];
            int numBytes = 0;
            while ((numBytes = inStream.read(b)) > 0) {
                outStream.write(b, 0, numBytes);
            }

            inStream.close();
            outStream.close();
            fsObj.close();

            strRetFilename = tempStrDestPath;

        } catch (Exception e) {

        }

        return strRetFilename;
    }

    /**
     * Upload to hdfs
     *
     * @param localPath
     *            the str local file path
     * @param fileName
     *            the file name
     * @return the boolean
     */
    public static boolean uploadToHdfs(String localPath, String fileName) throws IOException {
        boolean status = false;
        FileSystem fs = null;
        try {
            fs = getFS();
            Path path = new Path(PropReader
                    .getPropertyValue(Constants.USER_FUNCTION_PATH) + Path.SEPARATOR +
                    fileName);
            if(fs.exists(path)) {
                fs.delete(path,false);
            }
            fs.copyFromLocalFile(new Path(localPath), new Path(PropReader
                    .getPropertyValue(Constants.USER_FUNCTION_PATH)));
            status = true;
            return status;
        } catch (Exception e){
            return status;
        } finally {
            fs.close();
        }
    }


    /**
     * Delete jar file from hdfs
     *
     * @param fileName
     *            the file name
     * @return the boolean
     */
    public static boolean deleteFromHdfs(String fileName) throws IOException {
        boolean status =  false;
        FileSystem fs = null;
        try {
            fs = getFS();
            if(fs != null) {
                Path path = new Path(PropReader
                        .getPropertyValue(Constants.USER_FUNCTION_PATH) + Path.SEPARATOR + fileName);
                if (fs.exists(path)) {
                    fs.delete(path,false);
                    status = true;
                } else {
                    status = false;
                }
            }
        } catch (Exception e){
            status = false;
        } finally {
            fs.close();
        }
        return status;
    }


}
