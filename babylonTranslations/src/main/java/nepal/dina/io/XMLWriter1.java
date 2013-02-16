package nepal.dina.io;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLWriter1 {
	
	private static Logger log = Logger.getLogger("XMLWriter1");

	private static DocumentBuilderFactory docFactory;
	private static DocumentBuilder docBuilder;

	private static void init() throws ParserConfigurationException,
			TransformerException, SAXException, IOException,
			TransformerFactoryConfigurationError {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		// docFactory.setValidating(true);
		docFactory.setNamespaceAware(true);
		docFactory.setSchema(loadSchema("res\\words.xsd"));
		docBuilder = docFactory.newDocumentBuilder();
	}

	public static void writeInNewXML(List<String> words, String fileName)
			throws ParserConfigurationException, TransformerException,
			SAXException, IOException, TransformerFactoryConfigurationError {

		init();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("tns:wordList");
		rootElement.setAttribute("xmlns:tns", "http://www.example.org/words");
		rootElement.setAttribute("xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		rootElement.setAttribute("xsi:schemaLocation",
				"http://www.example.org/words words.xsd ");
		doc.appendChild(rootElement);

		for (String w : words) {
			// word elements
			Element word = doc.createElement("tns:word");
			rootElement.appendChild(word);

			if (w == null || w == "") {
				System.out.println();
				continue;
			}

			// set id
			word.setAttribute("id", w);
			word.setIdAttribute("id", true);

			Element wordInner = doc.createElement("tns:word");
			wordInner.appendChild(doc.createTextNode(w));
			word.appendChild(wordInner);
		}

		writeInXml(doc, fileName);
	}

	private static void writeInXml(Document doc, String fileName)
			throws TransformerException {

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(fileName));

		transformer.transform(source, result);
	}

	public static void appendWords(List<String> words, String fileName)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {

		for (String w : words) {
			appendWord(w, fileName);
		}
	}

	public static void appendWord(String word, String filePath)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {

		init();
		Document doc = docBuilder.parse(filePath);

		Element rootElement = (Element) doc
				.getElementsByTagName("tns:wordList").item(0);
		Element wordElement = doc.createElement("tns:Word");

		if (word == null || word == "") {
			System.out.println();
			return;
		}

		wordElement.appendChild(doc.createTextNode(word));
		rootElement.appendChild(wordElement);

		wordElement.setAttribute("id", word);
		wordElement.setIdAttribute("id", true);

		// write the content into xml file
		writeInXml(doc, filePath);

	}

	public static void addDefinition(String word, String definition,
			String fileName) throws ParserConfigurationException,
			TransformerException, SAXException, IOException {
		System.out.println("starting add definition =" + definition + "for word - " + word);
		addPart(word, definition, fileName, "tns:definition");
	}
	
	public static void addDefinitions(Map<String, String> words, 
			String fileName) throws ParserConfigurationException,
			TransformerException, SAXException, IOException {
		
		addParts(words, fileName, "tns:definition");
	}
	
	public static void addParts(Map<String, String> words, 
			String fileName, String partName) throws ParserConfigurationException,
			TransformerException, SAXException, IOException{
		init();
		Document doc = doc = docBuilder.parse(fileName);
		
		Set<String> keys = words.keySet();
		for(String key:keys){
			
			
			Element wordElem = doc.getElementById(key);
			Element defElement = doc.createElement(partName);
			defElement.appendChild(doc.createTextNode(words.get(key)));
			wordElem.appendChild(defElement);
			
		}
		writeInXml(doc, fileName);
	}

	public static void addTranslation(String word, String definition,
			String fileName) throws ParserConfigurationException,
			TransformerException, SAXException, IOException {		
		addPart(word, definition, fileName, "tns:translation");
	}

	public static void addTranslations(Map<String, String> words, 
			String fileName) throws ParserConfigurationException,
			TransformerException, SAXException, IOException {
		
		addParts(words, fileName, "tns:translation");
	}
	
	public static void addExample(String word, String definition,
			String fileName) throws ParserConfigurationException,
			TransformerException, SAXException, IOException  {
		addPart(word, definition, fileName, "tns:example");
	}
	
	public static void addExamples(Map<String, String> words, 
			String fileName) throws ParserConfigurationException,
			TransformerException, SAXException, IOException {
		
		addParts(words, fileName, "tns:example");
	}
	private static void addPart(String word, String definition,
			String fileName, String part) throws ParserConfigurationException,
			TransformerException, SAXException, IOException {
		
		System.out.println("starting add part " + part);
		
		init();

		Document doc = docBuilder.parse(fileName);

		Element wordElem = doc.getElementById(word);

		Element defElement = doc.createElement(part);
		defElement.appendChild(doc.createTextNode(word));
		wordElem.appendChild(defElement);

		writeInXml(doc, fileName);
		
		System.out.println("end add part " + part);
		
	}

	// reader
	public List<String> getWords(String filePath)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException, TransformerFactoryConfigurationError {

		init();
		Document doc = docBuilder.parse(filePath);

		NodeList listOfWords = doc.getElementsByTagName("tns:lngWord");

		for (int i = 0; i < listOfWords.getLength(); i++) {

		}

		List<String> ret = new ArrayList<String>();

		return ret;
	}

	public static void validateXml(Schema schema, Document document) {
		try {
			// creating a Validator instance
			Validator validator = schema.newValidator();
			System.out.println();
			System.out.println("Validator Class: "
					+ validator.getClass().getName());

			// validating the document against the schema
			validator.validate(new DOMSource(document));
			System.out.println();
			System.out.println("Validation passed.");

		} catch (Exception e) {
			// catching all validation exceptions
			System.out.println();
			System.out.println(e.toString());
		}
	}

	public static Schema loadSchema(String name) {
		Schema schema = null;
		try {
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(language);

			// SchemaFactory schemaFactory= SchemaFactory.newInstance(
			// XMLConstants.W3C_XML_SCHEMA_NS_URI,
			// "javax.xml.validation.SchemaFactory",
			// null);
			schema = factory.newSchema(new File(name));

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return schema;
	}

	public static Document parseXmlDom(String name) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			// factory.setValidating(true);
			factory.setSchema(loadSchema("res\\words.xsd"));

			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(name));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return document;
	}

}
