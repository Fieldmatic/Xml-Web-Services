package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import rs.tim14.xml.model.korisnici.TLice;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"jePronalazac",
	"podnosilac"
})
public class PodaciOPodnosiocu {

	@XmlElement(name = "je_pronalazac")
	protected boolean jePronalazac;
	@XmlElement(required = true)
	protected TLice podnosilac;
	public boolean isJePronalazac() {
		return jePronalazac;
	}
	public void setJePronalazac(boolean value) {
		this.jePronalazac = value;
	}
	public TLice getPodnosilac() {
		return podnosilac;
	}
	public void setPodnosilac(TLice value) {
		this.podnosilac = value;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		sb.append("\n\t\tPodnosilac je pronalazac: ").append(jePronalazac);
		sb.append("\n\t\tPodnosilac:").append(podnosilac.toString("\t\t\t"));
		return sb.toString();
	}
}
