jQuery.validator.addMethod("landphone", function(value, element) {
    return this.optional(element) || /^([0-9]{4}[\-]{1}[0-9]{7})$/i.test(value);
}, "plz enter 4-digits std code and 7 digitd number");


jQuery.validator.addMethod("mobileorlandphone", function(value, element) {
    return this.optional(element) || 
            ( /^-?\d+$/.test(value) && value.length == 10 ) || 
            ( /^([0-9]{4}[\-]{1}[0-9]{7})$/i.test(value) || /^([0-9]{4}[\-]{1}[0-9]{6})$/i.test(value) );
}, "Either enter mobile or land line number");


jQuery.validator.addMethod("cusinteger", function(value, element) {
    return this.optional(element) || 
            ( /^\d+$/.test(value) )
}, "Not a valid whole number(e.g. 40)");


jQuery.validator.addMethod("greaterThanMaxAdult", function (value, element, param) {
    var $min = $(param);
    if (this.settings.onfocusout) {
        $min.off(".validate-greaterThan").on("blur.validate-greaterThan", function () {
            $(element).valid();
        });
    }
    return parseInt(value) >= parseInt($min.val());
}, "Max Adult must be greater than Total Adult");

jQuery.validator.addMethod("smallerThanMaxAdult", function (value, element, param) {
    var $min = $(param);
    if (this.settings.onfocusout) {
        $min.off(".validate-greaterThan").on("blur.validate-greaterThan", function () {
            $(element).valid();
        });
    }
    return parseInt(value) <= parseInt($min.val());
}, "Max Adult must be greater than Total Adult");






var FormValidation = function () {
    // Login form validation
	var login_form = function() {
        var eLoginForm = $('#login_form');
        eLoginForm.validate({
            errorElement: 'span', 
            errorClass: 'help-inline', 
            focusInvalid: true, 
            ignore: "",
            rules: {
                username: { required: true },
                password: { required: true }
            },
            //display error alert on form submit              
            invalidHandler: function (event, validator) { },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
					
				//customized error display
				var myFunction = function() {
					$(element).next().children(".ah").html($(element).siblings(".help-inline").text());
				}
				$(element)
				.keyup(myFunction)				
				.change(myFunction);
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            /*success: function (label) {
                label.addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            },*/

            submitHandler: function (form) {
                form.submit();
            }
        });
    }

	var new_user_form = function() {
        var new_user_form = $('#new_user_form');
        new_user_form.validate({
            errorElement: 'span', 
            errorClass: 'help-inline', 
            focusInvalid: true, 
            ignore: "",
            rules: {
                full_name: { required: true },
                email: { email: true },
                role: { required: true},
                phone: { required: true, cusinteger:true },
            },

            invalidHandler: function (event, validator) {},

            // hightlight error inputs
            highlight: function (element) { 
                $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
					
				//customized error display
				var myFunction = function() {
					$(element).next().children(".ah").html($(element).siblings(".help-inline").text());
				}
				$(element)
				.keyup(myFunction)				
				.change(myFunction);
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            /*success: function (label) {
                label.addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            },*/

            submitHandler: function (form) {
                form.submit();
            }
        });
    }
	
	var new_block_form = function() {
        var new_block_form = $('#new_block_form');
        new_block_form.validate({
            errorElement: 'span', 
            errorClass: 'help-inline', 
            focusInvalid: true, 
            ignore: "",
            rules: {
                block_name: { required: true },
                block_addr_one: { required: true },
                pincode: { required: true},
                block_office_phone: { required: true },
                block_officer_name: { required: true },
                emergency_number: { required: true, cusinteger: true },
                email: { required: true, email: true }
            },

            invalidHandler: function (event, validator) {},

            // hightlight error inputs
            highlight: function (element) { 
                $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
					
				//customized error display
				var myFunction = function() {
					$(element).next().children(".ah").html($(element).siblings(".help-inline").text());
				}
				$(element)
				.keyup(myFunction)				
				.change(myFunction);
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            /*success: function (label) {
                label.addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            },*/

            submitHandler: function (form) {
                form.submit();
            }
        });
    }
	
	var new_anganwadi_form = function() {
        var new_anganwadi_form = $('#new_anganwadi_form');
        new_anganwadi_form.validate({
            errorElement: 'span', 
            errorClass: 'help-inline', 
            focusInvalid: true, 
            ignore: "",
            rules: {
                angan_name: { required: true },
                block_name: { required: true },
                angan_addr_one: { required: true },
                pincode: { required: true},
                incharge_name: { required: true },
                incharge_contact_no: { required: true, cusinteger: true }
            },

            invalidHandler: function (event, validator) {},

            // hightlight error inputs
            highlight: function (element) { 
                $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
					
				//customized error display
				var myFunction = function() {
					$(element).next().children(".ah").html($(element).siblings(".help-inline").text());
				}
				$(element)
				.keyup(myFunction)				
				.change(myFunction);
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            /*success: function (label) {
                label.addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            },*/

            submitHandler: function (form) {
                form.submit();
            }
        });
    }
	
	var new_pregnancy_form = function() {
        var new_pregnancy_form = $('#new_pregnancy_form');
        new_pregnancy_form.validate({
            errorElement: 'span', 
            errorClass: 'help-inline', 
            focusInvalid: true, 
            ignore: "",
            rules: {
                lmp: { required: true }
            },

            invalidHandler: function (event, validator) {},

            // hightlight error inputs
            highlight: function (element) { 
                $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
					
				//customized error display
				var myFunction = function() {
					$(element).next().children(".ah").html($(element).siblings(".help-inline").text());
				}
				$(element)
				.keyup(myFunction)				
				.change(myFunction);
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            /*success: function (label) {
                label.addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            },*/

            submitHandler: function (form) {
                form.submit();
            }
        });
    }
	
	var new_chc_form = function() {
        var new_chc_form = $('#new_chc_form');
        new_chc_form.validate({
            errorElement: 'span', 
            errorClass: 'help-inline', 
            focusInvalid: true, 
            ignore: "",
            rules: {
                chc_name: { required: true },
                block_name: { required: true },
                chc_addr_one: { required: true },
                pincode: { required: true},
                incharge_name: { required: true },
                incharge_contact_no: { required: true, cusinteger: true }
            },

            invalidHandler: function (event, validator) {},

            // hightlight error inputs
            highlight: function (element) { 
                $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
					
				//customized error display
				var myFunction = function() {
					$(element).next().children(".ah").html($(element).siblings(".help-inline").text());
				}
				$(element)
				.keyup(myFunction)				
				.change(myFunction);
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            /*success: function (label) {
                label.addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            },*/

            submitHandler: function (form) {
                form.submit();
            }
        });
    }
	
	var new_phc_form = function() {
        var new_phc_form = $('#new_phc_form');
        new_phc_form.validate({
            errorElement: 'span', 
            errorClass: 'help-inline', 
            focusInvalid: true, 
            ignore: "",
            rules: {
                phc_name: { required: true },
                chc_name: { required: true },
                phc_addr_one: { required: true },
                pincode: { required: true},
                incharge_name: { required: true },
                incharge_contact_no: { required: true, cusinteger: true }
            },

            invalidHandler: function (event, validator) {},

            // hightlight error inputs
            highlight: function (element) { 
                $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
					
				//customized error display
				var myFunction = function() {
					$(element).next().children(".ah").html($(element).siblings(".help-inline").text());
				}
				$(element)
				.keyup(myFunction)				
				.change(myFunction);
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            /*success: function (label) {
                label.addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            },*/

            submitHandler: function (form) {
                form.submit();
            }
        });
    }
	
	
	var new_mother_form = function() {
        var new_mother_form = $('#new_mother_form');
        new_mother_form.validate({
            errorElement: 'span', 
            errorClass: 'help-inline', 
            focusInvalid: true, 
            ignore: "",
            rules: {
				mother_name: { required: true },
				husband_name: { required: true },
                //chc_name: { required: true },
                address: { required: true },
                block_name: { required: true },
                anganwadi_name: { required: true },
                pincode: { required: true},
                date_of_birth: { required: true },
                height: { required: true },
                blood_group: { required: true },
                //lmp: { required: true },
                no_of_gravida: { required: true },
                contact_no: { required: true, cusinteger: true }
            },

            invalidHandler: function (event, validator) {},

            // hightlight error inputs
            highlight: function (element) { 
                $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
					
				//customized error display
				var myFunction = function() {
					$(element).next().children(".ah").html($(element).siblings(".help-inline").text());
				}
				$(element)
				.keyup(myFunction)				
				.change(myFunction);
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            /*success: function (label) {
                label.addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            },*/

            submitHandler: function (form) {
                form.submit();
            }
        });
    }

	var changePassword = function() {
        var changePassword = $('#changePassword');
        changePassword.validate({
            errorElement: 'span', 
            errorClass: 'help-inline', 
            focusInvalid: true, 
            ignore: "",
            rules: {
                cur_password: {
                    required: true
                },
                new_password: {
                    required: true
                },                
                cnf_password: {
                    required: true,
                    equalTo: '#new_password'
                }
            },

            invalidHandler: function (event, validator) {},

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            /*success: function (label) {
                label.addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                .closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            },*/

            submitHandler: function (form) {
                form.submit();
            }
        });
    }
	
    return {
        //main function to initiate the module
        init: function () {
            login_form();
            changePassword();
            new_user_form();
            new_block_form();
			new_anganwadi_form();
			new_chc_form();
			new_phc_form();
			new_mother_form();
			new_pregnancy_form();
        },

	// wrapper function to scroll to an element
        scrollTo: function (el, offeset) {
            pos = el ? el.offset().top : 0;
            jQuery('html,body').animate({
                    scrollTop: pos + (offeset ? offeset : 0)
                }, 'slow');
        }
    };

}();