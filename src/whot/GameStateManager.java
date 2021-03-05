/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 *
 * @author Emmanuel Ozioma
 */
public class GameStateManager {

    private Deque<GameState> states;
    public GameStateManager()
    {
        states = new ArrayDeque<GameState>();
        GameState menuState = new MenuState(this);
        states.addFirst(menuState);
        menuState.setID("MenuState");
    }
    
    public Deque<GameState> getStates()
    {
        return states;
    }
    
    // update the state at the top of the stack. i.e the current state
    public void update()
    {
        states.peek().update();
    }
    
    public void render(Graphics2D g)
    {
        states.peek().render(g);
    }
    
    public void keyPressed(int keyCode)
    {
        states.peek().keyPressed(keyCode);
    }
    
    public void keyReleased(int keyCode)
    {
        states.peek().keyReleased(keyCode);
    }
    
    public void mouseEntered(int keyCode)
    {
        states.peek().mouseEntered(keyCode);
    }
    
    public void mouseExited(int keyCode)
    {
        states.peek().mouseExited(keyCode);
    }
    
    public void mousePressed(int keyCode)
    {
        states.peek().mousePressed(keyCode);
    }
    
    public void mouseReleased(int keyCode)
    {
        states.peek().mouseReleased(keyCode);
    }
    
    public void mouseClicked(int keyCode)
    {
        states.peek().mouseClicked(keyCode);
    }
}
