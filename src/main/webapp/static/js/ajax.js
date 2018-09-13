function ajax_get(url, callback, action) {
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        let data;
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            console.log('responseText:' + xmlhttp.responseText);
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
    xmlhttp.setRequestHeader('action', action);
    xmlhttp.send();
}

function requestMap(action) {
    ajax_get('/send', function (data) {
        mapObjects = data;
        draw();
    }, action)
}

function requestCharacter() {
    ajax_get('/send', function (data) {
        mainCharacter = data;
        draw();
    }, 'char')
}