package ru.quarantine.escape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ModelInfluencer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class OrthographicCameraController implements InputProcessor, GestureDetector.GestureListener {
    private static final String TAG = OrthographicCameraController.class.getName();

    final PerspectiveCamera camera;
    final ModelInstance modelInstance;

    /**
     * Enables logging. Setting this to false will override all options below.
     */
    boolean logActivity = true;
    /**
     * Log verbose activities, such as moving the mouse. These tend to generate a lot of log entries.
     */
    boolean logVerboseActivity = false;
    /**
     * Log all activity, overriding all options below.
     */
    boolean logAllActivity = true;
    /**
     * Log activity from InputProcessor
     */
    boolean logInputProcessorActivity = false;
    /**
     * Log activity from GestureListener.
     */
    boolean logGestureListenerActivity = true;

    public OrthographicCameraController(PerspectiveCamera camera, ModelInstance modelInstance) {
        this.camera = camera;
        this.modelInstance = modelInstance;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (logActivity && (logAllActivity || logGestureListenerActivity)) {
            Gdx.app.log(TAG, "fling triggered. velocityX " + velocityX + " velocityY " + velocityY + " button " + button);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (logActivity && (logAllActivity || logInputProcessorActivity)) {
            Gdx.app.log(TAG, "keyDown triggered. keycode " + keycode + " (" + Input.Keys.toString(keycode) + ")");
        }



        switch (keycode){
            case 22:
                camera.direction.z += 1;
                break;
            case 21:
                camera.direction.z -= 1;
                break;
            case 20:
                camera.direction.y -= 1;
                break;
            case 19:
                camera.direction.y += 1;
                break;
            case 45:
                camera.direction.x -= 1;
                break;
            case 33:
                camera.direction.x += 1;
                break;
            case 51:
                camera.position.z +=1;
                break;
            case 47:
                camera.position.z -= 1;
                break;
            case 29:
                camera.position.x -= 1;
                break;
            case 32:
                camera.position.x += 1;
                break;

            case 46:
                modelInstance.transform.rotate(1, 0, 0, 10);
                break;
            case 34:
                modelInstance.transform.rotate(-1, 0, 0, 10);
                break;
            case 48:
                modelInstance.transform.rotate(0, 1, 0, 10);
                break;
            case 35:
                modelInstance.transform.rotate(0, -1, 0, 10);
                break;
            case 53:
                modelInstance.transform.rotate(0, 0, 1, 10);
                break;
            case 36:
                modelInstance.transform.rotate(0, 0, -1, 10);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (logActivity && (logAllActivity || logInputProcessorActivity)) {
            Gdx.app.log(TAG, "keyTyped triggered. keycode " + character);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (logActivity && (logAllActivity || logInputProcessorActivity)) {
            Gdx.app.log(TAG, "keyUp triggered. keycode " + keycode + " (" + Input.Keys.toString(keycode) + ")");
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        if (logActivity && (logAllActivity || logGestureListenerActivity)) {
            Gdx.app.log(TAG, "longPress triggered. x " + x + " y " + y);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (logActivity && (logAllActivity || logInputProcessorActivity) && logVerboseActivity) {
            Gdx.app.log(TAG, "mouseMoved triggered. screenX " + screenX + " screenY " + screenY);
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (logActivity && (logAllActivity || logGestureListenerActivity)) {
            Gdx.app.log(TAG, "pan triggered. x " + x + " y " + y + " deltaX " + deltaX + " deltaY " + deltaY);
        }
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if (logActivity && (logAllActivity || logGestureListenerActivity)) {
            Gdx.app.log(TAG, "panStop triggered. x " + x + " y " + y + " pointer " + pointer + " button " + button);
        }
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        if (logActivity && (logAllActivity || logGestureListenerActivity)) {
            Gdx.app.log(TAG, "pinch triggered. initialPointer1 " + initialPointer1 + " initialPointer2 " + initialPointer2 + " pointer1 " + pointer1 + " pointer2 " + pointer2);
        }
        return false;
    }

    @Override
    public void pinchStop() {

    }

    @Override
    public boolean scrolled(int amount) {
        if (logActivity && (logAllActivity || logInputProcessorActivity)) {
            Gdx.app.log(TAG, "scrolled triggered. amount " + amount);
        }
        camera.position.y += amount;
        return false;
    }
    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (logActivity && (logAllActivity || logGestureListenerActivity)) {
            Gdx.app.log(TAG, "tap triggered. x " + x + " y " + y + " count " + count + " button " + button);
        }
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if (logActivity && (logAllActivity || logGestureListenerActivity)) {
            Gdx.app.log(TAG, "touchDown (Gesture) triggered. x " + x + " y " + y + " pointer " + pointer + " button " + button);
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (logActivity && (logAllActivity || logInputProcessorActivity)) {
            Gdx.app.log(TAG, "touchDown (Input) triggered. screenX " + screenX + " screenY " + screenY + " pointer " + pointer + " button " + button);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (logActivity && (logAllActivity || logInputProcessorActivity)) {
            Gdx.app.log(TAG, "touchDragged triggered. screenX " + screenX + " screenY " + screenY + " pointer " + pointer);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (logActivity && (logAllActivity || logInputProcessorActivity)) {
            Gdx.app.log(TAG, "touchUp triggered. screenX " + screenX + " screenY " + screenY + " pointer " + pointer + " button " + button);
        }
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (logActivity && (logAllActivity || logGestureListenerActivity)) {
            Gdx.app.log(TAG, "zoom triggered. initialDistance " + initialDistance + " distance " + distance);
        }
        return false;
    }
}
