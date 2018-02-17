package com.entities;

import com.com.graphics.Rectangle;
import com.input.Input;
import com.math.Vector3;

public class Paddle {

    private int upKeyCode, downKeyCode;
    // moveSpeed - same for both paddle
    private static final int moveSpeed = 5;

    private Rectangle shape;

    Paddle()
    {
        shape = new Rectangle(30, 100, new Vector3(0.5f, 0.8f, 0));
    }

    void update(){
        // Input
        if(Input.isKeyDown(upKeyCode)){
            // move shape up
        }
        if(Input.isKeyDown(downKeyCode)){
            // move shape down
        }
    }

}
