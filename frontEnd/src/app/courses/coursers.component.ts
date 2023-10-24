import { Component } from '@angular/core';
import { ICoursesModel } from '../_models/courses-model';

@Component({
  selector: 'app-coursers',
  templateUrl: './coursers.component.html',
  styleUrls: ['./coursers.component.scss']
})
export class CoursersComponent {

  displayedColumns = ['name', 'category']; //collun names
  coursesList: ICoursesModel[] = [{_id: '1', name: 'Angular', category: 'frontend'}];

}
