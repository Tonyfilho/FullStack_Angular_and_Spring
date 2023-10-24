import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoursersComponent } from './courses/coursers.component';

const routes: Routes = [
  { path: "", pathMatch: "full", component: CoursersComponent },
  { path: "coursers", loadChildren: () => import(`./courses/coursers.module`).then(m => m.CoursersModule) },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
