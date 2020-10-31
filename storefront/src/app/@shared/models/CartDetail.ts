export class CartDetail {

  public productId: string;
  public productDescription: string
  public price: number
  public quantity: number
  public totalPrice: number

  constructor(productId: string, productDescription: string,
    price: number, quantity: number){
      this.productId = productId;
      this.price = price;
      this.quantity = quantity;
      this.productDescription = productDescription;
      this.totalPrice = this.quantity * this.price
  }

  incrementQuantity() {
    if(this.quantity + 1 > 4){
      throw Error('Wowow! no puedes comprar más de 4 unidades');
    }
    this.quantity++;
    this.totalPrice = this.quantity * this.price;
  }

  changeQuantity(newQuantity: number) {
    this.quantity = newQuantity;
    this.totalPrice = this.quantity * this.price;
  }

}
