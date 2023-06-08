import { FileDTO } from "./FileDTO";

export class Znak {
  constructor(
    public vrsta: string,
    public drugaVrsta: string,
    public izgled: FileDTO,
    public boje: string[],
    public transliteracija: string,
    public prevod: string,
    public opis: string
  ) {
  }

  static parseXML(xml): Znak {
    const vrsta_znaka = xml["ns3:vrsta_znaka"]["_attributes"]["vrsta_znaka"];
    let druga_vrsta_znaka = "";
    if (vrsta_znaka === "DRUGI") {
      druga_vrsta_znaka = xml["ns3:vrsta_znaka"]["ns3:druga_vrsta_znaka"]["_text"];
    }
    const boje = xml["ns3:boje_znaka"].map(boja => boja["_text"]);
    let transliteracija = "";
    if ("ns3:transliteracija" in xml) {
      transliteracija = xml["ns3:transliteracija_znaka"]["_text"];
    }
    let prevod = "";
    if ("ns3:prevod_znaka" in xml) {
      prevod = xml["ns3:prevod_znaka"]["_text"];
    }
    const opis = xml["ns3:opis_znaka"]["_text"];
    return new Znak(vrsta_znaka, druga_vrsta_znaka, null, boje, transliteracija, prevod, opis);
  }

  toXML(): string {
    let XML = "<znak>";
    XML += `<vrsta_znaka vrsta_znaka="${this.vrsta}">${
      this.drugaVrsta ? this.drugaVrsta : ""
    }</vrsta_znaka>`;
    for (let boja of this.boje) {
      XML += `<boje_znaka>${boja}</boje_znaka>`;
    }
    XML += this.transliteracija
      ? `<transliteracija_znaka>${this.transliteracija}</transliteracija_znaka>`
      : "";
    XML += this.prevod ? `<prevod_znaka>${this.prevod}</prevod_znaka>` : "";
    XML += `<opis_znaka>${this.opis}</opis_znaka>`;
    XML += "</znak>";
    return XML;
  }
}
