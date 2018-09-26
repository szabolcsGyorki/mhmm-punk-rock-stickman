package com.codecool.mhmm.stickman.Map;

import com.codecool.mhmm.stickman.GameObjects.Characters.*;
import com.codecool.mhmm.stickman.GameObjects.Characters.Character;
import com.codecool.mhmm.stickman.GameObjects.Characters.Enemy.*;
import com.codecool.mhmm.stickman.GameObjects.GameObject;
import com.codecool.mhmm.stickman.GameObjects.GameObjectType;
import com.codecool.mhmm.stickman.GameObjects.Wall;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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
        generateBase();
    }

    public Level() {
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

    public void placeWall(int X,int Y){
        map.add(new Wall(X,Y,this.wallImage));
    }

    public void placePlayer(Player player){
        map.add(player);
    }

    public void addContent(GameObject content) {
        map.add(content);
    }

    public void placeEnemy(int X,int Y, GameObjectType type, int level) {
        switch (type) {
            case SLIME:
                map.add(new Slime(X,Y,level));
                break;
            case ORC:
                map.add(new Orc(X,Y,level));
                break;
            case SKELETON:
                map.add(new Skeleton(X,Y,level));
                break;
            case DRAGON:
                map.add(new Dragon(X,Y,level));
                break;
        }
    }

    public void move(int toX, int toY, Character movingCharacter){
        int fromX = movingCharacter.getX();
        int fromY = movingCharacter.getY();
        GameObject destination = null;

        for (GameObject mapElement: map) {
            if (mapElement.getX() == toX && mapElement.getY() == toY) {
                destination = mapElement;
                break;
            }
        }

        if (destination == null) {
            movingCharacter.place(toX, toY);
        } else {
            switch (destination.getType()) {
                case LOOT: {
                    movingCharacter.place(toX, toY);
                    // pick up
                    map.remove(destination);
                    break;
                }
                case DRAGON:
                case ORC:
                case SKELETON:
                case SLIME: {
                    if (movingCharacter instanceof Player) {
                        Player player = (Player) movingCharacter;
                        Enemy enemy = (Enemy) destination;
                        player.takeDamage(enemy.attack());
                        enemy.takeDamage(player.attack());
                        if (enemy.getHitPoint() <= 0) {
                            map.remove(destination);
                            movingCharacter.place(toX, toY);
                            //get loot after enemy
                        }
                        break;
                    }
                }
            }
        }
    }

    private void generateBase(){
        for(int i = 0; i< WIDTH; i++){
            map.add(new Wall(i,0,this.wallImage));
            map.add(new Wall(i,HEIGHT-1,this.wallImage));
        }
        for(int i = 1; i< HEIGHT-1; i++){
            map.add(new Wall(0,i,this.wallImage));
            map.add(new Wall(WIDTH-1,i,this.wallImage));
        }
    }
}
