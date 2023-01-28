package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import rs.tim14.xml.model.korisnici.TFizickoLice;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"neZeliDaBudeNaveden",
	"putanjaDoIzjave",
	"tipIzjave",
	"pronalazac"
})
public class PodaciOPronalazacu {

	@XmlElement(name = "ne_zeli_da_bude_naveden")
	protected boolean neZeliDaBudeNaveden;
	@XmlElement(name = "putanja_do_izjave")
	protected String putanjaDoIzjave;
	@XmlElement(name = "tip_izjave")
	protected String tipIzjave;
	protected TFizickoLice pronalazac;
	public boolean isNeZeliDaBudeNaveden() {
		return neZeliDaBudeNaveden;
	}
	public void setNeZeliDaBudeNaveden(boolean value) {
		this.neZeliDaBudeNaveden = value;
	}
	public String getPutanjaDoIzjave() {
		return putanjaDoIzjave;
	}

	public void setPutanjaDoIzjave(String value) {
		this.putanjaDoIzjave = value;
	}
	public String getTipIzjave() {
		return tipIzjave;
	}
	public void setTipIzjave(String value) {
		this.tipIzjave = value;
	}
	public TFizickoLice getPronalazac() {
		return pronalazac;
	}
	public void setPronalazac(TFizickoLice value) {
		this.pronalazac = value;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		sb.append("\n\t\tPronalazac ne zeli da bude naveden: ").append(neZeliDaBudeNaveden);
		if (putanjaDoIzjave != null) sb.append("\n\t\tPutanja do izjave: '").append(putanjaDoIzjave).append('\'');
		if (tipIzjave != null) sb.append("\n\t\tTip izjave: '").append(tipIzjave).append('\'');
		sb.append("\n\t\tPronalazac: ").append(pronalazac.toString("\t\t\t"));
		return sb.toString();
	}
}
