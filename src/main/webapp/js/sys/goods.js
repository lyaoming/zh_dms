$(function () {
    $("#jqGrid").jqGrid({
        url: '../goods/list',
        datatype: "json",
        colModel: [			
			{ label: 'gId', name: 'gId', width: 50, key: true ,hidden:true},
			{ label: '物品名称', name: 'gName', width: 80 },
			{ label: '物品编号', name: 'gNumber', width: 80 },
			{ label: '物品规格', name: 'gSpecification', width: 80 }, 			
			{ label: '物品类型', name: 'gType', width: 80 }, 			


        ],
		viewrecords: true,
		height:700,
		rowNum:15,
		rowList :[15,30,50,100,500],
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
		goods:{},
		gkeyword:'',

	},
	methods: {
		update: function (event) {
			var gId = getSelectedRow();
			if(gId == null){
				return ;
			}
			vm.getInfo(gId);
		},
		add:function(){
			vm.goods={};
			vm.modaltitle="新增";
			$("#Modal").modal("show");
		},
		getInfo: function(gId){
			$.get("../goods/info/"+gId, function(r){
				vm.goods = r.goods;
				vm.modaltitle="修改";
				$("#Modal").modal("show");
			});
		},
		query:function(event){
			$("#jqGrid").jqGrid('setGridParam',{
				postData: {
					'gkeyword':vm.gkeyword,
				},
				page:1
			}).trigger("reloadGrid");
		},
		saveOrUpdate: function (event) {
			var url = vm.goods.gId == null ? "../goods/save" : "../goods/update";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(vm.goods),
				success: function(r){
					if(r.code === 0){
						layer.msg('操作成功',{offset:'100px'}, function(index){
							$("#Modal").modal("hide");
							$("#jqGrid").trigger("reloadGrid");

						});
					}else{
						layer.msg(r.msg,{offset:'100px'});
					}
				},
				error:function (r) {
					layer.msg("录入失败！请检查信息是否有误或是漏填！",{offset: '100px;'});
				}
			});
		},
		del: function (event) {
			var gIds = getSelectedRows();
			if(gIds == null){
				return ;
			}
			
			layer.confirm('确定要删除选中的记录？',{offset:'100px'}, function(){
				$.ajax({
					type: "POST",
				    url: "../goods/delete",
				    data: JSON.stringify(gIds),
				    success: function(r){
						if(r.code == 0){
							layer.msg('删除成功！',{offset:'100px'} ,function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							layer.msg(r.msg,{offset:'100px'});
						}
					}
				});
			});
		}
	}
});