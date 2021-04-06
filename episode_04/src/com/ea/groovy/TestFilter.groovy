package com.ea.groovy

import groovy.mock.interceptor.MockFor


class TestFilter extends GroovyTestCase{

    void testFilter(){
        def filter = [ evaluate : {true},
                       isEnabled: {true}] as Filter
        assertTrue(filter.isEnabled())
    }

void testMockFilter () {
    MockFor filterMock = new MockFor(Filter)

    filterMock.demand.isEnabled {true}
    //filterMock.demand.isEnabled(2) {true}
    filterMock.ignore.evaluate {it == 2}

    def filter = filterMock.proxyDelegateInstance()

    //sample invocation
    filter.isEnabled()

    filterMock.verify(filter)
}
}
