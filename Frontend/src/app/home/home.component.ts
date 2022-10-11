import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public bookTrade !: FormGroup;
  isChecked = false;
  constructor(private formBuilder: FormBuilder,
              private router:Router) { }

  ngOnInit(): void {

  }
  toggle(){
    if(!this.isChecked){
    console.log("yes")
    this.isChecked=true;
    }
    else{
      console.log("no")
      this.isChecked=false
    }
  }

  onBack(){
    if(confirm("Do you want to exit?")){
      this.router.navigate([""]);
    }   
  }

}
