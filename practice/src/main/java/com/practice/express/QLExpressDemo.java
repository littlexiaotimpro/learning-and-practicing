package com.practice.express;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * TODO
 *
 * @author xiaosi
 * @date 2024/8/8
 * @since 1.0
 */
public class QLExpressDemo {

    private final static ExpressRunner expressRunner = new ExpressRunner();

    public static void main(String[] args) {
        DefaultContext<String, Object> context = new DefaultContext<>();
        try {
            Object execute = expressRunner.execute("1+2", context, null, true, true);
            System.out.println(execute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
