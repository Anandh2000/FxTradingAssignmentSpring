import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookTradeComponent } from './book-trade/book-trade.component';
import { ErrorResponseComponent } from './error-response/error-response.component';
import { HomeComponent } from './home/home.component';
import { TradeHistoryComponent } from './trade-history/trade-history.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  {
    path:'',redirectTo:'welcome',pathMatch:'full'
  },
  {
    path: "welcome", component: WelcomeComponent
  },
  {
    path:"home",component:HomeComponent
  },
  {
    path:"bookTrade",component:BookTradeComponent
  },
  {
    path:"home/bookTrade/back",redirectTo:'home',pathMatch:'full'
  },
  {
    path:"home/exit",redirectTo:'welcome',pathMatch:'full'
  },
  {
    path:"tradeHistory",component:TradeHistoryComponent
  },
  {
    path:"**",component:ErrorResponseComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
