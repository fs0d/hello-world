package reflect.target;

public class GenericEntity {

    final String field1;

    private boolean field2;

    protected byte field3;

    public char field4;

    private static short field5 = 1;

    protected static int field6 = 2;

    public static long field7 = 3l;

    protected final float field8;

    public final double field9;

    public GenericEntity(String field1, boolean field2, byte field3, char field4, float field8, double field9) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field8 = field8;
        this.field9 = field9;
    }
}
