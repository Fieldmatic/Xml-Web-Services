package rs.tim14.xml.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {

	private static long document_id_tracker;

	public static long getNextId() {
		document_id_tracker++;
		return document_id_tracker;
	}

	public static XMLGregorianCalendar getXMLGregorianCalendarCurrentDate() throws DatatypeConfigurationException {
		GregorianCalendar gregorianDate = new GregorianCalendar();
		gregorianDate.setTime(new Date(System.currentTimeMillis()));
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianDate);
	}

}
