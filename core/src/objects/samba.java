package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    public static final int JUMP_VELOCITY = 250;
    private boolean isJumping;
    private int jumpCount;

    private Vector2 position;
    private Vector2 velocity;
    private int width, height;
    private int direction;
    private Rectangle collisionRect;
    private Rectangle attackRect;
    private float attackTime;
    public boolean isAttacking;
    private int attackDirection;
    private int lastDirection;
    private float animationTime;





    public samba(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        direction = SAMBA_STILL;
        collisionRect = new Rectangle();

        isJumping = false;
        jumpCount = 0;

        isAttacking = false;
        attackRect = new Rectangle(); // Inicializa el rectángulo de ataque
        lastDirection = SAMBA_STILL; // Inicializa la última dirección como SAMBA_STILL
        animationTime = 0;




    }

    public void attack() {
        if (!isAttacking) {
            isAttacking = true;
            attackTime = 0.5f;
            attackDirection = lastDirection; // Almacena la dirección en el momento del ataque

            AssetManager.attack.play();

            // Configura el rectángulo de ataque para que se extienda desde samba en la dirección en la que se está moviendo
            if (attackDirection == SAMBA_LEFT) {
                attackRect.set(position.x - width, position.y, width * 2, height);
            } else if (attackDirection == SAMBA_RIGHT) {
                attackRect.set(position.x + width, position.y, width * 2, height);
            } else {
                // Si samba está quieto, el rectángulo de ataque se extiende hacia arriba
                attackRect.set(position.x, position.y + height, width, height * 2);
            }
        }
    }

    public void jump() {
        if (!isJumping || jumpCount < 2) {
            isJumping = true;
            jumpCount++;
            velocity.y = JUMP_VELOCITY;
        }
    }
    @Override
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

        if (isAttacking) {
            attackTime -= delta;
            if (attackTime <= 0) {
                isAttacking = false;
                // Restablece el rectángulo de ataque después del ataque
                attackRect.set(0, 0, 0, 0);
            } else {
                // Extiende el rectángulo de ataque durante el ataque
                if (attackDirection == SAMBA_LEFT) {
                    attackRect.set(position.x - width, position.y, width * 2, height);
                } else if (attackDirection == SAMBA_RIGHT) {
                    attackRect.set(position.x + width, position.y, width * 2, height);
                } else {
                    attackRect.set(position.x, position.y + height, width, height * 2);
                }
            }
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

        // Update the attack hitbox
        collisionRect.set(position.x, position.y + 3, width, 10);
        animationTime += delta;

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
        lastDirection = SAMBA_LEFT; // Actualiza la última dirección

    }

    // Canviem la direcció de l'samba: Derecha
    public void goRight() {
        direction = SAMBA_RIGHT;
        lastDirection = SAMBA_RIGHT; // Actualiza la última dirección

    }

    // Posem l'samba al seu estat original
    public void stayStill() {
        direction = SAMBA_STILL;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // Determine which animation to display
        Animation currentAnimation;
        if (isAttacking) {
            if (lastDirection == SAMBA_LEFT) {
                currentAnimation = AssetManager.sambaAttackLeftAnimation;
            } else {
                currentAnimation = AssetManager.sambaAttackRightAnimation;
            }
        } else if (isJumping) {
            if (lastDirection == SAMBA_LEFT) {
                currentAnimation = AssetManager.sambaJumpLeftAnimation;
            } else {
                currentAnimation = AssetManager.sambaJumpRightAnimation;
            }
        } else if (direction == SAMBA_LEFT) {
            currentAnimation = AssetManager.sambaLeftAnimation;
        } else if (direction == SAMBA_RIGHT) {
            currentAnimation = AssetManager.sambaRightAnimation;
        } else {
            currentAnimation = AssetManager.sambaRightAnimation;
        }

        // Draw the current frame of the animation
        batch.draw((TextureRegion) currentAnimation.getKeyFrame(animationTime), position.x, position.y, collisionRect.width, collisionRect.height);
    }
    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    public Texture getSambaTexture() {
        return AssetManager.samba;
    }

    public boolean isAttacking() {
        return isAttacking;
    }
    public Rectangle getAttackRect() {
        return attackRect;
    }


}
