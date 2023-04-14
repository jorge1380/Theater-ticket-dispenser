/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class AreaSelectionScreen extends Screen {
    private TheaterAreaState selectArea;
    private SeatSelectionScreen seatsScreen;
    private TheaterState theater;
    private LocalDate date;

    public AreaSelectionScreen(String title, ScreenMode mode, DispenserManager dispenser, TheaterState t, LocalDate date)/*Constructor en el cual se inicializan las propiedades de la pantalla*/ {
        super(mode,dispenser);
        super.setTitle(title);
        super.setDescription(""); 
        super.setImage(t.getImage());
        this.theater = t;
        this.date = date;
    }
    
    @Override
    public ScreenResult begin(DispenserHardware dh)/*Método en el cual se inicializan las opciones de la pantalla*/{
        List<String> optList = new ArrayList();
        
        for (int i=0;i<this.theater.getNumAreas();i++){
            int addPrice = this.theater.getArea(i).getPrice();
            if (this.date.getDayOfWeek() == DayOfWeek.FRIDAY || this.date.getDayOfWeek() == DayOfWeek.SATURDAY || this.date.getDayOfWeek() == DayOfWeek.SUNDAY)
                 addPrice = this.theater.getArea(i).getPrice() * dh.getModifier();
            optList.add(this.theater.getArea(i).getName().concat(" (").concat(Integer.toString(addPrice)).concat("€)"));
        }
        super.setOptions(optList);
     
        return ScreenResult.continueInScreen;
    }
    
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dh, char c)/*Método desde el que se elije la pantalla que se muestra y le pasa la información correspondiente*/{
        switch (c)
        {
            case 0: {
                dh.clearTicket();
                return ScreenResult.exitScreen;
            }
            case 'A': {
                this.selectArea = this.theater.getArea(0);
                dh.addInfoTicket(this.selectArea.getName());
                this.seatsScreen = new SeatSelectionScreen(this.selectArea.getName(),ScreenMode.theaterMode,this.getDispenser(),this.selectArea,this.date);
                super.getDispenser().showScreen(30, this.seatsScreen);
                return ScreenResult.exitScreen;
            }
            case 'B': {
                this.selectArea = this.theater.getArea(1);
                dh.addInfoTicket(this.selectArea.getName());
                this.seatsScreen = new SeatSelectionScreen(this.selectArea.getName(),ScreenMode.theaterMode,this.getDispenser(),this.selectArea,this.date);                
                super.getDispenser().showScreen(30, this.seatsScreen);
                return ScreenResult.exitScreen;
            }
            case 'C': {
                this.selectArea = this.theater.getArea(2);
                dh.addInfoTicket(this.selectArea.getName());
                this.seatsScreen = new SeatSelectionScreen(this.selectArea.getName(),ScreenMode.theaterMode,this.getDispenser(),this.selectArea,this.date);
                super.getDispenser().showScreen(30, this.seatsScreen);
                return ScreenResult.exitScreen;
            }
            case 'D': {
                this.selectArea = this.theater.getArea(3);
                dh.addInfoTicket(this.selectArea.getName());
                this.seatsScreen = new SeatSelectionScreen(this.selectArea.getName(),ScreenMode.theaterMode,this.getDispenser(),this.selectArea,this.date);
                super.getDispenser().showScreen(30, this.seatsScreen);
                return ScreenResult.exitScreen;
            }
            case 'E': {
                this.selectArea = this.theater.getArea(4);
                dh.addInfoTicket(this.selectArea.getName());
                this.seatsScreen = new SeatSelectionScreen(this.selectArea.getName(),ScreenMode.theaterMode,this.getDispenser(),this.selectArea,this.date);
                super.getDispenser().showScreen(30, this.seatsScreen);
                return ScreenResult.exitScreen;
            }
            case 'F': {
                dh.clearTicket();
                return ScreenResult.exitScreen;
            }
            default :
                return ScreenResult.continueInScreen;
        }
    }
}
