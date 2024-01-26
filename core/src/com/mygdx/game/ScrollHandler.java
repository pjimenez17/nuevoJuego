package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.Random;

import objects.MascaraBona;
import objects.mascara;
import objects.Background;
import objects.samba;
import utils.Methods;
import utils.Settings;

public class ScrollHandler extends Group {

    // Fons de pantalla
    Background bg, bg_back;

    // Asteroides
    int numMascaras, numMascaresBona;
    ArrayList<mascara> mascaras;
    ArrayList<MascaraBona> mascarasBona;


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

        numMascaras = 3;
        numMascaresBona = 3;

        // Creem l'ArrayList
        mascaras = new ArrayList<mascara>();
        mascarasBona = new ArrayList<>();

        for (int i = 0; i < numMascaresBona; i++) {
            float newSize = Methods.randomFloat(Settings.MIN_MASCARA, Settings.MAX_MASCARA) * 34;
            MascaraBona mascaraBona = new MascaraBona(Settings.GAME_WIDTH, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.MASCARA_SPEED);
            mascarasBona.add(mascaraBona);
            addActor(mascaraBona);
        }


        // Definim una mida aleatòria entre el mínim i el màxim
        float newSize = Methods.randomFloat(Settings.MIN_MASCARA, Settings.MAX_MASCARA) * 34;

        mascara mascara = new mascara(Settings.GAME_WIDTH, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.MASCARA_SPEED);
        mascaras.add(mascara);
        addActor(mascara);



        for (int i = 1; i < numMascaras; i++) {
            // Creem la mida aleatòria
            newSize = Methods.randomFloat(Settings.MIN_MASCARA, Settings.MAX_MASCARA) * 34;
            mascara = new mascara(mascaras.get(mascaras.size() - 1).getTailX() + Settings.MASCARA_GAP, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.MASCARA_SPEED);
            mascaras.add(mascara);
            addActor(mascara);
        }
        for (int i = 1; i < numMascaresBona; i++) {
            // Creem la mida aleatòria
            newSize = Methods.randomFloat(Settings.MIN_MASCARA, Settings.MAX_MASCARA) * 34;
            MascaraBona mascaraBona = new MascaraBona(mascarasBona.get(mascarasBona.size() - 1).getTailX() + Settings.MASCARA_GAP, r.nextInt(Settings.GAME_HEIGHT - (int) newSize), newSize, newSize, Settings.MASCARA_SPEED);
            mascarasBona.add(mascaraBona);
            addActor(mascaraBona);
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
                    mascara.reset(mascaras.get(mascaras.size() - 1).getTailX() + Settings.MASCARA_GAP);
                } else {
                    mascara.reset(mascaras.get(i - 1).getTailX() + Settings.MASCARA_GAP);
                }
            }
        }
        for (int i = 0; i < mascarasBona.size(); i++) {
            MascaraBona mascaraBona = mascarasBona.get(i);
            if (mascaraBona.isLeftOfScreen()) {
                if (i == 0) {
                    mascaraBona.reset(mascarasBona.get(mascarasBona.size() - 1).getTailX() + Settings.MASCARA_GAP);
                } else {
                    mascaraBona.reset(mascarasBona.get(i - 1).getTailX() + Settings.MASCARA_GAP);
                }
            }
        }
    }

    public ArrayList<mascara> getMascaras() {
        return mascaras;
    }
    public ArrayList<MascaraBona> getMascarasBona() {
        return mascarasBona;
    }
}