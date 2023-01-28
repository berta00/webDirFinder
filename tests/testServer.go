package main

import (
	"fmt"
	"net/http"
)

func main() {
	fmt.println("Welcome to webDirFinde's test server")
	initRoute()
	http.ListenAndServe(":8080", nil)
}
func initRoute() {
	http.HandleFunc("/", page1)
	http.HandleFunc("/dash", pageDah)
	http.HandleFunc("/info", pageInfo)
	http.HandleFunc("/api", pageApi)
	http.HandleFunc("/home", pageHome)
	http.HandleFunc("/id", pageId)
	http.HandleFunc("/test", pageTest)
}

// routes
func page1(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Page1!")
}
func pageDah(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Dashbaord!")
}
func pageInfo(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Secreet informations!")
}
func pageApi(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Some apis!")
}
func pageHome(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Home sweet home!")
}
func pageId(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Id is 123!")
}
func pageTest(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Test1 went wrong :(")
}
