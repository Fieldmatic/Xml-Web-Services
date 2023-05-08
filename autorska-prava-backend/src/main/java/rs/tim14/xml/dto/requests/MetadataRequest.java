package rs.tim14.xml.dto.requests;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="metadata")
@XmlType(name="metadata", propOrder = {"metadataTripleti"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MetadataRequest {

    private List<MetadataTriplet> metadataTripleti;

    @XmlElementWrapper(name="metadataTripleti")
    @XmlElement(name="triplet")
    public List<MetadataTriplet> getMetadataTripleti() {
        return metadataTripleti;
    }

}
