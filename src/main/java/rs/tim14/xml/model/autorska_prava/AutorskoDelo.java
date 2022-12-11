package rs.tim14.xml.model.autorska_prava;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"naslovAutorskogDela",
	"vrstaAutorskogDela",
	"formaZapisa",
	"podaciOAutoru",
	"izvornoAutorskoDelo",
	"stvorenoURadnomOdnosu",
	"nacinKoriscenjaAutorskogDela",
	"primerAutorskogDela",
	"opisAutorskogDela"
})
public class AutorskoDelo {

	@XmlElement(name = "naslov_autorskog_dela", required = true)
	protected String naslovAutorskogDela;
	@XmlElement(name = "vrsta_autorskog_dela", required = true)
	protected TVrstaAutorskogDela vrstaAutorskogDela;
	@XmlElement(name = "forma_zapisa", required = true)
	protected TFormaZapisa formaZapisa;
	@XmlElement(name = "podaci_o_autoru", required = true)
	protected PodaciOAutoru podaciOAutoru;
	@XmlElement(name = "izvorno_autorsko_delo")
	protected IzvornoAutorskoDelo izvornoAutorskoDelo;
	@XmlElement(name = "stvoreno_u_radnom_odnosu")
	protected boolean stvorenoURadnomOdnosu;
	@XmlElement(name = "nacin_koriscenja_autorskog_dela")
	protected String nacinKoriscenjaAutorskogDela;
	@XmlElement(name = "primer_autorskog_dela", required = true)
	protected PrimerAutorskogDela primerAutorskogDela;
	@XmlElement(name = "opis_autorskog_dela")
	protected OpisAutorskogDela opisAutorskogDela;

	public String getNaslovAutorskogDela() {
		return naslovAutorskogDela;
	}

	public void setNaslovAutorskogDela(String value) {
		this.naslovAutorskogDela = value;
	}

	public TVrstaAutorskogDela getVrstaAutorskogDela() {
		return vrstaAutorskogDela;
	}

	public void setVrstaAutorskogDela(TVrstaAutorskogDela value) {
		this.vrstaAutorskogDela = value;
	}

	public TFormaZapisa getFormaZapisa() {
		return formaZapisa;
	}

	public void setFormaZapisa(TFormaZapisa value) {
		this.formaZapisa = value;
	}

	public PodaciOAutoru getPodaciOAutoru() {
		return podaciOAutoru;
	}

	public void setPodaciOAutoru(PodaciOAutoru value) {
		this.podaciOAutoru = value;
	}

	public IzvornoAutorskoDelo getIzvornoAutorskoDelo() {
		return izvornoAutorskoDelo;
	}

	public void setIzvornoAutorskoDelo(IzvornoAutorskoDelo value) {
		this.izvornoAutorskoDelo = value;
	}

	public boolean isStvorenoURadnomOdnosu() {
		return stvorenoURadnomOdnosu;
	}

	public void setStvorenoURadnomOdnosu(boolean value) {
		this.stvorenoURadnomOdnosu = value;
	}

	public String getNacinKoriscenjaAutorskogDela() {
		return nacinKoriscenjaAutorskogDela;
	}

	public void setNacinKoriscenjaAutorskogDela(String value) {
		this.nacinKoriscenjaAutorskogDela = value;
	}

	public PrimerAutorskogDela getPrimerAutorskogDela() {
		return primerAutorskogDela;
	}

	public void setPrimerAutorskogDela(PrimerAutorskogDela value) {
		this.primerAutorskogDela = value;
	}

	public OpisAutorskogDela getOpisAutorskogDela() {
		return opisAutorskogDela;
	}

	public void setOpisAutorskogDela(OpisAutorskogDela value) {
		this.opisAutorskogDela = value;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		sb.append("\n\t\tNaslov autorskog dela: '").append(naslovAutorskogDela).append('\'');
		sb.append("\n\t\tVrsta autorskog dela: ").append(vrstaAutorskogDela);
		sb.append("\n\t\tForma zapisa: ").append(formaZapisa);
		sb.append("\n\t\tAutori: ").append(podaciOAutoru);
		sb.append("\n\t\tIzvorno autorsko delo: ").append(izvornoAutorskoDelo);
		sb.append("\n\t\tStvoreno u radnom odnosu: ").append(stvorenoURadnomOdnosu);
		sb.append("\n\t\tNacin koriscenja autorskog dela: '").append(nacinKoriscenjaAutorskogDela).append('\'');
		sb.append("\n\t\tPrimer autorskog dela: ").append(primerAutorskogDela);
		sb.append("\n\t\tOpis autorskog dela: ").append(opisAutorskogDela);
		return sb.toString();
	}
}


