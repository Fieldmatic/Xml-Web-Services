package rs.tim14.xml.itext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HTMLTransformer {
	
	private static DocumentBuilderFactory documentFactory;
	
	private static TransformerFactory transformerFactory;
	
	public static final String INPUT_FILE = "data/a1-primer1.xml";
	
	public static final String XSL_FILE = "data/a1.xsl";
	
	public static final String HTML_FILE = "gen/itext/a1.html";
	
	//public static final String OUTPUT_FILE = "gen/itext/a1.pdf";

	static {
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);
		transformerFactory = TransformerFactory.newInstance();
		
	}

//    public void generatePDF(String filePath) throws IOException, DocumentException {
//		Document document = new Document();
//		PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(filePath)));
//		document.open();
//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, Files.newInputStream(Paths.get(HTML_FILE)));
//		document.close();
//
//    }

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
    
    public void generateHTML(String xmlPath, String xslPath) throws FileNotFoundException {
    	
		try {
			StreamSource transformSource = new StreamSource(new File(xslPath));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
			DOMSource source = new DOMSource(buildDocument(xmlPath));
			StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
			transformer.transform(source, result);
			
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}

	}
    
    public static void main(String[] args) throws IOException, DocumentException {

    	System.out.println("[INFO] " + HTMLTransformer.class.getSimpleName());
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(HTML_FILE);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		HTMLTransformer htmlTransformer = new HTMLTransformer();
//
		htmlTransformer.generateHTML(INPUT_FILE, XSL_FILE);
		//pdfTransformer.generatePDF(OUTPUT_FILE);
		
		System.out.println("[INFO] File \"" + HTML_FILE + "\" generated successfully.");
		System.out.println("[INFO] End.");
    }
    
}
