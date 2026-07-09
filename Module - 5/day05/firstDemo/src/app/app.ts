import { Component, inject, OnInit, signal, WritableSignal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import EmployeeDTO from './dto/EmployeeDTO';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  ngOnInit(): void {
    this.getAll();
  }

  protected httpClient:HttpClient = inject(HttpClient);
  protected title = 'Kedarnath';
  protected person = {fname: "Kedarnath", lName: "Nagaradone", age: 21};
  protected avengers = ["Iron Man", "Captain America", "Thor Odinson", "Hulk", "Black Widow", "Hawkeye"];
  protected newAvenger = '';
  protected names: WritableSignal<EmployeeDTO[]> = signal<EmployeeDTO[]>([]);
  protected cskBestXII = [
    {name: "Sanju Samson", role: "Wicket Keeper"},
    {name: "Ayush Mathre", role: "Batsman"},
    {name: "Urvil Patel", role: "Batsman"},
    {name: "Ruturaj Gaikwad", role: "Batsman"},
    {name: "Dewald Brevis", role: "Batsman"},
    {name: "Shivam Dube", role: "All Rounder"},
    {name: "MS Dhoni", role: "Wicket Keeper"},
    {name: "Anshul Kamboj", role: "Bowler"},
    {name: "Noor Ahmed", role: "Spinner"},
    {name: "Khaleel Ahmed", role: "Bowler"},
    {name: "Nathan Ellis", role: "Bowler"},
    {name: "Akeal Hoseien", role: "All Rounder"}
  ];

  showBestXII() {
    
  }

  removeAvenger(avenger: string) {
    this.avengers = this.avengers.filter(a => a !== avenger);
  }

  addAvenger() {
    if(this.newAvenger.trim() !== '') {
      this.avengers.push(this.newAvenger.trim());
    }
    this.newAvenger = '';
  }

  updateAvenger(index: number){
    if(this.newAvenger.trim() !== '') {
      this.avengers[index] = this.newAvenger.trim();
      this.newAvenger = '';
    }
  }

  getAll(){
    this.httpClient.get<EmployeeDTO[]>('http://localhost:8080/api/employees').subscribe({
      next:(data)=>{
        console.log(data);
        this.names.set(data);
      }
    });
  }
}
