import{d as $,r as w,a as V,b as s,o as S,c as x,w as o,e,f as _,m as q,E as z,q as U,s as E,t as I,v as O,x as R}from"./index-Dqq0dEST.js";import{g as M,s as P,p as T,d as D}from"./baseClass-DTepS_It.js";import"./request-a1Er5x_U.js";const K=$({__name:"add-or-edit",emits:["refreshPage"],setup(B,{expose:l,emit:b}){const p=b,f=w(!1),v=w(),n=V({id:"",code:"",packageName:"",fields:"",remark:""}),k=V({code:[{required:!0,message:"必填项不能为空",trigger:"blur"}],packageName:[{required:!0,message:"必填项不能为空",trigger:"blur"}],fields:[{required:!0,message:"必填项不能为空",trigger:"blur"}]}),h=i=>{f.value=!0,n.id="",v.value&&v.value.resetFields(),i&&r(i)},r=i=>{M(i).then(a=>{Object.assign(n,a.data)})},t=()=>{v.value.validate(i=>{if(!i)return!1;P(n).then(()=>{f.value=!1,p("refreshPage"),z.success("操作成功")})})};return l({init:h}),(i,a)=>{const u=s("el-input"),m=s("el-form-item"),c=s("el-form"),y=s("el-button"),C=s("el-dialog");return S(),x(C,{modelValue:f.value,"onUpdate:modelValue":a[7]||(a[7]=d=>f.value=d),title:n.id?"修改":"新增","close-on-click-modal":!1},{footer:o(()=>[e(y,{onClick:a[5]||(a[5]=d=>f.value=!1)},{default:o(()=>[_("取消")]),_:1}),e(y,{type:"primary",onClick:a[6]||(a[6]=d=>t())},{default:o(()=>[_("确定")]),_:1})]),default:o(()=>[e(c,{ref_key:"dataFormRef",ref:v,model:n,rules:k,"label-width":"120px",onKeyup:a[4]||(a[4]=q(d=>t(),["enter"]))},{default:o(()=>[e(m,{label:"基类编码",prop:"code"},{default:o(()=>[e(u,{modelValue:n.code,"onUpdate:modelValue":a[0]||(a[0]=d=>n.code=d),placeholder:"基类编码"},null,8,["modelValue"])]),_:1}),e(m,{label:"基类包名",prop:"packageName"},{default:o(()=>[e(u,{modelValue:n.packageName,"onUpdate:modelValue":a[1]||(a[1]=d=>n.packageName=d),placeholder:"基类包名"},null,8,["modelValue"])]),_:1}),e(m,{label:"基类字段",prop:"fields"},{default:o(()=>[e(u,{modelValue:n.fields,"onUpdate:modelValue":a[2]||(a[2]=d=>n.fields=d),placeholder:"基类字段（多个字段，用英文逗号分隔）"},null,8,["modelValue"])]),_:1}),e(m,{label:"备注",prop:"remark"},{default:o(()=>[e(u,{modelValue:n.remark,"onUpdate:modelValue":a[3]||(a[3]=d=>n.remark=d),placeholder:"备注"},null,8,["modelValue"])]),_:1})]),_:1},8,["model","rules"])]),_:1},8,["modelValue","title"])}}}),H=$({__name:"index",setup(B){const l=V({queryForm:{code:""},page:{current:1,pageSize:10,total:0,orderItems:[{column:"createTime",asc:!1}]},data:[],dataSelections:[],loading:!1}),b=w();U(()=>{p()});const p=(r=1,t)=>{l.page.current=r,t!==void 0&&(l.page.pageSize=t),l.loading=!0;const i={current:l.page.current,pageSize:l.page.pageSize,orderItems:l.page.orderItems,...l.queryForm};T(i).then(a=>{const{list:u,total:m}=a.data;l.data=u,l.page.total=m}),l.loading=!1},f=r=>{p(1,r)},v=r=>{p(r)},n=r=>{b.value.init(r)},k=r=>{let t=[];if(r)t=[r];else if(t=l.dataSelections?l.dataSelections:[],t.length===0){z.warning("请选择删除的记录");return}R.confirm("确定进行删除操作？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{D(t).then(()=>{z.success("删除成功"),p()})}).catch(i=>{console.error(i)})},h=r=>{l.dataSelections=r.map(t=>t.id)};return(r,t)=>{const i=s("el-input"),a=s("el-form-item"),u=s("el-button"),m=s("el-form"),c=s("el-table-column"),y=s("el-table"),C=s("el-pagination"),d=s("el-card"),F=E("loading");return S(),x(d,{shadow:"hover"},{default:o(()=>[e(m,{inline:!0,model:l.queryForm,onKeyup:t[4]||(t[4]=q(g=>p(),["enter"])),onSubmit:t[5]||(t[5]=I(()=>{},["prevent"]))},{default:o(()=>[e(a,null,{default:o(()=>[e(i,{modelValue:l.queryForm.code,"onUpdate:modelValue":t[0]||(t[0]=g=>l.queryForm.code=g),placeholder:"基类编码"},null,8,["modelValue"])]),_:1}),e(a,null,{default:o(()=>[e(u,{onClick:t[1]||(t[1]=g=>p())},{default:o(()=>[_("查询")]),_:1}),e(u,{type:"primary",onClick:t[2]||(t[2]=g=>n())},{default:o(()=>[_("新增")]),_:1}),e(u,{type:"danger",onClick:t[3]||(t[3]=g=>k())},{default:o(()=>[_("删除")]),_:1})]),_:1})]),_:1},8,["model"]),O((S(),x(y,{data:l.data,border:!0,style:{width:"100%"},onSelectionChange:h},{default:o(()=>[e(c,{type:"selection","header-align":"center",align:"center",width:"50"}),e(c,{label:"基类编码",prop:"code","header-align":"center",align:"center"}),e(c,{label:"基类包名",prop:"packageName","show-overflow-tooltip":"","header-align":"center",align:"center"}),e(c,{label:"基类字段",prop:"fields","show-overflow-tooltip":"","header-align":"center",align:"center"}),e(c,{label:"备注",prop:"remark","show-overflow-tooltip":"","header-align":"center",align:"center"}),e(c,{label:"操作",fixed:"right","header-align":"center",align:"center",width:"150"},{default:o(g=>[e(u,{type:"primary",link:"",onClick:N=>n(g.row.id)},{default:o(()=>[_("编辑")]),_:2},1032,["onClick"]),e(u,{type:"danger",link:"",onClick:N=>k(g.row.id)},{default:o(()=>[_("删除")]),_:2},1032,["onClick"])]),_:1})]),_:1},8,["data"])),[[F,l.loading]]),e(C,{"current-page":l.page.current,"page-size":l.page.pageSize,"page-sizes":[10,20,50,100,200],total:l.page.total,layout:"total,sizes,prev,pager,next,jumper",onSizeChange:f,onCurrentChange:v},null,8,["current-page","page-size","total"]),e(K,{ref_key:"addOrEditRef",ref:b,onRefreshPage:p},null,512)]),_:1})}}});export{H as default};
