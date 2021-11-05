function bodylessRequestMaker(method, url){
    let request = new XMLHttpRequest
    request.open(method, url, false)
    request.send()
    return request.responseText
}

function requestMaker(method, url, body){
    let request = new XMLHttpRequest
    request.open(method, url, false)
    request.send(body)
    return request.responseText
}

function getListCategory(category){
    data = bodylessRequestMaker('GET', `https://my-cook-book-bck.herokuapp.com/api/recipes/category/${category}`)
    recipesList = JSON.parse(data)
    console.log(recipesList)
}