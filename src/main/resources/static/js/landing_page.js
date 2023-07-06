const state = document.getElementById("test");
const page = document.getElementById("pageContent");
const header = document.getElementById("stateHeader");
const postGroup = document.getElementById('pageContent');
console.log(postGroup);

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
            }).then((response) => response.text())
            .then((data) => {
            if(data == "Success") {
                e.target.form.childNodes[9].childNodes[1].innerHTML++
            } else {
                e.target.form.childNodes[9].childNodes[1].innerHTML--
            }
            return data});
}

async function test(postId, userId, e) {
 // get info from backend
 const url = `http://localhost:8080/posts/like?postId=${postId}&visitorId=${userId}`;
 const json = await this.getJSON(url, e);
 console.log(json);

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

 const posts = document.getElementsByClassName("btn");

 for(i=0; i < document.getElementsByClassName("post").length; i++) {
    posts[i].addEventListener("click", (e) => {
    console.log(e.target.form.childNodes[9].childNodes[1].innerHTML)
//    e.target.form.childNodes[9].childNodes[1].innerHTML++
    postId = e.target.form[0].value;
    userId = e.target.form[1].value;
    test(postId, userId, e);
    })

 }
;
