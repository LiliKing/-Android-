package work.frame.com.hzcitizenframework.utils.string;

import android.annotation.SuppressLint;
import android.widget.EditText;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String EMPTY_STRING = "";
    public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"; // 长日期格式
    final static String PLEASE_SELECT = "请选择...";

    /**
     * 不为空 返回真
     *
     * @param str 字符串
     * @return 如果字符串不为空且长度大于1 返回真 ，其他返回假
     */
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().equals(EMPTY_STRING);
    }

    /**
     * 如果为空 返回真
     *
     * @param str 字符串
     * @return 如果为空或长度等于零，返回真，其他返回假
     */
    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 去掉空格不为空 返回真
     *
     * @param str 字符串
     * @return 如果字符串不为空且去掉空格长度大于1 返回真 ，其他返回假
     */
    public static boolean isNotTrimBlank(String str) {
        return str != null && !str.trim().equals(EMPTY_STRING);
    }

    /**
     * 去掉空格
     * \n 回车(\u000a)  \t 水平制表符(\u0009)  \s 空格(\u0008) \r 换行(\u000d)
     */
    public static String trimBlank(String str) {
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ' ') {
                    if (i == 0) {
                        str = str.substring(1, str.length());
                        i = -1;
                    } else if (i == str.length() - 1) {
                        str = str.substring(0, str.length() - 1);
                    } else {
                        str = str.substring(0, i) + str.substring(i + 1, str.length());
                        i = i - 1;
                    }
                }
            }
//            Pattern p = Pattern.compile("\\s");
//            Matcher m = p.matcher(str);
//            str = m.replaceAll("");

//            if (str.contains(" ")) { // 空格
//                str = str.replace(" ","");
//            }
        } else {
            str = "";
        }
        return str;
    }

    public static boolean empty(Object o) {
        return o == null || "".equals(o.toString().trim())
                || "null".equalsIgnoreCase(o.toString().trim())
                || "undefined".equalsIgnoreCase(o.toString().trim())
                || PLEASE_SELECT.equals(o.toString().trim());
    }

    public static boolean notEmpty(Object o) {
        return o != null && !"".equals(o.toString().trim())
                && !"null".equalsIgnoreCase(o.toString().trim())
                && !"undefined".equalsIgnoreCase(o.toString().trim())
                && !PLEASE_SELECT.equals(o.toString().trim());
    }

    /**
     * 字符串为空，或去掉空格为空，则返回真
     *
     * @param str 字符串
     * @return 如果字符串为空, 或去掉空格长度为0, 返回真，其他返回假
     */
    public static boolean isTrimBlank(String str) {
        return str == null || str.trim().equals(EMPTY_STRING);
    }

    /**
     * 处理空字符串
     *
     * @param str
     * @return String
     */
    public static String doEmpty(String str) {
        return doEmpty(str, "");
    }

    /**
     * 处理空字符串
     *
     * @param str
     * @param defaultValue
     * @return String
     */
    public static String doEmpty(String str, String defaultValue) {
        if (str == null || str.equalsIgnoreCase("null")
                || str.trim().equals("") || str.trim().equals("－请选择－")) {
            str = defaultValue;
        } else if (str.startsWith("null")) {
            str = str.substring(4, str.length());
        }
        return str.trim();
    }

    /**
     * 首字母大写
     *
     * @param str 要转换的字符串
     * @return 首字母大写的字符串
     */
    @SuppressLint("DefaultLocale")
    public static String capFirstUpperCase(String str) {
        if (isBlank(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str 要转换的字符串
     * @return 首字母小写的字符串
     */
    @SuppressLint("DefaultLocale")
    public static String capFirstLowerCase(String str) {
        if (isBlank(str)) {
            return str;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 小写转大写
     * */
    public static void lowerToUpper(String text, EditText edt){
        if(StringUtil.isNotBlank(text)){
            boolean hasLower = false;
            for(int i= 0;i<text.length();i++){
                char lowChar = text.charAt(i);
                // 判断输入的文字是否是小写a-z
                if(lowChar >= 97 && lowChar <= 122){
                    hasLower = true;
                    break;
                }
            }
            if(hasLower){
                // 小写转大写
                edt.setText(text.toUpperCase());
                // 设置EditText光标位置
                edt.setSelection(text.length());
            }
        }
    }

    /**
     * 是否是手机字符串
     *
     * @param str
     * @return
     */
    public static boolean isMobileNumber(String str) {
//        Pattern p = Pattern.compile("^((\\+?86)|((\\+86)))?1\\d{10}$");
        Pattern p = Pattern.compile("^1(3[0-9]|5[0-35-9]|8[0125-9])\\d{8}$");
        Pattern p1 = Pattern.compile("^1(34[0-8]|(3[5-9]|5[017-9]|8[2345678])\\d)\\d{7}$");
        Pattern p2 = Pattern.compile("^1(3[0-9]|5[0-35-9]|8[0125-9])\\d{8}$");
        Pattern p3 = Pattern.compile("^1(3[0-9]|5[0-35-9]|8[0125-9])\\d{7}$");
        return p.matcher(str).matches()
                || p1.matcher(str).matches()
                || p2.matcher(str).matches()
                || p3.matcher(str).matches();
    }

    /**
     * 是否是固定电话字符串
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null, p3 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的(带-)
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
        p3 = Pattern.compile("^[0][1-9]{2,3}[0-9]{5,10}$"); // 验证带区号的(不带-)
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
            if (!b) {
                m = p3.matcher(str);
                b = m.matches();
            }
        } else {
            m = p2.matcher(str);
            b = m.matches();

        }
        return b;
    }

    public static boolean checkMailAddress(String sText) {
        boolean rs = false;
        if (sText != null && sText.length() > 0) {
            Pattern pattern = Pattern
                    .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
            Matcher matcher = pattern.matcher(sText);
            if (matcher.find()) {
                rs = true;
            }
        }
        return rs;
    }

    public static boolean checkMobilePhone(String sText) {
        boolean rs = false;
        if (sText != null && sText.length() > 0) {
            Pattern pattern = Pattern
                    .compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
            Matcher matcher = pattern.matcher(sText);
            if (matcher.find()) {
                rs = true;
            }
        }
        return rs;
    }

    /**
     * 使用Webkaifa/zzbds' target='_blank'>正则表达式进行表单验证
     */
    public static boolean check(String str, String regex) {
        boolean flag = false;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证座机号码和传真号码. 区号有3-4位（第一位数字是0，后面有2位或3位数字）； 然后可以以横杠连接（横杠可有可无）后面的7-8位电话号码，
     * 若后面还有分机号（1-4位数字），则前面加横杠连接。
     *
     * @param fax
     * @return
     */
    public static boolean isPhoneOrFax(String fax) {
        String regex = "^(0\\d{2}(-)?\\d{7,8}(-\\d{1,4})?)|(0\\d{3}(-)?\\d{7,8}(-\\d{1,4})?)$";
        return check(fax, regex);
    }

    /**
     * 验证QQ号码： QQ号码是阿拉伯数字0-9组成，开头第一位不能是0，剩下的随意，最短QQ号码5位。这里限制最长为21位。
     *
     * @param QQ
     * @return
     */
    public static boolean isQQ(String QQ) {
        String regex = "^[1-9][0-9]{4,20}$";
        return check(QQ, regex);
    }

    // 验证邮政编码
    public static boolean isPostCode(String post) {
        if (post.matches("[1-9]\\d{5}(?!\\d)")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否是身份证号码
     *
     * @param str
     * @return
     */
    public static boolean isIdCardNumber(String str) {
        String isIDCard = "";
        if (str.length() == 15) {
            isIDCard = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        } else {
            isIDCard = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";
        }
        Pattern p = Pattern
                .compile(isIDCard);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断是否是Email
     *
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern emailPattern = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(email);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 确认密码对比
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isConfirmPassword(String str1, String str2) {
        if (str1 == null || str2 == null)
            return false;
        return str1.equals(str2);
    }

    /**
     * 隐藏手机号码中间4位, example: 185****6666
     *
     * @param phone
     * @return
     */
    public static String hiddenPhoneNum(final String phone) {
        if (isMobileNumber(phone)) {
            char[] mobile = phone.toCharArray();
            for (int i = 3; i < 7; i++) {
                mobile[i] = '*';
            }
            return String.valueOf(mobile);
        }
        return "";
    }

    /**
     * 隐藏身份证号码中间, example: 4******************5
     */
    public static String hiddenIdCardNum(final String idCard) {
        char[] mobile = idCard.toCharArray();
        for (int i = 1; i < mobile.length - 1; i++) {
            mobile[i] = '*';
        }
        return String.valueOf(mobile);
    }

    /**
     * 只显示银行卡号后四位, example: ******************5232
     */
    public static String hiddenBankIdCardNum(final String idCard) {
        char[] mobile = idCard.toCharArray();
        for (int i = 0; i < mobile.length - 4; i++) {
            mobile[i] = '*';
        }
        return String.valueOf(mobile);
    }

    /**
     * 隐藏账户号码的中间几位，只显示前三位和后四位, example: 882202010000201 ---> 88220********201
     *
     * @param str
     * @return
     */
    public static String hiddenSevralNum(final String str) {
        char[] number = str.toCharArray();
        int len = number.length;
        for (int i = 3; i < len - 4; i++) {
            number[i] = '*';
        }
        return String.valueOf(number);
    }

    /**
     * 隐藏email的中间几位，只显示@符号前三位, example: 13849620635@126.com --->
     * 126********@126.com
     *
     * @param str
     * @return
     */
    public static String hiddenEmail(final String str) {
        if (null == str) {
            return null;
        }
        char[] email = str.toCharArray();
        int len = email.length;
        for (int i = 3; i < len; i++) {
            if (email[i] == '@') {
                break;
            }
            email[i] = '*';
        }
        return String.valueOf(email);
    }

    /**
     * 输入的字符串每4位隔开并添加空格（比如银行卡号码等）
     */
    public static String add4blank(String str) {
        str = str.replace(" ", "");
        int strLength = str.length() / 4;
        String temp = "";
        for (int i = 0; i < strLength; i++) {
            temp += str.substring(i * 4, (i + 1) * 4);
            temp += " ";
        }
        temp += str.substring(strLength * 4);
        return temp;
    }

    /**
     * 手机号码3 4 4格式, example: 185 6666 6666
     */
    public static String addmobileblank(String str) {
        if (str.replace(" ", "").length() != 11)
            return str;
        String temp = "";
        temp += str.subSequence(0, 3);
        temp += ' ';
        temp += str.substring(3, 7);
        temp += ' ';
        temp += str.substring(7);
        return temp;
    }

    /**
     * 日期格式转换（Str转Str） 2014-05-06 转为 20140506
     */
    public static String formatDate2(String str) {
        return str.replace("-", "");
    }

    /**
     * 日期格式转换（Str转Str） 20140506转为2014-05-06
     */
    public static String formatDate(String str) {
        if (str.replace("-", "").length() != 8)
            return str;
        String temp = "";
        temp += str.subSequence(0, 4);
        temp += '-';
        temp += str.substring(4, 6);
        temp += '-';
        temp += str.substring(6);
        return temp;
    }

    /**
     * 毫秒转为日期 yyyy-MM-dd
     */
    public static String secondToDate(long time) {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (time != 0) {
            calendar.setTimeInMillis(time);
            return formatter.format(calendar.getTime());
        } else {
            return "";
        }
    }

    /**
     * 毫秒转为日期 yyyyMMddHHmmss
     */
    public static String secondToDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(calendar.getTime());
    }

    // yyyyMMdd
    public static String getFormatDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String formatDate = format.format(date);
        return formatDate;
    }

    // HHmmss
    public static String getFormatTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        String formatTime = format.format(date);
        return formatTime;
    }

    // 给定规则转换时间
    public static String getSysFormatTime(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String formatTime = format.format(date);
        return formatTime;
    }

    public static String secondsToData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000; // 毫秒
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    /**
     * 得到本月的第一天
     *
     * @return
     */
    public static String getMonthFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH));

        return sdf.format(calendar.getTime());
    }

    /**
     * 根据给定时间得到最后一天(yyyy-MM-dd)
     *
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    /**
     * 根据当前时间得到本月的最后一天
     *
     * @return
     */
    public static String getMonthLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }

    /**
     * 时间格式转换（Str转Str） 123312转为12:33
     */
    public static String formatTime(String str) {
        String temp = "";
        temp += str.subSequence(0, 2);
        temp += ':';
        temp += str.substring(2, 4);
        return temp;
    }

    /**
     * 将长日期格式的字符串转换为长整型 1970-09-01 12:00:00
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static long convert2long(String date) {
        try {
            if (isNotBlank(date)) {
                SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT);
                return sf.parse(date).getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    // 文字显示排版（标点符号处理）
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static boolean num(Object o) {
        int n = 0;
        try {
            n = Integer.parseInt(o.toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (n > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean decimal(Object o) {
        double n = 0;
        try {
            n = Double.parseDouble(o.toString().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (n > 0.0) {
            return true;
        } else {
            return false;
        }
    }

    // 保留小数点后两位数字，但不进行四舍五入
    public static String sub2DecimalPlaceNoRoundOff(String strDouble) {
        String str;
        int position = strDouble.indexOf("."); // 计算小数点的位置
        if (position != -1) {
            if ((strDouble.length() - 1 - position) >= 2) {
                // 如果小数点后多于两位
                strDouble = strDouble.substring(0, position + 3);
            } else {
                // 小数点后不足两位补0
                DecimalFormat df = new DecimalFormat("0.00");
                strDouble = df.format(strDouble);
            }
        }
        return strDouble;
    }

    // 转换成米 222----->222米2222----->2.2千米
    public static String str2m(String m) {
        if ("".equals(m)) {
            return "";
        }
        float distance = Float.parseFloat(m);
        if (distance < 999.5) {
            return Math.round(distance) + "m";
        }
        float cc = (distance / 100); //得到10.51==
        int d = Math.round(cc);//四舍五入
        float e = d / (float) 10;
        return e + "km";
    }

    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(String amount) {
        return changeF2Y(amount, false);
    }

    public static String changeF2Y(String amount, boolean addPlusSign) {
        if (amount == null) {
            return "";
        }
        if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
            return "";
        }

        int flag = 0;
        String amString = amount.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }
                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            if (addPlusSign)
                return "-" + result.toString() + "元";
            else
                return "-" + result.toString() + "元";
        } else {
            if (addPlusSign)
                return "+" + result.toString() + "元";
            else return result.toString();
        }
    }


    /**
     * 给JID返回用户名
     *
     * @param Jid
     * @return
     */
    public static String getUserNameByJid(String Jid) {
        if (empty(Jid)) {
            return null;
        }
        if (!Jid.contains("@")) {
            return Jid;
        }
        return Jid.split("@")[0];
    }

    /**
     * 根据给定的时间字符串，返回月 日 时 分 秒
     *
     * @param allDate like "yyyy-MM-dd hh:mm:ss SSS"
     * @return
     */
    public static String getMonthTomTime(String allDate) {
        return allDate.substring(5, 19);
    }

    /**
     * 根据给定的时间字符串，返回月 日 时 分 月到分钟
     *
     * @param allDate like "yyyy-MM-dd hh:mm:ss SSS"
     * @return
     */
    public static String getMonthTime(String allDate) {
        return allDate.substring(5, 16);
    }

    /**
     * 返回一个1-10000的随机整数
     *
     * @return
     */
    public static String getRandomIntStr() {
        int random = (int) (Math.random() * 10000 + 1); // 产生一个 1-10000的随机整数
        return String.valueOf(random);
    }

    /**
     * 流水号
     *
     * @return
     */
    public static String getReqSeq() {
        return System.currentTimeMillis() + getRandomIntStr(); // 当前时间拼接一个 1-10000的随机整数
    }


    // 签名文件编码进制转换
    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString().substring(8, 24);
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

}
