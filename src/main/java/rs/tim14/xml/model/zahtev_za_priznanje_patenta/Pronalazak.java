package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"nazivPronalaskaRs",
	"nazivPronalaskaEng"
})
public class Pronalazak {

	@XmlElement(name = "naziv_pronalaska_rs", required = true)
	protected String nazivPronalaskaRs;
	@XmlElement(name = "naziv_pronalaska_eng", required = true)
	protected String nazivPronalaskaEng;

	public String getNazivPronalaskaRs() {
		return nazivPronalaskaRs;
	}

	public void setNazivPronalaskaRs(String value) {
		this.nazivPronalaskaRs = value;
	}

	public String getNazivPronalaskaEng() {
		return nazivPronalaskaEng;
	}

	public void setNazivPronalaskaEng(String value) {
		this.nazivPronalaskaEng = value;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("naziv pronalaska (rs): '").append(nazivPronalaskaRs).append('\'');
		sb.append(", naziv pronalaska (eng): '").append(nazivPronalaskaEng).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
