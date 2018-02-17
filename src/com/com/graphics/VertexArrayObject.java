package com.com.graphics;

import com.utility.BufferUtility;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArrayObject {

    private int vao, vbo, ibo;
    private int count;

    public VertexArrayObject(float[] vertices, byte[] indices){
        count = 6;

        // 4 bytes in a float
        int floatByteSize = 4;

        // Create opengl VAO & VBO and fill the VBO with vertices
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtility.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.VERTEX_LOCATION, 3, GL_FLOAT, false, 6*floatByteSize, 0);
        glVertexAttribPointer(Shader.COLOR_LOCATION, 3, GL_FLOAT, false, 6*floatByteSize, 3*floatByteSize);
        glEnableVertexAttribArray(Shader.VERTEX_LOCATION);
        glEnableVertexAttribArray(Shader.COLOR_LOCATION);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtility.createByteBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void updateBufferData(float[] vertices){
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtility.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void bind(){
        glBindVertexArray(vao);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    }

    public void unbind(){
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void drawCall(){
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
    }

    public void render(){
        bind();
        drawCall();
        unbind();
    }

}
