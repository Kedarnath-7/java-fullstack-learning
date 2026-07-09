import { Injectable } from '@angular/core';
import TodoDTO from '../../../types/TodoDTO';

@Injectable({
  providedIn: 'root',
})
export class TodoService {

  private todos: TodoDTO[] = [
    {
      id: 1,
      title: 'Learn Angular',
      description: 'Learn the basics of Angular framework',
      completed: true
    },
    {
      id: 2,
      title: 'Build a Todo App',
      description: 'Create a simple todo application using Angular',
      completed: false
    },
    {
      id: 3,
      title: 'Write Unit Tests',
      description: 'Write unit tests for the todo application',
      completed: false
    },
    {
      id: 4,
      title: 'Deploy the App',
      description: 'Deploy the todo application to a web server',
      completed: false
    },
    {
      id: 5,
      title: 'Write Documentation',
      description: 'Write documentation for the todo application',
      completed: false
    }
  ];

  public getTodos(): TodoDTO[] {
    return this.todos;
  }

  public addTodo(todo: TodoDTO): void {
    this.todos.push(todo);
  }

  public updateTodo(updatedTodo: TodoDTO): void {
    const index = this.todos.findIndex(todo => todo.id === updatedTodo.id);
    if (index !== -1) {
      this.todos[index] = updatedTodo;
    }
  }
  
  public deleteTodo(id: number): void {
    this.todos = this.todos.filter(todo => todo.id !== id);
  }

  toggleTodoCompletion(id: number): void {
    const todo = this.todos.find(t => t.id === id);
    if (todo) {
      todo.completed = !todo.completed;
    }
  }

}
