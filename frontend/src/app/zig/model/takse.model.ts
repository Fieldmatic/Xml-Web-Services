import { FileDTO } from './FileDTO';

export class Takse {
  get ukupno() {
    return this.osnovna + this.zaGrafickoResenje + this.zaKlase;
  }

  constructor(
    public osnovna: number,
    public zaGrafickoResenje: number,
    public zaKlase: number,
    public dokazOUplati: FileDTO
  ) {}

  toXML(): string {
    let XML = `<takse ukupna_vrednost="${this.ukupno}">`;
    XML += `<osnovna_taksa>${this.osnovna}</osnovna_taksa>`;
    XML += `<taksa_za_klase>${this.zaKlase}</taksa_za_klase>`;
    XML += `<taksa_za_graficko_resenje>${this.zaGrafickoResenje}</taksa_za_graficko_resenje>`;
    XML += '</takse>';
    return XML;
  }
}
