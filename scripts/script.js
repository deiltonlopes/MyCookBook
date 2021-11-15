function bodylessRequestMaker(method, url){
    let request = new XMLHttpRequest
    request.open(method, url, false)
    request.send()
    return request.responseText
}

function requestMaker(method, url, body){
    let request = new XMLHttpRequest
    request.open(method, url, false)
    request.setRequestHeader("Content-Type", "application/json")
    request.send(body)
    return request.responseText
}

function getListCategory(category){
    data = bodylessRequestMaker('GET', `https://my-cook-book-bck.herokuapp.com/api/recipes/category/${category}`)
    recipesList = JSON.parse(data)
    console.log(recipesList)
    return recipesList
}

function showListCategory(category){
    let recipes = getListCategory(category)
    let list = makeRecipesMenu(recipes)
    document.getElementById('list-content').innerHTML = list.innerHTML
}

function makeRecipesMenu(recipes){
    let list = document.createElement('ul')
    for(const r of recipes){
        let element = document.createElement('li')
        element.innerHTML += `<img src=\"../images/${r.assetsName}/thumb.png\">`
        element.innerHTML += `<a href=\"#\" onclick=\"loadRecipe(${r.id})\"><abbr title=\"${r.description}\">${r.name}</abbr></a>`
        list.innerHTML += element.outerHTML
    }
    return list
}

function loadRecipe(id){
    let data = bodylessRequestMaker('GET', `https://my-cook-book-bck.herokuapp.com/api/recipe/${id}`)
    let recipe = JSON.parse(data)
    renderRecipeMain(recipe)
}

function getRandom(){
    rawRecipe = bodylessRequestMaker('GET', 'https://my-cook-book-bck.herokuapp.com/api/recipe/random')
    recipe = JSON.parse(rawRecipe)
    renderRecipeMain(recipe)
}

function renderRecipeMain(recipe){
    let article = makeArticle(recipe)
    let aside = makeAside(recipe)
    
    document.getElementsByTagName('main')[0].innerHTML = article.outerHTML + aside.outerHTML
}

function makeArticle(recipe){
    let article = document.createElement('article')
    article.innerHTML+= `<h1>${recipe.name}</h1>`
    article.innerHTML += `<p>${recipe.paragraph}</p>`
    let pic = document.createElement('picture')
    pic.innerHTML += `<source media=\"(max-width: 650px)\" srcset=\"../images/${recipe.assetsName}/main-p.png\">`
    pic.innerHTML += `<img src=\"../images/${recipe.assetsName}/main-g.png\">`
    article.innerHTML += pic.outerHTML
    article.innerHTML += '<h2>The ingredients</h2>'
    let ingredients = document.createElement('ul')
    for(const i of recipe.ingredients){
        let ingredient = String(i.amount)
        if(i.measure=='UNIT'){
            ingredient += ' units of '
        }else if(i.measure=='CUP'){
            ingredient += ' cups of '
        }else if(i.measure=='TABLESPOON'){
            ingredient += ' tablespoons of '
        }else if(i.measure=='GRAM'){
            ingredient += ' grams of '
        }else{
            ingredient += ' teaspoons of '
        }
        ingredient += i.name
        ingredients.innerHTML += `<li>${ingredient}</li>`
    }
    article.innerHTML += ingredients.outerHTML
    article.innerHTML += '<h2>How to prepare</h2>'
    let instructions = document.createElement('ol')
    for(let i = 0; i < Math.floor(recipe.instructions.length/3); i++){
        instructions.innerHTML += `<li>${recipe.instructions[i]}</li>`
    }
    let pic2 = document.createElement('picture')
    pic2.innerHTML += `<source media=\"(max-width: 650px)\" srcset=\"../images/${recipe.assetsName}/start-p.png\">`
    pic2.innerHTML += `<img src=\"../images/${recipe.assetsName}/start-g.png\">`
    instructions.innerHTML += pic2.outerHTML
    for(let i = Math.floor(recipe.instructions.length/3); i < Math.floor(recipe.instructions.length/3) * 2; i++){
        instructions.innerHTML += `<li>${recipe.instructions[i]}</li>`
    }
    instructions.innerHTML += `<img src=\"../images/${recipe.assetsName}/finish.png\" class=\"small\">`
    for(let i = Math.floor(recipe.instructions.length/3) * 2; i < recipe.instructions.length; i++){
        instructions.innerHTML += `<li>${recipe.instructions[i]}</li>`
    }
    article.innerHTML += instructions.outerHTML
    return article
}

function makeAside(recipe){
    let aside = document.createElement('aside')

    aside.innerHTML += `<h2>${recipe.asideTitle}</h2>`
    aside.innerHTML += `<p>${recipe.asideFirstText}</p>`
    if(recipe.asideList != [] && recipe.asideList !== null){
        let list = document.createElement('ul')
        for(const l of recipe.asideList){
            list.innerHTML += `<li>${l}</li>`
        }
        aside.innerHTML += list.outerHTML
    }
    if(recipe.asideSecondText != '' && recipe.asideSecondText !== null){
        aside.innerHTML += `<p>${recipe.asideSecondText}</p>`
    }
    return aside
}

function showIngredients(){
    let form = document.createElement('ul')
    form.id = 'ingredients'
    let rawIngredients = bodylessRequestMaker('GET', 'https://my-cook-book-bck.herokuapp.com/api/recipes/ingredients')
    let ingredients = JSON.parse(rawIngredients)
    for(const i of ingredients){
        form.innerHTML += `<li><input type=\"checkbox\" name=\"user-ingredients\" class=\"user-ingredients\" value=\"${i}\"> ${i}</li>`
    }
    document.getElementById('form').innerHTML = form.outerHTML + '<button onclick=\"showRecipeList()\">Get the recipes</button>'
}

function showRecipeList(){
    let ingredients = []
    let allCheckboxes = document.getElementsByClassName('user-ingredients')
    for(const box of allCheckboxes){
        if(box.checked){
            ingredients.push(box.value)
        }
    }
    
    let rawRecipes = requestMaker('POST', 'https://my-cook-book-bck.herokuapp.com/api/recipes/ingredients', JSON.stringify(ingredients))
    let recipes = JSON.parse(rawRecipes)
    document.getElementById('form').style.display = 'none'
    if(recipes.length > 0){
        let list = makeRecipesMenu(recipes)
        list.id = 'list-content'

        document.querySelector('main>p').innerText = 'The recipes I found were:'
        document.querySelector('main').innerHTML += list.outerHTML
    }else{
        document.querySelector('main>p').innerText = 'Unfortunately we couldn\'t find any recipes at this moment.'
    }
    
}