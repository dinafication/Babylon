package nepal.dina.parsers.pi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;

public class NewEnglisFilePreIntermediateWordlist extends
		English_pre_intermediate {

	public NewEnglisFilePreIntermediateWordlist(String pathName)
			throws IOException {
		super(pathName);
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
				
				
				words.add(new String(lineArr[0].trim().substring(0, lineArr[0].length()-1)));
		}

		removeWaste();

		return words;
	}

	
}
