

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Post_Data 
{
	private String url = null;
	private JSONObject json_data = null;
	
	public void post_Data(String url, Map json_map)
	{
		this.url = url;
		set_Data(json_map)
	}
	
	private void set_Data(Map json_map)
	{
		json_data = new JSONObject();
		Iterator<Entry<String, String>> iterator = json_map.entrySet().iterator();
		while (iterator.hasNext()) 
		{
			Map.Entry<String,String> pairs = (Map.Entry<String,String>)iterator.next();
			String key = pairs.getKey();
			String value =  pairs.getValue();
			json_data.put(key, value);
		}
		
	}
	
	public void post_Data() throws JSONException
	{  
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		
		try {
			
			JSONArray postjson=new JSONArray();
			postjson.put(json_data);
			
			// Post the data:
			httppost.setHeader("json",json_data.toString());
			httppost.getParams().setParameter("jsonpost",postjson);
			
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			
			// for JSON:
			if(response != null)
			{
				InputStream is = response.getEntity().getContent();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				
				String line = null;
				try {
					while ((line = reader.readLine()) != null) {
				    	sb.append(line + "\n");
					}
				} catch (IOException e) {
			    	e.printStackTrace();
				} finally {
				    try {
				        is.close();
				    } catch (IOException e) {
			        	e.printStackTrace();
				    }
				}
				text = sb.toString();
			}
			
			tv.setText(text);
		
		}catch (ClientProtocolException e) {
		    // TODO : Auto-generated catch block
		} catch (IOException e) {
		    // TODO : Auto-generated catch block
		}
	}
}
