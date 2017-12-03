package com.arawn.cms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.List;
import java.util.Map;

/**
 * FastJson工具类
 * Created by ArawN on 2017/12/2.
 */
public class FastJsonUtil {

	private static final SerializeConfig CONFIG;

	static {
		CONFIG = new SerializeConfig();
		CONFIG.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * Author:Jack Time:2017年9月2日下午4:24:14
	 *
	 * @param object
	 * @return Return:String Description:将对象转成成Json对象
	 */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object, CONFIG);
	}

	/**
	 * Author:Jack Time:2017年9月2日下午4:24:54
	 *
	 * @param jsonStr
	 * @return Return:Object Description:将Json数据转换成JSONObject
	 */
	public static JSONObject toJsonObj(String jsonStr) {
		return (JSONObject) JSON.parse(jsonStr);
	}

	/**
	 * Author:Jack Time:2017年9月2日下午4:25:20
	 *
	 * @param jsonStr
	 * @param clazz
	 * @return Return:T Description:将Json数据转换成Object
	 */
	public static <T> T toBean(String jsonStr, Class<T> clazz) {
		return JSON.parseObject(jsonStr, clazz);
	}

	/**
	 * Author:Jack Time:2017年9月2日下午4:25:34
	 *
	 * @param jsonStr
	 * @return Return:Object[] Description:将Json数据转换为数组
	 */
	public static Object[] toArray(String jsonStr) {
		return toArray(jsonStr, null);
	}

	/**
	 * Author:Jack Time:2017年9月2日下午4:25:57
	 *
	 * @param jsonStr
	 * @param clazz
	 * @return Return:Object[] Description:将Json数据转换为数组
	 */
	public static <T> Object[] toArray(String jsonStr, Class<T> clazz) {
		return JSON.parseArray(jsonStr, clazz).toArray();
	}

	/**
	 * Author:Jack Time:2017年9月2日下午4:26:08
	 *
	 * @param jsonStr
	 * @param clazz
	 * @return Return:List<T> Description:将Json数据转换为List
	 */
	public static <T> List<T> toList(String jsonStr, Class<T> clazz) {
		return JSON.parseArray(jsonStr, clazz);
	}

	/**
	 * 将javabean转化为序列化的JSONObject对象
	 *
	 * @param bean
	 * @return
	 */
	public static JSONObject beanToJsonObj(Object bean) {
		String jsonStr = JSON.toJSONString(bean);
		JSONObject objectJson = (JSONObject) JSON.parse(jsonStr);
		return objectJson;
	}

	/**
	 * json字符串转化为map
	 *
	 * @param jsonStr
	 * @return
	 */
	public static Map<?, ?> stringToCollect(String jsonStr) {
		Map<?, ?> map = JSONObject.parseObject(jsonStr);
		return map;
	}

	/**
	 * 将map转化为string
	 *
	 * @param map
	 * @return
	 */
	public static String collectToString(Map<?, ?> map) {
		String jsonStr = JSONObject.toJSONString(map);
		return jsonStr;
	}

}