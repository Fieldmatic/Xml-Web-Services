package rs.tim14.xml.dto.requests;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="metadata")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class NaprednaPretragaRequest {

    private List<NaprednaPretragaTriplet> naprednaPretragaTripleti;

    @XmlElement(name="triplet")
    public List<NaprednaPretragaTriplet> getMetadataTripleti() {
        return naprednaPretragaTripleti;
    }

    public void setMetadataTripleti(final List<NaprednaPretragaTriplet> naprednaPretragaTripleti) {
        this.naprednaPretragaTripleti = naprednaPretragaTripleti;
    }

}
