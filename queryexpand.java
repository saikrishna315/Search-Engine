package finalui;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import finalui.LuceneIndexer;

public class Queryexpand {
    
    static ArrayList<String> al=new ArrayList();
    static ArrayList<String> bl=new ArrayList();
	
    public Queryexpand() {
      
    }
    
    public  String caller(String query)
    {
        Queryexpand e1 = new Queryexpand();
        Queryexpand e2 = new Queryexpand();
        //System.out.println("in query expand.........."+query);
        ArrayList<String> urls=new ArrayList<String>();
        //ArrayList<String> indexresults2=new ArrayList<String>();
        LuceneIndexer ob1=new LuceneIndexer();
        urls=ob1.searchIndex(query, "./luceneIndexDir/Index_New");
		String expandedquery=e1.urlReader(urls,query);
        //System.out.println("Expanded query is "+expandedquery);
        return expandedquery;
       
    }
    
    public String urlReader(List<String> urls, String query){
    	
        List<String> QuestionLemma=new ArrayList<String>();
        TreeMap<Integer,List<String>> docs=new TreeMap();	
        Set<String> vocabulary = new TreeSet<String>();
        List<String> vocabularyList=new ArrayList<String>();
        TreeMap<String,List<Integer>> QuestionLemmaPosition = new TreeMap();
        TreeMap<String,List<Integer>> findPos= new TreeMap();
        String expandedQuery = "";
        String newexpandedQuery="";
    		
    		String[] arr1={"tennis","us open","australia open","tennis","wimbledon"};
    		int min=0;
    		int max=4;
    		Random random=new Random();
    		int num=random.nextInt(max - min + 1) + min;
            int i =0;
            stopWords();
            commonWords();
            query = query.toLowerCase();
            query = query.replaceAll("\\?","");     // code to be replaced
            expandedQuery = expandedQuery + query +" ";
            /* For Reading Query */
        Queryexpand qe = new Queryexpand();
       List<String> l= Arrays.asList(query.split(" "));
               //new ArrayList<String>();
               
               //qe.lemmatize(query);
       Iterator<String> it=l.iterator();
				     while(it.hasNext()){
				    	 String key=(String)it.next();
                                         key = replaceSpecialCharacters(key);
				    	 if(!al.contains(key) && !bl.contains(key)){
				    		 QuestionLemma.add(key);
				    	 }
				     }
        /* For retrieving text from URL */
        for( i=0; i < urls.size(); i++){
            BodyContentHandler handler = new BodyContentHandler(200*1024*1024);
      Metadata metadata = new Metadata();
      
      FileInputStream inputstream;
            try {
                URL oracle = new URL(urls.get(i));
                BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
                StringBuffer sb= new StringBuffer();
                String inputLine =""; 
                String inputLine1 = "";
                while ((inputLine1 = in.readLine()) != null)
                    inputLine = inputLine+inputLine1;                
                    inputLine = inputLine.replaceAll("\\.", "");
                    //    Content from Html is retrieved
                    in.close();                                        
                    File myFile = new File("html"+i+".txt");
                    FileWriter fW = new FileWriter(myFile);
                        if(myFile.delete()) {
                            myFile.createNewFile();
                            fW.write(inputLine);
                            fW.flush();
                            fW.close();
                        } 
                        else
                        {
                           myFile.createNewFile();
                           fW.write(inputLine);
                            fW.flush();
                            fW.close();
                        }
                inputstream = new FileInputStream(new File("html"+i+".txt"));
                ParseContext pcontext = new ParseContext();
                HtmlParser htmlparser = new HtmlParser();
                htmlparser.parse(inputstream, handler, metadata,pcontext);
                    Queryexpand qe1 = new Queryexpand();
                    //String sample = replaceSpecialCharacters(handler.toString());
                   List<String> q = new LinkedList<String>(Arrays.asList( handler.toString().toLowerCase().split(" ")));
                           //qe1.lemmatize(handler.toString().toLowerCase());
                   q.removeAll(al);
                   q.removeAll(bl);
                   q.remove("music");
                    q.remove("rights");
                    q.remove("corp");
                    q.remove("reserved");
                    q.remove("copyright");
                    q.remove("ibm");
                    q.remove("privacymediajobscontactsite");
                    q.remove("cookies");
                    q.remove("privacy");
                    q.remove("policy");
                    q.remove("photoprevious");
                    q.remove("vs");
                    q.remove("previous");
                   for(int p = 0; p < QuestionLemma.size(); p++)      
                     if(!q.contains(QuestionLemma.get(p)))
                         q.add(QuestionLemma.get(p));
                   for(int k = 0; k < q.size(); k++)
                   {
                       String s =replaceSpecialCharacters(q.get(k));
                       if(!al.contains(s) && !bl.contains(s)){
                        vocabulary.add(s);
                        }
                   }   
                   vocabulary.removeAll(al);
                   vocabulary.removeAll(bl);                     
                   docs.put(i,q);                                                                             
            } catch (FileNotFoundException ex) {             
               ex.printStackTrace();
               return query + " "+arr1[num];
            } catch (IOException ex) {                
                ex.printStackTrace();
                return query + " "+arr1[num];
            } catch (SAXException ex) {         
                ex.printStackTrace();
                return query +" "+arr1[num];
            } catch (TikaException ex) {
                ex.printStackTrace();
                return query + " "+arr1[num];
            }
            catch(Exception e){
                e.printStackTrace();
                return query +" "+arr1[num];
                
            }
        }
        Iterator <String> itr = vocabulary.iterator();
        while(itr.hasNext()){
            vocabularyList.add(itr.next());
        }
        List<Integer> pos;
        for(i = 0;i < vocabularyList.size(); i++)
            {
                 pos = new ArrayList<Integer>();
                for(int j=0;j<docs.size();j++)
                {
                pos.add(docs.get(j).indexOf(vocabularyList.get(i)));
                }
                findPos.put(vocabularyList.get(i),pos);
                
            }
        for(i = 0; i <QuestionLemma.size(); i++)
        {
        
                if(findPos.get(QuestionLemma.get(i))== null)
                {
                    return query + " "+arr1[num];
                }
            for(int z =0; z < findPos.get(QuestionLemma.get(i)).size();z++)
            {
            QuestionLemmaPosition.put(QuestionLemma.get(i),findPos.get(QuestionLemma.get(i)));
            }
        }
       
        /* For retrieving text from URL */
       for(int z =0; z < QuestionLemmaPosition.size(); z++ ){
           TreeMap<Double,String> findCor= new TreeMap();
               NavigableSet nset = findCor.descendingKeySet();
           List<Integer> lis = QuestionLemmaPosition.get(QuestionLemma.get(z));
           //System.out.print(lis);
            for(Map.Entry<String,List<Integer>> entry:findPos.entrySet()){
               String key = entry.getKey();
               //System.out.println(key);
               
               List<Integer> value = entry.getValue();
               double distance = 0;
               double metric = 0;
                for(int docnum = 0; docnum < value.size(); docnum++){
                    if(value.get(docnum)== -1)
                    {
                    value.set(docnum, Integer.MAX_VALUE);
                    }
                    distance =Math.abs(lis.get(docnum)-value.get(docnum));
                   
                    if(distance == 0)
                        distance = Integer.MAX_VALUE;
                    metric = metric + 1/distance;
                    findCor.put(metric, key);
                     nset=findCor.descendingKeySet();
                
                     
                }
                
            }
            
            Iterator<Integer> iterator = nset.iterator();
      int count = 0;
      
      while(iterator.hasNext()&& count<2)
      {
          
          if(!al.contains(findCor.get(iterator.next())) &&!bl.contains(findCor.get(iterator.next()))){
          count = count + 1;
          expandedQuery = expandedQuery +(findCor.get(iterator.next()))+" ";
          }
      }                                   
       }              
      String arr[] = expandedQuery.split(" ");      
      Set<String> set = new HashSet<String>();
       for ( i =0; i < arr.length; i++) {      
           if (set.add(arr[i]) == false) {           
               }
           else{
        	   	newexpandedQuery = newexpandedQuery +arr[i]+ " ";
        	   	
        	   	
           }
       }
       newexpandedQuery=newexpandedQuery.replaceAll("\\s$", "");
       //System.out.println("new query="+newexpandedQuery.length()+"query legth="+query.length());
       if(newexpandedQuery.length()==query.length()){
    	   newexpandedQuery=newexpandedQuery+" tennis";
       }
       return newexpandedQuery;
    }
    
    public static String replaceSpecialCharacters(String sCurrentLine){
    	sCurrentLine=sCurrentLine.toString().replaceAll("<.*>",""); 
    	sCurrentLine=sCurrentLine.replaceAll("'s", ""); 
    	sCurrentLine=sCurrentLine.replaceAll("[+^:,?';=%#&~`$!@*_)/(}{]","");
    	sCurrentLine=sCurrentLine.replaceAll("-","\t");
    	sCurrentLine=sCurrentLine.replaceAll("\\.", "");
    	sCurrentLine=sCurrentLine.replaceAll("\\d*",""); 
        sCurrentLine=sCurrentLine.replaceAll("[^\\x00-\\x7F]", "");
        sCurrentLine=sCurrentLine.replaceAll("[^\\p{L}\\p{Nd}]+", "");
        sCurrentLine=sCurrentLine.replaceAll("\\.*photo\\.*","");
        sCurrentLine=sCurrentLine.replaceAll("\\.*thumbnail\\.*","");
        sCurrentLine=sCurrentLine.toLowerCase();
    	return sCurrentLine;
   }
    
    

    public  static void stopWords(){
    	BufferedReader br1=null;
    	try{
    	br1 = new BufferedReader(new FileReader("C:\\Users\\charan\\Desktop\\finaluiorigina\\finaluiorigina\\src\\finalui\\stopwords")); 
    	String sCurrentLine;
            while ((sCurrentLine = br1.readLine()) != null) {
		al.add(sCurrentLine);
            }
		br1.close();
    	}
    	catch(Exception e){
    		
    		e.printStackTrace();
    	}
    }
    
    public  static void commonWords(){
    	BufferedReader br1=null;
    	try{
    	br1 = new BufferedReader(new FileReader("C:\\Users\\charan\\Desktop\\finaluiorigina\\finaluiorigina\\src\\finalui\\common_words")); 
    	String sCurrentLine;
            while ((sCurrentLine = br1.readLine()) != null) {
		bl.add(sCurrentLine);
            }
		br1.close();
    	}
    	catch(Exception e){
    		
    		e.printStackTrace();
    	}
    }
    
    
}
