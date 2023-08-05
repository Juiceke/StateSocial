//let page = document.getElementById("pageContent");
const logged = document.getElementById("logged");

function getDocHeight() {
    var D = document;
        return Math.max(
            D.body.scrollHeight, D.documentElement.scrollHeight,
            D.body.offsetHeight, D.documentElement.offsetHeight,
            D.body.clientHeight, D.documentElement.clientHeight
        );
}

async function getJSONPosts(url) {
    return await fetch(url, {
    method: "GET",
    headers: {
        "Content-Type": 'application/x-www-form-urlencoded'
        },
        }).then((response) => response.json())
        .then((data) => {
        console.log(data);
        addJSON(data);
            return data});
}

async function setAttributes(el, attrs) {
    Object.keys(attrs)
        .filter(key => el[key] !== undefined)
        .forEach(key =>
        typeof attrs[key] ==='object'
        ? Object.keys(attrs[key])
            .forEach(innerKey => el[key][innerKey] = attrs[key][innerKey])
            : el[key] = attrs[key]
        );
}

async function addJSON(data) {
const newForm = document.createElement("form");
setAttributes(newForm, {
    method: "POST",
    className: "post",
    action: "/posts/like"
});

const newTitle = document.createElement("div");
const newBody = document.createElement("div");
const newUser = document.createElement("div");


setAttributes(newTitle, {
    className: "post_title",
    innerHTML: data[0].postTitle
})

setAttributes(newBody, {
    className: "post_body",
    innerHTML: data[0].postBody
})

setAttributes(newUser, {
    className: "post_user",
    innerHTML: data[0].visitorName
})

newForm.append(newTitle);
newForm.append(newBody);
newForm.append(newUser);

const newOpposites = document.createElement("div");

setAttributes(newOpposites, {
    className: "opposite_sides"
})

const leftDiv = document.createElement("div");
const likeSpan = document.createElement("span");
const rightDiv = document.createElement("div");
const aWrap = document.createElement("a");

setAttributes(likeSpan, {
    className: "likes",
    innerHTML: data[0].likeamnt
})

setAttributes(leftDiv, {
    className: "left",
    innerHTML: "likes "
})

setAttributes(aWrap, {
    href: "/posts?stateId=" + data[0].statesId,
    className: "stateName"
})



setAttributes(rightDiv, {
    className:"bottom right",
    innerHTML: data[0].stateName
})

if(logged.text == "logout") {
const likeDiv = document.createElement("div");
const postId = document.createElement("input");
const visitorId = document.createElement("input");
const btn = document.createElement("input");

setAttributes(postId, {
    type: "hidden",
    value: data[0].postId,
    className: "postId",
    id: "postId",
    name: "postId"
})

setAttributes(visitorId, {
    type: "hidden",
    value: data[0].userPosted.visitorId,
    className: "visitorId",
    id: "visitorId",
    name: "postId"
})

setAttributes(btn, {
    type: "button",
    value: "smile",
    className: "btn"
})

leftDiv.append(likeSpan);

likeDiv.append(postId);
likeDiv.append(visitorId);
likeDiv.append(btn);

newForm.append(leftDiv);

newOpposites.append(likeDiv);

aWrap.append(rightDiv);

newOpposites.append(aWrap);
newForm.append(newOpposites);
} else {
leftDiv.append(likeSpan);

aWrap.append(rightDiv);

newOpposites.append(leftDiv);

newOpposites.append(aWrap);

newForm.append(newOpposites);
}



console.log(posts);

page.append(newForm);

console.log(newForm);
console.log(logged.text)
}

console.log(getDocHeight());
console.log(page.scrollTop || document.body.scrollTop);


console.log(window.innerHeight);

window.onscroll = function () {
var supportPageOffset = window.pageXOffset !== undefined;
var isCSS1Compat = ((document.compatMode || "") === "CSS1Compat");

//    getJson(url)

var scrollLeft = supportPageOffset ? window.pageXOffset : isCSS1Compat ? document.documentElement.scrollLeft : document.body.scrollLeft;
var scrollTop = supportPageOffset ? window.pageYOffset : isCSS1Compat ? document.documentElement.scrollTop : document.body.scrollTop;
    if(scrollTop + window.innerHeight == getDocHeight()) {

        console.log("bottom! Normal!");
        const selectedState = event.target.children[0].children[1].children[0].children[0].children[1][0];
        const selectedStateName = selectedState[selectedState.options.selectedIndex].text
        console.log(selectedState[selectedState.options.selectedIndex].text)
        const amount = posts.length;
        const url = `${window.location.href.split("/")[0]}//` + window.location.href.split("/")[2]+ `/getPostsAsNeeded?state=${selectedStateName}&amount=${amount}`
        console.log(url);
        getJSONPosts(url);
        for(i=0; i < document.getElementsByClassName("post").length; i++) {
            if(document.getElementsByClassName("post")[i].classList.contains("inserted")) {
            } else {
            posts[i].classList.add("inserted");
            posts[i].addEventListener("click", (e) => {
            postId = e.target.form[0].value;
            userId = e.target.form[1].value;
            clicked(postId, userId, e);
            })
            }
//            document.getElementById("1").append(newDiv);
        }
 }
 }