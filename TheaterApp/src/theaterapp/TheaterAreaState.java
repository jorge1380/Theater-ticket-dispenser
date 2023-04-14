/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.Serializable;

/**
 *
 * @author jorge
 */
class TheaterAreaState implements Serializable{
    private String name;
    private int cols;
    private int rows;
    private int price;
    private String file;
    private SeatState [][] seatsState;
    
    TheaterAreaState(TheaterArea area)/*Configura las propiedades del estado del área a partir del área que recibe*/{
        this.name = area.getName();
        this.seatsState = new SeatState[area.getRows()][area.getCols()];
        for (int i=0;i<area.getRows();i++)
            for (int a=0;a<area.getCols();a++)
                this.seatsState[i][a] = area.getSeat(i,a);
        this.cols = area.getCols();
        this.rows = area.getRows();     
        this.price = area.getPrice();
        this.file = area.getFile();
    }
    
    public int getRows()/*Devuelve las filas del estado del área*/{
        return this.rows;
    }
    
    public int getPrice()/*Devuelve el precio del estado del área*/{
        return this.price;
    }
    
    public int getCols()/*Devuelve las columnas del estado del área*/{
        return this.cols;
    }
    
    public String getName()/*Devuelve el nombre del estado del área*/{
        return this.name;
    }
    
    public SeatState getSeat(int rows, int col)/*Devuelve el asiento correspondiente a la fila y la columna que recibe como parámetro*/{
        if (seatsState[rows][col] != null)
            return seatsState[rows][col];
        return null;
    }
    
    public String getFile()/*Devuelve la fila del estado del área*/{
        return this.file;
    }

    public void setOcuppiedSeat(int row, int col)/*Marca el asiento correspondiente a la fila y la columna que recibe como parámetro como ocupado*/{
        this.seatsState[row][col] = SeatState.ocuppied;

    }

    public void setFreeSeat(int row, int col)/*Marca el asiento correspondiente a la fila y la columna que recibe como parámetro como libre*/{
        this.seatsState[row][col] = SeatState.free;
    }
}
