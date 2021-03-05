/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Emmanuel Ozioma
 */
public class GUI extends JFrame {
    Game game;
    
    // actual windows resolution
    private int actualHeight;
    private int actualWidth;
    
    
    private Board board;
    
    private JButton playBtn;
    //private JButton 
    
    private PlayBtnAction playBtnAction;
    
    //private BufferedImage 
    
    
    public GUI()
    {
        game = new Game();
        
        actualWidth = 1920;
        actualHeight = 1440;
        
        playBtn = new JButton();
        
        board = new Board();
        playBtnAction = new PlayBtnAction();
        
        setupWindow();
        loadPlayBtn();
        act();
    }
    
    public void setupWindow()
    {
        this.setSize(actualWidth, actualHeight);
        this.setTitle("WHOT!");
        this.setVisible(true);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // link the board with the window
        this.setContentPane(board);
        // change default layout
        this.setLayout(null);
    }
    
    public void loadPlayBtn()
    {
        // button
        playBtn.setBounds(200, 200, 140, 80);
        Color playBtnColor = new Color(204, 204, 0);
        playBtn.setBackground(playBtnColor);
        // font
        Font font = new Font("Times New Roman", Font.PLAIN, 30);
        playBtn.setFont(font);
        playBtn.setText("PLAY");
        board.add(playBtn); // add the play btn to the board
        
    }
    
    public void act()
    {
        playBtn.addActionListener(playBtnAction);
    }
        
    public Board getBoard()
    {
        return board;
    }
    
    public class Board extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            // board
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, actualWidth, actualHeight);
            
            // deck
            g.setColor(Color.gray);
            //draw the deck deck compactment at the center of the board
            g.drawRect(((board.getWidth() / 2) - 90), ((board.getHeight() / 2) - 120), 180, 240);
            g.fillRect(((board.getWidth() / 2) - 90), ((board.getHeight() / 2) - 120), 180, 240);
            
            // draw the market compactment
            g.setColor(Color.gray);
            g.drawRect(((board.getWidth() / 4) - 90), ((board.getHeight() / 2) - 120), 180, 240);
            g.fillRect(((board.getWidth() / 4) - 90), ((board.getHeight() / 2) - 120), 180, 240);
            
            // player cards compactment
            g.setColor(Color.gray);
            g.drawRect(0, (board.getHeight() / 2) + (board.getHeight() / 8), board.getWidth(), (board.getHeight() - this.getY()));
            g.fillRect(((board.getWidth() / 4) - 90), ((board.getHeight() / 2) - 120), 180, 240);
            
            // cards
            //g.drawImage(game.getCard().getCardImage(), ((board.getWidth() / 2) - 90), ((board.getHeight() / 2) - 120), this);
            //g.drawImage(game.getCard().getCardImageBehind(), ((board.getWidth() / 4) - 90), ((board.getHeight() / 2) - 120), this);
        }
        
        public void displayMarket()
        {
            
        }
    }
    
    
    
    public class PlayBtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Play btn clicked!");
        }
    }
}
