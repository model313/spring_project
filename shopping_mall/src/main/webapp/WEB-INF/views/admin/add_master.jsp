<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/resources/admin/css/mainlogin.css">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="128x128">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="64x64">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="32x32">
    <link rel="icon" href="/resources/admin/img/logo.png" sizes="16x16">
    <script src="/resources/admin/js/add_master.js?v=6"></script>
</head>
<body class="bodycss">
    <header class="admin_title_add">
        <p><img src="/resources/admin/img/logo.png" class="logo_sm"> ADMINISTRATOR ADD</p>
    </header>

    <section class="admin_bgcolor_add">
    <form id="add_admin_frm">
        <div class="admin_login_add">
            <ul>
                <li class="font_color1">아이디 및 패스워드 정보</li>
                <li>
                <input type="text" name="ad_id" id="ad_id" class="add_input1" placeholder="생성할 관리자 아이디를 입력하세요">
                <button type="button" class="btn_button" onclick="id_check()">중복체크</button>
                <input type="hidden" id="checkIdApproved" value="N">
                </li>
                <li>
                    <input type="text" name="ad_pass" id="ad_pass" class="add_input1" placeholder="접속할 패스워드를 입력하세요">
                    <input type="text" class="add_input1" id="pw_check" placeholder="동일한 패스워드를 입력하세요">
                </li>
                <li class="font_color1">관리자 기본정보 입력</li>
                <li>
                    <input type="text" name="ad_name" id="ad_name" class="add_input1" placeholder="담당자 이름을 입력하세요">
                </li>
                <li>
                <input type="text" name="ad_email" id="ad_email" class="add_input1 emails" placeholder="담당자 이메일을 입력하세요">
                </li>
                <li class="font_color1">
                <input type="text" id="tel1" class="add_input1 hp1" placeholder="HP" value="010" maxlength="3">
                -
                <input type="text" id="tel2" class="add_input1 hp2" placeholder="1234" maxlength="4">
                -
                <input type="text" id="tel3" class="add_input1 hp2" placeholder="5678" maxlength="4">
                <input type="hidden" name="ad_tel" id="ad_tel" value="">
                </li>
                <li class="font_color1">관리자 담당부서 및 직책</li>
                <li>
                    <select class="add_select1" name="ad_dep" id="ad_dep">
                        <option value="">담당자 부서를 선택하세요</option>
                        <option>임원</option>
                        <option>전산팀</option>
                        <option>디자인팀</option>
                        <option>CS팀</option>
                        <option>MD팀</option>
                    </select>
                    <select class="add_select1" name="ad_pos">
                        <option value="">담당자 직책을 선택하세요</option>
                        <option>대표</option>
                        <option>부장</option>
                        <option>팀장</option>
                        <option>과장</option>
                        <option>대리</option>
                        <option>주임</option>
                        <option>사원</option>
                    </select>
                </li>
                <li class="font_color1">※ 가입완료 후 전산 담당자가 확인 후 로그인 할 수 있습니다.</li>
            </ul>
            <span class="admin_addbtn">
                <input type="button" value="관리자 등록" class="btn_button btncolor1" title="관리자 등록" onclick="admin_add()">
                <input type="button" value="등록 취소" class="btn_button btncolor2" title="관리자 취소" onclick="cancelAddAdmin()">
            </span>
        </div>
    </form>
    </section>
    <footer class="admin_copy">
        <div>
            Copyright ⓒ 2024 shopbag All rights reserved.
        </div>
    </footer>
</body>
</html>