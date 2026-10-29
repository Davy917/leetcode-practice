## JavaScript `this` 快速複習
---

## 1) 範例一
### `talk` 還沒調用前，`this` 是誰？

```js
function talk(lang, isPolite) {
    if(isPolite){
        if (lang === 'en') {
            return `I am ${this.name}`
        } else if(lang === 'cn') {
            return `我是${this.name}`
        }
    } else {
        if (lang === 'en') {
            return `${this.name}, what you want?`
        } else if(lang === 'cn') {
            return `${this.name}, 你找誰?`
        }
    }
}
```

**答案：**
- 還沒「執行」函式前，`this` **不會先固定**。
- `this` 是在「呼叫當下」才依呼叫方式決定。

補充：
- 一般函式直接呼叫 `talk()`：  
  - strict mode：`this === undefined`
  - 非 strict mode：`this` 可能是全域物件（瀏覽器是 `window`）

---

### `talk.call(me, ...)` 調用後，`this` 是誰？

```js
const me = { name: 'Sina' }
talk.call(me, 'en', true)   'I am Sina'
talk.call(me, 'en', false)  'Sina, what you want?'
talk.call(me, 'cn', true)   '我是Sina'
talk.call(me, 'cn', false)  'Sina, 你找誰?'
```

**答案：**
- 在這次呼叫中，`this` 被 `call` 明確指定成 `me`。
- 所以 `this.name` 會是 `'Sina'`。

重點：
- `call` 只影響「這一次函式執行」的 `this`。
- 不是永久改掉函式本身。

---

## 2) 範例二
### `Person` 裡的 `this` vs `setTimeout` 裡的 `this`

```js
function Person(n) {
  this.name = n
  this.talk = function() { console.log(this) }
  setTimeout(function() {
    console.log(this)
  }, 100)
}
```

**答案：**
- `Person` 內的 `this`（用 `new Person(...)` 呼叫時）是「新建立的實例」。
- `setTimeout` 裡的「一般函式」有自己的呼叫者，`this` 不會自動沿用外層 `Person` 的 `this`。

常見結果：
- 瀏覽器：`setTimeout` 回呼中的 `this` 常是 `window`
- Node.js：常見是 `Timeout` 物件（不是 `Person` 實例）

---

### 為什麼 `.bind(this)` 會不同？哪個 `this` 變了？

```js
function Person(n) {
    this.name = n
    this.talk = function() {
        console.log(this)
    }
    setTimeout(function() {
        console.log(this)
    }.bind(this), 100)
}
```

**答案：**
- `bind(this)` 會建立一個「綁定版函式」。
- 這個新函式的 `this` 被固定成外層 `Person` 當下的 `this`（也就是實例）。

誰變了？
- 改變的是 **`setTimeout` 回呼函式裡的 `this`**。
- 外層 `Person` 的 `this` 本來就是實例，沒變。

---

### 為什麼箭頭函式會和 `.bind(this)` 得到相同結果？

```js
function Person(n) {
    this.name = n
    this.talk = function() {
        console.log(this)
    }
    setTimeout(() => {
        console.log(this)
    }, 100)
}
```

**答案：**
- 箭頭函式沒有自己的 `this`。
- 它會詞法捕獲（lexical capture）外層作用域的 `this`。
- 在這裡外層是 `Person`，所以拿到的是實例 `this`，效果等同你手動 `.bind(this)`。

---

## 一頁記憶版（超短）

- `this` 在函式「定義時」不固定，多數情況在「呼叫時」決定。
- `call/apply`：本次呼叫直接指定 `this`。
- `bind`：回傳新函式，永久綁定 `this`（可重複使用）。
- 一般回呼函式（如 `setTimeout(function(){})`）的 `this` 常不是你外層想要的物件。
- 箭頭函式 `=>` 不自帶 `this`，會抓外層 `this`。

---

### 補充
call不熟的話先看[`Call & Apply & Bind教學`](.\CallApplyBind.js)

教學從16:45開始看
https://www.youtube.com/watch?v=fVXp7ZWjlO4
