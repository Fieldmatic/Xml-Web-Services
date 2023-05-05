import { FileDTO } from './FileDTO';

export class Znak {
  constructor(
    public vrsta: string,
    public drugaVrsta: string,
    public izgled: FileDTO,
    public boje: string[],
    public transliteracija: string,
    public prevod: string,
    public opis: string
  ) {}

  toXML(): string {
    let XML = '<znak>';
    XML += `<vrsta_znaka vrsta_znaka="${this.vrsta}">${
      this.drugaVrsta ? this.drugaVrsta : ''
    }</vrsta_znaka>`;
    for (let boja of this.boje) {
      XML += `<boje_znaka>${boja}</boje_znaka>`;
    }
    XML += this.transliteracija
      ? `<transliteracija_znaka>${this.transliteracija}</transliteracija_znaka>`
      : '';
    XML += this.prevod ? `<prevod_znaka>${this.prevod}</prevod_znaka>` : '';
    XML += `<opis_znaka>${this.opis}</opis_znaka>`;
    XML += '</znak>';
    return XML;
  }
}
