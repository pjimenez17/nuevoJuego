package screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.SambaRace;

import helpers.AssetManager;

public class mainScreen implements Screen{
    private SambaRace game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Music music;

    public mainScreen(SambaRace game) {
        this.game = game;
        AssetManager.music2.play();
        this.batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2, 2); // Aumenta el tamaño de la fuente

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.11f, 0.61f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        String text = "JOC DE SAMBA";
        String text2 ="RECULL TOTES LES MASCARES BONES SENSE MORIR";
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);
        float textWidth = layout.width;
        float textHeight = layout.height;
        float x = (Gdx.graphics.getWidth() - textWidth) / 2;
        float y = (Gdx.graphics.getHeight() + textHeight) / 2;
        font.draw(batch, text, x, y);
        font.draw(batch, text2, x, y-30);
        batch.end();
        if (Gdx.input.justTouched()) {
            AssetManager.music2.stop();
            game.setScreen(new GameScreen(game));

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
        batch.dispose();
        font.dispose();
        music.dispose();
    }
}
