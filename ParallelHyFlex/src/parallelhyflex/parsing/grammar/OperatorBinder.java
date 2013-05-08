package parallelhyflex.parsing.grammar;

import java.util.LinkedList;
import java.util.TreeMap;
import parallelhyflex.algebra.collections.MultiThreadedList;
import parallelhyflex.algebra.collections.MultiThreadedListNode;
import parallelhyflex.parsing.ParsingException;
import parallelhyflex.parsing.tokenizing.Token;

/**
 *
 * @author kommusoft
 */
public class OperatorBinder {

    public MultiThreadedList<Token> bind(Iterable<? extends Token> tokenStream) throws ParsingException {
        MultiThreadedList<Token> memory = new MultiThreadedList<>();
        TreeMap<Double, LinkedList<MultiThreadedListNode<Token>>> bindingMap = new TreeMap<>();
        for (Token t : tokenStream) {
            memory.add(t);
            if (t instanceof Operator) {
                Operator to = (Operator) t;
                double priority = to.getOperatorPriority();
                LinkedList<MultiThreadedListNode<Token>> operatorList;
                if (!bindingMap.containsKey(priority)) {
                    operatorList = new LinkedList<>();
                    bindingMap.put(priority, operatorList);
                } else {
                    operatorList = bindingMap.get(priority);
                }
                operatorList.add(memory.getLastNode());
            }
        }
        for (LinkedList<MultiThreadedListNode<Token>> operatorList : bindingMap.values()) {
            for (MultiThreadedListNode<Token> tok : operatorList) {
                Operator op = (Operator) tok.getElement();
                if(op.getOperatorType().withLeft()) {
                    op.setLeft(tok.getPrevious().getElement());
                    memory.removeNode(tok.getPrevious());
                }
                if(op.getOperatorType().withRight()) {
                    op.setRight(tok.getNext().getElement());
                    memory.removeNode(tok.getNext());
                }
                op.process();
            }
        }
        return memory;
    }
}
