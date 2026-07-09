import { Component, inject } from '@angular/core';
import { TodoService } from '../../services/todo-service';
import { TodoItem } from '../todo-item/todo-item';
import { AddTodo } from "../add-todo/add-todo";

@Component({
  selector: 'app-todos-component',
  imports: [TodoItem, AddTodo],
  templateUrl: './todos-component.html',
  styleUrl: './todos-component.css',
})
export class TodosComponent {

  todoService: TodoService;

  constructor() {
    this.todoService = inject(TodoService);
  }

}
