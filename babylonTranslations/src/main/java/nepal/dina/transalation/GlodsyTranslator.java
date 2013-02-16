package nepal.dina.transalation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import nepal.dina.data.json.glodsy.Phrase;
import nepal.dina.data.json.glodsy.Tuc;
import nepal.dina.data.json.glodsy.Word;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import com.memetix.mst.language.Language;

public class GlodsyTranslator extends Translator {

	public GlodsyTranslator(Language from, Language to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * http://glosbe.com/gapi/translate?from=eng&dest=fra&format=json&phrase=cat&pretty=true
	 * */
	public HashSet<String> translate(String word, String dest) throws Exception {
		
		
		
		StringBuilder query = new StringBuilder();
		query.append("http://glosbe.com/gapi/translate?");
		query.append("from=" + "eng");
		query.append("&dest=" + dest);
		query.append("&format=json&");
		query.append("phrase=" + word.trim());
		query.append("&pretty=true");
		
		System.out.println(query.toString());

		URL url = new URL(query.toString());
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);  
		connection.setDoOutput(true); 
		
		InputStream inStream = connection.getInputStream();
        BufferedReader input =
        new BufferedReader(new InputStreamReader(inStream));

        String inputLine; 
        StringBuilder sb = new StringBuilder();
        
        while ((inputLine = input.readLine()) != null) {  
         sb.append(inputLine);  
       
        }  
        input.close();  
        
        // TODO promjeniti tako da koristi jsonIO
        ObjectMapper om = new ObjectMapper();        
        Word w = om.readValue(sb.toString(), Word.class);
        
        HashSet<String> translations = new HashSet<String>();
       if(w.getResult().equals("ok")){
    	   
    	   // TODO da li svi prijevodi ili samo 1 ili nekoliko?
//    	   for(Tuc t:w.getTuc()){
//    		   t.getPhrase().getText();
//    	   }
    	   
    	   List<Tuc> tucList = w.getTuc();
    	   if(tucList == null || tucList.size() == 0 )return null;
    	   Tuc t = tucList.get(0);
    	   Phrase ph = t.getPhrase();
    	   if(ph == null) return null;
    	   translations.add(ph.getText());
       }
       else return null;
        
		return translations;
	}

	/**
	 * http://en.wikipedia.org/wiki/List_of_ISO_639-3_codes
	 * */
	@Override
	public HashMap<String, HashSet<String>> translateAll(HashSet<String> words, String dest) throws Exception {
		
		HashMap<String, HashSet<String>> ret = new HashMap<String, HashSet<String>>();
		HashSet<String> translations = null;
		
		for(String w:words){
			
		try{
			translations = translate(w, dest);
		}
		catch(Exception e){
			continue;
		}
			
			// is OK
			if(translations != null){
				ret.put(w, translations);
			}
			
		}
		return ret;
	}

}
