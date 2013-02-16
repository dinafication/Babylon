package nepal.dina.parsers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public abstract class Parser {

	protected PDDocument pddDocument;
	protected PDFTextStripper textStripper;
	protected ArrayList<String> words;

	public Parser(String pathName) throws IOException {

		pddDocument = PDDocument.load(new File(pathName));
		textStripper = new PDFTextStripper();
		words = new ArrayList<String>();
	}

	public abstract ArrayList<String> extract(int cropFirst, int cropLast);

	public ArrayList<String> removeEq(List<String> list, List<String> removables) {

		ArrayList<String> ret = new ArrayList<String>();
		boolean remove = false;

		for (String l : list) {
			for (String r : removables) {
				if (l.equals(r)) {

					remove = true;
					break;
				}
			}
			if (!remove) {
				ret.add(l);
			}
			remove = false;
		}
		return ret;
	}

	public void removeCnt() {

		List<String> removables = getRemovablesCnt();
		ArrayList<String> ret = new ArrayList<String>();
		boolean remove = false;

		for (String w : words) {
			for (String r : removables) {
				if (w.contains(r)) {

					remove = true;
					break;
				}
			}
			if (!remove) {
				ret.add(w);
			}
			remove = false;
		}
		words = ret;
	}

	public void removeCntSpec() {

		List<String> removablesSpec = getRemovablesCntSpec();
		ArrayList<String> ret = new ArrayList<String>();
		boolean remove = false;

		int i = 0;
		for (String w : words) {
			for (String r : removablesSpec) {

				Pattern p = Pattern.compile(r, Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(w);
				remove = m.find();
				if (remove) {
					remove = true;
					break;
				}
			}
			if (!remove) {
				ret.add(w);
			}
			i++;
			remove = false;
		}

		words = ret;
	}

	protected void removeBrackets() {

		ArrayList<String> ret = new ArrayList<String>();

		for (String word : words) {
			if (word.contains("(") && word.contains(")")) {
				word = word.substring(word.length() - 3, word.length());
			}
			ret.add(word);
		}

		words = ret;
	}

	public abstract void close();

	protected List<String> getRemovablesCnt() {

		List<String> ret = new ArrayList<String>();
		ret.add("english");
		ret.add("Pre-intermediate");
		ret.add("lesson");
		ret.add("glossar");
		ret.add("german");
		ret.add("unit");

		return ret;
	}

	protected List<String> getRemovablesCntSpec() {

		List<String> ret = new ArrayList<String>();

		ret.add("υ");
		ret.add(" "); // TDODO iskljuciti
		ret.add("ɔ");
		ret.add("ə");
		ret.add("©");
		ret.add("\\)");
		ret.add("Â");
		ret.add(":");
		ret.add(",");
		ret.add(";");
		ret.add("\\?");
		ret.add("&");
		ret.add("#");
		ret.add("É™");
		ret.add("Ď");
		ret.add("ü");
		ret.add("Î");
		ret.add("¤");
		ret.add("Ă");
		ret.add("…");
		ret.add("Ľ");
		ret.add("¶");
		ret.add("â");
		ret.add("¦");
		ret.add("€");
		ret.add("Ę");
		ret.add("�");
		ret.add("ð");
		ret.add("");
		ret.add("ʃ");
		ret.add("ə");
		ret.add("");
		ret.add("");
		ret.add("Î");
		ret.add("¸");
		ret.add("θ");

		ret.add("š");
		ret.add("@");
		ret.add("ř");
		ret.add("ý");
		ret.add("ě");
		ret.add("á");
		ret.add("ě");
		ret.add("//.");
		ret.add("/");

		return ret;
	}

}
