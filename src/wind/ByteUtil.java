package wind;

public class ByteUtil {

    /**
     * 将整型数字转换为二进制字符串，一共32位，不舍弃前面的0
     * @param number 整型数字
     * @return 二进制字符串
     */
    public static String get32BitBinStrFromInt(int number) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < 32; i++){
            sBuilder.append(number & 1);
            number = number >>> 1;
        }
        return sBuilder.reverse().toString();
    }

    public static void parseVehicleStatus(String vehicleStatus) throws Exception{
        String bst = ByteUtil.get32BitBinStrFromInt(Integer.parseInt(vehicleStatus));
        bst = new StringBuffer(bst).toString();//反转字符串，以 低位-》高位的顺序读取解析状态位
        System.out.println("二进制形式：" + bst);
        for (int i = 0; i < bst.length(); i++) {
            String str = bst.substring(i, i +1);
            switch (i){
                case 0: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"ACC 关":"ACC 开"));break;
                case 1: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"未定位":"已定位"));break;
//                case 2: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"北纬":"南纬"));break;
//                case 3: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"东经":"西经"));break;
//                case 4: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"运营状态":"停运状态"));break;
//                case 5: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"经纬度未经保密插件加密":"经纬度已经保密插件加密"));break;
//                case 6: str = bst.substring(i, i + 2);System.out.println("第 6-7 位：" + str + "=======> 保留位");break;
//                case 8: str = bst.substring(i, i + 2);System.out.println("第 8-9 位：" + str + "=======>" + ("00".equals(str)?"空车":"01".equals(str)?"半载":"10".equals(str)?"保留":"11".equals(str)?"满载":"未知数据"));break;
                case 10: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"车辆油路正常":"车辆油路断开"));break;
                case 11: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"车辆电路正常":"车辆电路断开"));break;
//                case 12: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"车门解锁":"车门加锁"));break;
//                case 13: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"门1 关（前门）":"门1 开（前门）"));break;
//                case 14: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"门2 关（中门）":"门2 开（中门）"));break;
//                case 15: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"门3 关（后门）":"门3 开（后门）"));break;
//                case 16: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"门4 关（驾驶席门）":"门4 开（驾驶席门）"));break;
//                case 17: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"门5 关（自定义）":"门5 开（自定义）"));break;
//                case 18: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"未使用GPS 卫星进行定位":"使用GPS 卫星进行定位"));break;
//                case 19: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"未使用北斗卫星进行定位":"使用北斗卫星进行定位"));break;
//                case 20: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"未使用GLONASS 卫星进行定位":"使用GLONASS 卫星进行定位"));break;
//                case 21: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"未使用Galileo 卫星进行定位":"使用Galileo 卫星进行定位"));break;
                case 22: System.out.println("第 " + i + " 位：" + str + "=======>" + ("0".equals(str)?"已撤防":"已设防"));break;
                default:
//                    System.out.println("第 " + i + " 位：" + str);
            }
        }
    }

    public static void parseAlarmStatus(String alarm) throws Exception{
        String alarmStatus = ByteUtil.get32BitBinStrFromInt(Integer.valueOf(alarm,16));//100 20000000  20000100
        alarmStatus = new StringBuffer(alarmStatus).reverse().toString();
        System.out.println("二进制形式：" + alarmStatus);
        for(int i = 0;i < alarmStatus.length();i++){
            String str = alarmStatus.substring(i, i +1);
            switch (i){
                case 5: System.out.println("第 " + i + " 位：" + str + "=======>" + ("1".equals(str)?"GNSS天线未接或被剪断":"正常"));break;
                case 6: System.out.println("第 " + i + " 位：" + str + "=======>" + ("1".equals(str)?"GNSS天线短路":"正常"));break;
                case 7: System.out.println("第 " + i + " 位：" + str + "=======>" + ("1".equals(str)?"终端主电源欠压":"正常"));break;
                case 8: System.out.println("第 " + i + " 位：" + str + "=======>" + ("1".equals(str)?"终端主电源掉电":"正常"));break;
                case 28: System.out.println("第 " + i + " 位：" + str + "=======>" + ("1".equals(str)?"车辆非法位移":"正常"));break;
                case 29: System.out.println("第 " + i + " 位：" + str + "=======>" + ("1".equals(str)?"震动预警":"正常"));break;
                case 30: System.out.println("第 " + i + " 位：" + str + "=======>" + ("1".equals(str)?"侧翻预警":"正常"));break;
            }
        }
    }
}
