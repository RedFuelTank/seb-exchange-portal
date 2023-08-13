import {Component} from '@angular/core';
import {CurrencyService} from "../currency.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-convert',
  templateUrl: './convert.component.html',
  styleUrls: ['./convert.component.scss']
})
export class ConvertComponent {
  currencies: string[] = []
  convertForm!: FormGroup
  toValue: number = 0

  constructor(private service: CurrencyService, private fb: FormBuilder) {
    this.service.getCurrenciesCodes().subscribe(data => {
      console.log(data)
      this.currencies = data
    })
    this.convertForm = this.fb.group(
      {
        toCurrency: ['', Validators.required],
        fromValue: [0, Validators.required],
        toValue: [{value: this.toValue, disabled: true}, Validators.required]
      });
  }

  buttonPress() {
    this.service.getExchangeRateFor("EUR", this.convertForm.get('toCurrency')!.value).subscribe((data) => {
      this.convertForm.patchValue({toValue: this.convertForm.controls['fromValue']!.value * data.rate})
      }
      , error => {
      alert("This exchange rate does not exist")
    })
  }
}
