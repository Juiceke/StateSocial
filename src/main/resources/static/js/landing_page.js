const state = document.getElementById("test");
const page = document.getElementById("pageContent");
const header = document.getElementById("stateHeader");

window.onscroll = function() {stickyHeader()};

const sticky = header.offsetTop

function stickyHeader() {
    if(window.pageYOffset > sticky) {
        header.classList.add("sticky");
    } else {
        header.classList.remove("sticky");
    }
}

async function test() {
 // get info from backend
 const stateText = state.options[state.selectedIndex].value;
 const url = `http://localhost:8080/states/${stateText}/api`;
 console.log(url);
 let response = await fetch(url);
 let data = await response.json();

 // remove all content already on page
  page.textContent = '';

 // create div for posts
  showPosts(data, page);
 }

 async function showPosts(data, page) {

 for (i=0; i < data.posts.length; i++) {
 let posts = document.createElement('div');
 posts.classList.add('post');
 posts.classList.add('center');

 let postsTitle = document.createElement('div');
 postsTitle.classList.add('post_title');
 let postsBody = document.createElement('div');
 postsBody.classList.add('post_body');
 let postsUser = document.createElement('div');
 postsUser.classList.add('post_user');

 console.log(data.posts);
 console.log(data.posts[i].postTitle);

 // append new content to page
 postsTitle.append(data.posts[i].postTitle);
 postsBody.append(data.posts[i].postBody);
 postsUser.append(data.posts[i].visitorName)

 posts.append(postsTitle);
 posts.append(postsBody);
 posts.append(postsUser);
 page.append(posts);
    }

    return;
 }