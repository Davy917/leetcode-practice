//屬性 vs 方法
function Person(){
    this.age = 12
}

Person.age = 40
const me = new Person()

console.log(me) //Person.age 已經變 40, 但這邊的 me.age 還是 12, 說明 this.age 會在物件被實例化之後變成該物件的屬性
console.log(Person)
/*
proto chain
me.__proto__ === Person.prototype
Person.prototype.__proto__ === Object.prototype
 */

//Extend class
class otherPerson {
    talk(){
        return 'Talking'
    }
}

class SuperHuman extends otherPerson{
    fly(){
        return 'Flying'
    }
}
const him = new SuperHuman()
console.log(him.talk()) // him可以talk因為, SuperHuman 繼承 otherPerson, 而 otherPerson 有 talk 方法

//除了new之外, 其它創建物件的方式
const Andy = Object.create(SuperHuman)
const Ben = {}
Object.setPrototypeOf(Ben, SuperHuman)