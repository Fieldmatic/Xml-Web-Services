package rs.tim14.xml.model.zahtev_za_priznanje_ziga;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "content"
})
public class VrstaZnaka {

    @XmlElementRef(name = "druga_vrsta_znaka", namespace = "http://www.xml.tim14.rs/zahtev_za_priznanje_ziga", type = JAXBElement.class, required = false)
    @XmlMixed
    protected List<Serializable> content;
    @XmlAttribute(name = "vrsta_znaka")
    protected TVrstaZnaka vrstaZnaka;

    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }
    public TVrstaZnaka getVrstaZnaka() {
        return vrstaZnaka;
    }
    public void setVrstaZnaka(TVrstaZnaka value) {
        this.vrstaZnaka = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n\t\t\tSadrzaj: ").append(content);
        if (vrstaZnaka != null) sb.append("\n\t\t\tVrsta znaka=").append(vrstaZnaka);
        return sb.toString();
    }
}
