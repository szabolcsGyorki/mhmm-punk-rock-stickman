
function ajax_get(url, callback, action, value) {
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        let data;
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            //console.log('responseText:' + xmlhttp.responseText);
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

function requestMap(action) {
    ajax_get('/send', function (data) {
        mapObjects = data[0];
        mainCharacter = data[1][0];
        mainCharacterHealth = mainCharacter.hp;
        updateCharacterStats();
        updateInventory();

    }, "map", action)
}

function requestEquip(item_name) {
    ajax_get('/send', function (data) {
        mainCharacter = data;
        mainCharacterHealth = mainCharacter.hp;
        updateCharacterStats();
    },"equip", item_name)
}



