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

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.streamconnect.dss.dto.ColumnMetadataDto;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


/**
 */
@Service
public class MetadataUtil {

    /**
     * Method for getting the list of metadata dto of uploaded source schema.
     *
     * @param strInputString the str input string
     * @param strFileType    the str file type
     * @param strDelimiter   the str delimiter
     * @param isHeaderExists the is header exists
     * @return List list
     */
    public List<ColumnMetadataDto> getColumnMetadataDtoList(final String
                                                                    strInputString, final String strFileType,
                                                            final String
                                                                    strDelimiter, final boolean isHeaderExists) {
        String tempStrInputString = strInputString;
        List<ColumnMetadataDto> columnMetadataList = new
                ArrayList<ColumnMetadataDto>();
        if (strFileType.equalsIgnoreCase("json")) {
            readJsonXmlData(tempStrInputString, columnMetadataList);
        }
        if (strFileType.equalsIgnoreCase("xml")) {
            // XML to JSON Conversion
            JSONObject xmlJSONObj = XML.toJSONObject(tempStrInputString);
            tempStrInputString = xmlJSONObj.toString(Constants.NUMBER_FOUR);
            readJsonXmlData(tempStrInputString, columnMetadataList);
        }
        if (strFileType.equalsIgnoreCase("delimited")) {
            List<String> childCsv = null;
            List<String> headerCsv = null;
            int inHeaderIndex = 0;
            int inChildIndex = 1;
            List<String> inputCsv = Arrays.asList(tempStrInputString.split
                    ("\\s*\\n\\s*"));
            childCsv = getChildOrHeaderCsv(inputCsv, inChildIndex,
                    strDelimiter);
            if (isHeaderExists) {
                headerCsv = getChildOrHeaderCsv(inputCsv, inHeaderIndex,
                        strDelimiter);
            }
            for (int index = 0; index <= childCsv.size() - 1; index++) {
                String item = childCsv.get(index);
                String headerItem = null;
                if (isHeaderExists) {
                    headerItem = headerCsv.get(index);
                }
                composeCsvColumnMetadata(item, columnMetadataList, childCsv
                        .indexOf(item), headerItem);
            }
        }
        return columnMetadataList;
    }

    /**
     * Method to extract the header or first row of the csv file
     *
     * @param inputCsv  the input csv file as list
     * @param inIndex   the header or child index
     * @param delimiter the delimiter
     */
    private List<String> getChildOrHeaderCsv(List<String> inputCsv, int
            inIndex, String
                                                     delimiter) {
        if (delimiter.contains("\\")) {
            delimiter = delimiter.substring(1);
        }
        return Arrays.asList(inputCsv.get(inIndex).split
                ("\\s*\\" + delimiter + "\\s*"));
    }

    /**
     * Method to read the JSON or XML file data.
     *
     * @param strInputString     the str input string
     * @param columnMetadataList the column metadata list
     */
    private void readJsonXmlData(final String strInputString,
                                 final List<ColumnMetadataDto>
                                         columnMetadataList) {
        try {
            JsonReader reader = new JsonReader(new StringReader
                    (strInputString));
            reader.setLenient(true);
            while (true) {
                JsonToken token = reader.peek();
                switch (token) {
                    case BEGIN_ARRAY:
                        reader.beginArray();
                        break;
                    case END_ARRAY:
                        reader.endArray();
                        break;
                    case BEGIN_OBJECT:
                        reader.beginObject();
                        break;
                    case END_OBJECT:
                        reader.endObject();
                        break;
                    case NAME:
                        reader.nextName();
                        break;
                    case STRING:
                        String strType = reader.nextString();
                        composeJsonXmlColumnMetadata(reader.getPath(), quote
                                (strType), columnMetadataList);
                        break;
                    case NUMBER:
                        double dblType = reader.nextDouble();
                        composeJsonXmlColumnMetadata(reader.getPath(),
                                dblType, columnMetadataList);
                        break;
                    case BOOLEAN:
                        boolean blnType = reader.nextBoolean();
                        composeJsonXmlColumnMetadata(reader.getPath(),
                                blnType, columnMetadataList);
                        break;
                    case NULL:
                        reader.nextNull();
                        break;
                    case END_DOCUMENT:
                        return;
                    default:
                        return;
                }
            }
        } catch (Exception e) {

        }
    }

    /**
     * Method to compose the metadata of the uploaded JSON or XML file.
     *
     * @param strColumnName      the str column name
     * @param objData            the obj data
     * @param columnMetadataList the column metadata list
     */
    private void composeJsonXmlColumnMetadata(final String strColumnName,
                                              final Object objData,
                                              final List<ColumnMetadataDto>
                                                      columnMetadataList) {
        String columnType = null;
        String tempstrColumnName = "";
        String REGEX = "\\[[0-9]+\\]";
        Pattern PATTERN = Pattern.compile(REGEX);
        tempstrColumnName = strColumnName.substring(2);
        tempstrColumnName = PATTERN.matcher(tempstrColumnName).replaceAll("");
        if (isScalar(objData)) {
            columnType = scalarType(objData);
        }
        ColumnMetadataDto columnMetadata = new ColumnMetadataDto();
        columnMetadata.setColumnName(tempstrColumnName);
        columnMetadata.setColumnDataType(columnType);
        columnMetadataList.add(columnMetadata);
    }

    /**
     * Method to compose the metadata of the uploaded Csv or Tsv or Psc or text
     * file.
     *
     * @param objData            the obj data
     * @param columnMetadataList the column metadata list
     * @param index              the index
     * @param headerItem         the header item
     */
    private void composeCsvColumnMetadata(final Object objData,
                                          final List<ColumnMetadataDto>
                                                  columnMetadataList, final int
                                                  index,
                                          final String headerItem) {
        String columnType = null;
        String columnName = null;
        if (headerItem == null || headerItem.isEmpty()) {
            columnName = "column_" + index;
        } else {
            columnName = headerItem;
        }
        if (isScalar(objData)) {
            columnType = scalarType(objData);
        }
        ColumnMetadataDto columnMetadata = new ColumnMetadataDto();
        columnMetadata.setColumnName(columnName);
        columnMetadata.setColumnDataType(columnType);
        columnMetadataList.add(columnMetadata);
    }

    /**
     * Method to quote the input string.
     *
     * @param strType the str type
     * @return String
     */
    private String quote(final String strType) {
        return new StringBuilder().append('"').append(strType).append('"')
                .toString();
    }

    /**
     * Method to check the input is scalar or not.
     *
     * @param objData the obj data
     * @return boolean
     */
    private boolean isScalar(final Object objData) {
        return objData instanceof String || objData instanceof Number ||
                objData instanceof Boolean
                || objData == JSONObject.NULL;
    }

    /**
     * Method to return the datatype of the input data.
     *
     * @param objData the obj data
     * @return String
     */
    private String scalarType(final Object objData) {
        if (objData instanceof String) {
            String strData = objData.toString();
            if (isNumeric(strData)) {
                return scalarNumericType(objData);
            } else {
                return "string";
            }
        }
        if (objData instanceof Number) {
            return scalarNumericType(objData);
        }
        if (objData instanceof Boolean) {
            return "boolean";
        }
        return null;
    }

    /**
     * Checks if is numeric.
     *
     * @param strData the str data
     * @return true, if is numeric
     */
    private boolean isNumeric(final String strData) {
        return strData != null && strData.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * Method to return the datatype of the input NUMBER data.
     *
     * @param objData the obj data
     * @return String
     */
    private String scalarNumericType(final Object objData) {
        String strData = objData.toString();
        if (strData.contains(".")) {
            return "float";
        } else {
            return "long";
        }
    }

}
