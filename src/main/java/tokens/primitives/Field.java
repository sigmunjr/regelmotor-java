package tokens.primitives;

/**
 * Created by sigrol on 13.09.15.
 */
public class Field extends Primitive {
    private String key;

    public Field(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
