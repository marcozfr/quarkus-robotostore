import { OrderRequest } from "./OrderRequest";

export class OrderResponse extends OrderRequest {

  orderId : number;
  createdDate: Date;

}
