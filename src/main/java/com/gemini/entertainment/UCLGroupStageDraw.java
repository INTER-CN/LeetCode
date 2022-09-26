package com.gemini.entertainment;

import com.gemini.leetcode.problems.recur.Permutations;

import java.util.LinkedList;
import java.util.List;

/**
 * 复杂度过高，未实现
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class UCLGroupStageDraw {

    private class Team {
        private String code;
        private String name;
        private String country;

        public Team(String code, String name, String country) {
            this.code = code;
            this.name = name;
            this.country = country;
        }
    }

    private Team[] pot1;
    private Team[] pot2;
    private Team[] pot3;
    private Team[] pot4;

    public void init() {
        pot1 = new Team[8];
        pot1[0] = new Team("11", "皇家马德里", "西班牙");
        pot1[1] = new Team("12", "法兰克福", "德国");
        pot1[2] = new Team("13", "曼城", "英格兰");
        pot1[3] = new Team("14", "AC米兰", "意大利");
        pot1[4] = new Team("15", "拜仁慕尼黑", "德国");
        pot1[5] = new Team("16", "巴黎圣日尔曼", "法国");
        pot1[6] = new Team("17", "波尔图", "葡萄牙");
        pot1[7] = new Team("18", "阿贾克斯", "荷兰");

        pot2 = new Team[8];
        pot2[0] = new Team("21", "利物浦", "英格兰");
        pot2[1] = new Team("22", "切尔西", "英格兰");
        pot2[2] = new Team("23", "巴塞罗那", "西班牙");
        pot2[3] = new Team("24", "尤文图斯", "意大利");
        pot2[4] = new Team("25", "马德里竞技", "西班牙");
        pot2[5] = new Team("26", "塞维利亚", "西班牙");
        pot2[6] = new Team("27", "RB莱比锡", "德国");
        pot2[7] = new Team("28", "热刺", "英格兰");

        pot3 = new Team[8];
        pot3[0] = new Team("31", "多特蒙德", "德国");
        pot3[1] = new Team("32", "RB萨尔茨堡", "奥地利");
        pot3[2] = new Team("33", "顿涅茨克矿工", "乌克兰");
        pot3[3] = new Team("34", "国际米兰", "意大利");
        pot3[4] = new Team("35", "那不勒斯", "意大利");
        pot3[5] = new Team("36", "本菲卡", "葡萄牙");
        pot3[6] = new Team("37", "里斯本竞技", "葡萄牙");
        pot3[7] = new Team("38", "勒沃库森", "德国");

        pot4 = new Team[8];
        pot4[0] = new Team("41", "格拉斯哥流浪者", "苏格兰");
        pot4[1] = new Team("42", "萨格勒布迪纳摩", "克罗地亚");
        pot4[2] = new Team("43", "马赛", "法国");
        pot4[3] = new Team("44", "哥本哈根", "丹麦");
        pot4[4] = new Team("45", "布鲁日", "比利时");
        pot4[5] = new Team("46", "凯尔特人", "苏格兰");
        pot4[6] = new Team("47", "比尔森胜利", "捷克");
        pot4[7] = new Team("48", "海法马卡比", "以色列");
    }

    public List<Team[][]> getAllPossibilities() {
        List<Team[][]> result = new LinkedList<>();

        Permutations permutations = new Permutations();
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7};
        List<List<Integer>> permuteList = permutations.permute(nums);

        boolean valid;
        List<int[][]> possibleResultFor2Teams = new LinkedList<>();
        for (List<Integer> list2 : permuteList) {
            valid = true;
            for (int i = 0; i < 8; i++) {
                if (pot1[i].country.equals(pot2[list2.get(i)].country)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                int[][] onePossible = new int[8][2];
                for (int i = 0; i < 8; i++) {
                    onePossible[i] = new int[]{i, list2.get(i)};
                }
                possibleResultFor2Teams.add(onePossible);
            }
        }

        System.out.println(possibleResultFor2Teams.size());

        List<int[][]> possibleResultFor3Teams = new LinkedList<>();
        int index1, index2, index3;
        int count = 0;
        for (int[][] resultFor2Team : possibleResultFor2Teams) {
            for (List<Integer> list3 : permuteList) {
                valid = true;
                for (int i = 0; i < 8; i++) {
                    index1 = resultFor2Team[i][0];
                    index2 = resultFor2Team[i][1];
                    index3 = list3.get(i);
                    if (pot1[index1].country.equals(pot3[index3].country)
                        || pot2[index2].country.equals(pot3[index3].country)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    int[][] onePossible = new int[8][3];
                    for (int i = 0; i < 8; i++) {
                        onePossible[i] = new int[]{resultFor2Team[i][0], resultFor2Team[i][1], list3.get(i)};
                    }
                    possibleResultFor3Teams.add(onePossible);
                }
            }
            count++;
            if (count % 10 == 0) System.out.println("Progress: " + count);
        }

        System.out.println(possibleResultFor3Teams.size());

        return result;
    }

    public static void main(String[] args) {
        UCLGroupStageDraw draw = new UCLGroupStageDraw();
        draw.init();
        long start = System.currentTimeMillis();
        draw.getAllPossibilities();
        System.out.println("time cost: " + (System.currentTimeMillis() - start));
    }
}
