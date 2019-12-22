# GivMeGift-RUA

### Web App

In the Web App folder, the file to call the guzzle and handle the API is amazonsearcher.php and ./lib/Amazon.php. The results will be returned and displayed in amazonsearcher.html

What you need to do is to modify the ./.env and add your own associate tag and key, deploy it on the apache, then everything will be OK.

### Native App

In the Native app folder, the request is sent as a http url request in java. The file is ./app/src/main/java/com/example/RUA/myapplication/TestApi.java. You also need to add your key and ID to make it work. Besides that, the results are returned as a XML file, so we have  also created several classes to handle the data processing. See XmlUtil.java and ProcessXML.java

The following are the example figures:

<div style="width:450px" align=center><img width="200" height="400" alt="Web app Amazon API" src="https://github.com/lidall/Gift_selection_system/blob/master/Images/api.jpeg"/><img width="200" height="400" alt="Native app Amazon API" src="https://github.com/lidall/Gift_selection_system/blob/master/Images/api_native.png"/>
</div>