	var app = app || {}

	app.config = jQuery.extend(true, {}, app.config || {}, {
		baseURL: "/sbpm"
	});

	var mrfr = (function(){
		function _mrfr(){
			var _this = this,
			et = $("#root");

			this.init = function (){
				initEventHandlers();
			};

			var initEventHandlers = function (){
				$(window).bind("load", onWindowLoad);
			};
			

			/** events that triggers after the page get completely loaded
			*/
			onWindowLoad = function (){
				FormValidation.init();
				
				//form content repeater
				et.delegate(".repeater-btn", "click", function(e){
					e.preventDefault();
					$(this).siblings(".repeat-box").children(".repeat-me:first-child").clone().append("<div class='remove-me'><span class='btn btn-warning'>Delete</span>").appendTo(".repeat-box");
				});	
				
				et.delegate(".remove-me", "click", function(e){
					$(this).parent().remove();
				});

				/*$('.data-table').DataTable({
					columnDefs: {
						targets: "datatable-nosort",
						orderable: false
					},
					rowReorder: {
							selector: 'td:nth-child(2)'
					},
					responsive: true
				});*/
				
				/*$('.datepicker').datetimepicker({
                   format: 'DD-MM-YYYY'
				});*/
				
				// populating user details in the edit user form
				/*et.delegate(".update_user", "click", function(evt){
					var user_info = JSON.parse($(this).attr('data-info'));
					$("#user_panel_heading").html("Update User");
					$("#user_form_button").val("Update");
					$("#new_user_form input[name='op']").val("edit");
					$("#new_user_form input[name='user_id']").val(user_info.id);
					$("#new_user_form input[name='full_name']").val(user_info.full_name);
					$("#new_user_form [name='role'] option[value='"+user_info.role+"']").prop('selected', true);
					$("#new_user_form input[name='email']").val(user_info.email);
					$("#new_user_form input[name='phone']").val(user_info.emergency_number);
					$("#new_user_form [name='status'] option[value='"+user_info.status+"']").prop('selected', true);
					$("#new_user_form [name='block_name'] option[value='"+user_info.block_id+"']").prop('selected', true);
				});
				
				// populating block details in the edit block form
				et.delegate(".update_block", "click", function(evt){
					var block_info = JSON.parse($(this).attr('data-info'));
					$("#user_panel_heading").html("Update Block");
					$("#block_form_button").val("Update");
					$("#new_block_form input[name='op']").val("edit");
					$("#new_block_form input[name='block_id']").val(block_info.id);
					$("#new_block_form input[name='block_name']").val(block_info.block_name);
					$("#new_block_form textarea[name='block_addr_one']").val(block_info.block_off_addr_one);
					$("#new_block_form input[name='pincode']").val(block_info.pincode);
					$("#new_block_form input[name='block_office_phone']").val(block_info.contact_number);
					$("#new_block_form input[name='block_officer_name']").val(block_info.block_officer_name);
					$("#new_block_form input[name='emergency_number']").val(block_info.emergency_number);
					$("#new_block_form input[name='email']").val(block_info.email);
				});
				
				// populating anganwadi detaisl in the edit anganwadi form
				et.delegate(".update_anganwadi", "click", function(evt){
					var anganwadi_info = JSON.parse($(this).attr('data-info'));
					$("#user_panel_heading").html("Update Anganwadi");
					$("#anganwadi_form_button").val("Update");
					$("#new_anganwadi_form input[name='op']").val("edit");
					$("#new_anganwadi_form input[name='anganwadi_id']").val(anganwadi_info.id);
					$("#new_anganwadi_form input[name='angan_name']").val(anganwadi_info.anganwadi_name);
					$("#new_anganwadi_form [name='block_name'] option[value='"+anganwadi_info.block_id+"']").prop('selected', true);
					$("#new_anganwadi_form textarea[name='angan_addr_one']").val(anganwadi_info.anganwadi_addr_one);
					$("#new_anganwadi_form input[name='pincode']").val(anganwadi_info.pincode);
					$("#new_anganwadi_form input[name='incharge_name']").val(anganwadi_info.incharge_name);
					$("#new_anganwadi_form input[name='incharge_contact_no']").val(anganwadi_info.incharge_contact_number);
				});
				
				// populating chc details in the edit chc form
				et.delegate(".update_chc", "click", function(evt){
					var chc_info = JSON.parse($(this).attr('data-info'));
					$("#chc_panel_heading").html("Update CHC");
					$("#chc_form_button").val("Update");
					$("#new_chc_form input[name='op']").val("edit_chc");
					$("#new_chc_form input[name='chc_id']").val(chc_info.id);
					$("#new_chc_form input[name='chc_name']").val(chc_info.health_center_name);
					$("#new_chc_form [name='block_name'] option[value='"+chc_info.block_id+"']").prop('selected', true);
					$("#new_chc_form textarea[name='chc_addr_one']").val(chc_info.health_center_addr_one);
					$("#new_chc_form input[name='pincode']").val(chc_info.pincode);
					$("#new_chc_form input[name='incharge_name']").val(chc_info.incharge_name);
					$("#new_chc_form input[name='incharge_contact_no']").val(chc_info.incharge_contact_number);
				});
				
				// populating phc details in the edit phc form
				et.delegate(".update_phc", "click", function(evt){
					var phc_info = JSON.parse($(this).attr('data-info'));
					$("#phc_panel_heading").html("Update PHC");
					$("#phc_form_button").val("Update");
					$("#new_phc_form input[name='op']").val("edit_phc");
					$("#new_phc_form input[name='phc_id']").val(phc_info.id);
					$("#new_phc_form input[name='phc_name']").val(phc_info.health_center_name);
					$("#new_phc_form [name='chc_name'] option[value='"+phc_info.chc_id+"']").prop('selected', true);
					$("#new_phc_form textarea[name='phc_addr_one']").val(phc_info.health_center_addr_one);
					$("#new_phc_form input[name='pincode']").val(phc_info.pincode);
					$("#new_phc_form input[name='incharge_name']").val(phc_info.incharge_name);
					$("#new_phc_form input[name='incharge_contact_no']").val(phc_info.incharge_contact_number);
				});
				
				// populating mother details in the edit mother form
				et.delegate(".update_mother", "click", function(evt){
					var mother_info = JSON.parse($(this).attr('data-info'));
					$("#mother_panel_heading").html("Update Mother");
					$("#mother_form_button").val("Update");
					$("#new_mother_form input[name='op']").val("edit");
					$("#new_mother_form input[name='mother_id']").val(mother_info.id);
					$("#new_mother_form input[name='mother_name']").val(mother_info.mother_name);
					$("#new_mother_form input[name='husband_name']").val(mother_info.husband_name);
					$("#new_mother_form input[name='address']").val(mother_info.address);
					$("#new_mother_form [name='block_name'] option[value='"+mother_info.block_id+"']").prop('selected', true);
					$("#new_mother_form [name='anganwadi_name'] option[value='"+mother_info.anganwadi_id+"']").prop('selected', true);
					$("#new_mother_form input[name='pincode']").val(mother_info.pincode);
					$("#new_mother_form input[name='date_of_birth']").val(mother_info.date_of_birth);
					$("#new_mother_form input[name='contact_no']").val(mother_info.contact_no);
					$("#new_mother_form input[name='height']").val(mother_info.height);
					$("#new_mother_form [name='blood_group'] option[value='"+mother_info.blood_group+"']").prop('selected', true);
					//$("#new_mother_form input[name='lmp']").val(mother_info.lmp);
					$("#new_mother_form [name='no_of_gravida'] option[value='"+mother_info.no_of_gravida+"']").prop('selected', true);
					$("#current_gravida_no").text("(Serial No - " + mother_info.no_of_gravida+")");
					$("#new_mother_form input[name='edd']").val(mother_info.edd);
					$("#new_mother_form input[name='prev_delivery_status']").val(mother_info.prev_delivery_status);
					$("#new_mother_form input[name='prev_delivery_still_birth']").val(mother_info.prev_delivery_still_birth);
					$("#new_mother_form input[name='history_of_any_disease']").val(mother_info.history_of_any_disease);
					$("#new_mother_form input[name='month_of_delivery']").val(mother_info.month_of_delivery);
				});*/
				
				// when block name selection changed
				et.delegate("#sel_block_name", "change", function(){
					var block_name = $(this).val();
						target = $(this).attr("data-target");

					if(!_this.checkFalseUndef([block_name, target]))
						return false;

					if(block_name == "")
					{
						$(target).html("");
						return false;
					}

					$.get( app.config.baseURL + "/ajax/get", {op:'GET_BLOCK_ANGANWADI',block_name:block_name} )
					.done(function(data) {
						$(target).html(data);
					}, "html");
				});
			};
			
			this.checkFalseUndef = function(values){
				var x = true;
				$.each(values, function(k,v){
					if(v === false || v == undefined){
						x = false;
						return false
					}
				});
				return x;
			};

			this.checkAllEmpty = function(values){
				var x = true;
				$.each(values, function(k,v){
					if(v == "" || v == null){
						x = false;
						return false
					}
				});
				return x;
			};
			return this;
		}
		return new _mrfr;
	})();
	//JS entry point
	$(function () {
		mrfr.init();
	});