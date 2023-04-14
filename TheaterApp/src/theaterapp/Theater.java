/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
public class Theater {
    private List<TheaterArea> theaterAreas = new ArrayList<>();
    private String theaterImage;
    private String name;
    private int modifier;
    
    Theater()/*Constructor que llama al método read para que lea el fichero que contiene la información del teatro*/{
        this.read("theater.txt");
    }
    
    public String getImage()/*Método que devuelve la imagen del teatro*/{
        return this.theaterImage;
    }
    
    public int getNumAreas()/*Método que devuelve el número de áreas del teatro*/{
        return this.theaterAreas.size();
    }
    
    public TheaterArea getArea(int pos)/*Devuelve el área del teatro correspondiente a la posición que recibe como parámetro*/{
        return this.theaterAreas.get(pos);
    }
    
    public int getModifier(){
        return this.modifier;
    }
    
    private void read(String theatreDir)/*Método que lee el fichero que recibe como parámetro y configura las propiedades del objeto*/{        
        File f = new File("ConfigFilesExample/".concat(theatreDir));
        try {
            Scanner s;
            s = new Scanner(f);
            String line = s.nextLine();
            Scanner s1 = new Scanner(line);
            s1.useDelimiter(":");
            s1.next();
            this.name = s1.next();
            line = s.nextLine();
            Scanner s2 = new Scanner(line);
            s2.useDelimiter(":");
            s2.next();
            this.theaterImage = "ConfigFilesExample/".concat(s2.next());
            line = s.nextLine();
            Scanner s4 = new Scanner(line);
            s4.useDelimiter(":");
            s4.next();
            this.modifier = Integer.valueOf(s4.next().substring(0));
            
            while (s.hasNextLine()) {
                line = s.nextLine();
                Scanner s3 = new Scanner(line);
                s3.useDelimiter(":");
                s3.next();
                s3.useDelimiter(";");
                String nombre = s3.next().substring(1);
                s3.useDelimiter("€;");
                int price = Integer.valueOf(s3.next().substring(1));
                String file = s3.next();
                theaterAreas.add(new TheaterArea(nombre,price,file));
            }
            s.close();
        }
        catch (IOException e){
            System.out.println("No se ha encontrado el fichero");
        }
    }
}
