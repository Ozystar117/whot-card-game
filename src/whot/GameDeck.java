/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;
import static whot.Game.HEIGHT;
import static whot.Game.WIDTH;

/**
 *
 * @author Emmanuel Ozioma
 */
public class GameDeck {

    private Stack<Card> deck;
    
    private int width;
    private int height;
    
    public GameDeck()
    {
        this.deck = new Stack<Card>();
        this.height = 240;
        this.width = 180;
    }
    
    public Stack<Card> getDeck()
    {
        return deck;
    }
    
    public void addCard(Card card)
    {
        deck.push(card);
    }
        
    
    public void render(Graphics2D g)
    {
        g.setColor(Color.gray);
        int gap = 100;
        int x = (WIDTH / 2) - (width / 2);
        int y = (HEIGHT / 4) - (height / 2) - gap ;
        g.fillRect( x, y, width, height);
        if(deck.size() > 0)
        {
            deck.peek().renderFront(g, x, y);
        }else{
            System.out.println("Deck is Empty");
        }
    }
        
}
