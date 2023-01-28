package rs.tim14.xml.model.autorska_prava;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"putanjaDoPrimera",
	"tipPrimera"
})
public class PrimerAutorskogDela {

	@XmlElement(name = "putanja_do_primera", required = true)
	protected String putanjaDoPrimera;
	@XmlElement(name = "tip_primera", required = true)
	protected TTipPrimera tipPrimera;

	public String getPutanjaDoPrimera() {
		return putanjaDoPrimera;
	}

	public void setPutanjaDoPrimera(String value) {
		this.putanjaDoPrimera = value;
	}

	public TTipPrimera getTipPrimera() {
		return tipPrimera;
	}

	public void setTipPrimera(TTipPrimera value) {
		this.tipPrimera = value;
	}

	@Override
	public String toString() {
		final StringBuffer bf = new StringBuffer();
		bf.append("\n\t\t\tPutanja do primera: '").append(putanjaDoPrimera).append('\'');
		bf.append("\n\t\t\tTip primera: ").append(tipPrimera);
		return bf.toString();
	}
}
