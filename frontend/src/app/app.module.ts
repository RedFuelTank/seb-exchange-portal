import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ConvertComponent} from './convert/convert.component';
import {ChartComponent} from './chart/chart.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgChartjsModule} from "ng-chartjs";

@NgModule({
    declarations: [
        AppComponent,
        ConvertComponent,
        ChartComponent
    ],
    imports: [
        NgChartjsModule,
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        FormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
