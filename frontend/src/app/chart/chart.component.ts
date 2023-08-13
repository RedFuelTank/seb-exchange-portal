import {Component, OnInit} from '@angular/core';
import {CurrencyService} from "../currency.service";
import _default from "chart.js/dist/core/core.interaction";
import {NgChartjsService} from 'ng-chartjs';
import {Chart, ChartDataset, ChartType} from "chart.js";

@Component({
    selector: 'app-chart',
    templateUrl: './chart.component.html',
    styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {
    currencies:string[] = ["USD", "EUR"]
    toCurrency: any;
    fromCurrency: any;
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

    constructor(private service: CurrencyService, private ngChartjsService: NgChartjsService) {
    }

    buttonPress() {
        this.service.getExchangeRateHistory(this.fromCurrency, this.toCurrency)
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
                }
            )
    }

    ngOnInit(): void {
        // inline plugin
    }
}
