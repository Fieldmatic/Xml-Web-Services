import { Component, Inject, Input, OnChanges, OnInit, SimpleChanges } from "@angular/core";
import { SafeUrl } from "@angular/platform-browser";
import { DOCUMENT } from "@angular/common";
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ZahtevZaPriznanjeZiga } from "../../../model/zahtev-za-priznanje-ziga.model";
import { Prijava } from "../../../model/prijava.model";
import { Korisnik } from "../../../model/korisnik.model";
import { Adresa } from "../../../model/adresa.model";
import { Punomocje } from "../../../model/punomocje.model";
import { FileDTO } from "../../../model/FileDTO";
import { Znak } from "../../../model/znak.model";
import { PravoPrvenstva } from "../../../model/pravo-prvenstva.model";
import { Takse } from "../../../model/takse.model";
import { ZigHttpService } from "../../../services/zig-http.service";

@Component({
  selector: "app-zig-request",
  templateUrl: "./zig-request.component.html",
  styleUrls: ["./zig-request.component.scss"]
})
export class ZigRequestComponent implements OnInit, OnChanges {
  @Input() zig: ZahtevZaPriznanjeZiga;
  fileReader = new FileReader();
  zigForma: FormGroup;
  izgledZnaka: SafeUrl = null;
  punomocjeFile: File;
  izgledZnakaFile: File;
  opstiAktFile: File;
  spisakRobeFile: File;
  dokazPravaPrvenstvaFile: File;
  dokazUplateFile: File;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private zigService: ZigHttpService,
    @Inject(DOCUMENT) private document: Document
  ) {
  }

  sendRequest() {
    if (this.zigForma.valid) {
      const request = this.formValuesToZahtevZaPriznavanjeZiga();
      this.zigService.postRequest(request).subscribe(response => console.log(response));
    }
  }

  get controls() {
    return (<FormArray>this.zigForma.get("bojeZnaka")).controls;
  }

  get podnosilacIsFizickoLice() {
    return this.zigForma.value["podnosilac"]["tip"] === "fizickoLice";
  }

  get punomocnikIsFizickoLice() {
    return this.zigForma.value["punomocnik"]["tip"] === "fizickoLice";
  }

  get zajednickiPredstavnikPostoji() {
    return this.zigForma.value["zajednickiPredstavnik"][
      "zajednickiPredstavnikPostoji"
      ];
  }

  get zajednickiPredstavnikIsFizickoLice() {
    return (
      this.zigForma.value["zajednickiPredstavnik"]["tip"] === "fizickoLice"
    );
  }

  get controls2() {
    return (<FormArray>this.zigForma.get("klaseRobe")).controls;
  }

  get numberOfSelectedKlase() {
    let counter = 0;
    const formGroups = (<FormArray>this.zigForma.get("klaseRobe")).controls;
    for (let formGroup of formGroups) {
      if (formGroup.get("klasa").value) {
        counter++;
      }
    }
    return counter;
  }

  get totalPaidAmount() {
    return (
      this.zigForma.get("takse").get("osnovna").value +
      this.zigForma.get("takse").get("zaKlase").value +
      this.zigForma.get("takse").get("zaGrafickoResenje").value
    );
  }

  get zatrazenoPravoPrvenstva() {
    return this.zigForma.get("pravoPrvenstva").get("zatrazeno").value;
  }

  get sadPrilazemPunomocje() {
    return (
      this.zigForma.get("punomocnik").get("vremePrilozenjaPunomocja").value ===
      "sad"
    );
  }

  get zigNijeInduvidualni() {
    return this.zigForma.get("tipZiga").value !== "INDUVIDUALNI";
  }

  ngOnInit(): void {
    this.initForm();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes["zig"]) {
      if (!changes["zig"].firstChange) {
        this.initForm();
      }
    }
  }

  transformLink(path: string, flag?: boolean): string {
    let link = "http://localhost:7005/api/zig/pdf-resource/";
    if (flag) {
      link = "http://localhost:7005/api/zig/resource/";
    }
    const pathParts = path.split("resources")[1].split("/");
    return link + pathParts[2] + "/" + pathParts[3];
  }

  initForm() {
    const klaseRobe: FormGroup[] = [];
    for (let i = 0; i < 45; i++) {
      let value = false;
      if (this.zig) {
        if (this.zig.klaseRobe.includes(i + 1)) {
          value = true;
        }
      }
      klaseRobe.push(
        this.formBuilder.group({
          klasa: new FormControl(value)
        })
      );
    }

    if (this.zig) {
      this.zigForma = this.formBuilder.group({
        podnosilac: this.formBuilder.group({
          tip: new FormControl(this.zig.podnosilac.tip, Validators.required),
          ime: new FormControl(this.zig.podnosilac.ime),
          prezime: new FormControl(this.zig.podnosilac.prezime),
          poslovnoIme: new FormControl(this.zig.podnosilac.poslovnoIme),
          adresa: this.formBuilder.group({
            mesto: new FormControl(this.zig.podnosilac.adresa.mesto, Validators.required),
            ulica: new FormControl(this.zig.podnosilac.adresa.ulica, Validators.required),
            broj: new FormControl(this.zig.podnosilac.adresa.broj, Validators.required),
            drzava: new FormControl(this.zig.podnosilac.adresa.drzava, Validators.required),
            postanskiBroj: new FormControl(this.zig.podnosilac.adresa.postanskiBroj, Validators.required)
          }),
          email: new FormControl(this.zig.podnosilac.email, Validators.required),
          telefon: new FormControl(this.zig.podnosilac.brojMobilnogTelefona, Validators.required),
          faks: new FormControl(this.zig.podnosilac.brojFaksa, Validators.required)
        }),
        punomocnik: this.formBuilder.group({
          tip: new FormControl(this.zig.punomocnik.tip, Validators.required),
          ime: new FormControl(this.zig.punomocnik.ime),
          prezime: new FormControl(this.zig.punomocnik.prezime),
          poslovnoIme: new FormControl(this.zig.punomocnik.poslovnoIme),
          adresa: this.formBuilder.group({
            mesto: new FormControl(this.zig.punomocnik.adresa.mesto, Validators.required),
            ulica: new FormControl(this.zig.punomocnik.adresa.ulica, Validators.required),
            broj: new FormControl(this.zig.punomocnik.adresa.broj, Validators.required),
            drzava: new FormControl(this.zig.punomocnik.adresa.drzava, Validators.required),
            postanskiBroj: new FormControl(this.zig.punomocnik.adresa.postanskiBroj, Validators.required)
          }),
          email: new FormControl(this.zig.punomocnik.email, Validators.required),
          telefon: new FormControl(this.zig.punomocnik.brojMobilnogTelefona, Validators.required),
          faks: new FormControl(this.zig.punomocnik.brojFaksa, Validators.required),
          vremePrilozenjaPunomocja: new FormControl(this.zig.punomocje.bicePredano ? "posle" : (this.zig.punomocje.punomocje.naziv ? "sad" : "pre"), Validators.required)
        }),
        punomocje: new FormControl(this.zig.punomocje.punomocje?.naziv),
        zajednickiPredstavnik: this.formBuilder.group({
          zajednickiPredstavnikPostoji: new FormControl(
            !!this.zig.zajednickiPredstavnik,
            Validators.required
          ),
          tip: new FormControl(this.zig.zajednickiPredstavnik?.tip, Validators.required),
          ime: new FormControl(this.zig.zajednickiPredstavnik?.ime),
          prezime: new FormControl(this.zig.zajednickiPredstavnik?.prezime),
          poslovnoIme: new FormControl(this.zig.zajednickiPredstavnik?.poslovnoIme),
          adresa: this.formBuilder.group({
            mesto: new FormControl(this.zig.zajednickiPredstavnik?.adresa.mesto, Validators.required),
            ulica: new FormControl(this.zig.zajednickiPredstavnik?.adresa.ulica, Validators.required),
            broj: new FormControl(this.zig.zajednickiPredstavnik?.adresa.broj, Validators.required),
            drzava: new FormControl(this.zig.zajednickiPredstavnik?.adresa.drzava, Validators.required),
            postanskiBroj: new FormControl(this.zig.zajednickiPredstavnik?.adresa.postanskiBroj, Validators.required)
          }),
          email: new FormControl(this.zig.zajednickiPredstavnik?.email, Validators.required),
          telefon: new FormControl(this.zig.zajednickiPredstavnik?.brojMobilnogTelefona, Validators.required),
          faks: new FormControl(this.zig.zajednickiPredstavnik?.brojFaksa, Validators.required)
        }),
        tipZiga: new FormControl(this.zig.vrstaZiga, Validators.required),
        opstiAkt: new FormControl(this.zig.opstiAktOKolektivnomZiguIliZiguGarancije?.naziv),
        tipZnaka: new FormControl(this.zig.znak.vrsta, Validators.required),
        izgledZnaka: new FormControl(this.zig.znak.izgled.naziv, Validators.required),
        bojeZnaka: new FormArray(this.zig.znak.boje.map(color => {
          return new FormGroup({
            boja: new FormControl(color, Validators.required)
          });
        })),
        transliteracijaZnaka: new FormControl(this.zig.znak.transliteracija),
        prevodZnaka: new FormControl(this.zig.znak.prevod),
        opisZnaka: new FormControl(this.zig.znak.opis, Validators.required),
        spisakRobe: new FormControl(this.zig.spisakRobeIUsluga.naziv, Validators.required),
        klaseRobe: this.formBuilder.array(klaseRobe),
        pravoPrvenstva: this.formBuilder.group({
          zatrazeno: new FormControl(this.zig.pravoPrvenstva.zatrazeno, Validators.required),
          osnov: new FormControl(this.zig.pravoPrvenstva.osnov)
        }),
        dokazPravaPrvenstva: new FormControl(this.zig.pravoPrvenstva.dokaz?.naziv),
        dokazUplate: new FormControl(this.zig.takse.dokazOUplati.naziv, Validators.required),
        takse: this.formBuilder.group({
          osnovna: new FormControl(this.zig.takse.osnovna, Validators.required),
          zaKlase: new FormControl(this.zig.takse.zaKlase, Validators.required),
          zaGrafickoResenje: new FormControl(this.zig.takse.zaGrafickoResenje, Validators.required)
        })
      });
      this.disableAllFormControls(this.zigForma);
    } else {
      this.zigForma = this.formBuilder.group({
        podnosilac: this.formBuilder.group({
          tip: new FormControl("fizickoLice", Validators.required),
          ime: new FormControl(""),
          prezime: new FormControl(""),
          poslovnoIme: new FormControl(""),
          adresa: this.formBuilder.group({
            mesto: new FormControl("", Validators.required),
            ulica: new FormControl("", Validators.required),
            broj: new FormControl("", Validators.required),
            drzava: new FormControl("", Validators.required),
            postanskiBroj: new FormControl("", Validators.required)
          }),
          email: new FormControl("", Validators.required),
          telefon: new FormControl("", Validators.required),
          faks: new FormControl("", Validators.required)
        }),
        punomocnik: this.formBuilder.group({
          tip: new FormControl("fizickoLice", Validators.required),
          ime: new FormControl(""),
          prezime: new FormControl(""),
          poslovnoIme: new FormControl(""),
          adresa: this.formBuilder.group({
            mesto: new FormControl("", Validators.required),
            ulica: new FormControl("", Validators.required),
            broj: new FormControl("", Validators.required),
            drzava: new FormControl("", Validators.required),
            postanskiBroj: new FormControl("", Validators.required)
          }),
          email: new FormControl("", Validators.required),
          telefon: new FormControl("", Validators.required),
          faks: new FormControl("", Validators.required),
          vremePrilozenjaPunomocja: new FormControl("posle", Validators.required)
        }),
        punomocje: new FormControl(null),
        zajednickiPredstavnik: this.formBuilder.group({
          zajednickiPredstavnikPostoji: new FormControl(
            false,
            Validators.required
          ),
          tip: new FormControl("fizickoLice"),
          ime: new FormControl(""),
          prezime: new FormControl(""),
          poslovnoIme: new FormControl(""),
          adresa: this.formBuilder.group({
            mesto: new FormControl(""),
            ulica: new FormControl(""),
            broj: new FormControl(""),
            drzava: new FormControl(""),
            postanskiBroj: new FormControl("")
          }),
          email: new FormControl(""),
          telefon: new FormControl(""),
          faks: new FormControl("")
        }),
        tipZiga: new FormControl("", Validators.required),
        opstiAkt: new FormControl(null),
        tipZnaka: new FormControl("", Validators.required),
        izgledZnaka: new FormControl(null, Validators.required),
        bojeZnaka: new FormArray([]),
        transliteracijaZnaka: new FormControl(""),
        prevodZnaka: new FormControl(""),
        opisZnaka: new FormControl("", Validators.required),
        spisakRobe: new FormControl(null, Validators.required),
        klaseRobe: this.formBuilder.array(klaseRobe),
        pravoPrvenstva: this.formBuilder.group({
          zatrazeno: new FormControl(false, Validators.required),
          osnov: new FormControl("")
        }),
        dokazPravaPrvenstva: new FormControl(null),
        dokazUplate: new FormControl(null, Validators.required),
        takse: this.formBuilder.group({
          osnovna: new FormControl(0, Validators.required),
          zaKlase: new FormControl(0, Validators.required),
          zaGrafickoResenje: new FormControl(0, Validators.required)
        })
      });
    }
  }

  disableAllFormControls(formGroup: FormGroup | FormArray): void {
    Object.values(formGroup.controls).forEach(control => {
      if (control instanceof FormControl) {
        control.disable();
      } else if (control instanceof FormGroup || control instanceof FormArray) {
        this.disableAllFormControls(control);
      }
    });
  }

  onFileChange(event, field: string) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];

      this.fileReader.removeAllListeners();
      this.fileReader.addEventListener("load", (event: any) => {
        if (field === "izgledZnaka") {
          this.izgledZnakaFile = file;
          this.izgledZnaka =
            this.document.defaultView.URL.createObjectURL(file);
        } else if (field === "punomocje") {
          this.punomocjeFile = file;
        } else if (field === "opstiAkt") {
          this.opstiAktFile = file;
        } else if (field === "spisakRobe") {
          this.spisakRobeFile = file;
        } else if (field === "dokazPravaPrvenstva") {
          this.dokazPravaPrvenstvaFile = file;
        } else if (field === "dokazUplate") {
          this.dokazUplateFile = file;
        }
        this.zigForma.controls[field].setValue(file.name);
      });

      this.fileReader.readAsDataURL(file);
    }
  }

  getAfterViewUrl(url) {
    return `url("${url}")`;
  }

  getBackgroundImageUrl(url) {
    return `url("${this.getUrl(url)}")`;
  }

  getUrl(url): string {
    if (url.hasOwnProperty("changingThisBreaksApplicationSecurity")) {
      return url.changingThisBreaksApplicationSecurity;
    } else {
      return url;
    }
  }

  ukloniBoju(id: number) {
    (<FormArray>this.zigForma.get("bojeZnaka")).removeAt(id);
  }

  dodajBoju() {
    (<FormArray>this.zigForma.get("bojeZnaka")).push(
      new FormGroup({
        boja: new FormControl("", Validators.required)
      })
    );
  }

  private formValuesToZahtevZaPriznavanjeZiga(): ZahtevZaPriznanjeZiga {
    const formValue = this.zigForma.value;
    const prijava = new Prijava(null, new Date(), null, null);
    const podnosilac = this.formValuesToKorisnik("podnosilac");
    const punomocnik = this.formValuesToKorisnik("punomocnik");
    const punomocje = this.formValuesToPunomocje();
    let zajednickiPredstavnik = this.formValuesToKorisnik(
      "zajednickiPredstavnik"
    );
    Object.keys(zajednickiPredstavnik).forEach(prop => {
      if (zajednickiPredstavnik) {
        if (zajednickiPredstavnik[prop] === "" || zajednickiPredstavnik[prop] === null || zajednickiPredstavnik[prop] === undefined) {
          zajednickiPredstavnik = null;
        }
      }
    });
    const vrstaZiga = formValue["tipZiga"];
    const opstiAkt = this.opstiAktFile
      ? new FileDTO(null, this.opstiAktFile)
      : null;
    const znak = this.formValuesToZnak();
    return new ZahtevZaPriznanjeZiga(
      prijava,
      podnosilac,
      punomocnik,
      punomocje,
      zajednickiPredstavnik,
      vrstaZiga,
      opstiAkt,
      znak,
      this.formValuesToKlaseRobe(),
      new FileDTO(null, this.spisakRobeFile),
      this.formValuesToPravoPrvenstva(),
      this.formValuesToTakse()
    );
  }

  private formValuesToKorisnik(key: string) {
    const korisnik = this.zigForma.value[key];
    const adresaKorisnika = korisnik["adresa"];
    return new Korisnik(
      korisnik["tip"],
      korisnik["tip"] === "fizickoLice" ? korisnik["ime"] : null,
      korisnik["tip"] === "fizickoLice" ? korisnik["prezime"] : null,
      korisnik["tip"] !== "fizickoLice" ? korisnik["poslovnoIme"] : null,
      new Adresa(
        adresaKorisnika["ulica"],
        adresaKorisnika["broj"],
        adresaKorisnika["mesto"],
        adresaKorisnika["postanskiBroj"],
        adresaKorisnika["drzava"]
      ),
      korisnik["telefon"],
      korisnik["faks"],
      korisnik["email"]
    );
  }

  private formValuesToPunomocje() {
    const punomocnik = this.zigForma.value["punomocnik"];
    return new Punomocje(
      new FileDTO(null, this.punomocjeFile),
      punomocnik["vremePrilozenjaPunomocja"] === "pre",
      punomocnik["vremePrilozenjaPunomocja"] === "posle"
    );
  }

  private formValuesToZnak() {
    const formValue = this.zigForma.value;
    return new Znak(
      formValue["tipZnaka"],
      null,
      new FileDTO(null, this.izgledZnakaFile),
      this.formValuesToBojeZnaka(),
      formValue["transliteracijaZnaka"],
      formValue["prevodZnaka"],
      formValue["opisZnaka"]
    );
  }

  private formValuesToBojeZnaka() {
    const bojeZnaka = [];
    const formValuesBoje = this.zigForma.value["bojeZnaka"];
    for (let boja of formValuesBoje) {
      bojeZnaka.push(boja["boja"]);
    }
    return bojeZnaka;
  }

  private formValuesToKlaseRobe() {
    const klaseRobe = [];
    const formValuesKlase = this.zigForma.value["klaseRobe"];
    for (let i = 0; i < formValuesKlase.length; i++) {
      if (formValuesKlase[i]["klasa"]) {
        klaseRobe.push(i + 1);
      }
    }
    return klaseRobe;
  }

  private formValuesToPravoPrvenstva() {
    const formValuesPravoPrvenstva = this.zigForma.value["pravoPrvenstva"];
    return new PravoPrvenstva(
      formValuesPravoPrvenstva["zatrazeno"],
      formValuesPravoPrvenstva["osnov"],
      new FileDTO(null, this.dokazPravaPrvenstvaFile)
    );
  }

  private formValuesToTakse() {
    const formValuesTakse = this.zigForma.value["takse"];
    return new Takse(
      formValuesTakse["osnovna"],
      formValuesTakse["zaGrafickoResenje"],
      formValuesTakse["zaKlase"],
      new FileDTO(null, this.dokazUplateFile)
    );
  }

  protected readonly Boolean = Boolean;
}
