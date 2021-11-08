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
}


function getRandom(){
    rawRecipe = bodylessRequestMaker('GET', 'https://my-cook-book-bck.herokuapp.com/api/recipe/random')
    recipe = JSON.parse(rawRecipe)
    renderRecipeMain(recipe)
}

function renderRecipeMain(recipe){
    let recipeMain = document.createElement('main')
    
    let article = makeArticle(recipe)
    
    document.getElementById('recipe-content').innerHTML = article.outerHTML
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
        }else if(i.measure=='SPOON'){
            ingredient += ' spoons of '
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