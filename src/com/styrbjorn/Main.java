package com.styrbjorn;

import com.entities.Ball;
import com.entities.Paddle;
import com.math.Matrix4f;
import com.math.Vector3;
import com.input.Input;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import com.com.graphics.Shader;

import com.com.graphics.Window;

public class Main {

    // The window handle
    //private long window;
    Window window;


    public static final int WIDTH = 750;
    public static final int HEIGHT = 600;

    private void run() {
        init();
        loop();
    }

    private void init() {
        window = new Window(WIDTH, HEIGHT);
        window.setClearColor(new Vector3(0.25f, 0.1f, 0.1f));
    }

    private void loop() {

        // Create shader
        Shader shader = new Shader("D:\\LearningJavaFun\\ljgwl_2\\shaders\\vertex.txt", "D:\\LearningJavaFun\\ljgwl_2\\shaders\\fragment.txt");
        shader.enable();
        shader.setUniformMat4f("projection_matrix", Matrix4f.orthographic(0, WIDTH, HEIGHT, 0, -1, 1));

        float y = (HEIGHT/2)-(Paddle.HEIGHT/2);
        Paddle leftPaddle = new Paddle(new Vector3(20,y,0), GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_S);
        Paddle rightPaddle = new Paddle(new Vector3(WIDTH - Paddle.WIDTH - 20, y, 0), GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_DOWN);

        Ball ball = new Ball(leftPaddle, rightPaddle);

        // Run the rendering loop until the user has attempted to close
        // the window or has prkessed the ESCAPE key.
        while ( window.isOpen() ) {
            window.clear();

            // Update entities
            leftPaddle.update();
            rightPaddle.update();
            ball.update();

            // Ball OOB logic
            if(ball.getShape().getPosition().x > WIDTH || ball.getShape().getPosition().x < -Ball.SIZE){
                ball.reset();
                window.shakeWindow(10, 20);
            }

            // Render start
            shader.enable();

            leftPaddle.render();
            rightPaddle.render();
            ball.render();

            // Render stop
            shader.disable();

            window.display();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

}