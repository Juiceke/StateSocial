const title = document.getElementById("title_sign");
let title_num = document.getElementById("title_num");
console.log(title);

title.addEventListener("paste", (e) => {
if(title_num.textContent == 0
|| title_num.textContent - (e.clipboardData.getData("Text").length + 1) < 0) {
title_num.textContent = 0;
} else {
title_num.textContent -= (e.clipboardData.getData("Text").length - 1);
console.log(title_num);
}
console.log(e.clipboardData.getData("Text").length);
})

title.addEventListener("input", (e) => {
if(title_num.textContent == 0) {
let difference = 0;
difference = 255 - title.textLength;
title_num.textContent = difference;
} else if(e.inputType == "deleteContentBackward") {
let difference = 0;
difference = 255 - title.textLength;
title_num.textContent = difference;
} else {
title_num.textContent--;
//console.log(e);
}

//title_num
})