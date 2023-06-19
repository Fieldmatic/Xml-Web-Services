package rs.tim14.xml.dto;

import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="zahteviDTO")
@XmlSeeAlso({ ZahtevZaPriznanjePatenta.class})
public class ZahteviZaPriznanjePatentaDTO {
    @XmlElement(name="zahtevi")
    private final List<ZahtevZaPriznanjePatenta> zahteviZaPriznanjePatenta;

    public ZahteviZaPriznanjePatentaDTO() {
        zahteviZaPriznanjePatenta = new ArrayList<>();
    }

    public ZahteviZaPriznanjePatentaDTO(List<ZahtevZaPriznanjePatenta> zahteviZaPriznanjePatenta) {
        this.zahteviZaPriznanjePatenta = zahteviZaPriznanjePatenta;
    }
}
