import { Inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, catchError, map, of, tap } from "rxjs";
import { ToastrService } from "ngx-toastr";
import { parseString } from "xml2js";
import { Router } from "@angular/router";
import { APP_SERVICE_CONFIG } from "../../appConfig/appconfig.service";
import { AppConfig } from "../../appConfig/appconfig.interface";
import { LoggedInUser } from "../model/logged-in-user";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  loggedInUser: BehaviorSubject<LoggedInUser | null> = new BehaviorSubject(null);

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient,
    private router: Router,
    private toastrService: ToastrService
  ) {
  }

  logIn(email: string, password: string) {
    let body =
      "<loginRequest>" +
      "<email>" +
      email +
      "</email>" +
      "<password>" +
      password +
      "</password>" +
      "</loginRequest>";
    return this.http
      .post(this.config.usersEndpoint + "auth/login", body, {
        observe: "body",
        responseType: "text",
        headers: {
          "Content-Type": "application/xml",
          Accept: "application/xml"
        }
      })
      .pipe(
        tap(() => {
          this.router.navigate(["/"]);
        }),
        map((resData) => {
          return this.handleAuthentication(resData);
        }),
        catchError((errorResp) => {
          this.handleError(errorResp);
          return of();
        })
      );
  }

  logOut() {
    localStorage.removeItem("userData");
    this.router.navigate(["/"]);
    this.loggedInUser.next(null);
  }

  signUp(email: string, password: string, role: string, name: string, surname: string) {
    let body =
      "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
      "<user xmlns=\"http://www.xml.tim14.rs/user\">" +
      "<email>" +
      email +
      "</email>" +
      "<password>" +
      password +
      "</password>" +
      "<ime>" +
      name +
      "</ime>" +
      "<prezime>" +
      surname +
      "</prezime>" +
      "<role>" +
      role +
      "</role>" +
      "</user>";
    return this.http
      .post(this.config.usersEndpoint + "auth", body, {
        observe: "body",
        responseType: "text",
        headers: {
          "Content-Type": "application/xml",
          Accept: "application/xml"
        }
      })
      .pipe(
        tap(() => {
          this.notifySuccess("Uspesno ste se registrovali!");
          this.router.navigate(["/auth/login"]);
        }),
        catchError((errorResp) => {
          this.handleError(errorResp);
          return of();
        })
      );
  }

  handleAuthentication(loginResponse: string) {
    console.log(loginResponse);
    let user: LoggedInUser = parseString(
      loginResponse,
      (error, result) => {
        const user = new LoggedInUser(
          result["loginResponse"]["email"][0],
          result["loginResponse"]["role"][0],
          result["loginResponse"]["ime"][0],
          result["loginResponse"]["prezime"][0]
        );
        localStorage.setItem("userData", JSON.stringify(user));
        this.loggedInUser.next(user);
        this.notifySuccess("Uspesno ste se ulogovali!");
        return user;
      }
    );
    return user;
  }

  handleError(errorRes: any) {
    parseString(errorRes.error, (error, result) => {
      this.toastrService.error(
        result["exceptionResponse"]["message"][0],
        "Notification",
        {
          timeOut: 3000,
          tapToDismiss: false,
          newestOnTop: true,
          positionClass: "toast-top-center"
        }
      );
    });
  }

  notifySuccess(message: string) {
    this.toastrService.success(message, "Notification", {
      timeOut: 3000,
      tapToDismiss: false,
      newestOnTop: true,
      positionClass: "toast-top-center"
    });
  }

  autoLogIn() {
    const userData = localStorage.getItem("userData");
    const user = JSON.parse(userData);
    this.loggedInUser.next(user);
  }
}
