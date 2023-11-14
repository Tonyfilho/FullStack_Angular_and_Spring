import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ErrorDialogComponent } from './components-material/error-dialog/error-dialog.component';
import { AppMaterialModule } from './app-material/app-material.module';



@NgModule({
  declarations: [
    ErrorDialogComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule,

  ],
  exports: [ErrorDialogComponent]
})
export class SharedModule { }