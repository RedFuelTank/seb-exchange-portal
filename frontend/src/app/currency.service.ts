import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ExchangeRateData} from "./model/exchange-rate-data";

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  private static REST_API_SERVER = "/api";

  constructor(private http: HttpClient) {

  }

  getExchangeRateFor(from: string, to: string) {
    return this.http.get<ExchangeRateData>(CurrencyService.REST_API_SERVER + `/exchange-rates/current?from=${from}&to=${to}`)
  }

}
