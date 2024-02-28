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
    public static Music attack;
    public static Music stab;

    // Sprite Sheet
    public static Texture samba;
    public static Texture background;
    public static Texture mascara_malvada;
    public static Texture mascara_bona;
    public static float originalMusicVolume = 0.7f;
    // TextureRegions
    public static TextureRegion sambaLeftRegion[];
    public static Animation sambaLeftAnimation;
    public static TextureRegion sambaRightRegion[];
    public static Animation sambaRightAnimation;
    public static TextureRegion sambaJumpLeftRegion[];
    public static Animation sambaJumpLeftAnimation;
    public static TextureRegion sambaJumpRightRegion[];
    public static Animation sambaJumpRightAnimation;
    public static TextureRegion sambaDeadRegion[];
    public static Animation sambaDeadAnimation;
    public static TextureRegion sambaAttackLeftRegion[];
    public static Animation sambaAttackLeftAnimation;
    public static TextureRegion sambaAttackRightRegion[];
    public static Animation sambaAttackRightAnimation;



    public static void load() {
        samba = new Texture(Gdx.files.internal("sprite_sheet.png"));
        samba.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sambaLeftRegion = new TextureRegion[9];
        for(int i = 0; i < 9; i++) {
            sambaLeftRegion[i] = new TextureRegion(samba, i * 25, 127, 103, 114);
        }
        sambaLeftAnimation = new Animation(0.5f, sambaLeftRegion);
        sambaLeftAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        sambaRightRegion = new TextureRegion[9];
        for(int i = 0; i < 9; i++) {
            sambaRightRegion[i] = new TextureRegion(samba, i * 25, 2, 103, 114);
        }
        sambaRightAnimation = new Animation(0.5f, sambaRightRegion);
        sambaRightAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        sambaJumpLeftRegion = new TextureRegion[7];
        for(int i = 0; i < 7; i++) {
            sambaJumpLeftRegion[i] = new TextureRegion(samba, i * 32, 714, 32, 32);
        }
        sambaJumpLeftAnimation = new Animation(0.5f, sambaJumpLeftRegion);
        sambaJumpLeftAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        sambaJumpRightRegion = new TextureRegion[7];
        for(int i = 0; i < 7; i++) {
            sambaJumpRightRegion[i] = new TextureRegion(samba, i * 32, 833, 32, 32);
        }
        sambaJumpRightAnimation = new Animation(0.5f, sambaJumpRightRegion);
        sambaJumpRightAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        sambaDeadRegion = new TextureRegion[6];
        for(int i = 0; i < 6; i++) {
            sambaDeadRegion[i] = new TextureRegion(samba, i * 32, 257, 32, 32);
        }
        sambaDeadAnimation = new Animation(0.5f, sambaDeadRegion);
        sambaDeadAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        sambaAttackLeftRegion = new TextureRegion[5];
        for(int i = 0; i < 5; i++) {
            sambaAttackLeftRegion[i] = new TextureRegion(samba, i * 32, 558, 32, 32);
        }
        sambaAttackLeftAnimation = new Animation(0.5f, sambaAttackLeftRegion);
        sambaAttackLeftAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        sambaAttackRightRegion = new TextureRegion[5];
        for(int i = 0; i < 5; i++) {
            sambaAttackRightRegion[i] = new TextureRegion(samba, i * 32, 407, 200, 105);
        }
        sambaAttackRightAnimation = new Animation(0.5f, sambaAttackRightRegion);
        sambaAttackRightAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        // Cargar la imagen de fondo
        background = new Texture(Gdx.files.internal("background.jpg"));
        background.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);


        mascara_malvada = new Texture(Gdx.files.internal("bat.png"));
        mascara_malvada.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        mascara_bona = new Texture(Gdx.files.internal("vampire.png"));
        mascara_bona.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        music = Gdx.audio.newMusic(Gdx.files.internal("musica.mp3"));
        music.setVolume(0.7f);
        music.setLooping(true);

        music2 = Gdx.audio.newMusic(Gdx.files.internal("musicaMain.mp3"));
        music2.setVolume(0.7f);
        music2.setLooping(true);

        attack = Gdx.audio.newMusic(Gdx.files.internal("attack_effect.mp3"));
        stab = Gdx.audio.newMusic(Gdx.files.internal("stab.mp3"));


    }

    public static void dispose() {
        samba.dispose();
        mascara_bona.dispose();
        mascara_malvada.dispose();
        explosionSound.dispose();
        music.dispose();
    }
}
