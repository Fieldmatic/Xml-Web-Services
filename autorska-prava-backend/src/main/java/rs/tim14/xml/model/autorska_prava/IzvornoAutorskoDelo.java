package rs.tim14.xml.model.autorska_prava;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import rs.tim14.xml.model.autorska_prava.PodaciOAutorima.TAutor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"naslovIzvornogAutorskogDela",
	"autorIzvornogAutorskogDela"
})
public class IzvornoAutorskoDelo {

	@XmlElement(name = "naslov_izvornog_autorskog_dela", required = true)
	protected String naslovIzvornogAutorskogDela;
	@XmlElement(name = "autor_izvornog_autorskog_dela", required = true)
	protected TAutor autorIzvornogAutorskogDela;

	public String getNaslovIzvornogAutorskogDela() {
		return naslovIzvornogAutorskogDela;
	}

	public void setNaslovIzvornogAutorskogDela(String value) {
		this.naslovIzvornogAutorskogDela = value;
	}

	public TAutor getAutorIzvornogAutorskogDela() {
		return autorIzvornogAutorskogDela;
	}

	public void setAutorIzvornogAutorskogDela(TAutor value) {
		this.autorIzvornogAutorskogDela = value;
	}

	@Override
	public String toString() {
		final StringBuffer bf = new StringBuffer();
		bf.append("\n\t\t\tNaslov izvornog dela: '").append(naslovIzvornogAutorskogDela).append('\'');
		bf.append("\n\t\t\tAutor izvornog dela: ").append(autorIzvornogAutorskogDela);
		return bf.toString();
	}
}

