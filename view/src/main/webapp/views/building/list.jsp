<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var = "buildingURL" value = "/admin-building"/>
<c:url var="builddingAPI" value="/api-admin-building"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dach sách sản phẩm</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="admin-home">Trang chủ</a></li>
				<li>Dach sách sản phẩm</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- start form -->
					<!-- form -->
					<form action="${buildingURL}" method="get" id="formSubmit">
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
											<div class="col-sm-6">
												<label>Tên Sản phẩm</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="name" value="${model.name}" />
												</div>
											</div>
											<div class="col-sm-6">
												<label>Diện tích sàn</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="buildingArea" value="${model.buildingArea}" />
												</div>
											</div>
										</div>

									</div>

									<div class="form-horizontal">
										<div class="form-group">
											<div class="col-sm-4">
												<label>Quận hiện có</label>
												<div class="fg-line">
													<select class="form-control" name="district">
														<option value="">Chọn quận</option>
														<c:forEach var="item" items="${districts}">
															<option value="${item.key}"
																${item.key == model.district ? 'selected' : ''}>${item.value}</option>
														</c:forEach>
													</select>
												</div>

											</div>
											<div class="col-sm-4">
												<label>Phường</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="ward" value="${model.ward}" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Đường</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="street" value="${model.street}" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-horizontal">
										<div class="form-group">
											<div class="col-sm-4">
												<label>Số tầng hầm</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="numberOfBasement" value="${model.numberOfBasement}" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Hướng</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="direction" value="${model.direction}" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Hạng</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="level" value="${model.level}" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-horizontal">
										<div class="form-group">
											<div class="col-sm-3">
												<label>Diện tích từ</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="areaRentFrom" value="${model.areaRentFrom}" />
												</div>
											</div>
											<div class="col-sm-3">
												<label>Diện tích đến</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="areaRentTo" value="${model.areaRentTo}" />
												</div>
											</div>
											<div class="col-sm-3">
												<label>Giá thuê từ</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="costRentFrom" value="${model.costRentFrom}" />
												</div>
											</div>
											<div class="col-sm-3">
												<label>Giá thuê đến</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm"
														   name="costRentTo" value="${model.costRentTo}" />
												</div>
											</div>
										</div>
										<div class="form-horizontal">
											<div class="form-group">
												<div class="col-sm-4">
													<label>Tên quản lý</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															   name="managerName" value="${model.managerName}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label>Điện thoại quản lý</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															   name="managerPhone" value="${model.managerPhone}" />
													</div>
												</div>
												<%--<div class="col-sm-4">--%>
													<%--<label>NV hiện có</label>--%>
													<%--<div class="fg-line">--%>
														<%--<select class="form-control" name="xxxx">--%>
															<%--<option value="">Chọn Nhân viên</option>--%>
															<%--<c:forEach var="staff" items="${listStaff}">--%>
																<%--<option value="${staff.id}" >${staff.fullName}</option>--%>
															<%--</c:forEach>--%>
														<%--</select>--%>
													<%--</div>--%>
												<%--</div>--%>
											</div>
										</div>
										<div class="form-horizontal">
											<div class="form-group">
												<div class="col-sm-12">
													<label>Loại tòa nhà</label>
													<div class="fg-line">
														<c:forEach var="item" items="${buildingtypes}">
															<label class="checkbox-inline"> <input
																	type="checkbox" value="${item.key}"
																	name="buildingTypes"
																${fn:contains(fn:join(model.buildingTypes,','), item.key)?'checked':''} />${item.value}
															</label>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
										<input type="hidden" name="action" value="LIST" />
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
						</div>
						<input type="hidden" value="" id="page" name="page" />
						<input type="hidden" value="3" id="maxPageItem" name="maxPageItem" />
						<input type="hidden" value="" id="sortName" name="sortName" />
						<input type="hidden" value="" id="sortBy" name="sortBy" />

					</form>
					<!-- end form -->

					<!-- button add, delete -->
					<div class="table-btn-controls">
						<div class="pull-right tableTools-container">
							<div class="dt-buttons btn-overlap btn-group">
								<a
										class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Thêm tòa nhà'
										href='<c:url value="/admin-building?action=EDIT"/>'> <span><i
										class="fa fa-plus-circle bigger-110 purple"></i></span>
								</a>
								<button type="button" id="btnDelete"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Xóa tòa nhà'>
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
							<th>Tên sản phẩm</th>
							<th>Địa chỉ</th>
							<th>Số tầng hầm</th>
							<th>Diện tích sàn</th>
							<th>Diện tích thuê</th>
							<th>Giá thuê</th>
							<th>Loại tòa nhà</th>
							<th>Tên quản lý</th>
							<th>SĐT quản lí</th>
							<th>Thao tác</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${model.listResults}">
							<tr>
								<td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}" /></td>
								<td>${item.name}</td>
								<td>${item.address}</td>
								<td>${item.numberOfBasement}</td>
								<td>${item.buildingArea}</td>
								<td>${item.rentArea}</td>
								<td>${item.costRent}</td>
								<td>${item.type}</td>
								<td>${item.managerName}</td>
								<td>${item.managerPhone}</td>
								<td>
									<a class="btn btn-xs btn-primary btn-edit"
									   data-toggle="tooltip"
									   title='Cập nhật tòa nhà'
									   href='<c:url value="/admin-building?action=EDIT&id=${item.id}"/>'>
										<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
									</a>
									<a class="btn btn-xs btn-primary btn-edit" data-toggle="modal" data-target="#exampleModal"
									   data-toggle="tooltip"
									   title='Giao tòa nhà'
									   href='<c:url value="/employee-list?id=${item.id}"/>'>
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
        deleteBuilding(data);
    });

    $('#btnSearch').click(function () {
        $('#page').val(1);
        $('#formSubmit').submit();
    });

    function deleteBuilding(data) {
        $.ajax({
            url : 'http://localhost:8087/api/building',
            data: JSON.stringify(data),
            type: 'DELETE',
            contentType: 'application/json',
            success: function (data) {
                //alert("success");
                window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=10&message=delete_success";
            },
            error: function () {
                //alert("fail");
                window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=10&message=error_system";
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