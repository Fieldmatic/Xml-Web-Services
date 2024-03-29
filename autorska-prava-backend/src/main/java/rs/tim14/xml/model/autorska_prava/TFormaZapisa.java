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

@XmlType(name = "TForma_zapisa")
@XmlEnum
public enum TFormaZapisa {

    @XmlEnumValue("рукопис")
    RUKOPIS("рукопис"),
    @XmlEnumValue("штампани текст")
    STAMPANI_TEKST("штампани текст"),
    @XmlEnumValue("музичка партитура")
    MUZICKA_PARTITURA("музичка партитура"),
    @XmlEnumValue("звучни запис")
    ZVUCNI_ZAPIS("звучни запис"),
    @XmlEnumValue("визуелни запис")
    VIZUELNI_ZAPIS("визуелни запис"),
    @XmlEnumValue("аудиовизуелни запис")
    AUDIOVIZUELNI_ZAPIS("аудиовизуелни запис"),
    @XmlEnumValue("дигитална форма")
    DIGITALNA_FORMA("дигитална форма");
    private final String value;

    TFormaZapisa(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TFormaZapisa fromValue(String v) {
        for (TFormaZapisa c: TFormaZapisa.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
