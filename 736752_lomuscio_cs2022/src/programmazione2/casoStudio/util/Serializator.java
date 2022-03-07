/**
 * 
 */
package programmazione2.casoStudio.util;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import org.w3c.dom.Document;

/**
 * @author lucal
 *
 */
public class Serializator {

	public static void serializzaOggetto(Object obj, String fileSerializzato) {
		FileOutputStream outFile;
		try {
			outFile = new FileOutputStream(fileSerializzato);
			ObjectOutputStream outStream = new ObjectOutputStream(outFile);
			outStream.writeObject(obj);
			outFile.close();
			outStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERRORE: file " + fileSerializzato + " non trovato");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Object deserializzaOggetto(String fileSerializzato) {
		Object risultato = null;
		try {
			FileInputStream inFile = new FileInputStream(fileSerializzato);
			ObjectInputStream inStream = new ObjectInputStream(inFile);
			risultato = inStream.readObject();
			inFile.close();
			inStream.close();
		} catch (EOFException e) {
		} catch (FileNotFoundException e) {
			System.out.println("ERRORE: file " + fileSerializzato + " non trovato");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return risultato;
	}

	public static void writeToXML(String fileName, Map<String, Object> fields) {
		Document dom;
		Element element = null;

		// instance of a DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// use factory to get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// create instance of DOM
			dom = db.newDocument();

			// create the root element
			Element rootEle = dom.createElement("root");

			Iterator<Entry<String, Object>> iterator = fields.entrySet().iterator();

			while (iterator.hasNext()) {
				Map.Entry<String, Object> coppia = (Entry<String, Object>) iterator.next();
				// create data elements and place them under root
				element = dom.createElement(coppia.getKey());
				element.appendChild(dom.createTextNode(coppia.getValue().toString()));
				rootEle.appendChild(element);
			}

			dom.appendChild(rootEle);

			try {
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

				transformer.transform(new DOMSource(dom),
						new StreamResult(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8")));
			} catch (TransformerException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} catch (ParserConfigurationException e) {
			System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + e);
		}
	}

	public static String readElementToXML(String fileName, String field) {
		Document dom;
		// Make an instance of the DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// use the factory to take an instance of the document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the
			// XML file
			dom = db.parse(fileName);

			Element doc = dom.getDocumentElement();

			String result = getTextValue(doc, field);
			if (result != null) {
				if (!result.isEmpty())
					return result;
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	private static String getTextValue(Element doc, String tag) {
		String value = null;
		NodeList nl;
		nl = doc.getElementsByTagName(tag);
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			value = nl.item(0).getFirstChild().getNodeValue();
		}
		return value;
	}

}
