package com.practice.utils.convert;

import com.google.common.base.Converter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName ConvertUtils
 * @Description 数据转换类
 * @Author XiaoSi
 * @Date 2019/9/1512:13
 */
@Accessors(chain = true)
@Data
public class ConvertUtils<A, B> {
    private A a;
    private B b;

    public B convertTo() {
        ConvertUtilsConverter convertUtilsConverter = new ConvertUtilsConverter();
        convertUtilsConverter.convert(a);
        return b;
    }

    public A convertBack() {
        ConvertUtilsConverter convertUtilsConverter = new ConvertUtilsConverter();
        convertUtilsConverter.reverse().convert(b);
        return a;
    }


    private class ConvertUtilsConverter extends Converter<A, B> {
        @Override
        protected B doForward(A a) {
            BeanUtils.copyProperties(a, b);
            return b;
        }

        @Override
        protected A doBackward(B b) {
            BeanUtils.copyProperties(b, a);
            return a;
        }
    }

}
