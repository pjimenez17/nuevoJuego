package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import objects.MascaraBona;
import objects.mascara;
import objects.Background;
import objects.samba;
import screens.GameScreen;
import utils.Methods;
import utils.Settings;

import java.util.ArrayList;
import java.util.Random;

public class ScrollHandler extends Group {

    // Fons de pantalla
    Background bg, bg_back;

    int numMascaras, numMascaresBona;
    ArrayList<mascara> mascaras;
    ArrayList<MascaraBona> mascarasBona;


    // Objecte Random
    Random r;
    private float timeSinceLastSpawn;


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
        float newSize = 30;

        // Creem l'ArrayList
        mascaras = new ArrayList<mascara>();
        mascarasBona = new ArrayList<>();

        // Creación de las mascaras especiales
        for (int i = 0; i < numMascaresBona; i++) {
            float xPos = Settings.GAME_WIDTH + r.nextInt(Settings.GAME_WIDTH); // Posición aleatoria en el borde derecho de la pantalla
            float yPos = Settings.SAMBA_STARTY; // Altura fija
            MascaraBona mascaraBona = new MascaraBona(xPos, yPos, newSize, newSize, Settings.MASCARA_SPEED);
            mascarasBona.add(mascaraBona);
            addActor(mascaraBona);
        }

        for (int i = 0; i < numMascaras; i++) {
            float xPos = Settings.GAME_WIDTH + r.nextInt(Settings.GAME_WIDTH); // Posición aleatoria en el borde derecho de la pantalla
            float yPos = Settings.SAMBA_STARTY; // Altura fija
            mascara mascara = new mascara(xPos, yPos, newSize, newSize, Settings.MASCARA_SPEED);
            mascaras.add(mascara);
            addActor(mascara);
        }
    }

    public boolean collides(samba smb){
        for(mascara mascara : mascaras){
            if(mascara.collides(smb)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);



        if (bg.isLeftOfScreen()) {
            bg.reset(bg_back.getTailX());
        } else if (bg_back.isLeftOfScreen()) {
            bg_back.reset(bg.getTailX());
        }

        for (mascara mascara : mascaras) {
            if (mascara.isLeftOfScreen()) {
                // Restablece la posición x a un valor aleatorio en el lado derecho de la pantalla
                mascara.reset(Settings.GAME_WIDTH + r.nextInt(Settings.GAME_WIDTH));
            }
        }

        for (MascaraBona mascaraBona : mascarasBona) {
            if (mascaraBona.isLeftOfScreen()) {
                // Restablece la posición x a un valor aleatorio en el lado derecho de la pantalla
                mascaraBona.reset(Settings.GAME_WIDTH + r.nextInt(Settings.GAME_WIDTH));
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