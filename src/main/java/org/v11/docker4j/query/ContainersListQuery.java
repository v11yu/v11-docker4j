package org.v11.docker4j.query;

import java.lang.reflect.Field;
/**
 * 用于GET /containers/json的请求参数描述
 * @author v11
 *
 */
public class ContainersListQuery extends BasicQuery{
	/** 默认false/0 , true:现实所有container，false：只现实运行时的*/
	Boolean all;
	/** 如果设置了，就显示limit数量的container，包括不在运行的。按时间顺序*/
	Integer limit;
	/** since = container_id,显示在id之后的container,不包含它自己，按时间顺序 */
	String since;
	/** since = container_id,显示在id之前的container,不包含它自己，按时间顺序 */
	String before;
	/** 默认false，true：显示container的size大小*/
	Boolean size;
	public Boolean getAll() {
		return all;
	}
	public void setAll(Boolean all) {
		this.all = all;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getSince() {
		return since;
	}
	public void setSince(String since) {
		this.since = since;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public Boolean getSize() {
		return size;
	}
	public void setSize(Boolean size) {
		this.size = size;
	}
	
}
