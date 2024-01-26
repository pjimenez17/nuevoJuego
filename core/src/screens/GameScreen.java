package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import helpers.AssetManager;

import com.mygdx.game.SambaRace;
import com.mygdx.game.ScrollHandler;
import helpers.InputHandler;
import objects.MascaraBona;
import objects.mascara;
import objects.samba;
import utils.Settings;

public class GameScreen implements Screen {
    Boolean gameOver = false;
    private SambaRace game; // Añade esta línea


    private Stage stage;
    private samba samba;
    private mascara mascara;
    private ScrollHandler scrollHandler;
    // Representació de figures geomètriques
    private ShapeRenderer shapeRenderer;
    // Per obtenir el batch de l'stage
    private Batch batch;
    private int points = 0; // Añade un contador de puntos
    private ArrayList<MascaraBona> mascarasBona;



    public GameScreen(SambaRace game) {
        this.game = game;
        AssetManager.music.play();
        shapeRenderer = new ShapeRenderer();
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT , camera);
        mascarasBona = new ArrayList<MascaraBona>();
        stage = new Stage(viewport);
        batch = stage.getBatch();
        samba = new samba(Settings.SPACECRAFT_STARTX, Settings.SPACECRAFT_STARTY, Settings.SPACECRAFT_WIDTH, Settings.SPACECRAFT_HEIGHT);
        scrollHandler = new ScrollHandler();
        stage.addActor(scrollHandler);
        stage.addActor(samba);
        samba.setName("samba");
        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    private void drawElements() {
        //Gdx.gl20.glClearColor(0, 0, 0, 1);
        //Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(new Color(0,1,0,1));
        shapeRenderer.rect(samba.getX(), samba.getY(), samba.getWidth(), samba.getHeight());

        ArrayList<mascara> mascaras = scrollHandler.getAsteroids();
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
        shapeRenderer.end();
    }

    public Stage getStage() {
        return stage;
    }

    public samba getSpacecraft() {
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

        if (!gameOver) {
            for(MascaraBona mascaraBona: mascarasBona){
                if(mascaraBona.collides(samba)){
                    points++; // Incrementa el contador de puntos
                    mascarasBona.remove(mascaraBona); // Elimina la MascaraBona de la lista
                    mascaraBona.remove(); // Elimina la MascaraBona del Stage
                    break; // Sal del bucle para evitar errores de concurrencia
                }
            }
            if (scrollHandler.collides(samba)) {
                stage.getRoot().findActor("samba").remove();
                gameOver = true;
                game.setScreen(new DeathScreen(game, points)); // Cambia a la pantalla de muerte cuando el jugador muere

            }
        } else {
            batch.begin();
            BitmapFont font = new BitmapFont(false);
            font.getData().setScale(0.7f, 0.7f); // Cambia el tamaño del texto
            font.draw(batch, "Game Over - Toca per tornar a jugar", 20,20);
            batch.end();

            if (Gdx.input.justTouched()) {
                restartGame();
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
        samba = new samba(Settings.SPACECRAFT_STARTX, Settings.SPACECRAFT_STARTY, Settings.SPACECRAFT_WIDTH, Settings.SPACECRAFT_HEIGHT);
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
