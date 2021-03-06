package takenoko.util.comparators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import takenoko.Plateau;
import takenoko.plot.CoordAxial;
import takenoko.plot.Plot;

import static org.junit.Assert.assertTrue;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompPosPandaTest {
    @Autowired
    @Qualifier("plateauTakenoko")
    Plateau p;
    @Test
    public void compare() {

        p.putPlot(new Plot(1,0));
        p.putPlot(new Plot(-1,1));

        CompPosPanda cp = new CompPosPanda(p);

        assertTrue(cp.compare(new CoordAxial(-1,1), new CoordAxial(1,0)) == 0);

        p.getPlot(1,0).pousserBambou();

        assertTrue(cp.compare(new CoordAxial(-1,1), new CoordAxial(1,0)) > 0);
    }
}