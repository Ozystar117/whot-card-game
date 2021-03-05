/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Graphics2D;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Emmanuel Ozioma
 */
public abstract class GameState {
    protected GameStateManager gsm;
    private String id;
    
    protected GameState(GameStateManager gsm)
    {
        this.gsm = gsm;
        id = "";
        init();
    }
    
    public void setID(String id)
    {
        this.id = id;
    }
    
    public String getID()
    {
        return id;
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
    
    protected void playSelectionSound()
    {
        playSound(new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV10/build/classes/whot/audio/selectionSound.wav"));
    }
    
    protected void playClickSound()
    {
        playSound(new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV10/build/classes/whot/audio/click.wav"));
    }
    
    protected void playCardSound()
    {
        playSound(new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV10/build/classes/whot/audio/card.wav"));
    }
    
    public abstract void init();
    public abstract void update();
    public abstract void render(Graphics2D g);
    public abstract void keyPressed(int keyCode);
    public abstract void keyReleased(int keyCode);
    public abstract void mouseEntered(int mouseCode);
    public abstract void mouseExited(int mouseCode);
    public abstract void mousePressed(int mouseCode);
    public abstract void mouseReleased(int mouseCode);
    public abstract void mouseClicked(int mouseCode);
    //public abstract void mousePressed(int mouseCode);
}
