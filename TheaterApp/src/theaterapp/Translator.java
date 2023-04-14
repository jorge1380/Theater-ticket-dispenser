/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author jorge
 */
class Translator {
    private Map<String,String> messages;
    
    Translator(String translatorFile)/*Constructor que inicializa el mapa de los mensajes y llama al método read*/{
        this.messages = new TreeMap();
        this.read(translatorFile);
    }
    
    public String translate(String msg)/*Método que se encarga de devolver la traducción correspondiente al mensaje que recibe por parámetro*/{ 
        return messages.get(msg);
    }
    
    private void read(String fileName)/*Método desde el cual se lee el fichero que se le pasa y configura el mapa para traducir los mensajes*/{ 
        File f = new File("ConfigFilesExample/".concat(fileName));
		Scanner s;
		try {
			s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				Scanner sl = new Scanner(line);
				sl.useDelimiter(":");
				messages.put(sl.next(),sl.next());
			}
                        messages.put("","");
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
}
