import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError((error) => {
      let userMessage = 'An unexpected error occurred. Please try again.';

      if (error.status === 0) {
        userMessage = 'Unable to connect to server. Please check if the backend is running.';
      } else if (error.status === 401) {
        userMessage = 'Session expired. Please login again.';
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        router.navigate(['/auth/login']);
      } else if (error.status === 403) {
        userMessage = 'You do not have permission to perform this action.';
      } else if (error.status === 404) {
        userMessage = 'The requested resource was not found.';
      } else if (error.status === 400 || error.status === 422) {
        if (error.error?.errors?.length) {
          userMessage = error.error.errors.join(', ');
        } else if (error.error?.message) {
          userMessage = error.error.message;
        } else {
          userMessage = 'Invalid request. Please check your input.';
        }
      } else if (error.status === 409) {
        userMessage = error.error?.message || 'A conflict occurred. The resource may already exist.';
      } else if (error.status >= 500) {
        userMessage = error.error?.message || 'Server error. Please try again later.';
      }

      const enrichedError = { ...error, userMessage };
      return throwError(() => enrichedError);
    })
  );
};
