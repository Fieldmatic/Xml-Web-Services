import { FileDTO } from "./FileDTO";

export class Punomocje {
  constructor(
    public punomocje: FileDTO,
    public predano: boolean,
    public bicePredano: boolean
  ) {
  }

  static parseXML(xml) {
    const predano = xml["ns3:generalno_punomocje_predano"]["_text"] !== "false";
    const bice_predano = xml["ns3:punomocje_ce_biti_predano"]["_text"] !== "false";
    return new Punomocje(null, predano, bice_predano);
  }

  toXML(): string {
    let XML = "";
    XML += this.punomocje.naziv
      ? `<punomocje>${this.punomocje.naziv}</punomocje>`
      : "";
    XML += `<generalno_punomocje_predano>${this.predano}</generalno_punomocje_predano>`;
    XML += `<punomocje_ce_biti_predano>${this.bicePredano}</punomocje_ce_biti_predano>`;
    return XML;
  }
}
