import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CurrencyFormComponent} from "./currency-form/currency-form.component";
import {CurrencyRequestsComponent} from "./currency-requests/currency-requests.component";

const routes: Routes = [
  {path: '', component: CurrencyFormComponent},
  {path: 'requests', component: CurrencyRequestsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
