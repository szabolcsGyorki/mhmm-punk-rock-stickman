package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.GameObjectType;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Sound {

    private static Map<GameObjectType, Runnable> attackSounds = new HashMap();
    private static Map<GameObjectType, Runnable> deathSounds = new HashMap();

    public static void init() {
        attackSounds.put(GameObjectType.SKELETON, Sound::playSkeletonAttack);
        attackSounds.put(GameObjectType.MAIN_CHARACTER, Sound::playSwordSwing);
        attackSounds.put(GameObjectType.SLIME, Sound::playSlimeAttack);

        deathSounds.put(GameObjectType.SKELETON, Sound::playSkeletonDeath);
        deathSounds.put(GameObjectType.SLIME, Sound::playSlimeDeath);
    }

    public static void playAttack(GameObjectType type) {
        attackSounds.get(type).run();
    }

    public static void playDie(GameObjectType type) {
        deathSounds.get(type).run();
    }

    private static void playSound(String path) {
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // create an audiostream from the inputstream
        AudioStream audioStream = null;
        try {
            assert in != null;
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }

    private static void playSwordSwing() {
        playSound("src/main/webapp/static/sounds/sword_swipe.wav");
    }

    private static void playSkeletonAttack() {
        playSound("src/main/webapp/static/sounds/skeleton_attack.wav");
    }

    private static void playSkeletonDeath() {
        playSound("src/main/webapp/static/sounds/skeleton_die.wav");
    }

    private static void playSlimeAttack() {
        playSound("src/main/webapp/static/sounds/slime_attack.wav");
    }

    private static void playSlimeDeath() {
        playSound("src/main/webapp/static/sounds/slime_die.wav");
    }

    public static void playMiss() {
        playSound("src/main/webapp/static/sounds/missed.wav");
    }
}
