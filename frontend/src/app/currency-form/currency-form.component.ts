import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserRequest } from '../../models/UserRequest';
import {HttpClientService} from "../services/http/httpclient.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-currency-form',
  templateUrl: './currency-form.component.html',
  styleUrls: ['./currency-form.component.css']
})
export class CurrencyFormComponent {
  public rate: number | null = null;
  public currencyForm = new FormGroup({
    userName: new FormControl('', [
      Validators.required,
    ]),
    currencySymbol: new FormControl('', [
      Validators.required,
      Validators.minLength(3)
    ]),
  });

  constructor(private httpService: HttpClientService, private router: Router, private toastr: ToastrService) {}

  handleForm() {
    if (this.currencyForm.valid) {
      const requestPayload = new UserRequest(
        this.currencyForm.get('currencySymbol')!.value ?? '',
        this.currencyForm.get('userName')!.value ?? ''
      )
      this.httpService.sendRequest(requestPayload).subscribe({
        next: (response: any) => {
          if(typeof response.value == "number") {
            this.rate = response.value.toFixed(4);
          } else {
            this.toastr.error("Try again", '', {positionClass: 'toast-top-center'});
          }
        },
        error: (error: any) => {
          console.error("Error occurred:", error);
          if (error.status === 404) {
            this.toastr.error('Currency not found', '', {positionClass: 'toast-top-center' });
          }
          else {
            this.toastr.error(error.error.message, '', {positionClass: 'toast-top-center'});
          }
        }
      });
    }
    else if (this.currencyForm.get('currencySymbol')?.invalid || this.currencyForm.get('userName')?.invalid) {
      this.toastr.error('Fill in all fields', '', {positionClass: 'toast-top-center'});
    }
  }

  navigateToRequests() {
    this.router.navigate(['/requests'])
  }
}
