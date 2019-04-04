import { Component, OnInit } from '@angular/core';
import { AuthService } from '@app/core/services';


@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.scss']
})
export class AdminLayoutComponent implements OnInit {

  constructor(public authService: AuthService, ){}
  ngOnInit() {
  }
}
