$(function () {
    $("#jqGrid").jqGrid({
        url: '../month/list',
        datatype: "json",
        colModel: [			
			{ label: 'mId', name: 'mId', width: 50, key: true,hidden:true},
			{ label: '年份', name: 'yName', width: 80 },
			{ label: '月份', name: 'mName', width: 80 },
			{ label: '开始时间', name: 'mBgtime', width: 80 },
			{ label: '结束时间', name: 'mEndtime', width: 80 }
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		modaltitle:'',
		month:{}
	},
	methods: {
		add:function(){
			vm.month={};
			$("#bgTime").val('');
			$("#endTime").val('');
			vm.modaltitle="新增";
			$("#Modal").modal("show");
		},
		update: function (event) {
			var mId = getSelectedRow();
			if(mId == null){
				return ;
			}
			vm.modaltitle="修改";
			vm.getInfo(mId);
			$("#Modal").modal("show");

		},
		getInfo: function(mId){
			$.get("../month/info/"+mId, function(r){
				vm.month = r.month;
				$("#bgTime").val(vm.month.mBgtime);
				$("#endTime").val(vm.month.mEndtime);
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.month.mId == null ? "../month/save" : "../month/update";
			vm.month.mBgtime=$("#bgTime").val();
			vm.month.mEndtime=$("#endTime").val();
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.month),
				success: function(r){
					if(r.code === 0){
						layer.msg('操作成功',{offset:'100px'}, function(index){
							$("#jqGrid").trigger("reloadGrid");
							$.get("../update/list",function (r) {

							});
							$("#Modal").modal("hide");
						});
					}else{
						layer.alert(r.msg,{offset:'100px'});
					}
				},
				error:function (r) {
					layer.msg("提交失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
				}
			});
		},
		del: function (event) {
			var mIds = getSelectedRows();
			if(mIds == null){
				return ;
			}
			layer.confirm('确定要删除选中的记录？',{offset:'100px'}, function(){
				$.ajax({
					type: "POST",
				    url: "../month/delete",
				    data: JSON.stringify(mIds),
				    success: function(r){
						if(r.code == 0){
							layer.msg('操作成功',{offset:'100px'}, function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							layer.alert(r.msg,{offset:'100px'});
						}
					}
				});
			});
		}
	}
});