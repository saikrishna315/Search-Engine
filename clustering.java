package finalui;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Clustering {


	
	  HashMap<String,Integer> urls=new HashMap<>();
	  HashMap<Integer,ArrayList<String>> output=new HashMap<>();
	  ArrayList<String> Index=new ArrayList<String>();
	  ArrayList<ArrayList<String>> finalCluster=new ArrayList<>();
	 
	  public ArrayList<ArrayList<String>> indexResults(ArrayList<String> Index_results)
	  {
			String csvFile = "C:\\Users\\Balaji Gupta\\Desktop\\kmeans.csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";

			try {

				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {

				        // use comma as separator
					String[] documents = line.split(cvsSplitBy);
					Integer cluster_id=Integer.parseInt(documents[0]);
					urls.put(documents[1],cluster_id);
					ArrayList<String> temp=output.get(cluster_id);
					if (temp!=null) {
						temp.add(documents[1]);
					}
					else {
						temp=new ArrayList<String>();
						temp.add(documents[1]);
					}
					output.put(cluster_id,temp);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		  System.out.println(output.size());
		  Index=new ArrayList<>();
			for(String url:Index_results)
				Index.add(url);
			return clusteredResults();
	  }
	  
	  public ArrayList<ArrayList<String>> clusteredResults()
	  {
		  for (String index:Index) {
				Integer cluster_id=urls.get(index);
				ArrayList<String> relevant_urls=output.get(cluster_id);
				if(cluster_id!=null){
					Integer it=relevant_urls.indexOf(index);
					ArrayList<String> temp=new ArrayList<>();
					temp.add(cluster_id.toString());
					if(it+5>=relevant_urls.size())
						it=it-10;
					for(int i=it-5;i<=it+5;i++)
					{
						temp.add(relevant_urls.get(i));
					}
					finalCluster.add(temp);
				}
			}
		  return finalCluster;
	  }
	  
	  public void run() {

		String csvFile = "C:\\Users\\charan\\Desktop\\finaluiorigina\\finaluiorigina\\src\\finalui\\kmeans.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] documents = line.split(cvsSplitBy);
				Integer cluster_id=Integer.parseInt(documents[0]);
				urls.put(documents[1],cluster_id);
				ArrayList<String> temp=output.get(cluster_id);
				if (temp!=null) {
					temp.add(documents[1]);
				}
				else {
					temp=new ArrayList<String>();
					temp.add(documents[1]);
				}
				output.put(cluster_id,temp);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	  }

	
}
