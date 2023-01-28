package rs.tim14.xml.model.zahtev_za_priznanje_ziga;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "osnovnaTaksa",
        "taksaZaKlase",
        "taksaZaGrafickoResenje"
})
public class Takse {

    @XmlElement(name = "osnovna_taksa")
    protected double osnovnaTaksa;
    @XmlElement(name = "taksa_za_klase")
    protected double taksaZaKlase;
    @XmlElement(name = "taksa_za_graficko_resenje")
    protected double taksaZaGrafickoResenje;
    @XmlAttribute(name = "ukupna_vrednost", required = true)
    protected double ukupnaVrednost;

    public double getOsnovnaTaksa() {
        return osnovnaTaksa;
    }
    public void setOsnovnaTaksa(double value) {
        this.osnovnaTaksa = value;
    }
    public double getTaksaZaKlase() {
        return taksaZaKlase;
    }
    public void setTaksaZaKlase(double value) {
        this.taksaZaKlase = value;
    }
    public double getTaksaZaGrafickoResenje() {
        return taksaZaGrafickoResenje;
    }
    public void setTaksaZaGrafickoResenje(double value) {
        this.taksaZaGrafickoResenje = value;
    }
    public double getUkupnaVrednost() {
        return ukupnaVrednost;
    }
    public void setUkupnaVrednost(double value) {
        this.ukupnaVrednost = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n\t\tOsnovna taksa: ").append(osnovnaTaksa);
        sb.append("\n\t\tTaksa za klase: ").append(taksaZaKlase);
        sb.append("\n\t\tTaksa za graficko resenje: ").append(taksaZaGrafickoResenje);
        sb.append("\n\t\tUkupna vrednost: ").append(ukupnaVrednost);
        return sb.toString();
    }
}
