package nepal.dina.parsers.pi;

import nepal.dina.parsers.Parser;
import nepal.dina.util.Help;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class English_pre_intermediate extends Parser {


	public English_pre_intermediate(String pathName) throws IOException {

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

		String[] lineArr;
		int i = 0;
		for (String line : lines) {

			lineArr = line.split("[/\\[\\(]");

			if (lineArr == null || lineArr.length == 0)
				continue;

			lineArr[0] = lineArr[0].trim();
			lineArr[0] = lineArr[0].toLowerCase();

			if (lineArr[0] == "" || lineArr[0].contains("?"))
				continue;

			if (lineArr[0].length() < 2)
				continue;

			if (!words.contains(lineArr[0]))
				words.add(new String(lineArr[0]));
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
