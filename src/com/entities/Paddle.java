package com.entities;

import com.com.graphics.Rectangle;
import com.input.Input;
import com.math.Vector3;
import com.styrbjorn.Main;

public class Paddle {

    private int upKeyCode, downKeyCode;
    // moveSpeed - same for both paddle
    private static final int moveSpeed = 9;
    public static final int WIDTH = 15;
    public static final int HEIGHT = 100;

    private Rectangle shape;

    public Paddle(Vector3 startPos, int _upKeyCode, int _downKeyCode)
    {
        shape = new Rectangle(WIDTH, HEIGHT, new Vector3(0f, 0.8f, 0));
        shape.setPosition(startPos);

        upKeyCode = _upKeyCode;
        downKeyCode = _downKeyCode;
    }

    public float getY(){
        return shape.getPosition().y;
    }

    public void update(){
        // Input
        if(Input.isKeyDown(upKeyCode)){
            // move shape up
            float delta = shape.getPosition().y - moveSpeed;
            Vector3 newPosition = new Vector3(shape.getPosition().x, delta, 0);
            shape.setPosition(newPosition);
        }
        if(Input.isKeyDown(downKeyCode)){
            // move shape down
            float delta = shape.getPosition().y + moveSpeed;
            Vector3 newPosition = new Vector3(shape.getPosition().x, delta, 0);
            shape.setPosition(newPosition);
        }

        this.OOBCheck();
    }

    private void OOBCheck(){
        if(shape.getPosition().y < 0)
            shape.setPosition(new Vector3(shape.getPosition().x, 0, 0));
        else if(shape.getPosition().y > Main.HEIGHT-HEIGHT){
            shape.setPosition(new Vector3(shape.getPosition().x, Main.HEIGHT-HEIGHT, 0));
        }
    }

    public void render(){
        shape.render();
    }

}
