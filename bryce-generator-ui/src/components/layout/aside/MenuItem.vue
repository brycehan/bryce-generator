<template>
  <el-sub-menu v-if="menu.children?.length > 0" :key="menu.path" :index="menu.path">
    <template #title>
      <el-icon>
        <component :is="menu.meta.icon" class="el-icon-content"></component>
      </el-icon>
      <span>{{ menu.meta.title }}</span>
    </template>
    <MenuItem v-for="item in menu.children" :key="item.path" :menu="item" />
  </el-sub-menu>
  <el-menu-item
    v-if="menu.children === undefined"
    :key="menu.path"
    :index="menu.path"
    @click="handleClick(menu)"
  >
    <el-icon>
      <component :is="menu.meta?.icon"></component>
    </el-icon>
    <template #title>
      {{ menu.meta?.title }}
    </template>
  </el-menu-item>
</template>

<script setup lang="ts">
import type { PropType } from 'vue'
import { useRouter } from 'vue-router'

defineProps({
  menu: {
    type: Object as PropType<any>,
    required: true
  }
})

const router = useRouter()

/**
 * 菜单点击事件
 *
 * @param menu 菜单
 */
const handleClick = (menu: any) => {
  router.push(menu.path)
  return
}
</script>

<script lang="ts">
export default {
  name: 'MenuItem'
}
</script>

<style scoped lang="scss">
.el-sub-menu {
  ::v-deep(.el-sub-menu__title) {
    height: 48px !important;
  }
}
.el-menu-item {
  height: 45px !important;
  line-height: 45px !important;
}
.el-menu-item.is-active {
  border-right: 2px solid;
}
</style>
