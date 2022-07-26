public class Main {
    public static String calc(String input) throws Exception {
        // Метод должен принимать строку с арифметическим выражением между двумя числами и возвращать строку с результатом их выполнения
        //System.out.println("[" + input + "]");
        String[] arr = input.split(" ");
        if(arr.length != 3){
            throw new Exception("Wrong number of operands");
        }

//        for (String i: arr) {
//            System.out.println(i);
//        }

        String resStr = "";
        boolean error = false;
        try {
            resStr = numArabian(arr);
        } catch(Exception err) {
            //System.out.println(err.getMessage());
            if(err.getMessage().equals("Error! Operands are above than 10")) {
                throw new Exception(err.getMessage());
            } else if(err.getMessage().equals("Division by zero")) {
                throw new Exception(err.getMessage());
            } else if(err.getMessage().equals("No such operation")) {
                throw new Exception(err.getMessage());
            } else {
                error = true;
            }
        }
        if (error) {
            try {
                resStr = numRome(arr);
                resStr = buildRomeString(resStr);
            } catch(Exception er) {
                //System.out.println(er.getMessage());
                throw new Exception(er.getMessage());
            }
        }
        //System.out.println("[" + resStr + "]");
        return resStr;
    }



    public static int operations(String oper, int a, int b) throws Exception {
        int result;
        switch (oper) {
            case "+" -> {
                result = a + b;
                //System.out.println("Сумма: " + result);
            }
            case "-" -> {
                result = a - b;
                //System.out.println("Разность: " + result);
            }
            case "*" -> {
                result = a * b;
                //System.out.println("Результат умножения: " + result);
            }
            case "/" -> {
                if (b == 0) {
                    throw new Exception("Division by zero");
                }
                result = a / b;
                //Задать исключение
                //System.out.println("Результат деления: " + result);
            }
            default ->
                //Здесь надо задать ИСКЛЮЧЕНИЕ
                    throw new Exception("No such operation");
        }
        return result;
    }




    public static String numArabian(String[] arr) throws Exception {
        int a, b;
        try {
            a = Integer.parseInt(arr[0]);
            b = Integer.parseInt(arr[2]);
            //NumberFormatException, если не число.
        } catch(NumberFormatException err) {
            //System.out.println("Операнды - не числа.");
            throw new Exception("");
        }
        if(a > 10 || b > 10) {
            throw new Exception("Error! Operands are above than 10");
        }
        try {
            int res = operations(arr[1], a, b);
            return "" + res;
        } catch(Exception err) {
            throw new Exception(err.getMessage());
        }
    }




    public static String numRome(String[] arr) throws Exception {
        int a, b;
        try {
            RomeNum operRome1 = RomeNum.valueOf(arr[0]);
            RomeNum operRome2 = RomeNum.valueOf(arr[2]);
            a = operRome1.getNumOfRome();
            b = operRome2.getNumOfRome();
            //System.out.printf("a = %d      b = %d\n", a, b);

        } catch(IllegalArgumentException error) {
            throw new Exception("Operands are not Arabian or Rome numbers within 10, or second operand equals 0");
        }
        try {
            int res = operations(arr[1], a, b);
            if(res == 0) {
                return "nulla";
            }
            if(res < 0) {
                throw new Exception("Error! Roman numerals do not have negative values");
            }
            return "" + res;
        } catch(Exception err) {
            throw new Exception(err.getMessage());
        }
    }


    public static String buildRomeString(String res) {
        int result = Integer.parseInt(res);
        String finalResult = "";
        System.out.printf("Получили: %d\n", result);
        int divResultByTen = result / 10;
        if(divResultByTen != 0) {
            if(divResultByTen < 4) {
                System.out.print("Результат: ");
                for(int i = 1; i <= divResultByTen; i++) {
                    finalResult = finalResult + "X";
                }
            } else if(divResultByTen == 4) {
                finalResult = "XL";
            } else if(divResultByTen == 5) {
                finalResult = "L";
            } else if(divResultByTen < 9) {
                finalResult = "L";
                for(int i = 1; i <= divResultByTen - 5; i++) {
                    finalResult = finalResult + "X";
                }
            } else if(divResultByTen == 9) {
                finalResult = "XC";
            } else if(divResultByTen == 10) {
                finalResult = "C";
            }
        }

        int remainderOfDivByTen = result % 10;
        if(remainderOfDivByTen == 0) {
            finalResult += "";
        } else if(remainderOfDivByTen >= 1 && remainderOfDivByTen <= 3) {
            for(int i = 1; i <= remainderOfDivByTen; i++) {
                finalResult += "I";
            }
        } else if(remainderOfDivByTen == 4) {
            finalResult += "IV";
        } else if(remainderOfDivByTen == 5) {
            finalResult += "V";
        } else if(remainderOfDivByTen < 9) {
            finalResult += "V";
            for(int i = 1; i <= remainderOfDivByTen - 5; i++) {
                finalResult += "I";
            }
        } else if(remainderOfDivByTen == 9) {
            finalResult += "IX";
        }
        System.out.println(finalResult);
        return finalResult;
    }
}

