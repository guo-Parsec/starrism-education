package nom.edu.starrism.common.support;

/**
 * <p>An immutable Quatra consisting of four Object elements.</p>
 *
 * @author guocq
 * @since 2022/11/24
 **/
public final class ImmutableQuatra<T, R, B, L> extends Quatra<T, R, B, L> {
    private static final long serialVersionUID = 7941353610975923167L;

    public static final ImmutableQuatra<?, ?, ?, ?>[] EMPTY_ARRAY = new ImmutableQuatra[0];

    @SuppressWarnings("rawtypes")
    private static final ImmutableQuatra NULL = of(null, null, null, null);

    public static <T, R, B, L> ImmutableQuatra<T, R, B, L> nullQuatra() {
        return NULL;
    }

    @SuppressWarnings("unchecked")
    public static <T, R, B, L> ImmutableQuatra<T, R, B, L>[] emptyArray() {
        return (ImmutableQuatra<T, R, B, L>[]) EMPTY_ARRAY;
    }

    /**
     * <p>Obtains an immutable Quatra of three objects inferring the generic types.</p>
     *
     * <p>This factory allows the triple to be created using inference to
     * obtain the generic types.</p>
     *
     * @param <T>    the top element type
     * @param <R>    the right element type
     * @param <B>    the bottom element type
     * @param <L>    the left element type
     * @param top    the top element, may be null
     * @param right  the right element, may be null
     * @param bottom the bottom element, may be null
     * @param left   the left element, may be null
     * @return a triple formed from the three parameters, not null
     */
    public static <T, R, B, L> ImmutableQuatra<T, R, B, L> of(final T top, final R right, final B bottom, final L left) {
        return new ImmutableQuatra<>(top, right, bottom, left);
    }

    /**
     * Top object
     */
    public final T top;
    /**
     * Right object
     */
    public final R right;
    /**
     * Bottom object
     */
    public final B bottom;
    /**
     * Left object
     */
    public final L left;


    public ImmutableQuatra(final T top, final R right, final B bottom, final L left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    /**
     * <p>Gets the top element from this quatra</p>
     *
     * @return the top element, may be null
     * @author guocq
     * @date 2022/11/24 17:41
     */
    @Override
    public T getTop() {
        return this.top;
    }

    /**
     * <p>Gets the right element from this quatra</p>
     *
     * @return the right element, may be null
     * @author guocq
     * @date 2022/11/24 17:41
     */
    @Override
    public R getRight() {
        return this.right;
    }

    /**
     * <p>Gets the bottom element from this quatra</p>
     *
     * @return the bottom element, may be null
     * @author guocq
     * @date 2022/11/24 17:41
     */
    @Override
    public B getBottom() {
        return this.bottom;
    }

    /**
     * <p>Gets the left element from this quatra</p>
     *
     * @return the left element, may be null
     * @author guocq
     * @date 2022/11/24 17:41
     */
    @Override
    public L getLeft() {
        return this.left;
    }
}
