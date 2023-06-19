package rs.tim14.xml.dto.responses;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;

@XmlRootElement(name="zahteviDTO")
@XmlSeeAlso({ ZahtevZaPriznanjeZiga.class})
public class ZahteviZaPriznanjeZigaDTO {
	@XmlElement(name="zahtevi")
	private final List<ZahtevZaPriznanjeZiga> zahteviZaZigDTO;

	public ZahteviZaPriznanjeZigaDTO() {
		zahteviZaZigDTO = new ArrayList<>();
	}

	public ZahteviZaPriznanjeZigaDTO(List<ZahtevZaPriznanjeZiga> zahteviZaZigDTO) {
		this.zahteviZaZigDTO = zahteviZaZigDTO;
	}
}
