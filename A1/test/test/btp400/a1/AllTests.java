package test.btp400.a1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import lab4JUnit.MyClassTest;
import lab4JUnit.MySecondClassTest;

@RunWith(Suite.class)
@SuiteClasses({AccountTest.class, BankTest.class, ChequingTest.class, GICTest.class})
public class AllTests {

}
