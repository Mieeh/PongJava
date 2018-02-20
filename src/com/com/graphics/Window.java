package com.com.graphics;

import com.input.Input;
import com.math.Vector3;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import java.nio.IntBuffer;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class Window {

    private long window;

    // Window shake
    private boolean doShakeWindow;
    private int shakeStrength;
    private int timeToShake;
    private int[] startX, startY;
    private Random random;

    public Window(int _width, int _height){

        // Non-glfw
        startX = new int[1];
        startY = new int[1];
        random = new Random();

        if(!GLFW.glfwInit()){
            System.out.println("Failed to initalize GLFW Window!");
        }

        // Create GLFW window
        window = GLFW.glfwCreateWindow(_width, _height, "Pong!", 0, 0);
        GLFW.glfwMakeContextCurrent(window);

        GL.createCapabilities(); // Important for LWJGL, it detects the newly created context
        GLFW.glfwSwapInterval(-1); // This enables vSync

        // Callbacks
        // Window resize callback w/lambda
        GLFW.glfwSetWindowSizeCallback(window, (window, width, height) -> {
            glViewport(0,0,width,height);
        });
        // Input callback from the Input::GLFWKeyCallback class
        GLFW.glfwSetKeyCallback(window, new Input());

        GLFW.glfwShowWindow(window);
    }

    public void close(){
        // Close the GLFW window
        GLFW.glfwSetWindowShouldClose(window, true);

        // Exit GLFW
        //GLFW.glfwFreeCallbacks(window); // Is this needed?
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public boolean isOpen(){
        return !GLFW.glfwWindowShouldClose(window);
    }

    public void clear(){
        // Clear the framebuffer & poll events for callbacks
        glClear(GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();

        // Window shake logic
        if(doShakeWindow){

            // Actual shake logic

            int _x = startX[0] + (random.nextInt(shakeStrength*2) - shakeStrength);
            int _y = startY[0] + (random.nextInt(shakeStrength*2) - shakeStrength);
            GLFW.glfwSetWindowPos(window, _x, _y);

            // Exit logic
            timeToShake -= 1; // 1 frame
            if(timeToShake < 0){
                doShakeWindow = false;
            }
        }
    }

    public void display(){
        GLFW.glfwSwapBuffers(window);
    }

    public void setClearColor(Vector3 clearColor){
        glClearColor(clearColor.x, clearColor.y, clearColor.z, 0.0f);
    }

    // Strength is pixel-shake-diameter (what)
    // Length is in frames
    public void shakeWindow(int strength, int length){
        shakeStrength = strength;
        timeToShake = length;
        doShakeWindow = true;

        GLFW.glfwGetWindowPos(window, startX, startY);
    }

}
