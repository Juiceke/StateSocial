const state = document.getElementById("test");
const page = document.getElementById("pageContent");
const header = document.getElementById("stateHeader");
const postGroup = document.getElementById('pageContent');
console.log(postGroup);
//const original = document.getElementById("no").value;
//
//let toCheck = original;
//
//console.log(original);




async function checker() {
console.log(toCheck);

}

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

async function test(postId, userId) {
 // get info from backend

// const button = e.target.nodeName === 'BUTTON'
//
// if(!button) {
// return
// }

// console.log(e.target.id)



 const url = `http://localhost:8080/posts/like?postId=${postId}&visitorId=${userId}`;


 for(i=0; i < document.getElementsByClassName("postId").length; i++) {
    console.log(posts[i].value);
 }

 console.log(JSON.stringify(postId));
 let response = await fetch(url, {
    method: "POST",
    headers: {
    "Content-Type": 'application/x-www-form-urlencoded'
    },
    body: JSON.stringify({
    postId: postId,
    visitorId: userId
    })
 });
 let data = await response.json();
 console.log(data);

 // remove all content already on page
//  page.textContent = '';
//
// // create div for posts
//  showPosts(data, page);
 }

 async function showPosts(data, page) {

 for (i=0; i < data.posts.length; i++) {
 let posts = document.createElement('div');
 posts.classList.add('post');
 posts.classList.add('center');

 let form = document.createElement('form');
 form.setAttribute("method", "POST");
 form.setAttribute("action", "/posts/like");

 let postsTitle = document.createElement('div');
 postsTitle.classList.add('post_title');
 let postsBody = document.createElement('div');
 postsBody.classList.add('post_body');
 let postsUser = document.createElement('div');
 postsUser.classList.add('post_user');
 let postsId = document.createElement('input');
 postsId.setAttribute("type", "hidden");
  postsId.setAttribute("value", "");
   postsId.setAttribute("name", "postIdReal");
 let id = document.createElement('input');
 id.setAttribute("type", "hidden");
   id.setAttribute("value", "");
   id.setAttribute("name", "visitorId");
 postsLikeBtn = document.createElement("button");


 console.log(data.posts);
 console.log(data.posts[i].postTitle);

 // append new content to page
 postsTitle.append(data.posts[i].postTitle);
 postsBody.append(data.posts[i].postBody);
 postsUser.append(data.posts[i].visitorName);
 postsId.append(data.posts[i].postId);
 id.append(await user())

 form.append(postsTitle);
 form.append(postsBody);
 form.append(postsUser);
 form.append(postsId);
 form.append(id);
 form.append(postsLikeBtn);
 posts.append(form);
 page.append(posts);
    }

    return;
 }

 const posts = document.getElementsByClassName("btn")
// user();
console.log(posts);
 for(i=0; i < document.getElementsByClassName("post").length; i++) {
    posts[i].addEventListener("click", (e) => {
    console.log();
    console.log();
    postId = e.target.parentElement[0].value;
    userId = e.target.parentElement[1].value;
    console.log(JSON.parse(postId))
    test(postId, userId);
    })

 }
;
