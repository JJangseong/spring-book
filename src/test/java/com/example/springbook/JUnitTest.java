package com.example.springbook;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;

public class JUnitTest {
    static JUnitTest testObject;

    @Test public void test() {
        assertThat(this, is(not(sameInstance(testObject))));
        testObject = this;
    }

    @Test public void test2() {
        assertThat(this, is(not(sameInstance(testObject))));
        testObject = this;
    }

    @Test public void test3() {
        assertThat(this, is(not(sameInstance(testObject))));
        testObject = this;
    }

}
