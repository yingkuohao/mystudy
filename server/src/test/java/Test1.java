import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/8/27
 * Time: 14:45
 * CopyRight: taobao
 * Descrption:
 */

public class Test1 {
    public static void main(String[] args) {
        int i = 0;
        boolean bool = (i++ < 1);
        System.out.println("" +bool);
        System.out.println("i==" + i++);
        Double s = 2e10;
        System.out.println("s=" + s);
        Double b = 2000d;
        System.out.println(s.compareTo(b));
        Test1 test1 = new Test1();
        boolean bool1 = test1.percent(BigDecimal.valueOf(90), BigDecimal.valueOf(111));
        boolean bool2 = test1.percent(BigDecimal.valueOf(11), BigDecimal.valueOf(99));
        System.out.println((int) Math.ceil(13 / 10));
    }

    private boolean percent(int i, int j) {
        IntMath.divide(9, 10, RoundingMode.HALF_DOWN);

        double percent = Math.abs(1.0 - 1.0 * i / j);
        return percent > 0.95;
    }

    private boolean percent(BigDecimal i, BigDecimal j) {

        BigDecimal percent = BigDecimal.ONE.subtract(i.divide(j, 2, BigDecimal.ROUND_DOWN));
        return percent.abs().compareTo(BigDecimal.valueOf(0.1)) >= 0;
    }
}

