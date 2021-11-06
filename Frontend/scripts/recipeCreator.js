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
    let recipe ={
        ingredients : getIngredients(),
        instructions : getInstructions(),
        categories : getCategories(),
        name : document.getElementById('recipe-name').value,
        assetsName : document.getElementById('assets-name').value,
        description : document.getElementById('recipe-description').value,
        paragraph : document.getElementById('recipe-paragraph').value,
        asideTitle : document.getElementById('recipe-aside-title').value,
        asideFirstText : document.getElementById('recipe-aside-text').value,
        asideList : getAsideList(),
        asideSecondText : document.getElementById('recipe-aside-second-text').value
    }
    data = JSON.stringify(recipe)
    postRecipe(data)
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
    let allRawIngredients = document.querySelectorAll('#recipe-ingredients>span')
    let ingredients = []
    for(const i of allRawIngredients){
       let ingredient = {
           name: i.querySelector('#ingredient-name').value,
           amount: i.getElementsByClassName('ingredient-amt')[0].value,
           measure: i.getElementsByClassName('ingredient-measure')[0].value
       }
       ingredients.push(ingredient)
    }
    return ingredients
}

function getInstructions(){
    let allRawInstructions = document.getElementsByClassName('instruction')
    let instructions = []
    for(const i of allRawInstructions){
        instructions.push(i.value)
    }
    return instructions
}

function getAsideList(){
    let allRawItems = document.querySelectorAll('.recipe-aside-list>input')
    let items = []
    for(const i of allRawItems){
        items.push(i.value)
    }
    return items
}

function postRecipe(data){
    let request = new XMLHttpRequest
    request.open('POST', 'https://my-cook-book-bck.herokuapp.com/api/recipe', false)
    request.send(data)
    console.log(request.responseText)
}