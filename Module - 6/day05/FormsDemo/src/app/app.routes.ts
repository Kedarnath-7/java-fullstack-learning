import { Routes } from '@angular/router';

export const routes: Routes = [
    {path: 'special-form', loadComponent: () => import('./special-form/special-form').then(m => m.SpecialForm)},
    {path: 'normal-form', loadComponent: () => import('./normal-form/normal-form').then(m => m.NormalForm)},
    {path: 'reactive-form', loadComponent: () => import('./reactive-form/reactive-form').then(m => m.ReactiveForm)},
];
