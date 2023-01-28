package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import rs.tim14.xml.model.korisnici.Adresa;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"nacinDostavljanja",
	"adresa"
})
public class PodaciODostavljanju {

	@XmlElement(name = "nacin_dostavljanja", required = true)
	protected TNacinDostavljanja nacinDostavljanja;
	@XmlElement(namespace = "http://www.xml.tim14.rs/korisnici", nillable = true)
	protected Adresa adresa;
	public TNacinDostavljanja getNacinDostavljanja() {
		return nacinDostavljanja;
	}
	public void setNacinDostavljanja(TNacinDostavljanja value) {
		this.nacinDostavljanja = value;
	}
	public Adresa getAdresa() {
		return adresa;
	}
	public void setAdresa(Adresa value) {
		this.adresa = value;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		sb.append("\n\t\tNacin dostavljanja: ").append(nacinDostavljanja);
		if (adresa != null) sb.append("\n\t\tAdresa: ").append(adresa);
		return sb.toString();
	}
}