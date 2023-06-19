export class Adresa {
  constructor(
    public ulica: string,
    public broj: string,
    public mesto: string,
    public postanskiBroj: string,
    public drzava: string
  ) {
  }

  static parseXML(xml): Adresa {
    const ulica = xml["ns2:ulica"]["_text"];
    const broj = xml["ns2:broj"]["_text"];
    const mesto = xml["ns2:mesto"]["_text"];
    const drzava = xml["ns2:drzava"]["_text"];
    const postanski_broj = xml["ns2:postanski_broj"]["_text"];
    return new Adresa(ulica, broj, mesto, drzava, postanski_broj);
  }

  toXML(): string {
    let XML = "<adresa xmlns=\"http://www.xml.tim14.rs/korisnici\">";
    XML += `<ulica>${this.ulica}</ulica>`;
    XML += `<broj>${this.broj}</broj>`;
    XML += `<mesto>${this.mesto}</mesto>`;
    XML += `<postanski_broj>${this.postanskiBroj}</postanski_broj>`;
    XML += `<drzava>${this.drzava}</drzava>`;
    XML += "</adresa>";
    return XML;
  }
}
