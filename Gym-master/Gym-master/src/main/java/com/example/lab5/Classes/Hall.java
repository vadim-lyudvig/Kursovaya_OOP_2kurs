package com.example.lab5.Classes;
//Класс, реализующий зал
public class Hall {
    //id Зала
    private int hallId;
    //Название зала
    private String hallName;
    //Конструктор с параметрами
    public Hall(int hallId, String hallName) {
        this.hallId = hallId;
        this.hallName = hallName;
    }
    //Конструктор без параметров
    public Hall() {
        this.hallId = 0;
        this.hallName = "hallName";
    }
    //Стандартный набор геттреов и сеттеров
    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) throws Exception {
        //Проверка на пустоту
        if(hallName.length()==0){
            throw new Exception("Пусте значение");
        }
        this.hallName = hallName;
    }

    @Override
    public String toString() {
        return "Название: "+ hallName;
    }
}
