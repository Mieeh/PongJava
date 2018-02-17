package com.com.graphics;

import com.math.Vector3;

public class Rectangle extends GraphicsObject {

    private VertexArrayObject vao;
    byte indicies[];

    public void render(){
        float vertices[] = new float[]{
                position.x, position.y, 0,                  color.x, color.y, color.z,
                position.x + + width, position.y, 0,        color.x, color.y, color.z,
                position.x + width, position.y + height, 0, color.x, color.y, color.z,
                position.x, position.y + height, 0,         color.x, color.y, color.z,
        };
        vao.updateBufferData(vertices);
        vao.render();
    }

    public Rectangle(float _width , float _height, Vector3 _color){
        super(_width, _height, _color);

        float vertices[] = new float[]{
            position.x, position.y, 0,                  color.x, color.y, color.z,
            position.x + + width, position.y, 0,        color.x, color.y, color.z,
            position.x + width, position.y + height, 0, color.x, color.y, color.z,
            position.x, position.y + height, 0,         color.x, color.y, color.z,
        };

        indicies = new byte[]{
                0, 1, 2,
                2, 3, 0
        };

        vao = new VertexArrayObject(vertices, indicies);

    }

}
