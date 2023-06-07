import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs";
import {A1Service} from "../../../services/a1.service";
import {A1Obrazac} from "../../../model/A1Obrazac";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmationDialogComponent} from "../../../../shared/confirmation-dialog/confirmation-dialog.component";
import {ResenjeService} from "../../../services/resenje.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-a1-obrada-obrasca',
  templateUrl: './a1-obrada-obrasca.component.html',
  styleUrls: ['./a1-obrada-obrasca.component.scss']
})
export class A1ObradaObrascaComponent {
  a1Obrazac: A1Obrazac;
  priloziForm: FormGroup;

  constructor(
    private a1Service: A1Service,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private resenjeService: ResenjeService,
    private formBuilder: FormBuilder
  ) {
    this.priloziForm = this.formBuilder.group({
      opisDelaChecked: [false],
      primerDelaChecked: [false]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        const id = params.get('id');
        return this.a1Service.dobaviZahtevPoIdu(id);
      })
    ).subscribe(jsonResult => {
      console.log(jsonResult)
      this.a1Obrazac = this.a1Service.mapirajObjekatA1(jsonResult);
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
        let request = {id: this.a1Obrazac.brojPrijave, emailSluzbenika:"istevanovic3112@gmail.com", imeSluzbenika: "ime sluzbenika", prezimeSluzbenika: "prezime", odbijen: !result.accepted, razlogOdbijanja: result.reason}
        this.resenjeService.obradiZahtev(request).subscribe(() =>
        {
          console.log("vrh")
        })
      }
    });
  }

}
