import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private http: HttpClient) { }

  login() {
    const loginData = {
      email: this.username,
      senha: this.password
    };

    this.http.post<any>('http://localhost:8080/v1/auth/login', loginData)
      .subscribe(
        response => {
          const jwt = response.jwt
          console.log('JWT:', jwt);
        },
        error => {
          const mensagem = error.error.mensagem
          alert(mensagem);
        }
      );
  }
}
