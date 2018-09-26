let equip_button = document.getElementById("equip_button");

//Equip button
equip_button.addEventListener("click", function () {
    requestEquip(get_selected_item());
});