package com.com.graphics;

import com.math.Vector3;

import java.awt.*;

public abstract class GraphicsObject {

    protected Vector3 color;
    protected Vector3 position;
    protected float width, height;

    public abstract void render();

    GraphicsObject(float _width , float _height, Vector3 _color){
        position = new Vector3(0,0,0);
        setWidth(_width);
        setHeight(_height);
        setColor(_color);
    }

    public Vector3 getPosition(){
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setColor(Vector3 color) {
        this.color = color;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
