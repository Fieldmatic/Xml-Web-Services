package rs.tim14.xml.model.zahtev_za_priznanje_ziga;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "primerakZnaka",
        "spisakRobeIUsluga",
        "punomocje",
        "generalnoPunomocje",
        "naknadnoPunomocje",
        "aktiOKolektivnomZigIliZiguGarancije",
        "dokazOPravuPrvenstva",
        "dokazOUplatiTakse"
})
public class PriloziUzZahtev {

    @XmlElement(name = "primerak_znaka")
    protected String primerakZnaka;
    @XmlElement(name = "spisak_robe_i_usluga")
    protected String spisakRobeIUsluga;
    protected String punomocje;
    @XmlElement(name = "generalno_punomocje")
    protected String generalnoPunomocje;
    @XmlElement(name = "naknadno_punomocje")
    protected String naknadnoPunomocje;
    @XmlElement(name = "akti_o_kolektivnom_zig_ili_zigu_garancije")
    protected String aktiOKolektivnomZigIliZiguGarancije;
    @XmlElement(name = "dokaz_o_pravu_prvenstva")
    protected String dokazOPravuPrvenstva;
    @XmlElement(name = "dokaz_o_uplati_takse")
    protected String dokazOUplatiTakse;

    public String getPrimerakZnaka() {
        return primerakZnaka;
    }
    public void setPrimerakZnaka(String value) {
        this.primerakZnaka = value;
    }
    public String getSpisakRobeIUsluga() {
        return spisakRobeIUsluga;
    }
    public void setSpisakRobeIUsluga(String value) {
        this.spisakRobeIUsluga = value;
    }
    public String getPunomocje() {
        return punomocje;
    }
    public void setPunomocje(String value) {
        this.punomocje = value;
    }
    public String getGeneralnoPunomocje() {
        return generalnoPunomocje;
    }
    public void setGeneralnoPunomocje(String value) {
        this.generalnoPunomocje = value;
    }
    public String getNaknadnoPunomocje() {
        return naknadnoPunomocje;
    }
    public void setNaknadnoPunomocje(String value) {
        this.naknadnoPunomocje = value;
    }
    public String getAktiOKolektivnomZigIliZiguGarancije() {
        return aktiOKolektivnomZigIliZiguGarancije;
    }
    public void setAktiOKolektivnomZigIliZiguGarancije(String value) {
        this.aktiOKolektivnomZigIliZiguGarancije = value;
    }
    public String getDokazOPravuPrvenstva() {
        return dokazOPravuPrvenstva;
    }
    public void setDokazOPravuPrvenstva(String value) {
        this.dokazOPravuPrvenstva = value;
    }
    public String getDokazOUplatiTakse() {
        return dokazOUplatiTakse;
    }
    public void setDokazOUplatiTakse(String value) {
        this.dokazOUplatiTakse = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n\t\tPrimerak znaka: '").append(primerakZnaka).append('\'');
        sb.append("\n\t\tSpisak robe i usluga: '").append(spisakRobeIUsluga).append('\'');
        sb.append("\n\t\tPunomocje: '").append(punomocje).append('\'');
        sb.append("\n\t\tGeneralno punomocje: '").append(generalnoPunomocje).append('\'');
        if (naknadnoPunomocje != null) sb.append("\n\t\tNaknadno punomocje: '").append(naknadnoPunomocje).append('\'');
        if (aktiOKolektivnomZigIliZiguGarancije != null)
            sb.append("\n\t\tAkti o kolektivnom zigu ili zigu garancije: '").append(aktiOKolektivnomZigIliZiguGarancije).append('\'');
        sb.append("\n\t\tDokaz o pravu prvenstva: '").append(dokazOPravuPrvenstva).append('\'');
        sb.append("\n\t\tDokaz o uplati takse: '").append(dokazOUplatiTakse).append('\'');
        return sb.toString();
    }
}
