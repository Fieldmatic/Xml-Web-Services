import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
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
      ime: new FormControl('', [Validators.pattern('^[a-zA-Z]+$')]),
      prezime: new FormControl('', [Validators.pattern('^[a-zA-Z]+$')]),
      pseudonim: new FormControl(''),
      godinaSmrti: new FormControl('', Validators.pattern('[0-9]*')),
      drzavljanstvo: this.formBuilder.group({
        tip: new FormControl('страно'),
        jmbg: new FormControl('', Validators.pattern('^[0-9]{13}$')),
        brojPasosa: new FormControl('', [Validators.minLength(8)]),
      }),
      adresa: this.formBuilder.group({
        mesto: new FormControl(''),
        ulica: new FormControl(''),
        broj: new FormControl(''),
        drzava: new FormControl(''),
        postanskiBroj: new FormControl('', [Validators.pattern('[0-9]{5}')]),
      }),
    });
  }

  odustani() {
    this.dialogRef.close();
  }

  dodajAutora() {
    if (this.autorForm.valid) {
      this.dialogRef.close(this.autorForm.value);
    }
  }
}
