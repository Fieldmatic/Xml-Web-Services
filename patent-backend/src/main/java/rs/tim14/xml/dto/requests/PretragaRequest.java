package rs.tim14.xml.dto.requests;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pretraga")
public class PretragaRequest {

	private List<String> filteri = new ArrayList<>();

	public PretragaRequest() {
	}

	@XmlElement(name = "filteri")
	public List<String> getFilteri() {
		return filteri;
	}

	public void setFilteriPretrage(final List<String> filteriPretrage) {
		filteri = filteriPretrage;
	}
}
