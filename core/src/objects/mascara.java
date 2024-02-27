package objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Scrollable;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

import utils.Methods;
import utils.Settings;
import helpers.AssetManager;



public class mascara extends Scrollable {
    private float runTime;
    private Circle collisionCircle;
    private float timer;
    private boolean direction;
    private float velocity;
    private static Float initialSize = null;


    public mascara(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);
        this.velocity = velocity;
        runTime = Methods.randomFloat(0,1);
        collisionCircle = new Circle();
        timer = 0;
        direction = true;
        if (initialSize == null) {
            // Si no se ha establecido, lo establece al tamaño del objeto mascara actual
            initialSize = height;
        }

    }
    @Override
    public void act(float delta) {
        super.act(delta);
        runTime += delta;
        timer += delta;

        collisionCircle.set(position.x + width / 2.0f, position.y + width / 2.0f, width / 2.0f);
    }
    public void reset(float newX) {
        super.reset(newX);
        if (initialSize != null) {
            this.height = initialSize;
            this.width = initialSize;
        }
        position.y = Settings.SAMBA_STARTY+10;
    }
    public float getRunTime() {

        return runTime;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.mascara_malvada, position.x, position.y, width, height);
    }
    public boolean collides(samba smb){
        if (position.x <= smb.getX() + smb.getWidth()) {
            return (Intersector.overlaps(collisionCircle, smb.getCollisionRect()));
        }
        return false;
    }
    public Circle getCollisionCircle() {
        return collisionCircle;
    }


}
