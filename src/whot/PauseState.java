/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import static whot.Game.HEIGHT;
import static whot.Game.WIDTH;

/**
 *
 * @author Emmanuel Ozioma
 */
public class PauseState extends GameState{

    public PauseState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 2));
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_DOWN)
        {
            playSelectionSound();
//            currentSelection++;
//            if(currentSelection >= options.length)
//            {
//                currentSelection = 0;
//            }
        }else if(keyCode == KeyEvent.VK_UP){
            playSound(new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV10/build/classes/whot/audio/selectionSound.wav"));
//            currentSelection--;
//            if(currentSelection < 0)
//            {
//                currentSelection = options.length - 1;
//            }
        }else if(keyCode == KeyEvent.VK_ENTER)
        {
            playClickSound();
            
            GameState playState = null;
            for(GameState state : gsm.getStates())
            {
                if(state.getID().equalsIgnoreCase("PlayState"))
                {
                    playState = state;
                    gsm.getStates().remove(state);
                }
            }
            gsm.getStates().push(playState);
            System.out.println(gsm.getStates());
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
