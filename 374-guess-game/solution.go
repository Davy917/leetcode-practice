package main

var pick int = 6

func guess(num int) int {
	if num > pick {
		return -1
	} else if num < pick {
		return 1
	} else {
		return 0
	}
}

func guessNumber(n int) int {
	l, r := 1, n
	for l <= r {
		m := (l + r) / 2
		if guess(m) == -1 {
			r = m - 1
		} else if guess(m) == 1 {
			l = m + 1
		} else {
			return m
		}
	}
	return -1
}
