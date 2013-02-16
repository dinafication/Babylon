package nepal.dina.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import nepal.dina.data.json.translated.WordTranslation;
import nepal.dina.data.json.translated.WordTranslationList;
import nepal.dina.io.FileIO;
import nepal.dina.io.JsonIO;
import nepal.dina.io.XMLWriter1;
import nepal.dina.io.parsers.e.ElementaryWordlist;
import nepal.dina.io.parsers.e.EngElementary;
import nepal.dina.io.parsers.e.TotalnEnglishElementary;
import nepal.dina.parsers.Parser;
import nepal.dina.parsers.i.EngInt;
import nepal.dina.parsers.i.IntermediateWordlistFrench;
import nepal.dina.parsers.pi.English_pre_intermediate;
import nepal.dina.parsers.pi.NCE_Edge;
import nepal.dina.parsers.pi.NewEnglisFilePreIntermediateWordlist;
import nepal.dina.parsers.pi.total_english_pre_intermediate_eng_fra;
import nepal.dina.parsers.ui.EnglishUpperIntermediate;
import nepal.dina.parsers.ui.LaserB2;
import nepal.dina.parsers.ui.PremiumB2EGF;
import nepal.dina.parsers.ui.UpperIntermediateWordlist;
import nepal.dina.transalation.GlodsyTranslator;

import org.xml.sax.SAXException;

import com.memetix.mst.language.Language;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		try {

			// HashSet<String> ret = new HashSet<String>();
			//
			// Parser parser = new
			// TotalnEnglishElementary("res\\levels\\I\\Total_English_Intermediate_Wordlist_English_-_Dutch_-_French.pdf");
			// ArrayList<String> ret1 = parser.extract(15,2);
			// parser.close();
			// ret.addAll(ret1);

			// Parser parser2 = new
			// NewEnglisFilePreIntermediateWordlist("res\\levels\\PI\\prevedno\\New English File Pre-Intermediate Wordlist.pdf");
			// ArrayList<String> ret2 = parser2.extract(7,2);
			// parser.close();
			// ret.addAll(ret2);

			// Parser parser3 = new
			// total_english_pre_intermediate_eng_fra("res\\levels\\PI\\prevedno\\total-english-pre-intermediate-eng-fra.pdf");
			// ArrayList<String> ret3 = parser3.extract(5,2);
			// parser3.close();
			// ret.addAll(ret3);
			//

			/**
			 * 1. Upisi extrahirane, neprevedene rijeci u file.
			 * */
			// FileIO.writeInFile("res\\levels\\I\\I", ret);

			/**
			 * 2. Raspodijeli file u vise filova, tako da imaju manje od 500
			 * entrija.
			 * */

			/**
			 * 3. Ucitaj rijeci iz fila.
			 * */
			HashSet<String> words = FileIO
					.readFromFile("res\\wordlists\\I\\I_8");
			// HashSet<String> words =
			// FileIO.readFromFile("res\\levels\\PI\\PI_1");

			/**
			 * 4. Prevedi.
			 * */
			Language from = Language.ENGLISH;
			Language dest = Language.ITALIAN;
			GlodsyTranslator gt = new GlodsyTranslator(from, dest);
			HashMap<String, HashSet<String>> wordsNTranslations = gt
					.translateAll(words, "ita");
			JsonIO jio = new JsonIO();
			String jios = null;

			WordTranslationList list = new WordTranslationList();
			for (String word : wordsNTranslations.keySet()) {
				list.addWT(new WordTranslation(word, wordsNTranslations
						.get(word)));
			}

			jios = jio.getString(list);

			/**
			 * 5. Upisi u file.
			 * */
			FileIO.appendFile("res\\translations\\talian\\I\\I_eng_tal_8", jios);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
