/**
 * even.HTML에 포함. 
 */


// const output = document.querySelector('div#output');
// 
// const btnInput = document.querySelector('button#btnInput'); 
// let text = '';
// btnInput.addEventListener('click', (e) => {
////    console.log(e); // -> PointerEvent 리턴 확인
//    const itemInput = document.querySelector('input#itemInput');
//    const itemList = document.querySelector('ul#itemList');
//    text += itemInput.value + `<br/>`;
//    itemList.innerHTML = text;
//    itemInput.value = ''; 
// });

btnInput.addEventListener('click', (e) => {
    const itemInput = document.querySelector('input#itemInput');
    const itemList = document.querySelector('ul#itemList');
    itemList.innerHTML += `<li>${itemInput.value}</li>`;
    itemInput.value = '';
});


// TODO: input#itemInput2 요소에 'keydown' 이벤트 리스너를 등록:
// 엔터키가 눌렸을 때, input에 입력된 내용을 ul#itemList2의 리스트 아이템으로 추가.
// 이벤트를 우선 찾은 후, 콘솔로그를 통해 하고자 하는 이벤트 정보를 알아내면 작성하기 수월함.
const itemInput2 = document.querySelector('input#itemInput2');
itemInput2.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') { // console.log(e)를 했을 때 콘솔창에 이벤트 정보를 알 수 있음.
        const itemList2 = document.querySelector('ul#itemList2');
        itemList2.innerHTML += `<li>${itemInput2.value}</li>`;
        itemInput2.value = '';
    }
});


// TODO: input#username 요소에 'change' 이벤트 리스너를 등록:
// input에 입력된 내용이 바뀔 때마다 div를 입력 내용으로 덮어씀.
/*const username = document.querySelector('input#username');
const output = document.querySelector('div#output');
username.addEventListener('change', (e) => {
    output.innerHTML = username.value;
    username.value = '';
});
*/

const username = document.querySelector('input#username');
username.addEventListener('change', (e) => {
   console.log(e);  // -> Event
   // change 이벤트는 input이 편집상태가 아니고 (포커스를 잃어버린 상태이고),
   // input에 입력된 값이 이전과 달라진 경우에 발생
   const output = document.querySelector('div#output');
   output.innerHTML = username.value;
}); // 예를들어 회원가입 등 할때 입력을 하다가 다른곳을 클릭했을 때 검사를 하는 경우 change 사용

// TODO: img#bulb 요소에 'mouseenter' 이벤트 리스너를 등록:
// img의 src를 'images/bulb_on.gif'로 변경.
const bulb = document.querySelector('img#bulb');
bulb.addEventListener('mouseenter', (e) => {
    bulb.src = "images/bulb_on.gif";
});


// TODO: img#bulb 요소에 'mouseleave' 이벤트 리스너를 등록:
// img의 src를 'images/bulb_off.gif'로 변경.
bulb.addEventListener('mouseleave', (e) => {
    bulb.src = "images/bulb_off.gif";
});
