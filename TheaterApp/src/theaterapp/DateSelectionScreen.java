/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
class DateSelectionScreen extends Screen {
    private Theater theater = new Theater();
    private Map<String,TheaterState> Schedule;
    private AreaSelectionScreen areaScreen;
    
    public DateSelectionScreen(String title, ScreenMode mode, DispenserManager dispenser)/*Constructor que inicializa las propiedades de la pantalla y las opciones*/ {
        super(mode, dispenser);
        super.setTitle(title);
        super.setDescription("");
        
        this.Schedule = new TreeMap();
        LocalDate date = LocalDate.now();
        Theater theat = new Theater();
        List<String> opts = new ArrayList<>();
        
        for (int i=0;i<5;i++){
            if (date.getDayOfWeek() == DayOfWeek.MONDAY)
                date = date.plusDays(1);
            opts.add(date.toString());
            date = date.plusDays(1);
        }
                
        opts.add("Cancelar");
        super.setOptions(opts);
        this.loadStateFiles();
    }
        
    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dh, char c)/*Muestra la pantalla correspondiente al botón pulsado*/{
        LocalDate date = LocalDate.now();
        if (date.getDayOfWeek() == DayOfWeek.MONDAY)
                date = date.plusDays(1);
        switch (c)
        {
            case 0: {
                dh.clearTicket();
                return ScreenResult.exitScreen;
            }
            case 'A': {
                dh.addInfoTicket(super.getOptions().get(0));
                dh.setModifier(Schedule.get(super.getOptions().get(0)).getModifier());
                this.areaScreen = new AreaSelectionScreen("Seleccione el área",ScreenMode.optionsMode,super.getDispenser(),Schedule.get(super.getOptions().get(0)),date);
                super.getDispenser().showScreen(30,this.areaScreen);
                return ScreenResult.exitScreen;
            }
            case 'B': {
                dh.addInfoTicket(super.getOptions().get(1));
                dh.setModifier(Schedule.get(super.getOptions().get(1)).getModifier());
                this.areaScreen = new AreaSelectionScreen("Seleccione el área",ScreenMode.optionsMode,super.getDispenser(),Schedule.get(super.getOptions().get(1)),date.plusDays(1));
                super.getDispenser().showScreen(30,this.areaScreen);
                return ScreenResult.exitScreen;
            }
            case 'C': {
                dh.addInfoTicket(super.getOptions().get(2));
                dh.setModifier(Schedule.get(super.getOptions().get(2)).getModifier());
                this.areaScreen = new AreaSelectionScreen("Seleccione el área",ScreenMode.optionsMode,super.getDispenser(),Schedule.get(super.getOptions().get(2)),date.plusDays(2));
                super.getDispenser().showScreen(30,this.areaScreen);
                return ScreenResult.exitScreen;
            }
            case 'D': {
                dh.addInfoTicket(super.getOptions().get(3));
                dh.setModifier(Schedule.get(super.getOptions().get(3)).getModifier());
                this.areaScreen = new AreaSelectionScreen("Seleccione el área",ScreenMode.optionsMode,super.getDispenser(),Schedule.get(super.getOptions().get(3)),date.plusDays(3));
                super.getDispenser().showScreen(30,this.areaScreen);
                return ScreenResult.exitScreen;
            }
            case 'E': {;
                dh.addInfoTicket(super.getOptions().get(4));
                dh.setModifier(Schedule.get(super.getOptions().get(4)).getModifier());
                this.areaScreen = new AreaSelectionScreen("Seleccione el área",ScreenMode.optionsMode,super.getDispenser(),Schedule.get(super.getOptions().get(4)),date.plusDays(4));
                super.getDispenser().showScreen(30,this.areaScreen);
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

    private void loadStateFiles()/*Carga el estado del teatro correspondiente a cada día*/{
        LocalDate date = LocalDate.now();
        for (int i=0;i<5;i++){
            String file = "ConfigFilesExample/States/".concat(super.getOptions().get(i).concat("Theater.txt")); 
            if (date.getDayOfWeek() == DayOfWeek.MONDAY)
                date = date.plusDays(1);
            try {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
                TheaterState theater = (TheaterState) input.readObject();
                    this.Schedule.put(super.getOptions().get(i), theater);
                    date = date.plusDays(1);
              
            } catch (FileNotFoundException ex) {
                File f = new File("ConfigFilesExample/States/".concat(super.getOptions().get(i).concat("Theater.txt")));
                TheaterState theat = new TheaterState(this.theater,date);
                this.Schedule.put(super.getOptions().get(i), theat);
                date = date.plusDays(1);
            }
            catch (IOException ex) {
                TheaterState theat = new TheaterState(this.theater,date);
                this.Schedule.put(super.getOptions().get(i), theat);
                date = date.plusDays(1);
                Logger.getLogger(DateSelectionScreen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DateSelectionScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateStateFiles()/*Actualiza los ficheros del teatro correspondiente a cada día*/{
        for (int i=0;i<5;i++){
            String file = "ConfigFilesExample/States/".concat(super.getOptions().get(i).concat("Theater.txt"));
            try {
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
                output.writeObject(this.Schedule.get(super.getOptions().get(i)));
            } catch (FileNotFoundException ex) {
                System.out.println("No se ha encontrado el fichero");
                Logger.getLogger(DateSelectionScreen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DateSelectionScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int getScheduleSize(){
        return this.Schedule.size();
    }
}
