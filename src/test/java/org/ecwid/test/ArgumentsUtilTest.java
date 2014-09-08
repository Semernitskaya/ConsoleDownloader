package org.ecwid.test;

import org.ecwid.ArgumentsUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author: Semernitskaya
 */
public class ArgumentsUtilTest {

    @Test
    public void testValidGetMaxSpeedInBytes() {
        Assert.assertEquals(200 * 1024, ArgumentsUtil.getMaxSpeedInBytes("200k"));
        Assert.assertEquals(200 * 1024, ArgumentsUtil.getMaxSpeedInBytes("200K"));
        Assert.assertEquals(200 * 1024 * 1024, ArgumentsUtil.getMaxSpeedInBytes("200m"));
        Assert.assertEquals(200 * 1024 * 1024, ArgumentsUtil.getMaxSpeedInBytes("200M"));
        Assert.assertEquals(200, ArgumentsUtil.getMaxSpeedInBytes("200"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGetMaxSpeedInBytes() {
        ArgumentsUtil.getMaxSpeedInBytes("200kb");
    }

    @Test
    public void testIsArgumentsFormatValid() {
        Assert.assertTrue(ArgumentsUtil.isArgumentsFormatValid(new String[]{"-n", "5", "-l", "2000k", "-o", "output_folder", "-f", "links.txt"}));
        Assert.assertFalse(ArgumentsUtil.isArgumentsFormatValid(new String[]{"-n", "5", "-l", "2000k", "-o", "output_folder", "-f"}));
        Assert.assertFalse(ArgumentsUtil.isArgumentsFormatValid(new String[]{"-nn", "5", "-l", "2000k", "-o", "output_folder", "-f", "links.txt"}));
    }
}
