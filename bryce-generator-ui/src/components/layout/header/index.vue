<template>
  <el-container class="header-container">
    <div class="header-logo">
      <el-avatar src="./favicon.ico"></el-avatar>
      <span class="logo-title">Bryce Generator</span>
    </div>
    <div class="header-content">代码生成器工作台</div>
  </el-container>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
.header-container {
  background-color: var(--theme-header-bg-color);
  color: var(--theme-header-text-color);
  align-items: center;
}
.header-logo {
  width: var(--theme-menu-width);
  display: flex;
  align-items: center;
  justify-content: center;

  .el-avatar {
    width: 25px;
    height: 25px;
  }
  .logo-title {
    font-size: var(--theme-logo-text-fontsize);
    margin-left: 10px;
  }
}
</style>
