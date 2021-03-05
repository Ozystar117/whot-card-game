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
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import static whot.Game.HEIGHT;
import static whot.Game.WIDTH;

/**
 *
 * @author Emmanuel Ozioma
 */
public class MenuState extends GameState{

    private String[] options = {"Start", "Help", "Quit"};
    private int currentSelection;
    //private String id;
    
    //private OptionsState optionsState;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        currentSelection = 0;
        
        //id = "MenuState";
        //optionsState = new OptionsState(gsm);
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
        
        // options
        
        for(int i = 0; i < options.length; i++)
        {
            if(i == currentSelection)
            {
                g.setColor(Color.green);
            }else{
                g.setColor(Color.white);
            }
            
            int padding = i * 200;
            g.setFont(new Font("Arial", Font.PLAIN, 70));           // font
            g.drawString(options[i], ((WIDTH / 2) - (WIDTH / 8)), (WIDTH / 6) + padding);
            
            
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        
        if(keyCode == KeyEvent.VK_DOWN)
        {
            playSelectionSound();
            currentSelection++;
            if(currentSelection >= options.length)
            {
                currentSelection = 0;
            }
        }else if(keyCode == KeyEvent.VK_UP){
            playSound(new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV10/build/classes/whot/audio/selectionSound.wav"));
            currentSelection--;
            if(currentSelection < 0)
            {
                currentSelection = options.length - 1;
            }
        }else if(keyCode == KeyEvent.VK_ENTER)
        {
            playClickSound();
            if(currentSelection == 0)
            {
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
            }else if(currentSelection == 1)
            {
                //help
            }else if(currentSelection == 2)
            {
                System.exit(0);
            }
            
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
