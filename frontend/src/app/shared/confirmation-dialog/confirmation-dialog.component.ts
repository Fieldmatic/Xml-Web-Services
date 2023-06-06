import { Component, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.scss'],
})
export class ConfirmationDialogComponent {
  reason: string = '';

  constructor(
    public dialogRef: MatDialogRef<ConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: { title: string; text: string; hideReason: boolean},
  ) {}

  onAccept(): void {
    this.dialogRef.close({ accepted: true });
  }

  onReject(): void {
    this.dialogRef.close({ accepted: false, reason: this.reason });
  }
}
