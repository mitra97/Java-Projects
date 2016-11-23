package com.mitra4;

import javax.swing.Timer;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Point;
import java.util.Random;

/**
 * Created by Mitra on 11/23/2016.
 */
public class Snake implements ActionListener,KeyListener { //action listener is an interface with abstract methods the class can inherit.
    public JFrame frame;
    public Toolkit toolkit;
    public Panel panel;
    public static Snake snake;
    public Timer timer = new Timer(20,this); //timer with an action listener. This refers to action listener
    public ArrayList<Point> snakeParts = new ArrayList<Point>();//An array list is an automatically growing array. Points are an object that has x and y values as its inputs. So we have an automatically growing array with each point having its own x and y coordinate.
    public int ticks = 0;
    public Point head, cherry;
    public static final int up=0, down = 1, left = 2, right = 3, scale = 10;
    public int direction = down;
    public int score =0;
    public boolean over = false, paused;
    public Random random;
    public int taillength = 10;

    public Snake() {
        toolkit = Toolkit.getDefaultToolkit(); //toolkit class has a lot of things in it, can get frame size as well.
        frame = new JFrame("Snake"); //frame name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Tells program to stop running when window is closed
        frame.setVisible(true); //can you see the frame
        frame.setSize(805, 700); //size of frame
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //set location to middle? see at end
        frame.add(panel = new Panel()); //just creates the panel, from panel class
        frame.addKeyListener(this);//allows us to input key movements in the game
        StartGame();
    }
    public void StartGame(){
        head = new Point(0,-1);
        random = new Random();
        direction = down; //starts the snake moving down
        taillength = 1;
        score = 0;
        over = false;
        paused = false; //can pause the game
        snakeParts.clear(); //makes sure that the board is cleared on replay
        cherry = new Point(random.nextInt(79), random.nextInt(66)); //assigns cherry a random spot on the board
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
        ticks++;
        if (ticks % 5 == 0 && head != null && over == false && !paused) {
            snakeParts.add(new Point(head.x, head.y));
            if (direction == up) {
                if(head.y - 1 >= 0 && noTailAt(head.x, head.y-1)) {
                    head = (new Point(head.x, head.y - 1));
                }
                else{
                    over = true;
                }
            }
            if(direction == down){
                if(head.y + 1 < 67 && noTailAt(head.x, head.y+1)) {
                    head = (new Point(head.x, head.y + 1));
                }
                else{
                    over = true;
                }
            }
            if(direction == left){
                if(head.x - 1 >= 0 && noTailAt(head.x-1, head.y)) {
                    head = (new Point(head.x - 1, head.y));
                }
                else{
                    over = true;
                }
            }
            if(direction == right){
                if(head.x + 1 < 80 && noTailAt(head.x + 1, head.y)) {
                    head = (new Point(head.x + 1, head.y));
                }
                else{
                    over = true;
                }
            }
            if(snakeParts.size() > taillength){
                snakeParts.remove(0);
            }
            if(cherry != null){
                if(head.equals(cherry)){
                    score+=10;
                    taillength++;
                    cherry.setLocation(random.nextInt(79), random.nextInt(66));
                }
            }
        }
    }

    public boolean noTailAt(int x, int y) {
        for(Point point : snakeParts){
            if(point.equals(new Point(x,y))){
                return false;
            }
        }
        return true;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(i== KeyEvent.VK_LEFT && direction != right){
            direction = left;
        }
        if(i == KeyEvent.VK_RIGHT && direction != left){
            direction = right;
        }
        if(i == KeyEvent.VK_UP && direction != down){
            direction = up;
        }
        if(i == KeyEvent.VK_DOWN && direction != up){
            direction = down;
        }
        if(i == KeyEvent.VK_SPACE) {
            if (over == true) {
                StartGame();
            }
            else{
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args){
        snake = new Snake();
    }


}

