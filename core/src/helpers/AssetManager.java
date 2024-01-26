package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
    public static Sound explosionSound;
    public static Music music;
    public static Music music2;
    public static Sound dead;
    public static Sound collect;

    // Sprite Sheet
    public static Texture samba;
    public static Texture background;
    public static Texture mascara_malvada;
    public static Texture mascara_bona;
    public static float originalMusicVolume = 0.7f;


    public static void load() {
        samba = new Texture(Gdx.files.internal("samba.png"));
        samba.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        // Cargar la imagen de fondo
        background = new Texture(Gdx.files.internal("background.jpg"));
        background.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);


        mascara_malvada = new Texture(Gdx.files.internal("mascara_malvada.png"));
        mascara_malvada.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        mascara_bona = new Texture(Gdx.files.internal("mascara_bona.png"));
        mascara_bona.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        music = Gdx.audio.newMusic(Gdx.files.internal("musica.mp3"));
        music.setVolume(0.7f);
        music.setLooping(true);

        music2 = Gdx.audio.newMusic(Gdx.files.internal("musicaMenu.mp3"));
        music2.setVolume(0.7f);
        music2.setLooping(true);

        dead = Gdx.audio.newSound(Gdx.files.internal("lose.mp3"));
        collect = Gdx.audio.newSound(Gdx.files.internal("collect.mp3"));


    }

    public static void dispose() {
        samba.dispose();
        explosionSound.dispose();
        music.dispose();
    }
}
