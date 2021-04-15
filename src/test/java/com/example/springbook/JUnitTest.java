package com.example.springbook;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;

public class JUnitTest {
    static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();

    @Test
    public void test() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }

    @Test
    public void test2() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

    }

    @Test
    public void test3() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
       
    }

}
