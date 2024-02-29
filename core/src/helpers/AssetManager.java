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
    public static TextureRegion sambastill;

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
    public static TextureRegion sambastillL;
    public static TextureRegion sambastillR;



    public static void load() {
        samba = new Texture(Gdx.files.internal("sprite_sheet.png"));
        samba.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sambastillR = new TextureRegion(samba, 19, 714, 47, 52);
        sambastillL = new TextureRegion(samba, 5, 587, 47, 52);

        sambaLeftRegion = new TextureRegion[9];
        for(int i = 0; i < 9; i++) {
            sambaLeftRegion[i] = new TextureRegion(samba, i * 19, 588, 54, 50);
        }
        sambaLeftAnimation = new Animation(0.06f, sambaLeftRegion);
        sambaLeftAnimation.setPlayMode(Animation.PlayMode.LOOP);

        sambaRightRegion = new TextureRegion[9];
        for(int i = 0; i < 9; i++) {
            sambaRightRegion[i] = new TextureRegion(samba, i * 19, 717, 54, 50);
        }
        sambaRightAnimation = new Animation(0.06f, sambaRightRegion);
        sambaRightAnimation.setPlayMode(Animation.PlayMode.LOOP);

        sambaJumpLeftRegion = new TextureRegion[7];
        for(int i = 0; i < 7; i++) {
            sambaJumpLeftRegion[i] = new TextureRegion(samba, i * 34, 78, 31, 50);
        }
        sambaJumpLeftAnimation = new Animation(0.06f, sambaJumpLeftRegion);
        sambaJumpLeftAnimation.setPlayMode(Animation.PlayMode.LOOP);

        sambaJumpRightRegion = new TextureRegion[7];
        for(int i = 0; i < 7; i++) {
            sambaJumpRightRegion[i] = new TextureRegion(samba, i * 34, 207, 31, 47);
        }
        sambaJumpRightAnimation = new Animation(0.06f, sambaJumpRightRegion);
        sambaJumpRightAnimation.setPlayMode(Animation.PlayMode.LOOP);

        sambaDeadRegion = new TextureRegion[6];
        for(int i = 0; i < 6; i++) {
            sambaDeadRegion[i] = new TextureRegion(samba, i * 24, 1295, 53, 49);
        }
        sambaDeadAnimation = new Animation(0.06f, sambaDeadRegion);
        sambaDeadAnimation.setPlayMode(Animation.PlayMode.LOOP);

        sambaAttackLeftRegion = new TextureRegion[6];
        for(int i = 0; i < 6; i++) {
            if(i == 5){
                sambaAttackLeftRegion[i] = new TextureRegion(samba, i * 95, 3149, 81, 52);
            }
            else{
                sambaAttackLeftRegion[i] = new TextureRegion(samba, i * 115, 3149, 81, 52);
            }
        }
        sambaAttackLeftAnimation = new Animation(0.06f, sambaAttackLeftRegion);
        sambaAttackLeftAnimation.setPlayMode(Animation.PlayMode.LOOP);

        sambaAttackRightRegion = new TextureRegion[6];
        for(int i = 0; i < 6; i++) {
            sambaAttackRightRegion[i] = new TextureRegion(samba, i * 130, 1998, 101, 48);
        }
        sambaAttackRightAnimation = new Animation(0.06f, sambaAttackRightRegion);
        sambaAttackRightAnimation.setPlayMode(Animation.PlayMode.LOOP);

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
