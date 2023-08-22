package agty.libs;

import java.util.HashMap;

public class Params {
    private HashMap<String, String> $array = new HashMap<String, String>();

    public Params param(String name, String value) {
        return this;
    }

    public Params put(String name, String value) {
        $array.put(name, value);
        return this;
    }

    public String get(String name) {
        return $array.get(name);
    }

    public void clear() {
        $array.clear();
        $array = null;
    }

    public HashMap instance() {
        return this.$array;
    }
}
