/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;
import static whot.Game.HEIGHT;
import static whot.Game.WIDTH;

/**
 *
 * @author Emmanuel Ozioma
 */
public class AI extends Player{

    public AI(String name) {
        super(name);
        
    }
    
    public void play(PlayState p)
    {
        super.play(p);
        System.out.println("TIME TO PLAY");
        
        
        Stack<Card> gameDeck = p.getGameDeck().getDeck();
        List<Card> validCardList = new ArrayList<Card>();
        System.out.println("CARDS ON DECK" + deck);
        for(Card c : deck)
        {
            if(c.getShape().equals(gameDeck.peek().getShape()) || c.getNumber() == gameDeck.peek().getNumber())
            {
                validCardList.add(c);
                System.out.println("VALID CARDS " + c);
            }
        }
        
        // play the first valid card
        if(!validCardList.isEmpty())
        {
            selectCard(validCardList.get(0));
            p.compareCards();
            //gameDeck.add(cardSelected);
            //deck.remove(cardSelected);
            //p.managePlayerTurns();
        }else{
            p.goToMarket(this);
            System.out.println("WENT TO MARKET" + deck);
            //p.managePlayerTurns(); //already in p.goToMarket();
        }
        
    }
    
    public String toString()
    {
        return super.toString();
    }

    @Override
    public void rightKeyPressed() {
    }

    @Override
    public void leftKeyPressed() {
    }

    @Override
    public void upKeyPressed() {
    }

    @Override
    public void downKeyPressed() {
    }

    @Override
    public void enterKeyPressed() {
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
    public void update() {
        // update the positions of the cards on the player's deck
        int x = 0;
        int y = (HEIGHT / 4) + (HEIGHT / 8);
        for(Card c : deck)
        {
            c.setX(x);
            c.setY(y);
            if((x + 180) + 180 >= WIDTH)
            {
                x = 0;
                y += 50;
            }else{
                x += 190;
            } 
        }
    }
}
