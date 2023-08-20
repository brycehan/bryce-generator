<template>
  <el-scrollbar>
    <div class="main-container">
        <router-view v-slot="{ Component, route }">
          <component :is="Component" :key="route.name" />
        </router-view>
    </div>
  </el-scrollbar>
</template>

<script setup lang="ts"></script>

<style lang="scss">

.el-pagination {
  margin-top: 15px;
  justify-content: flex-end;
}

</style>
