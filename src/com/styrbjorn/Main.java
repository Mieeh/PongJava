package com.styrbjorn;

import com.com.graphics.VertexArrayObject;
import com.math.Matrix4f;
import com.utility.BufferUtility;
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

// com.com what?
import com.com.graphics.Shader;

public class Main {

    // The window handle
    private long window;

    static int WIDTH = 750;
    static int HEIGHT = 600;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        // Enable v-sync
        glfwSwapInterval(1);

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
        // Window resize callback
        glfwSetWindowSizeCallback(window, (window, width, height) -> {
            glViewport(0,0,width,height);
        });

        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop() {
        // Set the clear color
        glClearColor(0.1f, 0.1f, 0.1f, 0.0f);

        Shader shader = new Shader("D:\\LearningJavaFun\\ljgwl_2\\shaders\\vertex.txt", "D:\\LearningJavaFun\\ljgwl_2\\shaders\\fragment.txt");
        shader.enable();
        shader.setUniformMat4f("projection_matrix", Matrix4f.orthographic(0, WIDTH, HEIGHT, 0, -1, 1));

        float vertex2[] = new float[]{
            0, 0, 0,        1, 0, 0,
            0, 300, 0,      0, 0, 1,
            300, 300, 0,    0, 1, 0
        };
        float vertex[] = new float[]{
                100, 100, 0,        1, 0, 0,
                100, 400, 0,        0, 0, 1,
                400, 400, 0,        0, 1, 0
        };

        // Test w/VertexArrayObject
        VertexArrayObject vao = new VertexArrayObject(vertex);
        //vao.updateBufferData(vertex2);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            shader.enable();
            vao.render();
            shader.disable();

            glfwSwapBuffers(window); // swap the color buffers
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

}