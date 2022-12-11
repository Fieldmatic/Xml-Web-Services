package rs.tim14.xml.model.autorska_prava;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import rs.tim14.xml.model.korisnici.TFizickoLice;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"autor"
})
public class PodaciOAutoru {

	@XmlElement(nillable = true)
	protected List<TAutor> autor;

	public List<TAutor> getAutor() {
		if (autor == null) {
			autor = new ArrayList<TAutor>();
		}
		return this.autor;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < autor.size(); i++) {
			sb.append("\n\t\t\tAutor " + (i+1) + ":").append(autor.get(i));
		}
		return sb.toString();
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "TAutor", propOrder = {
		"pseudonim",
		"godinaSmrti",
		"licniPodaci",
		"anonimniAutor"
	})


	public static class TAutor {

		protected String pseudonim;
		@XmlElement(name = "godina_smrti")
		@XmlSchemaType(name = "positiveInteger")
		protected BigInteger godinaSmrti;
		@XmlElement(name = "licni_podaci")
		protected TFizickoLice licniPodaci;
		@XmlElement(name = "anonimni_autor")
		protected boolean anonimniAutor;
		public String getPseudonim() {
			return pseudonim;
		}
		public void setPseudonim(String value) {
			this.pseudonim = value;
		}
		public BigInteger getGodinaSmrti() {
			return godinaSmrti;
		}
		public void setGodinaSmrti(BigInteger value) {
			this.godinaSmrti = value;
		}
		public TFizickoLice getLicniPodaci() {
			return licniPodaci;
		}
		public void setLicniPodaci(TFizickoLice value) {
			this.licniPodaci = value;
		}
		public boolean isAnonimniAutor() {
			return anonimniAutor;
		}

		public void setAnonimniAutor(boolean value) {
			this.anonimniAutor = value;
		}

		@Override
		public String toString() {
			final StringBuffer bf = new StringBuffer();
			if (pseudonim != null) bf.append("pseudonim: '").append(pseudonim).append('\'');
			if (godinaSmrti != null) bf.append("godina smrti: ").append(godinaSmrti);
			if (licniPodaci != null) bf.append(licniPodaci.toString("\t\t\t\t"));
			bf.append("\n\t\t\t\tanonimni autor: ").append(anonimniAutor);
			return bf.toString();
		}
	}
}

