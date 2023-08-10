import {CurrencyData} from "./currency-data";

export class ExchangeRateData {
    time: string
    from: CurrencyData
    to: CurrencyData
    rate: number

    constructor(time: string, from: CurrencyData, to: CurrencyData, rate: number) {
        this.time = time;
        this.from = from;
        this.to = to;
        this.rate = rate;
    }
}
