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
public abstract class Screen {
    private DispenserManager dispenseManager;
    private String title;
    private String description;
    private String image;
    private List<String> options;
    public ScreenMode mode;
          
    Screen(ScreenMode mode, DispenserManager dispenser)/*Constructor por defecto de todas las pantallas*/{
        this.mode = mode;
        this.dispenseManager = dispenser;
        this.options = new ArrayList();
                
    }
    
    public List<String> getOptions()/*Devuelve la lista de las opciones de la pantalla*/{
        return this.options;
    }
    
    public String getTitle()/*Devuelve el título de la pantalla*/{
        return this.title;
    }
    
    public String getDescription()/*Devuelve la descripción de la pantalla*/{
        return this.description;
    }
    
    public String getImage()/*Devuelve la imagen de la pantalla*/{
        return this.image;
    }
    
    public ScreenMode getScreenMode()/*Devuelve el modo de la pantalla*/{
        return this.mode;
    }
    
    public TheaterAreaState getAreaState()/*Devuelve el área del teatro, por defecto una vacía*/{
        TheaterAreaState a = new TheaterAreaState(new TheaterArea("",0,""));
        return a;
    }
    
    public abstract ScreenResult optionButtonPressed(DispenserHardware d, char c);/*Método que hace lo que corresponda al botón pulsado*/
    
    public ScreenResult seatButtonPressed(DispenserManager d, byte row, byte col)/*Método que marca el asiento presionado*/{
        return ScreenResult.continueInScreen;
    };
    
    public ScreenResult crediCardDetected(DispenserHardware d)/*Método que realiza el pago al detectar la tarjeta*/{
        return ScreenResult.continueInScreen;
    }
    
    public void setDispenserManager(DispenserManager dispenser)/*Método que asigna un objeto DispenserManager*/{
        this.dispenseManager = dispenser;
    }
    
    public ScreenResult begin(DispenserHardware d)/*Método que inicializa lo que necesite la pantalla*/{
        return ScreenResult.continueInScreen;
    }
    
    public void setDescription(String desc)/*Método que asigna una descripción*/{
        this.description = desc;
    }
    
    public void setImage(String img)/*Método que asigna una imagen*/{
        this.image = img;
    }
    
    public void setOptions(List opt)/*Método que asigna una lista con las opciones*/{
        this.options = opt;
    }
    
    public void setTitle(String title)/*Método que asigna un título*/{
        this.title = title;
    }
    
    public DispenserManager getDispenser()/*Método que devuelve el objeto de la clase DispenserManager*/{
        return this.dispenseManager;
    }
}
