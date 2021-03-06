package com.entities;

import com.com.graphics.Rectangle;
import com.math.Collision;
import com.math.Vector2;
import com.math.Vector3;
import com.styrbjorn.Main;

import java.util.Random;

public class Ball {

    private Rectangle shape;
    public static final int SIZE = 20;
    private Random random = new Random();
    private float velocityX, velocityY;

    private Paddle paddleLeft, paddleRight;

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
            velocityX = 4;
            if(num > 75){
                velocityY = random.nextInt(7)+1;
            }
            else{
                velocityY = -(random.nextInt(7)+1);
            }
        }
        else{
            velocityX = -4;
            if(num < 25){
                velocityY = random.nextInt(7)+1;
            }
            else{
                velocityY = -(random.nextInt(7)+1);
            }
        }
    }

    public void update(){
        // Move based on velocity
        Vector3 newPos = new Vector3(shape.getPosition().x + velocityX, shape.getPosition().y + velocityY, 0);
        shape.setPosition(newPos);

        // Collision check
        // Window height bounds
        if(shape.getPosition().y < 0 || shape.getPosition().y > Main.HEIGHT-SIZE){
            velocityY *= -1;
        }

        // Left paddle & ball
        // Right paddle & ball
        if(Collision.intersects(shape, paddleLeft.getShape())){
            // Y Velocity
            // Test
            Vector2 v1 = new Vector2(velocityX, velocityY);
            Vector2 upNormal = new Vector2(0, 1);
            float radians = v1.normalized().getAngleBetween(upNormal);
            float degree = (radians*180)/3.14159f;
            System.out.println(degree);

            velocityX *= 1.25f; // 10% speed increase/hit
            velocityX *= -1;
        }
        if(Collision.intersects(shape, paddleRight.getShape())){
            // Y Velocity
            // Test
            Vector2 v1 = new Vector2(velocityX, velocityY);
            Vector2 upNormal = new Vector2(0, 1);
            float radians = v1.normalized().getAngleBetween(upNormal);
            float degree = (radians*180)/3.14159f;
            System.out.println(degree);

            velocityX *= 1.25f; // 10% speed increase/hit
            velocityX *= -1;
        }
    }

    public void reset(){
        shape.setPosition(new Vector3(Main.WIDTH/2 - (SIZE/2), Main.HEIGHT/2 - (SIZE/2), 0));
        setVelocity();
    }


    public Rectangle getShape() {
        return shape;
    }

    public void render(){
        shape.render();
    }

}
