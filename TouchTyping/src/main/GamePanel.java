package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Klasa opisująca obszar graficzny gry, dziedziczy po klasie JPanel i implementuje KeyListener.
 * @author Patrycja Gładkowska
 */
public class GamePanel extends JPanel implements KeyListener{
	
	/**Szerokość pola graficznego gry*/
	public int gWidth;
	/**Wysokość pola graficznego gry*/
	public int gHeight;
	/**Wysokość paska menu*/
	public int menuHeight;
	/**Maksymalna ilość liter w linii poziomej na ekranie*/
	public int lettersInLine;		
	/**Maksymalna dopuszczalna ilość pominietych liter*/
	public int maxMissed = 20;
	/**Przesunięcie pomiędzy liniami z literami*/
	public int movement;
	/**Obiekt statusu gry*/
	public Status gStatus;	
	/**Czcionka dla liczby punktów i numeru poziomu*/
	public Font pointsFont;
	/**Czcionka dla napisów w pasku Menu*/
	public Font menuFont;
	/**Czcionka dla napisu 'Pominiete'*/
	public Font missedFont;	
	/**Czcionka dla spadających liter*/
	public Font letterFont;			
	/**Czy wybrano tryb początkujący (jeśli tak to true)*/
	public boolean beginner = true;
	/**Główne litery wyświetlane w grze*/
	private Letter[] letters;		
	
	/**
	 * Kostruktor klasy - ustawienia parametrów, obsługa grafiki i kliknięcia myszy.
	 * @param width		szerokość pola graficznego
	 * @param height	wysokość pola graficznego
	 */
	public GamePanel(int width, int height)
	{
		gStatus = new Status();
		gStatus.resetGame();
		pointsFont = new Font ("Dialog", Font.BOLD,45);
		letterFont=new Font("Dialog",Font.BOLD,70);
		missedFont=new Font("Dialog",Font.BOLD,25);
		
		this.gWidth = width;
		this.gHeight = height;
		menuHeight = 100;		//wysokosc paska menu
		
		lettersInLine = 3;
		movement=gHeight/(Parameters.noOfLetters/lettersInLine);
		letters = new Letter[Parameters.noOfLetters];
		
		//rysowanie okna glownego-tryby
			setLayout(new FlowLayout(50,100,50));
			
			JLabel text = new JLabel("<html>Witaj w grze Touch Typing Hero!<br/><br/>"
					+ "Postaraj się jak najszybciej wcisnąć klawisz na klawiaturze<br/>"
					+ "odpowiadający spadającej literze. <br/>" +
					"Aby rozpocząć, wybierz tryb początkujący (podstawowy zestaw liter)<br/>"
					+ " lub zaawansowany (pełny zestaw liter). <br/> Gra kończy się z chwilą pominięcia 20 liter. <br/> Powodzenia!</html>");
			
			text.setFont(new Font ("Dialog", Font.PLAIN, 25));
			add(text);
			
			JButton buttonP = new JButton ("Początkujący");
			JButton buttonZ = new JButton ("Zaawansowany");
			buttonP.setFont(new Font("Dialog", Font.PLAIN, 45));
			buttonZ.setFont(new Font("Dialog", Font.PLAIN, 45));
			add(buttonP);
			add(buttonZ);
			
			buttonP.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent ev) {
		        	 Parameters.gameStarted=true;
		        	 Parameters.pause=false;
		        	 gStatus.resetGame();
		        	 beginner = true;
		        	 buttonP.setVisible(false);
		        	 buttonZ.setVisible(false);
		        	 text.setVisible(false);
		        	 setLayout(new GridLayout(1,1));
		        	 restartGame();
		         }
		    });
			
			buttonZ.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent ev) {
		        	 Parameters.gameStarted=true;
		        	 Parameters.pause=false;
		        	 gStatus.resetGame();
		        	 beginner = false;
		        	 buttonP.setVisible(false);
		        	 buttonZ.setVisible(false);
		        	 text.setVisible(false);
		        	 setLayout(new GridLayout(1,1));
		        	 restartGame();
		         }
		    });

		//potrzebne do wyswietlania przyciskow trybow
		this.addKeyListener(this);	
		this.setFocusable(true);
		
		/**
		 * Obsługa zdarzeń - kliknięcie przycisku myszki.
		 */
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				//czy wybrano Menu
				if(me.getX()>(400) && me.getX()<(550) && me.getY()<(menuHeight/2))  
					Parameters.pause = true;
				
				//czy wybrano Koniec gry
				if (me.getX()<180 && me.getY()<menuHeight && me.getY()>menuHeight/2)
				{
					if(Parameters.pause) {
						System.exit(1);
					}
				}
				
				//gdy skonczymy gre napis Wznow gre nie wyswietla sie
				if (Parameters.end==false)
				{
					//czy wybrano Wznow gre
					if (me.getX()>390 && me.getX()<(560) && getY()<menuHeight && me.getY()>menuHeight/2)
					{
						if(Parameters.pause)
							Parameters.pause = false;
					}
				}
				
				//czy wybrano napis Zmien tryb
				if (me.getX()>830 && me.getX()<gWidth && getY()<menuHeight && me.getY()>menuHeight/2)
				{
					if(Parameters.pause)
					{
						Parameters.gameStarted = false;
						setLayout(new FlowLayout(50,100,50));
						buttonP.setVisible(true);
			        	buttonZ.setVisible(true);
			        	text.setVisible(true);
			        	gStatus.resetGame();
			        	Parameters.pause=false;
			        	Parameters.end=false;
					}
				}
				
			}
		});
		
	}//koniec konstruktora GamePanel

	/**
	 * Restart gry - ustawienie parametrów początkowych.
	 */
	private void restartGame() {
		gStatus.resetGame();
		Parameters.pause = false;
		int offset = gWidth/(lettersInLine+1); 		
		
		int inLine=0;    
        int yLine=0;
        
        for(int i=0; i<Parameters.noOfLetters;i++){  
        	
        	if (beginner==true)
        		letters[i]=new Letter((((inLine%lettersInLine)+1)*offset)-Parameters.lettersBasic[(i%Parameters.lettersBasic.length)],0,Parameters.lettersBasic);
        	else
        		letters[i]=new Letter((((inLine%lettersInLine)+1)*offset)-Parameters.lettersFullSet[(i%Parameters.lettersFullSet.length)],0,Parameters.lettersFullSet);
        
        	letters[i].setScreenSize(gWidth, gHeight);
                
            if(inLine>=lettersInLine){
                  yLine++;
                  inLine%=lettersInLine;
             }
             inLine++;
             letters[i].setYPosition(yLine*movement*-1);
   
        }
		
	}

	/**
	 * Metoda rysująca panel gry.
	 * @param g2
	 */
	@Override
	protected void paintComponent(Graphics g2)
	{
		if (Parameters.gameStarted==true)
		{
			Graphics2D g = (Graphics2D) g2;
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(letterFont);
			
			//jak skonczymy gre lub gdy jest pauza to nie rysujemy liter
			if(Parameters.end==false && Parameters.pause==false)		
			{
				//rysowanie spadajacych liter
				for (int i=0; i<letters.length; i++)
				{
					letters[i].fallingLetter();		//zwiekszamy currY
					//zwiekszamy licznik pominietych liter gdy przekrocza dolny pasek
					if(letters[i].click==false && letters[i].missed==false && letters[i].currY>=(gHeight-90))
					{
						letters[i].missed=true;
						gStatus.incMissed();
					}
					
					if(letters[i].missed==true && letters[i].currY==0)
						letters[i].missed=false;
					if(letters[i].click == true && letters[i].currY == 0)		
						letters[i].click = false;
					if(letters[i].click==false)
						g.drawString(String.valueOf(letters[i].charLetter), letters[i].currX, letters[i].currY);
				}
			}
			
			
			//rysowanie paska menu
					g.setColor(Color.lightGray);
					g.fillRect(0, 0, gWidth, menuHeight);
					g.setColor(Color.BLACK);
					g.drawImage(Parameters.loadImages("menu"), gWidth/2-80, 0, null);
					g.setFont(pointsFont);
			
			//rysowanie paska dolnego
					if (Status.points >= 0 && Status.points < Parameters.secondLevel)	
						g.drawImage(Parameters.loadImages("grass"),0,gHeight-100,null);
					
					if (Status.points >= Parameters.secondLevel && Status.points < Parameters.thirdLevel)
						g.drawImage(Parameters.loadImages("waves"),0,gHeight-100,null);
							
					if (Status.points >= Parameters.thirdLevel)
						g.drawImage(Parameters.loadImages("fire"),0,gHeight-100,null);
				
			//koniec gry jesli przekroczymy max dopuszczalnych pominietych liter
			if(Status.missed>=maxMissed)
				Parameters.end = true;
			
			//rysowanie po kliknieciu w menu
			if(Parameters.pause==true)
			{
				menuFont = new Font ("Dialog", Font.BOLD,25);
				g.setFont(menuFont);
				g.setColor(Color.white);
				g.drawString("KONIEC GRY",10, menuHeight-20);
				g.setColor(Color.white);
				g.drawString("ZMIEŃ TRYB",840, menuHeight-20);
				if (Parameters.end==false)
					g.drawString("WZNÓW GRĘ",400, menuHeight-20);
			}
			//rysowanie w ciagu gry
			else
			{
				g.drawImage(Parameters.loadImages("poziom"), gWidth-1000, 0, null);
				g.drawString(""+Status.level, 230, 62);
				g.drawImage(Parameters.loadImages("punkty"), gWidth-300, 0, null);
	            g.drawString(""+Status.points,920, 62);
	            g.setColor(Color.RED);
	            g.setFont(missedFont);
	            g.drawString("Pominięte:" + Status.missed, gWidth-290, 85);
	            if (beginner==true)
	            	g.drawString("Tryb początkujący", gWidth-990, 85);
	            else
	            	g.drawString("Tryb zaawansowany", gWidth-990, 85);
			}
			
			//koniec gry
			if(Parameters.end){ 	
				g.setColor(Color.BLACK);
				g.setFont(pointsFont);
				g.drawString("KONIEC GRY!",gWidth/2-200, gHeight/2-62);
				g.drawImage(Parameters.loadImages("punkty"), gWidth/2-200, gHeight/2-62, null);
				g.drawString(""+Status.points,gWidth/2, gHeight/2);
				
			}
		}
		//jesli nie gramy, to widzimy okno glowne, czyli tryby
		else 
		{
			Parameters.gameStarted=false;
		}
		
	}//koniec paintComponent

	/**
	 * Obsługa zdarzeń - wciśnięcie klawisza na klawiaturze.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(Parameters.end==false && Parameters.pause==false)
		{
			char c = e.getKeyChar();
			int maxcurrY=0; 
			int index=-1;
			for (int i=0; i<letters.length; i++) 	
			{
				if (c == letters[i].charLetter){
					if(letters[i].click==false){
						if(letters[i].currY>=maxcurrY)
						{
							maxcurrY = letters[i].currY;
							index=i;		//indeks litery z maksymalna wartoscia y
						}					
					}		
				}
			}
			//dopiero gdy wybierzemy litere z maksymalna wartoscia y to wtedy robimy obsluge znikniecia litery
			if (index>=0 && letters[index].currY<(gHeight-90))
			{
				letters[index].setClick();
				gStatus.incPoints();
				//losowanie nowej litery na miejsce starej w zaleznosci od trybu
				if (beginner==true)
					letters[index].charLetter = Parameters.lettersBasic[(int) ((Math.random()*100)%Parameters.lettersBasic.length)];
				else
					letters[index].charLetter = Parameters.lettersFullSet[(int) ((Math.random()*100)%Parameters.lettersFullSet.length)];
				
				if (Status.points == Parameters.secondLevel || Status.points == Parameters.thirdLevel) 
					gStatus.nextLevel();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
