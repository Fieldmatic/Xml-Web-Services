export class Adresa {
  constructor(
    public ulica: string,
    public broj: string,
    public mesto: string,
    public postanskiBroj: string,
    public drzava: string
  ) {
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
