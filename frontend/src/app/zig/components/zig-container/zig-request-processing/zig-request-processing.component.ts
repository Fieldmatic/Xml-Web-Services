import { Component, OnInit } from "@angular/core";
import { ZahtevZaPriznanjeZiga } from "../../../model/zahtev-za-priznanje-ziga.model";
import { ZigHttpService } from "../../../services/zig-http.service";
import { switchMap } from "rxjs";
import { ActivatedRoute } from "@angular/router";
import { ConfirmationDialogComponent } from "../../../../shared/confirmation-dialog/confirmation-dialog.component";
import { MatDialog } from "@angular/material/dialog";
import { LoggedInUser } from "../../../../auth/model/logged-in-user";
import { AuthService } from "../../../../auth/services/auth.service";
import { ResenjeService } from "../../../services/resenje.service";

@Component({
  selector: "app-zig-request-processing",
  templateUrl: "./zig-request-processing.component.html",
  styleUrls: ["./zig-request-processing.component.scss"]
})
export class ZigRequestProcessingComponent implements OnInit {
  z1Obrazac: ZahtevZaPriznanjeZiga;
  loggedInUser: LoggedInUser;

  constructor(private zigService: ZigHttpService, private route: ActivatedRoute, private dialog: MatDialog, private resenjeService: ResenjeService, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        const id = params.get("id");
        return this.zigService.getRequest(id);
      })
    ).subscribe(jsonResult => {
      if (jsonResult.zahteviDTO.zahtevi) {
        this.z1Obrazac = this.kreirajZ1Zahteve(this.napraviListuOdPristiglihZahteva(jsonResult.zahteviDTO.zahtevi))[0];
      }
    });
    this.authService.loggedInUser.subscribe(user => this.loggedInUser = user);
  }

  openConfirmationDialog() {
    const data = {
      title: "Prihvatanje zahteva",
      text: "Da li ste sigurni da želite da prihvatite zahtev?",
      hideReason: true
    };
    this.openDialog(data);
  }

  openRejectDialog() {
    const data = {
      title: "Odbijanje zahteva",
      text: "Molim Vas unesite razlog za odbijanje zahteva.",
      hideReason: false
    };
    this.openDialog(data);
  }

  openDialog(data: any): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, { data });

    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {

        let request = {
          id: this.z1Obrazac.prijava.broj_prijave,
          emailSluzbenika: this.loggedInUser.email,
          imeSluzbenika: this.loggedInUser.name,
          prezimeSluzbenika: this.loggedInUser.surname,
          odbijen: !result.accepted,
          razlogOdbijanja: result.reason
        };
        this.resenjeService.obradiZahtev(request).subscribe(() => {
          console.log("vrh");
        });
      }
    });
  }

  kreirajZ1Zahteve(listaXmlZahteva: any) {
    return listaXmlZahteva.map(xmlZahtev => ZahtevZaPriznanjeZiga.parseXML(xmlZahtev));
  }

  napraviListuOdPristiglihZahteva(zahtevi: any) {
    return Array.isArray(zahtevi) ? zahtevi : [zahtevi];
  }
}
