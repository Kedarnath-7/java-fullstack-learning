import { Routes } from '@angular/router';

export const routes: Routes = [
    {path: '', loadComponent: () => import('./components/home/home').then(m => m.Home)},
    {path: 'about', loadComponent: () => import('./components/about/about').then(m => m.About)},
    {path: 'contact', loadComponent: () => import('./components/contact/contact').then(m => m.Contact)},
    {path: 'services', loadComponent: () => import('./components/services/services').then(m => m.Services)},
    {path: 'person', loadComponent: () => import('./components/person/person').then(m => m.Person)},
    {path: '**', redirectTo: ''}
];
