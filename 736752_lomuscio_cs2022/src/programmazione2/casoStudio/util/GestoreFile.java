package programmazione2.casoStudio.util;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import org.w3c.dom.Document;

/**
 * La classe GestoreFile gestisce la scrittura e lettura da file e la
 * serializzazione e deserializzazione di oggetti.
 * 
 * @author Luca Lomuscio
 *
 */
public class GestoreFile {

	/**
	 * Metodo che permette la serializzazione di un oggetto su file.
	 * 
	 * @param obj              oggetto da serializzare
	 * @param fileSerializzato file nel quale scrivere l'oggetto serializzato
	 */
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
		}
	}

	/**
	 * Metodo che permette la deserializzazione di un oggetto da file.
	 * 
	 * @param fileSerializzato
	 * @return oggetto letto da file serializzato.
	 * @throws ClassNotFoundException
	 */
	public static Object deserializzaOggetto(String fileSerializzato) throws ClassNotFoundException {
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
		}
		return risultato;
	}

	/**
	 * Metodo che permette la scrittura di coppie chiave valore su file xml
	 * 
	 * @param fileName path del file
	 * @param fields   elementi dell'xml
	 */
	public static void scriviSuXML(String fileName, Map<String, Object> fields) {
		Document document;
		Element element = null;

		DocumentBuilderFactory docbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docb = docbf.newDocumentBuilder();
			document = docb.newDocument();

			Element rootElement = document.createElement("root");

			Iterator<Entry<String, Object>> iterator = fields.entrySet().iterator();

			while (iterator.hasNext()) {
				Map.Entry<String, Object> coppia = (Entry<String, Object>) iterator.next();

				element = document.createElement(coppia.getKey());
				element.appendChild(document.createTextNode(coppia.getValue().toString()));
				rootElement.appendChild(element);
			}

			document.appendChild(rootElement);

			try {
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

				transformer.transform(new DOMSource(document),
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

	/**
	 * Metodo che permette la lettura di un elemento dell'xml.
	 * 
	 * @param fileName path del file
	 * @param field    da cui leggere il dato
	 * @return valore dell'elemento
	 */
	public static String leggiElementoXML(String fileName, String field) {
		Document document;

		DocumentBuilderFactory docbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder docb = docbf.newDocumentBuilder();
			document = docb.parse(fileName);

			Element doc = document.getDocumentElement();

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

	/**
	 * Metodo che restituisce il valore dell'elemento xml in stringa
	 * 
	 * @param doc
	 * @param tag
	 * @return valore del tag
	 */
	private static String getTextValue(Element doc, String tag) {
		String value = null;
		NodeList nl;
		nl = doc.getElementsByTagName(tag);
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			value = nl.item(0).getFirstChild().getNodeValue();
		}
		return value;
	}

	/**
	 * Metodo che permette la scrittura su file di una Set.
	 * 
	 * @param <T>
	 * @param set      valori da scrivere su file
	 * @param filePath
	 */
	public static <T> void scriviSetSuFile(Set<T> set, String filePath) {
		try {
			FileWriter fw = new FileWriter(filePath);
			Writer output = new BufferedWriter(fw);

			for (T element : set) {
				output.write(element.toString() + "\n\n");
			}

			output.flush();
			output.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo che permette la scrittura su file di una List.
	 * 
	 * @param <T>
	 * @param list     valori da scrivere su file
	 * @param filePath
	 */
	public static <T> void scriviListSuFile(List<T> list, String filePath) {
		try {
			FileWriter fw = new FileWriter(filePath);
			Writer output = new BufferedWriter(fw);

			for (T element : list) {
				output.write(element.toString() + "\n\n");
			}

			output.flush();
			output.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
