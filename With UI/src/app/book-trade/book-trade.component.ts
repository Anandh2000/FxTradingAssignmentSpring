import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {FormGroup,FormBuilder,Validators } from '@angular/forms'
import { WebServiceService } from '../Services/web-service.service';
@Component({
  selector: 'app-book-trade',
  templateUrl: './book-trade.component.html',
  styleUrls: ['./book-trade.component.css']
})
export class BookTradeComponent implements OnInit {
  public bookTrade !: FormGroup;
  message:string = '';
  form:any
  tradeConfirm:string=''
  submitted = false
  isChecked=false
  constructor(private formBuilder: FormBuilder,private http:HttpClient,private service:WebServiceService) { 
    this.bookTrade = this.formBuilder.group({
      customerName:['',Validators.pattern('^(?=.{1,28}$)(?![\\s])[a-zA-Z\\s+\\.]+')],
      currencyPair:['USDINR'],
      amount:['',Validators.min(1)],
      getRateYesOrNo:['no']
    })
  }

  get f(){return this.form.controls;}

  ngOnInit(): void {
    
  }
  toggle(){
    if(!this.isChecked){
    this.bookTrade.value.getRateYesOrNo='yes'
    this.isChecked=true;
    }
    else{
      this.bookTrade.value.getRateYesOrNo='no'
      this.isChecked=false
    }
  }
  tradeBook(){
   //if(confirm("are you sure"))
   this.isChecked=false
   this.submitted = true;
    this.service.bookTrades(this.bookTrade.value).subscribe(
      Response =>{
        console.log(this.bookTrade.value.getRateYesOrNo)
        console.log(Response.message)
        let popup:any = document.getElementById("ConfirmationPopup")
        this.message=Response.message
        console.log(Response)
        popup.classList.add("open-popup");
        //this.bookTrade.reset();
      },
      error =>{
        console.log(error.message);
      }
    )
  }

  booking(value:any){
    let popup:any = document.getElementById("ConfirmationPopup")
    popup.classList.remove("open-popup");
    this.service.confirmation(value).subscribe(
      Response => {
        let popup:any = document.getElementById("resultPopUp")
        this.message=Response.message
        popup.classList.add("open-resultPopUp");
       
      },error =>{
        console.log(error.message)
      }
    )
  }
  

}
