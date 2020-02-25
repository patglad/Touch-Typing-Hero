package main;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/**
 * Klasa przechowująca parametry wykorzystywane w grze.
 * @author Patrycja Gładkowska
 *
 */
public class Parameters 
{
	/**Tablice liter jako znaki - pełny zestaw*/
	public static char[] lettersFullSet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	/**Tablice liter jako znaki - podstawowy zestaw*/
	public static char[] lettersBasic = {'a','s','d','f','j','k','l'};
    /**Czy jest pauza w grze*/
	public static boolean pause=false;
	/**Czy gra się zakończyła*/
    public static boolean end=false;
    /**Czy gra została rozpoczęta*/
    public static boolean gameStarted=false; 
    /**Maksymalna ilość spadających liter na ekranie*/
    public static int noOfLetters=12; 
    /**Szerokość pola graficznego*/
    public static int gWidth=1024;
    /**Wysokość pola graficznego*/
    public static int gHeight=768;
    /**Ilość punktów, po przekroczeniu której rozpoczynamy drugi poziom*/
    public static int secondLevel = 30;		
    /**Ilość punktów, po przekroczeniu której rozpoczynamy trzeci poziom*/
    public static int thirdLevel = 50;
	
    /**
     * Metoda ładująca obrazy na podstawie ścieżki dostępu.
     * @param fileName	nazwa pliku tyu String
     * @return			obraz
     */
    public static Image loadImages(String fileName)
    {
    	BufferedImage img = null;
    	try {
			img = ImageIO.read(new File("res/" + fileName + ".png"));	
		} catch (IOException e) {}
    	return img;
    }

	
}
