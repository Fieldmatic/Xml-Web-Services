package rs.tim14.xml;

import javax.xml.bind.JAXBException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xmldb.api.modules.XMLResource;

import rs.tim14.xml.xmldb.ExistDbManager;

@SpringBootApplication
public class Tim14Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Tim14Application.class, args);

		ExistDbManager existDbManager = new ExistDbManager();

		existDbManager.store( "/db/xml/p1", "1.xml", "data/p1-primer1.xml");

		XMLResource xmlResource = existDbManager.load( "/db/xml/p1", "1.xml");
		System.out.println(xmlResource.getContent());

	}
}
