## Deployment

### To run all services via docker-compose:
1) From `./currency-converter` directory run `docker-build.sh`
2) From `./currency-converter-ui` run `docker-build.sh`
3) From `./` run `docker-compose up`

### To run services for development
1) From `./currency-converter` run `docker-compose up`. This will start database.
2) Launch `CurrencyConverterApplication.java`
3) From `./currency-converter-ui` run `npm start`. This will launch UI with hot reload

## What is next?

1) UI will be available at `localhost:3000`
2) Backend Swagger is available at `http://localhost:8090/currency-calculator/swagger-ui.html`
3) Some backend requiests can also be tested via `prepared-request.http` file