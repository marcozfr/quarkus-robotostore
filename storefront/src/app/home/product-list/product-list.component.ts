import { Component, OnInit } from '@angular/core';
import { Product } from '../../@shared/models/Product';
import { ProductsService } from 'src/app/services/products.service';
import { CartDetail } from 'src/app/@shared/models/CartDetail';
import { CartService } from 'src/app/services/cart.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PostActionModalComponent } from 'src/app/@shared/components/post-action-modal/post-action-modal.component';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  public products : Array<Product>;

  constructor(private productsService : ProductsService,
    private modalService: NgbModal,
    private cartService : CartService) {

  }

  ngOnInit(): void {
    this.productsService.getProducts().subscribe(
      data => {
        this.products = data;
      }
    )
  }

  onAddToCartEvent(evt: Product){
    let detail = new CartDetail(evt.id, evt.description,
      evt.discountedPrice ? evt.discountedPrice : evt.originalPrice, 1);
      try{
        this.cartService.addToCart(detail);
      }catch(e){
        const modalRef = this.modalService.open(PostActionModalComponent);
        modalRef.componentInstance.title = 'Error';
        modalRef.componentInstance.content = e.message;
      }


  }

}
