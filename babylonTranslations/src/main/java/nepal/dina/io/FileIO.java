package nepal.dina.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class FileIO {

	public static void writeInFile(String fileName, HashSet<String> words)
			throws IOException {

		FileWriter fstream = new FileWriter(fileName);
		BufferedWriter out = new BufferedWriter(fstream);

		String pom = null;
		for (String s : words) {
			pom = new String(s.trim());
			out.write(pom + "\n");
		}

		out.close();
	}
	
	public static void writeInFile(String fileName, String translations)
			throws IOException {

		FileWriter fstream = new FileWriter(fileName);
		BufferedWriter out = new BufferedWriter(fstream);
		
		out.write(translations);
		
		out.close();
	}
	
	public static void appendFile(String fileName, String translations)
			 {

		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.println(translations);
		    out.close();
		}
	    
	}

	public static HashSet<String> readFromFile(String fileName){

		FileReader fstream = null;
		BufferedReader out = null;
		HashSet<String> words = null;
		
		try {
			fstream = new FileReader(fileName);
			out = new BufferedReader(fstream);

			words = new HashSet<String>();

			String pom = out.readLine();
			while (pom != null) {
				words.add(pom);
				pom = out.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return words;
	}

}
