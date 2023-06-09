import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dodaj-autora-dijalog',
  templateUrl: './dodaj-autora-dijalog.component.html',
  styleUrls: ['./dodaj-autora-dijalog.component.scss'],
})
export class DodajAutoraDijalogComponent implements OnInit {
  autorForm!: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<DodajAutoraDijalogComponent>,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.autorForm = this.formBuilder.group({
      preminuliAutor: new FormControl(false),
      ime: new FormControl(''),
      prezime: new FormControl(''),
      pseudonim: new FormControl(''),
      godinaSmrti: new FormControl(''),
      drzavljanstvo: this.formBuilder.group({
        tip: new FormControl('страно'),
        jmbg: new FormControl(''),
        brojPasosa: new FormControl(''),
      }),
      adresa: this.formBuilder.group({
        mesto: new FormControl(''),
        ulica: new FormControl(''),
        broj: new FormControl(''),
        drzava: new FormControl(''),
        postanskiBroj: new FormControl(''),
      }),
    });
  }

  odustani() {
    this.dialogRef.close();
  }

  dodajAutora() {
    this.dialogRef.close(this.autorForm.value);
  }
}
