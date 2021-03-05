/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import static whot.Game.HEIGHT;
import static whot.Game.WIDTH;

/**
 *
 * @author Emmanuel Ozioma
 */
public class OptionsState extends GameState{

    private String[] options = {"New Game", "Load Game", "Save Game"};
    private int currentSelection;
    //private String id;
    
    // constructor
    public OptionsState(GameStateManager gsm) {
        super(gsm);
        currentSelection = 0;
        //id = "OptionsSatae";
    }
    
//    public String getID()
//    {
//        return id;
//    }
    
    public String[] getOptions()
    {
        return options;
    }
    
    @Override
    public void init() {
        
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics2D g) {
        
        // background
        g.setColor(new Color(0, 102, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        for(int i = 0; i < options.length; i++)
        {
            
            if(i == currentSelection)
            {
                g.setColor(Color.green);
            }else{
                g.setColor(Color.white);
            }
            
            int padding = i * 200;
            Font font= new Font("Arial", Font.PLAIN, 70);
            g.setFont(font);           // font
            g.drawString(options[i], (WIDTH / 2) - 100, (WIDTH / 6) + padding);    
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_UP)
        {
            playSelectionSound();
            if(currentSelection == 0 )
            {
                currentSelection = options.length - 1;
            }else{
                currentSelection--;
            }
            
        }else if(keyCode == KeyEvent.VK_DOWN)
        {
            playSelectionSound();
            if(currentSelection == options.length - 1)
            {
                currentSelection = 0;
            }else{
                currentSelection++;
            }
        }else if(keyCode == KeyEvent.VK_ENTER)
        {
            playClickSound();
            if(currentSelection == 0)
            {
                GameState playState = new PlayState(gsm);
                playState.setID("PlayState");
                for(GameState state : gsm.getStates())
                {
                    if(state.getID().equalsIgnoreCase(playState.getID()))
                    {
                        gsm.getStates().remove(state);
                    }
                }
                gsm.getStates().addFirst(playState);
            }else if(currentSelection == 1)
            {
                // load game
            }else if(currentSelection == 2)
            {
                // save game
            }
        }else if(keyCode == KeyEvent.VK_ESCAPE)
        {
            GameState menuState = new MenuState(gsm);
            menuState.setID("MenuState");
            for(GameState state : gsm.getStates())
            {
                if(state.getID().equalsIgnoreCase(menuState.getID()))
                {
                    gsm.getStates().remove(state);
                }
            }
            gsm.getStates().addFirst(menuState);
        }
            
    }

    @Override
    public void keyReleased(int keyCode) {
        
    }

    @Override
    public void mouseEntered(int mouseCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
