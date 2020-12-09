package kylin;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class network extends BaseServlet {
    private ResultInfo info = new ResultInfo();
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        info.setFlag(true);
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        network_bean bean = new network_bean();
        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        String[] wangluo = bean.getUsername().split("\\.");
        String[] ziwang = bean.getPassword().split("\\.");
        String i1 = wangluo[0];
        String i2 = wangluo[1];
        String i3 = wangluo[2];
        String i4 = wangluo[3];

        String s1 = ziwang[0];
        String s2 = ziwang[1];
        String s3 = ziwang[2];
        String s4 = ziwang[3];
        int numm = Integer.parseInt(wangluo[0]);
        //子网数的计算
        String aString =Eight(Integer.parseInt(s1))+Eight(Integer.parseInt(s2))+Eight(Integer.parseInt(s3))+Eight(Integer.parseInt(s4));
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
        info.setZiwangshu("子网数："+ ziwangshu);
        //主机数的计算
        String aString2 =Eight(Integer.parseInt(s1))+Eight(Integer.parseInt(s2))+Eight(Integer.parseInt(s3))+Eight(Integer.parseInt(s4));
        int num2 = 0;
        for (int i = 0; i < aString2.length(); i++) {
            if (aString2.charAt(i)=='1') {
                num2++;
            }
        }
        int zhujishu = (int)(Math.pow(2, 32-num2)-2);
        info.setZhujishu("主机数："+zhujishu);

        if (numm > 0 && numm <= 127){
            info.setLiebie("类别：A类");
            int hostnum = HostnumB(s1,s2,s3,s4);
            int subnum = SubnetB(s1,s2,s3,s4);
            int n=0;
            int zu=256/subnum;
            String save = "";
            String sa = "";
            for (int i = 0; i < subnum; i++) {
                save=save+"第"+(i+1)+"个子网IP范围："+i1+"."+n+"."+0+"."+1+"~"+i1+"."+(n+zu-1)+"."+255+"."+254+"\n";
                sa=sa+"第"+ (i+1)+"个广播地址："+i1+"."+(n+zu-1)+"."+255+"."+255+"\n";
                n = n+zu;
            }
            info.setZiwangIP(save);
            info.setGuangpodizhi(sa);
        }else if (numm > 127 && numm < 192){
            info.setLiebie("类别：B类");
            int hostnum = HostnumB(s1,s2,s3,s4);
            int subnum = SubnetB(s1,s2,s3,s4);
            int n=0;
            int zu=256/subnum;
            //定义字符串save保存所有数据
            String sassve = "";
            String sss= "";;
            for (int i = 0; i < subnum; i++) {
                sassve=sassve+"第"+(i+1)+"个子网IP范围："+i1+"."+i2+"."+n+"."+1+"~"+i1+"."+i2+"."+(n+zu-1)+"."+254+"\n";
                sss=sss+"第"+ (i+1)+"个广播地址："+i1+"."+i2+"."+(n+zu-1)+"."+255+"\n";
                n = n+zu;
            }
            info.setZiwangIP(sassve);
            info.setGuangpodizhi(sss);
        }else if (numm >= 192 && numm < 224){
            info.setLiebie("类别：C类");
            int hostnum = HostnumB(s1,s2,s3,s4);
            int subnum = SubnetB(s1,s2,s3,s4);
            int n=1;
            //定义一个字符串来保存所有信息
            String saasdve = "";
            String saaaa = "";
            for (int i = 0; i < subnum; i++) {
                saasdve=saasdve+("第"+(i+1)+"个子网IP范围："+i1+"."+i2+"."+i3+"."+n+"~"+(n+hostnum-1))+"\n";
                saaaa=saaaa+("第"+ (i+1)+"个广播地址是："+i1+"."+i2+"."+i3+"."+(n+hostnum))+"\n";
                n = n+hostnum-1+3;
            }
            info.setZiwangIP(saasdve);
            info.setGuangpodizhi(saaaa);
        }
        String json = writeValueToString(info);
        //使用response写回页面
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);

    }


    //将十进制数换成八位的二进制数(字符串)
    public String Eight(int num) {
        String a = Integer.toBinaryString(num);
        while (a.length() < 8)
        {
            a = '0' + a;
        }
        return a;
    }
    //将二进制数转换成十进制的数
    public int Ten(String s){
        int x = 0;
        for(char c: s.toCharArray())
            x = x * 2 + (c == '1' ? 1 : 0);
        return x;
    }
    //子网数的计算
    public  int SubnetB(String s1,String s2,String s3,String s4){
        String aString =Eight(Integer.parseInt(s1))+Eight(Integer.parseInt(s2))+Eight(Integer.parseInt(s3))+Eight(Integer.parseInt(s4));
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
        return (int)(Math.pow(2, num));
    }
    //主机数的计算
    public  int HostnumB(String s1,String s2,String s3,String s4){
        String aString2 =Eight(Integer.parseInt(s1))+Eight(Integer.parseInt(s2))+Eight(Integer.parseInt(s3))+Eight(Integer.parseInt(s4));
        int num2 = 0;
        for (int i = 0; i < aString2.length(); i++) {
            if (aString2.charAt(i)=='1') {
                num2++;
            }
        }
        return (int)(Math.pow(2, 32-num2)-2);
    }
}
