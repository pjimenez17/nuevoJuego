package screens;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.SambaRace;
import utils.Settings;

public class WinScreen implements Screen {
    private SambaRace game;
    private Stage stage;
    private BitmapFont font;
    private SpriteBatch batch;


    public WinScreen(SambaRace game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT , camera);
        stage = new Stage(viewport);
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
        batch.begin(); // Add this line
        String text = "FELICITATS, HAS GUANYAT!";
        String text2 ="Toca per tornar a jugar";
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, text);
        float textWidth = layout.width;
        float textHeight = layout.height;
        float x = (Gdx.graphics.getWidth() - textWidth) / 2;
        float y = (Gdx.graphics.getHeight() + textHeight) / 2;

        font.draw(batch, text, x, y);
        font.draw(batch, text2, x, y-30);
        batch.end(); // And this line

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
        stage.dispose();
    }
}
