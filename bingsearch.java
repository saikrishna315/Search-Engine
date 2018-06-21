package finalui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

public class bingsearch {
    public  ArrayList<ArrayList<String>> bingsearcher(String query2) throws Exception {

    	ArrayList<ArrayList<String>> bingresult=new ArrayList<ArrayList<String>>();
    	String accountKey = "ZMJgaJl3wg/xYkiNOnC7kU+z901fw/q76sptaiPdVEU";
         String bingUrlPattern = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%%27%s%%27&$format=JSON";
          query2="'"+query2+"'";
         String query = URLEncoder.encode(query2, Charset.defaultCharset().name());
         String bingUrl = String.format(bingUrlPattern, query);

         String accountKeyEnc =  Base64.encodeBase64String((accountKey + ":" + accountKey).getBytes());
                                 
         URL url = new URL(bingUrl);
         URLConnection connection = url.openConnection();
        connection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);

        try ( BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
             StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
             JSONObject json = new JSONObject(response.toString());
             JSONObject d = json.getJSONObject("d");
             JSONArray results = d.getJSONArray("results");
             int resultsLength = results.length();
            for (int i = 0; i < resultsLength; i++) {
            	ArrayList<String> templist=new ArrayList<String>();
                 JSONObject aResult = results.getJSONObject(i);
                System.out.println(aResult.get("Url"));
                templist.add(aResult.get("Title").toString());
                templist.add(aResult.get("Url").toString());
                //templist.add(aResult.get("Description").toString());
                
                bingresult.add(templist);
            }
        }
        return bingresult;
    }	

}
