import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {ResenjeService} from "../../../shared/services/resenje.service";
import {switchMap} from "rxjs";
import {ConfirmationDialogComponent} from "../../../shared/confirmation-dialog/confirmation-dialog.component";
import {PatentObrazac} from "../../model/PatentObrazac";
import {PatentService} from "../../services/patent.service";

@Component({
  selector: 'app-obrada-patenta',
  templateUrl: './obrada-patenta.component.html',
  styleUrls: ['./obrada-patenta.component.scss']
})
export class ObradaPatentaComponent {
  patentObrazac: PatentObrazac;
  idObrasca: string;

  constructor(
    private patentService: PatentService,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private resenjeService: ResenjeService,
  ) {
  }

  ngOnInit(): void {
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
        let request = {
          id: this.idObrasca,
          emailSluzbenika: "istevanovic3112@gmail.com",
          imeSluzbenika: "ime sluzbenika",
          prezimeSluzbenika: "prezime",
          odbijen: !result.accepted,
          razlogOdbijanja: result.reason
        }
        // this.resenjeService.obradiZahtev(request, 'p').subscribe(() => {
        //   console.log("vrh")
        // })
      }
    });
  }

}
