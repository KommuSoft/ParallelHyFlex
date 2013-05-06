package parallelhyflex.parsing.grammar;

import parallelhyflex.parsing.ParsingException;
import parallelhyflex.parsing.tokenizing.Token;

/**
 *
 * @author kommusoft
 */
public interface Operator<TL extends Token, TR extends Token> extends Token {

    void setLeft(TL left) throws ParsingException;

    void setRight(TR right) throws ParsingException;

    TL getLeft();

    TR getRight();

    boolean canSetLeft(TL token);

    boolean canSetRight(TR token);

    void process();

    double getOperatorPriority();

    OperatorType getOperatorType();
}
