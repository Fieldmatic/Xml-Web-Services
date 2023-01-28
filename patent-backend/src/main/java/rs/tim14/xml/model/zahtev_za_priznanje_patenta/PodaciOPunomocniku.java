package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import rs.tim14.xml.model.korisnici.TLice;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"tipPunomocnika",
	"punomocnikZaPrijemPismena",
	"punomocnik"
})
public class PodaciOPunomocniku {

	@XmlElement(name = "tip_punomocnika", required = true)
	protected TTipPunomocnika tipPunomocnika;
	@XmlElement(name = "punomocnik_za_prijem_pismena")
	protected boolean punomocnikZaPrijemPismena;
	@XmlElement(required = true)
	protected TLice punomocnik;

	public TTipPunomocnika getTipPunomocnika() {
		return tipPunomocnika;
	}

	public void setTipPunomocnika(TTipPunomocnika value) {
		this.tipPunomocnika = value;
	}

	public boolean isPunomocnikZaPrijemPismena() {
		return punomocnikZaPrijemPismena;
	}

	public void setPunomocnikZaPrijemPismena(boolean value) {
		this.punomocnikZaPrijemPismena = value;
	}

	public TLice getPunomocnik() {
		return punomocnik;
	}

	public void setPunomocnik(TLice value) {
		this.punomocnik = value;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		sb.append("\n\t\tTip punomocnika: ").append(tipPunomocnika);
		sb.append("\n\t\tPunomocnik za prijem pismena: ").append(punomocnikZaPrijemPismena);
		sb.append("\n\t\tPunomocnik: ").append(punomocnik.toString("\t\t\t"));
		return sb.toString();
	}
}
