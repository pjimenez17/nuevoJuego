package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;

import helpers.AssetManager;
import utils.Settings;


public class samba extends Actor {

    public static final int SAMBA_STILL = 0;
    public static final int SAMBA_LEFT = 1;
    public static final int SAMBA_RIGHT = 2;

    public static final int SAMBA_ATTACK = 3;

    public static final int GRAVITY = -10;
    public static final int JUMP_VELOCITY = 350;
    private boolean isJumping;
    private int jumpCount;

    private Vector2 position;
    private Vector2 velocity;
    private int width, height;
    private int direction;
    private Rectangle collisionRect;


    public samba(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        direction = SAMBA_STILL;
        collisionRect = new Rectangle();

        isJumping = false;
        jumpCount = 0;

    }



    public void jump() {
        if (!isJumping || jumpCount < 2) {
            isJumping = true;
            jumpCount++;
            velocity.y = JUMP_VELOCITY;
        }
    }
    public void act(float delta) {
        switch(direction){
            case SAMBA_LEFT:
                if (this.position.x - Settings.SAMBA_VELOCITY * delta >= 0) {
                    this.position.x -= Settings.SAMBA_VELOCITY * delta;
                }
                break;
            case SAMBA_RIGHT:
                if (this.position.x + width + Settings.SAMBA_VELOCITY * delta <= Settings.GAME_WIDTH) {
                    this.position.x += Settings.SAMBA_VELOCITY * delta;
                }
                break;
            case SAMBA_STILL:
                break;
        }
        if (isJumping) {
            velocity.y += GRAVITY;
            position.y += velocity.y * delta;

            if (position.y <= Settings.SAMBA_STARTY) {
                isJumping = false;
                jumpCount = 0;
                velocity.y = 0;
            }
        }
        // Si Samba está por debajo de SAMBA_STARTY, aumenta su posición
        // gradualmente hasta que llegue a SAMBA_STARTY
        if (position.y < Settings.SAMBA_STARTY) {
            position.y += 5 * delta;
            if (position.y > Settings.SAMBA_STARTY) {
                position.y = Settings.SAMBA_STARTY;
            }
        }
        collisionRect.set(position.x, position.y + 3, width, 10);
    }
    // Getters dels atributs principals
    public float getX() {
        return position.x;
    }

    public boolean overlaps(Rectangle rectangle, Circle circle) {
        float closestX = Math.max(rectangle.x, Math.min(circle.x, rectangle.x + rectangle.width));
        float closestY = Math.max(rectangle.y, Math.min(circle.y, rectangle.y + rectangle.height));

        float distanceX = circle.x - closestX;
        float distanceY = circle.y - closestY;

        return (distanceX * distanceX + distanceY * distanceY) <= (circle.radius * circle.radius);
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
    public void goLeft() {
        direction = SAMBA_LEFT;
    }

    // Canviem la direcció de l'samba: Derecha
    public void goRight() {
        direction = SAMBA_RIGHT;
    }

    // Posem l'samba al seu estat original
    public void stayStill() {
        direction = SAMBA_STILL;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getSambaTexture(), position.x, position.y, width, height);
    }
    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    public Texture getSambaTexture() {
        return AssetManager.samba;
    }


}
