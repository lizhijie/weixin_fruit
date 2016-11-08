$.getJSON('/json.jsp?pages=Index', function(data){
	for(var i = 0;i < data.length; i++) {
	$("#palist").append(xuanran($("#list").clone(),data[i]));
	}
	$("#list").hide();
	$("#palist").show();
});
function xuanran(list,oneData){
	list.find("h4").text(oneData.name);
	list.find("p").text(oneData.about);
	list.attr("href","http://www.baidu.com");
	return list;
}