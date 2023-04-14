/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author jorge
 */
class DispenserHardware {
    private TheaterTicketDispenser dispenser;
    private List<List<String>> tickets;   
    private int modifier;
    
    DispenserHardware(TheaterTicketDispenser dispenser)/*Constructor que asigna el dispensador que recibe como parámetro a la propiedad dispenser*/{
        this.dispenser = dispenser;
        this.tickets = new ArrayList<>();
        for (int i=0;i<4;i++)
            this.tickets.add(new ArrayList<>());
    }
    
    public void printTicket(List msg)/*Método que usa el dispenser para imprimir el ticket con los mensajes de la lista que recibe por parámetro*/{
        this.dispenser.print(msg);
    }
    
    public void retainCreditCard(boolean bool)/*Método que hace que el dispenser retenga la tarjeta o no*/{
        this.dispenser.retainCreditCard(bool);
    }
    
    public void expelCreditCard()/*Método que hace que el dispenser expulse la tarjeta*/{
        this.dispenser.expelCreditCard(10);
    }
    
    public long getCardNumber()/*Método que devuelve el número de la tarjeta que detecta el dispenser*/{
        return this.dispenser.getCardNumber();
    }
    
    public void addInfoTicketByPos(String msg,int pos){
            this.tickets.get(pos).add(msg);
    }
    
    public void addInfoTicket(String msg){
        for (int i=0;i<4;i++)
            this.tickets.get(i).add(msg);
    }

    List getTicket(int pos) {
        return this.tickets.get(pos);
    }
    
    public void clearTicket(){
        this.tickets = new ArrayList<>();
        List <String> newTicket = new ArrayList<>();
        for (int i=0;i<4;i++)
            this.tickets.add(newTicket);
    }
    
    public void setModifier(int mod){
        this.modifier = mod;
    }
    
    public int getModifier(){
        return this.modifier;
    }
    
}
