import {Component, OnDestroy} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ResenjeService} from "../../../shared/services/resenje.service";
import {switchMap} from "rxjs";
import {PatentObrazac} from "../../model/PatentObrazac";
import {PatentService} from "../../services/patent.service";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmationDialogComponent} from "../../../shared/confirmation-dialog/confirmation-dialog.component";
import {LoggedInUser} from "../../../auth/model/logged-in-user";
import {AuthService} from "../../../auth/services/auth.service";

@Component({
  selector: 'app-obrada-patenta',
  templateUrl: './obrada-patenta.component.html',
  styleUrls: ['./obrada-patenta.component.scss']
})
export class ObradaPatentaComponent implements OnDestroy {
  patentObrazac: PatentObrazac;
  idObrasca: string;
  loggedInUser: LoggedInUser = null;

  constructor(
    private patentService: PatentService,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private resenjeService: ResenjeService,
    private authService: AuthService
  ) {
  }

  ngOnInit(): void {
    this.authService.loggedInUser.subscribe(user => this.loggedInUser = user);
    this.route.paramMap.pipe(
      switchMap(params => {
        this.idObrasca = params.get('id');
        return this.patentService.dobaviZahtevPoIdu(this.idObrasca);
      })
    ).subscribe(jsonResult => {
      this.patentObrazac = new PatentObrazac(jsonResult);
    });
  }

  openConfirmationDialog() {
    const data = {title: 'Prihvatanje zahteva',
      text: 'Da li ste sigurni da želite da prihvatite zahtev?',
      hideReason: true}
    this.openDialog(data);
  }

  openRejectDialog() {
    const data = {title: 'Odbijanje zahteva',
      text: 'Molim Vas unesite razlog za odbijanje zahteva.',
      hideReason: false}
    this.openDialog(data);
  }

  openDialog(data: any): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {data});

    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        let request = {id: this.idObrasca, emailSluzbenika: this.loggedInUser.email, imeSluzbenika: this.loggedInUser.name, prezimeSluzbenika: this.loggedInUser.surname, odbijen: !result.accepted, razlogOdbijanja: result.reason}
        this.resenjeService.obradiZahtev(request, 'p').subscribe(() => {
          console.log("vrh")
        })
      }
    });
  }

  ngOnDestroy() {
    console.log("Deleting object")
    this.patentObrazac = null;
  }

}
