package com.example.designmodel.interpreter;

import java.util.ArrayList;

/**
 * 解析指令
 * BEGIN
 * MOVE 500,600;
 *      BEGIN LOOP 5
 *          LEFT_CLICK;
 *          DELAY 1;
 *      END;
 * RIGHT_DOWN;
 * DELAY 7200;
 * END;
 */
public class Cline {
    public static void main(String[] args) {
        Expression expression = new Sequence(new ArrayList<Expression>() {{
            add(new Move(500, 600));
            add(new Repetition(5, new Sequence(new ArrayList<Expression>() {{
                add(new LeftKeyClick());
                add(new Delay(1));
            }})));
            add(new RightKeyDown());
            add(new Delay(7200));
        }});

        expression.interpret();
    }
}
