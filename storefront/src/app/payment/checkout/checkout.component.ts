import { Component, OnInit } from '@angular/core';
import { CartDetail } from 'src/app/@shared/models/CartDetail';
import { OrderRequest } from 'src/app/@shared/models/OrderRequest';
import { CartService } from '../../services/cart.service'
import { OrdersService } from '../../services/orders.service'
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, ModalDismissReasons, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { PostActionModalComponent } from 'src/app/@shared/components/post-action-modal/post-action-modal.component';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  modalOptions : NgbModalOptions;
  closeResult: string;

  constructor(private cartService: CartService,
      private orderService : OrdersService,
      private route: ActivatedRoute,
      private router: Router,
      private modalService: NgbModal
    ) {
      this.modalOptions = {
        backdrop:'static',
        backdropClass:'customBackdrop'
      }
  }

  ngOnInit(): void {
    console.log('initializing checkout form');
  }

  onRemoveCartDetail(evt: CartDetail){
    return this.cartService.removeFromCart(evt);
  }

  openModal(content) {

    const modalRef = this.modalService.open(PostActionModalComponent);
    modalRef.componentInstance.title = 'Confirmación';
    modalRef.componentInstance.content = content;
    modalRef.result.then((result) => {
      this.cartService.initCart();
      this.router.navigate(['/home'])
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  onCheckout(){

    let orderDetails =  this.cart().details.map(val => { return {
        productId : val.productId,
        quantity : val.quantity
      }}
    );

    let order : OrderRequest = {
      username : localStorage.getItem('username'),
      orderDetails : orderDetails
    }

    this.orderService.createOrder(order).subscribe(
      data => {
        this.openModal(`Se registró la orden ${data.orderId} para el usuario ${data.username}`);
      }
    );
  }

  cart(){
    return this.cartService.getCart();
  }

}
