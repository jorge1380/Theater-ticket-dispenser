/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
class Play {
    private String title;
    private String image;
    private String description;
    
    Play()/*Constructor que llama al método read para configurar las propiedades de la obra*/{
        this.read();
    }
    
    public String getTitle()/*Método que devuelve el título de la obra*/{
        return this.title;
    }
    
    public String getDescription()/*Método que devuelve la descripción de la obra*/{
        return this.description;
    }
    
    public String getImage()/*Método que devuelve la imagen de la obra*/{
        return this.image;
    }
    
    private void read()/*Método que lee el fichero de la obra y configura las propiedades del objeto*/{
        StringBuilder s = new StringBuilder();
        try {
            FileReader reader = new FileReader("ConfigFilesExample/play.txt");
            int c = reader.read();
            
            while (c!=-1){
                c = reader.read();
                s.append((char) c);
            }
            Scanner scanner = new Scanner(s.toString());
            scanner.useDelimiter(":");
            scanner.next();
            scanner.useDelimiter("\r");
            this.title = scanner.next().toString().substring(1);
            scanner.useDelimiter(":");
            scanner.next();
            scanner.useDelimiter("\r");
            this.image = "ConfigFilesExample/".concat(scanner.next().toString().substring(1));
            scanner.useDelimiter(":");
            scanner.next();
            scanner.useDelimiter("\r");
            this.description = scanner.next().toString().substring(1);
        }
        catch (IOException e){
            System.out.println("No se ha encontrado el fichero");
        }

    }
}
