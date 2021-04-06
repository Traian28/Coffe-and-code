package main

import "fmt"

func main() {
	fmt.Printf("Hey there, let me call a function: %d\n", fib(23))
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
