package Test;

public class demo {
    public static void main(String[] args) {
        String aString ="11111111110000000000000000000000";
        int num=0;
        for (int i = 0; i < aString.length(); i++) {
            if (num < 9){
                if (aString.charAt(i)=='1') {
                    num++;
                }
            }else {
                num = 1;
                if (aString.charAt(i)=='1') {
                    num++;
                }
            }
        }
        int ziwangshu = (int)(Math.pow(2, num));
        System.out.println(ziwangshu);
//        double pow = Math.pow(2, 3);
//        System.out.println(pow);
//        String eight = Eight(451);
//        System.out.println(eight);
//        int ten = Ten("111000011");
//        System.out.println(ten);
    }
    public static String Eight(int num) {
        String a = Integer.toBinaryString(num);
        while (a.length() < 8)
        {
            a = '0' + a;
        }
        return a;
    }
    //将二进制数转换成十进制的数
    public static int Ten(String s){
        int x = 0;
        for(char c: s.toCharArray())
            x = x * 2 + (c == '1' ? 1 : 0);
        return x;
    }
}
