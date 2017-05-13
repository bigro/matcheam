package matcheam.common.exception;

/**
 * システム例外です。
 * <p>継続不可能な場合に使用します。</p>
 * @since 1.0
 */
public class SystemException extends RuntimeException {

    /**
     * コンストラクタです。
     * @param e 例外
     */
    public SystemException(Exception e) {
        super(e);
    }
}
