import { defineStore } from 'pinia'

export const useMenuStore = defineStore('menu',{
    //データの設定
    state:() =>({
        menuList:[]
    }),
    //データを取得する方法
    getters:{
        Array:(state) => state.menuList,
    },
    //データを修正する方法
    actions:{
        setMenuList(data){
            console.log("setMenuList ====>".data);
            this.menuList = data;
        }
    }
})