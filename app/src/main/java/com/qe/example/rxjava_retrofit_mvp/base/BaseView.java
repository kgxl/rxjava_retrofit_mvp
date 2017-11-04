package com.qe.example.rxjava_retrofit_mvp.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by zjy on QE.
 */

public interface BaseView {
    //为了防止内存泄漏的风险，使用了第三方库rxlifecycle
    LifecycleTransformer bindLifecycle();
}
