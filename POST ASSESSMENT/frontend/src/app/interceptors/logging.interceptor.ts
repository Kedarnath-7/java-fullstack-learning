import { HttpInterceptorFn } from '@angular/common/http';
import { tap } from 'rxjs/operators';

export const loggingInterceptor: HttpInterceptorFn = (req, next) => {
  const startTime = Date.now();
  
  console.log(`%c[API Request] ${req.method} ${req.url}`, 'color: #2196F3; font-weight: bold;');
  if (req.body) {
    console.log('%c[Request Body]', 'color: #2196F3;', req.body);
  }

  return next(req).pipe(
    tap({
      next: (event: any) => {
        if (event.body !== undefined) {
          const duration = Date.now() - startTime;
          console.log(
            `%c[API Response] ${req.method} ${req.url} (${duration}ms)`,
            'color: #4CAF50; font-weight: bold;'
          );
          console.log('%c[Response Body]', 'color: #4CAF50;', event.body);
        }
      },
      error: (error) => {
        const duration = Date.now() - startTime;
        console.log(
          `%c[API Error] ${req.method} ${req.url} (${duration}ms)`,
          'color: #F44336; font-weight: bold;'
        );
        console.log('%c[Error Details]', 'color: #F44336;', error);
      },
    })
  );
};
