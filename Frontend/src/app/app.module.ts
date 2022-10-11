import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxPaginationModule } from 'ngx-pagination';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { BookTradeComponent } from './book-trade/book-trade.component';
import {  TradeHistoryComponent } from './trade-history/trade-history.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ErrorResponseComponent } from './error-response/error-response.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { SideMenuBarComponent } from './side-menu-bar/side-menu-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BookTradeComponent,
    TradeHistoryComponent,
    WelcomeComponent,
    ErrorResponseComponent,
    SideMenuBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
