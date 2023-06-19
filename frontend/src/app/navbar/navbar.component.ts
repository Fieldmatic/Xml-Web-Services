import { LoggedInUser } from 'src/app/auth/model/logged-in-user';
import {Component, OnInit,} from '@angular/core';
import {AuthService} from "../auth/services/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  loggedInUser: LoggedInUser = null;

  constructor(private authService: AuthService) {}

  get isAuthenticated(): boolean {
    return !!this.loggedInUser;
  }

  ngOnInit(): void {
    this.authService.loggedInUser.subscribe(user => this.loggedInUser = user);
  }

  onLogout() {
    this.authService.logOut();
  }
}
