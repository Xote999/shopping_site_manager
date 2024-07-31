package com.example.common.response;

import com.example.common.constants.HttpStatus;
import cn.hutool.core.util.ObjectUtil;

import java.util.HashMap;
import java.util.Objects;

/**
 * 統一されたレスポンスクラス
 */
public class SsmSysUserResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * ステータスコード
     */
    public static final String CODE_TAG = "code";

    /**
     * メッセージ
     */
    public static final String MSG_TAG = "msg";

    /**
     * データオブジェクト
     */
    public static final String DATA_TAG = "data";

    /**
     * 空のメッセージを持つ新しい AjaxResult オブジェクトを初期化します。
     */
    public SsmSysUserResult() {}

    /**
     * 新しい AjaxResult オブジェクトを初期化します
     *
     * @param code ステータスコード
     * @param msg  メッセージ
     */
    public SsmSysUserResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 新しい AjaxResult オブジェクトを初期化します
     *
     * @param code ステータスコード
     * @param msg  メッセージ
     * @param data データオブジェクト
     */
    public SsmSysUserResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (ObjectUtil.isNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 成功メッセージを返します
     *
     * @return 成功メッセージ
     */
    public static SsmSysUserResult success() {
        return SsmSysUserResult.success("操作成功");
    }

    /**
     * 成功データを返します
     *
     * @return 成功メッセージ
     */
    public static SsmSysUserResult success(Object data) {
        return SsmSysUserResult.success("操作成功", data);
    }

    /**
     * 成功メッセージを返します
     *
     * @param msg メッセージ
     * @return 成功メッセージ
     */
    public static SsmSysUserResult success(String msg) {
        return SsmSysUserResult.success(msg, null);
    }

    /**
     * 成功メッセージを返します
     *
     * @param msg  メッセージ
     * @param data データオブジェクト
     * @return 成功メッセージ
     */
    public static SsmSysUserResult success(String msg, Object data) {
        return new SsmSysUserResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 警告メッセージを返します
     *
     * @param msg メッセージ
     * @return 警告メッセージ
     */
    public static SsmSysUserResult warn(String msg) {
        return SsmSysUserResult.warn(msg, null);
    }

    /**
     * 警告メッセージを返します
     *
     * @param msg  メッセージ
     * @param data データオブジェクト
     * @return 警告メッセージ
     */
    public static SsmSysUserResult warn(String msg, Object data) {
        return new SsmSysUserResult(HttpStatus.WARN, msg, data);
    }

    /**
     * エラーメッセージを返します
     *
     * @return エラーメッセージ
     */
    public static SsmSysUserResult error() {
        return SsmSysUserResult.error("操作失敗");
    }

    /**
     * エラーメッセージを返します
     *
     * @param msg メッセージ
     * @return エラーメッセージ
     */
    public static SsmSysUserResult error(String msg) {
        return SsmSysUserResult.error(msg, null);
    }

    /**
     * エラーメッセージを返します
     *
     * @param msg  メッセージ
     * @param data データオブジェクト
     * @return エラーメッセージ
     */
    public static SsmSysUserResult error(String msg, Object data) {
        return new SsmSysUserResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * エラーメッセージを返します
     *
     * @param code ステータスコード
     * @param msg  メッセージ
     * @return エラーメッセージ
     */
    public static SsmSysUserResult error(int code, String msg) {
        return new SsmSysUserResult(code, msg, null);
    }

    /**
     * 成功メッセージかどうかを確認します
     *
     * @return 結果
     */
    public boolean isSuccess() {
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * 警告メッセージかどうかを確認します
     *
     * @return 結果
     */
    public boolean isWarn() {
        return Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * エラーメッセージかどうかを確認します
     *
     * @return 結果
     */
    public boolean isError() {
        return Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }

    /**
     * チェーンメソッド呼び出しのために便利です
     *
     * @param key   キー
     * @param value 値
     * @return データオブジェクト
     */
    @Override
    public SsmSysUserResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
