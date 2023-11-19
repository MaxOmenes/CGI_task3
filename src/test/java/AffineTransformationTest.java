import com.cgvsu.transformation.AffineTransformation;
import org.junit.Test;

import javax.vecmath.Matrix3f;
import java.beans.JavaBean;

public class AffineTransformationTest {
    @Test
    public void rotateTest(){
        AffineTransformation f = new AffineTransformation();
        f.setRotateX(30);
        Matrix3f j = f.rotate();
        System.out.println(j.toString());

    }
}
