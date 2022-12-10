package rs.tim14.xml.jaxb;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.util.MyValidationEventHandler;
import rs.tim14.xml.util.NSPrefixMapper;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

/**
 * Primer 4.
 * 
 * Primer demonstrira konverziju iz XML u objektni (unmarshalling),
 * a zatim iz objektnog u XML model (marshalling). Prilikom konverzije 
 * iz objektnog u XML model postavljaju se custom namespace prefiksi
 * koji su definisani "NSPrefixMapper" klasom.
 *  
 */
public class JaxbParser {

	public <T> T unmarshall(String xmlPath, String jaxbContextPath, String schemaPath) throws JAXBException, SAXException {
		// Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
		//"rs.tim14.xml.model.autorska_prava"
		JAXBContext context = JAXBContext.newInstance(jaxbContextPath);

		// XML schema validacija
		//"./data/zahtev_za_autorska_prava.xsd"
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File(schemaPath));

		// Unmarshaller - zadužen za konverziju iz XML-a u objektni model
		Unmarshaller unmarshaller = context.createUnmarshaller();

		// Podešavanje unmarshaller-a za XML schema validaciju
		unmarshaller.setSchema(schema);
		unmarshaller.setEventHandler(new MyValidationEventHandler());

		// Učitavanje XML-a u objektni model
		//"./data/a1-primer1.xml"
		return (T) unmarshaller.unmarshal(new File(xmlPath));
	}

	public <T> void marshall(T objectToMarshall, String jaxbContextPath) throws JAXBException {
		// Marshaller - zadužen za konverziju iz objekta u XML
		JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
		Marshaller marshaller = context.createMarshaller();

		// Konfiguracija marshaller-a custom prefiks maperom
		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Kreira se novi imenik
		//addressBook = createAddressBook();

		System.out.println("[INFO] Marshalling customized address book:");
		marshaller.marshal(objectToMarshall, System.out);
	}

//	private AddressBook createAddressBook() {
//
//		AddressBook addressBook = new AddressBook();
//
//		addressBook.setName("Sample address book");
//		addressBook.getContacts().add(createContact1());
//		addressBook.getContacts().add(createContact2());
//
//		return addressBook;
//	}
	
//	private Person createContact1() {
//
//		Person person = new Person();
//
//		person.setFirstName("Stevo");
//		person.setLastName("Simić");
//		person.getPhones().add("021/444-555");
//		person.getPhones().add("021/444-556");
//		person.setBirthDate(MyDatatypeConverter.parseDate("1991-12-21"));
//
//		Address homeAddress = new Address();
//		homeAddress.setCountry("Serbia");
//		homeAddress.setCity("Novi Sad");
//		homeAddress.setStreet("Futoška");
//		homeAddress.setNumber(33);
//		person.setHomeAddress(homeAddress);
//
//		Address officeAddress = new Address();
//		officeAddress.setCity("Novi Sad");
//		officeAddress.setStreet("Železnička");
//		officeAddress.setNumber(4);
//		person.setOfficeAddress(officeAddress);
//
//		return person;
//	}
}
