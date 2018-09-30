
function ajax_get(url, callback, action, value) {
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        let data;
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            //console.log('resúponseText:' + xmlhttp.responseText);
            try {
                data = JSON.parse(xmlhttp.responseText);
            } catch(err) {
                console.log(err.message + " in " + xmlhttp.responseText);
                return;
            }
            callback(data);
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.setRequestHeader(action, value);
    xmlhttp.send();
}

function requestMove(action) {
    ajax_get('/move', function (data) {
        mapObjects = data[0];
        mainCharacter = data[1][0];
        mainCharacterHealth = mainCharacter.Health;
        updateCharacterStats();
    }, "map", action)
}

function requestEquipWeapon(item_name) {
    ajax_get('/equip', function (data) {
        mapObjects = data[0];
        mainCharacter = data[1][0];
        mainCharacterHealth = mainCharacter.Health;
        updateCharacterStats();
        updateInventory();
    },"equipWeapon", item_name)
}

function requestEquipArmor(item_name) {
    ajax_get('/equip', function (data) {
        mapObjects = data[0];
        mainCharacter = data[1][0];
        mainCharacterHealth = mainCharacter.Health;
        updateCharacterStats();
        updateInventory();
    },"equipArmor", item_name)
}

function requestLoot(direction) {
    ajax_get('/loot', function (data) {
        mapObjects = data[0];
        mainCharacter = data[1][0];
        mainCharacterHealth = mainCharacter.Health;
        updateCharacterStats();
        updateInventory();
    },"pickUpLoot", direction)
}

function requestFight(direction) {
    ajax_get('/fight', function (data) {
        mapObjects = data[0];
        mainCharacter = data[1][0];
        mainCharacterHealth = mainCharacter.Health;
        updateCharacterStats();
    },"fight", direction)
}



