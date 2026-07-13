import { Component, signal } from '@angular/core';

import { ShowAllEmployees } from "./components/show-all-employees/show-all-employees";
import { AddEmployee } from './components/add-employee/add-employee';

@Component({
  selector: 'app-root',
  imports: [AddEmployee, ShowAllEmployees],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

}
