db.createUser({
  user: 'productsUser',
  pwd: 'productsPassword',
  roles: [
    {
      role: 'readWrite',
      db: 'productsdb'
    }
  ]
});

db.product.insert({
  _id : ObjectId(),
  description: 'Chappie',
  originalPrice : NumberDecimal('100.99'),
  discountedPrice : NumberDecimal('69.99'),
  stock: 15
});

db.product.insert({
  _id : ObjectId(),
  description: 'T3',
  originalPrice : NumberDecimal('99.99'),
  discountedPrice : NumberDecimal('79.99'),
  stock: 15
});
