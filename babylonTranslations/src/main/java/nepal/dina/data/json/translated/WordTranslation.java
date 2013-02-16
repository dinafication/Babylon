package nepal.dina.data.json.translated;

import java.util.HashSet;

public class WordTranslation {
	
	String word;
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public HashSet<String> getTranslations() {
		return translations;
	}

	public void setTranslations(HashSet<String> translations) {
		this.translations = translations;
	}

	HashSet<String>translations;
	
	public WordTranslation(String word, HashSet<String>translations) {
		this.word = word;
		this.translations = translations;
	}

}
