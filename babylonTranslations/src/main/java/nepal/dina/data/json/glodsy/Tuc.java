package nepal.dina.data.json.glodsy;

import java.util.List;


public class Tuc{
   	private List<Authors> authors;
   	private Number meaningId;
   	private List<Meanings> meanings;
   	private Phrase phrase;

 	public List<Authors> getAuthors(){
		return this.authors;
	}
	public void setAuthors(List<Authors> authors){
		this.authors = authors;
	}
 	public Number getMeaningId(){
		return this.meaningId;
	}
	public void setMeaningId(Number meaningId){
		this.meaningId = meaningId;
	}
 	public List<Meanings> getMeanings(){
		return this.meanings;
	}
	public void setMeanings(List<Meanings> meanings){
		this.meanings = meanings;
	}
 	public Phrase getPhrase(){
		return this.phrase;
	}
	public void setPhrase(Phrase phrase){
		this.phrase = phrase;
	}
}

