# 3. Longest Substring Without Repeating Characters

<span style="color:green">**Medium**</span>

Given a string, find the length of the longest substring without repeating characters.

**Example 1：**

```
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
```

**Example 2：**

```
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

**Example 3：**

```
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

题序 | 原题 | 官方题解
:------------: | :------------: | :-------------:
3 |[Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | [Solution](https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/)


思路一：

使用 `Set` 集合处理数据结果集，遍历字符串，对 `set` 执行新增操作，并更新 `result` 值，当判断当前字符已经存在于集合中时，判断集合于result的大小，

同时对集合进行清空最终返回正确结果，看运行结果会发现无论是耗时还是内存，都比较浪费，因为没有具体的测试，并不知道具体的资源浪费情况，已知的是新建对象和循环体种的 `Set` 集合的方法所耗费的时间和空间较多。代码如下：

``` java
class Solution {
    /**
     * 求字符串的最长连续子串长度
     * 通过比较set集合与result的大小，对结果进行赋值
     * 当if中的结果存在时，更新result，清空set集合，执行下一循环
     *
     * @param s
     * @return
     */11
    public int lengthOfLongestSubstring(String s) {
        Set set = new HashSet();
        int result = 0;
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(set.contains(s.charAt(i))){
                result = result<=set.size()?set.size():result;
                set.clear();
                i= count++;
                continue;
            }else{
                set.add(s.charAt(i));
                result = result<=set.size()?set.size():result;
            }
        }
        return result;
    }
    
}
```
测试用例结果：

Runtime: 51 ms, faster than 17.89% of Java online submissions for Longest Substring Without Repeating Characters.

Memory Usage: 38.3 MB, less than 49.53% of Java online submissions for Longest Substring Without Repeating Characters.

思路二：

在思路一的基础上，优化程序算法，减少资源的浪费，实现效率最大化。

结语
---
如果对代码有疑问，或者认为有更好的方法，欢迎指出

