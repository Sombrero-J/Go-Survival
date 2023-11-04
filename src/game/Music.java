package game;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Music {

    static SoundClip menuMusic, levelOneMusic, levelTwoMusic, levelThreeMusic, failMusic;
    Game g;

    static boolean musicAllowed = true;
    static boolean soundEffectsAllowed = true;

    static double volume = 1.0;
    static double SEvolume = 1.0;

    static {
        try {
            menuMusic = new SoundClip("music/menuSound.mp3");
            levelOneMusic = new SoundClip("music/levelOneSound.mp3");
            levelTwoMusic = new SoundClip("music/levelTwoSound.mp3");
            levelThreeMusic = new SoundClip("music/levelThreeSound.mp3");
            failMusic = new SoundClip("music/failMusic.mp3");
        } catch (UnsupportedAudioFileException | IOException |
                 LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Music(Game game) {
        g = game;
    }

    public void playMusic() {
        menuMusic.stop();
        levelOneMusic.stop();
        levelTwoMusic.stop();
        levelThreeMusic.stop();
        failMusic.stop();
        if (musicAllowed) {
            switch (g.gameLevel) {
                case 4:
                    failMusic.setVolume(volume);
                    failMusic.loop();
                    break;
                case 5:
                case 0:
                    menuMusic.setVolume(volume);
                    menuMusic.loop();
                    break;
                case 1:
                    levelOneMusic.setVolume(volume);
                    levelOneMusic.loop();
                    break;
                case 2:
                    levelTwoMusic.setVolume(volume);
                    levelTwoMusic.loop();
                    break;
                case 3:
                    levelThreeMusic.setVolume(volume);
                    levelThreeMusic.loop();
                    break;
            }
        }
    }
}

