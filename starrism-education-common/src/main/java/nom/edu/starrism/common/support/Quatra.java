package nom.edu.starrism.common.support;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>四元组</p>
 *
 * @author guocq
 * @since 2022/11/24
 **/
public abstract class Quatra<T, R, B, L> implements Comparable<Quatra<T, R, B, L>>, Serializable {
    private static final long serialVersionUID = 5976268416198429931L;

    private static final class QuatraAdapter<T, R, B, L> extends Quatra<T, R, B, L> {

        private static final long serialVersionUID = -2168194526687153235L;

        @Override
        public T getTop() {
            return null;
        }

        @Override
        public R getRight() {
            return null;
        }

        @Override
        public B getBottom() {
            return null;
        }

        @Override
        public L getLeft() {
            return null;
        }
    }

    /**
     * An empty array.
     * <p>
     * Consider using {@link #emptyArray()} to avoid generics warnings.
     * </p>
     *
     */
    public static final Quatra<?, ?, ?, ?>[] EMPTY_ARRAY = new Quatra.QuatraAdapter[0];

    /**
     * Returns the empty array singleton that can be assigned without compiler warning.
     *
     * @param <T> the top element type
     * @param <R> the right element type
     * @param <B> the bottom element type
     * @param <L> the left element type
     * @return the empty array singleton that can be assigned without compiler warning.
     */
    @SuppressWarnings("unchecked")
    public static <T, R, B, L> Quatra<T, R, B, L>[] emptyArray() {
        return (Quatra<T, R, B, L>[]) EMPTY_ARRAY;
    }

    public static <T, R, B, L> Quatra<T, R, B, L> of(final T top, final R right, final B bottom, final L left) {
        return new ImmutableQuatra<>(top, right, bottom, left);
    }

    /**
     * <p>Compares the Quatra based on the top element, followed by the right element,followed by the bottom element,
     * finally the left element.
     * The types must be {@code Comparable}.</p>
     *
     * @param other the other Quatra, not null
     * @return negative if this is less, zero if equal, positive if greater
     */
    @Override
    public int compareTo(final Quatra<T, R, B, L> other) {
        return new CompareToBuilder().append(getTop(), other.getTop())
                .append(getRight(), other.getRight())
                .append(getBottom(), other.getBottom())
                .append(getLeft(), other.getLeft())
                .toComparison();
    }

    /**
     * <p>Compares this Quatra to another based on the three elements.</p>
     *
     * @param obj the object to compare to, null returns false
     * @return true if the elements of the Quatra are equal
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Quatra<?, ?, ?, ?>) {
            final Quatra<?, ?, ?, ?> other = (Quatra<?, ?, ?, ?>) obj;
            return Objects.equals(getLeft(), other.getLeft())
                    && Objects.equals(getTop(), other.getTop())
                    && Objects.equals(getBottom(), other.getBottom())
                    && Objects.equals(getRight(), other.getRight());
        }
        return false;
    }

    /**
     * <p>Gets the top element from this quatra</p>
     *
     * @return the top element, may be null
     * @author guocq
     * @date 2022/11/24 17:41
     */
    public abstract T getTop();

    /**
     * <p>Gets the right element from this quatra</p>
     *
     * @return the right element, may be null
     * @author guocq
     * @date 2022/11/24 17:41
     */
    public abstract R getRight();

    /**
     * <p>Gets the bottom element from this quatra</p>
     *
     * @return the bottom element, may be null
     * @author guocq
     * @date 2022/11/24 17:41
     */
    public abstract B getBottom();

    /**
     * <p>Gets the left element from this quatra</p>
     *
     * @return the left element, may be null
     * @author guocq
     * @date 2022/11/24 17:41
     */
    public abstract L getLeft();

    /**
     * <p>Returns a suitable hash code.</p>
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getTop()) ^ Objects.hashCode(getRight()) ^ Objects.hashCode(getBottom()) ^ Objects.hashCode(getLeft());
    }

    /**
     * <p>Returns a String representation of this quatra using the format {@code ($top,$right,$bottom,$left)}.</p>
     *
     * @return a string describing this object, not null
     */
    @Override
    public String toString() {
        return "(" + getTop() + "," + getRight() + "," + getBottom() + "," + getLeft() + ")";
    }
}
