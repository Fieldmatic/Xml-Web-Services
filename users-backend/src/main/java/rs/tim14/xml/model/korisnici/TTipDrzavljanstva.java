//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:13 PM CET 
//


package rs.tim14.xml.model.korisnici;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TTip_drzavljanstva")
@XmlEnum
public enum TTipDrzavljanstva {

    @XmlEnumValue("домаће")
    DOMACE_DRZAVLJANSTVO("домаће"),
    @XmlEnumValue("страно")
    STRANO_DRZAVLJANSTVO("страно");
    private final String value;

    TTipDrzavljanstva(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TTipDrzavljanstva fromValue(String v) {
        for (TTipDrzavljanstva c: TTipDrzavljanstva.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}