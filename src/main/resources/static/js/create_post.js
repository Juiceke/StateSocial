const title = document.getElementById("title_sign");
let title_num = document.getElementById("title_num");
let body = document.getElementById("body_sign");
let body_num = document.getElementById("body_num");
console.log(title);

title.addEventListener("paste", (e) => {
charactersLeftPaste(e, title_num)
})

title.addEventListener("input", (e) => {
charactersLeftInpt(e, title_num, title, title.maxLength)
})

body.addEventListener("paste", (e) => {
charactersLeftPaste(e, body_num)
})

body.addEventListener("input", (e) => {
charactersLeftInpt(e, body_num, body, body.maxLength)
})



function charactersLeftInpt(e, num, characters, max) {
if(num.textContent == 0) {
let difference = 0;
difference = max - characters.textLength;
num.textContent = difference;
} else if(e.inputType == "deleteContentBackward") {
let difference = 0;
difference = max - characters.textLength;
num.textContent = difference;
} else {
console.log(title.maxLength)
num.textContent--;
}
}

function charactersLeftPaste(e, num) {
if(num.textContent == 0 || num.textContent - (e.clipboardData.getData("Text").length + 1) < 0) {
num.textContent = 0;
} else {
num.textContent -= (e.clipboardData.getData("Text").length - 1);
console.log(num);
}
}