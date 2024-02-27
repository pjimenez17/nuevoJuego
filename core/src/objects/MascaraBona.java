package objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Scrollable;
import helpers.AssetManager;
import utils.Methods;
import utils.Settings;

import java.util.Random;

public class MascaraBona extends Scrollable {
    private float runTime;
    private Rectangle collisionRect;
    private ShapeRenderer shapeRenderer;

    public MascaraBona(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);
        runTime = Methods.randomFloat(0,1);
        collisionRect = new Rectangle();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        runTime += delta;
        float hitboxHeight = height - 300; // Subtract 10 from the height
        float hitboxY = position.y + 10; // Add 10 to the y position of the hitbox
        collisionRect.set(position.x, hitboxY, width, hitboxHeight); // Update the Rectangle
    }

    public void reset(float newX) {
        super.reset(newX);
        width = 20; // Establece el ancho a un valor fijo
        height = width * 2; // Hace que el alto sea el doble del ancho
        position.y = Settings.SAMBA_STARTY;
        position.x = Settings.GAME_WIDTH;
    }

    public float getRunTime() {
        return runTime;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.mascara_bona, position.x, position.y, width, height);


    }

    public boolean collides(samba smb){
        if (position.x <= smb.getX() + smb.getWidth()) {
            return (Intersector.overlaps(collisionRect, smb.getCollisionRect()));
        }
        return false;
    }

    public Rectangle getCollisionRect() {
        return collisionRect;
    }
}