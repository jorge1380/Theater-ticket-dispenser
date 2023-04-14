/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
class TheaterState implements Serializable {
    private LocalDate date;
    private List<TheaterAreaState> theaterAreasState = new ArrayList<>();
    private String theaterImage;
    private int modifier;
    
    TheaterState(Theater t, LocalDate date)/*Constructor que inicializa las propiedades del estado del teatro*/{
        this.date = date;
        this.theaterImage = t.getImage();
        for (int i=0;i<t.getNumAreas();i++){
            this.theaterAreasState.add(new TheaterAreaState(t.getArea(i)));
        }
        this.modifier = t.getModifier();
    }
    
    public String getImage()/*Devuelve la imagen*/{
        return this.theaterImage;
    }
    
    public LocalDate getDate()/*Devuelve la fecha correspondiente al estado del teatro*/{
        return this.date;
    }
    
    public TheaterAreaState getArea(int pos)/*Devuelve el área correspondiente*/{
        return this.theaterAreasState.get(pos);
    }
    
    public int getNumAreas()/*Devuelve el número de áreas*/{
        return this.theaterAreasState.size();
    }
    
    public int getModifier(){
        return this.modifier;
    }
}
