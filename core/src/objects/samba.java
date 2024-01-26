package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;
import helpers.AssetManager;
import utils.Settings;


public class samba extends Actor {

    public static final int SPACECRAFT_STRAIGHT = 0;
    public static final int SPACECRAFT_UP = 1;
    public static final int SPACECRAFT_DOWN = 2;

    private Vector2 position;
    private int width, height;
    private int direction;
    private Rectangle collisionRect;


    public samba(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        direction = SPACECRAFT_STRAIGHT;
        collisionRect = new Rectangle();

    }

    public void act(float delta) {
        switch(direction){
            case SPACECRAFT_UP:
                if (this.position.y - Settings.SAMBA_VELOCITY * delta >= 0) {
                    this.position.y -= Settings.SAMBA_VELOCITY * delta;
                }
                break;
            case SPACECRAFT_DOWN:
                if (this.position.y + height + Settings.SAMBA_VELOCITY * delta <= Settings.GAME_HEIGHT) {
                    this.position.y += Settings.SAMBA_VELOCITY * delta;
                }
                break;
            case SPACECRAFT_STRAIGHT:
                break;
        }
        collisionRect.set(position.x, position.y + 3, width, 10);

    }
    // Getters dels atributs principals
    public float getX() {
        return position.x;
    }


    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // Canviem la direcció de l'samba: Puja
    public void goUp() {
        direction = SPACECRAFT_UP;
    }

    // Canviem la direcció de l'samba: Baixa
    public void goDown() {
        direction = SPACECRAFT_DOWN;
    }

    // Posem l'samba al seu estat original
    public void goStraight() {
        direction = SPACECRAFT_STRAIGHT;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getSpacecraftTexture(), position.x, position.y, width, height);
    }
    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    public Texture getSpacecraftTexture() {
        return AssetManager.samba;
    }


}
