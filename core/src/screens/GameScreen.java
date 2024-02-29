package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import helpers.AssetManager;

import com.mygdx.game.SambaRace;
import com.mygdx.game.ScrollHandler;
import helpers.InputHandler;
import objects.MascaraBona;
import objects.mascara;
import objects.samba;
import utils.Methods;
import utils.Settings;

public class GameScreen implements Screen {
    Boolean gameOver = false;
    private SambaRace game; // Añade esta línea
    private Random r; // Add this line


    private Stage stage;
    private samba samba;
    private mascara mascara;
    private MascaraBona mascaraBona;
    private ScrollHandler scrollHandler;
    // Representació de figures geomètriques
    private ShapeRenderer shapeRenderer;
    // Per obtenir el batch de l'stage
    private Batch batch;
    private int points = 0; // Añade un contador de puntos


    private ArrayList<MascaraBona> mascarasBona;



    public GameScreen(SambaRace game) {
        r = new Random(); // Initialize the Random instance
        this.game = game;
        AssetManager.music.play();
        shapeRenderer = new ShapeRenderer();
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT , camera);
        stage = new Stage(viewport);
        batch = stage.getBatch();
        samba = new samba(Settings.SAMBA_STARTX, Settings.SAMBA_STARTY, Settings.SAMBA_WIDTH, Settings.SAMBA_HEIGHT);
        scrollHandler = new ScrollHandler();
        stage.addActor(scrollHandler);
        stage.addActor(samba);
        samba.setName("samba");
        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    private void drawElements() {
        if (shapeRenderer != null && samba != null && scrollHandler.getMascaras() != null && scrollHandler.getMascarasBona() != null) {
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            if (samba.isAttacking()) {
                Rectangle attackRect = samba.getAttackRect();
                shapeRenderer.rect(attackRect.x, attackRect.y, attackRect.width, attackRect.height);
            } else {
                shapeRenderer.setColor(new Color(0,1,0,1));
            }
            shapeRenderer.rect(samba.getX(), samba.getY(), samba.getWidth(), samba.getHeight());
            ArrayList<mascara> mascaras = scrollHandler.getMascaras();
            mascara mascara;

            for(int i = 0; i< mascaras.size(); i++) {
                mascara = mascaras.get(i);
                switch(i){
                    case 0:
                        shapeRenderer.setColor(new Color(1,0,0,1));
                        break;
                    case 1:
                        shapeRenderer.setColor(new Color(0,0,1,1));
                        break;
                    case 2:
                        shapeRenderer.setColor(new Color(1,1,0,1));
                        break;
                    default:
                        shapeRenderer.setColor(new Color(1,1,1,1));
                        break;
                }
                shapeRenderer.circle(mascara.getX() + mascara.getWidth()/2, mascara.getY()+ mascara.getWidth()/2, mascara.getWidth()/2);
            }

            ArrayList<MascaraBona> mascarasBona = scrollHandler.getMascarasBona();
            MascaraBona mascaraBona;

            for(int i = 0; i< mascarasBona.size(); i++) {
                mascaraBona = mascarasBona.get(i);
                switch(i){
                    case 0:
                        shapeRenderer.setColor(new Color(1,0,0,1));
                        break;
                    case 1:
                        shapeRenderer.setColor(new Color(0,0,1,1));
                        break;
                    case 2:
                        shapeRenderer.setColor(new Color(1,1,0,1));
                        break;
                    default:
                        shapeRenderer.setColor(new Color(1,1,1,1));
                        break;
                }
                shapeRenderer.circle(mascaraBona.getX() + mascaraBona.getWidth()/2, mascaraBona.getY()+ mascaraBona.getWidth()/2, mascaraBona.getWidth()/2);
            }

            shapeRenderer.end();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public samba getSamba() {
        return samba;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.draw();
        stage.act(delta);
        //drawElements();

        if (!gameOver) {
            boolean hasHandledCollision = false;
            for (mascara mascara : scrollHandler.getMascaras()) {
                if (!hasHandledCollision && samba.overlaps(samba.getAttackRect(), mascara.getCollisionCircle())) {
                    points++;
                    if (!AssetManager.stab.isPlaying()) {
                        AssetManager.stab.play();
                    }
                    float newX = Settings.GAME_WIDTH + r.nextFloat() * 100; // Reset en una posición aleatoria fuera de la pantalla
                    mascara.reset(newX); // Use the mascara object from the list
                    hasHandledCollision = true;
                } else if (mascara.collides(samba)) {
                    gameOver = true;
                    samba.setGameOver(true);
                    return;
                }
            }
            for (MascaraBona mascaraBona : scrollHandler.getMascarasBona()) {
                if (!hasHandledCollision && samba.getAttackRect().overlaps(mascaraBona.getCollisionRect())) {
                    points++;
                    if (!AssetManager.stab.isPlaying()) {
                        AssetManager.stab.play();
                    }
                    float newX = Settings.GAME_WIDTH + r.nextFloat() * 100; // Reset en una posición aleatoria fuera de la pantalla
                    mascaraBona.reset(newX); // Use the mascaraBona object from the list
                    hasHandledCollision = true;
                } else if (mascaraBona.collides(samba)) {
                    gameOver = true;
                    return;
                }
            }
        } else {
            batch.begin();
            BitmapFont font = new BitmapFont(false);
            font.getData().setScale(0.7f, 0.7f); // Cambia el tamaño del texto
            font.draw(batch, "Game Over - Toca per tornar a jugar: " + points, 20, 20);
            batch.end();

            if (Gdx.input.justTouched()) {
                restartGame();
                samba.setGameOver(false);
                points = 0;
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }
    public void restartGame() {
        gameOver = false;
        samba = new samba(Settings.SAMBA_STARTX, Settings.SAMBA_STARTY, Settings.SAMBA_WIDTH, Settings.SAMBA_HEIGHT);
        scrollHandler = new ScrollHandler();
        stage.clear();
        stage.addActor(scrollHandler);
        stage.addActor(samba);
        samba.setName("samba");
        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
