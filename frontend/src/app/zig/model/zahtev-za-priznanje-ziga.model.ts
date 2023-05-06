import { Prijava } from './prijava.model';
import { Korisnik } from './korisnik.model';
import { Znak } from './znak.model';
import { PravoPrvenstva } from './pravo-prvenstva.model';
import { FileDTO } from './FileDTO';
import { Takse } from './takse.model';
import { Punomocje } from './punomocje.model';

export class ZahtevZaPriznanjeZiga {
  constructor(
    public prijava: Prijava,
    public podnosilac: Korisnik,
    public punomocnik: Korisnik,
    public punomocje: Punomocje,
    public zajednickiPredstavnik: Korisnik,
    public vrstaZiga: string,
    public opstiAktOKolektivnomZiguIliZiguGarancije: FileDTO,
    public znak: Znak,
    public klaseRobe: number[],
    public spisakRobeIUsluga: FileDTO,
    public pravoPrvenstva: PravoPrvenstva,
    public takse: Takse
  ) {}

  toXML(): string {
    let XML = '<zahtev_za_priznanje_ziga>';
    XML += this.prijavaToXML();
    XML += `<podnosilac>${this.podnosilac.toXML()}</podnosilac>`;
    XML += `<punomocnik>${this.punomocnik.toXML()}</punomocnik>`;
    XML += this.zajednickiPredstavnik
      ? `<zajednicki_predstavnik>${this.zajednickiPredstavnik.toXML()}</zajednicki_predstavnik>`
      : '';
    XML += `<vrsta_ziga>${this.vrstaZiga}</vrsta_ziga>`;
    XML += this.znak.toXML();
    for (let klasa of this.klaseRobe) {
      XML += `<klase_robe_ili_usluge>${klasa}</klase_robe_ili_usluge>`;
    }
    XML += this.pravoPrvenstva.toXML();
    XML += this.takse.toXML();
    XML += this.priloziToXML();
    XML += '</zahtev_za_priznanje_ziga>';
    return XML;
  }

  private prijavaToXML() {
    let XML = '<prijava>';
    XML += `<broj_prijave>${this.prijava.broj_prijave}</broj_prijave>`;
    XML += `<broj_prijave>${this.prijava.datum_podnosenja.getFullYear()}-${this.prijava.datum_podnosenja.getMonth()}-${this.prijava.datum_podnosenja.getDate()}</broj_prijave>`;
    XML += '</prijava>';
    return XML;
  }

  private priloziToXML() {
    let XML = '<Prilozi_uz_zahtev>';
    XML += `<primerak_znaka>${this.znak.izgled.naziv}</primerak_znaka>`;
    XML += `<spisak_robe_i_usluga>${this.spisakRobeIUsluga.naziv}</spisak_robe_i_usluga>`;
    XML += this.punomocje.toXML();
    XML += `<akti_o_kolektivnom_zig_ili_zigu_garancije>${this.opstiAktOKolektivnomZiguIliZiguGarancije}</primerak_znaka>`;
    XML += this.pravoPrvenstva.zatrazeno
      ? `<dokaz_o_pravu_prvenstva>${this.pravoPrvenstva.dokaz.naziv}</dokaz_o_pravu_prvenstva>`
      : '';
    XML += `<dokaz_o_uplati_takse>${this.takse.dokazOUplati.naziv}</dokaz_o_uplati_takse>`;
    XML += '</Prilozi_uz_zahtev>';
    return XML;
  }
}
