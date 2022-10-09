import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public bookTrade !: FormGroup;
  isChecked = false;
  constructor(private formBuilder: FormBuilder) { }

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

}
