# Quarkus RobotoStore

By Marco Flores @marcozfr

Robots lovingly delivered by Robohash.org

## Descripción

El proyecto demo muestra la interacción entre microservicios basados 
en Quarkus de acuerdo al siguiente diagrama:

Está compuesto por los siguientes microservicios:
- Ordenes
- Productos
- Usuarios

Entre estos se puede encontras las implementaciones de:

- CDI
- Mutiny
- Microprofile (Rest Client, Fault Tolerance, Circuit Breaker, Health)
- Panache for MongoDB
- Panache for SQL (PostgreSQL)
- Kafka Operations

## Ejecución

### Modo JVM


Compilar el proyecto angular
```
cd storefront
npm install
ng build --prod
cd ..
```

Volver a la raiz y para levantar los servicios de la infraestuctura local, ejecutar:

```
docker-compose  up
```

Ejecutar en modo desarrollo los microservicios orders, products, users:

```
cd users
mvn quarkus:dev
```

```
cd orders
mvn quarkus:dev
```

```
cd products
mvn quarkus:dev
```

Crear una tabla Users en DynamoDB con llave 'username'.
 
Para ello ingresar a http://localhost:8000/shell y ejecutar:

```
var params = {
    TableName: 'Users',
    KeySchema: [ 
        { 
            AttributeName: 'username',
            KeyType: 'HASH',
        }
    ],
    AttributeDefinitions: [ 
        {
            AttributeName: 'username',
            AttributeType: 'S', 
        }
    ],
    ProvisionedThroughput: { 
        ReadCapacityUnits: 1,
        WriteCapacityUnits: 1,
    },
};

dynamodb.createTable(params, function(err, data) {
    if (err) ppJson(err); 
    else ppJson(data); 
});
```
Asimismo, ingresar una data de prueba:

```
var params = {
    TableName: 'Users',
    Item: { 
    
        'username': 'marcozfr',
        'countryCode': 'PE',
        'createdDate' : 1602288000000
    }
};
docClient.put(params, function(err, data) {
    if (err) ppJson(err); 
    else ppJson(data);
});
```

Y listo!

Para ingresar a la tienda RobotoStore, ingresar a: http://localhost:4000


