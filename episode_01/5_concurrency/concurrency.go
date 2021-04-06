package main

import (
	"fmt"
	"net/http"
	"time"
)

func main() {
	urls := []string{"https://www.google.com/", "https://www.google.com/", "https://www.google.com/"}

	printResults("Sequential:", getURLsSequential(urls))
	printResults("Concurrent:", getURLsConcurrent(urls))
}

func printResults(prefix string, results []Result) {
	fmt.Println(prefix)
	for _, r := range results {
		fmt.Printf("Result: %v\n", r)
	}
}

func getURLsSequential(urls []string) []Result {
	results := make([]Result, len(urls))
	for i, url := range urls {
		results[i] = getURL(url)
	}
	return results
}

func getURLsConcurrent(urls []string) []Result {
	ch := make(chan Result)

	// Submit all URL requests in parallel.
	for i := range urls {
		go func(index int) {
			ch <- getURL(urls[index])
		}(i)
	}
	// Dequeue one getURL result at a time.
	results := make([]Result, len(urls))
	for i := range urls {
		results[i] = <-ch
	}
	return results
}

func getURL(url string) Result {
	client := http.Client{Timeout: time.Duration(1 * time.Second)}

	before := time.Now().UnixNano()
	_, err := client.Get(url)
	after := time.Now().UnixNano()
	durationMs := (after - before) / 1000 / 1000

	return Result{durationMs, err == nil}
}

// Result encapsulates the response time and whether there were any errors.
type Result struct {
	TimeMs  int64
	Success bool
}

func (r Result) String() string {
	return fmt.Sprintf("{timeMs: %4d, success: %v}", r.TimeMs, r.Success)
}
