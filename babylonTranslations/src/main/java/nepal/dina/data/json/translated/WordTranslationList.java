package nepal.dina.data.json.translated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WordTranslationList {
	
	public WordTranslationList() {
		super();
		this.wordTranslations =  new ArrayList<>();
	}

	ArrayList<WordTranslation> wordTranslations;

	public ArrayList<WordTranslation> getWordTranslations() {
		return wordTranslations;
	}

	public void setWordTranslations(ArrayList<WordTranslation> wordTranslations) {
		this.wordTranslations = wordTranslations;
	}
	
	public void addWT(WordTranslation wt){
		wordTranslations.add(wt);
	}

}
