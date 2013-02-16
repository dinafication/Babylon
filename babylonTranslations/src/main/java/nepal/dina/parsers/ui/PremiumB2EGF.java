package nepal.dina.parsers.ui;


import nepal.dina.parsers.Parser;
import nepal.dina.util.Help;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class PremiumB2EGF extends Parser {
		
		public PremiumB2EGF(String pathName) throws IOException {

			super(pathName);
		}

		protected List<String> cropLines(List<String> lines, int firstNum, int lastNum) {
			lines = Help.removeFirstLines(lines, firstNum);
			lines = Help.removeLastLines(lines, lastNum);
			
			return lines;
		}

		public ArrayList<String> extract(int cropFirst, int cropLast) {

			// parsiraj
			String text = null;
			try {
				text = textStripper.getText(pddDocument);
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<String> lines = Arrays.asList(text.split(textStripper
					.getLineSeparator()));

			lines = cropLines(lines, cropFirst, cropLast);

			String[] lineArr;
			String word = null;
			
			for (String line : lines) {
				
				if(!line.contains("/"))continue;
				lineArr = line.split(" ");

				for(int j = 0; j < lineArr.length; j++){
					if(lineArr[j].contains("/") && j>0)word = lineArr[j-1];
				}
				
				words.add(word);
				
			}

			removeWaste();

			return words;
		}

		protected void removeWaste() {

			// makni viskove, containts
			removeCnt();

			// makni special characters
			removeCntSpec();

			// makni viskove, equals
			List<String> removablesEq = new ArrayList<String>();
			removablesEq.add("");

			words = removeEq(words, removablesEq);
			removeBrackets();
			
			
			System.out.print(" lista iz pdfa");
			Help.printList(words);

		}

		@Override
		public void close() {
			try {
				pddDocument.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		


	}


