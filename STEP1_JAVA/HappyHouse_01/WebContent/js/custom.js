/*
 * Version: 2.1.1
 */

// Notify Plugin - Code for the demo site of HtmlCoder
// You can delete the code below
//-----------------------------------------------
jQuery(document).ready(function() {
	//sido option 추가
	jQuery.each(hangjungdong.sido, function(idx, code) {
		//append를 이용하여 option 하위에 붙여넣음
		jQuery('#city').append(fn_option(code.sido, code.codeNm));
	});

	//sido 변경시 시군구 option 추가
	jQuery('#city').change(function() {
		jQuery('#gu').show();
		jQuery('#gu').empty();
		jQuery('#gu').append(fn_option('', '선택')); //
		jQuery.each(hangjungdong.sigugun, function(idx, code) {
			if (jQuery('#city > option:selected').val() == code.sido)
				jQuery('#gu').append(fn_option(code.sigugun, code.codeNm));
		});

		//세종특별자치시 예외처리
		//옵션값을 읽어 비교
		/*if (jQuery('#city option:selected').val() == '36') {
			jQuery('#gu').hide();
			//index를 이용해서 selected 속성(attr)추가
			//기본 선택 옵션이 최상위로 index 0을 가짐
			jQuery('#gu option:eq(1)').attr('selected', 'selected');
			//trigger를 이용해 change 실행
			jQuery('#gu').trigger('change');
		}*/
	});

	//시군구 변경시 행정동 옵션추가
	jQuery('#gu').change(function() {
		//option 제거
		jQuery('#dong').empty();
		jQuery.each(hangjungdong.dong, function(idx, code) {
			if (jQuery('#city > option:selected').val() == code.sido && jQuery('#gu > option:selected').val() == code.sigugun)
				jQuery('#dong').append(fn_option(code.dong, code.codeNm));
		});
		//option의 맨앞에 추가
		jQuery('#dong').prepend(fn_option('',  '선택'));
		//option중 선택을 기본으로 선택
		jQuery('#dong option:eq("")').attr('selected', 'selected');

	});

	jQuery('#dong').change(function() {
		var cityName = jQuery("#city option:selected").val();
		var dongName = jQuery("#dong option:selected").val();
		//var city = jQuery('#city option:selected').val();
		var gu = jQuery('#gu option:selected').val();
		var url = "./searchRecentHouseDeal.html?city=" + cityName + "&gu=" + gu + "&dong=" + dongName;
		
		window.location.href = url;
		//동네예보 URL
		//searchRecentHouseDeal.do?city=서울특별시&dongcode=11110&dong=청운동
		//var url = 'https://www.weather.go.kr/weather/process/timeseries-dfs-body-ajax.jsp?myPointCode=' + dongCode + '&unit=K';

		//iframe으로 결과 보기
		//fn_iframe(url);
	});
});

function fn_option(code, name) {
	return '<option value="' + code + '">' + name + '</option>';
}
function fn_iframe(url) {
	jQuery('#iframe').attr('src', url);
}
(function($) {

	"use strict";

	$(document).ready(function() {
		if (($(".main-navigation.onclick").length > 0) && $(window).width() > 991) {
			$.notify({
				// options
				message: 'The Dropdowns of the Main Menu, are now open with click on Parent Items. Click "Home" to checkout this behavior.'
			}, {
				// settings
				type: 'info',
				delay: 10000,
				offset: {
					y: 150,
					x: 20
				}
			});
		};
		if (!($(".main-navigation.animated").length > 0) && $(window).width() > 991 && $(".main-navigation").length > 0) {
			$.notify({
				// options
				message: 'The animations of main menu are disabled.'
			}, {
				// settings
				type: 'info',
				delay: 10000,
				offset: {
					y: 150,
					x: 20
				}
			}); // End Notify Plugin - The above code (from line 14) is used for demonstration purposes only

		};
	}); // End document ready

})(jQuery);
