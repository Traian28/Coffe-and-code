package main

import "testing"

func TestFibBaseCases(t *testing.T) {
	if res := fib(0); res != 0 {
		t.Errorf("fib(0) != %d", res)
	}
	if res := fib(1); res != 1 {
		t.Errorf("fib(1) != %d", res)
	}
}

func TestFib(t *testing.T) {
	if res := fib(20); res != 6765 {
		t.Errorf("fib(20) != %d", res)
	}
}
