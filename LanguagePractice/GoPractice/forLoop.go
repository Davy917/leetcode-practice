package main

import "fmt"

func forLoop() {
	fmt.Println("infinity")
	i := 1
	for {
		fmt.Print(i, "\t")
		i++
		if i == 10 {
			fmt.Println()
			break
		}
	}
	fmt.Println("condition")
	i = 1
	for i < 11 {
		fmt.Print(i, "\t")
		i++
	}
	fmt.Println()
	fmt.Println("standard")
	for i := 0; i < 11; i++ {
		fmt.Print(i, "\t")
	}
}

/*
影片教學:
https://www.youtube.com/watch?v=tMMljQ4hkFA&list=PLBjZhzRvV2ChPTPNDx_apHdKa9Ha7LVpN&index=13
*/
