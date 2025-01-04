# 📌 To-Do-List

- **프로젝트 명**: To-Do-List 
- **프로젝트 기간**: 2024년 07월 03일 ~ 2024년 11월 25일 ( + 최종 수정 25년 01월 04일 )
<br>

![image](https://github.com/user-attachments/assets/7ef3bf87-3811-4663-8b25-f4900cbfe5af)

<br>

## 프로젝트 명세서 & API 명세서
- <a href="https://o365deu-my.sharepoint.com/:x:/g/personal/20193182_office_deu_ac_kr/EbAdew-JhnFNiDy2H1IBnC0ByMPUyS1jvPGvT00tVA6Y3w?e=cXZDGy" class="no-underline">🔍프로젝트 명세서</a>
- <a href="https://www.postman.com/research-astronomer-95486919/my-workspace/documentation/k66797g/to-do-list" class="no-underline">🔍API 명세서</a> 

## 실행 방법
### 1.  node.js 설치
> 공식 사이트 : https://nodejs.org/en
### 2.  의존성 설치
> npm install
### 3.  서버 실행
> npm run serve
### 4.  프로젝트 서버 실행
> TodolistApplication run

## Architecture & Tools

![image](https://github.com/user-attachments/assets/182efa26-27bb-4723-a258-33ecfec7c11e)


### Screen ( <a href="https://youtu.be/eMcfJc47V1U" class="no-underline">🎬 실행 영상 </a>)


#### 1. 회원가입
- 유효성 검사
- 아이디 ( 이메일 형식 )
- 비밀번호 ( 입력 시 대,소문자 및 특수문자 포함 8자 이상 입력 )
- 비밀번호는 회원가입시 비밀번호 암호화
- 이메일 및 닉네임은 중복 확인
- 시큐리티 적용
 
  
#### 2. 로그인
- jwt 적용
- 시큐리티 적용
- 비밀번호 찾기

#### 3. 마이 페이지
- 회원 정보 조회
- 회원 정보 수정(닉네임, 비밀번호, 소개 등)
- 회원 탈퇴
- 내가 등록한 게시글 목록 확인
- 내 작업목록

<h4>4. 게시판</h4>

- 게시판 (조회, 등록, 수정, 삭제)
- 즐겨찾기
- 좋아요
- 정렬 (오래된 순, 최신 순, 좋아요 순)
- 검색 (제목, 내용, 작성자 등 모두 검색하도록)
- 댓글 (추가,삭제)

+) 게시판 수정과 삭제 및 댓글 삭제는 내가 등록한 댓글이나 게시글만 수정 및 삭제가 되도록

<h4>5. 작업 관리</h4>

- 페이지 목록, 생성, 삭제
- 작업 관리
- 블록 페이지 생성, 목록, 삭제
- 중요도
- 진행 상태 (대기 중, 진행 중, 완료)
- 공유
- 휴지통
- 태그


