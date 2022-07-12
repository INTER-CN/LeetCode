package com.gemini.nowcoder.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * HJ16 购物单
 * https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&tqId=21239
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/11
 */
public class ShopList {

    static class ShopItem {
        public int mainValue;
        public int mainPriority;
        public boolean hasAccessory1;
        public int accValue1;
        public int accPriority1;
        public boolean hasAccessory2;
        public int accValue2;
        public int accPriority2;

        public ShopItem(int mainValue, int mainPriority,
                        boolean hasAccessory1, int accValue1, int accPriority1,
                        boolean hasAccessory2, int accValue2, int accPriority2) {
            this.mainValue = mainValue;
            this.mainPriority = mainPriority;
            this.hasAccessory1 = hasAccessory1;
            this.accValue1 = accValue1;
            this.accPriority1 = accPriority1;
            this.hasAccessory2 = hasAccessory2;
            this.accValue2 = accValue2;
            this.accPriority2 = accPriority2;
        }
    }

    public static void main(String[] args) throws Exception {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] initItem = line.split(" ");
        int budget = Integer.parseInt(initItem[0]) / 10;
        int m = Integer.parseInt(initItem[1]);
        int[] value = new int[m];
        int[] priority = new int[m];
        int[] type = new int[m];
        for (int i = 0; i < m; i++) {
            line = br.readLine();
            String[] item = line.split(" ");
            value[i] = Integer.parseInt(item[0]) / 10;
            priority[i] = Integer.parseInt(item[1]);
            type[i] = Integer.parseInt(item[2]);
        }
        br.close();

        // 把附件解析成主件+附件
        List<ShopItem> shopItemList = new ArrayList<>();
        Map<Integer, ShopItem> shopItemMap = new HashMap<>();
        // 读取主件
        for (int i = 0; i < m; i++) {
            if (type[i] > 0) continue;
            ShopItem item = new ShopItem(value[i], priority[i],
                false, 0, 0,
                false, 0, 0);
            shopItemList.add(item);
            shopItemMap.put(i + 1, item);
        }
        // 读取附件
        for (int i = 0; i < m; i++) {
            if (type[i] == 0) continue;
            ShopItem item = shopItemMap.get(type[i]);
            if (!item.hasAccessory1) {
                item.hasAccessory1 = true;
                item.accValue1 = value[i];
                item.accPriority1 = priority[i];
            } else if (!item.hasAccessory2) {
                item.hasAccessory2 = true;
                item.accValue2 = value[i];
                item.accPriority2 = priority[i];
            }
        }

        int n = shopItemList.size();

        // dp[i][j][k]: 在主件[0..i]范围内使用预算j的最大满意度
        // k=0: 不买附件; k=1: 只买第一个附件; k=2: 只买第二个附件; k=3: 买两个附件
        int[][][] dp = new int[n][budget + 1][4];

        // 预算为0
        for (int i = 0; i < n; i++) for (int k = 0; k < 4; k++) dp[i][0][k] = 0;

        // 只买第0个主件
        for (int j = 1; j <= budget; j++) {
            ShopItem item = shopItemList.get(0);
            int mainValue = item.mainValue;
            int value1 = item.hasAccessory1 ? item.accValue1 : 0;
            int value2 = item.hasAccessory2 ? item.accValue2 : 0;

            if (j >= mainValue) {
                dp[0][j][0] = mainValue * item.mainPriority;
            }

            if (item.hasAccessory1) {
                if (j >= mainValue + value1) dp[0][j][1] = mainValue * item.mainPriority + value1 * item.accPriority1;
            } else {
                dp[0][j][1] = 0;
            }

            if (item.hasAccessory2) {
                if (j >= mainValue + value2) dp[0][j][2] = mainValue * item.mainPriority + value2 * item.accPriority2;
            } else {
                dp[0][j][2] = 0;
            }

            if (item.hasAccessory1 && item.hasAccessory2) {
                if (j >= mainValue + value1 + value2) dp[0][j][3] = mainValue * item.mainPriority
                    + value1 * item.accPriority1 + value2 * item.accPriority2;
            } else {
                dp[0][j][3] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= budget; j++) {
                ShopItem item = shopItemList.get(i);
                int mainValue = item.mainValue;
                int value1 = item.hasAccessory1 ? item.accValue1 : 0;
                int value2 = item.hasAccessory2 ? item.accValue2 : 0;

                if (j >= mainValue) {
                    dp[i][j][0] = getMax(
                        dp[i - 1][j][0], dp[i - 1][j][1], dp[i - 1][j][2], dp[i - 1][j][3],
                        dp[i - 1][j - mainValue][0] + mainValue * item.mainPriority,
                        dp[i - 1][j - mainValue][1] + mainValue * item.mainPriority,
                        dp[i - 1][j - mainValue][2] + mainValue * item.mainPriority,
                        dp[i - 1][j - mainValue][3] + mainValue * item.mainPriority);
                } else {
                    dp[i][j][0] = getMax(dp[i - 1][j][0], dp[i - 1][j][1], dp[i - 1][j][2], dp[i - 1][j][3]);
                }

                if (item.hasAccessory1 && j >= mainValue + value1) {
                    dp[i][j][1] = getMax(
                        dp[i - 1][j][0], dp[i - 1][j][1], dp[i - 1][j][2], dp[i - 1][j][3],
                        dp[i - 1][j - mainValue - value1][0] + mainValue * item.mainPriority + value1 * item.accPriority1,
                        dp[i - 1][j - mainValue - value1][1] + mainValue * item.mainPriority + value1 * item.accPriority1,
                        dp[i - 1][j - mainValue - value1][2] + mainValue * item.mainPriority + value1 * item.accPriority1,
                        dp[i - 1][j - mainValue - value1][3] + mainValue * item.mainPriority + value1 * item.accPriority1
                    );
                } else {
                    dp[i][j][1] = getMax(dp[i - 1][j][0], dp[i - 1][j][1], dp[i - 1][j][2], dp[i - 1][j][3]);
                }


                if (item.hasAccessory2 && j >= mainValue + value2) {
                    dp[i][j][2] = getMax(
                        dp[i - 1][j][0], dp[i - 1][j][1], dp[i - 1][j][2], dp[i - 1][j][3],
                        dp[i - 1][j - mainValue - value2][0] + mainValue * item.mainPriority + value2 * item.accPriority2,
                        dp[i - 1][j - mainValue - value2][1] + mainValue * item.mainPriority + value2 * item.accPriority2,
                        dp[i - 1][j - mainValue - value2][2] + mainValue * item.mainPriority + value2 * item.accPriority2,
                        dp[i - 1][j - mainValue - value2][3] + mainValue * item.mainPriority + value2 * item.accPriority2
                    );
                } else {
                    dp[i][j][2] = getMax(dp[i - 1][j][0], dp[i - 1][j][1], dp[i - 1][j][2], dp[i - 1][j][3]);
                }


                if (item.hasAccessory1 && item.hasAccessory2 && j >= mainValue + value1 + value2) {
                    dp[i][j][3] = getMax(
                        dp[i - 1][j][0], dp[i - 1][j][1], dp[i - 1][j][2], dp[i - 1][j][3],
                        dp[i - 1][j - mainValue - value1 - value2][0] + mainValue * item.mainPriority + value1 * item.accPriority1 + value2 * item.accPriority2,
                        dp[i - 1][j - mainValue - value1 - value2][1] + mainValue * item.mainPriority + value1 * item.accPriority1 + value2 * item.accPriority2,
                        dp[i - 1][j - mainValue - value1 - value2][2] + mainValue * item.mainPriority + value1 * item.accPriority1 + value2 * item.accPriority2,
                        dp[i - 1][j - mainValue - value1 - value2][3] + mainValue * item.mainPriority + value1 * item.accPriority1 + value2 * item.accPriority2
                    );
                } else {
                    dp[i][j][3] = getMax(dp[i - 1][j][0], dp[i - 1][j][1], dp[i - 1][j][2], dp[i - 1][j][3]);
                }

            }
        }

        System.out.println(10 * getMax(dp[n - 1][budget][0], dp[n - 1][budget][1], dp[n - 1][budget][2], dp[n - 1][budget][3]));
    }

    private static int getMax(Integer... values) {
        int max = Integer.MIN_VALUE;
        for (Integer value : values) {
            if (value > max) max = value;
        }
        return max;
    }
}
