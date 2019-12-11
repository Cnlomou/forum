package com.zykj.forum.util;

public class VerityCode {

    public static Long getCode(Long low,Long hight){
        Integer lowBit=low.toString().toCharArray().length;
        Integer hightBit=hight.toString().toCharArray().length;
        if(lowBit>hightBit)
            throw new IllegalStateException("low number must lt hight");
        while(true){
            int bit = (int) (Math.random() * (hightBit - lowBit));
            Long code = getCode(bit+lowBit);
            if(code>=low&&code<=hight)
                return code;
        }
    }
    public static Long getCode(Integer bit){
        long res=0;
        for(int i=0;i<bit;i++ ){
            res*=10;
            res+=Math.random()*10;
        }
         return res;
    }
    public static Long getCode(Long low,Integer bit){
        if(String.valueOf(low).toCharArray().length>bit)
            throw new IllegalStateException("low number must bt bit");
        Long code = getCode(bit);
        while (true)
            if(code>=low)
                return code;
    }
}
