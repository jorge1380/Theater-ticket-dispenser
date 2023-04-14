/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

/**
 *
 * @author jorge
 */
class TheaterManager {
        
    public void run()/*Método que ejecuta el programa creando un objeto DispenserManager, uno Theater y otro WelcomeScreen que corresponde a la primera pantalla en mostrarse*/{
        DispenserManager dm = new DispenserManager();
        Theater t = new Theater();
        WelcomeScreen WScreen = new WelcomeScreen(t,"Bienvenido al teatro Alfil",ScreenMode.optionsMode,dm);
        dm.showScreen(30,WScreen);
    }
}
