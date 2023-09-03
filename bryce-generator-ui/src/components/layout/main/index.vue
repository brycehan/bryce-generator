<template>
  <el-scrollbar>
    <el-container class="main-container">
      <router-view v-slot="{ Component, route }">
        <component :is="Component" :key="route.name" />
      </router-view>
    </el-container>
  </el-scrollbar>
</template>

<style lang="scss">
.main-container {
  padding: 15px !important;
  flex-direction: column;

  .el-pagination {
    margin-top: 15px;
    justify-content: flex-end;
  }
}
</style>
