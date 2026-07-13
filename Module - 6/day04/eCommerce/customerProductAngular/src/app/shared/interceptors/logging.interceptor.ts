import { HttpInterceptorFn } from '@angular/common/http';
import { tap } from 'rxjs';

export const loggingInterceptor: HttpInterceptorFn = (req, next) => {
  const startTime = Date.now();
  console.log(`[HTTP] --> ${req.method} ${req.urlWithParams}`);

  if (req.body) {
    console.log('[HTTP] Request Body:', req.body);
  }

  return next(req).pipe(
    tap({
      next: (event: any) => {
        if (event.status) {
          const elapsed = Date.now() - startTime;
          console.log(`[HTTP] <-- ${req.method} ${req.urlWithParams} [${event.status}] (${elapsed}ms)`);
          if (event.body) {
            console.log('[HTTP] Response Body:', event.body);
          }
        }
      },
      error: (error) => {
        const elapsed = Date.now() - startTime;
        console.error(`[HTTP] <-- ${req.method} ${req.urlWithParams} [${error.status}] (${elapsed}ms)`);
        console.error('[HTTP] Error:', error.error || error.message);
      }
    })
  );
};
