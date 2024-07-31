package com.example.common.constants;

/**
 * ステータスコードを保存するインターフェース
 */
public interface HttpStatus {
    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * オブジェクトの作成成功
     */
    public static final int CREATED = 201;

    /**
     * リクエストが受け入れられた
     */
    public static final int ACCEPTED = 202;

    /**
     * 操作は成功しましたが、データは返されなかった
     */
    public static final int NO_CONTENT = 204;

    /**
     * リソースが移動されました
     */
    public static final int MOVED_PERM = 301;

    /**
     * リダイレクト
     */
    public static final int SEE_OTHER = 303;

    /**
     * リソースが変更されていない
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * パラメータリストが間違っている（欠落、形式不一致）
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 未認証
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * アクセス制限、認証期限切れ
     */
    public static final int FORBIDDEN = 403;

    /**
     * リソース、サービスが見つからない
     */
    public static final int NOT_FOUND = 404;

    /**
     * 許可されていないHTTPメソッド
     */
    public static final int BAD_METHOD = 405;

    /**
     * リソースの競合、またはリソースがロックされている
     */
    public static final int CONFLICT = 409;

    /**
     * サポートされていないデータ、メディアタイプ
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * システム内部エラー
     */
    public static final int ERROR = 500;

    /**
     * インターフェースが実装されていない
     */
    public static final int NOT_IMPLEMENTED = 501;

    /**
     * システム警告メッセージ
     */
    public static final int WARN = 601;
}
