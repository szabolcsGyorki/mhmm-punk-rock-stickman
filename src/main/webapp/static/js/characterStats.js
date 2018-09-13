let character_stats_list = document.getElementById("character_stats_list");


function updateCharacterStats() {

    while (character_stats_list.firstChild) {
        character_stats_list.removeChild(character_stats_list.firstChild);
    }

    for (let key in mainCharacter) {
        if (mainCharacter.hasOwnProperty(key) && key !== 'inventory') {
            let new_list_item = document.createElement('li');
            new_list_item.classList.add('list-group-item');
            new_list_item.classList.add('list-group-item-action');
            new_list_item.classList.add('active');
            let new_list_text = document.createTextNode(key + ': ' + mainCharacter[key]);
            new_list_item.appendChild(new_list_text);
            character_stats_list.appendChild(new_list_item);
        }
    }
}