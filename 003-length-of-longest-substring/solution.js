/**
 * LeetCode Problem 003 - Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * 題目：給定一個字符串，找出不含重複字符的最長子串的長度
 */

/**
 * @param {string} s
 * @return {number}
 */
function lengthOfLongestSubstring(s) {
    const charMap = new Map();
    let maxLength = 0;
    let left = 0;

    for (let right = 0; right < s.length; right++) {
        const char = s[right];

        if (charMap.has(char)) {
            left = Math.max(left, charMap.get(char) + 1);
        }

        charMap.set(char, right);
        maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
}

// 測試用例
if (import.meta.url === `file://${process.argv[1]}`) {
    const testCases = [
        { input: 'abcabcbb', expected: 3 },      // "abc"
        { input: 'bbbbb', expected: 1 },         // "b"
        { input: 'pwwkew', expected: 3 },        // "wke"
        { input: 'au', expected: 2 },            // "au"
        { input: '', expected: 0 }               // empty string
    ];

    console.log('Running tests for Problem 003...\n');
    testCases.forEach((testCase, index) => {
        const result = lengthOfLongestSubstring(testCase.input);
        const passed = result === testCase.expected;
        const status = passed ? '✓' : '✗';
        console.log(`Test ${index + 1} ${status}`);
        console.log(`  Input: "${testCase.input}"`);
        console.log(`  Expected: ${testCase.expected}, Got: ${result}\n`);
    });
}

export default lengthOfLongestSubstring;
