$(function () {
    $("#jqGrid").jqGrid({
        url: '../exampaper/list',
        datatype: "json",
        colModel: [			
			{ label: '试卷编号', name: 'id', width: 50, key: true },
			{ label: '试卷标题', name: 'title', width: 80 }, 			
			//{ label: '试卷要求', name: 'detail', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', width: 80 }, 			
			{ label: '作者', name: 'authorId', width: 80 }			
        ],
        viewrecords: true,
        height: 'auto',
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
		
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			location.href = "exampaper_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../exampaper/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		load :function(event){
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			$("#jqGridItem_have").jqGrid({
		        url: '../sysitem/list',
		        datatype: "json",
		        colModel: [			
					{ label: '题号', name: 'id', width: 50, key: true },
					{ label: '题目标题', name: 'title', width: 150 }
		        ],
				viewrecords: true,
		        height: 200,
		        rowNum: 5,
				rowList : [5,10,30,50],
		        rownumbers: true, 
		        rownumWidth: 25, 
		        width:560,
		        //autowidth:true,
		        multiselect: true,
		        pager: "#jqGridPagerItem_have",
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
			
			$("#jqGridItem_nohave").jqGrid({
			   	 url: '../exampaper/list',
			        datatype: "json",
			        colModel: [			
						{ label: '试卷编号', name: 'id', width: '25%', key: true },
						{ label: '试卷标题', name: 'title', width: '65%' }		
			        ],
					viewrecords: true,
			       height: 200,
			       rowNum: 5,
			       rowList : [5,10,30,50],
			       rownumbers: true, 
			       rownumWidth: 25, 
			       width:560,
			       //autowidth:true,
			       multiselect: true,
			       pager: "#jqGridPagerItem_nohave",
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
			$('#myModal').modal('show');
		}
	}
});

