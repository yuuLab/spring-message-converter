package com.yuulab.api.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuulab.controller.request.TestRequest;

public class ApiDefinitionsPool {

	private static Map<String, List<RequestParam>> requestParamsMap = creageApiDefinitions();


	private ApiDefinitionsPool() {
		// do nothing.
	}


	public static Map<String, List<RequestParam>> getRequestParams() {
		return requestParamsMap;
	}

	private static Map<String, List<RequestParam>> creageApiDefinitions() {
		// 動作確認テスト用

		RequestParam param1 = new RequestParam(0, 5, "firstName");
		RequestParam param2 = new RequestParam(6, 10, "lastName");
		List<RequestParam> requestParams = new ArrayList<>();
		requestParams.add(param1);
		requestParams.add(param2);

		Map<String, List<RequestParam>> map = new HashMap<>();
		map.put(TestRequest.class.getName(), requestParams);

		return map;
	}

}
