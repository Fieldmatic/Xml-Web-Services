export class Prijava {
  constructor(
    public broj_prijave: number,
    public datum_podnosenja: Date,
    public sluzbenik: string,
    public prihvacena: boolean
  ) {
  }

  static parseXML(xml): Prijava {
    const broj_prijave = Number(xml["ns3:broj_prijave"]["_text"]);
    const date = xml["ns3:datum_podnosenja"]["_text"];
    const dateParts = date.split("+");
    const dateTimeString = `${dateParts[0]}T00:00:00`;
    const datum_podnosenja = new Date(dateTimeString);
    let sluzbenik = "";
    if ("ns3:sluzbenik" in xml) {
      sluzbenik = xml["ns3:sluzbenik"]["_text"];
    }
    const prihvacena = Boolean(xml["ns3:prihvacena"]["_text"]);
    return new Prijava(broj_prijave, datum_podnosenja, sluzbenik, prihvacena);
  }
}
