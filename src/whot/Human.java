/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.util.Scanner;
import javax.swing.JOptionPane;
import static whot.Game.HEIGHT;
import static whot.Game.WIDTH;

/**
 *
 * @author Emmanuel Ozioma
 */
public class Human extends Player{
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    
    private int cSelection;
    
    public Human(String name) {
        super(name);
        gamesPlayed = 0;
        gamesWon = 0;
        gamesLost = 0;
        
        cSelection = 0;
    }
    
    public int getGamesPlayed()
    {
        return gamesPlayed;
    }
    
    public void readData(Scanner scanner)
    {
        super.readData(scanner);
        gamesPlayed = scanner.nextInt();
        System.out.println("gamesPlayed: " + gamesPlayed);
        gamesWon = scanner.nextInt();
        System.out.println("gamesWon: " + gamesWon);
        gamesLost = scanner.nextInt();
        System.out.println("gamesLost: " + gamesLost);
    }
    
    @Override
    public void wonGame()
    {
        super.wonGame();
        gamesWon++;
    }
    
    public void playTurn()
    {
        super.playTurn();
        
    }
    
    @Override
    public void update()
    {
        // update the positions of the cards on the player's deck
        int x = 0;
        int y = (HEIGHT / 4) + (HEIGHT / 8);
        for(Card c : deck)
        {
            c.setX(x);
            c.setY(y);
            if((x + 180) + 180 >= WIDTH) //if the theres a card at the end of the deck put the card at the next line
            {
                x = 0;
                y += 50;
            }else{
                x += 190;
            } 
        }
        // raise the card selected
        if(!deck.isEmpty())
        {
            deck.get(cSelection).setY(y - 200);
        }
    }
    
    @Override
    public void renderDeck(Graphics2D g)
    {
        g.setColor(new Color(192, 192, 192));
        int y = (HEIGHT / 4) + (HEIGHT / 8);
        g.fillRect(0 , y, WIDTH, HEIGHT - y);
    }
    
    @Override
    public void renderCards(Graphics2D g)
    {
        for(Card c : deck)
        {
            c.renderFront(g, c.getX(), c.getY());
        }
    }
    
    @Override
    public void rightKeyPressed()
    {
        playCardSound();
        cSelection++;
        if(cSelection >= deck.size())
        {
            cSelection = 0;
        }
        System.out.println(cSelection);
    }
    
    @Override
    public void leftKeyPressed()
    {
        playCardSound();
        cSelection--;
        if(cSelection < 0)
        {
            cSelection = deck.size() - 1;
        }
        System.out.println(cSelection);
    }
    
    @Override
    public void upKeyPressed()
    {
        int noOfCardsInRow = WIDTH / 190;
        
        if(((cSelection  + 1 ) - (noOfCardsInRow)) > 0)
        {
            playCardSound();
            cSelection -= noOfCardsInRow;
        }else{
            playCardErrorSound();
        }
        System.out.println(cSelection + 1 + " row " + noOfCardsInRow);
    }
    
    @Override
    public void downKeyPressed()
    {
        int noOfCardsInRow = WIDTH / 190; //each card is 180 + 10 for the space between them
        
        if((cSelection  + 1 ) + (noOfCardsInRow) <= deck.size())
        {
            playCardSound();
            cSelection += noOfCardsInRow;
        }else{
            playCardErrorSound();
        }
        System.out.println(cSelection + 1 + " row " + noOfCardsInRow);
    }
    
    @Override
    public void enterKeyPressed()
    {
        //  remove the card selected
        System.out.println("Enter KEY PRESSED");
        Card c = deck.get(cSelection);
        super.selectCard(c);
        System.out.println("Selection " + cSelection + "Card Selected: " + cardSelected + "Size " + deck.size());
        
        // if the player select the card at the last position of the deck...take the current selection to the card at the new first position
        if(cSelection >= deck.size() - 1 || cSelection <= 0)
        {
            cSelection = 0;
        }
        
        System.out.println("Selection! " + cSelection + "Size " + deck.size());
        
    }
    
    public String toString()
    {
        return super.toString() + " " + gamesPlayed + " " + gamesWon + " " + gamesLost;
    }
}
