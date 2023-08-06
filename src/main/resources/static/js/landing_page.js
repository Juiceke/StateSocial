const state = document.getElementById("test");
const page = document.getElementById("pageContent");
const header = document.getElementById("stateHeader");
const states = document.getElementById("submit")
const posts = document.getElementsByClassName("post");
const likeAmnts = document.getElementsByClassName("likes");
const test = document.getElementById("test");

console.log(states);

window.onload = function() {stickyHeader()};

window.onscroll = function() {stickyHeader()};

const sticky = header.offsetTop

function stickyHeader() {
    if(window.pageYOffset > sticky) {
        header.classList.add("sticky");
    } else {
        header.classList.remove("sticky");
    }
}

async function user() {
const url = 'http://localhost:8080/session'
 let response = await fetch(url);
 let data = await response.json();
 console.log(data);
 return data.visitorId;
}

async function getJSON(url, e) {
    return await fetch(url, {
               method: "POST",
               headers: {
               "Content-Type": 'application/x-www-form-urlencoded'
               },
            }).then((response) => response.json())
            .then((data) => {
            console.log(data);

            console.log(posts);

            console.log(e.target.form.children[3].childNodes[1].innerHTML);

            const pageLikes = e.target.form.children[3].childNodes[1];


            if(data.likeamnt > pageLikes.innerHTML) {
               pageLikes.innerHTML++
            } else if (data.likeamnt < pageLikes.innerHTML && data.postBody) {
                pageLikes.innerHTML--
            }
            return data});
}


async function clicked(postId, userId, e) {

 // get info from backend
 const url = `${window.location.href.split("/")[0]}//` + window.location.href.split("/")[2] + `/posts/like?postId=${postId}&visitorId=${userId}`;
 const json = await this.getJSON(url, e);
 }

for(i=0; i < states.length; i++) {
states.addEventListener("change", (e) => {
states.submit();
})
}

 for(i=0; i < document.getElementsByClassName("post").length; i++) {
    posts[i].classList.add("inserted");
    posts[i].addEventListener("click", (e) => {
    postId = e.target.form[0].value;
    userId = e.target.form[1].value;
    clicked(postId, userId, e);
    })

 }
;
