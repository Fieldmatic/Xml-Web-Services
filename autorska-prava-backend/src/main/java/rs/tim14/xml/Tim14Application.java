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
	}
}
