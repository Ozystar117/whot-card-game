/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whot;

import javax.swing.JFrame;

/**
 *
 * @author Emmanuel Ozioma
 */
public class WhotLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game game = new Game();
        JFrame window  = new JFrame("GameLoop");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null); // centralise
        window.setVisible(true);
        
        game.start();
        //game.loadMarket();
        //game.shuffle();
        //game.writePlayerData("save003.txt");
        //game.saveGame("save001.txt");
        //game.loadGame("save001.txt");
        System.out.println("LAUNCHER CLASS");
        //Window window = new Window();
        //GUI gui = new GUI();
    }
    
}
