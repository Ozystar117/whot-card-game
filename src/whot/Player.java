/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import static whot.Game.HEIGHT;
import static whot.Game.WIDTH;

/**
 *
 * @author Emmanuel Ozioma
 */
public abstract class Player {
    protected String name;
    protected CopyOnWriteArrayList<Card> deck;
    
    protected Card cardSelected;
    
    protected boolean won;
    /**
     * Constructor
     */
    public Player(String name)
    {
        this.name = name;
        deck = new CopyOnWriteArrayList<Card>();
        cardSelected = null;
        
        won = false;
    }

    // return the name of the player
    public String getName() {
        return name;
    }
    
    // return the player's deck
    public CopyOnWriteArrayList<Card> getDeck()
    {
        return deck;
    }
    
    public void displayDeck()
    {
        System.out.println(deck);
    }
    
    public void setName(String name)
    {
        this. name = name;
    }
    
    public void playTurn()
    {
        System.out.println(name);
    }
    
    public void removeCardFromDeck(Card card)
    {
        deck.remove(card);
    }
    
    public void goToMarket(Stack<Card> market)
    {
        Card card = market.pop();
        System.out.println("TOOK FROM MARKET " + card);
        deck.add(card);
    }
    
    public void wonGame()
    {
        this.won = true;
    }
    
    /** Handle Data
     * @param scanner*/
    
    public void readData(Scanner scanner)
    {
        name = scanner.next();
        System.out.println("name: " + name);
    }
    
    public void writeData(PrintWriter writer)
    {
        writer.println(this);
        System.out.println(this);
    }   
    
    public void selectCard(Card card)
    {
        cardSelected = card;
    }
    
    public Card getCardSelected()
    {
        return cardSelected;
    }
    
    public void play(PlayState p)
    {
        
    }
    
    public String toString()
    {
        return name;
    }
    
    public void playSound(File soundFilePath)
    {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFilePath));
            clip.start();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void playCardSound()
    {
        playSound(new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV10/build/classes/whot/audio/card.wav"));
    }
    
    public void playCardErrorSound()
    {
        playSound(new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV10/build/classes/whot/audio/cardError.wav"));
    }
    
    public abstract void update();
    public abstract void rightKeyPressed();
    public abstract void leftKeyPressed();
    public abstract void upKeyPressed();
    public abstract void downKeyPressed();
    public abstract void enterKeyPressed();
    public abstract void renderCards(Graphics2D g);
    public abstract void renderDeck(Graphics2D g);
}
