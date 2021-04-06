package main

import (
	"fmt"
	"os"
	"strconv"
)

func main() {
	if len(os.Args) < 3 {
		// No arguments specified. Fallback to default values.
		calcFib(30, 29)
		return
	}

	n, _ := strconv.ParseInt(os.Args[1], 10, 64)
	m, _ := strconv.ParseInt(os.Args[2], 10, 64)
	calcFib(n, m)
}

func calcFib(n, m int64) {
	ch := make(chan int64)

	parFib(n, ch)
	parFib(m, ch)

	result1 := <-ch
	result2 := <-ch

	fmt.Printf("Result: %d\n", result1+result2)
}

func fib(n int64) int64 {
	if n == 0 {
		return 0
	}
	if n == 1 {
		return 1
	}
	return fib(n-1) + fib(n-2)
}

func parFib(n int64, ch chan int64) {
	go func() {
		ch <- fib(n)
	}()
}
