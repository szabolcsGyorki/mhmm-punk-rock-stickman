package com.codecool.mhmm.stickman.services;

import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.characters.Character;

import java.util.List;

public class MoveHandler {

    private static MoveHandler instance;

    private MoveHandler() {
    }

    public static MoveHandler getInstance() {
        if (instance == null) {
            instance = new MoveHandler();
        }
        return instance;
    }

    public void moveLeft(Character character) {
        character.place(character.getX() - 1, character.getY());
    }

    public void moveRight(Character character) {
        character.place(character.getX() + 1, character.getY());
    }

    public void moveUp(Character character) {
        character.place(character.getX(), character.getY() - 1);
    }

    public void moveDown(Character character) {
        character.place(character.getX(), character.getY() + 1);
    }

    public GameObject getDestination(int toX, int toY, List<GameObject> map) {
        GameObject destination = null;
        for (GameObject mapElement : map) {
            if (mapElement.getX() == toX && mapElement.getY() == toY) {
                destination = mapElement;
                break;
            }
        }
        return destination;
    }
}
