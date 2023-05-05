package rs.tim14.xml.dto.responses;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;

@XmlRootElement(name="zahteviDTO")
@XmlSeeAlso({ ZahtevZaAutorskaPrava.class})
public class ZahteviZaAutorskaPravaDTO {
	@XmlElement(name="zahtevi")
	private final List<ZahtevZaAutorskaPrava> zahteviZaAutorskoDelo;

	public ZahteviZaAutorskaPravaDTO() {
		zahteviZaAutorskoDelo = new ArrayList<>();
	}

	public ZahteviZaAutorskaPravaDTO(List<ZahtevZaAutorskaPrava> zahteviZaAutorskoDelo) {
		this.zahteviZaAutorskoDelo = zahteviZaAutorskoDelo;
	}
}
