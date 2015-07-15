package org.v11.docker4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.v11.docker4j.http.DockerApiKit;
import org.v11.docker4j.query.AttachContainerQuery;
import org.v11.docker4j.query.BasicQuery;
import org.v11.docker4j.query.ContainersListQuery;
import org.v11.docker4j.utils.DockerConfig;
/**
 * Rest: Containers<br />
 * 参见：http://docs.docker.com/reference/api/docker_remote_api_v1.19/
 * 
 * @author v11
 *
 */
public class Containers extends DockerApiKit{
	/* Start-- list container -- */
	public JSONArray listContainersByDefault(){
		return url2JSONArray(DockerConfig.getValue("baseURL")+"/containers/json");
	}
	public JSONArray listAllContainers(){
		return url2JSONArray(DockerConfig.getValue("baseURL")+"/containers/json"
				,new NameValuePair[]{new BasicNameValuePair("all", "1")});
	}
	public JSONArray listContainersByLimit(int limit){
		return url2JSONArray(DockerConfig.getValue("baseURL")+"/containers/json"
				,new NameValuePair[]{new BasicNameValuePair("limit", limit+"")});
	}
	public JSONArray listContainersBySinceID(String since){
		ContainersListQuery query = new ContainersListQuery();
		query.setSince(since);
		return url2JSONArray(DockerConfig.getValue("baseURL")+"/containers/json"
				+query.generateQuery());
	}
	public JSONArray listContainersByBeforeID(String before){
		ContainersListQuery query = new ContainersListQuery();
		query.setBefore(before);
		return url2JSONArray(DockerConfig.getValue("baseURL")+"/containers/json"
				+query.generateQuery());
	}
	public JSONArray listContainersByQuery(ContainersListQuery query){
		return url2JSONArray(DockerConfig.getValue("baseURL")+"/containers/json"
				+query.generateQuery());
	}
	/* End-- list container -- */
	/**
	 * inspect container
	 * @param id
	 * @return
	 */
	public JSONObject inspectContainer(String id){
		return url2JSONObject(DockerConfig.getValue("baseURL")+"/containers/"+id+"/json");
	}
	/**
	 * top running contain
	 * @param id
	 * @return
	 */
	public JSONObject topContainer(String id){
		return url2JSONObject(DockerConfig.getValue("baseURL")+"/containers/"+id+"/top");
	}
	/**
	 * Inspect changes on container id’s filesystem
	 * @param id
	 * @return 0: Modify,1: Add,2: Delete
	 */
	public JSONArray changeContainer(String id){
		return url2JSONArray(DockerConfig.getValue("baseURL")+"/containers/"+id+"/changes");
		
	}
	/**
	 * start container
	 * @param id
	 * @return
	 */
	public boolean startContainer(String id){
		try {
			client.post(DockerConfig.getValue("baseURL")+"/containers/"+id+"/start", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * Stop the container id
	 * @param id
	 * @param t number of seconds to wait before killing the container,null is default
	 * @return
	 */
	public boolean stopContainer(String id,Integer t){
		String url = DockerConfig.getValue("baseURL")+"/containers/"+id+"/stop"+
				(t==null?"":"?t="+t);
		getLogger().debug(url);
		try {
			client.post(url, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * restart the container id
	 * @param id
	 * @param t number of seconds to wait before killing the container,null is default
	 * @return
	 */
	public boolean restartContainer(String id,Integer t){
		String url = DockerConfig.getValue("baseURL")+"/containers/"+id+"/restart"+
				(t==null?"":"?t="+t);
		getLogger().debug(url);
		try {
			client.post(url, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * kill the container id
	 * @param id
	 * @return
	 */
	public boolean killContainer(String id){
		String url = DockerConfig.getValue("baseURL")+"/containers/"+id+"/kill";
		getLogger().debug(url);
		try {
			client.post(url, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * Rename the container id to a new_name
	 * @param id
	 * @param newName
	 * @return
	 */
	public boolean renameContainer(String id,String newName){
		String url = DockerConfig.getValue("baseURL")+"/containers/"+id+"/rename?name="+newName;
		getLogger().debug(url);
		try {
			client.post(url, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * Pause the container id to a new_name
	 * @param id
	 * @return
	 */
	public boolean pauseContainer(String id,String newName){
		String url = DockerConfig.getValue("baseURL")+"/containers/"+id+"/pause";
		getLogger().debug(url);
		try {
			client.post(url, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * Unpause the container id to a new_name
	 * @param id
	 * @return
	 */
	public boolean unpauseContainer(String id,String newName){
		String url = DockerConfig.getValue("baseURL")+"/containers/"+id+"/unpause";
		getLogger().debug(url);
		try {
			client.post(url, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * Attach to the container id - unfinish
	 * @param id
	 * @param query 
	 * @return
	 */
	public boolean attachContainer(String id,AttachContainerQuery query){
		String url = DockerConfig.getValue("baseURL")+"/containers/"+id+"/attach"+query.generateQuery();
		getLogger().debug(url);
		try {
			client.get(url, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
		
	}
	/**
	 * Remove the container id from the filesystem
	 * @param id
	 * @param v 1 or 0, Remove the volumes associated to the container
	 * @param force 1 or 0, Kill then remove the container
	 * @return
	 */
	public boolean removeContainer(String id,int v,int force){
		String url = DockerConfig.getValue("baseURL")+"/containers/"+id+"?v="+v+"&force="+force;
		getLogger().debug(url);
		try {
			client.delete(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * create container using remote api
	 * @param containerName
	 * @param imageName
	 * @return
	 */
	public boolean createContainer(String containerName,String imageName){
		String url = DockerConfig.getValue("baseURL")+"/containers/create?name="+containerName;
		getLogger().debug(url);
		try {
			StringEntity params =new StringEntity("{\"Image\": \""+imageName+"\"}");
			HttpResponse res = client.postByJson(url, params);
			int statusCode = res.getStatusLine().getStatusCode();
			System.out.println(statusCode);
//			ResponseHandler<String> responseHandler = new BasicResponseHandler();
//			String responseBody = responseHandler.handleResponse(res);
//			getLogger().info(responseBody);
			getLogger().info(getHeaders(res));
			getLogger().info(getResponseBody(res));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
}
