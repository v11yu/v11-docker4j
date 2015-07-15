package org.v11.docker4j;

import java.io.IOException;

import org.json.JSONObject;
import org.v11.docker4j.http.DockerApiKit;
import org.v11.docker4j.utils.DockerConfig;

public class Miscs extends DockerApiKit{
	String basicUrl = DockerConfig.getValue("baseURL");
	public JSONObject info(){
		return url2JSONObject(basicUrl+"/info");
	}
	public JSONObject version(){
		return url2JSONObject(basicUrl+"/version");
	}
	public boolean ping(){
		String url = basicUrl+"/_ping";
		getLogger().debug(url);
		try {
			client.get(url,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			getLogger().error(e.getMessage());
			return false;
		}
		return true;
	}
	
}
