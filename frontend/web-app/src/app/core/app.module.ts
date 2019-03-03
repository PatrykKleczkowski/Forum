import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app-component/app.component';
import {MatButtonModule} from '@angular/material';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SharedModule } from '@app/shared/modules/shared.module';
import { AuthService } from '@app/shared/services/auth.service';
import { AccessTokenInterceptor } from '@app/shared/services/access-token-interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatButtonModule,
    SharedModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
   { provide: HTTP_INTERCEPTORS,
    useClass: AccessTokenInterceptor,
    multi: true,
   }
  ],
  bootstrap: [AppComponent],
schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
