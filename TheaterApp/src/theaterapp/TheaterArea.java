/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author jorge
 */
class TheaterArea {
    private int rows;
    private int cols;
    private String name;
    private SeatState [][] seats;
    private int price;
    private String file;
    
    TheaterArea(String name, int price, String fileName)/*Constructor que configura las propiedades del objeto y llama al método read*/{ /*Se crea el área del teatro a partir de la información que se lee en el fichero*/
        this.file = fileName;
        this.read(fileName);
        this.name = name;
        this.price = price;
        
    }
    
    public int getRows()/*Devuelve el número de filas del área*/{
        return this.rows;
    }
    
    public int getCols()/*Devuelve el número de columnas del área*/{
        return this.cols;
    }
    
    public String getName()/*Devuelve el número del área*/{
        return this.name;
    }
    
    public SeatState getSeat(int rows, int col)/*Devuelve el asiento en la fila y columna que recibe como parámetro*/{
        return seats[rows][col];
    }
    
    private void read(String fileName)/*Lee el fichero que recibe por parámetro y configura las propiedades correspondientes*/{ /*Lee el fichero*/
        
        try {
            FileReader f = new FileReader("ConfigFilesExample/".concat(fileName));
            FileReader f2 = new FileReader("ConfigFilesExample/".concat(fileName));
            
            BufferedReader buffer = new BufferedReader(f);
            String lines;
            int rowsCounter = 0;
            while ((lines = buffer.readLine()) != null){
                rowsCounter++;
                int c = lines.length();
                if (this.cols < c)
                    this.cols = c;
                
            }


            
            
            this.rows = rowsCounter;
            this.seats = new SeatState[this.rows][this.cols];
            
            BufferedReader buffer2 = new BufferedReader(f2);
            int i=0;
            while ((lines = buffer2.readLine()) != null){
                    for (int a=0;a<lines.length();a++){
                        if (lines.charAt(a) == '*')
                            this.seats[i][a] = SeatState.free;
                        else if (lines.charAt(a) == '-')
                            this.seats[i][a] = SeatState.ocuppied;
                    }
                i++;
            }    
        }
        catch (IOException e){
            System.out.println("No se ha encontrado el fichero");
        }
    }
    
    public int getPrice()/*Devuelve el precio del área*/{
        return this.price;
    }
    
    public String getFile()/*Devuelve el fichero del área*/{
        return this.file;
    }
}
