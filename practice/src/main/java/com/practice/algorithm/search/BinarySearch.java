package com.practice.algorithm.search;

/**
 * 二分搜索
 */
public class BinarySearch {

    public static void main(String[] args) {
        System.out.println("***************binary_search****************");
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(binary_search(nums, 1));
        System.out.println(binary_search(nums, 2));
        System.out.println(binary_search(nums, 3));
        System.out.println(binary_search(nums, 4));
        System.out.println(binary_search(nums, 5));
        System.out.println(binary_search(nums, 6));
        System.out.println(binary_search(nums, 7));

        System.out.println("***************left_bound****************");
        nums = new int[]{1, 2, 2, 2, 5, 6};
        System.out.println(left_bound(nums, 1));
        System.out.println(left_bound(nums, 2));
        System.out.println(left_bound(nums, 3));
        System.out.println(left_bound(nums, 4));
        System.out.println(left_bound(nums, 5));
        System.out.println(left_bound(nums, 6));
        System.out.println(left_bound(nums, 7));

        System.out.println("***************right_bound****************");
        nums = new int[]{1, 2, 2, 2, 5, 6};
        System.out.println(right_bound(nums, 1));
        System.out.println(right_bound(nums, 2));
        System.out.println(right_bound(nums, 3));
        System.out.println(right_bound(nums, 4));
        System.out.println(right_bound(nums, 5));
        System.out.println(right_bound(nums, 6));
        System.out.println(right_bound(nums, 7));
    }

    /**
     * 查找目标值
     *
     * @param nums   数组
     * @param target 目标值
     */
    private static int binary_search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 查找目标值左边界
     *
     * @param nums   数组
     * @param target 目标值
     */
    private static int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 判断 target 是否存在于 nums 中
        if (left < 0 || left >= nums.length) {
            return -1;
        }
        // 判断一下 nums[left] 是不是 target
        return nums[left] == target ? left : -1;
    }

    /**
     * 查找目标值右边界
     *
     * @param nums   数组
     * @param target 目标值
     */
    private static int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }

        // 由于 while 的结束条件是 right == left - 1，且现在在求右边界
        // 所以用 right 替代 left - 1 更好记
        if (right < 0 || right >= nums.length) {
            return -1;
        }
        return nums[right] == target ? right : -1;
    }
}
