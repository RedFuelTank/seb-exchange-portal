import { Component } from '@angular/core';
import {CurrencyService} from "../currency.service";

@Component({
  selector: 'app-convert',
  templateUrl: './convert.component.html',
  styleUrls: ['./convert.component.scss']
})
export class ConvertComponent {
  currencies:string[] = ["USD", "EUR"]
  fromCurrency: string=""
  fromValue: number = 0;
  toCurrency: string=""
  toValue: number = 0;


  constructor(private service: CurrencyService) {}

  buttonPress() {
    this.service.getExchangeRateFor(this.fromCurrency, this.toCurrency).subscribe((data) => {
      this.toValue = this.fromValue * data.rate
    })
  }
}
