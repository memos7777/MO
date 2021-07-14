package com.mialab.common.json;

/**
 * 扩展JsonObjec，用于一个对象输出两个不同的json格式。
 *
 */
public interface JsonSimpleObject extends JsonObject {

	String toSimpleJSONString();
}
