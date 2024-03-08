package com.example.aya.demo.biz;

import com.example.aya.demo.dao.OrganYjdb;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tongji {
    public static void main(String[] args) throws IOException, ParseException {
        String textPath =
                "C:\\Users\\Administrator\\Desktop\\问题记录\\新建文件夹\\新建文件夹\\test.txt";
        File tp = new File(textPath);
        String textPath2 =
                "C:\\Users\\Administrator\\Desktop\\问题记录\\新建文件夹\\新建文件夹\\out.txt";
        String textPath3 =
                "C:\\Users\\Administrator\\Desktop\\问题记录\\新建文件夹\\新建文件夹\\out2.txt";
        //File tp2 = new File(textPath2);
        FileWriter tp2 = new FileWriter(textPath2);
        FileWriter tp3 = new FileWriter(textPath3);
        List<OrganYjdb> organYjdbList = new ArrayList<>();
        Set<String> organList = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(tp));
        String s = null;
        while( (s = br.readLine()) != null) {
            String str = s;
            str=str.replaceAll("\t",",");
            String[] split = str.split(",");
            if(split.length==7){
                OrganYjdb organYjdb = new OrganYjdb(split[0],split[1],split[2],split[3],split[4],split[5],split[6],"","");
                organYjdbList.add(organYjdb);
            }else if(split.length==8)
            {
                OrganYjdb organYjdb = new OrganYjdb(split[0],split[1],split[2],split[3],split[4],split[5],split[6],"","");
                organYjdbList.add(organYjdb);
            }else if(split.length==9){
                OrganYjdb organYjdb = new OrganYjdb(split[0],split[1],split[2],split[3],split[4],split[5],split[6],split[7],split[8]);
                organYjdbList.add(organYjdb);
                System.out.println("1");
            }else{
                int j=1/0;
            }

        }
        for (OrganYjdb o : organYjdbList) {
            organList.add(o.getOrganName());
        }
        List<List<OrganYjdb>> newOrganYjdbList = new ArrayList<List<OrganYjdb>>();
        for (String organName:organList){
            List<OrganYjdb> sameOrganYldbList = new ArrayList<>();
            for (OrganYjdb o : organYjdbList){
                if(organName.equals(o.getOrganName())){
                    sameOrganYldbList.add(o);
                }
            }
            newOrganYjdbList.add(sameOrganYldbList);
        }
        List<OrganYjdb> chuJiOrganYjdbList=new ArrayList<>();
        List<OrganYjdb> zhongJiOrganYjdbList=new ArrayList<>();
        List<OrganYjdb> gaoJiOrganYjdbList=new ArrayList<>();
        List<OrganYjdb> zhuanJiOrganYjdbList=new ArrayList<>();
        for (List<OrganYjdb> list:newOrganYjdbList){
            chuJiOrganYjdbList.clear();
            zhongJiOrganYjdbList.clear();
            gaoJiOrganYjdbList.clear();
            zhuanJiOrganYjdbList.clear();
            for ( OrganYjdb o: list) {
                if ("初级".equals(o.getType())){
                    chuJiOrganYjdbList.add(o);
                }else if("中级".equals(o.getType())){
                    zhongJiOrganYjdbList.add(o);
                }else if ("高级".equals(o.getType())){
                    gaoJiOrganYjdbList.add(o);
                }else{
                    zhuanJiOrganYjdbList.add(o);
                }
            }
            int size[]={chuJiOrganYjdbList.size(),zhongJiOrganYjdbList.size(),gaoJiOrganYjdbList.size(),zhuanJiOrganYjdbList.size()};
            int max=0;
            for(int i:size){
                if(max<i) {
                    max=i;
                }
            }
            String organName = list.get(0).getOrganName();
            String areaName = list.get(0).getAreaName();
            String hospitalType = list.get(0).getHosiptialType();
            String hospitalDate = list.get(0).getHosiptialDate();
            String NSYL1=" ";
            String NSYL2=" ";
            if (hospitalDate.isEmpty()){
                hospitalType=" ";
                hospitalDate=" ";
                NSYL1=" ";
                NSYL2=" ";
            }else {
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
                Date parse = sdf.parse(hospitalDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(parse);
                int year = calendar.get(Calendar.YEAR);
                if (year==2019){
                    if(hospitalType.isEmpty()){
                        hospitalType=" ";
                        hospitalDate=" ";
                        NSYL1=" ";
                        NSYL2=" ";
                    }else if("1".equals(hospitalType)||"2".equals(hospitalType)||"3".equals(hospitalType)||"5".equals(hospitalType)){
                        NSYL1="1";
                    }else if("0".equals(hospitalType)||"4".equals(hospitalType)){
                        NSYL2="1";
                    }else{
                        hospitalType=" ";
                        hospitalDate=" ";
                        NSYL1=" ";
                        NSYL2=" ";
                    }
                }else{
                    hospitalType=" ";
                    hospitalDate=" ";
                    NSYL1=" ";
                    NSYL2=" ";
                }
            }
            for(int i=0;i<max;i++){
                String chujiType;
                String chujiName;
                String chujiNum;
                if(chuJiOrganYjdbList.size()>i){
                    chujiType=chuJiOrganYjdbList.get(i).getType();
                    chujiName=chuJiOrganYjdbList.get(i).getEmpName();
                    chujiNum=chuJiOrganYjdbList.get(i).getNum();
                }else {
                    chujiType=" ";
                    chujiName=" ";
                    chujiNum=" ";
                }
                String zhongjiType;
                String zhongjiName;
                String zhongjiNum;
                if(zhongJiOrganYjdbList.size()>i){
                    zhongjiType=zhongJiOrganYjdbList.get(i).getType();
                    zhongjiName=zhongJiOrganYjdbList.get(i).getEmpName();
                    zhongjiNum=zhongJiOrganYjdbList.get(i).getNum();
                }else {
                    zhongjiType=" ";
                    zhongjiName=" ";
                    zhongjiNum=" ";
                }
                String gaojiType;
                String gaojiName;
                String gaojiNum;
                if(gaoJiOrganYjdbList.size()>i){
                    gaojiType=gaoJiOrganYjdbList.get(i).getType();
                    gaojiName=gaoJiOrganYjdbList.get(i).getEmpName();
                    gaojiNum=gaoJiOrganYjdbList.get(i).getNum();
                }else {
                    gaojiType=" ";
                    gaojiName=" ";
                    gaojiNum=" ";
                }
                String zhuanjiType;
                String zhuanjiName;
                String zhuanjiNum;
                if(zhuanJiOrganYjdbList.size()>i){
                    zhuanjiType=zhuanJiOrganYjdbList.get(i).getType();
                    zhuanjiName=zhuanJiOrganYjdbList.get(i).getEmpName();
                    zhuanjiNum=zhuanJiOrganYjdbList.get(i).getNum();
                }else {
                    zhuanjiType=" ";
                    zhuanjiName=" ";
                    zhuanjiNum=" ";
                }
                /*System.out.println(organName+"\t"+chujiType+"\t"+chujiName+"\t"+chujiNum+"\t"
                        +zhongjiType+"\t"+zhongjiName+"\t"+zhongjiNum+"\t"
                        +gaojiType+"\t"+gaojiName+"\t"+gaojiNum+"\t"
                        +zhuanjiType+"\t"+zhuanjiName+"\t"+zhuanjiNum+"\t"
                );*/
                tp2.write(organName+"\t"+areaName+"\t"+chujiType+"\t"+chujiName+"\t"+chujiNum+"\t"
                        +zhongjiType+"\t"+zhongjiName+"\t"+zhongjiNum+"\t"
                        +gaojiType+"\t"+gaojiName+"\t"+gaojiNum+"\t"
                        +zhuanjiType+"\t"+zhuanjiName+"\t"+zhuanjiNum+"\t"
                        +NSYL1+"\t"+NSYL2+"\t"+hospitalDate+ "\r\n");
                tp3.write(areaName+"\t"+organName+"\t" +
                        NSYL1+"\t"+(NSYL1.equals(" ")?"0":"100000")+ "\t"+
                        NSYL2+"\t"+(NSYL2.equals(" ")?"0":"500000")+"\t"+
                        chujiName+"\t"+chujiNum+"\t"+(chujiName.equals(" ")?"0":Float.parseFloat(chujiNum)*2480*0.02)+"\t"+
                        zhongjiName+"\t"+zhongjiNum+"\t"+(zhongjiName.equals(" ")?"0":Float.parseFloat(zhongjiNum)*2480*0.03)+"\t"+
                        gaojiName+"\t"+gaojiNum+"\t"+(gaojiName.equals(" ")?"0":Float.parseFloat(gaojiNum)*2480*0.05)+"\t"+
                        zhuanjiName+"\t"+zhuanjiNum+"\t"+(zhuanjiName.equals(" ")?"0":Float.parseFloat(zhuanjiNum)*2480*0.05)+"\r\n");
            }

        }

        tp2.flush();
        tp2.close();
        tp3.flush();
        tp3.close();
        br.close();
    }
}
