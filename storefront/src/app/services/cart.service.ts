import { Injectable } from '@angular/core';
import { Cart } from 'src/app/@shared/models/Cart';
import { CartDetail } from '../@shared/models/CartDetail';
import { OrderDetail } from '../@shared/models/OrderDetail';
import { OrderRequest } from '../@shared/models/OrderRequest';

@Injectable({
  providedIn: 'root'
})
export class CartService {


  private cart : Cart;

  constructor() {
    this.initCart();
  }

  getCart() {
    return this.cart;
  }

  initCart() {
    this.cart = {
      details : []
    }
  }


  addToCart(detail : CartDetail){
    let foundDetail = this.cart.details.find((val, i) =>{
      return val.productId === detail.productId
    });
    if(foundDetail){
      foundDetail.incrementQuantity();
    }else{
      this.cart.details.push(detail);
    }
  }

  removeFromCart(detail: CartDetail) {
    let newDetails = this.cart.details.filter((val, i) =>{
      return val.productId !== detail.productId
    });
    this.cart.details = newDetails;
  }

}
