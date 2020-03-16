# 面试题 01.06. 字符串压缩

字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。

比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。

思路一：

直接比较并统计各个字符出现的次数，累计求结果。

```java
public class Solution {
    /**
     * 思路一：累计并统计字符出现次数，拼接求结果
     */
    public String compressString(String S) {
        if (S == null || S.length() < 3) {
            // null || length < 3
            return S;
        } else {
            StringBuilder builder = new StringBuilder("");
            char c = S.charAt(0);
            builder.append(c);
            int count = 1;
            for (int i = 1; i < S.length(); i++) {
                if (c == S.charAt(i)) {
                    count += 1;
                } else {
                    builder.append(count);
                    // 出现新字符时重新赋值并统计新字符的个数
                    c = S.charAt(i);
                    count = 1;
                    builder.append(c);
                }
            }
            builder.append(count);
            String result = builder.toString();
            return result.length() >= S.length() ? S : result;
        }
    }
}
```
