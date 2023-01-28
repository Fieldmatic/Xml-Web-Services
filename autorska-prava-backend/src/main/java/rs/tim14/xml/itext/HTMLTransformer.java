package rs.tim14.xml.itext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class HTMLTransformer {
	
	private static DocumentBuilderFactory documentFactory;
	
	private static TransformerFactory transformerFactory;

	static {
		System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);
		transformerFactory = TransformerFactory.newInstance();
		
	}

    public org.w3c.dom.Document buildDocument(String filePath) {

    	org.w3c.dom.Document document = null;
		try {
			
			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			document = builder.parse(new File(filePath)); 

			if (document != null)
				System.out.println("[INFO] File parsed with no errors.");
			else
				System.out.println("[WARN] Document is null.");

		} catch (Exception e) {
			return null;
			
		} 

		return document;
	}
    
    public void generateHTML(String xmlPath, String xslPath, String resultPath) throws FileNotFoundException {
    	
		try {
			StreamSource transformSource = new StreamSource(new File(xslPath));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
			DOMSource source = new DOMSource(buildDocument(xmlPath));
			StreamResult result = new StreamResult(new FileOutputStream(resultPath));
			transformer.transform(source, result);
			
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}

	}
    
}
