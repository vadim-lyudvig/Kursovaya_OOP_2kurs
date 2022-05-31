package com.example.lab5.Classes;

public class Client {
    private int clientId;
    private String name;
    private String patronymic;
    private String surname;
    private String address;
    private String phoneNumber;

    public Client(int clientId, int staffId, String name, String patronymic, String surname, String address, String phoneNumber) {
        this.clientId = clientId;
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Client() {
        this.clientId = 0;
        this.name = "name";
        this.patronymic = "patronymic";
        this.surname = "surname";
        this.address = "address";
        this.phoneNumber = "phoneNumber";
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Имя: " + name + "\nОтчество: " + patronymic+"\n" +
                "Фамилия: " + surname + "\n Адрес: " + address + "\n Телефон: " + phoneNumber;
    }
}
