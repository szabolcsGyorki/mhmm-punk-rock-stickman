document.onkeydown = function(e) {
    let handled;
    if (e.keyCode !== undefined) {
        switch (e.keyCode) {
            case 37: //left
                requestMap('left');
                handled = true;
                break;
            case 39: //right
                requestMap('right');
                handled = true;
                break;
            case 38: //up
                requestMap('up');
                handled = true;
                break;
            case 40: //down
                requestMap('down');
                handled = true;
                break;
        }
        if (handled) {
            e.preventDefault();
        }
    }
};