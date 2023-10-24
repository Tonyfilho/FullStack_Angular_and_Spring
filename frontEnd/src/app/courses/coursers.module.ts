import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { RouterModule, Routes } from '@angular/router';
import { CoursersComponent } from './coursers.component';

const ROUTERS_COURSES: Routes = [
  {path:"", redirectTo:"coursers"}
];

@NgModule({
  declarations: [
    CoursersComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    RouterModule.forChild(ROUTERS_COURSES),
  ]
})
export class CoursersModule { }
