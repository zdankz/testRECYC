<?php
$keyword = $_GET['keyword'];
$inkyou = "Ngyfmai la thu ba";
$inkyou = str_replace(' ','',$inkyou);
$keyword = str_replace(' ','',$keyword);

$bidkey = "fsfjhff";
echo $inkyou;
echo "<br/>";
echo $keyword;


// $ham = "LạcTrôi";

// //echo convert_vi_to_en($ham);
// function convert_vi_to_en($str) {
// $str = preg_replace("/(à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|� �|ặ|ẳ|ẵ)/", 'a', $str);
// $str = preg_replace("/(è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ)/", 'e', $str);
// $str = preg_replace("/(ì|í|ị|ỉ|ĩ)/", 'i', $str);
// $str = preg_replace("/(ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|� �|ợ|ở|ỡ)/", 'o', $str);
// $str = preg_replace("/(ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ)/", 'u', $str);
// $str = preg_replace("/(ỳ|ý|ỵ|ỷ|ỹ)/", 'y', $str);
// $str = preg_replace("/(đ)/", 'd', $str);
// $str = preg_replace("/(À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|� �|Ặ|Ẳ|Ẵ)/", 'A', $str);
// $str = preg_replace("/(È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ)/", 'E', $str);
// $str = preg_replace("/(Ì|Í|Ị|Ỉ|Ĩ)/", 'I', $str);
// $str = preg_replace("/(Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|� �|Ợ|Ở|Ỡ)/", 'O', $str);
// $str = preg_replace("/(Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ)/", 'U', $str);
// $str = preg_replace("/(Ỳ|Ý|Ỵ|Ỷ|Ỹ)/", 'Y', $str);
// $str = preg_replace("/(Đ)/", 'D', $str);
// $str = preg_replace("/( )/", '_', $str);
// return $str;
// }
// function dau($str)
// {
// 	$str =  preg_replace("/(�)/", '', $str);
// }
// for($i= 0; $i < strlen($ham);$i++)
// {
// 	for($j = $i; $j < strlen($ham);$j++)
// 	{
// 		echo "$ham[$i]<br/>";
// 		convert_vi_to_en($ham[$i]);
		
// 		echo "$ham[$i]<br/>";
// 		dau($ham[$i]);
// 	}

// }
// $str = "DammiO.com";
 
// // Viết thường toàn bộ ký tự
// echo strtolower($str) . "<br/>";
 
// // Viết hoa toàn bộ ký tự
// echo strtoupper($str);

?>