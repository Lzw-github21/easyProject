webpackJsonp([8],{"3HPh":function(t,s){},Tmjx:function(t,s){},fanM:function(t,s,i){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var e=i("BO1k"),a=i.n(e),n=i("Cz8s"),o=i("Osha"),l=i("mtWM"),c=i.n(l),r=i("n5Qe"),v=i("mw3O"),d=i.n(v),_=i("8Q15"),p=i("OxuT"),u=i("RrG1"),f=i("VHy8"),C=i("yrNt"),h=(i("IcnI"),{name:"sunshiner",components:{bodyHead:n.a,bread:o.a,aptitude:_.a,regsiterPersonel:p.a,unRegsiterPersonel:u.a,intro:f.a,resume:C.a},beforeRouteLeave:function(t,s,i){"company"===t.name?this.$store.commit("setKeepAlive",["company"]):"integrity"===t.name?this.$store.commit("setKeepAlive",["integrity"]):"tatistics"===t.name?this.$store.commit("setKeepAlive",["tatistics"]):this.$store.commit("setKeepAlive",[]),i()},data:function(){return{type:1,numKey:0,zzCount:0,jlCount:0,jjCount:0,corpCode:"",FullCorpCode:"",info:{},isCeiling:!1,companyTabsList:[{name:"信息详情",isActive:!0},{name:"项目进度",isActive:!0}],companyTitleList:[{name:"信息详情",title:"收起",isShow:!0},{name:"项目进度",title:"",isShow:!0}]}},methods:{setNum:function(){this.numKey++},ceiling:function(t){var s=t.target.documentElement.scrollTop||t.target.body.scrollTop;this.isCeiling=s>=363},tag:function(t){var s=this,i=!0,e=!1,n=void 0;try{for(var o,l=a()(this.companyTabsList);!(i=(o=l.next()).done);i=!0){o.value.isActive=!1}}catch(t){e=!0,n=t}finally{try{!i&&l.return&&l.return()}finally{if(e)throw n}}setTimeout(function(){document.querySelector("#f"+t).scrollIntoView({behavior:"smooth"},500),s.companyTitleList[t].isShow=!0,s.companyTitleList[t].title="收起"},20),this.companyTabsList[t].isActive=!0},toggle:function(t){var s=!0,i=!1,e=void 0;try{for(var n,o=a()(this.companyTabsList);!(s=(n=o.next()).done);s=!0){n.value.isActive=!1}}catch(t){i=!0,e=t}finally{try{!s&&o.return&&o.return()}finally{if(i)throw e}}var l=this.companyTitleList[t];l.isShow?(l.isShow=!1,l.title="展开"):setTimeout(function(){l.isShow=!0,l.title="收起"},20),this.companyTabsList[t].isActive=!0},testClick:function(){},tabbar:function(t){this.type=t},companyDetailInfo:function(){var t=this,s=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(255, 255, 255, 0.5)",target:document.querySelector(".detailTable")}),i={corpCode:t.corpCode};c.a.post(r.a.api.GetCorpBasicInfoList,d.a.stringify(i)).then(function(i){var e=i.data;e.Success&&0!=e.Count?(t.info=e.Data[0],t.FullCorpCode=e.Data[0].FULLCORPCODE,null!==t.info.CASEDATE&&(t.info.CASEDATE=t.info.CASEDATE.substr(0,10)),s.close()):(s.close(),1e3==e.MsgID?t.$alert("未查到企业信息,请确认企业是否已在四库备案或审核通过！","系统提示",{confirmButtonText:"确定",callback:function(s){t.$router.go(-1)}}):r.a.errorMeaage(e.MsgID))}).catch(function(t){console.log(t),r.a.errorMeaage(t.response.status,t.response.data.msg),s.close()})}},mounted:function(){var t=this;this.$nextTick(function(){t.setNum(),window.addEventListener("scroll",t.ceiling,!1),setTimeout(function(){t.zzCount=window.sessionStorage.getItem("zzCount"),t.jjCount=window.sessionStorage.getItem("jjCount"),t.jlCount=window.sessionStorage.getItem("jlCount")},1e3)}),this.companyDetailInfo()},created:function(){this.corpCode=this.$route.query.corpCode},watch:{}}),m={render:function(){var t=this,s=t.$createElement,i=t._self._c||s;return i("div",{ref:"body"},[i("bodyHead",{attrs:{type:1}}),t._v(" "),i("div",{staticClass:"bg1"},[i("div",{staticClass:"titles w1200"},[t._v(t._s(t.info.CORPNAME)+"("+t._s(t.info.CORPCODE)+")")]),t._v(" "),i("div",{staticClass:"crumbs"},[i("div",{staticClass:"w1200"},[i("bread",{staticClass:"bread"})],1)])]),t._v(" "),i("div",{staticClass:"offten"},[i("div",{staticClass:"main"},[i("div",{staticClass:"right"},[i("div",{staticClass:"detailTable block",staticStyle:{"margin-top":"20px"},attrs:{id:"f0"}},[i("div",{staticClass:"infoHeader",on:{click:function(s){return s.stopPropagation(),t.toggle(0)}}},[i("div",{staticClass:"content",class:{clear:!t.companyTitleList[0].isShow}},[i("div",{staticClass:"titleLeft"},[i("span",[t._v(t._s(t.companyTitleList[0].name))])]),t._v(" "),i("div",{staticClass:"titleRight"},[i("span",[t._v(t._s(t.companyTitleList[0].title))]),t._v(" "),i("div",{staticClass:"box",class:{active:t.companyTitleList[0].isShow}},[i("img",{attrs:{src:"static/img/icon_more222.png",alt:"",srcset:""}})])])])]),t._v(" "),i("transition-group",{attrs:{name:"lyric"}},[t.companyTitleList[0].isShow?i("table",{key:1},[i("tbody",[i("tr",[i("td",{staticClass:"w180"},[t._v("项目编号")]),t._v(" "),i("td",{staticStyle:{width:"250px"}},[t._v(t._s(t.info.CORPCODE))]),t._v(" "),i("td",{staticClass:"w150"},[t._v("项目类型")]),t._v(" "),i("td",{staticStyle:{width:"250px"}},[t._v("\n                    "+t._s(t.info.AptitudeEconTypeName)+"\n                  ")]),t._v(" "),i("td",{staticClass:"w150"},[t._v("建筑面积")]),t._v(" "),i("td",{staticStyle:{width:"250px"}},[t._v(t._s(t.info.LICENSENUM))])]),t._v(" "),i("tr",[i("td",{staticClass:"w150"},[t._v("审查机构")]),t._v(" "),i("td",{staticStyle:{width:"250px"}},[t._v(t._s(t.info.LEGALMAN))]),t._v(" "),i("td",{staticClass:"w150"},[t._v("审查资质")]),t._v(" "),i("td",{staticStyle:{width:"250px"}},[t._v(t._s(t.info.LEGALMANDUTY))]),t._v(" "),i("td",{staticClass:"w150"},[t._v("审查机构联系方式")]),t._v(" "),i("td",{staticStyle:{width:"250px"}},[t._v(t._s(t.info.FIRSTBIRTHYEAR))])]),t._v(" "),i("tr",[i("td",{staticClass:"w150"},[t._v("建设单位")]),t._v(" "),i("td",{attrs:{colspan:"3"}},[t._v(t._s(t.info.COPRCMAN))]),t._v(" "),i("td",{staticClass:"w180"},[t._v("建设单位项目负责人")]),t._v(" "),i("td",{staticStyle:{width:"250px"}},[t._v(t._s(t.info.REGPRIN))])]),t._v(" "),i("tr",[i("td",{staticClass:"w150"},[t._v("设计单位")]),t._v(" "),i("td",{attrs:{colspan:"3"}},[t._v(t._s(t.info.TECHMAN))]),t._v(" "),i("td",{staticClass:"w180"},[t._v("设计单位项目负责人")]),t._v(" "),i("td",{staticStyle:{width:"250px"}},[t._v(t._s(t.info.TECHMANTECHTITLE))])]),t._v(" "),i("tr",[i("td",{staticClass:"w150"},[t._v("勘察单位")]),t._v(" "),i("td",{attrs:{colspan:"3"}}),t._v(" "),i("td",{staticClass:"w180"},[t._v("勘察单位项目负责人")]),t._v(" "),i("td",{staticStyle:{width:"250px"}})]),t._v(" "),i("tr",[i("td",{staticClass:"w150"},[t._v("工程地址")]),t._v(" "),i("td",{attrs:{colspan:"5"}},[t._v(t._s(t.info.ADDRESS))])])])]):t._e()])],1),t._v(" "),i("div",{staticClass:"block",attrs:{id:"f1"}},[i("div",{staticClass:"infoHeader",on:{click:function(s){return s.stopPropagation(),t.toggle(1)}}},[i("div",{staticClass:"content",class:{clear:!t.companyTitleList[1].isShow}},[i("div",{staticClass:"titleLeft"},[i("span",[t._v(t._s(t.companyTitleList[1].name))])]),t._v(" "),i("div",{staticClass:"titleRight"},[i("span",[t._v(t._s(t.companyTitleList[1].title))]),t._v(" "),i("div",{staticClass:"box"})])])]),t._v(" "),i("div",{staticClass:"buzhou"},[i("el-steps",{attrs:{active:"2","finish-status":"finish"}},[i("el-step",{attrs:{title:"已报审"}}),t._v(" "),i("el-step",{attrs:{title:"已受理"}}),t._v(" "),i("el-step",{attrs:{title:"已初审"}}),t._v(" "),i("el-step",{attrs:{title:"已复审"}}),t._v(" "),i("el-step",{attrs:{title:"已完成","finish-status":"error"}})],1)],1),t._v(" "),i("div",{staticClass:"buzhou"},[i("el-steps",{attrs:{active:"2","finish-status":"finish"}},[i("el-step",{attrs:{title:"已报审"}}),t._v(" "),i("el-step",{attrs:{title:"已受理"}}),t._v(" "),i("el-step",{attrs:{title:"已初审"}}),t._v(" "),i("el-step",{attrs:{title:"已复审"}}),t._v(" "),i("el-step",{attrs:{title:"已完成","finish-status":"error"}})],1)],1),t._v(" "),i("div",{staticClass:"buzhou"},[i("el-steps",{attrs:{active:"2","finish-status":"finish"}},[i("el-step",{attrs:{title:"已报审"}}),t._v(" "),i("el-step",{attrs:{title:"已受理"}}),t._v(" "),i("el-step",{attrs:{title:"已初审"}}),t._v(" "),i("el-step",{attrs:{title:"已复审"}}),t._v(" "),i("el-step",{attrs:{title:"已完成","finish-status":"error"}})],1)],1),t._v(" "),i("div",{staticClass:"buzhou"},[i("el-steps",{attrs:{active:"2","finish-status":"finish"}},[i("el-step",{attrs:{title:"已报审"}}),t._v(" "),i("el-step",{attrs:{title:"已受理"}}),t._v(" "),i("el-step",{attrs:{title:"已初审"}}),t._v(" "),i("el-step",{attrs:{title:"已复审"}}),t._v(" "),i("el-step",{attrs:{title:"已完成","finish-status":"error"}})],1)],1)])])])])],1)},staticRenderFns:[]};var y=i("VU/8")(h,m,!1,function(t){i("Tmjx"),i("3HPh")},"data-v-19b58555",null);s.default=y.exports}});