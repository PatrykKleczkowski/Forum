import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { AUTHORIZATION_HEADER, AuthService } from './auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ToastrService } from 'ngx-toastr';
import { throwError } from 'rxjs/internal/observable/throwError';
@Injectable()
export class AccessTokenInterceptor implements HttpInterceptor {
  
  private jwtHelper: JwtHelperService;

  constructor(private authService: AuthService, private toastr: ToastrService) {
    this.jwtHelper = new JwtHelperService();
  }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    const authHeader = AUTHORIZATION_HEADER;
    const accessToken = this.authService.getAuthorization();

    if (accessToken !== null) {
      if (this.jwtHelper.isTokenExpired(accessToken)) {
        return this.handleExpiredTokenError();
      }

      request = request.clone({
        headers: request.headers.set(authHeader, accessToken),
        withCredentials: false
      });
    }

    return next.handle(request);
  }

  handleExpiredTokenError = () => {
    this.toastr.error('Twoja sesja wygasła, zaloguj się ponownie', 'Błąd');
    this.authService.logout();
    return throwError('session expired');
  }
}