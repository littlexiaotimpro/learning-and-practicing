# 300. 最长上升子序列

给定一个无序的整数数组，找到其中最长上升子序列的长度。

思路一：

遍历每一种可能出现的结果，比较每次结果的集合大小求解
- 1.保留每次遍历结果的最小值，判断其与当前值的大小关系，偏小则保留
- 2.重复执行遍历过程，确保每一种结果都出现过

```java
public class Solution {
    public int lengthOfLIS(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int result = 0;
        int curr = 0;
        for(int i=0;i<nums.length;i++){
            curr = nums[i];
            Iterator<Integer> iter = set.iterator();
            while(iter.hasNext()){
                if(iter.next() >curr){
                    iter.remove();
                }
            }
            set.add(curr);
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]>=curr){
                    curr = nums[j];
                    set.add(curr);
                }
            }
            result = Math.max(result, set.size());
        }
        return result;
    }
}
```
