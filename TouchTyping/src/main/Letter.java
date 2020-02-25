package main;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * Klasa opisująca spadające litery.
 * @author Patrycja Gładkowska
 *
 */
public class Letter {
	
	/**Pojedyncza litera jako znak*/
	public char charLetter;
	/**Czy kliknięto literę*/
	public boolean click;
	/**Czy pominięto literę*/
	public boolean missed;	
	/**Początkowa współrzędna x litery*/
	public int x;	
	/**Początkowa współrzędna y litery*/
	public int y;
	/**Aktualne współrzędna x litery*/
	public int currX;
	/**Aktualne współrzędna y litery*/
	public int currY;
	/**Wartość przesunięcia litery z góry do dołu*/
	public int goDown; 	
	/**Szerokość pola graficznego*/
    public int gWidth;	
    /**Wysokość pola graficznego*/
    public int gHeight;
    
	/**
	 * Konstruktor klasy - ustawienie parametrów litery, wylosowanie litery z tablicy znaków.
	 * @param x			początkowa współrzędna x litery
	 * @param y			początkowa współrzędna y litery
	 * @param letters	tablica liter - znaków
	 */
    public Letter(int x, int y, char[] letters )
    {
    	this.x = x;
    	this.y = y;
    	currX = x;
    	currY = y;
    	this.goDown = 2;
    	gWidth = 1024;
    	gHeight = 768;
    	click = false;
    	missed = false;
    	
    	//losujemy litery z tablicy znakow
    	charLetter = letters[(int) ((Math.random()*100)%letters.length)];
    	    	
    }
    
    /**
     * Metoda kliknięcia litery - ustawia wartość true.
     */
	public void setClick()
	{
		if(!click)
		{
			click=true;
		}
	}
	
	/**
	 * Metoda ustawiająca wymiary pola graficznego.
	 * @param gWidth	szerokość
	 * @param gHeight	wysokość
	 */
	public void setScreenSize(int gWidth, int gHeight)
	{
		this.gWidth = gWidth;
		this.gHeight = gHeight;
	}
	
	/**
	 * Metoda ustawiająca aktualne położenie y litery.
	 * @param cY	aktualne położenie y litery
	 */
	public void setYPosition(int cY)
	{
		currY = cY;
	}
	
	/**
	 * Metoda obliczania położenia litery - spadania, zwiększenie prędkości spadania liter, 
	 * poprzez zmianę wartości goDown.
	 */
	public void fallingLetter()
	{
		//zmiana predkosci spadania liter, jako zwiekszanie wartosci y (przesuniecie goDown)
		if (Status.points >= 0 && Status.points < Parameters.secondLevel)
			goDown = 1;
		if (Status.points >= Parameters.secondLevel && Status.points < Parameters.thirdLevel)
			goDown = 2;
		if (Status.points >= Parameters.thirdLevel)
			goDown = 3;
		
		currY = currY+goDown;
		
		if(currY > gHeight)
			currY = 0;
	}

}
