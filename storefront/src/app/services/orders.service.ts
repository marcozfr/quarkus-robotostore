import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { OrderRequest } from '../@shared/models/OrderRequest';
import { OrderResponse } from "./../@shared/models/OrderResponse";


@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http : HttpClient) { }

  public createOrder(order: OrderRequest): Observable<OrderResponse> {
    return this.http.post(`${environment.ordersApi}`, order).pipe(
      map((data: OrderResponse) => data)
    );
  }

}
