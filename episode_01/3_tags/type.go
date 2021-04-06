package main

import (
	"encoding/json"
	"fmt"
)

func main() {
	request := FrontendRequest{
		ArtifactURL:     "https://example.com/foo.tar.bz2",
		SimulationClass: "com.ea.Foo",
		Output:          "output-folder",
		Region:          "us-east-1",
		InstanceCount:   1,
		InstanceType:    "c4.large",
		AmiID:           "ami-daf4bfba",
	}
	response := FrontendResponse{"the-id"}

	requestJSON, _ := toJSON(request)
	responseJSON, _ := toJSON(response)

	fmt.Printf("request in JSON: %s\n", requestJSON)
	fmt.Printf("response in JSON: %s\n", responseJSON)
}

// FrontendRequest A request for the front-end.
type FrontendRequest struct {
	ArtifactURL     string `json:"artifact-url"`
	SimulationClass string `json:"simulation-class"`
	Output          string `json:"output"`
	Region          string `json:"region"`
	InstanceCount   int64  `json:"instance-count"`
	InstanceType    string `json:"instance-type"`
	AmiID           string `json:"ami-id"`
}

// FrontendResponse The response object sent back by the front-end.
type FrontendResponse struct {
	ID string `json:"id"`
}

func toJSON(r interface{}) (string, error) {
	val, err := json.Marshal(r)

	if err != nil {
		return "", err
	}

	return string(val), nil
}
