webpackJsonp([14],{"3Sxe":function(t,e){},aNYF:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a("ea5G"),s={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("bodyHead"),t._v(" "),[a("div",{staticClass:"dingw"},[a("div",{staticClass:"comp"},[t._v("诚信")]),t._v(" "),a("div",{staticClass:"searchBox searchBox11"},[a("dl",{staticClass:"w1200"},[a("dt",[a("ul",{staticClass:"clear-fix"},[a("li",{class:["1"==t.companyType?"cur":""],on:{click:function(e){return t.companySwitch(1)}}},[t._v("\n                个人诚信\n              ")]),t._v(" "),a("li",{class:["2"==t.companyType?"cur":""],on:{click:function(e){return t.companySwitch(2)}}},[t._v("\n                企业诚信\n              ")])])]),t._v(" "),a("dd",{staticClass:"searchMain",staticStyle:{display:"block"}},[a("div",{staticClass:"box"},[a("el-form",{ref:"ruleForm",attrs:{model:t.ruleForm,"label-width":"100px",id:"isForm",onsubmit:"return false;"}},[a("div",{staticClass:"conditions"},[a("ul",[a("li",{staticClass:"citys",staticStyle:{width:"50%"}},[a("el-form-item",{attrs:{label:"诚信记录主体：",prop:"CorpName","label-width":"130px"}},[a("el-input",{attrs:{placeholder:"请输入诚信记录主体"},model:{value:t.ruleForm.CorpName,callback:function(e){t.$set(t.ruleForm,"CorpName",e)},expression:"ruleForm.CorpName"}})],1)],1),t._v(" "),a("li",{staticClass:"w430 citys",staticStyle:{width:"50%","margin-left":"0px"}},[a("el-form-item",{attrs:{label:"实施部门名称：",prop:"PrjName","label-width":"170px"}},[a("el-input",{attrs:{placeholder:"请输入实施部门名称"},model:{value:t.ruleForm.PrjName,callback:function(e){t.$set(t.ruleForm,"PrjName",e)},expression:"ruleForm.PrjName"}})],1)],1),t._v(" "),a("li",{staticClass:"w430 citys",staticStyle:{width:"50%","margin-left":"0px"}},[a("el-form-item",{attrs:{label:"征信对象：",prop:"Year","label-width":"130px"}},[a("el-select",{attrs:{placeholder:"全部"},model:{value:t.ruleForm.Year,callback:function(e){t.$set(t.ruleForm,"Year",e)},expression:"ruleForm.Year"}},t._l(t.ruleForm.Year_options,function(t,e){return a("el-option",{key:e,attrs:{label:t.LABEL,value:t.VALUE}})}),1)],1)],1),t._v(" "),a("li",{staticClass:"isForm_bottom",staticStyle:{width:"50%","margin-left":"0px"}},[a("button",{staticClass:"reset",attrs:{type:"reset"},on:{click:function(e){return t.resetForm("ruleForm")}}},[t._v("\n                        重置条件\n                      ")]),t._v(" "),a("input",{staticClass:"submit",attrs:{type:"submit",value:"查询"},on:{click:function(e){return t.search()}}})])])]),t._v(" "),a("div",{staticClass:"isForm_bottom"})])],1),t._v(" "),3==t.companyType?a("div",{staticClass:"box"},[a("el-form",{ref:"ruleForm",attrs:{model:t.ruleForm,"label-width":"100px",id:"isForm",onsubmit:"return false;"}},[a("div",{staticClass:"conditions"},[a("ul",[a("li",{staticClass:"citys",staticStyle:{width:"50%"}},[a("el-form-item",{attrs:{label:"企业名称：",prop:"CorpName","label-width":"130px"}},[a("el-input",{attrs:{placeholder:"请输入企业名称"},model:{value:t.ruleForm.CorpName,callback:function(e){t.$set(t.ruleForm,"CorpName",e)},expression:"ruleForm.CorpName"}})],1)],1),t._v(" "),a("li",{staticClass:"w430 citys",staticStyle:{width:"50%","margin-left":"0px"}},[a("el-form-item",{attrs:{label:"统一社会信用代码：",prop:"CorpCode","label-width":"170px"}},[a("el-input",{attrs:{placeholder:"请输入统一社会信用代码"},model:{value:t.ruleForm.CorpCode,callback:function(e){t.$set(t.ruleForm,"CorpCode",e)},expression:"ruleForm.CorpCode"}})],1)],1)])]),t._v(" "),a("div",{staticClass:"isForm_bottom"},[a("button",{staticClass:"reset",attrs:{type:"reset"},on:{click:function(e){return t.resetForm("ruleForm")}}},[t._v("\n                    重置条件\n                  ")]),t._v(" "),a("input",{staticClass:"submit",attrs:{type:"submit",value:"查询"},on:{click:function(e){return t.search()}}})])])],1):t._e()])])])]),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:"1"==t.companyType,expression:"companyType == '1'"}],staticClass:"companyList w1200"},[a("div",{staticClass:"qlist"},[t._v("行为列表")]),t._v(" "),a("div",{staticClass:"tab",staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},[a("div",{staticClass:"tabs",staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},[a("div",{staticClass:"item",class:{active:1==t.isTabsType},on:{click:function(e){return e.stopPropagation(),t.changeTabs(1)}}},[t._v("\n            全部\n            "),a("span",{staticClass:"xiangshang_tp"})]),t._v(" "),a("div",{staticClass:"item",class:{active:2==t.isTabsType},on:{click:function(e){return e.stopPropagation(),t.changeTabs(2)}}},[t._v("\n            不良行为\n            "),a("span",{staticClass:"xiangshang_tp"})]),t._v(" "),a("div",{staticClass:"item",class:{active:3==t.isTabsType},on:{click:function(e){return e.stopPropagation(),t.changeTabs(3)}}},[t._v("\n            良好行为\n            "),a("span",{staticClass:"xiangshang_tp"})])]),t._v(" "),a("div",{staticClass:"jieguos"},[t._v("\n          查询结果共\n          "),a("em",{staticClass:"jieguo2"},[t._v(" "+t._s(t.tableCount))]),t._v("\n\n          条记录\n        ")])]),t._v(" "),a("div",{staticClass:"table"},[a("div",{staticClass:"tableHead"},[a("div",{staticClass:"headPage",staticStyle:{float:"right"}},[a("el-pagination",{attrs:{"current-page":t.CurrentPage,"page-sizes":[15,30,40,400],"page-size":15,layout:"prev, pager, next, jumper",total:t.page},on:{"current-change":t.handleCurrentChange}})],1)]),t._v(" "),[a("vxe-table",{attrs:{align:"center",border:"","row-config":{isHover:!0},data:t.tableData,"seq-config":{startIndex:(t.CurrentPage-1)*t.pageSize}}},[a("vxe-column",{attrs:{type:"seq",width:"60",title:"序号"}}),t._v(" "),a("vxe-column",{attrs:{field:"",title:"诚信状态"},scopedSlots:t._u([{key:"default",fn:function(e){return["一等奖"==e.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_first.png",alt:""}})]:t._e(),t._v(" "),"二等奖"==e.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_second.png",alt:""}})]:t._e(),t._v(" "),"三等奖"==e.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_third.png",alt:""}})]:t._e(),t._v(" "),"表扬奖"==e.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_praise.png",alt:""}})]:t._e()]}}])}),t._v(" "),a("vxe-column",{attrs:{field:"PrjName",title:"诚信记录主体及编号",align:"center",width:"250"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("router-link",{staticStyle:{color:"#1577ff"},attrs:{to:{name:"integrityDetail",query:{ROW_GUID:e.row.ROW_GUID}},tag:"a"}},[[t._v(t._s(e.row.PrjName))]],2)]}}])}),t._v(" "),a("vxe-column",{attrs:{field:"CORPNAME",title:"决定内容",width:"200","show-header-overflow":"","show-overflow":"title","show-footer-overflow":""},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n                "+t._s(e.row.CorpName)+"\n              ")]}}])}),t._v(" "),a("vxe-column",{attrs:{field:"PriceName",width:"200",title:"实施部门","show-overflow":""}}),t._v(" "),a("vxe-column",{attrs:{field:"YEAR",title:"决定日期"}}),t._v(" "),a("vxe-column",{attrs:{field:"PriceType",title:"有效期"}}),t._v(" "),a("vxe-column",{attrs:{field:"",title:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{display:"inline-block",width:"70px",height:"30px",color:"#0094f2",cursor:"pointer","border-radius":"2px","line-height":"30px",border:"1px solid #0094f2"},on:{click:function(a){return t.certInfo(e.row)}}},[t._v("查看")])]}}])})],1)],t._v(" "),a("div",{staticClass:"pages"},[t.tableCount>300?a("span",{staticStyle:{"line-height":"32px",display:"inline-block"}},[t._v("仅展示前300条")]):t._e(),t._v(" "),a("el-pagination",{attrs:{"current-page":t.CurrentPage,"page-sizes":[15,30,40,400],"page-size":15,layout:"prev, pager, next, jumper",total:t.page},on:{"current-change":t.handleCurrentChange}})],1)],2)]),t._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:"2"==t.companyType,expression:"companyType == '2'"}],staticClass:"companyList w1200"},[a("div",{staticClass:"qlist"},[t._v("行为列表")]),t._v(" "),a("div",{staticClass:"tab",staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},[a("div",{staticClass:"tabs",staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},[a("div",{staticClass:"item",class:{active:1==t.isTabsType},on:{click:function(e){return e.stopPropagation(),t.changeTabs(1)}}},[t._v("\n            全部\n            "),a("span",{staticClass:"xiangshang_tp"})]),t._v(" "),a("div",{staticClass:"item",class:{active:2==t.isTabsType},on:{click:function(e){return e.stopPropagation(),t.changeTabs(2)}}},[t._v("\n            不良行为\n            "),a("span",{staticClass:"xiangshang_tp"})]),t._v(" "),a("div",{staticClass:"item",class:{active:3==t.isTabsType},on:{click:function(e){return e.stopPropagation(),t.changeTabs(3)}}},[t._v("\n            良好行为\n            "),a("span",{staticClass:"xiangshang_tp"})])]),t._v(" "),a("div",{staticClass:"jieguos"},[t._v("\n          查询结果共\n          "),a("em",{staticClass:"jieguo2"},[t._v(" "+t._s(t.tableCount))]),t._v("\n\n          条记录\n        ")])]),t._v(" "),a("div",{staticClass:"table"},[a("div",{staticClass:"tableHead"},[a("div",{staticClass:"headPage",staticStyle:{float:"right"}},[a("el-pagination",{attrs:{"current-page":t.CurrentPage,"page-sizes":[15,30,40,400],"page-size":15,layout:"prev, pager, next, jumper",total:t.page},on:{"current-change":t.handleCurrentChange}})],1)]),t._v(" "),[a("vxe-table",{attrs:{align:"center",border:"","row-config":{isHover:!0},data:t.tableData,"seq-config":{startIndex:(t.CurrentPage-1)*t.pageSize}}},[a("vxe-column",{attrs:{type:"seq",width:"60",title:"序号"}}),t._v(" "),a("vxe-column",{attrs:{field:"",title:"诚信状态"},scopedSlots:t._u([{key:"default",fn:function(e){return["一等奖"==e.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_first.png",alt:""}})]:t._e(),t._v(" "),"二等奖"==e.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_second.png",alt:""}})]:t._e(),t._v(" "),"三等奖"==e.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_third.png",alt:""}})]:t._e(),t._v(" "),"表扬奖"==e.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_praise.png",alt:""}})]:t._e()]}}])}),t._v(" "),a("vxe-column",{attrs:{field:"PrjName",title:"诚信记录主体及编号",align:"center",width:"250"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("router-link",{staticStyle:{color:"#1577ff"},attrs:{to:{name:"integrityDetail",query:{ROW_GUID:e.row.ROW_GUID}},tag:"a"}},[[t._v(t._s(e.row.PrjName))]],2)]}}])}),t._v(" "),a("vxe-column",{attrs:{field:"CORPNAME",title:"决定内容",width:"200","show-header-overflow":"","show-overflow":"title","show-footer-overflow":""},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n                "+t._s(e.row.CorpName)+"\n              ")]}}])}),t._v(" "),a("vxe-column",{attrs:{field:"PriceName",width:"200",title:"实施部门","show-overflow":""}}),t._v(" "),a("vxe-column",{attrs:{field:"YEAR",title:"决定日期"}}),t._v(" "),a("vxe-column",{attrs:{field:"PriceType",title:"有效期"}}),t._v(" "),a("vxe-column",{attrs:{field:"PriceType",title:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{display:"inline-block",width:"70px",height:"30px",color:"#0094f2",cursor:"pointer","border-radius":"2px","line-height":"30px",border:"1px solid #0094f2"},on:{click:function(a){return t.certInfo(e.row)}}},[t._v("查看")])]}}])})],1)],t._v(" "),a("div",{staticClass:"pages"},[t.tableCount>300?a("span",{staticStyle:{"line-height":"32px",display:"inline-block"}},[t._v("仅展示前300条")]):t._e(),t._v(" "),a("el-pagination",{attrs:{"current-page":t.CurrentPage,"page-sizes":[15,30,40,400],"page-size":15,layout:"prev, pager, next, jumper",total:t.page},on:{"current-change":t.handleCurrentChange}})],1)],2)])]],2)},staticRenderFns:[]};var r=function(t){a("3Sxe")},n=a("VU/8")(i.a,s,!1,r,"data-v-7e5bd156",null);e.default=n.exports},ea5G:function(t,e,a){"use strict";(function(t){var i=a("Cz8s"),s=a("Osha"),r=(a("IcnI"),a("mtWM")),n=a.n(r),o=a("n5Qe"),l=a("mw3O"),c=a.n(l);e.a={name:"integrity",components:{bodyHead:i.a,bread:s.a},data:function(){return{CurrentPage:1,breadlist:"",slideDown:!0,type:1,waiType:1,companyType:1,pageSize:15,tableCount:0,tableCount1:0,tableCount2:0,tableData:[],page:0,page1:0,page2:0,ruleForm:{PrjName:null,CorpName:null,CorpCode:null,WenHao:null,Year:null,Year_options:[]},tableData1:[],isTabsType:1}},methods:{changeTabs:function(t){this.isTabsType=t},companySwitch:function(t){this.companyType=t,this.isTabsType=1,this.ruleForm.CorpName=null,this.tableData=[],1==this.companyType?this.GetCreditList():3==this.companyType?this.GetReportCreditInfo():2==this.companyType&&this.falseInformation()},resetForm:function(t){var e=this;this.$nextTick(function(){e.$refs[t].resetFields()})},handleCurrentChange:function(t){this.CurrentPage=t,t>=40&&(t=40),1==this.companyType?this.GetCreditList():3==this.companyType?this.GetReportCreditInfo():this.falseInformation()},search:function(){var t=this.ruleForm;t.PrjName=""==t.PrjName?null:t.PrjName,t.CorpName=""==t.CorpName?null:t.CorpName,t.WenHao=""==t.WenHao?null:t.WenHao,t.Year=""==t.Year?null:t.Year,t.CorpCode=""==t.CorpCode?null:t.CorpCode,this.CurrentPage=1,1==this.companyType?this.GetCreditList():3==this.companyType&&this.GetReportCreditInfo()},sliderUp:function(e){"1"==e?(this.slideDown=!0,t(".searchBox").find(".searchMain").slideUp()):(this.slideDown=!1,t(".searchBox").find(".searchMain").slideDown())},GetReportCreditInfo:function(){var t=this,e=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(255, 255, 255, 0.5)",target:document.querySelector(".companyList")}),a={CorpCode:t.ruleForm.CorpCode,CorpName:t.ruleForm.CorpName,CurrentPage:t.CurrentPage,PageSize:t.pageSize,CorpName1:""};n.a.post(o.a.api.GetReportCreditInfo,c.a.stringify(a)).then(function(a){var i=a.data;i.Success?(t.tableData=i.Data,t.tableCount2=i.Count,i.Count>=300?t.page2=300:t.page2=i.Count,e.close()):(e.close(),o.a.errorMeaage(i.MsgID))}).catch(function(t){o.a.errorMeaage(t.response.status,t.response.data.msg),e.close()})},GetCreditList:function(){var t=this,e=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(255, 255, 255, 0.5)",target:document.querySelector(".companyList")}),a={PrjName:t.ruleForm.PrjName,CorpName:t.ruleForm.CorpName,WenHao:t.ruleForm.WenHao,Year:t.ruleForm.Year,CurrentPage:t.CurrentPage,PageSize:t.pageSize};n.a.post(o.a.api.GetCreditList,c.a.stringify(a)).then(function(a){var i=a.data;i.Success?(t.tableData=i.Data,t.tableCount=i.Count,i.Count>=300?t.page=300:t.page=i.Count,e.close()):(e.close(),o.a.errorMeaage(i.MsgID))}).catch(function(t){o.a.errorMeaage(t.response.status,t.response.data.msg),e.close()})},falseInformation:function(){var t=this,e=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(255, 255, 255, 0.5)",target:document.querySelector(".companyList")}),a={CurrentPage:t.CurrentPage,PageSize:t.pageSize};n.a.post(o.a.api.GetBlackList,c.a.stringify(a)).then(function(a){var i=a.data;i.Success&&(t.tableCount1=i.Count,i.Count>=300?t.page1=300:t.page1=i.Count,t.tableData1=i.Data),e.close()}).catch(function(t){o.a.errorMeaage(t.response.status,t.response.data.msg),e.close()})},YearData:function(){for(var t=(new Date).getFullYear(),e=[],a=2014;a<=t;a++){var i={VALUE:a,LABEL:a};e.push(i)}this.ruleForm.Year_options=e}},mounted:function(){this.YearData(),1==this.companyType?this.GetCreditList():2==this.companyType?this.falseInformation():this.GetReportCreditInfo()},created:function(){if(void 0!=this.$route.query.companyType)if(3==this.$route.query.companyType)this.companyType=this.$route.query.companyType;else{if(2!=this.$route.query.companyType)return this.$message.error("参数错误"),!1;this.companyType=this.$route.query.companyType}},watch:{},beforeRouteLeave:function(t,e,a){"integrityDetail"===t.name||"companyDetail"===t.name||"personnelDetail"===t.name||"personnelDetail_ysh"===t.name?this.$store.commit("setKeepAlive",["integrity"]):this.$store.commit("setKeepAlive",[]),a()}}}).call(e,a("7t+N"))}});