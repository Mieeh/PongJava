package com.entities;

import com.com.graphics.Rectangle;
import com.math.Vector3;
import com.styrbjorn.Main;

import java.util.Random;

public class Ball {

    Rectangle shape;
    public static final int SIZE = 20;
    Random random = new Random();
    float velocityX, velocityY;

    Paddle paddleLeft, paddleRight;

    public Ball(Paddle _paddleLeft, Paddle _paddleRight){
        shape = new Rectangle(SIZE, SIZE, new Vector3(1, 0, 0));
        shape.setPosition(new Vector3(Main.WIDTH/2 - (SIZE/2), Main.HEIGHT/2 - (SIZE/2), 0));

        setVelocity();

        paddleLeft = _paddleLeft;
        paddleRight = _paddleRight;
    }

    private void setVelocity(){
        int num = random.nextInt(100);
        if(num > 50){
            velocityX = 7;
            if(num > 75){
                velocityY = 3;
            }
            else{
                velocityY = -3;
            }
        }
        else{
            velocityX = -7;
            if(num < 25){
                velocityY = 3;
            }
            else{
                velocityY = -3;
            }
        }
    }

    public void update(){
        // Move based on velocity
        Vector3 newPos = new Vector3(shape.getPosition().x + velocityX, shape.getPosition().y + velocityY, 0);
        shape.setPosition(newPos);

        // Collision check
        float ballx1 = shape.getPosition().x;
        float ballx2 = shape.getPosition().x + shape.getWidth();
        float rectx1 =
    }

    public void render(){
        shape.render();
    }

}
