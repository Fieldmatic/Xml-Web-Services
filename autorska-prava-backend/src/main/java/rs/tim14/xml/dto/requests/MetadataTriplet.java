package rs.tim14.xml.dto.requests;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="triplet", propOrder={"predikat", "objekat", "operator"})
public class MetadataTriplet {

    @XmlElement(name="predikat", required = true)
    private String predikat;

    @XmlElement(name="objekat", required=true)
    private String objekat;
    @XmlElement(name="operator", required=true)
    private String operator;

    public String getPredikat() {
        return predikat;
    }

    public String getObjekat() {
        return objekat;
    }

    public String getOperator() {
        return operator;
    }
}
