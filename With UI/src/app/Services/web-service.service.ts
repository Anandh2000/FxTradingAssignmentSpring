import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http'
import { Trade } from '../trade-history/trade-history.component';
@Injectable({
  providedIn: 'root'
})
export class WebServiceService {

  constructor(public http:HttpClient) { }

  printAll(){ 
    return this.http.get<Trade[]>("http://localhost:8080/PrintTrade");
  }
  bookTrades(value:any){ 
    return this.http.post<any>("http://localhost:8080/BookTrade",value);
  }
  confirmation(bookOrCancel:any){
    return this.http.post<any>("http://localhost:8080/bookorCancel",bookOrCancel)
  }
  rateChange(rate:string){
    return this.http.post<any>(`http://localhost:8080/RateChanger/${rate}`,null)
  }

}
