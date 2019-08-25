<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var = "customerURL" value = "/admin-customer"/>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<c:if test="${not empty model.id}">
	<title>Cập nhật thông tin khách hàng</title>
</c:if>
<c:if test="${empty model.id}">
	<title>Thêm khách hàng</title>
</c:if>

</head>

<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
					chủ</a></li>
				<li>Sản phẩm</li>
				<li>Chỉnh sửa thông tin sản phẩm</li>
			</ul>
		</div>
		<!-- /.breadcrumb -->

		<div class="page-content container">
			<form id="formId">
				<div class="row">
					<div class="col-xs-12">

						<div class="widget-body">
							<div class="widget-main">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left"> Tên khách hàng </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="fullName" value="${model.fullName}">
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left"> SĐT </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="phoneNumber" value="${model.phoneNumber}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left"> Email </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="email" value="${model.email}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" > Tên công ty </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="companyName" value="${model.companyName}" />
										</div>
									</div>
								</div>

								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left"> Nhu cầu </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="need" value="${model.need}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left"> Ghi chú </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="notes" value="${model.notes}" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" name="id" value="${model.id}" id="customerId" />
				<input type="hidden" name="page" value="" id="page" />
				<input type="hidden" name="maxPageItem" value="10" id="maxPageItem" />
			</form>
			<div class="row text-center btn-addsp">
				<c:if test="${empty model.id}">
					<button class="btn btn-success" id="btnAddOrUpdateCustomer">Thêm khách hàng</button>
				</c:if>
				<c:if test="${not empty model.id}">
					<button class="btn btn-success" id="btnAddOrUpdateCustomer">Cập nhật thông tin khách hàng</button>
				</c:if>
				<button class="btn btn-danger"><a href='<c:url value="/admin-customer?action=LIST&page=1&maxPageItem=10&sortName=name&sortBy=ASC"/>'>
					Hủy
				</a></button>
			</div>

			<c:if test="${not empty model.id}">
			<h2>CHĂM SÓC KHÁCH HÀNG
				<button
						id="btnCSKH"
						class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
						data-toggle="tooltip" title='Thêm hoạt động'> <span><i
						class="fa fa-plus-circle bigger-110 purple"></i></span>
				</button>
			</h2>
			<div class="container row">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<thead>
						<tr>
							<th>Ngày tạo</th>
							<th>Ghi chú</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${assCustomer}">
							<c:if test="${item.type == 'CSKH'}">
								<tr>
									<td>${item.createddate}</td>
									<td>${item.message}</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td></td>
							<td><input type="text" style="width:100%" id="msgCskh"/></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<h2>ĐI XEM
				<button
						id="btnSEE"
						class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
						data-toggle="tooltip" title='Thêm hoạt động'> <span><i
						class="fa fa-plus-circle bigger-110 purple"></i></span>
				</button>
			</h2>
			<div class="container row">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<thead>
						<tr>
							<th>Ngày tạo</th>
							<th>Ghi chú</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${assCustomer}">
							<c:if test="${item.type == 'SEE'}">
								<tr>
									<td>${item.createddate}</td>
									<td>${item.message}</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td></td>
							<td><input type="text" style="width:100%" id="msgSee"/></td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>
		<!-- /.end-page-content -->
	</div>
</div>

<input type="hidden" name="userId" value="1" id="userId"/>
			</c:if>

<!-- /.main-content -->
<script type="text/javascript">
    $('#btnAddOrUpdateCustomer').click(function() {
        $('#page').val(1);
        addOrUpdateCustomer();
    });

    $('#btnCSKH').click(function() {
        var customerId = $('#customerId').val();
        var userId = $('#userId').val();
		var msg = $('#msgCskh').val();
		var data = {};
		data['type'] = "CSKH";
		data['userId'] = userId;
        data['customerId'] = customerId;
        data['message'] = msg;
        activityUpdate(data, customerId);
    });

    $('#btnSEE').click(function() {
        var customerId = $('#customerId').val();
        var userId = $('#userId').val();
        var msg = $('#msgSee').val();
        var data = {};
        data['type'] = "SEE";
        data['userId'] = userId;
        data['customerId'] = customerId;
        data['message'] = msg;
        activityUpdate(data, customerId);
    });

    function activityUpdate(data, id){
        $.ajax({
            url: 'http://localhost:8087/api/customer/deal',
            data: JSON.stringify(data),
            type: 'PUT',
            contentType: 'application/json',
            success: function(data) {
                window.location.href = "${customerURL}?action=EDIT&id=" + id + "&message=insert_success";
            },
            error: function() {
                window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=10&sortName=name&sortBy=ASC&message=error_system";
            }
        });
	}

    function addOrUpdateCustomer() {
        var customerId = $('#customerId').val();
        var formData = $('#formId').serializeArray();
        var data = {};
        $.each(formData, function(index, v) {
            data["" + v.name + ""] = v.value;
        });
        if (customerId == '') {
            addCustomer(data);
        } else {
            editCustomer(data);
        }
    }

    function addCustomer(data) {
        $.ajax({
            url: 'http://localhost:8087/api/customer',
            data: JSON.stringify(data),
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            success: function(data) {
                window.location.href = "${customerURL}?action=EDIT&id=" + data.id + "&message=insert_success";
            },
            error: function() {
                window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=10&sortName=name&sortBy=ASC&message=error_system";
            }
        });
    }

    function editCustomer(data) {
        $.ajax({
            url: 'http://localhost:8087/api/customer',
            data: JSON.stringify(data),
            type: 'PUT',
            contentType: 'application/json',
            success: function(data) {
                window.location.href = "${customerURL}?action=EDIT&id=" + data.id + "&message=update_success";
            },
            error: function() {
                window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=10&sortName=name&sortBy=ASC&message=error_system";
            }
        });
    }
</script>
</body>

</html>