package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SambaRace;

public class DeathScreen implements Screen {
    private SambaRace game; // Añade esta línea

    private SpriteBatch batch;
    private BitmapFont font;
    private int points; // Añade una variable para los puntos


    public DeathScreen(SambaRace game, int points) {
        this.game = game;
        this.points = points;

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2, 2); // Aumenta el tamaño de la fuente

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        String text = "Game Over - Toca para volver a jugar";
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);
        float textWidth = layout.width;
        float textHeight = layout.height;
        float x = (Gdx.graphics.getWidth() - textWidth) / 2;
        float y = (Gdx.graphics.getHeight() + textHeight) / 2;
        font.draw(batch, text, x, y);
        font.draw(batch, "Puntos: " + points, x, y - 100);
        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game)); // Cambia a la pantalla del juego para reiniciar el juego

        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

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

    // Implementa los otros métodos de la interfaz Screen aquí...
}