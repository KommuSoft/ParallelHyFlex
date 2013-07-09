package parallelhyflex.parsing.grammar;

import parallelhyflex.parsing.ParsingException;
import parallelhyflex.parsing.tokenizing.Token;

/**
 *
 * @author kommusoft
 * @param <TL>
 * @param <TR>
 */
public abstract class OperatorBase<TL extends Token, TR extends Token> implements Token, Operator<TL, TR> {

    private TL left = null;
    private TR right = null;

    /**
     *
     * @return
     */
    @Override
    public double getOperatorPriority() {
        return this.getClass().getAnnotation((Class<OperatorAnnotation>) OperatorAnnotation.class).operatorPriority();
    }

    /**
     *
     */
    @Override
    public void process() {
    }

    /**
     *
     * @return
     */
    @Override
    public OperatorType getOperatorType() {
        return this.getClass().getAnnotation((Class<OperatorAnnotation>) OperatorAnnotation.class).operatorType();
    }

    /**
     *
     * @param left
     * @throws ParsingException
     */
    @Override
    public void setLeft(TL left) throws ParsingException {
        if (this.canSetLeft(left)) {
            this.left = left;
        } else {
            throw new ParsingException(String.format("Cannot set left argument of %s", this.getClass().getSimpleName()));
        }
    }

    /**
     *
     * @param right
     * @throws ParsingException
     */
    @Override
    public void setRight(TR right) throws ParsingException {
        if (this.canSetRight(right)) {
            this.right = right;
        } else {
            throw new ParsingException(String.format("Cannot set right argument of %s", this.getClass().getSimpleName()));
        }
    }

    /**
     *
     * @return
     */
    @Override
    public TL getLeft() {
        return this.left;
    }

    /**
     *
     * @return
     */
    @Override
    public TR getRight() {
        return this.right;
    }
}
