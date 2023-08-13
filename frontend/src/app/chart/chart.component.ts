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
    plugins: {
      legend: {
        display: false
      }
    },
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
        toCurrency: ['', Validators.required],
      });
  }

  buttonPress() {
    this.service.getExchangeRateHistory(
      "EUR",
      this.chartForm.get('toCurrency')!.value
    )
      .subscribe(dataset => {
          var set:ChartDataset[]  = [{data: []}]
          var label:string[] = []
          dataset.forEach(data => {
              set.forEach(subset => subset.data.push(data.rate))
              label.push(data.time)
            }
          )
          this.lineChartData = set
          this.lineChartLabels = label

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
