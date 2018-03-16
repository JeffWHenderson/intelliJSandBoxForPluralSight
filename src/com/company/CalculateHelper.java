package com.company;

public class CalculateHelper {
    MathCommand command;
    double leftValue;
    double rightValue;
    double result;

    public void process(String statement) throws InvalidStatementException {
        // add 1.0 2.0
        String[] parts = statement.split(" ");

        if(parts.length != 3)
            throw new InvalidStatementException("Incorect number of fields", statement);


        String commandString = parts[0] = parts[0]; // add

        try {
            leftValue = Double.parseDouble(parts[1]); // 1.0
            rightValue = Double.parseDouble(parts[2]); // 2.0
        } catch(NumberFormatException event) {
            throw new InvalidStatementException("Non-numeric data", statement, event);
        }


        setCommandFromString(commandString);

        if(command == null)
            throw new InvalidStatementException("Invalid command", statement);

        CalculateBase calculator = null;

        switch(command) {
            case Add:
                calculator = new Adder(leftValue, rightValue);
                break;
            case Subtract:
                calculator = new Subtracter(leftValue, rightValue);
                break;
            case Multiply:
                calculator = new Multiplier(leftValue, rightValue);
                break;
            case Divide:
                calculator = new Divider(leftValue, rightValue);
                break;
        }


        calculator.calculate();
        result = calculator.getResult();
    }


    private void setCommandFromString(String commandString) {
        // add -> MathCommand.Add

        if(commandString.equalsIgnoreCase(MathCommand.Add.toString()))
                command = MathCommand.Add;
        else if(commandString.equalsIgnoreCase(MathCommand.Subtract.toString()))
            command = MathCommand.Subtract;
        else if(commandString.equalsIgnoreCase(MathCommand.Multiply.toString()))
            command = MathCommand.Multiply;
        else if(commandString.equalsIgnoreCase(MathCommand.Divide.toString()))
            command = MathCommand.Divide;
    }

    @Override
    public String toString() {
        char symbol = ' ';
        switch(command) {
            case Add:
                symbol = '+';
                break;
            case Subtract:
                symbol = '-';
                break;
            case Divide:
                symbol = '/';
                break;
            case Multiply:
                symbol = '*';
                break;

        }

        StringBuilder sb = new StringBuilder(20);
        sb.append(leftValue);
        sb.append(' ');
        sb.append(symbol);
        sb.append(' ');
        sb.append(rightValue);
        sb.append(" = ");
        sb.append(result);

        return sb.toString();
    }
}

