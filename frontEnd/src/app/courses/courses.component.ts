import { Component } from '@angular/core';
import { ICoursesModel } from '../_share/_models/courses-model';

@Component({
  selector: 'app-coursers',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent {

  displayedColumns = ['name', 'category']; //collun names
  coursesList: ICoursesModel[] = [{_id: '1', name: 'Angular', category: 'frontend'}];

}
