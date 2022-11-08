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
		// データテーブルやプロパティファイルにAPIの定義を登録し、オンメモリでプールしておく。
		// 以下動作確認テスト用

		Map<String, List<RequestParam>> map = new HashMap<>();
		map.put(TestRequest.class.getName(), getTestRequestDefinition());
		return map;
	}

	private static List<RequestParam> getTestRequestDefinition() {
		RequestParam param1 = new RequestParam(1, 10, "firstName", 20);
		RequestParam param2 = new RequestParam(11, 20, "lastName", 20);
		List<RequestParam> requestParams = new ArrayList<>();
		requestParams.add(param1);
		requestParams.add(param2);
		return requestParams;
	}

}
