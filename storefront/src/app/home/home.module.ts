import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';

@NgModule({
  declarations: [HomeComponent, ProductComponent, ProductListComponent],
  imports: [
    CommonModule
  ]
})
export class HomeModule { }
