document.onkeydown = function(e) {
    let handled;
    if (e.keyCode !== undefined) {
        switch (e.keyCode) {
            case 37: //left
                requestMap('left');
                requestCharacter();
                handled = true;
                break;
            case 39: //right
                requestMap('right');
                requestCharacter();
                handled = true;
                break;
            case 38: //up
                requestMap('up');
                requestCharacter();
                handled = true;
                break;
            case 40: //down
                requestMap('down');
                requestCharacter();
                handled = true;
                break;
        }

        if (handled) {
            e.preventDefault();
        }
    }

};