package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.Random;

import objects.mascara;
import objects.Background;
import objects.samba;
import utils.Methods;
import utils.Settings;

public class ScrollHandler extends Group {

    // Fons de pantalla
    Background bg, bg_back;

    // Asteroides
    int numAsteroids;
    ArrayList<mascara> mascaras;

    // Objecte Random
    Random r;

    public ScrollHandler() {

        // Creem els dos fons
        bg = new Background(0, 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);
        bg_back = new Background(bg.getTailX(), 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);

        // Afegim els fons al grup
        addActor(bg);
        addActor(bg_back);

        // Creem l'objecte random
        r = new Random();

        // Comencem amb 3 asteroides
        numAsteroids = 3;

        // Creem l'ArrayList
        mascaras = new ArrayList<mascara>();

        // Definim una mida aleatòria entre el mínim i el màxim
        float newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34;

        // Afegim el primer asteroide a l'array i al grup
        mascara mascara = new mascara(Settings.GAME_WIDTH, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.ASTEROID_SPEED);
        mascaras.add(mascara);
        addActor(mascara);

        // Des del segon fins l'últim asteroide
        for (int i = 1; i < numAsteroids; i++) {
            // Creem la mida aleatòria
            newSize = Methods.randomFloat(Settings.MIN_ASTEROID, Settings.MAX_ASTEROID) * 34;
            // Afegim l'asteroide
            mascara = new mascara(mascaras.get(mascaras.size() - 1).getTailX() + Settings.ASTEROID_GAP, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.ASTEROID_SPEED);
            // Afegim l'asteroide a l'ArrayList
            mascaras.add(mascara);
            // Afegim l'asteroide al grup d'actors
            addActor(mascara);
        }

    }
    public boolean collides(samba nau){
        for(mascara mascara : mascaras){
            if(mascara.collides(nau)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Si algun element es troba fora de la pantalla, fem un reset de l'element
        if (bg.isLeftOfScreen()) {
            bg.reset(bg_back.getTailX());

        } else if (bg_back.isLeftOfScreen()) {
            bg_back.reset(bg.getTailX());

        }

        for (int i = 0; i < mascaras.size(); i++) {

            mascara mascara = mascaras.get(i);
            if (mascara.isLeftOfScreen()) {
                if (i == 0) {
                    mascara.reset(mascaras.get(mascaras.size() - 1).getTailX() + Settings.ASTEROID_GAP);
                } else {
                    mascara.reset(mascaras.get(i - 1).getTailX() + Settings.ASTEROID_GAP);
                }
            }
        }
    }

    public ArrayList<mascara> getAsteroids() {
        return mascaras;
    }
}