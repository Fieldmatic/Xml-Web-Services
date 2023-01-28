package rs.tim14.xml.model.autorska_prava;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import rs.tim14.xml.model.korisnici.Adresa;
import rs.tim14.xml.model.korisnici.PunoIme;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"punoIme",
	"adresa"
})
public class Punomocnik {

	@XmlElement(name = "puno_ime", namespace = "http://www.xml.tim14.rs/korisnici", required = true)
	protected PunoIme punoIme;
	@XmlElement(namespace = "http://www.xml.tim14.rs/korisnici", required = true, nillable = true)
	protected Adresa adresa;

	public PunoIme getPunoIme() {
		return punoIme;
	}

	public void setPunoIme(PunoIme value) {
		this.punoIme = value;
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
		sb.append("\n\t\tPuno ime: ").append(punoIme);
		sb.append("\n\t\tAdresa: ").append(adresa);
		return sb.toString();
	}
}
