package rs.tim14.xml.model.zahtev_za_priznanje_ziga;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "vrstaZnaka",
        "izgledZnaka",
        "bojeZnaka",
        "transliteracijaZnaka",
        "prevodZnaka",
        "opisZnaka"
})
public class Znak {

    @XmlElement(name = "vrsta_znaka", required = true)
    protected VrstaZnaka vrstaZnaka;
    @XmlElement(name = "izgled_znaka", required = true)
    protected String izgledZnaka;
    @XmlElement(name = "boje_znaka", required = true)
    protected List<String> bojeZnaka;
    @XmlElement(name = "transliteracija_znaka")
    protected String transliteracijaZnaka;
    @XmlElement(name = "prevod_znaka")
    protected String prevodZnaka;
    @XmlElement(name = "opis_znaka", required = true)
    protected String opisZnaka;

    public VrstaZnaka getVrstaZnaka() {
        return vrstaZnaka;
    }
    public void setVrstaZnaka(VrstaZnaka value) {
        this.vrstaZnaka = value;
    }
    public String getIzgledZnaka() {
        return izgledZnaka;
    }
    public void setIzgledZnaka(String value) {
        this.izgledZnaka = value;
    }
    public List<String> getBojeZnaka() {
        if (bojeZnaka == null) {
            bojeZnaka = new ArrayList<String>();
        }
        return this.bojeZnaka;
    }
    public String getTransliteracijaZnaka() {
        return transliteracijaZnaka;
    }
    public void setTransliteracijaZnaka(String value) {
        this.transliteracijaZnaka = value;
    }
    public String getPrevodZnaka() {
        return prevodZnaka;
    }
    public void setPrevodZnaka(String value) {
        this.prevodZnaka = value;
    }
    public String getOpisZnaka() {
        return opisZnaka;
    }
    public void setOpisZnaka(String value) {
        this.opisZnaka = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n\t\tVrstaZnaka: ").append(vrstaZnaka);
        sb.append("\n\t\tIzgled znaka: '").append(izgledZnaka).append('\'');
        sb.append("\n\t\tBoje znaka: ").append(bojeZnaka);
        if (transliteracijaZnaka != null)
            sb.append("\n\t\tTransliteracija znaka: '").append(transliteracijaZnaka).append('\'');
        if (prevodZnaka != null) sb.append("\n\t\tPrevod znaka: '").append(prevodZnaka).append('\'');
        sb.append("\n\t\tOpis znaka:'").append(opisZnaka).append('\'');
        return sb.toString();
    }

}
