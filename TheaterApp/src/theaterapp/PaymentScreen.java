/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import urjc.UrjcBankServer;

/**
 *
 * @author jorge
 */
class PaymentScreen extends Screen{
    private int price;
    private UrjcBankServer bank;
    private ErrorScreen errorScreen;
    private int numberOfSeats;

    public PaymentScreen(String title,ScreenMode mode, DispenserManager dispenser, int price, int seatsNumber)/*Constructor que inicializa las propiedades de la pantalla*/{
        super(mode, dispenser);
        super.setTitle(title);
        super.setDescription(Integer.toString(price).concat("€"));
        this.errorScreen = new ErrorScreen("Error al realizar el pago",mode,dispenser);
        this.numberOfSeats = seatsNumber;
    }
    
    @Override
    public ScreenResult begin(DispenserHardware dh)/*Método que inicializa las opciones de la pantalla*/{
        List<String> opts = new ArrayList<>();
        opts.add("Cancelar");
        opts.add("Pagar");
        super.setOptions(opts);
        dh.addInfoTicket(super.getDescription());
        return ScreenResult.continueInScreen;
    }

    @Override
    public ScreenResult crediCardDetected(DispenserHardware d)/*Método que realiza el pago de la tarjeta insertada*/{
        d.retainCreditCard(false);
        long creditCardNumber = d.getCardNumber();
        this.bank = new UrjcBankServer();
        if (this.bank.comunicationAvaiable()){
            try {
                bank.doOperation(creditCardNumber, price);
                for (int i=0;i<this.numberOfSeats;i++)
                    d.printTicket(d.getTicket(i));
                d.expelCreditCard();
                d.retainCreditCard(true);
            } catch (CommunicationException ex) {
                super.getDispenser().showScreen(30,this.errorScreen);
                Logger.getLogger(PaymentScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        d.clearTicket();
        return ScreenResult.exitScreen;
    }

    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c)/*Método que cambia a la pantalla correspondiente y en caso de detectar la tarjeta llama al método creditCardDetected*/{
        switch (c)
        {
            case 0: {
                d.clearTicket();
                return ScreenResult.exitScreen;
            }
            case 'A': {
                d.clearTicket();
                return ScreenResult.exitScreen;
            }
            case '1': {
                return this.crediCardDetected(d);
            }
            default :{
                    return ScreenResult.continueInScreen;
            }
        }        

    }
    
}
