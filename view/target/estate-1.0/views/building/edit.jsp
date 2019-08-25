<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api-admin-building" />
<c:url var="buildingURL" value="/admin-building" />
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<c:if test="${not empty model.id}">
	<title>Cập nhật sản phẩm</title>
</c:if>
<c:if test="${empty model.id}">
	<title>Thêm mới sản phẩm</title>
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

		<div class="page-content">
			<form id="formId">
				<div class="row">
					<div class="col-xs-12">

						<div class="widget-body">
							<div class="widget-main">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Tên sản phẩm </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="name" value="${model.name}">
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Quận </label>
										<div class="col-xs-10">
											<div class="fg-line">
												<select class="custom-select" name="district">
													<option value="">Chọn quận</option>
													<c:forEach var="item" items="${districts}">
														<option value="${item.key}" ${item.key==model.district ? 'selected' : ''}>${item.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Phường </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="ward" value="${model.ward}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Đường </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="street" value="${model.street}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Kết cấu </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="structure" value="${model.structure}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Số tầng hầm </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="numberOfBasement" value="${model.numberOfBasement}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Diện tích sàn </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="buildingArea" value="${model.buildingArea}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Hướng </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="direction" value="${model.direction}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Hạng </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="level" value="${model.level}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Diện tích thuê </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="rentArea" value="${model.rentArea}" />
										</div>
									</div>
								</div>
								<!--
									<div class="form-horizontal">
										<div class="form-group">
											<label class="col-xs-2 control-label no-padding-left"
												for="form-field-1-1"> Mô tả diện tích </label>
											<div class="col-xs-10">
												<textarea class="form-control" rows="5" name="buildingArea" value="${model.buildingArea}"></textarea>
											</div>
										</div>
									</div>
									-->
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Giá thuê </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="costRent" value="${model.costRent}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Mô tả giá </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="costDescription" value="${model.costDescription}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Phí dịch vụ </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="serviceCost" value="${model.serviceCost}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Phí ô tô </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="carCost" value="${model.carCost}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Phí mô tô </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="motorbikeCost" value="${model.motorbikeCost}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Phí ngoài giờ </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="overTimeCost" value="${model.overTimeCost}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Tiền điện </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="electricityCost" value="${model.electricityCost}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Đặt cọc </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="deposit" value="${model.deposit}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Thanh toán </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="payment" value="${model.payment}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Thời hạn thuê </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="timeContract" value="${model.timeContract}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Thời gian trang trí </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="timeDecorator" value="${model.timeDecorator}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Tên quản lý </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="managerName" value="${model.managerName}" />
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> SĐT quản lý </label>
										<div class="col-xs-10">
											<input type="text" class="form-control" name="managerPhone" value="${model.managerPhone}" />
										</div>
									</div>
								</div>
								<!--
									<div class="form-horizontal">
										<div class="form-group distance">
											<label class="col-xs-2 control-label no-padding-left"
												for="form-field-1-1"> Phí môi giới </label>
											<div class="col-xs-10">
												<input type="text" class="form-control" name="buildingArea" value="${model.buildingArea}"/>
											</div>
										</div>
									</div>
									-->
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-xs-2 control-label no-padding-left" for="form-field-1-1"> Loại sản phẩm </label>
										<div class="col-xs-10">
											<div class="fg-line">
												<c:forEach var="item" items="${buildingtypes}">
													<label class="checkbox-inline">
														<input type="checkbox" value="${item.key}" name="buildingTypes" ${fn:contains(fn:join(model.buildingTypes, ','), item.key)? 'checked': ''} />${item.value}
													</label>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
								<!--
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-xs-2 control-label no-padding-left"
                                    for="form-field-1-1"> Hình ảnh sản phẩm </label>
                                <div class="col-xs-10">
                                    <input type="file" name="image"/>
                                </div>
                            </div>
                        </div>
                        -->
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" name="id" value="${model.id}" id="buildingId" />
				<input type="hidden" name="page" value="" id="page" />
				<input type="hidden" name="maxPageItem" value="10" id="maxPageItem" />
			</form>
			<div class="row text-center btn-addsp">
				<c:if test="${empty model.id}">
					<button class="btn btn-success" id="btnAddOrUpdateBuilding">Thêm tòa nhà</button>
				</c:if>
				<c:if test="${not empty model.id}">
					<button class="btn btn-success" id="btnAddOrUpdateBuilding">Cập nhật tòa nhà</button>
				</c:if>
				<%--<button class="btn btn-danger">Hủy</button>--%>
				<button class="btn btn-danger"><a href='<c:url value="/admin-building?action=LIST&page=1&maxPageItem=10&sortName=name&sortBy=ASC"/>'>
					Hủy
				</a></button>
			</div>
		</div>
		<!-- /.end-page-content -->
	</div>
</div>
<!-- /.main-content -->
<script type="text/javascript">
    $('#btnAddOrUpdateBuilding').click(function() {
        $('#page').val(1);
        addOrUpdateBuilding();
    });

    function addOrUpdateBuilding() {
        var buildingId = $('#buildingId').val();
        var formData = $('#formId').serializeArray();
        var data = {};
        var buildingTypes = [];
        $.each(formData, function(index, v) {
            if (v.name == 'buildingTypes') {
                buildingTypes.push(v.value);
            } else {
                data["" + v.name + ""] = v.value;
            }
        });
        data['buildingTypes'] = buildingTypes;
        if (buildingId == '') {
            addBuilding(data);
        } else {
            editBuilding(data, buildingId);
        }
    }

    function addBuilding(data) {
        $.ajax({
            url: 'http://localhost:8087/api/building',
            data: JSON.stringify(data),
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            success: function(data) {
                window.location.href = "${buildingURL}?action=EDIT&id=" + data.id + "&message=insert_success";
            },
            error: function() {
                window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=10&sortName=name&sortBy=ASC&message=error_system";
            }
        });
    }

    function editBuilding(data, id) {
        $.ajax({
            url: 'http://localhost:8087/api/building',
            data: JSON.stringify(data),
            type: 'PUT',
            contentType: 'application/json',
            success: function(data) {
                window.location.href = "${buildingURL}?action=EDIT&id=" + id + "&message=update_success";
            },
            error: function() {
                window.location.href = "${buildingURL}?action=LIST&page=1&maxPageItem=10&sortName=name&sortBy=ASC&message=error_system";
            }
        });
    }
</script>
</body>

</html>