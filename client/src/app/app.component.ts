import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  IsLoggin: boolean = false;
  roleName: string | null = null;
  showHeader: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {

    // initial values
    this.IsLoggin = this.authService.getLoginStatus;
    this.roleName = this.authService.getRole;

    // redirect if not logged in
    if (!this.IsLoggin) {
      this.router.navigateByUrl('/login');
    }

    // ✅ CONTROL NAVBAR VISIBILITY BY ROUTE
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.showHeader = ![
          '/login',
          '/registration'
        ].includes(event.url);
      }
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/login');
  }
}