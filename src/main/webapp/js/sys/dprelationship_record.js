$(function () {
    $("#jqGrid").jqGrid({
        url: '../exprotdprecord/recordlist',
		datatype: "json",
		mtype:"POST",
		serializeGridData: function(postData) {
			return JSON.stringify(postData);
		},//post请求需要加
        colModel: [			
			{ label: 'pdId', name: 'pdId', width: 50, key: true,hidden:true },
			{ label: '姓名', name: 'pName', width: 80},
			{ label: '入住房屋号', name: 'dAddress', width: 80},
			{ label: '房间', name: 'roomName', width: 80,formatter:function (value) {
					if(value!=null){return value};
					if(value==null)return "全套";

				}},
			{ label: '登记入住时间', name: 'checkInTime', width: 80 },
			{ label: '预期到期时间', name: 'expectedDueTime', width: 80 },
			{ label: '退租时间', name: 'leaveTime', width: 80 },
			{ label: '是否已交押金', name: 'deposit', width: 80,formatter:function (value, options, row) {
				if(value==1){return '<i class="fa fa-check" aria-hidden="true"></i>';}else if(value==0){return '<i class="fa fa-times" aria-hidden="true"></i>';}else{return '';}} },
			{ label: '押金金额', name: 'depositMoney', width: 80 },
        ],
		viewrecords: true,
		height:550,
		rowNum:15,
		rowList :[15,30,50,100,500],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect:true,
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
		dpkeyword:null,
		dpdorm: null,
		deposit:null
	},
	created:function(){
	},
	methods: {

		querydp: function () {
			$("#jqGrid").jqGrid('setGridParam', {
				postData: {
					'pName': vm.dpkeyword,
					'sbgTime':$("#sbgTime").val(),
					'sendTime':$("#sendTime").val(),
					'dorm': vm.dpdorm,
					'deposit':vm.deposit
				},
				page: 1
			}).trigger("reloadGrid");
		},
		exportAll(){
		   $.ajax({
			   url:'../exprotdprecord/exprotall',
			   method: 'get',
			   data:''
		   }).then(res => {
		   	if(res.code==0){
		   		layer.msg('导出成功，请等候Excel下载中.....',{offset:'100px'})
		   		location.href='../exprotdprecord/downloadExcel'
			}
		   })
		},
		exportSelect(){
			var dpIds = getSelectedRows()
			if(dpIds==null){
				return
			}
			$.ajax({
				url:'../exprotdprecord/exportselect',
				method:'POST',
				data:JSON.stringify(dpIds)
			}).then(res=>{
				if(res.code==0){
					layer.msg('导出成功,请等候Excel下载中......',{offset:'100px'})
					location.href='../exprotdprecord/downloadExcel'
				}
			})
		},
		exportQuery(){
			location.href='../exprotdprecord/exportquery'
		}
	}
});