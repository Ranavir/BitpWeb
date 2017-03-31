
$(document).ready(function(){
	
	//Get examcode list to dropdown
	
	var examCodeList = function() {
		$.ajax({
    		 "url": "getExamCode.do?reqId=1",
    		 "type": "POST",
    		 "dataType":'json',
    		 success:function(result) {
    			 var option = '<option value="select">Select ExamTitle</option>'
    				 for(var i=0;i<result.length;i++) {
    					 option+='<option value='+result[i].exam_code+'>'+result[i].exam_title+'</option>';
    				 }
    			 $('#examCodeList').html(option);
    		 }
		})
	}
	
	examCodeList();
	


var table = $("#studentList").DataTable({
	"lengthMenu" : [ [ 10, 20, 30, -1 ], [ 10, 20, 30, "All" ] ],
	"processing" : false,
	"ordering" : false,
	"info" : false,
	"bFilter" : false,
	"scrollX" : true,
	"scrollCollapse" : true,
	"columnDefs" : [

	    			{
	    				"targets" : 0,
	    				"data" : "uid"
	    			}, {
	    				"targets" : 1,
	    				"data" : "sn"
	    			}, {
	    				"targets" : 2,
	    				"data" : "ec"
	    			}, {
	    				"targets" : 3,
	    				"data" : "etitle"
	    			},{
	    				"targets" : 4,
	    				"data" : "oemt"
	    			}, {
	    				"targets" : 5,
	    				"data" : "imt"
	    			}, {
	    				"targets" : 6,
	    				"data" : "tcm"
	    			}, {
	    				"targets" : 7,
	    				"data" : "est"
	    			},{
	    				"targets" : 8,
	    				"data" : "eet"
	    			}, {
	    				"targets" : 9,
	    				"data" : "esid"
	    			}, {
	    				"targets" : 10,
	    				"data" : "msoe"
	    			}, {
	    				"targets" : 11,
	    				"data" : "msie"
	    			}, {
	    				"targets" : 12,
	    				"data" : "ss"
	    			}, {
	    				"targets": 13, "data": null, 
	    				"defaultContent": '<button type="button" class="btn btn-default btn-sm btn-edit" action="edit"><span class="glyphicon glyphicon-wrench"></span></button>'
	    			}

	    			],
	
})

var tableLoad = function(exam_code) {
	 $.ajax({
		 "url": "getUserResultByExamCode.do?reqId=2",
		 "type": "POST",
		 "data" :{exam_code:exam_code},
		 "dataType":'json',
		 success:function(result) {
			 //alert(result);
			 table.clear();
			 table.rows.add(result.data).draw();
		 }
	})
}


	$('#examCodeList').on('change', function() {
		  var exam_code =  this.value;
		  if(exam_code != 'select') {
			  tableLoad(exam_code);
			 /* $.ajax({
		    		 "url": "getUserResultByExamCode.do?reqId=2",
		    		 "type": "POST",
		    		 "data" :{exam_code:exam_code},
		    		 "dataType":'json',
		    		 success:function(result) {
		    			 //alert(result);
		    			 table.clear();
						 table.rows.add(result.data).draw();
		    		 }
				})*/
		  }
		 
//edit button function
		  $('#studentList tbody').on( 'click','button[action=edit]',function(){
				 var data = table.row( $(this).parents('tr') ).data();
				 var uid = data.uid;
				 var msoe = data.msoe;
				 var oemt = data.oemt;
				 var exam_code = data.ec;
				 $('#totalMark').val(oemt);
				 $('#uid').val(uid);
				 $('#marktextfield').val(msoe);
				 $('#examcode').val(exam_code);
				 
				 $('#myModalTxtArea').modal('show');
				
					})	  
		  
})
	
	
	$("#mdalSendBtnForAll").click(function() {
		 var exam_code =  $('#examcode').val();
		 //alert(exam_code)
		 var totmark = parseInt($('#totalMark').val(),10); 
		 var userid = parseInt($('#uid').val(),10);
		 var newmark = parseInt($('#marktextfield').val(),10);
		 if(newmark < totmark) {
			 $.ajax({
	    		 "url": "updateNewMark.do?reqId=3",
	    		 "type": "POST",
	    		 "data" :{userid:userid,newmark:newmark,exam_code:exam_code},
	    		 success:function(result) {
	    			 alert("saved successfully");
	    			 $('#myModalTxtArea').modal('hide');
	    			 tableLoad(exam_code);
	    		 }
			})
			
		 }else {
			
			 alert("Secured Mark Cant be Greater Then total mark")
		 }
	}) 
	

	
	
	
	
})