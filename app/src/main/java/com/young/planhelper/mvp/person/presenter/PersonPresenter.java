package com.young.planhelper.mvp.person.presenter;

import android.content.Context;

import com.young.planhelper.mvp.base.model.Biz;
import com.young.planhelper.mvp.base.model.IBiz;
import com.young.planhelper.mvp.base.presenter.Presenter;
import com.young.planhelper.mvp.base.view.IView;
import com.young.planhelper.mvp.person.model.IPersonBiz;
import com.young.planhelper.mvp.person.model.PersonBiz;
import com.young.planhelper.mvp.schedule.model.bean.BacklogInfo;

import java.util.List;

/**
 * @author: young
 * date:17/5/23  14:45
 */


public class PersonPresenter extends Presenter implements IPersonPresenter{

    private IPersonBiz mBiz;

    public PersonPresenter(IView view, Context context) {
        super(view, context);
        this.mBiz = new PersonBiz(context);
    }

    @Override
    public void initData() {

    }

    @Override
    public void backups(IBiz.ICallback iCallback) {
        mBiz.backups(iCallback);
    }

}
