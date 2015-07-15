package org.v11.docker4j.query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 基础查询类，包含日志和生成查询字符串
 * 
 * @author v11
 *
 */
public abstract class BasicQuery {
	/** 日志容器*/
	@SuppressWarnings("rawtypes")
	private static final Map<Class,Logger> loggers = new HashMap<Class,Logger>();
	/**
	 * 获取当前类的日志，用于继承情况：子类或父类<p/>
	 * get Logger for current class for subclass or superclass
	 * @return 
	 */
	protected Logger getLogger()
	{
	    Logger logger = null;
	    if (BasicQuery.loggers.containsKey(this.getClass())) {
	        logger = BasicQuery.loggers.get(this.getClass());
	    } else {
	        logger = LoggerFactory.getLogger(this.getClass());
	        BasicQuery.loggers.put (this.getClass(), logger);
	    }
	    return logger;
	}
	/**
	 * 根据参数值生成Get请求后的参数，利用java reflection
	 * @return
	 */
	public String generateQuery(){
		String res = "";
		for(Field f:this.getClass().getDeclaredFields()){
			try {
				if(null != f.get(this)){
					if(res.equals("")) res+="?";
					else res += "&";
					res += f.getName()+"="+f.get(this);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				getLogger().error(e.getMessage());
			}
		}
		getLogger().debug("query information:"+res);
		return res;
	}
	/**
	 * 根据参数值生成Get请求后的参数，利用java reflection
	 * @return
	 */
	public NameValuePair[] generatePairs(){
		List<NameValuePair> res = new ArrayList<NameValuePair>();;
		for(Field f:this.getClass().getDeclaredFields()){
			try {
				if(null != f.get(this)){
					res.add(new BasicNameValuePair(f.getName(),f.get(this)+""));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				getLogger().error(e.getMessage());
			}
		}
		getLogger().debug("query information:"+res);
		return (NameValuePair[]) res.toArray();
	}
}
