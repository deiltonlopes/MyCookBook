function addMoreIngredients(){
    document.getElementById('recipe-ingredients').innerHTML += '<span><input type=\"number\" name=\"ingredient-amt\" class=\"ingredient-amt\" placeholder=\"Amount\"><select name=\"ingredient-measure\" class=\"ingredient-measure\"><option value=\"UNIT\">Unit</option><option value=\"CUP\">Cup</option><option value=\"TEASPOON\">Teaspoon</option><option value=\"SPOON\">Spoon</option></select><input type=\"text\" name=\"ingredient-name\" id=\"ingredient-name\" placeholder=\"Ingredient name\"><span>'
}

function addMoreInstructions(){
    document.getElementsByClassName('recipe-instructions')[0].innerHTML += '<textarea name=\"instruction\" class=\"instruction\" cols=\"100\" rows=\"5\" placeholder=\"Add instruction\"></textarea>'
}

function addMoreItems(){
    document.getElementsByClassName('recipe-aside-list')[0].innerHTML += '<input type=\"text\" name=\"recipe-aside-list-item\" id=\"recipe-aside-list-item\" placeholder=\"List item\">'
}

function send(){
    let name = document.getElementById('recipe-name').value
    let assetsName = document.getElementById('assets-name').value
    let categories = getCategories()
    let description = document.getElementById('recipe-description').value
    let paragraph = document.getElementById('recipe-paragraph').value
    let ingredients = getIngredients()
    let instructions = getInstructions()
    let asideTitle = document.getElementById('recipe-aside-title').value
    let asideFirstText = document.getElementById('recipe-aside-text').value
    let asideList = getAsideList()
    let asideSecondText = document.getElementById('recipe-aside-second-text').value
    window.alert('Recipe created and persisted into the sysstem.')
}

function getCategories(){
    let categories = []
    let boxes = document.getElementsByClassName('categories')
    for(const box of boxes){
        if(box.checked){
            categories.push(box.value)
        }
    }
    return categories
}

function getIngredients(){

}

function getInstructions(){

}

function getAsideList(){
    
}