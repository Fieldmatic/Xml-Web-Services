import { CustomValidators } from '../../validators/custom-validators';
import { ActivatedRoute } from '@angular/router';
import {Component, Inject, OnInit,} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import { AppConfig } from 'src/app/appConfig/appconfig.interface';
import { APP_SERVICE_CONFIG } from 'src/app/appConfig/appconfig.service';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent implements OnInit {
  authForm!: FormGroup;
  isLoginMode: boolean;

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private authService: AuthService
  ) {}

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

  onSubmit(formDirective: FormGroupDirective) {
    if (this.isLoginMode) {
      const email = this.authForm.getRawValue()['email'];
      const password = this.authForm.getRawValue()['password'];
      this.authService.logIn(email, password).subscribe();
    } else {
      if (!this.authForm.valid) {
        return;
      }
      const email = this.authForm.getRawValue()['email'];
      const password = this.authForm.getRawValue()['password'];
      const role = this.authForm.getRawValue()['role'];
      const name = this.authForm.getRawValue()['name'];
      const surname = this.authForm.getRawValue()['surname'];
      this.authService.signUp(email, password, role, name, surname).subscribe();
    }
    formDirective.resetForm();
    this.authForm.reset();
  }
}
