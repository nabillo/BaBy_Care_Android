import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

public class Post_Data 
{
	private String addr = null;
	private String json_data = null;
	HttpURLConnection urlConnection=null;
	
	public void post_Data(String addr, String json_data)
	{
		this.addr = addr;
		this.json_data = json_data;
	}
	
	public boolean isConnected()
	{
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		    if (networkInfo != null && networkInfo.isConnected())
	    		return true;
		    else
	    		return false;
	}
	
	public void post_Data() throws JSONException
	{
		try
		{
			URL url = new URL(addr);  
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoInput (true);
			urlConnection.setDoOutput(true);   
			urlConnection.setRequestMethod("POST");  
			urlConnection.setUseCaches(false);  
			urlConnection.setConnectTimeout(10000);  
			urlConnection.setReadTimeout(10000);  
			urlConnection.setRequestProperty("Content-Type","application/json");   
			urlConnection.connect();
			
			OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());

			out.writeUTF(URLEncoder.encode(json_data,"UTF-8"));
			out.flush ();
			out.close ();
            
			int HttpResult =urlConnection.getResponseCode();
			if(HttpResult ==HttpURLConnection.HTTP_OK)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
				String line = null;
				while ((line = br.readLine()) != null)
				{
				    sb.append(line + "\n");
				}
				br.close();
				
				System.out.println(""+sb.toString());
				return sb.toString()
			
			}
			else
			{  
				System.out.println(urlConnection.getResponseMessage());  
			}
		
		}catch (MalformedURLException e) {
		    // TODO : Auto-generated catch block
		} catch (IOException e) {
		    // TODO : Auto-generated catch block
		}
	}
}
