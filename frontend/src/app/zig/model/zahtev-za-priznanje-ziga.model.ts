import { Prijava } from "./prijava.model";
import { Korisnik } from "./korisnik.model";
import { Znak } from "./znak.model";
import { PravoPrvenstva } from "./pravo-prvenstva.model";
import { FileDTO } from "./FileDTO";
import { Takse } from "./takse.model";
import { Punomocje } from "./punomocje.model";

export class ZahtevZaPriznanjeZiga {
  constructor(
    public prijava: Prijava,
    public podnosilac: Korisnik,
    public punomocnik: Korisnik,
    public punomocje: Punomocje,//
    public zajednickiPredstavnik: Korisnik,
    public vrstaZiga: string,
    public opstiAktOKolektivnomZiguIliZiguGarancije: FileDTO,//
    public znak: Znak,
    public klaseRobe: number[],
    public spisakRobeIUsluga: FileDTO,//
    public pravoPrvenstva: PravoPrvenstva,//
    public takse: Takse,
    public status?: string
  ) {
  }

  static parseXML(xml): ZahtevZaPriznanjeZiga {
    const prijava = Prijava.parseXML(xml["ns3:prijava"]);
    const podnosilac = Korisnik.parseXML(xml["ns3:podnosilac"]);
    const punomocnik = Korisnik.parseXML(xml["ns3:punomocnik"]);
    let zajednickiPredstavnik = null;
    if ("ns3:zajednicki_predstavnik" in xml) {
      zajednickiPredstavnik = Korisnik.parseXML("ns3:zajednicki_predstavnik");
    }
    const vrsta_ziga = xml["ns3:vrsta_ziga"]["_text"];
    const znak = Znak.parseXML(xml["ns3:znak"]);
    const klase_robe = xml["ns3:klase_robe_ili_usluge"].map(klasa => Number(klasa["_text"]));
    const status = xml["ns3:statusZahteva"]["_text"];
    const takse = Takse.parseXML(xml["ns3:takse"]);
    const punomocje = Punomocje.parseXML(xml["ns3:Prilozi_uz_zahtev"]);
    const pravo_prvenstva = PravoPrvenstva.parseXML(xml["ns3:pravo_prvenstva"]);
    const prilozi = xml["ns3:Prilozi_uz_zahtev"];
    znak.izgled = new FileDTO(prilozi["ns3:primerak_znaka"]["_text"], null);
    const spisakRobe = new FileDTO(prilozi["ns3:spisak_robe_i_usluga"]["_text"], null);
    if ("ns3:punomocje" in prilozi) {
      punomocje.punomocje = new FileDTO(prilozi["ns3:punomocje"]["_text"], null);
    }
    let opstiAkti = null;
    if ("ns3:akti_o_kolektivnom_zigu_ili_zigu_garancije" in prilozi) {
      opstiAkti = new FileDTO(prilozi["ns3:akti_o_kolektivnom_zigu_ili_zigu_garancije"]["_text"], null);
    }
    if ("ns3:dokaz_o_pravu_prvenstva" in prilozi) {
      pravo_prvenstva.dokaz = new FileDTO(prilozi["ns3:dokaz_o_pravu_prvenstva"]["_text"], null);
    }
    if ("ns3:dokaz_o_uplati_takse" in prilozi) {
      takse.dokazOUplati = new FileDTO(prilozi["ns3:dokaz_o_uplati_takse"]["_text"], null);
    }
    return new ZahtevZaPriznanjeZiga(
      prijava,
      podnosilac,
      punomocnik,
      punomocje,
      zajednickiPredstavnik,
      vrsta_ziga,
      opstiAkti,
      znak,
      klase_robe,
      spisakRobe,
      pravo_prvenstva,
      takse,
      status
    );
  }

  toXML(): string {
    let XML = "<zahtev_za_priznanje_ziga " +
      "xmlns=\"http://www.xml.tim14.rs/zahtev_za_priznanje_ziga\"\n" +
      "                          xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
      "                          xmlns:ks=\"http://www.xml.tim14.rs/korisnici\">";
    XML += `<podnosilac xsi:type="ks:${this.podnosilac.tip == "fizickoLice" ? "TFizicko_Lice" : "TPravno_Lice"}">${this.podnosilac.toXML()}</podnosilac>`;
    XML += `<punomocnik xsi:type="ks:${this.punomocnik.tip == "fizickoLice" ? "TFizicko_Lice" : "TPravno_Lice"}">${this.punomocnik.toXML()}</punomocnik>`;
    XML += this.zajednickiPredstavnik
      ? `<zajednicki_predstavnik xsi:type="ks:${this.zajednickiPredstavnik.tip == "fizickoLice" ? "TFizicko_Lice" : "TPravno_Lice"}">${this.zajednickiPredstavnik.toXML()}</zajednicki_predstavnik>`
      : "";
    XML += `<vrsta_ziga>${this.vrstaZiga}</vrsta_ziga>`;
    XML += this.znak.toXML();
    for (let klasa of this.klaseRobe) {
      XML += `<klase_robe_ili_usluge>${klasa}</klase_robe_ili_usluge>`;
    }
    XML += this.pravoPrvenstva.toXML();
    XML += this.takse.toXML();
    XML += this.priloziToXML();
    XML += "</zahtev_za_priznanje_ziga>";
    return XML;
  }

  private prijavaToXML() {
    let XML = "<prijava>";
    XML += `<broj_prijave>${this.prijava.broj_prijave}</broj_prijave>`;
    XML += `<broj_prijave>${this.prijava.datum_podnosenja.getFullYear()}-${this.prijava.datum_podnosenja.getMonth()}-${this.prijava.datum_podnosenja.getDate()}</broj_prijave>`;
    XML += "</prijava>";
    return XML;
  }

  private priloziToXML() {
    let XML = "<Prilozi_uz_zahtev>";
    XML += `<primerak_znaka>${this.znak.izgled.naziv}</primerak_znaka>`;
    XML += `<spisak_robe_i_usluga>${this.spisakRobeIUsluga.naziv}</spisak_robe_i_usluga>`;
    XML += this.punomocje.toXML();
    XML += this.opstiAktOKolektivnomZiguIliZiguGarancije ? `<akti_o_kolektivnom_zig_ili_zigu_garancije>${this.opstiAktOKolektivnomZiguIliZiguGarancije}</akti_o_kolektivnom_zig_ili_zigu_garancije>` : "";
    XML += this.pravoPrvenstva.zatrazeno && this.pravoPrvenstva.dokaz && this.pravoPrvenstva.dokaz.naziv
      ? `<dokaz_o_pravu_prvenstva>${this.pravoPrvenstva.dokaz.naziv}</dokaz_o_pravu_prvenstva>`
      : "";
    XML += `<dokaz_o_uplati_takse>${this.takse.dokazOUplati.naziv}</dokaz_o_uplati_takse>`;
    XML += "</Prilozi_uz_zahtev>";
    return XML;
  }
}
