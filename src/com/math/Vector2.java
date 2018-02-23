package com.math;

public class Vector2 {

    public float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getMagnitude(){
        return (float)(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }

    public float dot(Vector2 other){
        return ((this.x * other.x) + (this.y * other.y));
    }

    public float getAngleBetween(Vector2 other){
        // Formula:
        // cos(z) = (v1*v2) / (|v1|*|v2|)
        // v1 = this
        // v2 = other
        // z = angle between them
        float dot = this.dot(other);
        float magnitude = this.getMagnitude() * other.getMagnitude();
        return (float)(Math.acos(dot/magnitude)); // In radians, (angle*180)/PI -> convert to degrees
    }

    public Vector2 normalized(){
        return this.divide(getMagnitude());
    }

    public Vector2 divide(float scalar){
        return new Vector2(this.x/scalar, this.y/scalar);
    }
}
