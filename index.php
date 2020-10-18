<?php
define("MAX_RESULTS", 9);
//////////////////////////////Lấy key về và xét rỗng thì báo lỗi////////////////////////////////////////////////
$keyword = $_GET['keyword'];
$res = array("code"=>-1,"message"=>"Không tìm thấy", "data"=> "");
// Validate dữ liệu
if (empty($keyword)) // nếu key rỗng
{
	echo json_encode($res,256);
	return;
}
//xóa khoảng trống
$keyword = $_GET['keyword'];
//Hàm connect database
$connect = mysqli_connect("localhost", "id10563954_testgetjson", "Minhtam@12345@", "id10563954_listsong");

mysqli_query($connect, "SET NAMES 'utf8'");


//////////////////////////////Lấy key về và xét rỗng thì báo lỗi////////////////////////////////////////////////
/////////////Kiểm tra từ khóa đã có trong DB hay chưa//////////////////////////////////////////////////////////
$connect = mysqli_connect("localhost", "id10563954_testgetjson", "Minhtam@12345@", "id10563954_listsong");
$query = "SELECT ID,song,songkey FROM karaosong WHERE song LIKE '%$keyword%' or keyword LIKE '%$keyword%'";
$result = mysqli_query($connect, $query);

/////////////////////////////////Xét điều kiện biến RESULT//////////////////////////////////////////////////////
//////// <= 0 thì là chưa có trong BD cần gọi hàm liên kết đên YTB và hàm lưu thông tin nhận về/////////////////
if((mysqli_num_rows($result) == 0))
{
	GET_DB_YOUTUBE_AND_SAVE();
	//sleep(1);
	GETDATA_DB();
	return;
}
///////////////////////////////////////Nếu không thì gọi danh sách tất cả bài liên quán đến key/////////////////
else
{
	GETDATA_DB();
}




//////////////////////////////////////LIST  FUNTION/////////////////////////////////////////////////////////////


///////////////////////////////////////////Hàm kn Youtube và save thông tin vào DB//////////////////////////////
function GET_DB_YOUTUBE_AND_SAVE()
{
		$key = $GLOBALS['keyword'];
		$bien ="karaoke";
		$apikey = 'AIzaSyCoJQWujSfW9tpb5SkPA_946ZxJSZtJwEw'; 
		$googleApiUrl = 'https://www.googleapis.com/youtube/v3/search?part=snippet&q=' . $key . '' . $bien.'&maxResults=' . MAX_RESULTS . '&key=' . $apikey;
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_HEADER, 0);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($ch, CURLOPT_URL, $googleApiUrl);
		curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
		curl_setopt($ch, CURLOPT_VERBOSE, 0);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
		$response = curl_exec($ch);
		curl_close($ch);
		$data = json_decode($response);
		$value = json_decode(json_encode($data,256), true);
		$res2 = array("Ten"=>"","ID"=>"");
       
		for ($i = 0; $i < MAX_RESULTS; $i++) {

			if (!isset($value['items'][$i]['id']['videoId']) || !isset($value['items'][$i]['snippet']['description']) || !isset($value['items'][$i]['snippet']['title']))
				{
					// $value['items'][$i]['id']['videoId'] = "1";
					// $description = $value['items'][$i]['snippet']['description'] ="1";
					// $value['items'][$i]['snippet']['title'] = "1";
                    continue;
				}
            $inkyou = "https://www.youtube.com/watch?v=";    
			$videoId = $value['items'][$i]['id']['videoId'];
            $id_key = ''.$inkyou.''.$videoId.'' ;
			$res2["ID"] = $id_key;                       
			$title = $value['items'][$i]['snippet']['title'];
			$res2["Ten"] = $title;  

			//echo "<br>";
			// echo "1." .$res2 ["Ten"];
			// echo "<br>";
			//echo  "2." . $res2["ID"];
			//echo "<br><br>";
			$connect = mysqli_connect("localhost", "id10563954_testgetjson", "Minhtam@12345@", "id10563954_listsong");
			$query2 = "INSERT INTO karaosong VALUES(null,'$title','$id_key','$key')";
            mysqli_query($connect, $query2);  

		}
}


////////////////////////////////////////////Hàm hiện ds video///////////////////////////////////////////////////////
function GETDATA_DB()
{
	$key = $GLOBALS['keyword'];
	$connect = mysqli_connect("localhost", "id10563954_testgetjson", "Minhtam@12345@", "id10563954_listsong");
	$query3 = "SELECT ID,song, songkey FROM karaosong WHERE song LIKE '%$key%' or keyword LIKE '%$key%'";
	$data =mysqli_query($connect, $query3);
	$arraydata = array();
	while ($row = mysqli_fetch_assoc($data)) {
		# code...
		array_push($arraydata, new Lists($row['ID'],$row['song'], $row['songkey']));
	}
	echo json_encode($arraydata,256);
}

class Lists
{
	function __construct($id, $song, $songkey)
	{
		//$key = $GLOBALS['keyword'];
		$this->ID = $id;
		$this->song = $song;
		$this->songkey = $songkey;
		//$this->keyword = $key;
	}
}

?>