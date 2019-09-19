package bean;

public class Question {

    private int ID; // 题目id
    private int[] nums = new int[3];   // 随机数3个
    private int[] operationTypes = new int[2];   // 0为减，1为加，依次混合运算

    private int result = -2;    // 混合运算结果，初始化为-2避免与用户默认答案-1冲突

    public final int SCORE = 2; // 每道题的分数

    public Question() {
    }

    public Question(int ID, int[] nums, int[] operationTypes) {
        this.ID = ID;
        this.nums = nums;
        this.operationTypes = operationTypes;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int[] getNums() {
        return nums;
    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }

    public int[] getOperationTypes() {
        return operationTypes;
    }

    public void setOperationTypes(int[] operationTypes) {
        this.operationTypes = operationTypes;
    }

    public int getResult() {
        return result;
    }

    /**
     * 判断是否符合题目规则
     */
    public boolean canBeQuestion() {
        return calculateResult() >= 0;
    }


    /**
     * 计算混合运算结果
     *
     * @return 运算结果
     */
    public int calculateResult() {
        result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                result = nums[i];
                continue;
            }
            switch (operationTypes[i - 1]) {
                case 0: // -
                    result = result - nums[i];
                    break;
                case 1: // +
                    result = result + nums[i];
                    break;
            }
        }
        return result;
    }

    public void print() {
        System.out.print("题目" + ID + ": ");
        if (result < 0) {
            System.out.println("生成的题目有误！");
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                System.out.print(nums[i]);
                continue;
            }
            switch (operationTypes[i - 1]) {
                case 0: // -
                    System.out.print(" - " + nums[i]);
                    break;
                case 1: // +
                    System.out.print(" + " + nums[i]);
                    break;
            }
        }
        System.out.println();
//        System.out.println(" = " + result);
    }

    // 组装题目字串
    public String toString() {
        StringBuilder resultBuilder = new StringBuilder();
        if (result < 0) {
            return null;
        }
        resultBuilder.append(ID).append(") ");
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                resultBuilder.append(nums[i]);
                continue;
            }
            switch (operationTypes[i - 1]) {
                case 0: // -
                    resultBuilder.append(" - ").append(nums[i]);
                    break;
                case 1: // +
                    resultBuilder.append(" + ").append(nums[i]);
                    break;
            }
        }
        resultBuilder.append(" = ");
        return resultBuilder.toString();
    }

}
