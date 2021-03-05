/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

//import com.sun.glass.events.MouseEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import static whot.Game.HEIGHT;
import static whot.Game.WIDTH;

/**
 *
 * @author Emmanuel Ozioma
 */
public class PlayState extends GameState{

    private Market market;
    private GameDeck gameDeck;
    private Player player;
    
    // players currently playing
    private List<Player> currentPlayerList;
    
    private int currentSelection;
    private boolean gameOver;
    //private String id;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        gameDeck = new GameDeck();
        player = null;
        market = new Market();
        
        currentPlayerList = new CopyOnWriteArrayList<Player>();
        
        currentSelection = 0;
        gameOver = false;
        
        
        loadMarket();
        shareCards();
    }

    public GameDeck getGameDeck()
    {
        return gameDeck;
    }
    
    public Market getMarketClass()
    {
        return market;
    }
    
    public int getCurrentSelection()
    {
        return currentSelection;
    }
    
    public void loadMarket()
    {
        File file = new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/cards.txt");
        
        Scanner scanner = null;
        try{
            scanner = new Scanner(file);
        }catch(FileNotFoundException e)
        {
            System.out.println("Sorry, File not found!");
        }
        // if the file is correct or is found
        if(scanner != null)
        {
            
            String shape = "";
            
            while(scanner.hasNext())
            {
                int number = 0;
                String lineOfText = scanner.nextLine().trim();
                
                if(lineOfText.equals("Circle") || lineOfText.equals("Triangle") || lineOfText.equals("Cross") || lineOfText.equals("Square") || lineOfText.equals("Star"))
                {
                    shape = lineOfText;
                    System.out.println("Line: " + lineOfText);
                }else{
                    // scan each line for the numbers of the cards
                    Scanner reader = new Scanner(lineOfText);
                    number = reader.nextInt();
                    System.out.println("Number: " + number);
                }
                
                
                //create a card object passing the shape and number read by the scanner as parameters
                String imageFilePath = getImageFilePath(shape, number);
                
                //add the card to the stack (market)
                if(number != 0)
                {
                    Card card = new Card(shape, number, imageFilePath);
                    market.addCard(card);
                }
            }
            Stack<Card> m = market.getMarket();
            System.out.println(m);
            System.out.println(m.size());
            shuffle();
            System.out.println(m.size());
        }
    }
    
    // return the file path of the image of a card...this method will be used in loadMarket()
    public String getImageFilePath(String shape, int number)
    {
        String imageFilePath = "";
        if(shape.equals("Circle") && number == 1)
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c1.png";
                }else if(shape.equals("Circle") && number == 2)
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c2.png";
                }else if(shape.equals("Circle") && number == 3)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c3.png";
                }else if(shape.equals("Circle") && number == 4)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c4.png";
                }else if(shape.equals("Circle") && number == 5)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c5.png";
                }else if(shape.equals("Circle") && number == 6)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c6.png";
                }else if(shape.equals("Circle") && number == 7)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c7.png";
                }else if(shape.equals("Circle") && number == 8)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c8.png";
                }else if(shape.equals("Circle") && number == 10)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c10.png";
                }else if(shape.equals("Circle") && number == 11)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c11.png";
                }else if(shape.equals("Circle") && number == 12)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c12.png";
                }else if(shape.equals("Circle") && number == 13)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c13.png";
                }else if(shape.equals("Circle") && number == 14)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/c14.png";
                }else if(shape.equals("Triangle") && number == 1)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t1.png";
                }else if(shape.equals("Triangle") && number == 2)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t2.png";
                }else if(shape.equals("Triangle") && number == 3)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t3.png";
                }else if(shape.equals("Triangle") && number == 4)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t4.png";
                }else if(shape.equals("Triangle") && number == 5)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t5.png";
                }else if(shape.equals("Triangle") && number == 7)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t7.png";
                }else if(shape.equals("Triangle") && number == 8)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t8.png";
                }else if(shape.equals("Triangle") && number == 10)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t10.png";
                }else if(shape.equals("Triangle") && number == 11)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t11.png";
                }else if(shape.equals("Triangle") && number == 12)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t12.png";
                }else if(shape.equals("Triangle") && number == 13)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t13.png";
                }else if(shape.equals("Triangle") && number == 14)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/t14.png";
                }else if(shape.equals("Cross") && number == 1)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/cr1.png";
                }else if(shape.equals("Cross") && number == 2)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/cr2.png";
                }else if(shape.equals("Cross") && number == 3)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/cr3.png";
                }else if(shape.equals("Cross") && number == 5)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/cr5.png";
                }else if(shape.equals("Cross") && number == 7)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/cr7.png";
                }else if(shape.equals("Cross") && number == 10)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/cr10.png";
                }else if(shape.equals("Cross") && number == 11)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/cr11.png";
                }else if(shape.equals("Cross") && number == 13)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/cr13.png";
                }else if(shape.equals("Cross") && number == 14)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/cr14.png";
                }else if(shape.equals("Square") && number == 1)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/sq1.png";
                }else if(shape.equals("Square") && number == 2)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/sq2.png";
                }else if(shape.equals("Square") && number == 3)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/sq3.png";
                }else if(shape.equals("Square") && number == 5)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/sq5.png";
                }else if(shape.equals("Square") && number == 7)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/sq7.png";
                }else if(shape.equals("Square") && number == 10)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/sq10.png";
                }else if(shape.equals("Square") && number == 11)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/sq11.png";
                }else if(shape.equals("Square") && number == 13)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/sq13.png";
                }else if(shape.equals("Square") && number == 14)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/sq14.png";
                }else if(shape.equals("Star") && number == 1)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/st1.png";
                }else if(shape.equals("Star") && number == 2)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/st2.png";
                }else if(shape.equals("Star") && number == 3)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/st3.png";
                }else if(shape.equals("Star") && number == 4)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/st4.png";
                }else if(shape.equals("Star") && number == 5)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/st5.png";
                }else if(shape.equals("Star") && number == 7)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/st7.png";
                }else if(shape.equals("Star") && number == 8)   
                {
                    imageFilePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/assets/st8.png";
                }
        return imageFilePath;
    }
    
    public void showMarket()
    {
        System.out.println(market.getMarket());
    }
    
    // shuffle the market
    public void shuffle()
    {
        market.shuffle();
        showMarket();
    }
    
    // add a player
    public void addPlayer(Player player)
    {
        currentPlayerList.add(player);
    }
    
    // display the details of the players currently playing
    public void showPlayers()
    {
        System.out.println("ALL PLAYERS PLAYING");
        for(Player player : currentPlayerList)
        {
            System.out.println(player);
        }
    }
    
    public void createPlayers()
    {
        currentPlayerList.add(new Human("Emmanuel"));
        currentPlayerList.add(new AI("Bot1"));
    }
    
    // share cards at the beginning of the game
    public void shareCards()
    {
        //currentPlayerList.add(player);
        createPlayers();
        //shuffle the market
        market.shuffle();
        String input = JOptionPane.showInputDialog("How Many Cards?");
        
        int noOfCards = Integer.parseInt(input);
        for(int i = 0; i < noOfCards; i++)
        {
            if(currentPlayerList != null)
            {
                for(Player player : currentPlayerList)
                {
                    // remove the card at the top of the market and add it to the players deck
                    player.getDeck().add(market.getMarket().pop());
                }
            }else{
                System.out.println("current playerlist is null");
            }
        }
        // add the first card to the deck
        gameDeck.addCard(market.getMarket().pop());
        showMarket();
        
    }
    
    private void delay(int nanoSec)
    {
        try{
            Thread.sleep(nanoSec);
        }catch(Exception e){}
    }
    
    public void checkLastCard()
    {
        String message = player.getName() + " has last card!";
        String title = "Info";
        if(player.getDeck().size() == 1)
        {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void checkGameOver()
    {
        String message = player.getName() + " has won the game!";
        String title = "Info";
        if(player.getDeck().isEmpty())
        {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
            gameOver = true;
        }
        
        if(gameOver)
        {
            calculateCardsLeft();
            //currentSelection = -1; //maake sure no player can play with the help of the if statement in updte()
            displayOptionMenu();
        }
    }
    
    public void calculateCardsLeft()
    {
        String message = "";
        String title = "Results";
        for(Player p : currentPlayerList)
        {
            message += "\n" + p.getName() + " ";
            int number = 0;
            for(int i = 0; i < p.getDeck().size(); i++)
            {
                Card c = p.getDeck().get(i);
                number += c.getNumber();
                System.out.println("Name: " + p.getName() + " Number" + number);
            }
            message += "\n" + number + "\n";
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void compareCards()
    {
        Card cardSelected = player.getCardSelected();
        System.out.println(cardSelected);
        if(cardSelected.getShape().equals(gameDeck.getDeck().peek().getShape()) || cardSelected.getNumber() == gameDeck.getDeck().peek().getNumber())
        {
            
            gameDeck.addCard(cardSelected);
            player.removeCardFromDeck(cardSelected);
            
            // last card or win
            checkLastCard();
            //checkGameOver();
            //set the turn to the next player
            managePlayerTurns();
            
        }else{
            player.playCardErrorSound();
        }
    }
    
    public void goToMarket(Player p)
    {
        if(market.getMarket().isEmpty())
        {
            Stack<Card> deck = gameDeck.getDeck();
            for(int i = 0; i <  deck.size() - 1; i++)
            {
                market.getMarket().push(deck.get(i));
                deck.remove(deck.get(i));
            }
            market.shuffle();
        }
        p.goToMarket(market.getMarket());
        managePlayerTurns();
    }
    
    public void managePlayerTurns()
    {
        
        if(currentSelection >= currentPlayerList.size() - 1)
        {
            currentSelection = 0;
        }else{
            currentSelection++;
        }
    }
    
    private void displayOptionMenu()
    {
        playClickSound();
        GameState optionsState = new OptionsState(gsm);
        optionsState.setID("OptionsState");


        for(GameState state : gsm.getStates())
        {
            if(state.getID().equalsIgnoreCase(optionsState.getID()))
            {
                gsm.getStates().remove(state);
            }
        }
        gsm.getStates().addFirst(optionsState);
        System.out.println(gsm.getStates());
    }
    
    @Override
    public void init() {
        
    }

    @Override
    public void update() {
        //check whos player turn it is
        //allow the player to play
        if(currentSelection >= 0)
        {
            player = currentPlayerList.get(currentSelection);
            player.update();
            
            if(player.getName().startsWith("Bot") && !gameOver)
            {
                System.out.println("BOT" + player.getName());
                player.play(this);
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        
        // background
        g.setColor(new Color(0,102,0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // render market
        market.renderDeck(g);
        
        // render game deck
        gameDeck.render(g);
        
        // render player deck
        //player.renderDeck(g);
        //player.renderCards(g);
        
        // only render the deck of the human player
        for(Player p : currentPlayerList)
        {
            if(!p.getName().startsWith("Bot"))
            {
                p.renderDeck(g);
                p.renderCards(g);
            }
        }
    }
    
    

    @Override
    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_ESCAPE)
        {
            displayOptionMenu();
        }else if(keyCode == KeyEvent.VK_LEFT)
        {
            player.leftKeyPressed();
        }else if(keyCode == KeyEvent.VK_RIGHT)
        {
            player.rightKeyPressed();
        }else if(keyCode == KeyEvent.VK_UP)
        {
            player.upKeyPressed();
        }else if(keyCode == KeyEvent.VK_DOWN)
        {
            player.downKeyPressed();
        }else if(keyCode == KeyEvent.VK_ENTER)
        {
            player.enterKeyPressed();
            compareCards();
        }else if(keyCode == KeyEvent.VK_M)
        {
            //player.getDeck().add(market.getMarket().pop());
            goToMarket(player);
        }
    }

    @Override
    public void keyReleased(int keyCode) {
        
    }

    @Override
    public void mouseEntered(int mouseCode) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(int mouseCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(int mouseCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(int mouseCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(int mouseCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
