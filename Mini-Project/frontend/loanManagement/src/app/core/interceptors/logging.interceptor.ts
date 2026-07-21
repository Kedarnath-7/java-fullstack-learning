import { HttpInterceptorFn } from '@angular/common/http';
import { tap } from 'rxjs';

export const loggingInterceptor: HttpInterceptorFn = (req, next) => {
  const startTime = Date.now();

  console.log(
    `%c[HTTP ${req.method}] ${req.url}`,
    'color: #2196F3; font-weight: bold;',
    req.body ? { payload: req.body } : ''
  );

  return next(req).pipe(
    tap({
      next: (event: any) => {
        if (event.status) {
          const elapsed = Date.now() - startTime;
          console.log(
            `%c[HTTP ${event.status}] ${req.method} ${req.url} (${elapsed}ms)`,
            'color: #4CAF50; font-weight: bold;',
            { response: event.body }
          );
        }
      },
      error: (error) => {
        const elapsed = Date.now() - startTime;
        console.error(
          `%c[HTTP ERROR ${error.status}] ${req.method} ${req.url} (${elapsed}ms)`,
          'color: #F44336; font-weight: bold;',
          { error: error.error, message: error.message }
        );
      }
    })
  );
};
