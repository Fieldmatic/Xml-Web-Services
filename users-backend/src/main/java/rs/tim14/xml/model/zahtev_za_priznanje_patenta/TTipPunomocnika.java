//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:13 PM CET 
//


package rs.tim14.xml.model.zahtev_za_priznanje_patenta;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTip_punomocnika.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TTip_punomocnika">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="punomocnik_za_zastupanje"/>
 *     &lt;enumeration value="zajednicki_predstavnik"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TTip_punomocnika")
@XmlEnum
public enum TTipPunomocnika {

    @XmlEnumValue("punomocnik_za_zastupanje")
    PUNOMOCNIK_ZA_ZASTUPANJE("punomocnik_za_zastupanje"),
    @XmlEnumValue("zajednicki_predstavnik")
    ZAJEDNICKI_PREDSTAVNIK("zajednicki_predstavnik");
    private final String value;

    TTipPunomocnika(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TTipPunomocnika fromValue(String v) {
        for (TTipPunomocnika c: TTipPunomocnika.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
