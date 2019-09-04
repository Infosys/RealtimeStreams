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

package org.streamconnect.dss.api.ui.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.streamconnect.dss.api.ui.response.ErrorVO;
import org.streamconnect.dss.api.ui.response.SuccessVO;
import org.streamconnect.dss.logger.DSSLogger;

/**
 * Format builder for the Administrator related UI . In this layer mainly the
 * response object creation happens also in this layer primary validation will
 * also happens
 *
 * @version 1.0
 *
 *
 */
public abstract class BaseResponseBuilder {

	/** The Constant ERRORS_DATA. */
	private static final String ERRORS_DATA = "errors";

	/** The Constant SUCCESS_STATUS_FALSE. */
	private static final Boolean SUCCESS_STATUS_FALSE = false;

	/** The Constant LOGGER. */
	private static final DSSLogger LOGGER = DSSLogger
			.getLogger(BaseResponseBuilder.class);

	/** The object mapper. */
	@Autowired
	protected ObjectMapper objectMapper;

	/** The success key. */
	private static final String SUCCESS_KEY = "success";

	/** The success status true. */
	private static final Boolean SUCCESS_STATUS_TRUE = true;

	/** The success data. */
	private static final String SUCCESS_DATA = "root";

	/** The success response. */
	private static final String SUCCESS_RESPONSE = "successDetails";

	/**
	 * Platform Error Builders Messages for Content Building exception.
	 *
	 * @param message
	 *            the message
	 * @param code
	 *            the code
	 * @param e
	 *            the e
	 * @return String
	 */
	protected String getContentBuildingErrorMessage(final String message,
													final String code, final Exception e) {
		LOGGER.info(
				"In BaseResponseBuilder: getContentBuildingErrorMessage(String message, String code, Exception e) ---> Start");
		ErrorVO error = new ErrorVO();
		error.setErrorCode(code);
		error.setErrorMessage(message);
		// LOGGER.error(message, e);
		Map<String, Object> list = new HashMap<String, Object>();
		list.put(SUCCESS_KEY, SUCCESS_STATUS_FALSE);
		list.put(ERRORS_DATA, error);
		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(list);
			LOGGER.info(
					"In BaseResponseBuilder: getContentBuildingErrorMessage(String message, String code, Exception e) ---> End");
		} catch (Exception e1) {
			LOGGER.error(
					"Exception in BaseResponseBuilder: getContentBuildingErrorMessage function:",
					e1);
			return writeValueAsString;
		}
		return writeValueAsString;
	}

	/**
	 * Platform Error Builders Messages for Content Building exception.
	 *
	 * @param e
	 *            the e
	 * @return String
	 */
	protected String getContentBuildingErrorMessage(final Exception e) {
		LOGGER.info(
				"In BaseResponseBuilder: getContentBuildingErrorMessage(Exception e) ---> Start");
		ErrorVO error = new ErrorVO();
		Map<String, Object> list = new HashMap<String, Object>();
		list.put(SUCCESS_KEY, SUCCESS_STATUS_FALSE);
		list.put(ERRORS_DATA, error);
		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(list);
			LOGGER.info(
					"In BaseResponseBuilder: getContentBuildingErrorMessage(Exception e) ---> End");
		} catch (Exception e1) {
			LOGGER.error(
					"Exception in BaseResponseBuilder: getContentBuildingErrorMessage function:",
					e1);
			return writeValueAsString;
		}
		return writeValueAsString;
	}

	/**
	 * Platform Error Builders Messages for null pointer exception.
	 *
	 * @return String
	 */
	protected String getNullPointerErrorMessage() {
		LOGGER.info(
				"In BaseResponseBuilder: getNullPointerErrorMessage() ---> Start");
		ErrorVO error = new ErrorVO();
		error.setErrorCode("Errorcode");
		error.setErrorMessage("Errormessage");

		Map<String, Object> list = new HashMap<String, Object>();
		list.put(SUCCESS_KEY, SUCCESS_STATUS_FALSE);
		list.put(ERRORS_DATA, error);
		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(list);
			LOGGER.info(
					"In BaseResponseBuilder: getNullPointerErrorMessage() ---> End");
		} catch (Exception e) {
			LOGGER.error(
					"Exception in BaseResponseBuilder: getNullPointerErrorMessage function:",
					e);
			return writeValueAsString;
		}
		return writeValueAsString;
	}

	/**
	 * To convert List of collection into JSON object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param toJson
	 *            the to json
	 * @return the string
	 */
	protected <T> String toCollectionJsonify(final List<T> toJson) {
		LOGGER.info("In BaseResponseBuilder: toCollectionJsonify ---> Start");
		Map<String, Object> list = new HashMap<String, Object>();
		list.put(SUCCESS_KEY, SUCCESS_STATUS_TRUE);
		list.put(SUCCESS_DATA, toJson);
		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(list);
			LOGGER.info("In BaseResponseBuilder: toCollectionJsonify ---> End");
		} catch (Exception e) {
			LOGGER.error(
					"Exception in BaseResponseBuilder: toCollectionJsonify function:",
					e);
			return getContentBuildingErrorMessage(e);
		}
		return writeValueAsString;
	}

	/**
	 * To convert String into JSON object , Conversion code omits, then we can
	 * use it to create normal response.
	 *
	 * @param <T>
	 *            the generic type
	 * @param toJson
	 *            the to json
	 * @return the string
	 */
	protected <T> String toStringJsonify(final T toJson) {
		LOGGER.info("In BaseResponseBuilder: toStringJsonify ---> Start");
		Map<String, Object> list = new HashMap<String, Object>();
		list.put(SUCCESS_KEY, SUCCESS_STATUS_TRUE);
		list.put(SUCCESS_DATA, toJson);
		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(list);
			LOGGER.info("In BaseResponseBuilder: toStringJsonify ---> End");
		} catch (Exception e) {
			LOGGER.error(
					"Exception in BaseResponseBuilder: toStringJsonify function:",
					e);
			return getContentBuildingErrorMessage(e);
		}
		return writeValueAsString;
	}

	/**
	 * Platform Error Builders Messages for Content Building exception.
	 *
	 * @param message
	 *            the message
	 * @return String
	 */
	protected String getContentBuildingSuccessMessage(final String message) {
		LOGGER.info(
				"In BaseResponseBuilder: getContentBuildingSuccessMessage(String message)  ---> Start");
		SuccessVO success = new SuccessVO();

		success.setSuccessMessage(message);
		// LOGGER.error(message);
		Map<String, Object> list = new HashMap<String, Object>();
		list.put(SUCCESS_KEY, SUCCESS_STATUS_TRUE);
		list.put(SUCCESS_RESPONSE, success);
		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(list);
			LOGGER.info(
					"In BaseResponseBuilder: getContentBuildingSuccessMessage(String message)  ---> End");
		} catch (Exception e1) {
			LOGGER.error(
					"Exception in BaseResponseBuilder: getContentBuildingSuccessMessage function:",
					e1);
			return writeValueAsString;
		}
		return writeValueAsString;
	}

	/**
	 * Platform Error Builders Messages for Content Building exception.
	 *
	 * @param message
	 *            the message
	 * @param code
	 *            the code
	 * @return String
	 */
	protected String getContentBuildingError(final String message,
											 final String code) {
		LOGGER.info(
				"In BaseResponseBuilder: getContentBuildingError(String message, String code)  ---> Start");
		ErrorVO error = new ErrorVO();
		error.setErrorCode(code);
		error.setErrorMessage(message);

		Map<String, Object> list = new HashMap<String, Object>();
		list.put(SUCCESS_KEY, SUCCESS_STATUS_FALSE);
		list.put(ERRORS_DATA, error);
		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(list);
			LOGGER.info(
					"In BaseResponseBuilder: getContentBuildingError(String message, String code)  ---> End");
		} catch (Exception e1) {
			LOGGER.error(
					"Exception in BaseResponseBuilder: getContentBuildingError function:",
					e1);
			return writeValueAsString;
		}
		return writeValueAsString;
	}

}
