<?php

		$keySecret = base64_encode('<<API-KEY>>:<<SECRET>>');
		$path = "https://api.trustpilot.com/v1/oauth/oauth-business-users-for-applications/accesstoken";

		$dataSend	= "grant_type=password&username=<<EMAIL>>&password=<<PASSWORD>>"; //PAYLOAD

		$qrystr=$path;
		$curl = curl_init();

		curl_setopt_array($curl, array(
			CURLOPT_RETURNTRANSFER 	=> 1,
			CURLOPT_URL 			=> $qrystr,
			CURLOPT_POST			=> 1,
			CURLOPT_POSTFIELDS		=> $dataSend,
			CURLOPT_SSL_VERIFYHOST 	=> 0,
			CURLOPT_SSL_VERIFYPEER 	=> 0,

			CURLOPT_USERAGENT => 'cURL Request to REST server',

			CURLOPT_HTTPHEADER => array(

			'Authorization: '.$keySecret,
    		'Content-Type: application/x-www-form-urlencoded'
			)

		));


		$resp = curl_exec($curl);
		print curl_error($curl);

		$httpReturnCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

		if($httpReturnCode != 200){
		   echo "HTTP Error: ".$httpReturnCode;
		}


		 $result = json_decode($resp);
		 $access_token = $result->access_token;
		 echo $access_token;


		 //************************************* FURTHER REQUEST *********

    $path = "https://api.trustpilot.com/v1/private/product-reviews/business-units/<<BIZUNIT-ID>>/invitation-links";


		$dataSend	= ""; // CONSTRUCT THE PAYLOAD HERE AS PER DOCUMENTATION
        //REFER: https://developers.trustpilot.com/product-reviews-api#Create product review invitation link

		$qrystr=$path;
		$curl = curl_init();

		curl_setopt_array($curl, array(
			CURLOPT_RETURNTRANSFER 	=> 1,
			CURLOPT_URL 			=> $qrystr,
            CURLOPT_POSTFIELDS		=> $dataSend,

			CURLOPT_SSL_VERIFYHOST 	=> 0,
			CURLOPT_SSL_VERIFYPEER 	=> 0,

			CURLOPT_USERAGENT => '',

		));


		$resp = curl_exec($curl);print_r($resp);
		print curl_error($curl);

		$httpReturnCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

		if($httpReturnCode != 200){
		   echo "HTTP Error: ".$httpReturnCode;
		}

		//$result = json_decode($resp);
		print_r($resp);

?>