package com.math;

import com.com.graphics.Rectangle;

public class Collision {

    // Compares the bounding boxes of rect1 & rect2
    // Proof by contradiction
    // If no contradcition logic is met return true, assuming a intersection is taking place
    public static boolean intersects(Rectangle rect1, Rectangle rect2){

        // Proof by contradiction
        if(rect1.getPosition().x > rect2.getPosition().x + rect2.getWidth() || rect2.getPosition().x > rect1.getPosition().x + rect1.getWidth() || rect1.getPosition().y > rect2.getPosition().y + rect2.getHeight() || rect2.getPosition().y > rect1.getPosition().y + rect1.getHeight()){
            return false;
        }

        return true;
    }

}
