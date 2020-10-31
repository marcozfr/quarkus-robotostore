import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { map } from 'rxjs/operators';
import { Product } from '../@shared/models/Product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http : HttpClient) { }

  public getProducts(): Observable<Array<Product>>{
    return this.http.get(`${environment.productsApi}`).pipe(
      map((data: Array<Product>) => data)
    );
  }
}
