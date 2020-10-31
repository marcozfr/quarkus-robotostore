import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  form = new FormGroup({
    username : new FormControl('')
  })
  showForm=false;
  constructor(public cartService: CartService) { }

  ngOnInit(): void {
    this.showForm=false;
    this.form.controls.username.setValue(localStorage.getItem('username'));
  }

  getCartSize(){
    return this.cartService.getCart().details.length;
  }

  getUsername(){
    return localStorage.getItem('username');
  }

  onSubmit(){
    localStorage.setItem('username', this.form.controls.username.value);
    this.showForm=false;
  }

}
