<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="/css/basic.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/product.css?v=1">
    <link rel="icon" href="/img/logo.png" sizes="128x128">
    <link rel="icon" href="/img/logo.png" sizes="64x64">
    <link rel="icon" href="/img/logo.png" sizes="32x32">
    <link rel="icon" href="/img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./admin_top.jsp" %>
<nav class="navcss">
    <div class="nav_div">
        <ol>
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정">쇼핑몰 기본설정</li>
        </ol>
    </div>

</nav>
<form id="productForm">
<main class="maincss">
<section>
<p>상품 등록 페이지</p>
<div class="product_insert">
    <ul>
        <li>대메뉴 카테고리</li>
        <li>
            <select class="product_input1" name="pr_caname">
            	<cr:choose>
            		<cr:when test="${cateListSize == 0}">
            			<option>카테고리 없음</option>
            		</cr:when>
            		<cr:otherwise>
		            	<option value="">카테고리 선택</option>
		            	<cr:forEach var="cateData" items="${cateResultList}">
			                <option>${cateData.ca_name}</option>
		            	</cr:forEach>
            		</cr:otherwise>
            	</cr:choose>
            </select>
            <input type="button" 
	            value="카테고리 등록" 
	            title="카테고리 등록" 
	            class="product_btn" 
	            onclick="location.href='./cate_list';"
            /> 
            <span class="help_text">※ 해당 카테고리가 없을 경우 신규 등록하시길 바랍니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품코드</li>
        <li>
            <input type="text" 
            	class="product_input1" 
           		name="pr_code"
           		oninput="this.value = this.value.replace(/[^0-9]/g,'')"
         	/> 
            <input type="button" value="자동생성" title="자동생성" class="product_btn" id="codeGenBtn" onclick="codeGen()">
            <input type="button" value="중복확인" title="중복확인" class="product_btn" onclick="codeCheck()">
            <span class="help_text">※ 상품코드는 절대 중복되지 않도록 합니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품명</li>
        <li>
            <input type="text" class="product_input2" maxlength="100" name="pr_name"> 
            <span class="help_text">※ 상품명은 최대 100자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품 부가설명</li>
        <li>
            <input type="text" class="product_input4" maxlength="200" name="pr_info"> 
            <span class="help_text">※ 상품명은 최대 200자까지만 적용할 수 있습니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매가격</li>
        <li>
            <input type="text" 
	            class="product_input3" 
	            maxlength="7" 
	            name="pr_price"
	            oninput="this.value = this.value.replace(/[^0-9]/g,'')"
	            onchange="dcPriceCompute()"
            />
            <span class="help_text">※ , 없이 숫자만 입력하세요 최대 7자리</span>
        </li>
    </ul>
    <ul>
        <li>할인율</li>
        <li>
            <input type="text" 
            	class="product_input3" 
            	maxlength="2" 
            	value="0"
            	name="pr_dc"
            	oninput="this.value = this.value.replace(/[^0-9]/g,'')"
            	onchange="dcPriceCompute()"
           	/>% 
            <span class="help_text">※ 숫자만 입력하세요</span>
        </li>
    </ul>
    <ul>
        <li>할인가격</li>
        <li>
            <input type="text" 
            	class="product_input3" 
            	maxlength="7" 
            	value="0" 
            	name="pr_dcprice"
            	readonly
           	/> 
            <span class="help_text">※ 할인율이 0%일 경우 할인가격은 0으로 처리 합니다.</span>
        </li>
    </ul>
    <ul>
        <li>상품재고</li>
        <li>
            <input type="text" 
            	class="product_input3" 
            	maxlength="4" 
            	value="0"
            	name="pr_stock" 
            	oninput="this.value = this.value.replace(/[^0-9]/g,'')"
           	/>EA 
            <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>판매 유/무</li>
        <li>
            <label class="product_label">
            <input type="radio" name="pr_selluse" style="vertical-align:-1px;" value="Y" checked> 판매시작
            </label>
            <label class="product_label">
            <input type="radio" name="pr_selluse" style="vertical-align:-1px;" value="N"> 판매종료
            </label> <span class="help_text">※ 숫자만 입력하세요. 재고가 0일 경우 soldout이 됩니다.</span>
        </li>
    </ul>
    <ul>
        <li>조기품절</li>
        <li>
            <label class="product_label">
                <input type="radio" name="pr_earlystockuse" style="vertical-align:-1px;" value="Y"> 사용
            </label>
            <label class="product_label">
                <input type="radio" name="pr_earlystockuse" style="vertical-align:-1px;" value="N" checked> 미사용
            </label>
        </li>
    </ul>
    <ul style="height: 160px;">
        <li>상품 대표이미지</li>
        <li>
            <ol style="width:100%; height: auto;">
            <li style="width:100%; height:45px;">
            <input type="file" name="pr_file1" accept="image/*" onchange="onAttachTypeCheck(this)">
            <span class="help_text">※ 상품 대표이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="pr_file2" accept="image/*" onchange="onAttachTypeCheck(this)">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            <li style="height:45px;">
            <input type="file" name="pr_file3" accept="image/*" onchange="onAttachTypeCheck(this)">
            <span class="help_text">※ 추가 이미지 이며, 이미지 용량은 2MB 까지 입니다.</span>
            </li>
            </ol>
        </li>
    </ul>
    <ul style="height: 400px;">
        <li>상품 상세설명</li>
        <li>
            <textarea class="product_text1"></textarea>
        </li>
    </ul>
</div>
<div class="subpage_view4" style="text-align:center; margin-bottom: 100px;">
    <input type="button" value="상품 리스트" title="상품 리스트" class="p_button p_button_color1" style="margin-right: 5px;">
    <input type="button" value="상품 등록" title="상품 등록" class="p_button p_button_color2" onclick="productFormSend()">
</div>
</section>
</main>
</form>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="/js/product_write.js?v=10"></script>
</html>