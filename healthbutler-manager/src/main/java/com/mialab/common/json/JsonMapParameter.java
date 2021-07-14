package com.mialab.common.json;

import java.util.Map;

/**
 * 通过Map参数构建json对象。 
 *
 * @param <M>
 * @param <N>
 */
public interface JsonMapParameter<M, N> {

	String toJSONString(Map<M,N> para);
	
}
