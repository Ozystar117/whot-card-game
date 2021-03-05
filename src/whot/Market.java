/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.Stack;
import static whot.Game.HEIGHT;
import static whot.Game.WIDTH;

/**
 *
 * @author Emmanuel Ozioma
 */
public class Market {

    private Stack<Card> market;
    private int width;
    private int height;
    
    public Market() 
    {
        this.market = new Stack<Card>();
        this.height = 240;
        this.width = 180;
    }
    
    public Stack<Card> getMarket()
    {
        return market;
    }
    
    public void addCard(Card card)
    {
        market.add(card);
    }
        
    public void shuffle()
    {
        Collections.shuffle(market);
    }
    
//    public void giveTopCardToPlayer(Player player)
//    {
//        player.getDeck().add(market.pop());
//    }
    
    public void renderDeck(Graphics2D g)
    {
        g.setColor(Color.gray);
        int gap = 100;
        int x = (WIDTH / 4) - (width / 2);
        int y = (HEIGHT / 4) - (height / 2) - gap;
        g.fillRect( x, y, width, height);
        if(market.size() > 0)
        {
            market.peek().renderBehind(g, x, y);
        }
    }
}
