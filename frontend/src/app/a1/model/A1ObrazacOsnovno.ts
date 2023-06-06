export class A1ObrazacOsnovno {
  constructor(
    public id: string = '',
    public nazivPodnosioca: string = '',
    public datumPodnosenja: Date = new Date(),
    public status: string = 'PODNET',
  ) {
  }
}
