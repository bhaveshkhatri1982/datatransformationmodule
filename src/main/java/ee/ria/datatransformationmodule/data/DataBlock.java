package ee.ria.datatransformationmodule.data;

import java.util.HashMap;
import java.util.Map;

public class DataBlock<V> extends HashMap<String,V> {

    public DataBlock() {
        super();
    }

    public DataBlock(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public DataBlock(int initialCapacity) {
        super(initialCapacity);
    }

    public DataBlock(Map<String, ? extends V> m) {
        super(m);
    }

    public DataBlock(V titleValue) {
        super();
        put("title", titleValue);
    }

    public DataBlock(V titleValue, Map<String, ? extends V> m) {
        super(m);
        put("title", titleValue);
    }
}
