package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"ranijaPrijava"
})
public class ZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava {

	@XmlElement(name = "ranija_prijava")
	protected List<TPrijava> ranijaPrijava;
	public List<TPrijava> getRanijaPrijava() {
		if (ranijaPrijava == null) {
			ranijaPrijava = new ArrayList<TPrijava>();
		}
		return this.ranijaPrijava;
	}

	public void setRanijePrijave(List<TPrijava> prijave) {
		this.ranijaPrijava = prijave;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ranijaPrijava.size(); i++) {
			sb.append("\n\t\tPrijava " + (i+1) + ": ").append(ranijaPrijava.get(i));
		}
		return sb.toString();
	}
}