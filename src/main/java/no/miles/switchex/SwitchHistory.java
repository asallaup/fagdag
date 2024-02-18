package no.miles.switchex;




public class SwitchHistory {

    //
    public int java1(int expression) {
        byte result = 0;
        switch (expression) {
            case 1:
                result = 3;
                break;
            case 2:
                result = 2;
                break;
            default:
                result = 100;
        }
        return result;
    }


    // String in switch
    public String java7(String expression) {
        String result = null;
        switch (expression) {
            case "a":
                result = "10";
                break;
            case "b":
                result = "20";
                break;
            default:
                result = "30";
        }
        return result;
    }

    // Enumerator in switch
    public int javaEnum(Enum expression) {
        int result = 0;
        switch (expression) {
            case Number1:
                result = 3;
                break;
            case Number2:
            case Number3:
                result = 2;
                break;
            default:
                result = 100;
        }
        return result;
    }


    // Multi matching case in switch
    public int java14(Enum expression) {
        int result = 0;
        switch (expression) {
            case Number1:
                result = 3;
                break;
            case Number2, Number3:
                result = 2;
                break;
            default:
                result = 100;
        }
        return result;
    }

    public String java14Expression(String expression) {
        return switch (expression) {
            case "a" -> "Java";
            case "b" -> "20";
            default -> "asffs";
        };
    }

    public String javaYield(Enum expression) {

        return switch (expression) {
            case Number1 -> "AAA";
            case Number2, Number3 -> {
                System.out.println(expression);
                yield "BBBB";
            }
            default -> "CCC";
        };
    }


    // switch expression


    enum Enum {
        Number1,
        Number2,
        Number3,
        Number4
    }
}
