import java.util.Scanner;

class PemdasCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an equation: ");
        String equation = input.nextLine();

        double result = evaluate(equation);

        System.out.println("Result: " + result);
    }

    public static double evaluate(String equation) {
        equation = equation.replaceAll("\\s+", "");

        while (equation.contains("(")) {
            int openIndex = equation.lastIndexOf("(");
            int closeIndex = equation.indexOf(")", openIndex);

            String subEquation = equation.substring(openIndex + 1, closeIndex);
            double subResult = evaluate(subEquation);

            equation = equation.substring(0, openIndex) + subResult + equation.substring(closeIndex + 1);
        }


        while (equation.contains("^")) {
            int index = equation.indexOf("^");

            double leftOperand = Double.parseDouble(equation.substring(index - 1, index));
            double rightOperand = Double.parseDouble(equation.substring(index + 1, index + 2));

            double result = Math.pow(leftOperand, rightOperand);

            equation = equation.substring(0, index - 1) + result + equation.substring(index + 2);
        }

        while (equation.contains("*") || equation.contains("/")) {
            int index;

            if (equation.contains("*") && !equation.contains("/")) {
                index = equation.indexOf("*");
            } else if (equation.contains("/") && !equation.contains("*")) {
                index = equation.indexOf("/");
            } else {
                index = Math.min(equation.indexOf("*"), equation.indexOf("/"));
            }

            double leftOperand = Double.parseDouble(equation.substring(index - 1, index));
            double rightOperand = Double.parseDouble(equation.substring(index + 1, index + 2));

            double result;

            if (equation.charAt(index) == '*') {
                result = leftOperand * rightOperand;
            } else {
                result = leftOperand / rightOperand;
            }

            equation = equation.substring(0, index - 1) + result + equation.substring(index + 2);
        }

        while (equation.contains("+") || equation.contains("-")) {
            int index;

            if (equation.contains("+") && !equation.contains("-")) {
                index = equation.indexOf("+");
            } else if (equation.contains("-") && !equation.contains("+")) {
                index = equation.indexOf("-");
            } else {
                index = Math.min(equation.indexOf("+"), equation.indexOf("-"));
            }

            double leftOperand = Double.parseDouble(equation.substring(index - 1, index));
            double rightOperand = Double.parseDouble(equation.substring(index + 1, index + 2));

            double result;

            if (equation.charAt(index) == '+') {
                result = leftOperand + rightOperand;
            } else {
                result = leftOperand - rightOperand;
            }

            equation = equation.substring(0, index - 1) + result + equation.substring(index + 2);
        }

        return Double.parseDouble(equation);
    }
}