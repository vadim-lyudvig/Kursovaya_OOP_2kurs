package com.example.lab5.Classes;

import com.example.lab5.DataBase.DataBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.time.LocalDate;

public class ClubCard {
    private int cardId;
    private Date startCard;
    private Date endCard;
    private int price;
    private int serviceId;
    private int clientId;
    private String serviceInfo;
    private String clientInfo;

    public ClubCard(int cardId, Date startCard, Date endCard, int price, int serviceId, int clientId) {
        this.cardId = cardId;
        this.startCard = startCard;
        this.endCard = endCard;
        this.price = price;
        this.serviceId = serviceId;
        this.clientId = clientId;
    }

    public String getServiceInfo() {
         var service = DataBase.foundService(serviceId);
        serviceInfo = String.format("""
                Название: %s
                """, service.getTitle());
        return serviceInfo;
    }

    public String getClientInfo() {
         var client = DataBase.foundClient(clientId);
        clientInfo = String.format("""
                Имя: %s
                Отчество: %s
                Фамилия: %s
                """, client.getName(), client.getPatronymic(), client.getSurname());
        return clientInfo;
    }

    public ClubCard() {
        this.cardId = 0;
        this.startCard = Date.valueOf(LocalDate.now());
        this.endCard = Date.valueOf(LocalDate.now().plusDays(2));
        this.price = 0;
        this.serviceId = 0;
        this.clientId = 0;
        serviceInfo = "";
        clientInfo="";
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public Date getStartCard() {
        return startCard;
    }

    public void setStartCard(Date startCard) {
        this.startCard = startCard;
    }

    public Date getEndCard() {
        return endCard;
    }

    public void setEndCard(Date endCard) {
        this.endCard = endCard;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Информация о клиенте:\n " + DataBase.foundClient(clientId).toString() + "\n\n" +
                "Информация об услуге:\n" + DataBase.foundService(serviceId)+"\n\n" +
                "Дата начала абонемента: " + startCard.toString() + "\n" +
                "Дата конца абонемента: " + endCard.toString() +"\n" +
                "Стоимость: " + price;
    }

}
