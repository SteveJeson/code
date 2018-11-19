package wind;

public enum AlarmType {
    GNSSNOTCONNECT(5, "GNSS天线未接或被剪断"),GNSSCIRCUIT(6, "GNSS天线短路"),UNDERVOLTAGE(7, "终端主电源欠压"),
    POWERDOWN(8, "终端主电源掉电"),UNLAWOFFSET(28, "车辆非法位移"),SHAKE(29, "震动预警");

    private int value;
    private String desc;

    AlarmType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    /**
     * 根据值返回名称
     * @param value
     * @return
     */
    public static AlarmType getStatusByValue(int value){
        for(AlarmType status : AlarmType.values()){
            if(status.getValue() == value){
                return status;
            }
        }
        return null;
    }


    /**
     * 根据值返回描述
     * @param value
     * @return
     */
    public static String getDescByValue(int value){
        return getStatusByValue(value).getDesc();
    }

}
