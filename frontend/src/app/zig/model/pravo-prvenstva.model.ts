import { FileDTO } from "./FileDTO";

export class PravoPrvenstva {
  constructor(
    public zatrazeno: boolean,
    public osnov: string,
    public dokaz: FileDTO
  ) {
  }

  static parseXML(xml): PravoPrvenstva {
    const zatrazeno = xml["_attributes"]["zatrazeno_pravo_prvenstva"] !== "false";
    let osnov = "";
    if (zatrazeno) {
      osnov = xml["ns3:osnov_prava_prvenstva"]["_text"];
    }
    return new PravoPrvenstva(zatrazeno, osnov, null);
  }

  toXML(): string {
    let XML = `<pravo_prvenstva zatrazeno_pravo_prvenstva="${this.zatrazeno}">`;
    XML += this.zatrazeno
      ? `<osnov_prava_prvenstva>${this.osnov}</osnov_prava_prvenstva>`
      : "";
    XML += `</pravo_prvenstva>`;
    return XML;
  }
}
