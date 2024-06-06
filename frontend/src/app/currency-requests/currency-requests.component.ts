import {Component, OnInit} from '@angular/core';
import {RequestDto} from "../../dtos/RequestDto";
import {HttpClientService} from "../services/http/httpclient.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-currency-requests',
  templateUrl: './currency-requests.component.html',
  styleUrl: './currency-requests.component.css'
})
export class CurrencyRequestsComponent implements OnInit {
  public requests: RequestDto[] = [];

  constructor(private httpService: HttpClientService, private router: Router, private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.loadRequests();
  }

  loadRequests(): void {
    this.httpService.getRequests().subscribe({
      next: (data: RequestDto[]) => {
        this.requests = data.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
        console.log(this.requests);
      },
      error: (error: any) => {
        this.toast.error(error.message, '', {positionClass: 'toast-top-center'});
      }
    });
  }

  navigateHome() {
    this.router.navigate(['']);
  }
}
