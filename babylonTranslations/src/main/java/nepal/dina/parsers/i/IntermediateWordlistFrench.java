package nepal.dina.parsers.i;


import nepal.dina.parsers.Parser;
import nepal.dina.util.Help;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IntermediateWordlistFrench extends Parser {


			public IntermediateWordlistFrench(String pathName) throws IOException {

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<String> lines = Arrays.asList(text.split(textStripper
						.getLineSeparator()));

				lines = cropLines(lines, cropFirst, cropLast);
				String[] lineArr = null;
				StringBuilder word = null;
				
				for (String line : lines) {

					lineArr = line.split(" ");
					
					if(lineArr.length<1 || !line.contains("/"))continue;
					
					word = new StringBuilder();
					for(int i = 0; i < lineArr.length-2; i++){
						if(lineArr[i+1].contains("/"))word.append(lineArr[i]+" ");
					}
					
					words.add(word.toString().trim());
					
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
