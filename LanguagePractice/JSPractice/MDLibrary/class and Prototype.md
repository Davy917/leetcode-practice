## 學習JS object, Prototype, __proto__必看
https://www.youtube.com/watch?v=1UTqFAjYx1k

在 JavaScript 中，class（類別）是在 ES6 (2015年) 引入的語法糖（Syntactic Sugar）。
它本質上不是像 Java 或 C++ 那樣的傳統類別，而是基於 JavaScript 原有的原型鏈（Prototype Chain）機制的包裝，讓物件導向程式設計（OOP）的語法更直覺、更好讀。

---

## 基本語法與結構
一個標準的 class 包含建構子、屬性與方法：

```javascript
class Person {
  // 1. 建構子：建立物件時會自動執行，用來初始化屬性
  constructor(name, age) {
    this.name = name; // 公有屬性
    this.age = age;
  }

  // 2. 原型方法：定義在原型上，所有實例共享
  sayHello() {
    return `Hi, 我是 ${this.name}，今年 ${this.age} 歲。`;
  }
}

// 實例化：使用 new 關鍵字建立物件
const tom = new Person('Tom', 25);
console.log(tom.sayHello()); // 輸出: Hi, 我是 Tom，今年 25 歲。
```

---

## 核心進階功能

### 1. 繼承（Inheritance）
使用 `extends` 關鍵字可以繼承另一個類別。如果子類別有寫 `constructor`，就必須先呼叫 `super()` 來觸發父類別的建構子。

```javascript
class Employee extends Person {
  constructor(name, age, position) {
    super(name, age); // 呼叫父類別的 constructor
    this.position = position;
  }

  // 覆寫（Override）父類別的方法
  sayHello() {
    return `${super.sayHello()} 我的職位是 ${this.position}。`;
  }
}

const jerry = new Employee('Jerry', 30, '工程師');
console.log(jerry.sayHello()); // 輸出: Hi, 我是 Jerry，今年 30 歲。 我的職位是 工程師。
```

### 2. 靜態方法與屬性（Static）
使用 `static` 關鍵字定義的方法或屬性，只能由類別本身呼叫，建立出來的實例（物件）無法存取。通常用於工具函式。

```javascript
class MathTools {
  static pi = 3.14159;

  static add(a, b) {
    return a + b;
  }
}

console.log(MathTools.pi);       // 輸出: 3.14159
console.log(MathTools.add(5, 3)); // 輸出: 8
const tools = new MathTools();
// tools.add(5, 3); // 錯誤！實例無法呼叫靜態方法
```

### 3. 私有屬性與方法（Private）
使用 `#` 開頭的變數或方法代表私有成員，只能在 class 內部被讀寫，外部或子類別直接存取會報錯，達到了真正的封裝。

```javascript
class BankAccount {
  #balance = 0; // 私有屬性

  constructor(money) {
    this.#balance = money;
  }

  getBalance() {
    return this.#balance; // 內部可以存取
  }
}

const myAccount = new BankAccount(1000);
console.log(myAccount.getBalance()); // 輸出: 1000
// console.log(myAccount.#balance);  // 錯誤！語法不允許直接從外部存取
```

### 4. Getter 與 Setter
用來控制屬性的讀取與寫入，可以在賦值時進行資料驗證。

```javascript
class Thermostat {
  constructor(celsius) {
    this._celsius = celsius;
  }

  // 讀取器
  get fahrenheit() {
    return (this._celsius * 9) / 5 + 32;
  }

  // 設值器
  set fahrenheit(value) {
    this._celsius = ((value - 32) * 5) / 9;
  }
}

const temp = new Thermostat(25);
console.log(temp.fahrenheit); // 輸出: 77 (背後觸發 get)
temp.fahrenheit = 86;         // (背後觸發 set)
console.log(temp._celsius);   // 輸出: 30
```

---

## 常見注意事項

* **沒有提升（Hoisting）**：與 function 不同，class 必須先定義才能使用（new），否則會跳出 ReferenceError。
* **嚴格模式（Strict Mode）**：class 內部的所有程式碼預設都在嚴格模式下執行。
* **必須使用 new**：你不能像呼叫普通 function 那樣直接呼叫 Person()，必須搭配 new Person()。

如果你想了解特定的部分，我可以為你說明：

* 這種 class 語法在幕後是如何用舊版的 Prototype（原型鏈） 實現的
* class 中的 this 關鍵字指向 容易踩到的雷（特別是在 callback 中）
* 什麼時候該用 class，什麼時候用單純的 Object 或 Function 更好

ES6 的 class 語法確實在很大程度上參考了 Java 等傳統物件導向語言的寫法，目的是為了讓有其他語言背景的開發者更容易上手。

在 ES6（2015年）之前，JavaScript 沒有 class 關鍵字，主要透過以下 3 種主流方式來創建物件與達到類似類別的效果：

---

## 1. 構造函式 + 原型鏈（最正統的 OOP 做法）
這是最接近後來 class 的底層機制。透過普通 function 作為建構子，並將方法掛載到 `prototype`（原型）上，確保所有實例共享同一個方法，節省記憶體。

```javascript
// 1. 定義構造函式（習慣首字大寫）
function Person(name, age) {
  this.name = name; // 每個物件各自獨立的屬性
  this.age = age;
}

// 2. 將方法寫在原型物件（prototype）上
Person.prototype.sayHello = function() {
  return "Hi, 我是 " + this.name + "，今年 " + this.age + " 歲。";
};

// 3. 使用 new 關鍵字實例化
var tom = new Person('Tom', 25);
console.log(tom.sayHello()); // 輸出: Hi, 我是 Tom，今年 25 歲。
```

ES6 的對應：構造函式的內容變成了 `constructor`，而 `prototype` 上的方法變成了 class 內部的普通方法。

### 那當年是怎麼做「繼承」的？
在沒有 `extends` 的年代，要實現繼承非常繁瑣，需要同時繼承「屬性」與「原型方法」：

```javascript
function Employee(name, age, position) {
  // 繼承屬性（利用 call 切換 this 指向）
  Person.call(this, name, age);
  this.position = position;
}

// 繼承原型方法（讓子類的 prototype 指向父類的 prototype）
Employee.prototype = Object.create(Person.prototype);
// 修正被覆蓋掉的建構子指向
Employee.prototype.constructor = Employee;

Employee.prototype.sayJob = function() {
  return "我的職位是 " + this.position;
};
```

這段複雜的程式碼，在 ES6 之後直接被一個 `extends` 關鍵字完美取代。

---

## 2. 物件字面值（Object Literal）
如果只需要創定一個單一、特定用途的物件，不需要大量複製，最常使用這種極簡的寫法。

```javascript
var mary = {
  name: 'Mary',
  age: 22,
  sayHello: function() {
    return "嗨，我是 " + this.name;
  }
};

console.log(mary.sayHello());
```

* **優點**：直覺、快速。
* **缺點**：無法當作範本來大量重複產生結構相同的物件。

---

## 3. 工廠模式（Factory Pattern）
為了解決物件字面值無法重複產生的缺點，有些開發者會寫一個普通的 function，在裡面打包物件並回傳。

```javascript
function createPerson(name, age) {
  var obj = {}; // 建立一個空物件
  obj.name = name;
  obj.age = age;
  obj.sayHello = function() {
    return "Hi, 我是 " + this.name;
  };
  return obj; // 回傳物件
}

var jerry = createPerson('Jerry', 30);
```

* **優點**：不需要使用 `new` 關鍵字，呼叫很簡單。
* **缺點**：每次呼叫都會在記憶體中重新建立一個 `sayHello` 函式，造成記憶體浪費；且無法辨識這個物件是由哪個「類別」定義出來的（用 `instanceof` 會失靈）。

---

## 總結
ES6 之前的 JavaScript 核心思想是 「原型繼承（Prototypal Inheritance）」，物件是直接繼承自另一個物件，而不是繼承自一個藍圖（Class）。


在 JavaScript 中，`prototype`（原型） 是實現「繼承」與「程式碼共享」的核心機制。

簡單來說，`prototype` 就像是物件的「基因庫」或「共享大本營」。當你用同一個範本建立很多物件時，不需要把所有的方法複製給每一個物件，而是把這些方法放在 `prototype` 裡面，讓所有物件共同取用。

---

## 為什麼需要 Prototype？（為了解決記憶體浪費）
假設我們在沒有 `prototype` 的情況下，用 ES5 的構造函式建立 1000 個「人」的物件：

```javascript
function Person(name) {
  this.name = name;
  this.sayHello = function() {
    return "Hi, 我是 " + this.name;
  };
}
var a = new Person('Tom');
var b = new Person('Jerry');
console.log(a.sayHello === b.sayHello); // 輸出: false
```

這樣寫的話，記憶體裡面會出現 1000 個一模一樣的 `sayHello` 函式，非常浪費記憶體。

## 改用 Prototype 的做法：

```javascript
function Person(name) {
  this.name = name; // 每個人名字不同，各自獨立擁有
}

// 把共同的方法，掛載到共享大本營 (prototype)
Person.prototype.sayHello = function() {
  return "Hi, 我是 " + this.name;
};
var a = new Person('Tom');
var b = new Person('Jerry');
console.log(a.sayHello === b.sayHello); // 輸出: true (共用同一個函式記憶體)
```

---

## 核心概念：`prototype` 與 `__proto__` 的差別

這是在學習 `Prototype` 時最容易搞混的兩個東西：

### 1. `prototype`（原型的藍圖）

* 誰擁有它：只有 `Function`（函式） 才有。
* 它的作用：當這個函式被當作 `new` 的建構子時，它會把這個 `prototype` 當作範本，指派給新建立的物件。

### 2. `__proto__`（物件的隱式原型）

* 誰擁有它：所有物件（`Object`） 都有。
* 它的作用：它是物件內部的指標，指向它爸爸的 `prototype`。

💡 一言以蔽之：
`a.__proto__ === Person.prototype`
（兒子 `a` 內部的血緣指標，指向當初生下他的 `Person` 爸爸的基因庫。）

---

## 什麼是「原型鏈（Prototype Chain）」？

當你呼叫一個物件的屬性或方法時（例如 `a.sayHello()`），JavaScript 的尋找順序是：

1. 先在自己身上找：看看物件 `a` 本身有沒有 `sayHello`。
2. 找不到？去爸爸的基因庫找：透過 `__proto__` 跑到 `Person.prototype` 去找。
3. 還找不到？去爺爺的基因庫找：再透過 `__proto__` 跑到最頂層的 `Object.prototype` 去找。
4. 最後一站：如果一路上都找不到，最後會抵達 `null`，並回傳 `undefined`。

這種一層一層往上尋找的鏈結關係，就叫做 「原型鏈」。

---

## 現代 JavaScript 的 class 與它的關係

你上一題提到的 ES6 `class` 關鍵字，其實底層完全就是 `Prototype`。

```javascript
class Person {
  constructor(name) { this.name = name; }
  sayHello() { return "Hi"; }
}
```

當你寫下上面這段現代的 `class` 語法，JavaScript 在幕後運作時，依然會自動把 `sayHello` 這個方法掛載到 `Person.prototype` 上。它只是幫你把繁瑣的原型語法包裝成好看的樣子（語法糖）而已。