import { FileDTO } from './FileDTO';

export class PravoPrvenstva {
  constructor(
    public zatrazeno: boolean,
    public osnov: string,
    public dokaz: FileDTO
  ) {}

  toXML(): string {
    let XML = `<pravo_prvenstva zatrazeno_pravo_prvenstva="${this.zatrazeno}">`;
    XML += this.zatrazeno
      ? `<osnov_prava_prvenstva>${this.osnov}</osnov_prava_prvenstva>`
      : '';
    XML += `</pravo_prvenstva>`;
    return XML;
  }
}
