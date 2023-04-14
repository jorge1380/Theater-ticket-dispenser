/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class WelcomeScreen extends Screen{
    private Theater theater;
    private Play play = new Play();
    private DateSelectionScreen datesScreen;
    private IdiomSelectionScreen idiomScreen;


    public WelcomeScreen(Theater theat, String title, ScreenMode mode, DispenserManager dispenser)/*Constructor en el cual se inicializan las propiedades de la pantalla*/{ 
        super(mode, dispenser);                                                                     
        super.setTitle(play.getTitle());
        this.theater = theat;
                
        super.setDescription(this.play.getDescription());
        super.setImage(this.play.getImage());
        
        List<String> opt = new ArrayList<>();
        opt.add("Seleccionar fecha");
        opt.add("Seleccionar idioma");
        opt.add("");
        opt.add("");
        opt.add("");
        opt.add("");

        this.setOptions(opt);
        
        this.datesScreen = new DateSelectionScreen("Seleccione una fecha",ScreenMode.optionsMode,super.getDispenser());
        this.idiomScreen = new IdiomSelectionScreen("Seleccione un idioma",ScreenMode.optionsMode,super.getDispenser());
        
        
    }
    
    @Override
    public ScreenResult begin(DispenserHardware d)/*Método en el cual se llama al método de la clase dateScreen que actualiza los ficheros de los días*/{ 
        if (this.datesScreen.getScheduleSize() != 0)
            this.datesScreen.updateStateFiles();
        return ScreenResult.continueInScreen;
    }

    @Override
    public ScreenResult optionButtonPressed(DispenserHardware dh, char c)/*Método desde el se elige que pantalla se muestra*/{ 
        switch (c)
        {
            case 0: {
                return ScreenResult.continueInScreen;
            }
            case 'A': {
                dh.addInfoTicket(super.getTitle());
                dh.addInfoTicket("====================");
                super.getDispenser().showScreen(30,this.datesScreen);
                return ScreenResult.continueInScreen;
            }
            case 'B': {
                super.getDispenser().showScreen(30,this.idiomScreen);
                return ScreenResult.continueInScreen;
            }
            default: {
                return ScreenResult.continueInScreen;
            }
        }
    }
    

    
}
