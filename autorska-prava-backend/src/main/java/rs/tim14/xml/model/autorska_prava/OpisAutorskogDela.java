package rs.tim14.xml.model.autorska_prava;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"putanjaDoOpisa"
})
public class OpisAutorskogDela {

	@XmlElement(name = "putanja_do_opisa", required = true)
	protected String putanjaDoOpisa;

	public String getPutanjaDoOpisa() {
		return putanjaDoOpisa;
	}

	public void setPutanjaDoOpisa(String value) {
		this.putanjaDoOpisa = value;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		sb.append("\n\t\t\tPutanja do opisa: '").append(putanjaDoOpisa).append('\'');
		return sb.toString();
	}
}
