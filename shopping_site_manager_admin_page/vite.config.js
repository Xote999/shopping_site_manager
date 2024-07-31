import { fileURLToPath, URL } from 'node:url'
import path from 'path'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

//element-plusのインポート
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

const pathSrc = path.resolve(__dirname, 'src')

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    //自動的にelement-plusをインポートする
    AutoImport({
      resolvers: [ElementPlusResolver()],
      dts: path.resolve(pathSrc, 'auto-imports.d.ts')
    }),
    //自動的にelement-plusを登録する
    Components({
      resolvers: [
        ElementPlusResolver(),
        //自動的にIconを登録する
        IconsResolver({
          enabledCollections:['ep', 'logos','pajamas']
        })
      ],
      dts: path.resolve(pathSrc, 'components.d.ts')
    }),
    //自動的にIconをインストールする
    Icons({
      autoInstall:true
    })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})