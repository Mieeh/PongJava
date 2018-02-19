package com.math;

import com.com.graphics.Rectangle;

public class Collision {

    // Compares the bounding boxes of rect1 & rect2
    // If their bounding boxes intersect return true
    // If no intersection is detected return false
    public static boolean intersects(Rectangle rect1, Rectangle rect2){

        if(rect1.getPosition().x+rect1.getWidth() < rect2.getPosition().x || rect2.getPosition().x + rect2.getWidth() < rect1.getPosition().x)
        {
            return true;
        }
        else if(rect1.getPosition().y+rect1.getHeight() < rect2.getPosition().y || rect2.getPosition().y+rect2.getHeight() < rect1.getPosition().y)
        {
            return true;
        }
        else{
            return false;
        }
    }

}
