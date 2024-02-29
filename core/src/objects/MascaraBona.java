package objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Scrollable;
import helpers.AssetManager;
import utils.Methods;
import utils.Settings;

public class MascaraBona extends Scrollable {
    private float runTime;
    private Rectangle collisionRect;
    private ShapeRenderer shapeRenderer;
    private static Float initialSize = null;


    public MascaraBona(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);
        runTime = Methods.randomFloat(0,1);
        collisionRect = new Rectangle();
        shapeRenderer = new ShapeRenderer();
        if (initialSize == null) {
            // Si no se ha establecido, lo establece al tamaño del objeto MascaraBona actual
            initialSize = height;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        runTime += delta;

        collisionRect.set(position.x, position.y, width, height); // Update the Rectangle
    }

    public void reset(float newX) {
        super.reset(newX);
        // Usa initialSize para establecer el tamaño del objeto MascaraBona
        if (initialSize != null) {
            this.height = initialSize;
            this.width = initialSize;
        }

        position.y = Settings.SAMBA_STARTY;
        position.x = Settings.GAME_WIDTH;
    }

    public float getRunTime() {
        return runTime;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.vampire, position.x, position.y, width, height);


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