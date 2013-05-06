package parallelhyflex.parsing.tokenizing;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 *
 * @author kommusoft
 */
public class TokenStreamParser<StreamType extends Token> {
    
    private final TreeSet<TokenGenerator<? extends StreamType>> tokens = new TreeSet<>(TokenPriorityComparator.getInstance());
    private static final Pattern word = Pattern.compile("[^ \n\t]+");
    
    public void addToken (TokenGenerator<? extends StreamType> token) {
        this.tokens.add(token);
    }
    
    public void addToken (TokenGenerator<? extends StreamType>... token) {
        for(TokenGenerator<? extends StreamType> tok : token) {
            this.addToken(tok);
        }
    }
    public void addToken (Iterable<TokenGenerator<? extends StreamType>> token) {
        for(TokenGenerator<? extends StreamType> tok : token) {
            this.addToken(tok);
        }
    }
    
    public Iterable<StreamType> getIterable (InputStream stream) {
        return new TokenParserIterable(stream);
    }
    
    private class TokenParserIterable implements Iterable<StreamType> {
        
        private final InputStream stream;
        
        public TokenParserIterable (InputStream stream) {
            this.stream = stream;
        }

        @Override
        public Iterator<StreamType> iterator() {
            return new TokenParserIterator(this.stream);
        }
        
    }
    
    private class TokenParserIterator implements Iterator<StreamType> {
        
        private final Scanner scanner;
        
        public TokenParserIterator (InputStream stream) {
            this.scanner = new Scanner(stream);
        }

        @Override
        public boolean hasNext() {
            return this.scanner.hasNext(word);
        }

        @Override
        public StreamType next() {
            String tok = this.scanner.next(word);
            for(TokenGenerator<? extends StreamType> token : tokens) {
                if(token.validate(tok)) {
                    return token.generate(tok);
                }
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported by the TokenParserIterator.");
        }
        
    }
    
}