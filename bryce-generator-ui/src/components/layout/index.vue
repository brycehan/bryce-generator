<template>
  <el-container class="layout-container">
    <!-- 头部区域 -->
    <el-header class="layout-header">
      <Header />
    </el-header>
    <el-container>
      <!-- 侧栏区域 -->
      <el-aside class="layout-aside" width="200px">
        <Aside />
      </el-aside>
      <!-- 主区域 -->
      <el-main class="layout-main">
        <Main />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import Header from '@/components/layout/header/index.vue'
import Aside from '@/components/layout/aside/index.vue'
import Main from '@/components/layout/main/index.vue'
</script>

<style lang="scss">
.layout-container {
  width: 100%;
  height: 100%;

  .layout-header {
    display: flex;
    padding: 0;
    height: var(--theme-header-height);
  }

  .layout-aside {
    display: flex;
  }

  .layout-main {
    --el-main-padding: 0px !important;
    background-color: var(--theme-main-bg-color);
    overflow: hidden;
    width: 100%;

    .main-container {
      height: calc(100vh - 30px - var(--theme-header-height));
      padding: 15px;
    }
  }
}
</style>
