<template>
  <div class="aside-content">
    <el-scrollbar>
      <el-menu :default-active="route.path" :collapse-transition="true" mode="vertical">
        <MenuItem v-for="menu in menuRoutes" :key="menu.path" :menu="menu" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router'
import MenuItem from '@/components/layout/aside/MenuItem.vue'
import { menuRoutes } from '@/router'

const route = useRoute()
</script>

<style lang="scss">
.aside-content {
  width: 100%;

  .el-menu {
    border-right: none !important;
    --el-menu-text-color: var(--theme-menu-text-color);
    --el-menu-active-color: var(--theme-menu-hover-color);
  }

  .el-menu-item.is-active {
    background-color: var(--theme-menu-hover-bg-color) !important;
  }

  .el-sub-menu.is-active {
    .el-sub-menu__title {
      color: var(--theme-menu-hover-color);
    }
  }

  .el-sub-menu__title:hover,
  .el-menu-item:hover {
    background-color: unset;
    color: var(--theme-menu-hover-color);
  }

  .el-icon svg{
    font-size: 16px;
  }
}
</style>
