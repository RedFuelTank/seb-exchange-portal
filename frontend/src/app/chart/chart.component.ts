import {Component, OnInit} from '@angular/core';
import {Chart} from "chart.js";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {
  toCurrency: any;
  currencies: any;
  fromCurrency: any;
  ngOnInit(): void {
    var myChart = new Chart("myChart", {
      type: 'line',
      data: {
        datasets: [{
          data: [{x: '2016-12-25', y: 20}, {x: '2016-12-26', y: 10}]
        }]
      },
    });
  }

  buttonPress() {

  }
}
