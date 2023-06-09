import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dodaj-prijavu-dijalog',
  templateUrl: './dodaj-prijavu-dijalog.component.html',
  styleUrls: ['./dodaj-prijavu-dijalog.component.scss'],
})
export class DodajPrijavuDijalogComponent implements OnInit {
  prijavaForm!: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<DodajPrijavuDijalogComponent>,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.prijavaForm = this.formBuilder.group({
      brojPrijave: new FormControl(''),
      datumPodnosenja: new FormControl(''),
      oznakaDrzave: new FormControl('', Validators.pattern('[A-Z][A-Z]')),
      });
  }

  odustani() {
    this.dialogRef.close();
  }

  dodajPrijavu() {
    if (this.prijavaForm.valid) {
      this.dialogRef.close(this.prijavaForm.value);
    }
  }
}
