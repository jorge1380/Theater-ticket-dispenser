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
class IdiomSelectionScreen extends Screen {
    
    public IdiomSelectionScreen(String title, ScreenMode mode, DispenserManager dispenser)/*Constructor en el cual se inicializan las propiedades de la pantalla*/ { 
        super(mode, dispenser);
        super.setTitle(title);
        super.setDescription("");
        super.setImage("");
    }
    
        @Override
    public ScreenResult begin(DispenserHardware d)/*Método en el cual se inicializan las opciones de la pantalla*/{ 
        
        List<String> opt = new ArrayList<>();
        opt.add("Castellano");
        opt.add("Inglés");
        opt.add("Catalán");
        opt.add("Euskera");
        opt.add("");
        opt.add("Cancelar");

        this.setOptions(opt);
        return ScreenResult.continueInScreen;
    }

    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c)/*Método en el cual se llama al método setIdiom de la clase Dispensermanager con el idioma correspondiente*/ { 
        switch (c)
        {
            case 0: {
                return ScreenResult.exitScreen;
            }
            case 'A': {
                super.getDispenser().setIdiom("ES");
                return ScreenResult.exitScreen;
            }
            case 'B': {
                super.getDispenser().setIdiom("EN");
                return ScreenResult.exitScreen;
            }
            case 'C': {
                super.getDispenser().setIdiom("CAT");
                return ScreenResult.exitScreen;
            }
            case 'D': {
                super.getDispenser().setIdiom("EUS");
                return ScreenResult.exitScreen;
            }
            case 'E': {
                return ScreenResult.continueInScreen;
            }
            case 'F': {
                return ScreenResult.exitScreen;
            }
            default :
                return ScreenResult.continueInScreen;
        }
    }
    
}
