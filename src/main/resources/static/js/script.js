$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();

	$('.table .changebtn').on('click', function() {
		var id = $(this).parent().find('#btnChangeId').val();
		$('.btnchangeid').val(id);
	});

	$('.table .rosterchangebtn').on('click', function() {
		var id = $(this).parent().find('#btnChangeId').val();
		$('.rosterbtnchangeid').val(id);
	});

	//Button Issue book Data Set
	$('.table .issueBook').on('click', function() {
		var id = $(this).parent().find('#getIssueBookId').val();
		$('.setBookId').val(id);
	});

	//Button Repeate Data Set
	$('.table .btnRepeat').on('click', function() {
		var id = $(this).parent().find('#id').val();
		var rollNo = $(this).parent().find('#rollNo').val();
		var name = $(this).parent().find('#studentName').val();
		var registrationNo = $(this).parent().find('#registrationNo').val();
		var fName = $(this).parent().find('#fatherName').val();
		var semester = $(this).parent().find('#semester').val();
		var department = $(this).parent().find('#department').val();
		var batch = $(this).parent().find('#batch').val();
		var status = $(this).parent().find('#status').val();
		var paperName = $(this).parent().find('#paperName').val();
		var courseCode = $(this).parent().find('#courseCode').val();
		var courseCode = $(this).parent().find('#courseCode').val();
		$('.id').val(id);
		$('.rollNo').val(rollNo);
		$('.studentName').val(name);
		$('.registrationNo').val(registrationNo);
		$('.fatherName').val(fName);
		$('.semester').val(semester);
		$('.department').val(department);
		$('.batch').val(batch);
		$('.status').val(status);
		$('.paperName').val(paperName);
		$('.courseCode').val(courseCode);
		$('.feeType').val("Repeat");
	});

	//Button Improve Data Set
	$('.table .btnImprove').on('click', function() {
		var id = $(this).parent().find('#id').val();
		var rollNo = $(this).parent().find('#rollNo').val();
		var name = $(this).parent().find('#studentName').val();
		var registrationNo = $(this).parent().find('#registrationNo').val();
		var fName = $(this).parent().find('#fatherName').val();
		var semester = $(this).parent().find('#semester').val();
		var department = $(this).parent().find('#department').val();
		var batch = $(this).parent().find('#batch').val();
		var status = $(this).parent().find('#status').val();
		var paperName = $(this).parent().find('#paperName').val();
		var courseCode = $(this).parent().find('#courseCode').val();
		$('.id').val(id);
		$('.rollNo').val(rollNo);
		$('.studentName').val(name);
		$('.registrationNo').val(registrationNo);
		$('.fatherName').val(fName);
		$('.semester').val(semester);
		$('.department').val(department);
		$('.batch').val(batch);
		$('.status').val(status);
		$('.paperName').val(paperName);
		$('.courseCode').val(courseCode);
		$('.feeType').val("Improve");
	});
});

//Button Seat Book Data Set
function setData(seatBookId, busName, studentName, seatNo, busId, studentsId) {
	document.getElementById("seatBookId").value = seatBookId;
	document.getElementById("studentsName").value = studentName;
	document.getElementById("busName").value = busName;
	document.getElementById("studentsId").value = studentsId;
	document.getElementById("busId").value = busId;
	document.getElementById("seatNo").value = seatNo;

}


//Button Calculate Fee Data Set
function setFeeData(id, totalFee, remainingFee, submitFee) {
	document.getElementById("id").value = id;
	document.getElementById("totalFee").value = totalFee;
	document.getElementById("remainingFee").value = remainingFee
	document.getElementById("oldFee").value = submitFee;
	document.getElementById("rfee").value = remainingFee
	document.getElementById("ofee").value = submitFee;
}

function rowHiden(){
	$("#data").click(function(){
    $('#studentListBody tr [type="checkbox"]:checked').closest("tr").hide();
});

}


// Button Add remaining Fee Data Set
function setNewFeeData() {
	let newFee = document.getElementById("newFee").value;
	let remainingFee = document.getElementById("rfee").value;
	let oldFee = document.getElementById("ofee").value;
	document.getElementById("remainingFee").value = remainingFee - newFee;
	let sum = parseInt(document.querySelector("#ofee").value) + parseInt(document.querySelector("#newFee").value);
	if (!isNaN(sum)) {
		document.getElementById("oldFee").value = sum;
	} else {
		document.getElementById("oldFee").value = oldFee;
	}
}

//Button Checkout Product Details
function checkoutProduct(productId, productName, productQuantity, productPrice) {
	document.getElementById("productId").value = productId;
	document.getElementById("productName").value = productName;
	document.getElementById("quantity").value = productQuantity;
	document.getElementById("tquantity").value = productQuantity;
	document.getElementById("productPrice").value = productPrice;
	document.getElementById("tPrice").value = productPrice;
	document.getElementById("requiredQuantity").max = productQuantity;
	document.getElementById("sellingPrice").max = productPrice;
}


//Button Change Quantity
function setQuantityChange() {
	let quantity = document.getElementById("tquantity").value;
	let newQuantity = document.getElementById("requiredQuantity").value
	document.getElementById("quantity").value = quantity - newQuantity;
}

//Button Change Price
function setPriceChange() {
	let tPrice = document.getElementById("tPrice").value;
	let sPrice = document.getElementById("sellingPrice").value;
	document.getElementById("productPrice").value = tPrice - sPrice;
}

//examination student Result Record Search
const search = () => {
	let query = document.getElementById("search-input").value;
	let semester=document.getElementById("hsemester").value;
	let batch=document.getElementById("hbatch").value;
	if (query == '') {
		$(".search-result").hide();
	} else {
			
		let url = `http://localhost:8080/search/${query}/${semester}/${batch}`;
		fetch(url).then((response) => {
			return response.json();
		}).then((data) => {
			//			let text = `<div class='list-group'>`;
			//			data.forEach((result) => {
			//				text+=`<a href='#' class='list-group-item list-group-item-action'><small class='text-left']>${'Student Name: '+result.studentName+''}</small><small>${'Paper Name: '+result.paperName}</small></a>`
			let text = `<table class='table rTable caption-top'>`;
			text += `<thead>`;
			text += `<tr>`;
			text += `<th scope="col">Roll No</th>`;
			text += `<th scope="col">Student Name</th>`;
			/*text += `<th scope="col">Registration No</th>`;
			text += `<th scope="col">Father Name</th>`;*/
			text += `<th scope="col">Department</th>`;
			text += `<th scope="col">Paper</th>`;
			text += `<th scope="col">Marks</th>`;
			text += `<th scope="col">Action</th>`;
			text += `</tr>`;
			text += `</thead>`;
			text += `<tbody>`;
			data.forEach((r) => {
				text += `<tr>`;
				text += `<td >${r.rollNo}</td>`;
				text += `<td >${r.studentName}</td>`;
				/*text += `<td >${r.registrationNo}</td>`;
				text += `<td >${r.fatherName}</td>`;*/
				text += `<td >${r.department}</td>`;
				text += `<td >${r.paperName}</td>`;
				text += `<td >${r.marks}</td>`;
				if(r.marks>=50 && r.marks<=59){
				text += `<td >`;
				if(r.paperStatus!='Improved'){
				text += `<a  href="/examination/search-result-list?id=${r.id}&batch=${r.batch}&semester=${r.semester}" style="text-decoration:none" class="btnImprove btn-outline-success rounded badge bg-primary rounded-pill">Improve</a>`;
							text += `<input type="hidden" id="id" value="${r.id}" />`; text += `<input
							type="hidden" id="rollNo" value="${r.rollNo}" />`; text += `<input
							type="hidden" id="studentName" value="${r.studentName}" />`; text += `<input
							type="hidden" id="registrationNo" value="${r.registrationNo}" />`;
							text += `<input type="hidden" id="fatherName" value="${r.fatherName}" />`;
							text += `<input type="hidden" id="semester" value="${r.semester}" />`; text += `<input
							type="hidden" id="department" value="${r.department}" />`; text += `<input
							type="hidden" id="status" value="${r.status}" />`; text += `<input
							type="hidden" id="batch" value="${r.batch}" />`; text += `<input
							type="hidden" id="paperName" value="${r.paperName}" />`;
							}else if(r.paperStatus=='Improved'){
								text += `<p class="btn-outline-primary rounded badge bg-success rounded-pill" data-bs-toggle="modal">Improved</p>`;
							}
							
							
				text += `</td>`;
				}
				else if(r.marks<50){
				text += `<td >`;
				if(r.paperStatus!='Repeated'){
				text += `<a href="/examination/search-result-list?id=${r.id}&batch=${r.batch}&semester=${r.semester}" style="text-decoration:none"  class="btnRepeat btn-outline-success rounded badge bg-danger rounded-pill"  >Repeat</a>`;
							text += `<input type="hidden" id="id" value="${r.id}" />`;
							text += `<input type="hidden" id="rollNo" value="${r.rollNo}" />`;
							text += `<input type="hidden" id="studentName" value="${r.studentName}" />`;
							text += `<input type="hidden" id="registrationNo" value="${r.registrationNo}" />`;
							text += `<input type="hidden" id="fatherName" value="${r.fatherName}" />`;
							text += `<input type="hidden" id="semester" value="${r.semester}" />`;
							text += `<input type="hidden" id="department" value="${r.department}" />`;
							text += `<input type="hidden" id="status" value="${r.status}" />`;
							text += `<input type="hidden" id="batch" value="${r.batch}" />`;
							text += `<input type="hidden" id="paperName" value="${r.paperName}" />`;

				} else if(r.paperStatus =='Repeated'){
							text += `<p class="btn-outline-success" data-bs-toggle="modal">Repeated</p>`;
				}
							
				text += `</td>`;
				}
				if(r.marks>60){
				text += `<td >`;
				text += `<p class=" btn-outline-primary" ></p>`;
				text += `</td>`;
				}
				text += `</tr>`;
			});
			text += `</tbody>`;
			text += `</table>`;
			$(".search-result").html(text);
			$(".search-result").show();

		});
	}
	
}


//Admission Module Search Fee of student
const searchFee = () => {
	let query = document.getElementById("search-input").value;
	let semester=document.getElementById("hsemester").value;
	let batch=document.getElementById("hbatch").value;
	if (query == '') {
		$(".search-result").hide();
	} else {
			
		let url = `http://localhost:8080/search-fee/${query}/${semester}/${batch}`;
		fetch(url).then((response) => {
			return response.json();
		}).then((data) => {
			//			let text = `<div class='list-group'>`;
			//			data.forEach((result) => {
			//				text+=`<a href='#' class='list-group-item list-group-item-action'><small class='text-left']>${'Student Name: '+result.studentName+''}</small><small>${'Paper Name: '+result.paperName}</small></a>`
			let text = `<table class='table rTable caption-top'>`;
			text += `<thead>`;
			text += `<tr>`;
			text += `<th scope="col">Roll No</th>`;
			text += `<th scope="col">Student Name</th>`;
			text += `<th scope="col">Father Name</th>`;
			text += `<th scope="col">Total Fee</th>`;
			text += `<th scope="col">Submited Fee</th>`;
			text += `<th scope="col">Date</th>`;
			text += `<th scope="col">Action</th>`;
			text += `</tr>`;
			text += `</thead>`;
			text += `<tbody>`;
			data.forEach((s) => {
				text += `<tr>`;
				text += `<td >${s.studentsId.rollNo}</td>`;
				text += `<td >${s.studentsId.firstName+' '+s.studentsId.lastName}</td>`;
				text += `<td >${s.studentsId.fatherName}</td>`;
				text += `<td >${s.totalAmount}</td>`;
				text += `<td >${s.submitAmount}</td>`;
				text += `<td >${s.date}</td>`;
				
				text +=`<td>`
				if((s.totalAmount-s.submitAmount)>0){
				text+=`<a `
				text +=`data-bs-toggle="modal" data-bs-target="#addRemainingFee"`
				text +=`class="btnImprove btn  badge bg-danger rounded-pill" onclick="addFee('${s.accountId}',`
				text +=`'${s.studentsId.firstName+' '+s.studentsId.lastName}',`
				text +=`'${s.totalAmount}',`
				text +=`'${s.submitAmount}',`
				text +=`'${s.date}')">Add Fee</a>`
				}else if((s.totalAmount-s.submitAmount)<=0){
				text +=`<p class="btn-outline-success rounded badge bg-success rounded-pill">Accepted</p>`
				}
				text +=`</td>`
				
				text += `</tr>`;
			});
			text += `</tbody>`;
			text += `</table>`;
			$(".search-result").html(text);
			$(".search-result").show();

		});
	}
	
}


//Library Books Record Search
const searchBooks = () => {
	let query = document.getElementById("search-input").value;
	let bookId=document.getElementById("bookId").value;
	if (query == '') {
		$(".search-result").hide();
	} else {
			
		let url = `http://localhost:8080/search-book/${query}`;
		fetch(url).then((response) => {
			return response.json();
		}).then((data) => {
						let text = `<div class='list-group'>`;
						data.forEach((result) => {
							text+=`<a href='/Library/search-book-record?id=${result.id}' class='list-group-item list-group-item-action'><small>${'Book Name: '+result.title}</small></a>`;
			});
			text +=`</div>`;
			$(".search-result").html(text);
			$(".search-result").show();

		});
	}	
}






const messageDialog = () =>{
	
	let message=sessionStorage.getItem('message');
	if(message!=''){
		swal({
  title: message.content,
  text: message.content,
  icon: message.type,
});
	}
}


//Button Vehicle asign set id
function setVehicleAsignId(busAsignId) {
	//let id = document.getElementById("getid").value;
	document.getElementById("busAsignId").value = busAsignId;
}

function addFee(id,s_name,s_totalAmount,s_submitAmount,date) {
	document.getElementById("id").value=id;
	document.getElementById("s_name").value=s_name;
	document.getElementById("s_totalAmount").value = s_totalAmount;
	document.getElementById("s_submitAmount").value = s_submitAmount;
	document.getElementById("date").value = date;
}

function editPaper(id,studentName,rollNo,paperName,marks,date) {
	document.getElementById("e_id").value=id;
	document.getElementById("e_studentName").value=studentName;
	document.getElementById("e_rollNo").value=rollNo;
	document.getElementById("e_paperName").value = paperName;
	document.getElementById("e_marks").value = marks;
	document.getElementById("e_date").value = date;
}


/*OWL Carosoule*/
$(document).ready(function() {
	
	var owl = $('.owl-carousel');
owl.owlCarousel({
    items:3,
    loop:true,
    margin:1,
    autoplay:true,
    autoplayTimeout:2000,
    autoplayHoverPause:true
});
$('.play').on('click',function(){
    owl.trigger('play.owl.autoplay',[1000])
})
$('.stop').on('click',function(){
    owl.trigger('stop.owl.autoplay')
})
	
});
