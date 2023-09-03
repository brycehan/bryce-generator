<template>
  <el-drawer v-model="visible" :size="1305" title="代码预览" class="drawer-preview">
    <el-tabs v-model="activeName">
      <el-tab-pane
          v-for="(value, key) in state.data"
          :label="String(key).substring(String(key).lastIndexOf('/') + 1, String(key).indexOf('.ftl'))"
          :name="String(key).substring(String(key).lastIndexOf('/') + 1, String(key).indexOf('.ftl'))"
          :key="key">
        <el-link :underline="false" :icon="DocumentCopy" @click="handleCopy(value)" class="link-copy">复制</el-link>
        <pre><code class="hljs" v-html="handleHighlightedCode(value, String(key))"></code></pre>
      </el-tab-pane>
    </el-tabs>
  </el-drawer>
</template>

<script setup lang="ts">
import {reactive, ref} from 'vue'
import { preview } from '@/api/generator'
import {ElMessage} from 'element-plus'
import { useClipboard} from "@vueuse/core";
import hljs from 'highlight.js/lib/core';
import xml from 'highlight.js/lib/languages/xml';
import javascript from 'highlight.js/lib/languages/javascript';
import ts from 'highlight.js/lib/languages/typescript';
import java from 'highlight.js/lib/languages/java';
import sql from 'highlight.js/lib/languages/sql';
import 'highlight.js/styles/github.css'
import {DocumentCopy} from "@element-plus/icons-vue";
hljs.registerLanguage('xml', xml);
hljs.registerLanguage('html', xml);
hljs.registerLanguage('vue', xml);
hljs.registerLanguage('javascript', javascript);
hljs.registerLanguage('ts', ts);
hljs.registerLanguage('java', java);
hljs.registerLanguage('sql', sql);

const activeName = ref()

const visible = ref(false)

const state = reactive({
  tableId: '',
  data: [],
})

const { copy } = useClipboard()

const init = (id: string) => {
  visible.value = true
  state.tableId = id

  activeName.value = 'entity.java'

  getTable(id)
}

const getTable = (id: string) => {
  preview(id).then((res) => {
    state.data = res.data
  })
}

/** 高亮显示代码 */
const handleHighlightedCode = (code: string, key: string) => {
  const templateName = key.substring(key.lastIndexOf('/') + 1, key.indexOf('.ftl'));
  const language = templateName.substring(templateName.indexOf('.') + 1)
  const result = hljs.highlight(code || '', {language, ignoreIllegals: true })
  return result.value || '$nbsp;'
}

const handleCopy = (code: string) => {
  copy(code)
  ElMessage.success('已复制')
}

defineExpose({
  init
})
</script>

<style lang="scss">
.drawer-preview {
  .el-tabs__item {
    padding: 0 5px;
  }
  .link-copy {
    display: flex;
    justify-content: flex-end;
  }

  .el-drawer__header {
    margin-bottom: 0 !important;
    color: unset !important;
    font-weight: 600 !important;
  }

  .el-drawer__body {
    padding-top: 0 !important;
  }
}
</style>