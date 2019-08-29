<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var = "customerURL" value = "/admin-customer"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dach sách khách hàng</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="admin-home">Trang chủ</a></li>
				<li>Dach sách khách hàng</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- start form -->
					<!-- form -->
					<form action="${customerURL}" method="get" id="formSubmit">
						<div class="widget-box table-filter">
							<div class="widget-header">
								<h4 class="widget-title">Tìm kiếm</h4>
								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
											class="ace-icon fa fa-chevron-up"></i>
									</a>
								</div>
							</div>
							<div class="widget-body">
								<div class="widget-main">
									<div class="form-horizontal">

										<div class="form-group">
											<div class="col-sm-4">
												<label>Tên khách hàng</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="fullName" value="${model.fullName}" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>SĐT</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="phoneNumber" value="${model.phoneNumber}" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Email</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="email" value="${model.email}" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-horizontal">
										<div class="form-group">
											<div class="col-sm-4">
												<label>Nhân viên phụ trách</label>
												<div class="fg-line">
													<select class="form-control" name="idStaff">
														<option value="">Chọn nhân viên</option>
														<c:forEach var="item" items="${staffs}">
															<option value="${item.id}" ${item.id == model.idStaff ? 'selected' : ''} >${item.fullName}</option>
														</c:forEach>
														<%--item == user, model == customer--%>
													</select>
												</div>
											</div>
									</div>

									<div class="form-horizontal">
										<div class="form-group">
											<div class="col-sm-12">
												<button class="btn btn-xs btn-success pull-left"
														type="button" id="btnSearch">
													<span class="bigger-110">Tìm kiếm</span> <i
														class="ace-icon fa fa-arrow-right icon-on-right"></i>
												</button>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
						<input type="hidden" value="" id="page" name="page" />
						<input type="hidden" value="10" id="maxPageItem" name="maxPageItem" />
						<input type="hidden" value="" id="sortName" name="sortName" />
						<input type="hidden" value="" id="sortBy" name="sortBy" />
						<input type="hidden" name="action" value="LIST" />
						</div>
					</form>
					<!-- end form -->

					<!-- button add, delete -->
					<div class="table-btn-controls">
						<div class="pull-right tableTools-container">
							<div class="dt-buttons btn-overlap btn-group">
								<a
									class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
									data-toggle="tooltip" title='Thêm khách hàng'
									href='<c:url value="/admin-customer?action=EDIT"/>'> <span><i
									class="fa fa-plus-circle bigger-110 purple"></i></span>
								</a>
								<button type="button" id="btnDelete"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Xóa khách hàng'>
									<span><i class="fa fa-trash-o bigger-110 pink"></i></span>
								</button>
							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- table -->
			<div class="row">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<thead>
						<tr>
							<th><input type="checkbox" value="" id="checkAll" /></th>
							<th>Tên KH</th>
							<th>SĐT</th>
							<th>Email</th>
							<th>Nhu cầu</th>
							<th>Người nhập</th>
							<th>Ngày nhập</th>
							<%--<th>Tình trạng</th>--%>
							<th>Thao tác</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${model.listResults}">
							<tr>
								<td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}" /></td>
								<td>${item.fullName}</td>
								<td>${item.phoneNumber}</td>
								<td>${item.email}</td>
								<td>${item.need}</td>
								<td>${item.createdby}</td>
								<td>${item.createddate}</td>
								<%--<td>TÌNH TRẠNG</td>--%>
								<td>
									<a class="btn btn-xs btn-primary btn-edit"
									   data-toggle="tooltip"
									   title='Cập nhật thông tin khách hàng'
									   href='<c:url value="/admin-customer?action=EDIT&id=${item.id}"/>'>
										<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
									</a>
									<a class="btn btn-xs btn-primary btn-edit" data-toggle="modal" data-target="#exampleModal"
									   data-toggle="tooltip"
									   title='Giao khách hàng cho nhân viên quản lý'
									   href='<c:url value="/employee-list?assignment=CUSTOMER&id=${item.id}"/>'>
										<i class="menu-icon fa fa-list" aria-hidden="true"></i>
									</a>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- paging -->
			<div class="container">
				<nav aria-label="Page navigation">
					<ul class="pagination" id="pagination"></ul>
				</nav>
			</div>
		</div>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
		</div>
	</div>
</div>
<!-- /.main-content -->
<script type="text/javascript">
    $(document).ready(
        function () {
            var selectAll = $('#checkAll');
            var table = $('.table');
            var tdCheckbox = table.find('tbody input:checkbox');
            var tdCheckboxChecked = [];

            selectAll.on('click', function () {
                tdCheckbox.prop('checked', this.checked);
            });

            tdCheckbox.on('change', function () {
                tdCheckboxChecked = table.find('tbody input:checkbox:checked');
                selectAll.prop('checked', (tdCheckboxChecked.length == tdCheckbox.length));
            });

        });



    $('#btnDelete').click(function name() {
        var data = {};
        var dataArray = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
		data = dataArray;
        if (typeof data !== 'undefined' && data.length > 0) {
            deleteCustomer(data);
        } else {
            alert('chọn khách hàng để xóa !');
		}

    });

    $('#btnSearch').click(function () {
        $('#page').val(1);
        $('#formSubmit').submit();
    });

    function deleteCustomer(data) {
        $.ajax({
            url : 'http://localhost:8087/api/building',
            data: JSON.stringify(data),
            type: 'DELETE',
            contentType: 'application/json',
            success: function (data) {
                //alert("success");
                window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=10&message=delete_success";
            },
            error: function () {
                //alert("fail");
                window.location.href = "${customerURL}?action=LIST&page=1&maxPageItem=10&message=error_system";
            }
        });
    }

    var totalPage = ${ model.totalPage };
    var currentPage = ${ model.page };

    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPage,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        })
    });
</script>
</body>
</html>