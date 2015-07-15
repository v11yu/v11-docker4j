package org.v11.docker4j.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * 包装的HttpClient，只是为了使用顺手
 * 
 * @author v11
 *
 */
public class DockerClient {
	private HttpClient client = new DefaultHttpClient();
	private static final Logger log = LoggerFactory.getLogger(DockerClient.class);
	/**
	 * 通过get方式请求url，参数pairs. pairs＝null时，不带参加请求
	 * @param url 请求地址
	 * @param pairs 参加列表
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResponse get(String url,NameValuePair[] pairs) throws ClientProtocolException, IOException {
		//client.e
		log.debug("HttpClient Get connect to:"+url);
		if(null!=pairs){
			for(NameValuePair pair:pairs){
				String encodedParams = pair.getName()+"="+pair.getValue();
				if (-1 == url.indexOf("?")) {
					url += "?" + encodedParams;
				} else {
					url += "&" + encodedParams;
				}
			}
		}
		HttpGet httpGet = new HttpGet(url);
		return client.execute(httpGet);
	}
	/**
	 * 通过post方式提交信息
	 * @param url 地址
	 * @param pairs 字段
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResponse post(String url,NameValuePair[] pairs) throws ClientProtocolException, IOException {
		//client.e
		log.debug("HttpClient Post connect to:"+url);
		UrlEncodedFormEntity fromEntity = null;
		if(null!=pairs){
			List<NameValuePair> formParams = Arrays.asList(pairs);
			fromEntity = new UrlEncodedFormEntity(formParams, "uTF-8");
			
		}
		HttpPost post = new HttpPost(url);
	
		post.setHeader("Content-Type", " application/json");
		if(null!=fromEntity) post.setEntity(fromEntity);
		return client.execute(post);
	}
	/**
	 * Post param is jsonObject
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResponse postByJson(String url,StringEntity params) throws ClientProtocolException, IOException {
		//client.e
		log.debug("HttpClient Post connect to:"+url);
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", " application/json");
		post.setEntity(params);
		return client.execute(post);
	} 
	/**
	 * 通过delete方式提交信息
	 * @param url 地址
	 * @param pairs 字段
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResponse delete(String url) throws ClientProtocolException, IOException {
		//client.e
		log.debug("HttpClient Delete connect to:"+url);
		HttpDelete del = new HttpDelete(url);
		return client.execute(del);
	}
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet("192.168.59.103:2375/containers/json?all=1");
		HttpClient client = new DefaultHttpClient();
		HttpResponse res = client.execute(get);
	}
}
