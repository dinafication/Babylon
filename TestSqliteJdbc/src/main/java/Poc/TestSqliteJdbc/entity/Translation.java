package Poc.TestSqliteJdbc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "translation")
public class Translation implements Serializable{
	
	

	@Id 
	@Column(name="phrase1")
	private String phrase1;
	
	@Id 
	@Column(name="language1")
	private String language1;
	
	
	@Id 
	@Column(name="phrase2")
	private String phrase2;
	
	@Id 
	@Column(name="language2")
	private String language2;
	
	

	public Translation() {

	}

	public Translation(String phrase1, String language1, String phrase2, String language2) {
		
		this.phrase1 = phrase1;
		this.language1 = language1;
		this.phrase2 = phrase2;
		this.language2 = language2;
	}

	public String getPhrase1() {
		return phrase1;
	}

	public void setPhrase1(String phrase1) {
		this.phrase1 = phrase1;
	}

	 
	public String getLanguage1() {
		return language1;
	}

	public void setLanguage1(String language1) {
		this.language1 = language1;
	}
	
	
	public String getPhrase2() {
		return phrase2;
	}

	public void setPhrase2(String phrase2) {
		this.phrase2 = phrase2;
	}

	 
	public String getLanguage2() {
		return language2;
	}

	public void setLanguage2(String language2) {
		this.language2 = language2;
	}

	
	@Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Phrase1: ").append(this.getPhrase1()).append(" |language1:").append(this.getLanguage1()).append(" | phrase2:").append(this.getPhrase2()).append(" |language2:").append(this.getLanguage2());
         
        return stringBuilder.toString();
    }

}
