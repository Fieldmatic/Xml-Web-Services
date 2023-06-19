package rs.tim14.xml.xslfo;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;


public class XSLFOTransformer {
	private final FopFactory fopFactory;
	private final TransformerFactory transformerFactory;

	public XSLFOTransformer() throws SAXException, IOException {
		fopFactory = FopFactory.newInstance(new File("./zig-backend/src/fop.xconf"));
		transformerFactory = new TransformerFactoryImpl();
	}

	public void generatePDF(String inputFilePath, String xslFilePath, String outputFilePath) throws Exception {
		File xslFile = new File(xslFilePath);
		StreamSource transformSource = new StreamSource(xslFile);
		StreamSource source = new StreamSource(new File(inputFilePath));
		FOUserAgent userAgent = fopFactory.newFOUserAgent();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
		Result res = new SAXResult(fop.getDefaultHandler());
		xslFoTransformer.transform(source, res);
		File pdfFile = new File(outputFilePath);
		if (!pdfFile.getParentFile().exists()) {
			pdfFile.getParentFile().mkdir();
		}
		OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
		out.write(outStream.toByteArray());
		out.close();
	}

}
