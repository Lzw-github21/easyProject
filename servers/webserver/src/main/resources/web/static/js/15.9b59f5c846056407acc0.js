webpackJsonp([15],{FpXa:function(e,t){},L2Cg:function(e,t,a){"use strict";(function(e){var i=a("Cz8s"),s=a("Osha"),o=(a("IcnI"),a("mtWM")),n=a.n(o),r=a("n5Qe"),l=a("mw3O"),c=a.n(l);t.a={name:"prospect",components:{bodyHead:i.a,bread:s.a},data:function(){return{isTabsType:1,CurrentPage:1,breadlist:"",slideDown:!0,type:1,waiType:1,companyType:1,pageSize:15,tableCount:0,tableCount1:0,tableCount2:0,tableData:[],tableData111:[{name:"任震英",date:"1913",hangye:"规划设计",option:"",address:"兰州市市政府",pici:"1"},{name:"付文德",date:"1926",hangye:"市政",option:"给排水",address:"中国市政工程西北设计研究院有限公司",pici:"2"},{name:"刘纯翰",date:"1934",hangye:"建筑",option:"建筑设计",address:"甘肃省建筑设计研究院有限公司",pici:"2"}],tableData2:[{name:"杨光",date:"1964",hangye:"电力",option:"电力工程",address:"中国能源建设集团甘肃省电力设计院有限公司",pici:"1"},{name:"马国纲",date:"1966",hangye:"市政",option:"桥梁与道路",address:"中国市政工程西北设计研究院有限公司",pici:"1"},{name:"宁崇瑞",date:"1957",hangye:"建筑",option:"建筑设计",address:"兰州有色冶金设计研究院有限公司",pici:"1"},{name:"史春海",date:"1966",hangye:"市政",option:"给排水",address:"中国市政工程西北设计研究院有限公司",pici:"1"},{name:"吕生玺",date:"1970",hangye:"水利",option:"水利水电工程规划设计",address:"甘肃省水利水电勘测设计研究院有限责任公司",pici:"1"},{name:"吴若玉",date:"1966",hangye:"铁路",option:"铁路设计",address:"兰州铁道设计院",pici:"1"},{name:"张举涛",date:"1968",hangye:"建筑",option:"建筑结构设计",address:"甘肃省建筑设计研究院有限公司",pici:"1"},{name:"张恩祥",date:"1963",hangye:"勘察",option:"勘察",address:"甘肃中建市政工程勘察设计研究院有限公司",pici:"1"},{name:"章海峰",date:"1970",hangye:"建筑",option:"建筑",address:"甘肃土木工程科学研究院有限公司",pici:"1"},{name:"黄锐",date:"1970",hangye:"建筑",option:"建筑结构",address:"甘肃省建筑设计研究院有限公司",pici:"1"},{name:"韩友续",date:"1963",hangye:"公路",option:"桥梁专业",address:"甘肃省交通科学研究院有限公司",pici:"1"},{name:"廖小平",date:"1965",hangye:"勘察",option:"勘察",address:"中铁西北科学研究院有限公司",pici:"1"},{name:"马小蕾",date:"1970",hangye:"市政",option:"给水排水",address:"中国市政工程西北设计研究院有限公司",pici:"2"},{name:"王国尚",date:"1961",hangye:"勘察",option:"岩土工程与水文气象",address:"中国能源建设集团甘肃省电力设计院有限公司",pici:"2"},{name:"王桢",date:"1961",hangye:"勘察",option:"岩土工程",address:"中铁西北科学研究院有限公司",pici:"2"},{name:"王海梅",date:"1970",hangye:"市政",option:"给水排水",address:"中国市政工程西北设计研究院有限公司",pici:"2"},{name:"冯志涛",date:"1965",hangye:"建筑",option:"建筑设计",address:"甘肃省建筑设计研究院有限公司",pici:"2"},{name:"张森安",date:"1963",hangye:"勘察",option:"工程勘察",address:"甘肃中建市政工程勘察设计研究院有限公司",pici:"2"},{name:"陈天镭",date:"1963",hangye:"建筑",option:"建筑、水工结构",address:"兰州有色冶金设计研究院有限公司",pici:"2"},{name:"陈晓东",date:"1964",hangye:"水利",option:"水利水电",address:"甘肃省水利水电勘测设计研究院有限责任公司",pici:"2"},{name:"金立新",date:"1966",hangye:"勘察",option:"测绘",address:"甘肃铁道综合工程勘察院有限公司",pici:"2"},{name:"曹军",date:"1964",hangye:"规划",option:"城乡规划",address:"兰州市城乡规划设计研究院",pici:"2"},{name:"樊江",date:"1971",hangye:"公路",option:"公路",address:"甘肃省交通规划勘察设计院股份有限公司",pici:"2"}],page:0,page1:0,page2:0,ruleForm:{PrjName:null,CorpName:null,CorpCode:null,WenHao:null,Year:null,Year_options:[],Year_options2:[{value:"规划设计",label:"规划设计"},{value:"建筑",label:"建筑"},{value:"市政",label:"市政"},{value:"勘察",label:"勘察"},{value:"水利",label:"水利"},{value:"公路",label:"公路"},{value:"铁路",label:"铁路"}],Year_options1:[{value:1,label:"男"},{value:2,label:"女"}]},tableData1:[]}},methods:{changeTabs:function(e){this.isTabsType=e},companySwitch:function(e){this.companyType=e,this.ruleForm.CorpName=null,this.tableData=[],1==this.companyType?this.GetCreditList():3==this.companyType?this.GetReportCreditInfo():2==this.companyType&&this.falseInformation()},resetForm:function(e){var t=this;this.$nextTick(function(){t.$refs[e].resetFields()})},handleCurrentChange:function(e){this.CurrentPage=e,e>=40&&(e=40),1==this.companyType?this.GetCreditList():3==this.companyType?this.GetReportCreditInfo():this.falseInformation()},search:function(){var e=this.ruleForm;e.PrjName=""==e.PrjName?null:e.PrjName,e.CorpName=""==e.CorpName?null:e.CorpName,e.WenHao=""==e.WenHao?null:e.WenHao,e.Year=""==e.Year?null:e.Year,e.CorpCode=""==e.CorpCode?null:e.CorpCode,this.CurrentPage=1,1==this.companyType?this.GetCreditList():3==this.companyType&&this.GetReportCreditInfo()},sliderUp:function(t){"1"==t?(this.slideDown=!0,e(".searchBox").find(".searchMain").slideUp()):(this.slideDown=!1,e(".searchBox").find(".searchMain").slideDown())},GetReportCreditInfo:function(){var e=this,t=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(255, 255, 255, 0.5)",target:document.querySelector(".companyList")}),a={CorpCode:e.ruleForm.CorpCode,CorpName:e.ruleForm.CorpName,CurrentPage:e.CurrentPage,PageSize:e.pageSize,CorpName1:""};n.a.post(r.a.api.GetReportCreditInfo,c.a.stringify(a)).then(function(a){var i=a.data;i.Success?(e.tableData=i.Data,e.tableCount2=i.Count,i.Count>=300?e.page2=300:e.page2=i.Count,t.close()):(t.close(),r.a.errorMeaage(i.MsgID))}).catch(function(e){r.a.errorMeaage(e.response.status,e.response.data.msg),t.close()})},GetCreditList:function(){var e=this,t=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(255, 255, 255, 0.5)",target:document.querySelector(".companyList")}),a={PrjName:e.ruleForm.PrjName,CorpName:e.ruleForm.CorpName,WenHao:e.ruleForm.WenHao,Year:e.ruleForm.Year,CurrentPage:e.CurrentPage,PageSize:e.pageSize};n.a.post(r.a.api.GetCreditList,c.a.stringify(a)).then(function(a){var i=a.data;i.Success?(e.tableData=i.Data,e.tableCount=i.Count,i.Count>=300?e.page=300:e.page=i.Count,t.close()):(t.close(),r.a.errorMeaage(i.MsgID))}).catch(function(e){r.a.errorMeaage(e.response.status,e.response.data.msg),t.close()})},falseInformation:function(){var e=this,t=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(255, 255, 255, 0.5)",target:document.querySelector(".companyList")}),a={CurrentPage:e.CurrentPage,PageSize:e.pageSize};n.a.post(r.a.api.GetBlackList,c.a.stringify(a)).then(function(a){var i=a.data;i.Success&&(e.tableCount1=i.Count,i.Count>=300?e.page1=300:e.page1=i.Count,e.tableData1=i.Data),t.close()}).catch(function(e){r.a.errorMeaage(e.response.status,e.response.data.msg),t.close()})},YearData:function(){for(var e=(new Date).getFullYear(),t=[],a=2014;a<=e;a++){var i={VALUE:a,LABEL:a};t.push(i)}this.ruleForm.Year_options=t}},mounted:function(){this.YearData(),1==this.companyType?this.GetCreditList():2==this.companyType?this.falseInformation():this.GetReportCreditInfo()},created:function(){if(void 0!=this.$route.query.companyType)if(3==this.$route.query.companyType)this.companyType=this.$route.query.companyType;else{if(2!=this.$route.query.companyType)return this.$message.error("参数错误"),!1;this.companyType=this.$route.query.companyType}},watch:{},beforeRouteLeave:function(e,t,a){"integrityDetail"===e.name||"companyDetail"===e.name||"personnelDetail"===e.name||"personnelDetail_ysh"===e.name?this.$store.commit("setKeepAlive",["integrity"]):this.$store.commit("setKeepAlive",[]),a()}}}).call(t,a("7t+N"))},"PR3+":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=a("L2Cg"),s={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("bodyHead",{attrs:{type:1}}),e._v(" "),[a("div",{staticClass:"dingw"},[a("div",{staticClass:"comp"},[e._v("勘察设计大师")]),e._v(" "),a("div",{staticClass:"searchBox searchBox11"},[a("dl",{staticClass:"w1200"},[a("div",{staticClass:"crumbs",staticStyle:{"margin-top":"0"}},[a("div",{staticClass:"w1200"},[a("bread")],1)]),e._v(" "),a("dd",{staticClass:"searchMain",staticStyle:{display:"block"}},[1==e.companyType?a("div",{staticClass:"box"}):e._e(),e._v(" "),3==e.companyType?a("div",{staticClass:"box"},[a("el-form",{ref:"ruleForm",attrs:{model:e.ruleForm,"label-width":"100px",id:"isForm",onsubmit:"return false;"}},[a("div",{staticClass:"conditions"},[a("ul",[a("li",{staticClass:"citys",staticStyle:{width:"50%"}},[a("el-form-item",{attrs:{label:"企业名称：",prop:"CorpName","label-width":"130px"}},[a("el-input",{attrs:{placeholder:"请输入企业名称"},model:{value:e.ruleForm.CorpName,callback:function(t){e.$set(e.ruleForm,"CorpName",t)},expression:"ruleForm.CorpName"}})],1)],1),e._v(" "),a("li",{staticClass:"w430 citys",staticStyle:{width:"50%","margin-left":"0px"}},[a("el-form-item",{attrs:{label:"统一社会信用代码：",prop:"CorpCode","label-width":"170px"}},[a("el-input",{attrs:{placeholder:"请输入统一社会信用代码"},model:{value:e.ruleForm.CorpCode,callback:function(t){e.$set(e.ruleForm,"CorpCode",t)},expression:"ruleForm.CorpCode"}})],1)],1)])]),e._v(" "),a("div",{staticClass:"isForm_bottom"},[a("button",{staticClass:"reset",attrs:{type:"reset"},on:{click:function(t){return e.resetForm("ruleForm")}}},[e._v("\n                    重置条件\n                  ")]),e._v(" "),a("input",{staticClass:"submit",attrs:{type:"submit",value:"查询"},on:{click:function(t){return e.search()}}})])])],1):e._e()])])])]),e._v(" "),a("div",{staticClass:"companyList w1200"},[a("div",{staticClass:"qlistes"},[e._v("勘察设计大师列表")]),e._v(" "),a("div",{staticClass:"tab",staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},[a("div",{staticClass:"tabs",staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},[a("div",{staticClass:"item",class:{active:1==e.isTabsType},on:{click:function(t){return t.stopPropagation(),e.changeTabs(1)}}},[e._v("\n            全国工程勘察设计大师（甘肃）\n            "),a("span",{staticClass:"xiangshang_tp"})]),e._v(" "),a("div",{staticClass:"item",class:{active:2==e.isTabsType},on:{click:function(t){return t.stopPropagation(),e.changeTabs(2)}}},[e._v("\n            甘肃省工程勘察设计大师\n            "),a("span",{staticClass:"xiangshang_tp"})])]),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:1==e.isTabsType,expression:"isTabsType == 1"}],staticClass:"jieguos"},[e._v("\n          查询结果共\n          "),a("em",{staticClass:"jieguo2"},[e._v(" 3")]),e._v("\n\n          条记录\n        ")]),e._v(" "),a("div",{directives:[{name:"show",rawName:"v-show",value:2==e.isTabsType,expression:"isTabsType == 2"}],staticClass:"jieguos"},[e._v("\n          查询结果共\n          "),a("em",{staticClass:"jieguo2"},[e._v(" 23")]),e._v("\n\n          条记录\n        ")])]),e._v(" "),a("div",{staticClass:"table"},[a("div",{staticClass:"tableHead"},[a("div",{staticClass:"headPage",staticStyle:{float:"right"}},[a("el-pagination",{attrs:{"current-page":e.CurrentPage,"page-sizes":[15,30,40,400],"page-size":15,layout:"prev, pager, next, jumper",total:e.page},on:{"current-change":e.handleCurrentChange}})],1)]),e._v(" "),[a("vxe-table",{directives:[{name:"show",rawName:"v-show",value:1==e.isTabsType,expression:"isTabsType == 1"}],attrs:{align:"center",border:"","row-config":{isHover:!0},data:e.tableData111,"seq-config":{startIndex:(e.CurrentPage-1)*e.pageSize}}},[a("vxe-column",{attrs:{type:"seq",width:"60",title:"序号"}}),e._v(" "),a("vxe-column",{attrs:{field:"name",title:"姓名"}}),e._v(" "),a("vxe-column",{attrs:{field:"date",width:"150",title:"出生年","show-overflow":""}}),e._v(" "),a("vxe-column",{attrs:{field:"hangye",title:"行业",width:"200","show-header-overflow":"","show-overflow":"title","show-footer-overflow":""}}),e._v(" "),a("vxe-column",{attrs:{field:"option",width:"180",title:"专业","show-overflow":""}}),e._v(" "),a("vxe-column",{attrs:{field:"address",width:"220",title:"单位","show-overflow":""}}),e._v(" "),a("vxe-column",{attrs:{field:"pici",width:"200",title:"批次","show-overflow":""}})],1)],e._v(" "),[a("vxe-table",{directives:[{name:"show",rawName:"v-show",value:2==e.isTabsType,expression:"isTabsType == 2"}],attrs:{align:"center",border:"","row-config":{isHover:!0},data:e.tableData2,"seq-config":{startIndex:(e.CurrentPage-1)*e.pageSize}}},[a("vxe-column",{attrs:{type:"seq",width:"60",title:"序号"}}),e._v(" "),a("vxe-column",{attrs:{field:"name",title:"姓名"}}),e._v(" "),a("vxe-column",{attrs:{field:"date",title:"出生年",align:"center",width:"150"}}),e._v(" "),a("vxe-column",{attrs:{field:"hangye",title:"行业",width:"200","show-header-overflow":"","show-overflow":"title","show-footer-overflow":""}}),e._v(" "),a("vxe-column",{attrs:{field:"option",width:"180",title:"专业","show-overflow":""}}),e._v(" "),a("vxe-column",{attrs:{field:"address",width:"220",title:"单位","show-overflow":""}}),e._v(" "),a("vxe-column",{attrs:{field:"pici",width:"200",title:"批次","show-overflow":""}})],1)],e._v(" "),[a("vxe-table",{directives:[{name:"show",rawName:"v-show",value:3==e.isTabsType,expression:"isTabsType == 3"}],attrs:{align:"center",border:"","row-config":{isHover:!0},data:e.tableData,"seq-config":{startIndex:(e.CurrentPage-1)*e.pageSize}}},[a("vxe-column",{attrs:{type:"seq",width:"60",title:"序号"}}),e._v(" "),a("vxe-column",{attrs:{field:"",title:"姓名"},scopedSlots:e._u([{key:"default",fn:function(t){return["一等奖"==t.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_first.png",alt:""}})]:e._e(),e._v(" "),"二等奖"==t.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_second.png",alt:""}})]:e._e(),e._v(" "),"三等奖"==t.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_third.png",alt:""}})]:e._e(),e._v(" "),"表扬奖"==t.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_praise.png",alt:""}})]:e._e()]}}])}),e._v(" "),a("vxe-column",{attrs:{field:"PrjName",title:"性别",align:"center",width:"250"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("router-link",{staticStyle:{color:"#1577ff"},attrs:{to:{name:"integrityDetail",query:{ROW_GUID:t.row.ROW_GUID}},tag:"a"}},[[e._v(e._s(t.row.PrjName))]],2)]}}])}),e._v(" "),a("vxe-column",{attrs:{field:"CORPNAME",title:"从事单位",width:"200","show-header-overflow":"","show-overflow":"title","show-footer-overflow":""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(t.row.CorpName)+"\n              ")]}}])}),e._v(" "),a("vxe-column",{attrs:{field:"PriceName",width:"200",title:"职称","show-overflow":""}})],1)],e._v(" "),[a("vxe-table",{directives:[{name:"show",rawName:"v-show",value:4==e.isTabsType,expression:"isTabsType == 4"}],attrs:{align:"center",border:"","row-config":{isHover:!0},data:e.tableData,"seq-config":{startIndex:(e.CurrentPage-1)*e.pageSize}}},[a("vxe-column",{attrs:{type:"seq",width:"60",title:"序号"}}),e._v(" "),a("vxe-column",{attrs:{field:"",title:"姓名"},scopedSlots:e._u([{key:"default",fn:function(t){return["一等奖"==t.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_first.png",alt:""}})]:e._e(),e._v(" "),"二等奖"==t.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_second.png",alt:""}})]:e._e(),e._v(" "),"三等奖"==t.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_third.png",alt:""}})]:e._e(),e._v(" "),"表扬奖"==t.row.LevelName?[a("img",{staticClass:"integrity_icon",attrs:{src:"static/img/icon_praise.png",alt:""}})]:e._e()]}}])}),e._v(" "),a("vxe-column",{attrs:{field:"PrjName",title:"性别",align:"center",width:"250"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("router-link",{staticStyle:{color:"#1577ff"},attrs:{to:{name:"integrityDetail",query:{ROW_GUID:t.row.ROW_GUID}},tag:"a"}},[[e._v(e._s(t.row.PrjName))]],2)]}}])}),e._v(" "),a("vxe-column",{attrs:{field:"CORPNAME",title:"从事单位",width:"200","show-header-overflow":"","show-overflow":"title","show-footer-overflow":""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(t.row.CorpName)+"\n              ")]}}])}),e._v(" "),a("vxe-column",{attrs:{field:"PriceName",width:"200",title:"职称","show-overflow":""}})],1)],e._v(" "),a("div",{staticClass:"pages"},[e.tableCount>300?a("span",{staticStyle:{"line-height":"32px",display:"inline-block"}},[e._v("仅展示前300条")]):e._e(),e._v(" "),a("el-pagination",{attrs:{"current-page":e.CurrentPage,"page-sizes":[15,30,40,400],"page-size":15,layout:"prev, pager, next, jumper",total:e.page},on:{"current-change":e.handleCurrentChange}})],1)],2)])]],2)},staticRenderFns:[]};var o=function(e){a("FpXa")},n=a("VU/8")(i.a,s,!1,o,"data-v-677d4b1d",null);t.default=n.exports}});