package org.v11.docker4j.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.v11.docker4j.query.BasicQuery;
import org.v11.docker4j.utils.DockerConfig;
/**
 * 请求 Rest 基础工具类
 * 其他具体请求特定接口类，都继承它
 * 
 * @author v11
 *
 */
public class DockerApiKit {
	protected static DockerClient client = new DockerClient();
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
	    if (DockerApiKit.loggers.containsKey(this.getClass())) {
	        logger = DockerApiKit.loggers.get(this.getClass());
	    } else {
	        logger = LoggerFactory.getLogger(this.getClass());
	        DockerApiKit.loggers.put (this.getClass(), logger);
	    }
	    return logger;
	}
	/**
	 * 获取response的body信息
	 * @param res
	 * @return
	 */
	public String getResponseBody(HttpResponse res){
		HttpEntity entity = res.getEntity();
		if(entity != null){
			String content;
			try {
				content = EntityUtils.toString(entity,"UTF-8");
				return content;
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 获取response的header信息
	 * @param response
	 * @return
	 */
	public String getHeaders(HttpResponse response){
		Header[] headers = response.getAllHeaders();
		String res = "";
    	for(Header header:headers){
    		res += "key; "+header.getName()
    				+" value:"+header.getValue();
    	}
    	return res;
	}
	
	/**
	 * Get 请求
	 * @param url Resst地址
	 * @return
	 */
	protected JSONArray url2JSONArray(String url){
		return url2JSONArray(url,null);
	}
	/**
	 * Get 请求
	 * @param url Resst地址
	 * @param pairs 请求参数
	 * @return
	 */
	protected JSONArray url2JSONArray(String url,NameValuePair[] pairs){
		JSONArray res = null;
		String str = null;
		try {
			HttpResponse response = client.get(url,pairs);
			str = getResponseBody(response);
			getLogger().debug("response jsonArray:"+str);
			res = new JSONArray(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			getLogger().error("error response:"+str);
		} 
		return res;
	}
	
	/**
	 * Get 请求
	 * @param url Resst地址
	 * @return jsonObject
	 */
	protected JSONObject url2JSONObject(String url){
		return url2JSONObject(url,null);
	}
	/**
	 * Get 请求
	 * @param url Resst地址
	 * @param pairs 请求参数
	 * @return jsonObject
	 */
	protected JSONObject url2JSONObject(String url,NameValuePair[] pairs){
		JSONObject res = null;
		String str = null;
		try {
			HttpResponse response = client.get(url,pairs);
			str = getResponseBody(response);
			getLogger().debug("response jsonArray:"+str);
			res = new JSONObject(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			getLogger().error("error response:"+str);
		}
		return res;
	}
	/**
	 * Post 请求
	 * @param url
	 * @param pairs
	 * @return
	 */
	protected JSONObject url2JSONObjectByPost(String url,NameValuePair[] pairs){
		JSONObject res = null;
		String str = null;
		try {
			HttpResponse response = client.post(url,pairs);
			str = getResponseBody(response);
			getLogger().debug("response jsonArray:"+str);
			res = new JSONObject(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			getLogger().error("error response:"+str);
		}
		return res;
	}
	public JSONObject res2JsonObejct(HttpResponse response){
		JSONObject res = null;
		String str = null;
		try {
			str = getResponseBody(response);
			getLogger().debug("response jsonArray:"+str);
			res = new JSONObject(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			getLogger().error("error response:"+str);
		}
		return res;
	}
}
