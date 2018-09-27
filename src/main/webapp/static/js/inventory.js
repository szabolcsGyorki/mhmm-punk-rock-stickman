let character_inventory_list = document.getElementById("list-tab");
let character_inventory_list_descriptions = document.getElementById("nav-tabContent");

function updateInventory() {
    while (character_inventory_list.firstChild) {
        character_inventory_list.removeChild(character_inventory_list.firstChild);
    }

    while (character_inventory_list_descriptions.firstChild) {
        character_inventory_list_descriptions.removeChild(character_inventory_list_descriptions.firstChild);
    }

    for (let item of mainCharacter.inventory) {
        let item_info;
        if (item.type === "weapon") {
            item_info = item.minDamage + " - " + item.maxDamage;
        } else {
            item_info = "+ " + item.healthIncrease + " HP";
        }

        //inventory item list
        character_inventory_list.innerHTML +=
            '<a class="list-group-item list-group-item-action" id="list-'
            + item.name + '-list" data-toggle="list" href="#list-' + item.name + '" role="tab" aria-controls="'
            + item.name + '">' + item.name + '</a>';


        //description of the items
        character_inventory_list_descriptions.innerHTML +=
            '<div class="tab-pane fade" id="list-' + item.name + '" role="tabpanel" aria-labelledby="list-' + item.name + '-list">'
            + item_info + '</div>'
    }

    //setting the first list item selected
    let first_list_item = document.getElementById("list-tab").firstChild;
    first_list_item.classList.add("active");
}

function get_selected_item() {
    let items = character_inventory_list.getElementsByClassName("list-group-item list-group-item-action");
    for (let item of items) {
        if (item.classList.contains("active")) {
            return item.innerHTML;
        }
    }
}
