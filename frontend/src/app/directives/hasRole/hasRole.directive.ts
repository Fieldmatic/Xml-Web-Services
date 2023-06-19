import {Directive, Input, OnInit, TemplateRef, ViewContainerRef} from '@angular/core';
import { LoggedInUser } from 'src/app/auth/model/logged-in-user';
import {AuthService} from "../../auth/services/auth.service";

@Directive({
  selector: '[appHasRole]',
})
export class HasRoleDirective implements OnInit {
  currentUser!: LoggedInUser;
  requiredRoles!: string[];
  isVisible = false;

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.authService.loggedInUser.subscribe(user => this.currentUser = user);
  }

  @Input()
  set appHasRole(roles: string[]) {
    this.requiredRoles = roles;
    this.updateView();
  }

  private updateView() {
    if (this.hasRole()) {
      if (!this.isVisible) {
        this.isVisible = true;
        this.viewContainer.createEmbeddedView(this.templateRef);
      }
    } else {
      this.isVisible = false;
      this.viewContainer.clear();
    }
  }

  private hasRole(): boolean {
    if (this.currentUser && this.currentUser.role && this.requiredRoles) {
      return this.requiredRoles.includes(this.currentUser.role);
    }
    return false;
  }
}
