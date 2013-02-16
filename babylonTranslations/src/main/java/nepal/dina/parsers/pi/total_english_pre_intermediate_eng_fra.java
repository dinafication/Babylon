package nepal.dina.parsers.pi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class total_english_pre_intermediate_eng_fra extends
		English_pre_intermediate {

	public total_english_pre_intermediate_eng_fra(String pathName)
			throws IOException {
		super(pathName);
		// TODO Auto-generated constructor stub
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

}
