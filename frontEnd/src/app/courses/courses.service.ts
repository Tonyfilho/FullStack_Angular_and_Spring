import { RouterTestingModule } from '@angular/router/testing';
import { ICoursesModel } from './../_share/_models/iCourses-model';
import { ErrorHandler, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable, catchError, delay, first, from, of, reduce, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {
  private readonly API = "./../../assets/_mockup_services/courses.json";

  constructor(private http: HttpClient) {  }

    list() {
      return this.http.get<ICoursesModel[]>(this.API)
      .pipe(
        first(), //just get 1 subscrition, after that close the conection
        delay(3000), // create a delay to see the spinner in front 3s
        tap( localCourses => console.log(localCourses)), catchError(e => {
          /**Precisamos devolver um observable, mesmo q seja um Array vasio */
          console.error("Error Course: ", e)
          return of([])
        }))

    }



}
