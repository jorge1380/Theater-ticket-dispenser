/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
class SeatSelectionScreen extends Screen{
    private TheaterAreaState selectArea;
    private int seatsLimit;
    private PaymentScreen paymentScreen;
    private int totalPrice = 0;
    private LocalDate date;
    private SeatState [][] selectedSeats;

    public SeatSelectionScreen(String title, ScreenMode mode, DispenserManager dispenser, TheaterAreaState t, LocalDate date)/*Constructor que inicializa las propiedades de la pantalla*/ {
        super(mode, dispenser);
        super.setTitle(title);
        this.selectArea = t;
        this.seatsLimit = 0;
        this.date = date;
        this.selectedSeats = new SeatState [t.getRows()][t.getCols()];
        for (int i=0;i<t.getRows();i++){
            for (int a=0;a<t.getCols();a++)
                this.selectedSeats[i][a] = SeatState.free;
        }
    }
    
    @Override
    public ScreenResult begin(DispenserHardware dh)/*Asigna las opciones de la pantalla*/{
        List<String> opts = new ArrayList<>();
        opts.add("Cancelar");
        opts.add("Continuar");
        super.setOptions(opts);
        return ScreenResult.continueInScreen;
    }

    @Override
    public ScreenResult optionButtonPressed(DispenserHardware d, char c)/*Muestra la pantalla correspondiente al botón pulsado y marca el asiento pulsado*/ {
        
        switch (c)
        {
            case 0: {
                d.clearTicket();
                return ScreenResult.exitScreen;
            }
            case 'A': {
                d.clearTicket();
                for (int i=0;i<this.selectArea.getRows();i++){
                    for (int a=0;a<this.selectArea.getCols();a++){
                        if (this.selectedSeats[i][a] == SeatState.ocuppied)
                                this.seatButtonPressed(super.getDispenser(), (byte) (i+1), (byte) (a+1));                                
                    }
                }
                return ScreenResult.exitScreen;
            }
            case 'B': {
                int pos = 0;
                for (int i=0;i<this.selectArea.getRows();i++){
                    for (int a=0;a<this.selectArea.getCols();a++)
                       if (this.selectedSeats[i][a] == SeatState.ocuppied){
                            d.addInfoTicketByPos("Fila: ".concat(Integer.toString(i+1)),pos);
                            d.addInfoTicketByPos("Asiento: ".concat(Integer.toString(a+1)),pos);
                            pos++;
                       }
                }
                this.paymentScreen = new PaymentScreen("Introduzca su tarjeta",ScreenMode.messageMode,super.getDispenser(),this.totalPrice,this.seatsLimit);
                super.getDispenser().showScreen(30,this.paymentScreen);
                return ScreenResult.exitScreen;
            }
            case '1': {
                return ScreenResult.continueInScreen;
            }
            default :{
                byte cols = (byte)(c & 0xFF);
                byte rows = (byte)((c & 0xFF00) >> 8);
                
                if (this.selectedSeats[rows-1][cols-1] == SeatState.ocuppied && this.selectArea.getSeat(rows-1,cols-1) == SeatState.ocuppied){
                    this.computePrice(d,false);
                    
                    this.seatButtonPressed(super.getDispenser(), rows, cols);
                    this.selectedSeats[rows-1][cols-1] = SeatState.free;
                }
                else if (this.selectedSeats[rows-1][cols-1] == SeatState.free && this.selectArea.getSeat(rows-1,cols-1) == SeatState.free && this.seatsLimit!=4){
                    this.computePrice(d,true);
                    
                    this.seatButtonPressed(super.getDispenser(), rows, cols);
                    this.selectedSeats[rows-1][cols-1] = SeatState.ocuppied;
                }
                    return ScreenResult.continueInScreen;
            }
        }        
    }
    
    @Override
   public ScreenResult seatButtonPressed(DispenserManager d, byte row, byte col)/*Llama al método markSeat del DispenserManager para marcar el asiento y llama al método updateState*/{       
        this.updateState(row,col);
        d.markSeat(row,col,1);
        super.setTitle("Fila " + row + " Asiento " + col);
        return ScreenResult.continueInScreen;
    };
    
    @Override
    public TheaterAreaState getAreaState()/*Devuelve el area seleccionada*/{
        return this.selectArea;
    }
    
    private void updateState(int row,int col)/*Actualiza los asientos del área del teatro correspondiente*/{
        if (this.selectArea.getSeat(row-1, col-1) == SeatState.free)    
            this.selectArea.setOcuppiedSeat(row-1,col-1);
        else if (this.selectArea.getSeat(row-1, col-1) == SeatState.ocuppied)    
            this.selectArea.setFreeSeat(row-1,col-1);

    }
    
    private void computePrice(DispenserHardware dh, boolean bool)/*Calcula el precio total*/{
        if (bool == true){
            if (this.date.getDayOfWeek() == DayOfWeek.FRIDAY || this.date.getDayOfWeek() == DayOfWeek.SATURDAY || this.date.getDayOfWeek() == DayOfWeek.SUNDAY)
                this.totalPrice += this.selectArea.getPrice()*dh.getModifier();
            else
                this.totalPrice += this.selectArea.getPrice();
            this.seatsLimit++;
        }
        else if (bool == false){
            if (this.date.getDayOfWeek() == DayOfWeek.FRIDAY || this.date.getDayOfWeek() == DayOfWeek.SATURDAY || this.date.getDayOfWeek() == DayOfWeek.SUNDAY)
                this.totalPrice -= this.selectArea.getPrice()*dh.getModifier();
            else
                this.totalPrice -= this.selectArea.getPrice();
            this.seatsLimit--;
        }
    }
    
}
