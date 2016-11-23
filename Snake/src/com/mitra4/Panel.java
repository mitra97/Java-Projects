package com.mitra4;

/**
 * Created by Mitra on 11/23/2016.
 */
import java.awt.*;
import javax.swing.JPanel;

@SuppressWarnings("serials")
public class Panel extends JPanel{
    public static int currcolor = 0;

    @Override
    protected void paintComponent(Graphics g) {//given JPanel class allows us to modify the panel without having to make new shit on our own. A protected class that allows us to modify the panel itself. Super useful for all games.
        super.paintComponent(g); //calling super class. Have to call before everything else. Calling after causes it to repaint back to white.
        g.setColor((Color.green));
        currcolor++;
        g.fillRect(0,0,800,700);
        Snake snake = Snake.snake;
        for(Point point : snake.snakeParts){
            g.setColor(Color.blue);
            g.fillRect(point.x * snake.scale, point.y * snake.scale,snake.scale,snake.scale);
        }
        g.fillRect(snake.head.x * snake.scale, snake.head.y * snake.scale,snake.scale,snake.scale);
        g.setColor(Color.red);
        g.fillRect(snake.cherry.x * snake.scale, snake.cherry.y * snake.scale,snake.scale,snake.scale);
        String end = "Score " + snake.score;
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(end, 650, 15);
    }
}
