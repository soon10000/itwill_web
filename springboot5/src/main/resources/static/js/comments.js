/**
 * comment.js
 * /post/details.html에 포함
 * 댓글 생성, 목록, 수정, 삭제
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
    let currentPageNo = 0; // 현재 댓글 목록의 페이지 번호
    //-> getAllComments() 함수 안에서 Ajax 요청을 보내고, 정상 응답이 오면 현재 페이지 번호가 바뀜.
    //-> currentPageNo의 값은 [더보기] 버튼에서 사용



    // bootstrap 라이브러리의 Collapse 객체를 생성
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', { toggle: false }); // 토글 상태가 false이다.

    // [댓글 보기] 버튼을 찾아서 클릭 이벤트 리스너 설정
    const btnToggle = document.querySelector('button#btnToggle');
    btnToggle.addEventListener('click', () => {
        bsCollapse.toggle(); // Collapse 객체를 보기/감추기 토글


        const toggle = btnToggle.getAttribute('data-toggle');
        if (toggle === 'collapse') { // === 3개는 좌우측 데이터 타입까지 비교  == 2개는 값만 비교
            btnToggle.innerHTML = '댓글 감추기';
            btnToggle.setAttribute('data-toggle', 'unfold'); // toggle의 속성 값이 collap이면, 토글버튼의 텍스트를 '댓글 감추기'로 변경하고 속성값을 'unfold'로 수정
            // 댓글 목록 불러오기:

            getAllComments(0);

        } else {
            btnToggle.innerHTML = '댓글 보기';
            btnToggle.setAttribute('data-toggle', 'collapse'); // toggle의 속성 값이 unfold이면, 토글버튼의 텍스트를 '댓글 감추기'로 변경하고 속성값을 'collapse'로 수정
        }
    });


    //[댓글 등록] 버튼을 찾아서 클릭 이벤트 리스너를 설정
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    btnRegisterComment.addEventListener('click', registerComment);


    // [댓글 더보기] 버튼을 찾아서, 클릭 이벤트 리스너를 설정:
    const btnMoreComments = document.querySelector('button#btnMoreComments');
    btnMoreComments.addEventListener('click', () => getAllComments(currentPageNo + 1)); // TODO 





    // -------------------------------- 함수 선언 -------------------------------------------

    function registerComment() {
        // 댓글이 달린 포스트의 아이디
        const postId = document.querySelector('input#id').value;

        // 댓글 내용
        const ctext = document.querySelector('textarea#commentText').value;

        // 댓글 작성자
        const writer = document.querySelector('input#commentWriter').value;

        if (ctext.trim() === '') { // .trim()을 써주는 이유는 비어있는 공백(스페이스)을 넣었을 때 문자열의 앞 뒤에 있는 공백을 제거해주는 함수 
            alert('댓글 내용을 입력하세요.');
            return;
        }

        // Ajax 요청에서 보낼 데이터
        const data = { postId, ctext, writer }; // javascript에서 {}는 객체를 만듦

        /*        data{
                    postId = postId
                    ctext = ctext
                    writer = writer
                }  이거와 같은 코드 */

        // Ajax POST 방식 요청을 보냄.
        axios.post('/api/comment', data)
            .then((response) => {
                console.log(response.data);
                alert('댓글 작성 완료.');
                // 댓글 텍스트 필드 초기화
                document.querySelector('textarea#commentText').value = '';
                // 댓글목록 갱신
                getAllComments(0);
            })
            .catch((error) => console.log(error));
    }



    function getAllComments(pageNo) {
        // 댓글들이 달린 포스트 아이디:
        const postId = document.querySelector('input#id').value;

        // Ajax 요청을 보낼 주소:
        // path variable: 댓글이 달린 포스트 아이디. request param: 페이지 번호
        const uri = `/api/comment/all/${postId}?p=${pageNo}`;


        // Ajax 요청을 보내고, 성공/실패 콜백 설정
        axios.get(uri)
            .then((responese) => {
                console.log(responese);
                currentPageNo = responese.data.number;
                
                 // 현재 페이지 번호보다 페이지 개수가 더 많으면 댓글 [더보기] 버튼을 보여줌.
                const divBtnMore = document.querySelector('div#divBtnMore');
                // if (currentPageNo + 1 < responese.data.totalPages) {
                    if(!responese.data.last){
                        divBtnMore.classList.remove('d-none');
                    } else {
                        divBtnMore.classList.add('d-none');
                    }
               
                    makeCommentElements(responese.data.content, responese.data.number);
                })
                .catch((error) => console.log(error));
            
    }




    function makeCommentElements(data, pageNo) {
        // 로그인 사용자 정보 -> 댓글 삭제/업데이트 버튼을 만들지 여부를 결정.
        // (속성값에 value가 들어가는 경우 .value / 태그와 태그 사이에 내용이 들어가는 경우 .innerText innerHTML은 HTML코드 전체를 가지고 온다.)
        const authUser = document.querySelector('span#authenticatedUser').innerText;
        
        // 댓글 목록을 추가 할 div 요소 
        const divComments = document.querySelector('div#divComments');

        let htmlStr = ''; // div에 삽입할 html 코드(댓글목록)

        // for in은 인덱스를 넘겨주고 for of는 데이터를 줌
        for (let comment of data) {
            // console.log(comment);
            htmlStr += `
            <div class="card card-body mt-2">
                <div class="mt-2">
                    <span class="fw-bold">${comment.writer}</span>
                    <span class="text-secondary">${comment.modifiedTime}</span>
                </div>
                <div class="mt-2">
                    <div class="mt-2">
                        <textarea class="commentText form-control" data-id="${comment.id}">${comment.ctext}</textarea>
                    </div>
                    `;
                    
                    // 로그인 사용자와 댓글 작성자가 같은 경우에만 삭제/업데이트 버튼을 만듦.
                    if(authUser === comment.writer){
                    htmlStr += `<div class="mt-2">
                        <button class="btnDelete btn btn-outline-danger btn-sm" data-id="${comment.id}" >삭제</button>
                        <button class="btnUpdate btn btn-outline-primary btn-sm" data-id="${comment.id}">수정</button>
                    </div>
                    
                    
                </div>
            </div>
            `;
            } else {
                htmlStr += `
                </div>
            </div>
                `;
            }


        }
        if (pageNo === 0) {
            // 댓글 목록 첫번째 페이지이면, 기존 내용을 다 지우고새로 작성
            divComments.innerHTML = htmlStr;
        } else {
            // 댓글 목록에서 첫번째 페이지가 아니면, 기존 내용 밑에 추가(append)
            divComments.innerHTML += htmlStr;
        }

        // 댓글 [삭제], [수정] 버튼들의 이벤트 리스너는 버튼들이 생겨난 이후에 등록!
        // 모든 button.btnDelete 버튼을 찾아서 클릭 이벤트 리스너를 등록.
        // [댓글 삭제] 모튼 버튼을 찾아서 이벤트 리스너를 설정
        const btnDeletes = document.querySelectorAll('button.btnDelete');
        btnDeletes.forEach((btn) => {
            btn.addEventListener('click', deleteComment);
        });


        // [댓글 업데이트] 모든 버튼을 찾아서 이벤트 리스너를 설정
        const btnUpdates = document.querySelectorAll('button.btnUpdate');
        btnUpdates.forEach((btn) => {
            btn.addEventListener('click', updateComment);
        })


    }



    // 댓글 [삭제], [수정] 버튼들의 이벤트 리스너는 버튼들이 생겨난 이후에 등록!
    // 모든 button.btnDelete 버튼을 찾아서 클릭 이벤트 리스너를 등록.
    function deleteComment(event) {

        // console.log(event);
        // console.log(event.target);
        if (!confirm('정말 삭제할까요?')) {
            return;
        }
        const id = event.target.getAttribute('data-id'); // 삭제할 댓글 아이디
        const uri = `/api/comment/${id}`;  // 삭제 Ajax 요청을 보낼 주소.
        axios.delete(uri)
            .then((response) => {
                console.log(response);
                alert(`댓글 #${id} 삭제 성공.`);
                getAllComments(0); // 댓글 목록 갱신(0페이지 부터 보이게끔)  
            })
            .catch((error) => console.log(error));

    }


    function updateComment(event) {
        // console.log(event.target);
        const id = event.target.getAttribute('data-id'); // 업데이트 할 댓글 아이디
        const textarea = document.querySelector(`textarea.commentText[data-id="${id}"]`);
        const ctext = textarea.value; // 업데이트 할 댓글 내용
        console.log(textarea);
        if (ctext.trim() === '') {
            alert('댓글 내용은 반드시 입력해야 합니다.');
            return;
        }

        if (!confirm('변경된 댓글을 저장할까요?')) {
            return;
        }

        const uri = `/api/comment/${id}`; // Ajax 요청을 보낼 주소
        const data = { id, ctext }; // 업데이트 요청 데이터. {id:id, ctext:ctext}
        console.log(data);
        axios.put(uri, data)
            .then((response) => {
                console.log(response);
                // TODO:
                alert('해당 댓글 수정이 완료되었습니다.');
                getAllComments(0);
            })
            .catch((error) => console.log(error));



    }

















});