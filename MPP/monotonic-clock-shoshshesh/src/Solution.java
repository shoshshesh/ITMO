import org.jetbrains.annotations.NotNull;

/**
 * В теле класса решения разрешено использовать только финальные переменные типа RegularInt.
 * Нельзя volatile, нельзя другие типы, нельзя блокировки, нельзя лазить в глобальные переменные.
 *
 * @author Bondarev Nikita
 */
public class Solution implements MonotonicClock {
    private final RegularInt c11 = new RegularInt(0);
    private final RegularInt c12 = new RegularInt(0);
    private final RegularInt c13 = new RegularInt(0);

    private final RegularInt c21 = new RegularInt(0);
    private final RegularInt c22 = new RegularInt(0);
    private final RegularInt c23 = new RegularInt(0);

    @Override
    public void write(@NotNull Time time) {
        c21.setValue(time.getD1());
        c22.setValue(time.getD2());
        c23.setValue(time.getD3());
        c13.setValue(c23.getValue());
        c12.setValue(c22.getValue());
        c11.setValue(c21.getValue());
    }

    @NotNull
    @Override
    public Time read() {
        Time r1 = new Time(c11.getValue(), c12.getValue(), c13.getValue());
        Time r2 = readReversed();
        if (r1.compareTo(r2) == 0) {
            return r1;
        } else {
            int p = getP(r1, r2);
            int[] r2Array = {r2.getD1(), r2.getD2(), r2.getD3()};
            for (int i = p + 1; i < 3; i++) {
                r2Array[i] = 0;
            }
            return new Time(r2Array[0], r2Array[1], r2Array[2]);
        }

    }

    private Time readReversed() {
        int d3 = c23.getValue();
        int d2 = c22.getValue();
        int d1 = c21.getValue();
        return new Time(d1, d2, d3);
    }

    private static int getP(Time r1, Time r2) {
        int p = 0;
        boolean[] equals = {r1.getD1() == r2.getD1(), r1.getD2() == r2.getD2(), r1.getD3() == r2.getD3()};
        for (boolean eq : equals) {
            if (eq) {
                p++;
            } else {
                break;
            }
        }
        return p;
    }
}