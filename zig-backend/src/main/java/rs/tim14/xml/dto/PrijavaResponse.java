package rs.tim14.xml.dto;

import lombok.*;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.TPrijava;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "prijava")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrijavaResponse {
    @XmlElement(name = "brojZahteva", required = true)
    protected String brojZahteva;
    @XmlElement(name = "datumPodnosenja", required = true)
    protected TPrijava.DatumPodnosenja datumPodnosenja;
    @XmlElement(name = "podnosilac", required = true)
    protected String podnosilac;
    @XmlElement(name = "status", required = true)
    protected String status;
    @XmlElement(name = "sluzbenik")
    protected String sluzbenik;
}
