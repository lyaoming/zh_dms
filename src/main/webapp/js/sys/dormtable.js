$(function () {
    $("#jqGrid").jqGrid({
        url: '../dormtable/list',
        datatype: "json",
        colModel: [
            { label:'ID',name:'dId',width:50,key:true,hidden:true},
            { label: '房屋号', name:'dAddress', width:80 },
            { label: '房屋类型', name: 'dType', width: 80,formatter:function (value, options, row) {
                    if(value==1){
                        return '合并';
                    }else if(value==2){
                        return '拆分';
                    }else{
                        return '';
                    }
                } },
            { label: '房屋规格', name: 'dSpecification', width: 80,formatter:function (value, options, row) {
                    if(value==1){
                        return '2房一厅';
                    }else if(value==2){
                        return '3房一厅';
                    }else if(value==3){
                        return '4房一厅';
                    }else{
                        return '';
                    }
                } },
            { label: '使用权管理', name:'useAdmin', width:80 },
            { label: '使用单位', name:'useUnit', width:80 },
            { label: '可住人数', name: 'dAllnum', width: 80 },
            { label: '现住人数', name: 'dNum', width: 80 },
            {label:'空房数',name:'dNull',width:80},
            {label:'入住率',name:'occupancyRate',width:80,formatter:function (value) {
                  if(value==1){
                      return "<span style='color:red;font-weight: bold'>"+value*100+"%</span>";
                  }else{
                      return "<span style='color:green;font-weight: bold'>"+value*100+"%</span>";
                  }
                }},


        ],
        viewrecords: true,
        height:750,
        rowNum: 20,
        rowList : [20,30,50,100,500],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount",
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
        showdata:{
            sum:null,
            nownum:null,
            nullnum:null,
            reta:null
        }
    },
    created:function () {
       $.get("../dormtable/showdata",function (r) {
           vm.showdata.sum=r.res[0];
           vm.showdata.nownum=r.res[1];
           vm.showdata.nullnum=r.res[2];
           vm.showdata.reta=r.res[3];
       })
    },
    methods:{
        //查询
        search:function(){
            $("#jqGrid").jqGrid('setGridParam',{
                postData: {
                "bgTime":$("#dbgTime").val(),
                "endTime":$("#dendTime").val()
                },
                page:1
            }).trigger("reloadGrid");
        },
        //导出所有
        exportdate:function() {
            var month=vm.month;
            layer.confirm('确定要导出全部记录？',{offset:'100px'}, function() {
                $.ajax({
                    type: "post",
                    url: "../dormtable/exportAll",
                    success: function (r) {
                        if (r.code == 0) {
                            layer.msg('导出成功,请等候Excel下载中......', {offset: '100px'}, function (index) {
                                location.href = "../dormtable/downloadExcel";
                            });
                        } else {
                            layer.msg(r.msg, {offset: '100px'});
                        }
                    }
                });
            });
        },
        //导出选中
        selectexport:function () {
            var month=vm.month;
            var pdIds = getSelectedRows();
            if(pdIds == null){
                return ;
            }
            layer.confirm('确定要导出选中的记录？',{offset:'100px'}, function(){
                $.ajax({
                    type: "post",
                    url: "../dormtable/exportSelect",
                    data: JSON.stringify(pdIds),
                    success: function(r){
                        if(r.code == 0){
                            layer.msg('导出成功,请等候Excel下载中......',{offset:'100px'}, function(index){
                                location.href="../dormtable/downloadExcel";
                            });
                        }else{
                            layer.msg(r.msg,{offset:'100px'});
                        }
                    }
                });
            });
        },
        //导出查询
        classexport:function () {
            var month=vm.month;
            location.href="../dormtable/downloadExcel";
        },

    }
});