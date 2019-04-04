import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { AUTHORIZATION_HEADER, AuthService } from './auth.service';

@Injectable()
export class AccessTokenInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    const authHeader = AUTHORIZATION_HEADER;
    console.log(authHeader);
    const accessToken = this.authService.getAuthorization();
console.log(accessToken);
    if (accessToken !== null) {
      request = request.clone({
        headers: request.headers.set(authHeader, accessToken),
        withCredentials: false
      });
    }

    return next.handle(request);
  }
}
