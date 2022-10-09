import { Component, OnInit } from '@angular/core';
import { WebServiceService } from '../Services/web-service.service';

export class Trade{
  constructor(
    public tradeNumber:number,
    public currencyPair:string,
    public customerName:string,
    public amount:number,
    public rate:number
  )
  {
  }
}

@Component({
  selector: 'app-trade-history',
  templateUrl: './trade-history.component.html',
  styleUrls: ['./trade-history.component.css']
})



export class TradeHistoryComponent implements OnInit {
  page:number=1;
  count:number=0;
  tableSize:number=10;
  tableSizes:any=[5,10,15,20]
  trades:Trade[] =[]

  constructor(private service:WebServiceService) { }

  ngOnInit(): void {
    this.tradeList();
  }

  tradeList(){
    this.service.printAll().subscribe(
      (response) => {
        console.log(response)
        this.trades = response}
    )
  }
  onTableDataChange(event:any){
    this.page=event;
    this.tradeList();
  }

  onTableSizeChange(event:any):void{
    this.tableSize=event.target.value;
    this.page=1;
    this.tradeList();
  }

}
