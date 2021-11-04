function getMaker(url){
    let request = new XMLHttpRequest
    request.open('GET', url, false)
    request.send()
    return request.responseText
}

function getListCategory(category){
    data = getMaker(`https://my-cook-book-bck.herokuapp.com/recipes/category/${category}`)
    recipesList = JSON.parse
    console.log(recipesList)
}