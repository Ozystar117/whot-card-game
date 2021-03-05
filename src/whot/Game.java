/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Emmanuel Ozioma
 */
public class Game extends JPanel implements KeyListener, MouseListener, Runnable{
    public static final int WIDTH =  1920; //1280
    public static final int HEIGHT = 1440; //800
    public static final Font font = new Font("Bebas Neue Regular", Font.PLAIN, 28);
    
    private Thread game;
    private boolean running;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    
    private long startTime; // keep track of the time the thread started
    private long elapsed;
    
    
    private GameStateManager gsm;
    
    private String dumpFileName;
    
    // all players
    private List<Player> playerList;
    
    private boolean finished;
    
    public Game()
    {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        
        
        gsm = new GameStateManager();
        
        dumpFileName = "";
        playerList = new ArrayList<Player>();
        finished = false;
        
        
        
    }
    
    // display the details of the players stored
    public void showAllPlayers()
    {
        System.out.println("ALL PLAYERS");
        for(Player player : playerList)
        {
            System.out.println(player);
        }
    }
    
    public void savePlayer()
    {
        
    }
    // create 5 bots
    public void createBots()
    {
        for(int i = 1; i < 6; i++)
        {
            Player player = new AI("Bot" + i);
            //player.setName("Bot" + i);
            playerList.add(player);
        }
    }
    
    
    /** HANDLE DATA */
    public void readPlayerData(String fileName)
    {
        
        File file = new File("C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/" + fileName);
        Scanner scanner = null;
        try{
            scanner = new Scanner(file);
        }catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        if(scanner != null)
        {
            while(scanner.hasNext())
            {
                
                Player player = new Human("");
                player.readData(scanner);
            }
            
        }else{
            System.out.println("Scanner is null");
        }
    }
    
    public void writePlayerData(String fileName)
    {
        PrintWriter writer = null;
        String filePath = "C:/Users/Emmanuel Ozioma/Documents/NetBeansProjects/WhotV8/build/classes/whot/" + fileName;
        try{
            writer = new PrintWriter(filePath);
        }catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        if(writer != null)
        {
            if(!playerList.isEmpty())
            {
                for(Player player :  playerList)
                {
                    player.writeData(writer);
                }
                writer.close();
            }else{
                System.out.println("No player data found!");
            }
            
        }
    }
    
    // save the game
    public void saveGame(String fileName)
    {
        writePlayerData(fileName);
        
    }
    
    
    // load a saved game
    public void loadGame(String fileName)
    {
        readPlayerData(fileName);
    }
    
    
    private void update()
    {
         //System.out.println("updated");
        gsm.update();
    }
    
    private void render()
    {
        Graphics2D g = (Graphics2D)image.getGraphics();
        
        gsm.render(g);
        //System.out.println(gsm.getStates());
        
        //System.out.println("RENDERD!");
        
        g.dispose();
        
        Graphics2D g2d = (Graphics2D)getGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
    }
        
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }

    
    // GAME LOOP
    @Override
    public void run() {
        System.out.println(" Run called");
        
        boolean shouldRender = false;
        int fps = 0, updates = 0;
        long fpsTimer = System.currentTimeMillis();
        double nsPerUpdate = 1000000000.0 / 60;
        System.out.println(" fpsTimer: " + fpsTimer);
        
        // last update time in nanoseconds
        double then = System.nanoTime();
        double unprocessed = 0;
        
        while(running)
        {
            double now = System.nanoTime();
            unprocessed += (now - then) / nsPerUpdate;
            //System.out.println(" umprocessed: " + unprocessed);
            then = now;
            //System.out.println(" now: " + now);
            //update queue
            while(unprocessed >= 1) // while there's smth to process
            {
                updates++;
                update();
                unprocessed--;
                shouldRender = true;
            }

            // render
            if(shouldRender)
            {
                fps++;
                render();
                shouldRender = false;
            }else{
                try{
                    Thread.sleep(1);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

            // FPS Timer
            if(System.currentTimeMillis() - fpsTimer > 1000)
            {
                System.out.printf("%d fps %d updates", fps, updates);
                System.out.println();
                fps = 0;
                updates = 0;
                fpsTimer += 1000;
            }
        }
    }
    
    public synchronized void start()
    {
        if(running)return; // if for any reason run() is called while theres an existing thread, don't create another thread.
        running = true;
        game = new Thread(this, "game");
        game.start();
    }
    
    public synchronized void stop()
    {
        if(!running)return;
        running = false;
        System.exit(0);
    }
    //public String toString()
    //{
      //  for()
    //}

    @Override
    public void mouseClicked(MouseEvent e) {
        gsm.mouseClicked(e.getButton());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gsm.mousePressed(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gsm.mouseReleased(e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        gsm.mouseEntered(e.getButton());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
