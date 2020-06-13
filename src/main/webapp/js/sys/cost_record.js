var model = [{label: 'pdId', name: 'pdId', width: 50, key: true, hidden: true}
   , {label: '人员名字', name: 'pName', width: 80}
   , {label: '入住房屋', name: 'dAddress', width: 80}
   , {
    label: '人员类别',
    name: 'pCategroy',
    width: 80,
    formatter: function (value) {
        if (value == 1) return "交流干部";
        if (value == 2) return "借调人员";
        if (value == 3) return "新员工";
    }}
    , {label: '部室', name: 'pDepartment', width: 80}
    ,{label: '入住时间', name: 'checkInTime', width: 80}
    ,{label: '到期时间', name: 'expectedDueTime', width: 80}
    ,{label: '退租时间', name: 'leaveTime', width: 80}]
var names = ['pdId', '人员名字', '入住房屋', '人员类型', '部室','入住时间','到期时间','退租时间'];
$.getJSON("../cost/list", function (r) {
    for (var i = 0; i < r.costList.length; i++) {
        names.push(r.costList[i].cName);
        model.push({label: r.costList[i].cName, name: r.costList[i].cId, width: 100})
    }
    names.push('总额');
    model.push({label:'总额', name:'costs', width: 80,formatter:function (value) {
            if(value!=null){
                var sum=0;
                for(var i=0;i<value.length;i++){sum=sum+value[i].value;}
                return sum;
            }else{return 0;}
        }});
});
function intiMain(url) {
    let reportData =[];
    let pdId = [];
    $.getJSON(url, function (r) {
        for (var i = 0; i < r.page.list.length; i++) {
            let reportObject = {};
            $.each(r.page.list[i], function (key, value) {
                if (key != "pId") {
                    reportObject[key] = value;
                }
                pdId = r.page.list[i].pdId;
            });
            console.log(reportObject);
            var cost = r.page.list[i].costs;
            for (var j = 0; j < cost.length; j++) {
                reportObject[cost[j].cId] = cost[j].value;
            }
            reportData.push(reportObject);
        }
        $("#jqGrid").jqGrid({
            datatype: "local",
            colNames: names,
            colModel: model,
            viewrecords: true,
            height:700,
            rowNum:15,
            rowList :[15,30,50,100,500],
            rownumbers: true,
            rownumWidth: 25,
            autowidth: true,
            multiselect: true,
            pager: "#jqGridPager",
            jsonReader : {
                page: "page.currPage",
                total: "page.totalPage",
                records: "page.totalCount"
            },
            prmNames: {
                page:"page",
                rows: "limit",
                order: "order"
            },

            gridComplete: function () {
                //隐藏grid底部滚动条
                $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
            }
        });
        for (var i = 0; i <= reportData.length; i++) {
            $("#jqGrid").jqGrid('addRowData', pdId[i], reportData[i]);
        }
    });
}
$(function () {
    $("#djqGrid").jqGrid({
        url: '../department/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'dpmId', width:250, key: true,hidden:true },
            { label: '部室名称', name: 'dpmName', width:720 }
        ],
        viewrecords: true,
        height:180,
        rowNum:5,
        rowList : [5,10,20],
        rownumbers:true,
        rownumWidth: 25,
        autowidth:true,
        multiselect:false,
        pager: "#djqGridPager",
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
            $("#djqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

});
/********************************************VUE部分*******************************************************************/

var vm = new Vue({
    el:'#rrapp',
    data:{
        modaltitle:'',
        pDepartment:'',
        pCategroy:'',
        month:'',
        department:{},
    },
    created:function () {

        $.get("../department/list",function (r) {
            vm.department=r.departmentList;
        });
        var date = new Date();
        this.month = date.getMonth() + 1;
        var url="../cost/recordlist?_search&limit=10&page=1"
        intiMain(url);
    },
    methods: {
        //搜索
        search: function () {
            //清空表格重新加载
            $("#jqGrid").jqGrid("clearGridData");
            //把要搜索的连接加到请求后
            var url = "../cost/recordlist?_search&limit=10&page=1&pDepartment=" + vm.pDepartment + "&pCategroy=" + vm.pCategroy;
            //调用表格初始化函数
            intiMain(url);
        },
        searchDpm:function(index){
            vm. pDepartment=vm.department[index].dpmName;
        },
    }
});
function getSelectedRowName1() {
    //获取部门名字
    var grid = $("#djqGrid").getGridParam("selrow");
    var rowdpmName = $("#djqGrid").getCell(grid,"dpmName");
    //限制多选
    var selecteddpmName = $("#djqGrid").getGridParam("selarrrow");
    if(selecteddpmName.length > 1){
        return ;
    }
    return rowdpmName;
}