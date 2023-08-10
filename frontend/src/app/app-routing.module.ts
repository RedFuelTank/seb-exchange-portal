import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ConvertComponent} from "./convert/convert.component";
import {ChartComponent} from "./chart/chart.component";

const routes: Routes = [
  {path:"convert", component:ConvertComponent},
  {path:"chart", component:ChartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
