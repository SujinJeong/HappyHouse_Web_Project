<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.ssafy.happyhouse.model.UserDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<%
String root = request.getContextPath();

UserDto userDto = (UserDto) session.getAttribute("userinfo");
%>
<!--  123, 166 Google API Key 입력후 테스트 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BackEnd Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="img/favicon.ico">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
  <nav class="navbar navbar-light bg-light">
    <a class="navbar-brand"></a>
    <form class="form-inline">
      <%
      if (userDto == null) {
      %>
      <a href="<%=root%>/user?act=mvlogin" class="btn btn-outline-success m-2 m-sm-0">로그인</a> <a href="<%=root%>/user?act=mvsignUp" class="btn btn-outline-success m-2 m-sm-0">회원가입</a>
      <%
      } else {
      %>
      <%=userDto.getUserName()%>님 안녕하세요.<br> <a href="<%=root%>/user?act=logout" class="btn btn-outline-success m-2 m-sm-0">로그아웃</a>
      <%
      }
      %>

    </form>
  </nav>



  <div class="container">
    <header id="index_header" class="jumbotron text-center mb-1">
      <h4>행복한 우리 집</h4>
    </header>
    <!-- nav start -->
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
      <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> 동네 정보 </a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#">APT 매매</a> <a class="dropdown-item" href="#">APT 전월세</a> <a class="dropdown-item" href="#">주택 매매</a> <a class="dropdown-item" href="#">주택 전월세</a> <a class="dropdown-item" href="#">상권 정보</a> <a class="dropdown-item" href="#">환경 정보</a>
          </div></li>
        <li class="nav-item"><a class="nav-link" href="#">Notice</a></li>
        <li class="nav-item"><a class="nav-link" href="#">News</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
      </ul>
    </nav>

    <section id="index_section">
      <div class="card col-sm-12 mt-1" style="min-height: 850px;">
        <div class="card-body">
          <script>
											let colorArr = [ 'table-primary',
													'table-success',
													'table-danger' ];
											$(document)
													.ready(
															function() {
																$
																		.get(
																				"${pageContext.request.contextPath}/map",
																				{
																					act : "sido"
																				},
																				function(
																						data,
																						status) {
																					$
																							.each(
																									data,
																									function(
																											index,
																											vo) {
																										$(
																												"#sido")
																												.append(
																														"<option value='"+vo.sido_code+"'>"
																																+ vo.sido_name
																																+ "</option>");
																									});//each
																				}//function
																				,
																				"json");//get
															});//ready
											$(document)
													.ready(
															function() {
																$("#sido")
																		.change(
																				function() {
																					$
																							.get(
																									"${pageContext.request.contextPath}/map",
																									{
																										act : "gugun",
																										sido : $(
																												"#sido")
																												.val()
																									},
																									function(
																											data,
																											status) {
																										$(
																												"#gugun")
																												.empty();
																										$(
																												"#gugun")
																												.append(
																														'<option value="0">선택</option>');
																										$
																												.each(
																														data,
																														function(
																																index,
																																vo) {
																															$(
																																	"#gugun")
																																	.append(
																																			"<option value='"+vo.gugun_code+"'>"
																																					+ vo.gugun_name
																																					+ "</option>");
																														});//each
																									}//function
																									,
																									"json");//get
																				});//change
		$("#gugun").change( function() {	
								$.get("${pageContext.request.contextPath}/map",{
													act : "dong",
													gugun : $(
															"#gugun")
															.val()
												},
												function(
														data,
														status) {
													$("#dong").empty();
													$("#dong").append('<option value="0">선택</option>');
												$.each(data, function(index,
																			vo) {
																		$(
																				"#dong")
																				.append(
																						"<option value='"+vo.dong+"'>"
																								+ vo.dong
																								+ "</option>");
																	});//each
												}//function
						,"json");//get
							});//change
																$("#dong")
																		.change(
																				function() {
																					$
																							.get(
																									"${pageContext.request.contextPath}/map",
																									{
																										act : "apt",
																										dong : $(
																												"#dong")
																												.val()
																									},
																									function(
																											data,
																											status) {
																										$(
																												"#searchResult")
																												.empty();
																										$(
																												"tbody")
																												.empty();
																										$
																												.each(
																														data,
																														function(
																																index,
																																vo) {
																															let str = "<tr class="
																																	+ colorArr[index % 3]
																																	+ ">"
																																	+ "<td>"
																																	+ vo.no
																																	+ "</td>"
																																	+ "<td>"
																																	+ vo.dong
																																	+ "</td>"
																																	+ "<td>"
																																	+ vo.aptName
																																	+ "</td>"
																																	+ "<td>"
																																	+ vo.jibun
																																	+ "</td>"
																																	+ "<td>"
																																	+ vo.code
																																	+ "</td><td id='lat_"+index+"'>"
																																	+ vo.lat
																																	+ "</td><td id='lng_"+index+"'>"
																																	+ vo.lng
																																	+ "</td></tr>";
																															$(
																																	"tbody")
																																	.append(
																																			str);
																															$(
																																	"#searchResult")
																																	.append(
																																			vo.dong
																																					+ " "
																																					+ vo.aptName
																																					+ " "
																																					+ vo.jibun
																																					+ "<br>");
																														});//each
																										geocode(data);
																									}//function
																									,
																									"json");//get
																				});//change

															});//ready

											function geocode(jsonData) {
												initMap(jsonData[0]);
												let idx = 0;
												$
														.each(
																jsonData,
																function(index,
																		vo) {
																	let tmpLat;
																	let tmpLng;
																	$
																			.get(
																					"https://maps.googleapis.com/maps/api/geocode/json",
																					{
																						key : 'Google API KEY',
																						address : vo.dong
																								+ "+"
																								+ vo.aptName
																								+ "+"
																								+ vo.jibun
																					},
																					function(
																							data,
																							status) {
																						tmpLat = vo.lat;
																						tmpLng = vo.lng;
																						//alert(data.results[0].geometry.location.lat);
																						/* tmpLat = data.results[0].geometry.location.lat;
																						tmpLng = data.results[0].geometry.location.lng; */
																						$(
																								"#lat_"
																										+ index)
																								.text(
																										tmpLat);
																						$(
																								"#lng_"
																										+ index)
																								.text(
																										tmpLng);
																						addMarker(
																								vo);
																					},
																					"json");//get
																});//each
											}

											// 동, 아파트 검색 기능
											function search() {
												if($("input[id='searchType1']").is(":checked") == true){
													document.getElementById("searchForm").action = "${root}/search?act=searchByAptName";
													document.getElementById("searchForm").submit();
										        } else if($("input[id='searchType2']").is(":checked") == true){
										        	document.getElementById("searchForm").action = "${root}/search?act=searchByDong";
										        	document.getElementById("searchForm").submit();
										        } else {
										        	alert("카테고리를 선택하세요!");
										        }
											}
										</script>
          시도 : <select id="sido">
            <option value="0">선택</option>
          </select> 구군 : <select id="gugun">
            <option value="0">선택</option>
          </select> 읍면동 : <select id="dong">
            <option value="0">선택</option>
          </select>


          <table class="table mt-2">
            <thead>
              <tr>
                <th>번호</th>
                <th>법정동</th>
                <th>아파트이름</th>
                <th>지번</th>
                <th>지역코드</th>
                <th>위도</th>
                <th>경도</th>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>

          <div id="map" style="width: 100%; height: 500px; margin: auto;"></div>
          <script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
          <script defer src="https://maps.googleapis.com/maps/api/js?key='Google API Key'&callback=initMap"></script>
          <script>
											var multi = {
												lat : 37.5665734,
												lng : 126.978179
											};
											var map;
											function initMap(jsonData) {
												if (jsonData == null) {
													map = new google.maps.Map(
															document
																	.getElementById('map'),
															{
																center : multi,
																zoom : 12
															});
												} else {
													multi.lat = parseFloat(jsonData.lat);
													multi.lng = parseFloat(jsonData.lng);
													map = new google.maps.Map(
															document
																	.getElementById('map'),
															{
																center : multi,
																zoom : 14
															});
												}

											}
											function addMarker(vo) {
												var marker = new google.maps.Marker(
														{
															position : new google.maps.LatLng(
																	parseFloat(vo.lat),
																	parseFloat(vo.lng)),
															label : vo.aptName,
															title : vo.aptName
														});
												marker
														.addListener(
																'click',
																function() {
																	map
																			.setZoom(17);
																	map
																			.setCenter(marker
																					.getPosition());
																	callHouseDealInfo(this, vo);
																});
												marker.setMap(map);
											}
											function callHouseDealInfo(marker, vo) {
												var contentStr = "";

												$.get("${pageContext.request.contextPath}/search"
														,{act:"joinByCode", dong:vo.dong, AptName:vo.aptName}
														,function(data, status){
															contentStr = "<div>";
    															contentStr += "<h5>";
    																contentStr += data[0].AptName;
    															contentStr += "</h5><br>";
    															contentStr += "<h5>";
    																contentStr += vo.dong + " " + data[0].jibun;
    															contentStr += "</h5><br>";
    															contentStr += "<h5> 최저가 : ";
    																contentStr += data[0].dealAmount;
    															contentStr += " 만원</h5>";
															contentStr += "</div>";
															$.each(data, function(index, vo) {
																//
															});//each
														}//function
														, "json"
												).then(function(){
													var infoWindow = new google.maps.InfoWindow(
															{
																content : contentStr,
																maxWidth : 300
															});
													infoWindow.open(map, marker);	
												})
											}
										</script>
                    <div>
          <br><h3>동별/아파트별 실거래가 정보 검색</h3>
          </div>
          <form id="searchForm" method="post">
            <label class="col-sm-1 control-label">
              <input type="checkbox"  class="form-check-input" id="searchType1" name="searchType" value="dong" unchecked> 
              <span class="label label-info">아파트</span>
            </label> 
            <label class="col-sm-1 control-label">
              <input type="checkbox"  class="form-check-input" id="searchType2" name="searchType" value="AptName" unchecked>
              <span class="label label-info">동</span>
            </label> 
            <input type="text" class="form-control" id="searchString" name="searchString" />
            <button type="button" id = "searchBtn" class="btn btn-success" onclick="javascript:search();">검색</button>
          </form>
        </div>
      </div>
    </section>
  </div>
</body>
</html>