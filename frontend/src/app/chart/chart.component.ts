import {Component, OnInit} from '@angular/core';
import {CurrencyService} from "../currency.service";
import {NgChartjsService} from 'ng-chartjs';
import {ChartDataset, ChartType} from "chart.js";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {
  currencies: string[] = []
  chart: any;
  chartVisible: boolean = false;
  lineChartData: ChartDataset[] = [
    {
      data: [],
    },
  ];
  lineChartLabels: Array<any> = [];
  lineChartType: ChartType = 'line';
  lineChartOptions: any = {
    responsive: true
  };
  chartForm: FormGroup;

  constructor(private service: CurrencyService,
              private ngChartjsService: NgChartjsService,
              private fb: FormBuilder) {
    this.service.getCurrenciesCodes().subscribe(data => {
      console.log(data)
      this.currencies = data
    })

    this.chartForm = this.fb.group(
      {
        fromCurrency: ['', Validators.required],
        toCurrency: ['', Validators.required],
      });
  }

  buttonPress() {
    this.service.getExchangeRateHistory(
      this.chartForm.get('fromCurrency')!.value,
      this.chartForm.get('toCurrency')!.value
    )
      .subscribe(dataset => {
          dataset.forEach(data => {
              this.lineChartData.forEach(chartData =>
                chartData.data.push(data.rate)
              )
              this.lineChartLabels.push(data.time)
            }
          )
          const chart: any = this.ngChartjsService.getChart('myChart');
          chart.update()
          this.chartVisible = true;
        },
        error => {
          alert("This exchange rate does not exist")
        }
      )
  }

  ngOnInit(): void {
    // inline plugin
  }
}
