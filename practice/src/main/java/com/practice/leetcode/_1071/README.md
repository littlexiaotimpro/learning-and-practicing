# 1071. 字符串的最大公因子

<span style="color:green">**Simple**</span>

对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。

返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。

思路一：

求解两个字符串相同的最长子串，且每个字符串都可以由若干（>=1）个字串组成。

通过计算两个字符串长度的最大公因子，截取子串
- 1.比较两个资源字符串能否被子串完全替换
- 2.获取字串最后的出现位置结合长度的最大公因子比较求解

```java
public class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int s1 = str1.length();
        int s2 = str2.length();
        int result = 0;
        for (int i = 1; i <= Math.min(s1, s2); i++) {
            if (s1 % i == 0 && s2 % i == 0) {
                result = i;
            }
        }
        String answer = str1.substring(0, result);
//        str1 = str1.replaceAll(answer, "");
//        str2 = str2.replaceAll(answer, "");
//        return "".equals(str1) && "".equals(str2) ? answer : "";
        int lastIndexOf1 = str1.lastIndexOf(answer);
        int lastIndexOf2 = str2.lastIndexOf(answer);
        return (s1 + s2 - 2 * result) == (lastIndexOf1 + lastIndexOf2) ? answer : "";
    }
}
```

思路二：
如果存在结果，则两个字符串拼接后一定为整数倍的公因子串，所以如果 1+2 != 2+1 则一定无解

辗转相除法求字符串长度的最大公约数
- 1.gcd(a,0) = a
- 2.gcd(a,b) = gcd(b,a mod b)

```java
public class Solution {
    public String gcdOfStrings_best(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // 辗转相除法求gcd。
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```
