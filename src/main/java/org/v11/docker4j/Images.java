package org.v11.docker4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.v11.docker4j.http.DockerApiKit;
import org.v11.docker4j.query.BasicQuery;
import org.v11.docker4j.query.BuildImageQuery;
import org.v11.docker4j.utils.DockerConfig;

public class Images extends DockerApiKit{
	String basicUrl = DockerConfig.getValue("baseURL");
	/**
	 * list images
	 * @param all 1 or 0, show all information about images
	 * @return
	 */
	public JSONArray listImages(int all){
		String url = basicUrl+"/images/json?all="+all;
		return url2JSONArray(url);
	}
	/**
	 * build it from DockerFile-unfinish
	 * @param query
	 * @return
	 */
	public boolean buildImageFromDockerFile(BuildImageQuery query){
		String url = basicUrl+"/build";
		getLogger().debug(url);
		try {
			client.post(url, query.generatePairs());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * Return low-level information on the image name
	 * @param name
	 * @return
	 */
	public JSONObject inspectImage(String name){
		return url2JSONObject(basicUrl+"/images/"+name+"/json");
	}
	/**
	 * Return the history of the image name
	 * @param name
	 * @return
	 */
	public JSONArray hostoryImages(String name){
		String url = basicUrl+"/images/"+name+"/history";
		return url2JSONArray(url);
	}
	/**
	 * Push the image name on the registry
	 * @param name
	 * @return
	 */
	public boolean pushImage(String name){
		String url = basicUrl+"/images/"+name+"/push";
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
	 * Tag the image name into a repository
	 * @param name
	 * @param repo
	 * @param force
	 * @param tagName
	 * @return
	 */
	public boolean tagImage(String name,String repo,int force,String tagName){
		String url = basicUrl+"/images/"+name+"/tag?repo="+repo+"&force="+force+"&tag="+tagName;
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
	 * Remove the image name from the filesystem
	 * @param name
	 * @return
	 */
	public boolean removeImage(String name,int force){
		String url = basicUrl+"/images/"+name+"?force="+force;
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
	 * Search for an image on Docker Hub.
	 * @param name
	 * @return
	 */
	public JSONArray searchImages(String name){
		return url2JSONArray(basicUrl+"/images/search?term="+name);
	}
	/**
	 * Build an image from a Dockerfile
	 * @param path
	 * @param name
	 * @return
	 */
	public JSONObject buildImageFromDockerFile(String path,String name){
		String url = DockerConfig.getValue("baseURL")+"/build";
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("dockerfile", path));
		param.add(new BasicNameValuePair("t", name));
		getLogger().debug(url);
		HttpResponse res = null;
		try {
			NameValuePair[] names = new BasicNameValuePair[param.size()];
			names = param.toArray(names);
			res = client.post(url, names);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return null;
		}
		return res2JsonObejct(res);
		
	}
	
}
