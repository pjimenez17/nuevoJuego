package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;


public class Scrollable extends Actor {
    protected Vector2 position;
    protected float veloctiy;
    protected float width;
    protected float height;
    protected boolean leftofScreen;

    public Scrollable(float x, float y, float width, float height, float veloctiy) {
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.veloctiy = veloctiy;
        leftofScreen = false;
    }

    public void reset(float newX) {
        position.x = newX;
        leftofScreen = false;
    }

    public void act(float delta) {
        position.x += veloctiy * delta;
        if (position.x + width < 0) {
            leftofScreen = true;
        }
    }
    public boolean isLeftOfScreen() {
        return leftofScreen;
    }

    public float getTailX() {
        return position.x + width;
    }

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


}
