package module;

/**
 * 功能：基金
 * -------------------------------------------------------------------------------------------------
 * 创建者：@author 林钦宏
 * -------------------------------------------------------------------------------------------------
 * 创建日期：2019-03-26
 * -------------------------------------------------------------------------------------------------
 * 更新历史
 * 编号|更新日期|更新人|更新内容
 */
public class SheepFund {

    /**
     * 基金名称
     */
    private String name;

    /**
     * 基金代码
     */
    private String code;

    /**
     * 单位净值
     */
    private String netWorth;

    /**
     * 升幅
     */
    private String increase;

    public static String DEFAULT_NAME = "无法获取基金名称";
    public static String DEFAULT_CODE = "无法获取基金代码";
    public static String DEFAULT_NET_WORTH = "无法获取单位净值";
    public static String DEFAULT_INCREASE = "无法获取基金升幅";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(String netWorth) {
        this.netWorth = netWorth;
    }

    public String getIncrease() {
        return increase;
    }

    public void setIncrease(String increase) {
        this.increase = increase;
    }

    @Override
    public String toString() {
        return "SheepFund{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", netWorth='" + netWorth + '\'' +
                ", increase='" + increase + '\'' +
                '}';
    }
}
