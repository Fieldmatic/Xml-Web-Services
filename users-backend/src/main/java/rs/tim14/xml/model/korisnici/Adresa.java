//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:13 PM CET 
//


package rs.tim14.xml.model.korisnici;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mesto",
    "postanskiBroj",
    "ulica",
    "broj",
    "drzava"
})
public class Adresa {

    @XmlElement(required = true)
    protected String mesto;
    @XmlElement(name = "postanski_broj", required = true)
    protected String postanskiBroj;
    @XmlElement(required = true)
    protected String ulica;
    @XmlElement(required = true)
    protected String broj;
    protected String drzava;
    public String getMesto() {
        return mesto;
    }
    public void setMesto(String value) {
        this.mesto = value;
    }
    public String getPostanskiBroj() {
        return postanskiBroj;
    }
    public void setPostanskiBroj(String value) {
        this.postanskiBroj = value;
    }
    public String getUlica() {
        return ulica;
    }
    public void setUlica(String value) {
        this.ulica = value;
    }
    public String getBroj() {
        return broj;
    }
    public void setBroj(String value) {
        this.broj = value;
    }
    public String getDrzava() {
        return drzava;
    }
    public void setDrzava(String value) {
        this.drzava = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("mesto: '").append(mesto).append('\'');
        sb.append(", postanskiBroj: '").append(postanskiBroj).append('\'');
        sb.append(", ulica: '").append(ulica).append('\'');
        sb.append(", broj: '").append(broj).append('\'');
        if (drzava != null) sb.append(", drzava: '").append(drzava).append('\'');
        sb.append("}");
        return sb.toString();
    }
}