package com.example.lab5.Classes;

import com.example.lab5.DataBase.DataBase;

import java.time.LocalDate;
import java.sql.Date;

public class TaskTable {
    //Номер
    private int number;
    //Номер сотрудника
    private int codeStaff;
    //Номер клиента
    private int codeClient;
    //Номер зала
    private int codeHall;
    //Дата
    private Date date;
    //Продолжительность
    private int duration;

    private String clientInfo;
    private String staffInfo;
    private String hallInfo;

    public String getClientInfo() {
        var client = DataBase.foundClient(codeClient);
        clientInfo = String.format("""
                Имя: %s
                Отчество: %s
                Фамилия: %s
                """, client.getName(), client.getPatronymic(), client.getSurname());
        return clientInfo;

    }

    public String getStaffInfo() {
        var staff = DataBase.foundStaff(codeStaff);
        staffInfo = String.format("""
                Имя: %s
                Отчество: %s
                Фамилия: %s
                """, staff.getName(), staff.getPatronymic(), staff.getSurname());
        return staffInfo;
    }

    public String getHallInfo() {
        var hall = DataBase.foundHall(codeHall);
        hallInfo = String.format("""
                Название: %s
                """, hall.getHallName());
        return hallInfo;
    }

    public TaskTable(int number, int codeEmployee, int codeClient, int codeRoom, Date date, int duration) {
        this.number = number;
        this.codeStaff = codeEmployee;
        this.codeClient = codeClient;
        this.codeHall = codeRoom;
        this.date = date;
        this.duration = duration;
    }

    public TaskTable(){
        this.number = 0;
        this.codeStaff = 0;
        this.codeClient = 0;
        this.codeHall = 0;
        this.date = Date.valueOf(LocalDate.now());
        this.duration = 0;
        clientInfo = "";
        staffInfo = "";
        hallInfo = "";
    }
    public int getCodeClient() {
        return codeClient;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCodeStaff() {
        return codeStaff;
    }

    public void setCodeStaff(int codeStaff) {
        this.codeStaff = codeStaff;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    public int getCodeHall() {
        return codeHall;
    }

    public void setCodeHall(int codeHall) {
        this.codeHall = codeHall;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return  "Клиент:\n " + DataBase.foundClient(codeClient) + "\n\n" +
                "Сотрудник:\n " + DataBase.foundStaff(codeStaff) + "\n\n" +
                "Зал: " + DataBase.foundHall(codeHall) + "\n\n" +
                "Дата:" + date.toString() + "\n" +
                "Продолжительность: " + duration;
    }
}
