// リクエストインターフェース
import httpClient from '@/utils/request.js'

// ログインインターフェース
export function login(data){

    return httpClient({
        url: 'auth/sys',
        method: 'POST',
        data: data
    })
}