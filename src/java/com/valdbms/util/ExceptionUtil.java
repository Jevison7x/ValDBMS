/*
 * Copyright (c) 2018, Xyneex Technologies. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * You are not meant to edit or modify this source code unless you are
 * authorized to do so.
 *
 * Please contact Xyneex Technologies, #1 Orok Orok Street, Calabar, Nigeria.
 * or visit www.xyneex.com if you need additional information or have any
 * questions.
 */
package com.valdbms.util;

/**
 *
 * @author Jevison7x
 * @since Jun 11, 2020 4:35:11 AM
 */
public class ExceptionUtil
{
    public static boolean isCause(Class<? extends Throwable> expected, Throwable exc)
    {
        return expected.isInstance(exc) || (exc != null && isCause(expected, exc.getCause()));
    }
}
