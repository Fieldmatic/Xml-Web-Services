import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {MatChipInputEvent} from "@angular/material/chips";


@Component({
  selector: 'app-pretraga-zahteva',
  templateUrl: './pretraga-zahteva.component.html',
  styleUrls: ['./pretraga-zahteva.component.scss']
})
export class PretragaZahtevaComponent {
  @Output() obicnaPretragaEvent = new EventEmitter<any>();
  @Output() naprednaPretragaEvent = new EventEmitter<any>();
  @Input() metadataElementi: string[];

  metadataSearchForm: FormGroup;
  filters: string[] = [];
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  addOnBlur = true;

  constructor(private fb: FormBuilder) {
    this.metadataSearchForm = this.fb.group({
      triplets: this.fb.array([this.kreirajNoviTriplet()])
    });
  }

  izvrsiObicnuPretragu() {
    if (this.filters.length)
      this.obicnaPretragaEvent.emit(this.filters);
  }

  izvrsiNaprednuPetragu() {
    this.naprednaPretragaEvent.emit(this.dobaviKompletneUneteTriplete())
  }

  get triplets(): FormArray {
    return this.metadataSearchForm.get('triplets') as FormArray;
  }

  kreirajNoviTriplet(): FormGroup {
    return this.fb.group({
      operator: '',
      predikat: '',
      objekat: ''
    });
  }

  dodajTriplet(): void {
    this.triplets.push(this.kreirajNoviTriplet());
  }

  dodajFilter(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value) {
      this.filters.push(value);
    }
    event.chipInput!.clear();
  }

  ukloniFilter(fruit: string): void {
    const index = this.filters.indexOf(fruit);
    if (index >= 0) {
      this.filters.splice(index, 1);
    }
  }

  dobaviKompletneUneteTriplete() {
    return this.metadataSearchForm.value.triplets.filter(triplet => triplet.predikat !== '' && triplet.objekat !== '' && triplet.operator !== '')
  }
}
