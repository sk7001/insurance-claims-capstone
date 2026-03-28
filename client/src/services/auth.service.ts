import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private token: string | null = null;
  private isLoggedIn: boolean = false;

  constructor() {}

  /* ======================
     TOKEN METHODS
     ====================== */

  setToken(token: string): void {
    this.token = token;
    this.isLoggedIn = true;
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isUserLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  /* ======================
     ROLE METHODS
     ====================== */

  setRole(role: string): void {
    localStorage.setItem('role', role);
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  /* ======================
     USER ID METHODS
     ====================== */

  setUserId(userId: string): void {
    localStorage.setItem('userId', userId);
  }

  getUserId(): string | null {
    return localStorage.getItem('userId');
  }

  /* ======================
     LOGOUT
     ====================== */

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('userId');

    this.token = null;
    this.isLoggedIn = false;
  }
}