package helpers;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import objects.samba;
import screens.GameScreen;


public class InputHandler implements InputProcessor{
    private samba samba;
    private GameScreen screen;

    int previousX = 0;

    public InputHandler(GameScreen screen) {
        this.screen = screen;
        samba = screen.getSamba();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                samba.jump();
                break;
            case Input.Keys.A:
                samba.goLeft();
                break;
            case Input.Keys.S:
                // Aquí puedes agregar la lógica para mover a Samba hacia abajo
                break;
            case Input.Keys.D:
                samba.goRight();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A || keycode == Input.Keys.D) {
            samba.stayStill();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        previousX = screenX;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        samba.stayStill();
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(Math.abs(previousX - screenX) > 2) {
            if(previousX > screenX) { // Si arrastras hacia la izquierda
                samba.goLeft();
            } else {
                samba.goRight();
            }
            previousX = screenX;
            return true;
        }
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (amountY < 0) { // Scroll hacia arriba
            samba.jump();
        }
        return false;
    }
}
