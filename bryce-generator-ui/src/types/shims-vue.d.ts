declare module '*.svg'
declare module '*.scss'
declare module '*.ts'
declare module '*.js'
declare module '*.mjs'

declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}
