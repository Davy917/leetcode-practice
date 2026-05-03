package main

import "fmt"

type User struct {
	Name string
	Id   uint32
}
type Account struct {
	User
	password string //小寫不能跨包訪問
}
type Contact struct {
	*User
	Remark string
}

func structPractice() {
	var u1 User = User{
		Name: "張三",
	}
	u1.Id = 10000

	var u2 *User = &User{
		Name: "李四",
	}
	u2.Id = 10001 //(*u2).Id = 10001

	var a1 = Account{
		User: User{
			Name: "王五",
		},
		password: "666",
	}

	var c1 *Contact = &Contact{
		User: &User{
			Id: u2.Id,
		},
		Remark: "張麻子",
	}
	c1.Name = "王五"
	//c1.User.Name = "王五" 沒有重複自段時可以簡寫成上面那樣
	fmt.Println("a1 = ", a1)
	fmt.Println("c1 = ", c1)
	fmt.Println("c1.User = ", *((*c1).User))
}

/*
java的組合,點進去之後點開筆記本
https://leetcode.cn/leetbook/read/on-java-zhong-wen-ban-ji-chu-juan/lva2xg/

FAQ:
User: &User
在型別前面加上&代表什麼意思??
User: &User{...} 的 & 不是「加在型別前面」，而是：
	先建立一個 User{...}（User 值）
	再用 & 取這個值的位址
	結果型別變成 *User
為什麼這裡要 &User{...}？
	因為你的 Contact 定義是：
	type Contact struct {
		*User
		Remark string
	}
	它內嵌的是 *User（指標型別），所以初始化時要給它一個 *User。
	User{...} 只有 User 型別，不符合；
	&User{...} 才是 *User，型別吻合。
& 的本質
&x = 取得 x 的地址（pointer）。
	例如:
	u := User{Name: "張三"} // u 是 User
	p := &u                // p 是 *User
就是「先有一個匿名 User 值，再拿它地址」，結果是 *User。
	User{...} → 型別是 User
	&User{...} → 型別是 *User

& 是取址運算子，讓 User 值變成 *User 指標值。
*/
