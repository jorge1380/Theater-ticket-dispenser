/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterapp;
import java.util.Map;
import java.util.TreeMap;
/**
 *
 * @author jorge
 */
public class TranslatorManager {
    private Map<String,Translator> translator = new TreeMap();
    private String activeIdiom;
    
    TranslatorManager()/*Constructor en el cual se inicializan los traductores junto con sus respectivos idiomas y ficheros*/{ 
        this.activeIdiom = "ES";
        this.translator.put("ES", new Translator("ES.txt"));
        this.translator.put("EN", new Translator("EN.txt"));
        this.translator.put("CAT", new Translator("CAT.txt"));
        this.translator.put("EUS", new Translator("EUS.txt"));
    }
    
    public String translate(String msg)/*Llama al traductor correspondiente al idioma y le pasa el mensaje*/{ 
        String result = translator.get(this.activeIdiom).translate(msg);
        return result;
    }
    
    public void setIdiom(String idiom)/*Asigna el idioma que se le pasa al atributo activeIdiom*/{ 
        this.activeIdiom = idiom;
    }
}
