export class OsnovniPodaciObrascu {
  constructor(
    public id: string = '',
    public nazivPodnosioca: string = '',
    public datumPodnosenja: Date = new Date(),
    public status: string = 'PODNET',
  ) {
}
}
