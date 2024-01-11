import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { tap } from 'rxjs';
import { ICoursesModel } from './../_share/_models/iCourses-model';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {
  private readonly API = "./../../assets/_mockup_services/courses.json";

/**Dialog fpoi oserviço criado para abrir a PopUp de error, aqui pelo Service sem ter que ir para compoment */
  constructor(private http: HttpClient, public dialog: MatDialog) { }

    list() {
      return this.http.get<ICoursesModel[]>(this.API)
      .pipe(tap( localCourses => console.log(localCourses) ));
    }



}
