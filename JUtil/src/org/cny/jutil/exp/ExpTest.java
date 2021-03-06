package org.cny.jutil.exp;

import junit.framework.Assert;
import org.cny.jutil.exception.AssertFailException;
import org.cny.jutil.exp.limit.R;
import org.junit.Test;

/**
 * @author Centny. 7/12/14.
 */
public class ExpTest {
    @Test
    public void testExp() throws Exception {
        Exp exp;
        //
        exp = Exp.compile("R|I,L:3");
        System.out.println(exp.valid(1).getClass());
        this.valid(exp, 4);
        this.valid(exp, 4);
        exp.valid(1F);
        this.valid(exp, 4F);
        this.valid(exp, 5F);
        exp.valid("1");
        System.out.println(exp.valid("1").getClass());
        this.valid(exp, "4");
        this.valid(exp, "5");
        //
        exp = Exp.compile("R|I,L:1~2,integer");
        exp.valid(1);
        this.valid(exp, 0);
        this.valid(exp, 3);
        exp.valid(1F);
        this.valid(exp, 0F);
        this.valid(exp, 3F);
        exp.valid("1");
        this.valid(exp, "0");
        this.valid(exp, "3");
        //
        exp = Exp.compile("R|I,R:1~2,integer");
        exp.valid(1);
        this.valid(exp, 0);
        this.valid(exp, 3);
        exp.valid(1F);
        this.valid(exp, 0F);
        this.valid(exp, 3F);
        exp.valid("1");
        this.valid(exp, "0");
        this.valid(exp, "3");
        //
        exp = Exp.compile("R|F", "L:1~2", "integer");
        exp.valid(1);
        this.valid(exp, 0);
        this.valid(exp, 3);
        exp.valid(1F);
        this.valid(exp, 0F);
        this.valid(exp, 3F);
        exp.valid("1");
        this.valid(exp, "0");
        this.valid(exp, "3");
        //
        exp = Exp.compile("R|F", "R:1~2", "integer");
        exp.valid(1);
        this.valid(exp, 0);
        this.valid(exp, 3);
        exp.valid(1F);
        this.valid(exp, 0F);
        this.valid(exp, 3F);
        exp.valid("1");
        this.valid(exp, "0");
        this.valid(exp, "3");
        //
        exp = Exp.compile("R|S", "L:1~2", "integer");
        exp.valid("1");
        this.valid(exp, "");
        //
        exp = Exp.compile("R|S", "R:1~2", "integer");
        exp.valid("1");
        this.valid(exp, "");
        //
        exp = Exp.compile("R|I", "O:1~2", "integer");
        exp.valid(1);
        this.valid(exp, 0);
        this.valid(exp, 3);
        exp.valid(1F);
        this.valid(exp, 0F);
        this.valid(exp, 3F);
        exp.valid("1");
        this.valid(exp, "0");
        this.valid(exp, "3");
        //
        exp = Exp.compile("R|F", "O:1~2", "integer");
        exp.valid(1);
        this.valid(exp, 0);
        this.valid(exp, 3);
        exp.valid(1F);
        this.valid(exp, 0F);
        this.valid(exp, 3F);
        exp.valid("1");
        this.valid(exp, "0");
        this.valid(exp, "3");
        //
        exp = Exp.compile("R|S", "O:a~b~c", "integer");
        exp.valid("a");
        exp.valid("c");
        this.valid(exp, "d");
        this.valid(exp, "3");
        //
        exp = Exp.compile("R|S", "P:^a.*c$", "integer");
        exp.valid("ac");
        exp.valid("assssc");
        exp.valid("abc");
        this.valid(exp, "d");
        this.valid(exp, "3");
        this.valid(exp, null);
        //
        exp = Exp.compile("O|S", "P:^a.*c$", "integer");
        exp.valid(null);
        this.valid(exp, "ad");
        //
        exp = Exp.compile("O|I", "R:0~", "integer");
        exp.valid(1);
        //
        exp = Exp.compile("O|I", "R:~10", "integer");
        exp.valid(1);
        //
        exp.getL();
        exp.getLimit();
        exp.getMsg();
        exp.getRtype();
        exp.getVtype();
    }

    @Test
    public void testRegex() {
        Exp.compile("O|S", "P:(^0\\d{2&3}(\\-)?\\d{7&8})$|^(1(([35][0-9])|(47)|[8][01236789]))\\d{8}", "integer");
    }

//    @Test
//    public void testExp2() throws Exception {
//        Exp exp = Exp.compile("R|S,P:^[0-9]*$,abbb");
//        exp.valid("　");
//    }

    private void valid(Exp exp, Object v) {
        try {
            exp.valid(v);
            Assert.fail();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    @Test
    public void testErr() {
        try {
            Exp exp = Exp.compile("R|S", "R:1~2", "integer");
            new R("1~1").valid(exp, new Object());
        } catch (Exception e) {

        }
        new AssertFailException(null, null);
    }
}
