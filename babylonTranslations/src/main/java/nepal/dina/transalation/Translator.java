package nepal.dina.transalation;

import java.util.HashMap;
import java.util.HashSet;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public abstract class Translator {
	
	protected Language from;
	protected Language to;	
	
	
	public Translator(Language from, Language to){
		
		this.from = from;
		this.to = to;
				
	}
	
	
	public abstract HashMap<String, HashSet<String>> translateAll(HashSet<String> words, String dest) throws Exception;

	public HashSet<String> translate(String word, String dest) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
