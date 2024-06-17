import{d as $,r as C,a as x,b as u,o as N,c as h,w as t,e,f as j,m as q,E as U,q as E,s as O,t as D,v as I,x as K}from"./index-Dqq0dEST.js";import{r as S}from"./request-a1Er5x_U.js";import{d as T}from"./download-Bo0_qoN9.js";const M=m=>m.id?S.put("/gen/projectModify",m):S.post("/gen/projectModify",m),A=m=>S.delete("/gen/projectModify",{data:{ids:m}}),z=m=>S.get(`/gen/projectModify/${m}`),G=m=>S.post("/gen/projectModify/page",m),H=m=>{T.get(`/gen/projectModify/download/${m}`)},J=$({__name:"add-or-edit",emits:["refreshPage"],setup(m,{expose:r,emit:y}){const b=y,l=C(!1),V=C(),p=x({id:"",projectName:"",projectCode:"",projectPackage:"",projectPath:""}),w=x({projectName:[{required:!0,message:"必填项不能为空",trigger:"blur"}],projectCode:[{required:!0,message:"必填项不能为空",trigger:"blur"}],projectPackage:[{required:!0,message:"必填项不能为空",trigger:"blur"}],projectPath:[{required:!0,message:"必填项不能为空",trigger:"blur"}]}),k=n=>{l.value=!0,V.value&&V.value.resetFields(),n&&g(n)},g=n=>{z(n).then(o=>{Object.assign(p,o.data)})},a=()=>{V.value.validate(n=>{if(!n)return!1;M(p).then(()=>{l.value=!1,b("refreshPage"),U.success("操作成功")})})};return r({init:k}),(n,o)=>{const i=u("el-input"),c=u("el-form-item"),f=u("el-form"),P=u("el-button"),_=u("el-dialog");return N(),h(_,{modelValue:l.value,"onUpdate:modelValue":o[7]||(o[7]=s=>l.value=s),title:p.id?"修改":"新增","close-on-click-modal":!1},{footer:t(()=>[e(P,{onClick:o[5]||(o[5]=s=>l.value=!1)},{default:t(()=>[j("取消")]),_:1}),e(P,{type:"primary",onClick:o[6]||(o[6]=s=>a())},{default:t(()=>[j("确定")]),_:1})]),default:t(()=>[e(f,{ref_key:"dataFormRef",ref:V,model:p,rules:w,"label-width":"100px",onKeyup:o[4]||(o[4]=q(s=>a(),["enter"]))},{default:t(()=>[e(c,{label:"项目名",prop:"projectName"},{default:t(()=>[e(i,{modelValue:p.projectName,"onUpdate:modelValue":o[0]||(o[0]=s=>p.projectName=s),placeholder:"项目名"},null,8,["modelValue"])]),_:1}),e(c,{label:"项目标识",prop:"projectCode"},{default:t(()=>[e(i,{modelValue:p.projectCode,"onUpdate:modelValue":o[1]||(o[1]=s=>p.projectCode=s),placeholder:"项目标识"},null,8,["modelValue"])]),_:1}),e(c,{label:"项目包名",prop:"projectPackage"},{default:t(()=>[e(i,{modelValue:p.projectPackage,"onUpdate:modelValue":o[2]||(o[2]=s=>p.projectPackage=s),placeholder:"项目包名"},null,8,["modelValue"])]),_:1}),e(c,{label:"项目路径",prop:"projectPath"},{default:t(()=>[e(i,{modelValue:p.projectPath,"onUpdate:modelValue":o[3]||(o[3]=s=>p.projectPath=s),placeholder:"项目路径"},null,8,["modelValue"])]),_:1})]),_:1},8,["model","rules"])]),_:1},8,["modelValue","title"])}}}),L=$({__name:"download",setup(m,{expose:r}){const y=C(!1),b=C(),l=x({id:"",projectName:"",projectPath:"",projectCode:"",projectPackage:"",modifyProjectName:"",modifyProjectCode:"",modifyProjectPackage:"",exclusions:1,modifySuffix:""}),V=x({modifyProjectName:[{required:!0,message:"必填项不能为空",trigger:"blur"}],modifyProjectCode:[{required:!0,message:"必填项不能为空",trigger:"blur"}],modifyProjectPackage:[{required:!0,message:"必填项不能为空",trigger:"blur"}],exclusions:[{required:!0,message:"必填项不能为空",trigger:"blur"}],modifySuffix:[{required:!0,message:"必填项不能为空",trigger:"blur"}]}),p=g=>{y.value=!0,l.id="",b.value&&b.value.resetFields(),w(g)},w=g=>{z(g).then(a=>{Object.assign(l,a.data)})},k=()=>{b.value.validate(async g=>{if(!g)return!1;await M(l),H(l.id),y.value=!1})};return r({init:p}),(g,a)=>{const n=u("el-input"),o=u("el-form-item"),i=u("el-col"),c=u("el-row"),f=u("el-divider"),P=u("el-form"),_=u("el-button"),s=u("el-dialog");return N(),h(s,{modelValue:y.value,"onUpdate:modelValue":a[12]||(a[12]=d=>y.value=d),title:"源码下载","close-on-click-modal":!1},{footer:t(()=>[e(_,{onClick:a[10]||(a[10]=d=>y.value=!1)},{default:t(()=>[j("取消")]),_:1}),e(_,{type:"primary",onClick:a[11]||(a[11]=d=>k())},{default:t(()=>[j("下载")]),_:1})]),default:t(()=>[e(P,{ref_key:"dataFormRef",ref:b,model:l,rules:V,"label-width":"100px",onKeyup:a[9]||(a[9]=q(d=>k(),["enter"]))},{default:t(()=>[e(c,null,{default:t(()=>[e(i,{span:12},{default:t(()=>[e(o,{label:"项目名",prop:"projectName"},{default:t(()=>[e(n,{modelValue:l.projectName,"onUpdate:modelValue":a[0]||(a[0]=d=>l.projectName=d),disabled:"",placeholder:"项目名"},null,8,["modelValue"])]),_:1})]),_:1}),e(i,{span:12},{default:t(()=>[e(o,{label:"项目路径",prop:"projectPath"},{default:t(()=>[e(n,{modelValue:l.projectPath,"onUpdate:modelValue":a[1]||(a[1]=d=>l.projectPath=d),disabled:"",placeholder:"项目路径"},null,8,["modelValue"])]),_:1})]),_:1})]),_:1}),e(c,null,{default:t(()=>[e(i,{span:12},{default:t(()=>[e(o,{label:"项目标识",prop:"projectCode"},{default:t(()=>[e(n,{modelValue:l.projectCode,"onUpdate:modelValue":a[2]||(a[2]=d=>l.projectCode=d),disabled:"",placeholder:"项目标识"},null,8,["modelValue"])]),_:1})]),_:1}),e(i,{span:12},{default:t(()=>[e(o,{label:"项目包名",prop:"projectPackage"},{default:t(()=>[e(n,{modelValue:l.projectPackage,"onUpdate:modelValue":a[3]||(a[3]=d=>l.projectPackage=d),disabled:"",placeholder:"项目包名"},null,8,["modelValue"])]),_:1})]),_:1})]),_:1}),e(f,null,{default:t(()=>[j("变更后的信息")]),_:1}),e(o,{label:"项目名",prop:"modifyProjectName"},{default:t(()=>[e(n,{modelValue:l.modifyProjectName,"onUpdate:modelValue":a[4]||(a[4]=d=>l.modifyProjectName=d),placeholder:"项目名"},null,8,["modelValue"])]),_:1}),e(c,null,{default:t(()=>[e(i,{span:12},{default:t(()=>[e(o,{label:"项目标识",prop:"modifyProjectCode"},{default:t(()=>[e(n,{modelValue:l.modifyProjectCode,"onUpdate:modelValue":a[5]||(a[5]=d=>l.modifyProjectCode=d),placeholder:"项目标识"},null,8,["modelValue"])]),_:1})]),_:1}),e(i,{span:12},{default:t(()=>[e(o,{label:"项目包名",prop:"modifyProjectPackage"},{default:t(()=>[e(n,{modelValue:l.modifyProjectPackage,"onUpdate:modelValue":a[6]||(a[6]=d=>l.modifyProjectPackage=d),placeholder:"项目包名"},null,8,["modelValue"])]),_:1})]),_:1})]),_:1}),e(c,null,{default:t(()=>[e(i,{span:12},{default:t(()=>[e(o,{label:"排除文件",prop:"exclusions"},{default:t(()=>[e(n,{modelValue:l.exclusions,"onUpdate:modelValue":a[7]||(a[7]=d=>l.exclusions=d),placeholder:"排除文件"},null,8,["modelValue"])]),_:1})]),_:1}),e(i,{span:12},{default:t(()=>[e(o,{label:"变更文件",prop:"modifySuffix"},{default:t(()=>[e(n,{modelValue:l.modifySuffix,"onUpdate:modelValue":a[8]||(a[8]=d=>l.modifySuffix=d),placeholder:"变更文件"},null,8,["modelValue"])]),_:1})]),_:1})]),_:1})]),_:1},8,["model","rules"])]),_:1},8,["modelValue"])}}}),Y=$({__name:"index",setup(m){const r=x({queryForm:{projectName:""},page:{current:1,pageSize:10,total:0,orderItems:[{column:"createTime",asc:!1}]},data:[],dataSelections:[],loading:!1}),y=C(),b=C();E(()=>{l()});const l=(n=1,o)=>{r.page.current=n,o!==void 0&&(r.page.pageSize=o),r.loading=!0;const i={current:r.page.current,pageSize:r.page.pageSize,orderItems:r.page.orderItems,...r.queryForm};G(i).then(c=>{const{list:f,total:P}=c.data;r.data=f,r.page.total=P}),r.loading=!1},V=n=>{l(1,n)},p=n=>{l(n)},w=n=>{b.value.init(n)},k=n=>{y.value.init(n)},g=n=>{let o=[];if(n)o=[n];else if(o=r.dataSelections?r.dataSelections:[],o.length===0){U.warning("请选择删除的记录");return}K.confirm("确定进行删除操作？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{A(o).then(()=>{U.success("删除成功"),l()})}).catch(i=>{console.error(i)})},a=n=>{r.dataSelections=n.map(o=>o.id)};return(n,o)=>{const i=u("el-input"),c=u("el-form-item"),f=u("el-button"),P=u("el-form"),_=u("el-table-column"),s=u("el-table"),d=u("el-pagination"),R=u("el-card"),B=O("loading");return N(),h(R,{shadow:"hover"},{default:t(()=>[e(P,{inline:!0,model:r.queryForm,onKeyup:o[4]||(o[4]=q(v=>l(),["enter"])),onSubmit:o[5]||(o[5]=D(()=>{},["prevent"]))},{default:t(()=>[e(c,null,{default:t(()=>[e(i,{modelValue:r.queryForm.projectName,"onUpdate:modelValue":o[0]||(o[0]=v=>r.queryForm.projectName=v),placeholder:"项目名"},null,8,["modelValue"])]),_:1}),e(c,null,{default:t(()=>[e(f,{onClick:o[1]||(o[1]=v=>l())},{default:t(()=>[j("查询")]),_:1}),e(f,{type:"primary",onClick:o[2]||(o[2]=v=>k())},{default:t(()=>[j("新增")]),_:1}),e(f,{type:"danger",onClick:o[3]||(o[3]=v=>g())},{default:t(()=>[j("删除")]),_:1})]),_:1})]),_:1},8,["model"]),I((N(),h(s,{data:r.data,border:!0,style:{width:"100%"},onSelectionChange:a},{default:t(()=>[e(_,{type:"selection","header-align":"center",align:"center",width:"50"}),e(_,{label:"项目名",prop:"projectName","header-align":"center",align:"center"}),e(_,{label:"项目标识",prop:"projectCode","header-align":"center",align:"center"}),e(_,{label:"项目包名",prop:"projectPackage","show-overflow-tooltip":"","header-align":"center",align:"center"}),e(_,{label:"项目路径",prop:"projectPath","show-overflow-tooltip":"","header-align":"center",align:"center"}),e(_,{label:"操作",fixed:"right","header-align":"center",align:"center",width:"180"},{default:t(v=>[e(f,{type:"success",link:"",onClick:F=>w(v.row.id)},{default:t(()=>[j("源码下载")]),_:2},1032,["onClick"]),e(f,{type:"primary",link:"",onClick:F=>k(v.row.id)},{default:t(()=>[j("编辑")]),_:2},1032,["onClick"]),e(f,{type:"danger",link:"",onClick:F=>g(v.row.id)},{default:t(()=>[j("删除")]),_:2},1032,["onClick"])]),_:1})]),_:1},8,["data"])),[[B,r.loading]]),e(d,{"current-page":r.page.current,"page-size":r.page.pageSize,"page-sizes":[10,20,50,100,200],total:r.page.total,layout:"total,sizes,prev,pager,next,jumper",onSizeChange:V,onCurrentChange:p},null,8,["current-page","page-size","total"]),e(J,{ref_key:"addOrEditRef",ref:y,onRefreshPage:l},null,512),e(L,{ref_key:"downloadRef",ref:b},null,512)]),_:1})}}});export{Y as default};