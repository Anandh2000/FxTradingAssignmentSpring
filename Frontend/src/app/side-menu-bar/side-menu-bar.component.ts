import { Component, OnInit } from '@angular/core';
import { WebServiceService } from '../Services/web-service.service';

@Component({
  selector: 'app-side-menu-bar',
  templateUrl: './side-menu-bar.component.html',
  styleUrls: ['./side-menu-bar.component.css']
})
export class SideMenuBarComponent implements OnInit {
  title = 'Fx-Trading';
  rate =0;
  i=0;
  constructor(private service:WebServiceService) { }

  ngOnInit(): void {
    const body:any = document.querySelector('body'),
    sidebar:any = body.querySelector('nav'),
    toggle:any = body.querySelector(".toggle"),
    modeSwitch:any = body.querySelector(".toggle-switch"),
    modeText:any = body.querySelector(".mode-text");

    
    toggle.addEventListener("click", () =>{
      sidebar.classList.toggle("close");
      this.i=this.i+1
      console.log(this.i)
      let popup:any = document.getElementById("RatePopup");
      popup.classList.remove("open-popup");
  })
  
  modeSwitch.addEventListener("click", () =>{

      body.classList.toggle("dark");
      
      if(body.classList.contains("dark")){
          modeText.innerText = "Light mode";
      }else{
          modeText.innerText = "Dark mode";  
      }
  });

  }
  Rate(){
    let popup:any = document.getElementById("RatePopup");
    popup.classList.add("open-popup");
    if(!(this.i%2===1)){
      const body:any = document.querySelector('body'),
      sidebar:any = body.querySelector('nav');
      sidebar.classList.toggle("close");
      this.i=this.i+1;
    }
    
  }
  RateConfirm(rate:any){
    this.service.rateChange(rate).subscribe(
      (response) => {
        alert(response.message)
        console.log(response)
        let popup:any = document.getElementById("RatePopup");
        popup.classList.remove("open-popup");}
    )
  
  }
  cancel(){
    let popup:any = document.getElementById("RatePopup");
    popup.classList.remove("open-popup");
    const body:any = document.querySelector('body'),
    sidebar:any = body.querySelector('nav');
    sidebar.classList.toggle("close");
    this.i=this.i+1
  }




}
