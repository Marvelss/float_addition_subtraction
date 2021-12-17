import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Start {
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
        int b;//得到一位二进制
        while (num1>0){
            b=num1%2;
            num1=num1/2;
            list.add(b);
        }
        return list;
    }
    //比较两个二进制数的个数，得到最大位数
    public static int comparedLength(int a,int b){
        return Math.max(a, b);
    }
    //二进制补满最大位
    public static ArrayList<Integer> getFullNum(ArrayList<Integer> list,int comparedLength){
        ArrayList<Integer> list1 = new ArrayList<>();
        int[] arr=new int[comparedLength];//用0填入补满位
        for(int i=0;i<list.size();i++){
                arr[i]=list.get(i);
        }
        for (int i=0;i<arr.length;i++){
            list1.add(arr[i]);
        }
        Collections.reverse(list1);//集合逆序
        return list1;
    }
    //正负符号转换
    public static String getFullNum_next(ArrayList<Integer> list,String isPos) {
        String str1 = listToString(list);
        String str2 =isPos+str1;
        return str2;
    }
        public static String isPos(int a){
            if(a>0){
                return "1,";
            }else {
                return "0,";
            }
        }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();//输入一个十进制数字
        int num2 = scanner.nextInt();//输入一个十进制数字
        String num3 = isPos(num1);//判断正负
        String num4 = isPos(num2);

        ArrayList<Integer> list1 = inputNum(Math.abs(num1));
        ArrayList<Integer> list2 = inputNum(Math.abs(num2));
        int comparedLength =comparedLength(list1.size(),list2.size());

        ArrayList<Integer> list3 = getFullNum(list1,comparedLength);
        ArrayList<Integer> list4 = getFullNum(list2,comparedLength);
        System.out.println(getFullNum_next(list3,num3));
        System.out.println(getFullNum_next(list4,num4));
    }
}
