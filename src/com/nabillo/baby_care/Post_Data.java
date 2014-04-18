import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

public class Post_Data extends AsyncTask<Void, Void, String>
{
	private String addr = null;
	private String json_data = null;
	private HttpURLConnection urlConnection=null;
	
	private static final String TAG = "Post_Data";
	
	public void Post_Data (String addr, String json_data)
	{
		this.addr = addr;
		this.json_data = json_data;
	}
	
	private boolean isConnected()
	{
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		    if (networkInfo != null && networkInfo.isConnected())
	    		return true;
		    else
	    		return false;
	}
	
	private String send()
	{
		String result;
		
		if (!isConnected())
		{
			Toast.makeText(getApplicationContext(), R.String.Not_Connected, Toast.LENGTH_SHORT).show();
			return "Error"
		}
		try
		{
			URL url = new URL(addr);  
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoInput (true);
			urlConnection.setDoOutput(true);   
			urlConnection.setRequestMethod ("POST");  
			urlConnection.setUseCaches (false);  
			urlConnection.setConnectTimeout (10000);  
			urlConnection.setReadTimeout (10000);  
			urlConnection.setRequestProperty ("Content-Type","application/json");   
			urlConnection.connect ();
			
			OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());

			out.writeUTF(URLEncoder.encode(json_data,"UTF-8"));
			out.flush ();
			out.close ();
            
			int HttpResult =urlConnection.getResponseCode();
			urlConnection.disconnect();
			
			if (HttpResult ==HttpURLConnection.HTTP_OK)
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
				String line = null;
				while ((line = br.readLine()) != null)
				{
				    sb.append(line + "\n");
				}
				br.close();
				
				System.out.println(""+sb.toString());
				result = sb.toString();
			}
			else
			{  
				System.out.println(urlConnection.getResponseMessage());  
			}
		
		}
		catch (MalformedURLException e)
		{
		    Log.e(TAG, "URL malformed : "+e.printStackTrace());
		}
		catch (JSONException e)
		{
			Log.e(TAG, "URL malformed : "+e.printStackTrace());
		}
		catch (IOException e)
		{
		    Log.e(TAG, e.printStackTrace());
		}
		finally
		{  
			if (urlConnection!=null)
				urlConnection.disconnect();
			return result;
		}
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progress.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected String doInBackground(Void...params)
	{
		// Post request and retrive response from raspi
		return send();
	}
	
	@Override
	protected void onPostExecute(String result) {
		progressBar.setVisibility(View.INVISIBLE);
		// Set result flag positionned on activity
		return result;
	}
}
