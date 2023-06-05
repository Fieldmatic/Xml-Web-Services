import { CustomValidators } from '../../validators/custom-validators';
import * as AuthActions from '../../store/auth.actions';
import { filter, first, Observable, Subscription, tap } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import {
  AfterViewInit,
  Component,
  Inject,
  NgZone,
  OnDestroy,
  OnInit,
  Renderer2,
} from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupDirective,
  Validators,
} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AppConfig } from 'src/app/appConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from 'src/app/appConfig/appconfig.service';
import * as fromApp from '../../../store/app.reducer';
import { select, Store } from '@ngrx/store';
import { getUserExistsSelector } from 'src/app/auth/store/auth.selectors';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-login',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent implements OnInit, OnDestroy {
  private storeSub: Subscription;
  authForm!: FormGroup;
  isLoginMode: boolean;
  error: string = null;
  userExists$: Observable<boolean>;

  callback = null;
  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private formBuilder: FormBuilder,
    private store: Store<fromApp.AppState>,
    private activatedRoute: ActivatedRoute,
    private render: Renderer2,
    private ngZone: NgZone,
    private toastrService: ToastrService
  ) {}

  ngOnDestroy(): void {
    this.storeSub.unsubscribe();
  }

  ngOnInit(): void {
    this.reloadIfNecessary();
    this.authForm = this.formBuilder.group(
      {
        email: new FormControl('', {
          validators: [
            Validators.required,
            Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
          ],
        }),
        password: new FormControl('', {
          validators: [
            Validators.required,
            Validators.pattern(
              '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$'
            ),
          ],
        }),
        confirmPassword: new FormControl('', {
          validators: [Validators.required],
        }),
        name: new FormControl('', {
          validators: [
            Validators.required
          ],
        }),
        surname: new FormControl('', {
          validators: [
            Validators.required
          ],
        }),
        role: new FormControl('Klijent'),
      },
      {
        validator: CustomValidators.MatchValidator(
          'password',
          'confirmPassword'
        ),
      }
    );

    this.activatedRoute.params.subscribe((params) => {
      let authMode = params['authMode'];
      if (authMode === 'login') {
        this.isLoginMode = true;
        this.authForm.get('email').clearValidators();
        this.authForm.get('password').clearValidators();
      } else if (authMode === 'signup') {
        this.isLoginMode = false;
        this.authForm
          .get('email')
          .addValidators([
            Validators.required,
            Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
          ]);
        this.authForm
          .get('password')
          .addValidators([
            Validators.required,
            Validators.pattern(
              '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$'
            ),
          ]);
      }
    });

    this.storeSub = this.store.select('auth').subscribe((authState) => {
      this.error = authState.authError;
    });

    this.userExists$ = this.store.pipe(select(getUserExistsSelector));
  }
  private reloadIfNecessary() {
    var isLoadedBefore = localStorage.getItem('IsLoadedBefore');
    if (isLoadedBefore === 'true') {
      localStorage.removeItem('IsLoadedBefore');
    } else {
      localStorage.setItem('IsLoadedBefore', 'true');
      location.reload();
    }
  }

  setRoleValue(value: string) {
    this.authForm['role'] = value;
  }

  onSubmit(formDirective: FormGroupDirective) {
    if (this.isLoginMode) {
      this.store.dispatch(
        new AuthActions.LoginStart({
          email: this.authForm.getRawValue()['email'],
          password: this.authForm.getRawValue()['password'],
        })
      );
    } else {
      if (!this.authForm.valid) {
        return;
      }

      this.store.dispatch(
        new AuthActions.SignupStart({
          email: this.authForm.getRawValue()['email'],
          password: this.authForm.getRawValue()['password'],
          role: this.authForm.getRawValue()['role'],
          name: this.authForm.getRawValue()['name'],
          surname: this.authForm.getRawValue()['surname']
        })
      );
    }
    formDirective.resetForm();
    this.authForm.reset();
  }
}
