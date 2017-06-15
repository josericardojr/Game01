package com.josericardojunior.game01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by josericardo on 12/06/17.
 */

public abstract class GameEntity {

    private float speed = 8;
    protected int x, y;
    Rect collisionBox = new Rect();

    public Rect getCollisionBox() {
        return collisionBox;
    }


    public  GameEntity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void update(float elapsedTime){

        collisionBox.left = x;
        collisionBox.top = y;
        collisionBox.right = x + getWidth();
        collisionBox.bottom = y + getHeight();
    }

    public boolean Collided(Rect other){
        return Rect.intersects(collisionBox, other);
    }

    public abstract int getWidth();
    public abstract int getHeight();
}
