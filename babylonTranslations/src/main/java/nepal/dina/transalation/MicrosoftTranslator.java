package nepal.dina.transalation;


import java.util.HashMap;
import java.util.HashSet;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import nepal.dina.transalation.Translator;


// TDODO microsoft and glodsy
public class MicrosoftTranslator extends Translator{
	
	private Language from;
	private Language to;
	
	
	
	public MicrosoftTranslator(Language from, Language to){
		
		super(from, to);
		
		Translate.setClientId("clientId1");
        Translate.setClientSecret("AtSA1tIcUJUvMwIQVMzBdcsXyUeNaxVVVix1gAHHnQY=");
	}



	@Override
	public HashSet<String> translate(String word, String dest) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public HashMap<String, HashSet<String>> translateAll(HashSet<String> words, String dest)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
