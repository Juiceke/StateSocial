//let page = document.getElementById("pageContent");

function getDocHeight() {
    var D = document;
        return Math.max(
            D.body.scrollHeight, D.documentElement.scrollHeight,
            D.body.offsetHeight, D.documentElement.offsetHeight,
            D.body.clientHeight, D.documentElement.clientHeight
        );
}



console.log(getDocHeight());
console.log(page.scrollTop || document.body.scrollTop);


console.log(window.innerHeight);

window.onscroll = function () {
var supportPageOffset = window.pageXOffset !== undefined;
var isCSS1Compat = ((document.compatMode || "") === "CSS1Compat");

var scrollLeft = supportPageOffset ? window.pageXOffset : isCSS1Compat ? document.documentElement.scrollLeft : document.body.scrollLeft;
var scrollTop = supportPageOffset ? window.pageYOffset : isCSS1Compat ? document.documentElement.scrollTop : document.body.scrollTop;
    if(scrollTop + window.innerHeight == getDocHeight()) {
            console.log("bottom! Normal!");
        }
 }