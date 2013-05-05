package parallelhyflex.parsing;

public abstract class OperatorBase<T, TL extends Token, TR extends Token> extends TokenBase<T> implements Operator<T, TL, TR> {

    private TL left = null;
    private TR right = null;

    @Override
    public void setLeft(TL left) throws ParsingException {
        if (this.canSetLeft(left)) {
            this.left = left;
        } else {
            throw new ParsingException(String.format("Cannot set left argument of %s", this.getClass().getSimpleName()));
        }
    }

    @Override
    public void setRight(TR right) throws ParsingException {
        if (this.canSetRight(right)) {
            this.right = right;
        } else {
            throw new ParsingException(String.format("Cannot set right argument of %s", this.getClass().getSimpleName()));
        }
    }

    @Override
    public TL getLeft() {
        return this.left;
    }

    @Override
    public TR getRight() {
        return this.right;
    }
}
