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
class ErrorScreen extends Screen{

    public ErrorScreen(String title, ScreenMode mode, DispenserManager dispenser)/*Método que inicializa las propiedades de la pantalla*/{
        super(mode, dispenser);
        super.setTitle(title);
        super.setDescription("");
    }
    
    @Override
    public ScreenResult begin(DispenserHardware dh)/*Método que inicializa las opciones de la pantalla*/{
        List<String> opts = new ArrayList<>();
        opts.add("Volver");
        opts.add("Cancelar");
        super.setOptions(opts);
        return ScreenResult.continueInScreen;
    }

    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c)/*Método que cambia de pantalla*/{
        switch (c)
        {
            case 0: {
                return ScreenResult.exitScreen;
            }
            case 'A': {
                return ScreenResult.exitScreen;
            }
            case 'B': {
                return ScreenResult.exitScreen;
            }
            default :{
                    return ScreenResult.continueInScreen;
            }
        }
    }
    
}
