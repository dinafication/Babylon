package Poc.TestSqliteJdbc.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * The persistent class for the contact database table.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "word")
public class Word implements Serializable{

	@Id 
	@Column(name="phrase")
	private String phrase;
	
	@Id 
	@Column(name="language")
	private String language;
	
	@Column(name="level")
	private String level;

	public Word() {

	}

	public Word(String phrase, String language, String level) {
		this.phrase = phrase;
		this.language = language;
		this.level = level;
	}

	
	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	 @Id
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
	@Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Phrase: ").append(this.getPhrase()).append(" |language:").append(this.getLanguage()).append(" | level:").append(this.getLevel());
         
        return stringBuilder.toString();
    }

}
