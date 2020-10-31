import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeModule } from './home/home.module';
import { PaymentModule } from './payment/payment.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LayoutComponent } from './@shared/components/layout/layout.component';
import { HttpErrorInterceptor } from './@shared/interceptors/HttpErrorInterceptor'
import { LoaderInterceptor } from './@shared/interceptors/LoaderInterceptor'
import { ReactiveFormsModule } from '@angular/forms';
import { LoaderComponent } from './@shared/components/loader/loader.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    LoaderComponent
  ],
  imports: [
    BrowserModule,
    MatProgressSpinnerModule,
    HomeModule,
    PaymentModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserModule,
    NgbModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpErrorInterceptor,
    multi:true
  },{
    provide: HTTP_INTERCEPTORS,
    useClass: LoaderInterceptor,
    multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
