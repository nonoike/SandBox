package exercise19_6;

import static exercise19_6.FrameworkTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import exercise19_6.Framework.ApplicationServer;
import exercise19_6.Framework.Database;

@RunWith(Theories.class)
public class FrameworkTest {
    private Framework sut;

    @DataPoints
    public static ApplicationServer[] applicationServers = ApplicationServer.values();
    @DataPoints
    public static Database[] databases = Database.values();

    @Before
    public void setUp() throws Exception {
        sut = new Framework();
    }

    @Theory
    public void isSupportはサポート対象ならばtrueを返す(ApplicationServer applicationServer, Database database)
            throws Exception {
        assumeTrue(isSupport(applicationServer, database));
        String msg = "expected: " + true + ", but actual is " + sut.isSupport(applicationServer, database);
        assertThat(msg, sut.isSupport(applicationServer, database), is(true));
    }

    @Theory
    public void isSupportはサポート対象ではないならばfalseを返す(ApplicationServer applicationServer, Database database)
            throws Exception {
        assumeFalse(isSupport(applicationServer, database));
        String msg = "expected: " + false + ", but actual is " + sut.isSupport(applicationServer, database);
        assertThat(msg, sut.isSupport(applicationServer, database), is(false));
    }
}
