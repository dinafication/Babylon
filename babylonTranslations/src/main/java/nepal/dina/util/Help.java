package nepal.dina.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class Help {
	
	public static ArrayList<String> removeFirstLines(List<String> lines, int num){
		
		ArrayList<String> ret = new ArrayList<String>();
		
		for(String line: lines){
			
			num--;
			if(num>0)continue;
			
			ret.add(line);			
		}
		
		return ret;		
	}
	
public static ArrayList<String> removeLastLines(List<String> lines, int num){
		
	ArrayList<String> ret = new ArrayList<String>();
		
		for(String line: lines){
			
			num++;
			if(num== line.length())continue;
			
			ret.add(line);			
		}
		
		return ret;		
	}
	
	
	
	public static void printList(List<String> list){
		for(String s:list){
			System.out.println(s);
		}
	}
	
	

	
	
}
