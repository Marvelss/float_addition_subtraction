import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Start {
    public static boolean isSpill=false;//判断结果溢出
    //list转字符串
    public static String listToString(ArrayList<Integer> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
            stringBuffer.append(list.get(i));
        }
        return stringBuffer.toString();
    }
    //十进制转化二进制
    public static ArrayList<Integer> inputNum(int num1){
        ArrayList<Integer> list = new ArrayList<Integer>();//存放二进制数
        int t = 0;  //用来记录位数
        int bin = 0; //用来记录最后的二进制数
        int r = 0;  //用来存储余数
        while(num1 != 0){
            r = num1 % 2;
            num1 = num1 / 2;
            bin += r * Math.pow(10,t);
            t++;
            list.add(r);
        }
        Collections.reverse(list);
        return list;
    }
    //比较两个二进制数的个数，得到最大位数
    public static int comparedLength(int a,int b){
        return Math.max(a, b);
    }
    //二进制补满最大位
    public static ArrayList<Integer> getFullNum(ArrayList<Integer> list,int comparedLength){
        ArrayList<Integer> list1 = new ArrayList<>();
        int[] arr=new int[list.size()];//0填入补满位,默认8位，comparedLength比较位
        for(int i=0;i<list.size();i++){
                arr[i]=list.get(i);
        }
        for (int i=0;i<arr.length;i++){
            list1.add(arr[i]);
        }
        Collections.reverse(list1);//集合逆序
        return list1;
    }
    //正负符号拼接原有二进制数
    public static String getFullNum_next(ArrayList<Integer> list,String isPos) {
//        System.out.println(list);
//        判断正不变；负就转换
        if(isPos.equals("1,")){
            getMin(list);
            Collections.reverse(list);
        }
//        String str2 =isPos+str1;
//        System.out.println(list);
        ArrayList<Integer> list1=getFull8Num(list);
        return listToString(list1);
    }
    //判断两数正负符号
        public static String isPos(int a,int b){
            if(a+b>0){
                return "0 ";
            }else {
                return "1 ";
            }
        }
        //判断一个数是否正数
    public static String isPos(int a){
        if(a>0){
            return "0,";
        }else {
            return "1,";
        }
    }
        //负数的原码转化补码
    public static void getMin(ArrayList<Integer> list){
        //负数的原码取反
        Collections.reverse(list);
        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
            if(list.get(i)==0){
                list.set(i,1);
            }else {
                list.set(i,0);
            }

        }
//        System.out.println(list);
        //处理+1进位
        int j=list.size()-1;
        while (j>=0){
                if(list.get(j)+1==2){
                    list.set(j,0);
                    j--;
                }
                else {
                    list.set(j,list.get(j)+1);
                    return;
                }
            }
    }

//    两数相加
    public static void add(String num1, String num2,ArrayList<Integer> result){
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for(int i=0;i<num1.length();i++){
            result.add(0);
        }
        for(int i=0;i<num1.length();i++){
            list1.add(Integer.parseInt(num1.substring(i,i+1)));
        }
        for(int i=0;i<num2.length();i++){
            list2.add(Integer.parseInt(num2.substring(i,i+1)));
        }

//        处理+1进位
        int j=result.size()-1;
        boolean isAdd=true;
        while (j>=0){
            if(list1.get(j)+list2.get(j)>=2){
                isAdd=true;
                int i=j;
                int first=1;
                while (isAdd){
                    int flag=1;
                    try {
                        if (list1.get(i)+list2.get(i)+flag==3) {
                            if(first==1){
                                result.set(i, 0);
                                i--;
                                first++;
                            }else {
                                result.set(i, 1);
                                i--;
                                first++;
                            }
                        }
                        else if (list1.get(i)+list2.get(i)+flag==2){
                            result.set(i, 0);
                            i--;
                            first++;
                        }
                        else if(list1.get(i)+list2.get(i)+flag==1){
                            result.set(i,1);
                            isAdd=false;
//                            System.out.println(i);
                        }
                    }
                    catch (Exception e){
//                        System.out.println("溢出");
                        isSpill=true;
                        return;
                    }
                }
                j=i-1;
                continue;
            }
            else if(list1.get(j)+list2.get(j)==1){
                result.set(j, 1);
               j--;
            }
            else {
                result.set(j,0);
                j--;
            }
        }
    }
    //二进制补满最大位
    public static ArrayList<Integer> getFull8Num(ArrayList<Integer> list){
        ArrayList<Integer> list1 = new ArrayList<>();
        int[] arr=new int[8];//0填入补满位,默认8位，comparedLength比较位
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i);
        }
        for (int i=0;i<arr.length;i++){
            list1.add(arr[i]);
        }
        Collections.reverse(list1);//集合逆序
        return list1;
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int num1 = scanner.nextInt();//输入一个十进制数字
            int num2 = scanner.nextInt();//输入一个十进制数字
            ArrayList<Integer> add_result = new ArrayList<>();//存放两数相加结果
            String result_isPos =isPos(num1,num2);//两数相加结果是否为正数
            String num3 = isPos(num1);//判断正负
            String num4 = isPos(num2);

            ArrayList<Integer> list1 = inputNum(Math.abs(num1));
            ArrayList<Integer> list2 = inputNum(Math.abs(num2));
            int comparedLength =comparedLength(list1.size(),list2.size());

            ArrayList<Integer> list3 = getFullNum(list1,comparedLength);
            ArrayList<Integer> list4 = getFullNum(list2,comparedLength);
            String num5= getFullNum_next(list3,num3);
            String num6 = getFullNum_next(list4,num4);
            add(num5,num6,add_result);
            System.out.println(num5);
            System.out.println(num6);
            String add_result_reveal=result_isPos+listToString(add_result);
            //溢出判断
            if(!isSpill){
                System.out.println(add_result_reveal);//两数相加结果
            }else {
                System.out.println("结果溢出");
                isSpill=false;
            }
        }
    }
}
