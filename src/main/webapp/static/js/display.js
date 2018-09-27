let mapObjects = [];
let mainCharacter;
let mainCharacterHealth;

window.addEventListener('load', function() {
    ajax_get("/send", function (data) {
        mapObjects = data[0];
        mainCharacter = data[1][0];
        mainCharacterHealth = mainCharacter.hp;
        updateCharacterStats();
        updateInventory();
    }, 'load');
});

function preload() {
    loadImages();

}

function setup() {
    let cnv = createCanvas(490, 490);
    cnv.parent('canvas');
}

function draw() {
    clear();
    fill(230);
    drawBoard();

}

function drawBoard() {
    for (let i = 0; i < mapObjects.length; i++) {
        let object = mapObjects[i];
        switch (object.name) {
            case 'DRAGON': image(dragon_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'LOOT': image(loot_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'MAIN_CHARACTER': image(main_character_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'SKELETON': image(skeleton_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'SLIME': image(slime_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'WALL': image(wall_image, object.x*50, object.y*50, height/12, width/12);
                break;
            case 'ORC': image(orc_image, object.x*50, object.y*50, height/12, width/12)
        }
    }

}