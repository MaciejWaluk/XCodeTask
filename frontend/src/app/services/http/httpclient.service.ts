import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserRequest} from "../../../models/UserRequest";
import {map, Observable} from "rxjs";
import {RequestDto} from "../../../dtos/RequestDto";

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient: HttpClient) { }

  private readonly API_URL = "http://localhost:8090/currencies"

  public sendRequest(request: UserRequest) {
    return this.httpClient.post(this.API_URL + "/get-current-currency-value-command", request)
  }

  public getRequests(): Observable<RequestDto[]> {
    return this.httpClient.get<any[]>(this.API_URL + "/requests").pipe(
      map((data: any[]) => data.map(item => new RequestDto(
        item.currency,
        item.name,
        new Date(item.date),
        item.value
      )))
    );
  }

}
