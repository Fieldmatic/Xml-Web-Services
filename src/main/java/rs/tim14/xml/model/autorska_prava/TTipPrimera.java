//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 05:55:12 PM CET 
//


package rs.tim14.xml.model.autorska_prava;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTip_primera.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TTip_primera">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="slika"/>
 *     &lt;enumeration value="video_zapis"/>
 *     &lt;enumeration value="audio_zapis"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TTip_primera")
@XmlEnum
public enum TTipPrimera {

    @XmlEnumValue("slika")
    SLIKA("slika"),
    @XmlEnumValue("video_zapis")
    VIDEO_ZAPIS("video_zapis"),
    @XmlEnumValue("audio_zapis")
    AUDIO_ZAPIS("audio_zapis");
    private final String value;

    TTipPrimera(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TTipPrimera fromValue(String v) {
        for (TTipPrimera c: TTipPrimera.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}