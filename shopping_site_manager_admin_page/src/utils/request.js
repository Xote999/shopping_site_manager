// axiosをインポート
import axios from 'axios'
// routerをインポート
import router from '@/router/index.js'

import { ElMessage } from 'element-plus'

// tokenをインポート
import { getToken } from './token/index.js'

// axiosの作成
const httpClient = axios.create({
    // ルートリクエストアドレス
    baseURL:'http://localhost:8081/',
    // クロスオリジンの解決
    withCredentials:false,
    // タイムアウト時間、単位はミリ秒
    timeout:30000
})

// リクエストヘッダーのパラメータタイプを設定
axios.defaults.headers['Content-Type'] = 'application/json?charset=utf-8'

// リクエストのインターセプターを設定
httpClient.interceptors.request.use((config) =>{

    // リクエストヘッダーにトークンを追加し、トークンが必要かどうかを判断
    if(getToken("ssmSysToken")){
        config.headers['ssmSys-Authorization'] = getToken("ssmSysToken");
    }
    return config;
    },

    (error) =>{
        // 異常が発生
        console.log("リクエスト異常====>",error);
        return Promise.reject(error);
    }
)

// レスポンスインターセプターを設定
httpClient.interceptors.response.use((response) =>{
    // レスポンスコードを判断し、バックエンドから返されたデータを確認
    let {msg,code} = response.data;
    console.log("code====>",code,"msg====>",msg);
    if(code == null){
        return response;
    }
    else if(code == 200){
        return response;
    }
    else if(code == 500){
        ElMessage.error("サーバーエラー!");
    }
    else if(code == 401){
        ElMessage.error("操作権限がありません!");
    }
    else if(code == 403){
        ElMessage.error("ログインが期限切れです!");
        // ログインページにリダイレクトし、piniaとsessionStorageのデータをクリア
        window.sessionStorage.clear();
        router.push('/login');
    }
    return Promise.reject(msg);
    },

    (error) =>{
        // 異常が発生
        ElMessage.error("error====>",error);
        window.sessionStorage.clear();
        router.push('/login');
        return Promise.reject(error);
    }
)

// エクスポート
export default httpClient;