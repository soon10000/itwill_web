/**
 *  while.html에 포함
 */

 const list = document.querySelector('#list');
 // const list = document.getElementById('list') 와 동일함
 
 const tableBody = document.querySelector('#tableBody');
 
 let html = ''; // <ul></ul> 태그의 컨텐트로 작성할 HTML 코드.
 
 
 let n = 1;
 while(n <= 5){
    html += `<li> 아이템 ${n} </li>`;
    n++;
 }
 
 list.innerHTML = html;
 
 
 html = '';
 n = 1;
 while(n <= 5){
  html += `<tr>
            <td>${n}</td>
            <td>제목 ${n}</td>
           </tr>`;
  n++; 
}
 
 tableBody.innerHTML = html;