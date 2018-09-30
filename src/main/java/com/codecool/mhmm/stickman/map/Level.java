package com.codecool.mhmm.stickman.map;

import com.codecool.mhmm.stickman.game_objects.GameObject;
import com.codecool.mhmm.stickman.game_objects.GameObjectType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name="Level.getAll", query="SELECT level FROM Level level")
public class Level {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany
    @JoinTable(name = "level_content")
    private List<GameObject> map = new ArrayList<>();

    private int WIDTH;
    private int HEIGHT;
    private GameObjectType wallImage;
    private GameObjectType floorImage;

    public Level(int width, int height, GameObjectType wall, GameObjectType floor) {
        this.WIDTH=width;
        this.HEIGHT=height;
        this.wallImage = wall;
        this.floorImage = floor;
    }

    public Level() {
    }

    public long getId() {
        return id;
    }

    public List<GameObject> getMap() {
        return map;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public GameObjectType getFloorImage() {
        return floorImage;
    }

    public GameObjectType getWallImage() {
        return wallImage;
    }

    public void addContent(GameObject content) {
        map.add(content);
    }
}
