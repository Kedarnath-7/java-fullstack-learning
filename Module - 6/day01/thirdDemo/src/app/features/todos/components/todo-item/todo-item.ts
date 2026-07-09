import { Component, inject, Input } from '@angular/core';
import TodoDTO from '../../../../types/TodoDTO';
import { TodoService } from '../../services/todo-service';

@Component({
  selector: 'app-todo-item',
  imports: [],
  templateUrl: './todo-item.html',
  styleUrl: './todo-item.css',
})
export class TodoItem {
  @Input()
  todo!: TodoDTO;

  todoService: TodoService = inject(TodoService);
}
