/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Emmanuel Ozioma
 */
public class Card{
    //the shape of the card
    private String shape;
    // the number on the card
    private int number;
    
    private int x;
    private int y;
    
    // the card image
    private BufferedImage cardImage;
    // the image of the back of the card
    private BufferedImage cardImageBehind;
    
    /**
     * Constructor
     * @param shape
     * @param number 
     * @param imgFilePath 
     */
    public Card(String shape, int number, String imgFilePath)
    {
       this.shape = shape;
       this.number = number;
       
       this.x = 0;
       this.y = 0;
       
       this.cardImage = null;
       try
        {
            this.cardImage = ImageIO.read(new File(imgFilePath));
        }catch(IOException e)
        {
            System.out.println("IO Error!");
        }
       this.cardImageBehind = null;
       try
        {
            this.cardImageBehind = ImageIO.read(new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/backPic1.png"));
        }catch(IOException e)
        {
            System.out.println("IO Error!");
        }
    }

    // return the shape of the card
    public String getShape() {
        return shape;
    }

    // return the number on the card
    public int getNumber() {
        return number;
    }
    
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
    
    // return the image of the card
    public BufferedImage getCardImage() {
        return cardImage;
    }
    
    public BufferedImage getCardImageBehind()
    {
        return cardImageBehind;
    }
    
    public void moveUp()
    {
        this.y -= 90;
    }
    
    public void moveDown()
    {
        this.y += 90;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public void setImage(String filePath)
    {
        try
        {
            this.cardImage = ImageIO.read(new File(filePath));
        }catch(IOException e)
        {
                 System.out.println("IO Error!");
        }
    }
    
    public void renderFront(Graphics2D g, int x, int y)
    {
        this.x = x;
        this.y = y;
        g.drawImage(cardImage, this.x, this.y, null);
    }
    
    public void renderBehind(Graphics2D g, int x, int y)
    {
        this.x = x;
        this.y = y;
        g.drawImage(cardImageBehind, this.x, this.y, null);
    }
    
    public String toString()
    {
        return shape + " " + number;// + " " + cardImage.getWidth() + " " + cardImageBehind.getWidth();
    }
    
    
    //public void paintComponent(Graphics g)
    //{
      //  g.drawImage(cardImage, 0, 0, this);
    //}

    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void mouseEntered(int mouseCode) {
        
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseExited(int mouseCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
