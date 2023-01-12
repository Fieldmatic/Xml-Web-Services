package rs.tim14.xml.model.zahtev_za_priznanje_ziga;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "osnovPravaPrvenstva"
})
public class PravoPrvenstva {

    @XmlElement(name = "osnov_prava_prvenstva")
    protected String osnovPravaPrvenstva;
    @XmlAttribute(name = "zatrazeno_pravo_prvenstva", required = true)
    protected boolean zatrazenoPravoPrvenstva;

    public String getOsnovPravaPrvenstva() {
        return osnovPravaPrvenstva;
    }
    public void setOsnovPravaPrvenstva(String value) {
        this.osnovPravaPrvenstva = value;
    }
    public boolean isZatrazenoPravoPrvenstva() {
        return zatrazenoPravoPrvenstva;
    }
    public void setZatrazenoPravoPrvenstva(boolean value) {
        this.zatrazenoPravoPrvenstva = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n\t\tOsnov prava prvenstva: '").append(osnovPravaPrvenstva).append('\'');
        sb.append("\n\t\tZatrazeno pravo prvenstva: ").append(zatrazenoPravoPrvenstva);
        return sb.toString();
    }
}
