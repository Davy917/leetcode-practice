/**
 * @param {string[]} dictionary
 */

const ValidWordAbbr = (function(){
    function ValidWordAbbr(dictionary){
        this.m =new Map()
        for (let word of dictionary){
            key = createKey(word)
            if(this.m.has(key))
                this.m.get(key).push(word)
            else
                this.m.set(key, [word])
        }
        console.log(this.m)
    }
    function createKey(word){
        //設計哈希鍵
        const n = word.length
        if(n===1)
            key = word.charAt(0)
        else if(n===2)
            key = word.charAt(0) + word.charAt(n-1)
        else
            key = word.charAt(0) + `${n-2}` + word.charAt(n-1)
        return key
    }

    ValidWordAbbr.prototype.isUnique = function(word) {
        const key = createKey(word)
        if(this.m.has(key))
            for(let str of this.m.get(key))
                if(str !== word)
                    return false
        return true
    };
    return ValidWordAbbr
})();

if (require.main === module){
    const validWordAbbr = new ValidWordAbbr(["deer", "deer", "door", "cake", "card", "internationalization", "it", "z"])
    console.log(validWordAbbr.isUnique("deer"))
}

/*
自己寫的
FAQ:
Q:
ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
validWordAbbr.isUnique("deer"); 
這個isUnique是返回true還是false

A:
false因為 door !=== deer

Q:
d2r: ["deer", "deer"] 這樣的鍵值對是允許存在的嗎?

A:
允許存在, 而且這種情況下 validWordAbbr.isUnique("deer") 會返回 true
*/