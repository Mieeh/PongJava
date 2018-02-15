package com.com.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import com.utility.FileUtility;
import com.math.*;

public class Shader {

    // Program ID
    private int id;

    // layout(location = n) in type attrib_name
    public static final int VERTEX_LOCATION = 0;
    public static final int COLOR_LOCATION = 1;

    // Constructor
    public Shader(String vertexPath, String fragmentPath){

        // Create program and compile shaders
        id = glCreateProgram();
        int vertex = glCreateShader(GL_VERTEX_SHADER);
        int fragment = glCreateShader(GL_FRAGMENT_SHADER);

        String vertexSource = FileUtility.loadFileAsString(vertexPath);
        String fragmentSource = FileUtility.loadFileAsString(fragmentPath);

        glShaderSource(vertex, vertexSource);
        glShaderSource(fragment, fragmentSource);

        // Compile vertex shader
        glCompileShader(vertex);
        if(glGetShaderi(vertex, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Vertex shader did not compile!");
            System.err.println(glGetShaderInfoLog(vertex));
        }

        // Compile fragment shader
        glCompileShader(fragment);
        if(glGetShaderi(fragment, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Vertex shader did not compile!");
            System.err.println(glGetShaderInfoLog(fragment));
        }

        // Link shaders to the shader program
        glAttachShader(id, vertex);
        glAttachShader(id, fragment);
        glLinkProgram(id);
        glValidateProgram(id);

        // Delete the individual shaders now, we do not need them
        glDeleteShader(vertex);
        glDeleteShader(fragment);
    }

    private int getUniformLocation(String name){
        return glGetUniformLocation(id, name);
    }

    public void setUniform3f(String name, Vector3 vector3){
        glUniform3f(getUniformLocation(name), vector3.x, vector3.y, vector3.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix4f){
        glUniformMatrix4fv(getUniformLocation(name), false, matrix4f.toFloatBuffer());
    }

    public void enable() {
        glUseProgram(id);
    }

    public void disable() {
        glUseProgram(0);
    }

}
