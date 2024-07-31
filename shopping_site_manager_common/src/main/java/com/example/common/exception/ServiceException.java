package com.example.common.exception;

/**
 * カスタム業務例外。システムでエラーが発生した場合に、この例外をフロントエンドに返します。
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * エラーコード
     */
    private Integer code;

    /**
     * エラーメッセージ
     */
    private String message;

    /**
     * エラー詳細、内部デバッグ用エラー
     */
    private String detailMessage;

    /**
     * デフォルトコンストラクタ。デシリアライズの問題を避けるため
     */
    public ServiceException() {
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }

}
