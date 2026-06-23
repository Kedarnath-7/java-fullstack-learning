package com.northernArc.firstbootapp.model;

public class Todo {
    private int id;
    private String content;
    private boolean done;

    public Todo(){

    }

    public Todo(String content, boolean done){
        this.content = content;
        this.done = done;
    }

    public Todo(int id, String content, boolean done){
        this.id= id;
        this.content = content;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo - [id: " + this.id + ", content: " + this.content + ", done: " + this.done + "]";
    }
}
