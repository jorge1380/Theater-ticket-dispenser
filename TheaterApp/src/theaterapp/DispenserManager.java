/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author jorge
 */
public class DispenserManager {
    private TranslatorManager translator = new TranslatorManager(); 
    private TheaterTicketDispenser dispenser = new TheaterTicketDispenser();
    private DispenserHardware dh = new DispenserHardware(this.dispenser);
    
    public void showScreen(int time,Screen screen)/*Muestra la pantalla y llama al método optionButtonPressed de la clase Screen*/{ 
        ScreenResult c = ScreenResult.continueInScreen;
        while (c == ScreenResult.continueInScreen){
            screen.begin(this.dh);
            switch (screen.getScreenMode())
                {
                    case optionsMode: {
                        this.dispenser.setMenuMode();
                        this.dispenser.setTitle(this.translator.translate(screen.getTitle()));
                        for (int i=0;i<screen.getOptions().size();i++){
                            if (this.translator.translate(screen.getOptions().get(i)) != null)
                                this.dispenser.setOption(i,this.translator.translate(screen.getOptions().get(i)));
                            else
                                this.dispenser.setOption(i,screen.getOptions().get(i));
                        }
                        this.dispenser.setDescription(this.translator.translate(screen.getDescription()));
                        this.dispenser.setImage(screen.getImage());
                        c = screen.optionButtonPressed(this.dh,this.dispenser.waitEvent(30));
                        break;
                    }
                    case theaterMode: {
                        this.dispenser.setTheaterMode(screen.getAreaState().getRows(),screen.getAreaState().getCols());
                        if (this.translator.translate(screen.getTitle()) != null)
                            this.dispenser.setTitle(this.translator.translate(screen.getTitle()));
                        this.drawArea(screen.getAreaState());
                        for (int i=0;i<screen.getOptions().size();i++)
                            this.dispenser.setOption(i,this.translator.translate(screen.getOptions().get(i)));
                        c = screen.optionButtonPressed(this.dh,this.dispenser.waitEvent(30));
                        break;
                    }
                    case messageMode: {
                        this.dispenser.setMessageMode();
                        this.dispenser.setTitle(this.translator.translate(screen.getTitle()));
                        if (this.translator.translate(screen.getDescription()) == null)
                            this.dispenser.setDescription(screen.getDescription());
                        else
                            this.dispenser.setDescription(this.translator.translate(screen.getDescription()));
                        for (int i=0;i<screen.getOptions().size();i++)
                            this.dispenser.setOption(i,this.translator.translate(screen.getOptions().get(i)));
                        c = screen.optionButtonPressed(this.dh,this.dispenser.waitEvent(30));
                    }
                }            
        }  
        
    }
    
    private void drawArea(TheaterAreaState t)/*Muestra los asientos de la zona por pantalla*/{ 
        for (int i=0;i<t.getRows();i++){
            for(int a=0;a<t.getCols();a++){
                if (t.getSeat(i, a) == SeatState.free)
                    this.dispenser.markSeat(i+1, a+1, 2);
                else if (t.getSeat(i, a) == SeatState.ocuppied)
                    this.dispenser.markSeat(i+1, a+1, 1);
                else
                    this.dispenser.markSeat(i+1, a+1, 0);
            }
        }
    }
    
    public void markSeat(byte row, byte col, int state)/*Marca el asiento seleccionado en el dispensador y lo muestra e el título*/{ 
        this.dispenser.setTitle(this.translator.translate("Fila").concat(" ").concat(Integer.toString(row)).concat(" ").concat(this.translator.translate("Asiento")).concat(" ").concat(Integer.toString(col)));
        this.dispenser.markSeat(row, col, state);
    }
    
    public void setIdiom(String idiom)/*Llama al setIdiom del TranslatorManager para colocarle el idioma seleccionado*/{ 
        this.translator.setIdiom(idiom);
    }

}
