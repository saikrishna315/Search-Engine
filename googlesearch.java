package finalui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;


public class googlesearch {
	public ArrayList<ArrayList<String>> mysearch(String query) throws IOException {
        
		int num=8;
		String GOOGLE_SEARCH_URL = "https://www.google.com/search";
		String searchURL = GOOGLE_SEARCH_URL + "?q="+query+"&num="+num;
		System.out.println(searchURL);
		//without proper User-Agent, we will get 403 error
		Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
		
		//below will print HTML data, save it to a file and open in browser to compare
		//System.out.println(doc.html());
		
		//If google search results HTML change the <h3 class="r" to <h3 class="r1"
		//we need to change below accordingly
		Elements results = doc.select("h3.r > a");
		ArrayList<String> minilist= null;
		ArrayList<ArrayList<String>> resultlist=new ArrayList<ArrayList<String>>();
		for (Element result : results) {
			minilist=new ArrayList<String>();
			String linkHref = result.attr("href");
			String linkText = result.text();
			
			minilist.add(linkText);
			minilist.add(linkHref.substring(6, linkHref.indexOf("&")));
			//minilist.add(results.getResponseData().getResults().get(m).getContent());
			
			
			
			//System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
			resultlist.add(minilist);
		}
		
		
			/*for (int i = 0; i <8; i = i + 4) {
			String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&start="+i+"&q=";
		 
			//query = "Programcreek";
			String charset = "UTF-8";
		 
			URL url = new URL(address + URLEncoder.encode(query, charset));
			Reader reader = new InputStreamReader(url.openStream(), charset);
			GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
			System.out.println(results.getresponseDetails());
			// Show title and URL of each results
			for (int m = 0; m <= 3; m++) {
				ArrayList<String> minilist=new ArrayList<String>();

				minilist.add(results.getResponseData().getResults().get(m).getTitle());
				minilist.add(results.getResponseData().getResults().get(m).getUrl());
				//minilist.add(results.getResponseData().getResults().get(m).getContent());
				resultlist.add(minilist);
				
				//System.out.println("Title: " + results.getResponseData().getResults().get(m).getTitle());
				//System.out.println("URL: " + results.getResponseData().getResults().get(m).getUrl());
				//System.out.println("CONTENT: " + results.getResponseData().getResults().get(m).getContent() + "\n");
			}
			
		}
*/		
		
		
/*		String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
		String query = "programcreek";
		String charset = "UTF-8";
 
		URL url = new URL(address + URLEncoder.encode(query, charset));
		Reader reader = new InputStreamReader(url.openStream(), charset);
		GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
 
		int total = results.getResponseData().getResults().size();
		System.out.println("total: "+total);
 
		// Show title and URL of each results
		for(int i=0; i<=total-1; i++){
			System.out.println("Title: " + results.getResponseData().getResults().get(i).getTitle());
			System.out.println("URL: " + results.getResponseData().getResults().get(i).getUrl() + "\n");
			System.out.println("CONTENT: " + results.getResponseData().getResults().get(i).getContent() + "\n");
			
			
 
		}*/
		
		return resultlist;
	}
}
 
 
class GoogleResults{
 
    private ResponseData responseData;
    public ResponseData getResponseData() { return responseData; }
    public void setResponseData(ResponseData responseData) { this.responseData = responseData; }
    public String toString() { return "ResponseData[" + responseData + "]"; }
    
    private String responseDetails;
    public String getresponseDetails() { return responseDetails; }
    public void setresponseDetails(ResponseData responseData) { this.responseDetails = responseDetails; }
    
 
     class ResponseData {
        private List<Result> results;
        public List<Result> getResults() { return results; }
        public void setResults(List<Result> results) { this.results = results; }
        public String toString() { return "Results[" + results + "]"; }
    }
 
    static class Result {
        private String url;
        private String title;
        private String content;
        public String getUrl() { return url; }
        public String getTitle() { return title; }
        public String getContent(){return content;}
        public void setUrl(String url) { this.url = url; }
        public void setTitle(String title) { this.title = title; }
        public void setContent(String content) { this.content = content; }
        
        public String toString() { return "Result[url:" + url +",title:" + title + "]"; }
    }
}
