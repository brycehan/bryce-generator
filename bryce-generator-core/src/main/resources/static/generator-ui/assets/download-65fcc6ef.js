import{r as l}from"./request-b68d8899.js";import{E as c}from"./index-6216958c.js";const d=o=>{l.request({...o,responseType:"blob"}).then(e=>{const r=new Blob([e.data]),n=new FileReader;toString.call(e.data)==="[object Blob]"?(n.readAsDataURL(r),n.onload=function(a){debugger;const t=document.createElement("a"),s=decodeURI(e.headers["content-disposition"]);t.download=s.split("''")[1],t.href=a.target.result,document.body.insertAdjacentElement("afterend",t),t.click(),t.remove()}):(n.readAsText(r),n.onload=function(a){c.error("下载文件出现错误，请联系管理员！"),console.error("generator.download",JSON.parse(a.target.result))})})},i=(o,e)=>d({url:o,method:"GET",...e}),m=(o,e,r)=>d({url:o,method:"POST",data:e,...r}),f={get:i,post:m};export{f as d};