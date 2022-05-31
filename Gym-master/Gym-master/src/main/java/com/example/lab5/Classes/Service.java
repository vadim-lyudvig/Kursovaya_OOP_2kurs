package com.example.lab5.Classes;

public class Service {
    private int id;
    private String title;
    private int cost;

    public Service(int id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
    public Service(){
        this.id =0;
        this.title = "title";
        this.cost = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Название: "+title+"\nСтоимость: "+cost;
    }

    public void setTitle(String title) throws Exception {
        if(title.length()==0){
            throw  new Exception("Вы не ввели название");
        }
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
