<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>


<html>
<head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="/lol/resources/js/jquery-3.5.1.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/scrap.js"></script>

<meta charset="UTF-8">
<style type="text/css">
#d1{
	float:left;
	padding-top:50px;
    padding-left: 100px;
}
#adminList{
width: 350px;
}
#t1{
margin-left: -100px;

}
td{
width: 100px;}
#t1 tr{
height: 70px;
}

.button{
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;

  font-size: 16px;

}
#new{
	float:left;
		padding-top:50px;
    padding-left: 100px;
}

#addTeamname {
	border:solid 2px black;
  font-size: 16px;
}
#removeTeambtn{
  background-color: red; /* Green */
  border: 3px;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;

}

select{
width: 150px;
margin-bottom: 5px;
}







#addmatch{
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
 
  font-size: 16px;

}


</style>
<title>Insert title here</title>
</head>
<body>
<div id="allList33">
<div id="d1" ></div>


<div id="new">
<input type="button" value="새로운팀추가" id="addTeam" class="btn btn-primary" style='width:150px; margin-bottom: 5px; ' disabled="disabled">	
	
	

		<br>
		<input type="text" id="addTeamname"      style='padding-bottom: 7px;'> <br>
		<input type="button" id="addTeambtn"  value="팀추가" class="btn btn-warning" style='width:120px; margin-top:5px'><br>
		<br>
		<select id='addmatch1' >
</select>
<select id='addmatch2' >
</select><br>
	<input type="button" id="matchinsert"  class="btn btn-success" value="경기등록" style='width:150px;'>

	


</div>


	<table class="table table-striped" id='t1'>
<thead>
<tr><td>매치번호</td><td>TEAM1</td><td>TEAM2</td><td>승리팀선택</td><td>승리팀</td><td>상태</td><td></td></tr>
</thead>
<tbody></tbody>
</table>


</div>
<input type="hidden" id="test11" >



</body>
<script type="text/javascript">
teamList();
	$(function() {		
		
		$("#teamDelete").click(function() {
			let team1 = $("#addmatch1 option:selected").val();
			let team2 = $("#addmatch2 option:selected").val();
			$("#addmatch1 option[value='" + team1 + "']").remove();
			$.ajax({
				dataType: 'json',
				url:"/lol/battingdeleteTeam?tnum="+team1,
				tyoe:'get',
				
				success:function(data){
					alert("삭제성공")
				}
			})

		});
		$("#pointgo").click(function(){
			console.log("ddddddddddd")
		})
		$("#teamDelete2").click(function() {
			let team2 = $("#addmatch2 option:selected").val();
			$("#addmatch2 option[value='" + team2 + "']").remove();
		});
		
		let today = new Date();   

		let yyy = today.getFullYear(); // 년도
		let mmm = today.getMonth() + 1;  // 월
		let ddd = today.getDate();  // 날짜
		let day = today.getDay();  // 요일
		var coco=yyy +"/"+ mmm +"/"+ ddd;
		dateList(coco);
		console.log(coco);
		$("#d1").datepicker({
			  dateFormat: 'yy/mm/dd',
		        prevText: '이전 달',	
		        nextText: '다음 달',
		        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	
		        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	
		        dayNames: ['일', '월', '화', '수', '목', '금', '토'],	
		        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],	
		        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],	
		        onSelect:function(d){
		        
		        	var dd=d;
		        	var matchday=dd.split("/");
		        
		        	var matchdayin=matchday[0]+"-"+matchday[1]+"-"+matchday[2];
		        	$("#test11").val(matchdayin);
		        
		        	dateList(d);

		        }
		})
		
	        	$("#matchinsert").click(function(){
	        		
	        	
	        		var matchdayin=$("#test11").val();
	        		let ttime=($("#test11").val())
		        		let team1=$("#addmatch1 option:selected").val();
		        		let team2=$("#addmatch2 option:selected").val();
		        		if(ttime==""){
		        			alert("날짜를 선택하세요")
		        			return;
		        		}else{
		        			console.log("노공백")
		        		
		        		
		        		
		        		$.ajax({
		        			url:"/lol/insertmatch",
		        			dataType: 'json',
		        			data:{
		        				mDate:matchdayin,tNum1:team1,tNum2:team2,mRate:'500'
		        				},
		        			tyep:'get',
		        			success: function(data){
		        			var a=	$("#test11").val();
		        			var matchday=a.split("-");
				        	var matchdayin=matchday[0]+"/"+matchday[1]+"/"+matchday[2];
				        	$("#tb").empty();
				        	dateList(matchdayin);
		        			console.log(a);
		        			}
		        		})
		        		}
		        	})
	        	

$("#addmatch1").change(function(){
	let team1=$("#addmatch1 option:selected").val();
	let team2=$("#addmatch2 option:selected").val();
	var add1index=$("#addmatch2 option").index($("#addmatch2 option:selected"))+1;
	if(team1==team2){
		alert("같은팀 선택불가");
		if(add1index>8){
			add1index=1;
		$("#addmatch1 option:eq("+add1index+")").attr("selected","selected");
		}else{
			$("#addmatch1 option:eq("+add1index+")").attr("selected","selected");
		}
	}
})
$("#addmatch2").change(function(){
	let team1=$("#addmatch1 option:selected").val();
	let team2=$("#addmatch2 option:selected").val();
	var add2index=$("#addmatch2 option").index($("#addmatch2 option:selected"))+1;
	

	if(team1==team2){
		alert("같은팀 선택불가");
		if(add2index>8){
			add2index=1;
		$("#addmatch2 option:eq("+add2index+")").attr("selected","selected");
		}else{
			$("#addmatch2 option:eq("+add2index+")").attr("selected","selected");
		}
		
	}
})
			$("#addTeambtn").click(function() {
				var teamname = $("#addTeamname").val();
				$.ajax({
					url : "/lol/insertTeam",
					type : 'get',
					dataType : 'json',
					data : {
						'tname' : teamname
					},
					success : function(data) {
						console.log(data);
						if (data.code == 'success') {
							$("#addmatch1").empty();
							$("#addmatch2").empty();
							teamList();
							alert("팀 추가 완료");
							$("#addTeamname").val("");
						
							
						} else {
							$("#addmatch1").empty();
							$("#addmatch2").empty();
							teamList();
							alert("팀 추가 실패");
							$("#addTeamname").val("");
						
							
						}
					}
				})
			});
		$("#addTeam").click(function() {
			$("#addTeamname").css({
				display : "inline"
			
			});
			$("#addTeambtn").css({
				display : "inline"
			});
			$("#removeTeambtn").css({
				display: "inline"
			})
				$("#addmatch1").css({
					display: "inline"
				})
					$("#addmatch2").css({
					display: "inline"
				})
			$("#addTeam").prop("disabled",true);
			$("#addTeam").css('backgroundColor', 'gray');
			

			
			$("#removeTeambtn").click(function() {
				$("#addTeam").prop("disabled",false);
				$("#addTeam").css('backgroundColor', '#4CAF50');
				$("#addTeamname").css({
					display : "none"
				});
				$("#addTeambtn").css({
					display : "none"
				});
				$("#removeTeambtn").css({
					display: "none"
				})
			});
			$("#teamDelete").click(function() {
				
			});

		})
/*		$("#d2").datepicker({
			  dateFormat: 'yy/mm/dd',
		        prevText: '이전 달',	
		        nextText: '다음 달',
		        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	
		        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	
		        dayNames: ['일', '월', '화', '수', '목', '금', '토'],	
		        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],	
		        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],	
			onSelect:function(d){
				dateList(d);
			}
		  }) */
	});
	
function yesorno(aa,bb,cc,dd,key,d){
	if(confirm("승리한팀이 맞습니까?")) {
		var dayone=$("#test11").val();
		var daytwo=dayone.split("-");
		var tod=daytwo[0]+"/"+daytwo[1]+"/"+daytwo[2];
		console.log(tod);

		$("#t1 tbody").empty();
		console.log(d);
		console.log("aaㅁㅁㅁㅁㅁ"+aa);
		console.log("매치번호"+bb);
		console.log("팀번호"+cc);
		console.log("승리팀"+dd);
		console.log($("#"+aa).parent());
		
		
		/* $("<span style='color:red'>홈팀 승리 입니다</span>").appendTo("#matchList3"); */
		$.ajax({
			url:'/lol/winupdate',
			dataType: 'json',
			data:{
				mNum:bb,mWinlose:cc
			},
			success : function(data){
				console.log(data);
				
				dateList(tod);
			}
		})
	}else{
		return;
	}
}function yesorno2(aa,bb,cc,dd,key){
	if(confirm("승리한팀이 맞습니까!?")) {
		
		var dayone=$("#test11").val();
		var daytwo=dayone.split("-");
		var tod=daytwo[0]+"/"+daytwo[1]+"/"+daytwo[2];
		console.log(tod);

		$("#t1 tbody").empty();
		console.log("aaㅁㅁㅁㅁㅁ"+aa);
		console.log("매치번호"+bb);
		console.log("팀번호"+cc);
		console.log($("#"+aa).parent());
		console.log($("#"+aa).parent());
		console.log($("#"+aa).next().next().find("input"));
		console.log()
		$("#"+aa).next().find("span").html("<input type='button' id='"+key+"' onclick='pointgo("+bb+","+dd+",event)' class='button' value='포인트 즉시지급'>")
		/* $("<span style='color:red'>홈팀 승리 입니다</span>").appendTo("#matchList3"); */
				$.ajax({
			url:'/lol/winupdate',
			dataType: 'json',
			data:{
				mNum:bb,mWinlose:cc
			},
			success : function(data){
				console.log(data);
				dateList(tod);
				
			}
		})
	}else{
		return;
	}
}
function teamList(){

	$.ajax({
		url:"/lol/listTeam",
		dataType: 'json',
		type:'get',
		success:function(data){
			$.each(data.list,function(key,value){
				console.log(value)
				console.log(value.tname);
				if(value.tname!=null){
			
				var team1=value.tname;
				$("#addmatch1").append("<option value='"+value.tnum+"'>"+value.tname+"</option> ");
				$("#addmatch2").append("<option value='"+value.tnum+"'>"+value.tname+"</option> ");
				
				var team1=$("#addmatch1 option:selected").text();
				var team2=$("#addmatch2 option:selected").text();
				
			console.log(team2)
				}else{
					return;
				}
			})
		}
	});
	}

	function dateList(d){
		$("#t1 tbody").empty();
		console.log(d);
		if(!d){
			return;
		}
		
		var today=$(this).datepicker("getDate");
		$.ajax({
			url:'/lol/match/yesdaylist',
			dataType: 'json',
			data:{
				mDate:d
			},
			success:function(data){
				console.log(data.matchinfo.length);
				if(data.matchinfo.length==0){
					$(".table tbody").append("<tr><td>오늘 경기가 없습니다. </td></tr>")
					
				}  else{
					
	 		$.each(data.matchinfo,function(key,value){
	 			console.log(data);
	 			console.log(key+"키");
	 			console.log(value)
	 			console.log(data.matchinfo);
	 			console.log(value)
	 			if(data.matchinfo.length>0){
	 				console.log(data.matchinfo);
	 				var team1win="<a href='javascript:yesorno("+key+","+data.matchinfo[key].MNUM+","+value.TNUM1+")'>"+data.matchinfo[key].B1NAME+"</a>";
	 				var team2win="<a href='javascript:yesorno2("+key+","+data.matchinfo[key].MNUM+","+value.TNUM2+")'>"+data.matchinfo[key].B2NAME+"</a>";
	 				var aa=value.TNUM1;
	 				console.log(value.TNUM1);
	 				var str="<div id='"+key+"' class='mm' '><h3>"+(key+1)+"번째 경기<span></span></h3></div>";
	 				console.log(data.matchinfo[key].MWINLOSE+"아랄랄");
	 				console.log(data.matchinfo[key].TNUM1+"dddddddddddddddd");
	 				console.log(data.matchinfo[key].point+"포인트")
	 				var kk="<tr><td>"+(key+1)+"번째경기</td>";
	 				if(((value.MWINLOSE==value.TNUM1) && (value.POINT==0))){
	 					kk+="<td>"+value.B1NAME+"</td><td>"+value.B2NAME+"</td><td><input type='button' class='btn btn-info' value='"+value.B1NAME+"'  onclick='yesorno("+key+","+data.matchinfo[key].MNUM+","+value.TNUM1+","+data.matchinfo[key].MWINLOSE+","+key+3+","+d+")'><input type='button' value='"+value.B2NAME+"' class='btn btn-warning' onclick='yesorno2("+key+","+data.matchinfo[key].MNUM+","+value.TNUM2+","+data.matchinfo[key].MWINLOSE+","+key+4+","+d+")'></td><td>"+value.B1NAME+"</td><td><input type='button' id='"+key+3+"' value='포인트지급' onclick='pointgo("+value.MNUM+","+value.MWINLOSE+",event)' class='btn btn-danger'></td></tr>";
	 					
	 				}else if(((value.MWINLOSE==value.TNUM2) && (value.POINT==0)) ){
	 					kk+="<td>"+value.B1NAME+"</td><td>"+value.B2NAME+"</td><td><input type='button' class='btn btn-info' value='"+value.B1NAME+"' onclick='yesorno("+key+","+data.matchinfo[key].MNUM+","+value.TNUM1+","+data.matchinfo[key].MWINLOSE+","+key+3+","+d+")'><input type='button' value='"+value.B2NAME+"' class='btn btn-warning' onclick='yesorno2("+key+","+data.matchinfo[key].MNUM+","+value.TNUM2+","+data.matchinfo[key].MWINLOSE+","+key+4+","+d+")'></td><td>"+value.B2NAME+"</td><td><input type='button' id='"+key+3+"' value='포인트지급' onclick='pointgo("+value.MNUM+","+value.MWINLOSE+",event)' class='btn btn-danger'></td></td></tr>";
	 					
	 				}else if(value.POINT==1){
	 					if(value.TNUM1==value.MWINLOSE){
	 					kk += "<td>"+value.B1NAME+"</td><td>"+value.B2NAME+"</td><td>종료된경기입니다.</td><td>"+value.B1NAME+"</td><td>지급완료</td></tr>";
	 					}else if(value.TNUM2==value.MWINLOSE){
	 						kk += "<td>"+value.B1NAME+"</td><td>"+value.B2NAME+"</td><td>종료된경기입니다.</td><td>"+value.B2NAME+"</td><td>지급완료</td></tr>";
	 					}
	 					str +="<div id='matchList3' '>"+data.matchinfo[key].B1NAME+"vs"+data.matchinfo[key].B2NAME+"<br>"+
	 					"<span id='span11'></span>포인트 지급이 완료 되었습니다..</span></div> ";
	 				
	 				}else{
	 					kk += "<td>"+value.B1NAME+"</td><td>"+value.B2NAME+"</td><td><input type='button' value='"+value.B1NAME+"' class='btn btn-info' onclick='yesorno("+key+","+data.matchinfo[key].MNUM+","+value.TNUM1+","+data.matchinfo[key].MWINLOSE+","+key+3+","+d+")'><input type='button' class='btn btn-warning' value='"+value.B2NAME+"' onclick='yesorno2("+key+","+data.matchinfo[key].MNUM+","+value.TNUM2+","+data.matchinfo[key].MWINLOSE+","+key+4+","+d+")'></td><td>경기시작전</td><td>경기시작전</td><td><a href='javascript:deletematch("+value.MNUM+");'>x</a></td></tr>";
						
	 				}
		 			
	 					$(".table tbody").append(kk);
	 				
	 				
	 				
					
			}
					
				
				})
				}
			}
		})
	}
	
	
	function deletematch(mnum){
		if(confirm("해당 경기를 삭제 하시겠습니까?")){
			var mnum1=mnum;
			$.ajax({
				url:'/lol/match/deletematch',
				data:{
					
					mnum:mnum1
				},
				dataType:'json',
				success:function(data){
					var dayone=$("#test11").val();
					var daytwo=dayone.split("-");
					var tod=daytwo[0]+"/"+daytwo[1]+"/"+daytwo[2];
					alert("성공");		
					$("#t1 tbody").empty();
					dateList(tod);
				}
				
			})
			
		}else{
			return;
		}
	
		
	}
function pointgo(mnum,mwinlose,e){
	var dayone=$("#test11").val();
	var daytwo=dayone.split("-");
	var tod=daytwo[0]+"/"+daytwo[1]+"/"+daytwo[2];
	console.log(tod);
	if(confirm("해당팀이 승리팀이 맞습니까?")){
		
		console.log(e.target);
		var aa=e.target.id;
		var bb=e.target;

	$('#'+aa).prop("disabled", true);

	$.ajax({
		url:'/lol/batting/pointGo',
		data:{
			mnum:mnum
		},
		dataType: 'json',
		success:function(data){
		console.log(data);
	
		$.each(data.list,function(key,value){
			
			
			$.ajax({
				url:'/lol/batting/pointInsert',
				data:{
					score:data.list[key].MRATE,username:data.list[key].USERNAME,mNum:mnum
				}
			})
		})
			$("#t1 tbody").empty();
	dateList(tod);
		alert("포인트지금완료");
	
		}
	})
	}else{
		return;
	}
}
</script>

</html>