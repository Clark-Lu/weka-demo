package com.weka.demo.util;

/**
 * Created by changlu on 10/16/18.
 */
public class BigNumber {

    public BigNumber() {
    }

    public BigNumber(String value) {
        this.value = value;
    }

    private static final int digits = 10;

    private static final long divide = (long) Math.pow(10, digits);


    private String value;

    public BigNumber add(BigNumber bigNumber) {
        int largeLength = value.length();
        if (largeLength < bigNumber.getValue().length()) {
            largeLength = bigNumber.getValue().length();
        }
        int times = (int) Math.ceil(largeLength / 1.0 / digits);
        String resultStr = "";
        Result result = new Result();
        result.setCarry(0);
        for (int i = 0; i < times; i++) {
            int valueEnd = value.length() - i * digits;
            valueEnd = valueEnd < 0 ? 0 : valueEnd;
            int bigNumberEnd = bigNumber.getValue().length() - i * digits;
            bigNumberEnd = bigNumberEnd < 0 ? 0 : bigNumberEnd;
            result = this.add(value.substring(valueEnd - digits < 0 ? 0 : valueEnd - digits, valueEnd),
                    bigNumber.getValue().substring(bigNumberEnd - digits < 0 ? 0 : bigNumberEnd - digits, bigNumberEnd),
                    result.getCarry());
            resultStr = result.getResult() + resultStr;
        }
        if (result.getCarry() != 0) {
            resultStr = result.getCarry() + resultStr;
        }
        BigNumber number = new BigNumber(resultStr);
        return number;
    }


    public BigNumber subtract(BigNumber bigNumber) {
        boolean isNeg = false;
        if (bigNumber.getValue().length() > value.length()){
            isNeg = true;
        }else if (bigNumber.getValue().length() == value.length()){
            for (int i = 0; i < value.length(); i++) {
                String bs = bigNumber.getValue().substring(i,i+1);
                String as = value.substring(i,i+1);
                if (bs.equals(as)){
                    continue;
                }else if (Integer.valueOf(bs) > (Integer.valueOf(as))){
                    isNeg = true;
                    break;
                }else {
                    break;
                }
            }
        }
        String big = value;
        String small = bigNumber.getValue();
        if (isNeg){
            big = bigNumber.getValue();
            small = value;
        }
        
        int largeLength = big.length();
        if (largeLength < small.length()) {
            largeLength = small.length();
        }
        int times = (int) Math.ceil(largeLength / 1.0 / digits);
        String resultStr = "";
        Result result = new Result();
        result.setCarry(0);
        for (int i = 0; i < times; i++) {
            int valueEnd = big.length() - i * digits;
            valueEnd = valueEnd < 0 ? 0 : valueEnd;
            int bigNumberEnd = small.length() - i * digits;
            bigNumberEnd = bigNumberEnd < 0 ? 0 : bigNumberEnd;
            result = this.subtract(big.substring(valueEnd - digits < 0 ? 0 : valueEnd - digits, valueEnd),
                    small.substring(bigNumberEnd - digits < 0 ? 0 : bigNumberEnd - digits, bigNumberEnd),
                    result.getCarry());
            resultStr = result.getResult() + resultStr;
        }
        if (isNeg){
            resultStr = "-" + resultStr;
        }
        BigNumber number = new BigNumber(resultStr);
        return number;
    }

    private class Result {
        private int carry;
        private String result;

        public int getCarry() {
            return carry;
        }

        public void setCarry(int carry) {
            this.carry = carry;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    private Result add(String a, String b, int carry) {
        Result result = new Result();
        if (a.equals("")) {
            a = "0";
        }
        if (b.equals("")) {
            b = "0";
        }
        long c = Long.valueOf(a) + Long.valueOf(b) + carry;
        result.setCarry((int) (c / divide));
        String value = String.valueOf(c);
        if (a.length() == digits || b.length() == digits) {
            while (value.length() < digits) {
                value = "0" + value;
            }
        }
        if (result.getCarry() == 0) {
            result.setResult(value);
        } else {
            result.setResult(value.substring(1, value.length()));
        }
        return result;
    }

    private Result subtract(String a, String b, int carry) {
        Result result = new Result();
        if (a.equals("")) {
            a = "0";
        }
        if (b.equals("")) {
            b = "0";
        }
        long c = Long.valueOf(a) - Long.valueOf(b) + carry;
        if (c < 0) {
            result.setCarry(-1);
            c = divide + c;
        }
        String value = String.valueOf(c);
        if (a.length() == digits || b.length() == digits) {
            while (value.length() < digits) {
                value = "0" + value;
            }
        }else if (value.equals("0")){
            value = "";
        }
        result.setResult(value);
        return result;
    }

    public String getValue() {
        return value;
    }

}
