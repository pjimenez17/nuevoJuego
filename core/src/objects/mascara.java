package objects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.Scrollable;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

import utils.Methods;
import utils.Settings;
import helpers.AssetManager;



public class mascara extends Scrollable {
    private float runTime;
    private Circle collisionCircle;
    public mascara(float x, float y, float width, float height, float velocity) {
        super(x, y, width, height, velocity);
        runTime = Methods.randomFloat(0,1);
        collisionCircle = new Circle();

    }
    @Override
    public void act(float delta) {
        super.act(delta);
        runTime += delta;
        collisionCircle.set(position.x + width / 2.0f, position.y + width / 2.0f, width / 2.0f);
    }
    public void reset(float newX) {
        super.reset(newX);
        float newSize = Methods.randomFloat(Settings.MIN_MASCARA, Settings.MAX_MASCARA);
        width = height = 20 * newSize;
        position.y = new Random().nextInt((int) (Settings.GAME_HEIGHT - (int)height));
    }
    public float getRunTime() {

        return runTime;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.mascara_malvada, position.x, position.y, width, height);
    }
    public boolean collides(samba nau){
        if (position.x <= nau.getX() + nau.getWidth()) {
            return (Intersector.overlaps(collisionCircle, nau.getCollisionRect()));
        }
        return false;
    }

}
